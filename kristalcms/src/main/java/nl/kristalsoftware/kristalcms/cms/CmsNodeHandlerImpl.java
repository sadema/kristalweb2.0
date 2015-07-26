package nl.kristalsoftware.kristalcms.cms;

import javax.inject.Inject;
import javax.jcr.*;
import java.io.ByteArrayInputStream;
import java.util.logging.Logger;

/**
 * Created by sjoerdadema on 22-05-15.
 */
public class CmsNodeHandlerImpl implements CmsNodeHandler {

    @Inject
    private Logger logger;

    @Inject
    //private Session session;

    public CmsNodeHandlerImpl() {}

    @Override
    public boolean nodeExists(String path) {
        boolean nodeExists = false;
//        try {
//            nodeExists = session.nodeExists(path);
//        } catch (RepositoryException e) {
//            e.printStackTrace();
//        }
        return nodeExists;
    }

    @Override
    public boolean createFileNode(String nodePath, String content) {
        boolean successFlag = false;
//        int lastElementIndex = nodePath.lastIndexOf('/');
//        String nodeName = nodePath.substring(lastElementIndex+1);   //remove slash
//        logger.info("nodeName: " + nodeName);
//        String parentNodePath = nodePath.substring(0, lastElementIndex);
//        logger.info("ParentNodePath: " + parentNodePath);
//        try {
//            Node parentNode = session.getNode(parentNodePath);
//            if (parentNode != null) {
//                ByteArrayInputStream is = new ByteArrayInputStream(content.getBytes());
//                Binary binaryData = session.getValueFactory().createBinary(is);
//                Node pageNode = parentNode.addNode(nodeName, "nt:file");
//                Node contentNode = pageNode.addNode("jcr:content", "nt:resource");
//                contentNode.setProperty("jcr:mimeType", "text/html");
//                contentNode.setProperty("jcr:encoding", "UTF-8");
//                contentNode.setProperty("jcr:data", binaryData);
//            }
//            try {
//                session.save();
//                successFlag = true;
//            } catch (RepositoryException e) {
//                e.printStackTrace();
//            }
//        } catch (RepositoryException e) {
//            e.printStackTrace();
//        }
        return successFlag;
    }

    @Override
    public String getPage(String path) throws RepositoryException {
        String content = null;
//        Node pageNode = session.getNode(path);
//        Node contentNode = pageNode.getNode("jcr:content");
//        Property dataProperty = contentNode.getProperty("jcr:data");
//        content = dataProperty.getString();
        return content;
    }
}
