package nl.kristalsoftware.kristalcms.cms;

import nl.kristalsoftware.jcrutils.exception.AppRepositoryException;
import nl.kristalsoftware.jcrutils.main.NodeHandler;
import nl.kristalsoftware.kristalcms.resourcedata.PageCollectionData;
import nl.kristalsoftware.kristalcms.resourcedata.PageData;

import javax.inject.Inject;
import javax.jcr.*;
import java.util.logging.Logger;

/**
 * Created by sjoerdadema on 22-05-15.
 */
public class CmsContentHandlerImpl implements CmsContentHandler {

    @Inject
    private Logger log;

    @Inject
    private NodeHandler pageNodeHandler;

    public CmsContentHandlerImpl() {}

    @Override
    public PageCollectionData getPageCollection(String nodePath) throws PathNotFoundException, AppRepositoryException {
        PageCollectionData pageCollectionData = new PageCollectionData();
        if (pageNodeHandler.nodeExists(nodePath)) {
            NodeIterator nodeIter = pageNodeHandler.getPageIterator(nodePath);
            while(nodeIter.hasNext()) {
                Node pageNode = nodeIter.nextNode();
                PageData pageData = new PageData();
                try {
                    pageData.setNodename(pageNode.getName());
                    pageCollectionData.getPageCollection().add(pageData);
                } catch (RepositoryException e) {
                    throw new AppRepositoryException(e.getMessage(), e);
                }
            }
        }
        else {
            throw new PathNotFoundException("Node path not found: " + nodePath);
        }
        return pageCollectionData;
    }

    @Override
    public String getPage(String nodePath) throws PathNotFoundException {
        String page = "";
        if (pageNodeHandler.nodeExists(nodePath)) {
            try {
                page = pageNodeHandler.getPageContent(nodePath);
            } catch (RepositoryException e) {
                log.severe(e.getMessage());
            }
        }
        else {
            throw new PathNotFoundException("Node path not found: " + nodePath);
        }
        return page;
    }

    @Override
    public boolean pageExists(String nodePath) {
        return pageNodeHandler.nodeExists(nodePath);
    }

    @Override
    public String createPage(String pageNodePath, String id, String content) throws PathNotFoundException, AppRepositoryException, ItemExistsException {
        String nodePath = new StringBuilder(pageNodePath).append('/').append(id).toString();
        if (pageNodeHandler.nodeExists(nodePath)) {
            log.info(new StringBuilder("Node: ").append(nodePath).append(" exists").toString());
            throw new ItemExistsException();
        }
        return pageNodeHandler.createFileNode(pageNodePath, id, content);
    }

}
