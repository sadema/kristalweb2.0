package nl.kristalsoftware.kristalcms.cms;

import javax.inject.Inject;
import javax.jcr.RepositoryException;
import java.util.logging.Logger;

/**
 * Created by sjoerdadema on 22-05-15.
 */
public class CmsContentHandlerImpl implements CmsContentHandler {

    @Inject
    private Logger log;

    @Inject
    private CmsNodeHandler pageNodeHandler;

    public CmsContentHandlerImpl() {}

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
/*
        StringBuilder page = new StringBuilder("<html><head><title>KristalCMS</title></head><body>");
        page.append("<h1>Hello ").append(customerName).append("</h1>");
        page.append("<p>").append(nodePath).append("</p>");
        page.append("</body></html>");
        return page;
*/
    }

    @Override
    public boolean pageExists(String nodePath) {
        return pageNodeHandler.nodeExists(nodePath);
    }

    @Override
    public boolean createPage(String nodePath, String content) {
        return pageNodeHandler.createFileNode(nodePath, content);
    }

}
