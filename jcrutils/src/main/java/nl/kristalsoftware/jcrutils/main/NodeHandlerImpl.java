package nl.kristalsoftware.jcrutils.main;

import nl.kristalsoftware.jcrutils.exception.AppRepositoryException;

import javax.inject.Inject;
import javax.jcr.*;
import java.io.ByteArrayInputStream;
import java.util.logging.Logger;

/**
 * Created by sjoerdadema on 22-05-15.
 */
public class NodeHandlerImpl implements NodeHandler {

    @Inject
    private Logger logger;

    @Inject
    private Session session;

    public NodeHandlerImpl() {}

    @Override
    public boolean nodeExists(String path) {
        boolean nodeExists = false;
        try {
            nodeExists = session.nodeExists(path);
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        return nodeExists;
    }

    @Override
    public String getPage(String path) throws RepositoryException {
        String content = null;
        Node pageNode = session.getNode(path);
        Node contentNode = pageNode.getNode("jcr:content");
        Property dataProperty = contentNode.getProperty("jcr:data");
        content = dataProperty.getString();
        return content;
    }

    @Override
    public String createFileNode(String parentNodePath, String id, String content) throws PathNotFoundException, ItemExistsException, AppRepositoryException {
        String newPagePath = null;
        try {
            Node parentNode = session.getNode(parentNodePath);
            if (parentNode != null) {
                ByteArrayInputStream is = new ByteArrayInputStream(content.getBytes());
                Binary binaryData = session.getValueFactory().createBinary(is);
                Node pageNode = parentNode.addNode(id, "nt:file");
                newPagePath = pageNode.getPath();
                Node contentNode = pageNode.addNode("jcr:content", "nt:resource");
                contentNode.setProperty("jcr:mimeType", "text/html");
                contentNode.setProperty("jcr:encoding", "UTF-8");
                contentNode.setProperty("jcr:data", binaryData);
            }
            try {
                session.save();
            } catch (RepositoryException e) {
                throw new AppRepositoryException(e.getMessage(), e);
            }
        } catch (RepositoryException e) {
            throw new AppRepositoryException(e.getMessage(), e);
        }
        return newPagePath;
    }

    @Override
    public boolean removeFileNode(String nodePath) throws PathNotFoundException {
        boolean success = true;
        try {
            session.removeItem(nodePath);
            session.save();
        } catch (RepositoryException e) {
            success = false;
            logger.severe(e.getMessage());
        }
        return success;
    }

    @Override
    public boolean updateFileNode(String nodePath, String content) throws PathNotFoundException {
        boolean success = true;
        try {
            Node pageNode = session.getNode(nodePath);
            if (pageNode.isNodeType("nt:file")) {
                ByteArrayInputStream is = new ByteArrayInputStream(content.getBytes());
                Binary binaryData = session.getValueFactory().createBinary(is);
                Node contentNode = pageNode.getNode("jcr:content");
                contentNode.setProperty("jcr:data", binaryData);
                session.save();
            }
            else {
                success = false;
                logger.severe("Node is not of type: nt:file");
            }
        } catch (RepositoryException e) {
            success = false;
            logger.severe(e.getMessage());
        }
        return success;
    }
}
