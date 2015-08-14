package nl.kristalsoftware.kristalcms.cms;

import nl.kristalsoftware.jcrutils.exception.AppRepositoryException;

import javax.jcr.ItemExistsException;
import javax.jcr.PathNotFoundException;

/**
 * Created by sjoerdadema on 22-05-15.
 */
public interface CmsContentHandler {

    public String getPage(String contextPath, String customerName, String nodePath);

    public boolean pageExists(String nodePath);

    public String createPage(String parentNodePath, String id, String content) throws PathNotFoundException, AppRepositoryException, ItemExistsException;

}
