package nl.kristalsoftware.kristalweb.page;

import nl.kristalsoftware.jcrutils.exception.AppRepositoryException;
import nl.kristalsoftware.jcrutils.main.NodeHandler;

import javax.inject.Inject;
import javax.jcr.ItemExistsException;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import java.util.logging.Logger;

/**
 * Created by sjoerdadema on 22-05-15.
 */
public class PageContentHandlerImpl implements PageContentHandler {

    @Inject
    private Logger log;

    @Inject
    private NodeHandler pageNodeHandler;

    public PageContentHandlerImpl() {}

    @Override
    public String getPage(String contextPath, String customerName, String nodePath) {
        String page = "";
        if (pageNodeHandler.nodeExists(nodePath)) {
            try {
                page = pageNodeHandler.getPage(nodePath);
            } catch (RepositoryException e) {
                log.info("Node path not found: " + nodePath);
            }
        }
        else {
            log.info("Node path not found: " + nodePath);
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

    @Override
    public boolean updatePage(String pageNodePath, String content) throws PathNotFoundException {
        boolean success = false;
        if (this.pageExists(pageNodePath)) {
            success = pageNodeHandler.updateFileNode(pageNodePath, content);
        }
        else {
            throw new PathNotFoundException(pageNodePath + " not found");
        }
        return success;
    }

    @Override
    public boolean removePage(String pageNodePath) throws PathNotFoundException {
        boolean success = false;
        if (pageNodeHandler.nodeExists(pageNodePath)) {
            success = pageNodeHandler.removeFileNode(pageNodePath);
        }
        else {
            throw new PathNotFoundException(pageNodePath + " not found");
        }
        return success;
    }

}
