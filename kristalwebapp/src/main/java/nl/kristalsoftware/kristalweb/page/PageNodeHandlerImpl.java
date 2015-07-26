package nl.kristalsoftware.kristalweb.page;

import nl.kristalsoftware.kristalweb.exception.AppRepositoryException;

import javax.inject.Inject;
import javax.jcr.*;
import java.io.ByteArrayInputStream;
import java.util.logging.Logger;

/**
 * Created by sjoerdadema on 22-05-15.
 */
public class PageNodeHandlerImpl implements PageNodeHandler {

    @Inject
    private Logger logger;

    @Inject
    private Session session;

    public PageNodeHandlerImpl() {}

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
    public String getPage(String path) throws RepositoryException {
        String content = null;
        Node pageNode = session.getNode(path);
        Node contentNode = pageNode.getNode("jcr:content");
        Property dataProperty = contentNode.getProperty("jcr:data");
        content = dataProperty.getString();
        return content;
    }
}
