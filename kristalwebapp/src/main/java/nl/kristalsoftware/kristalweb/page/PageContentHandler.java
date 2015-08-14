package nl.kristalsoftware.kristalweb.page;

import nl.kristalsoftware.jcrutils.exception.AppRepositoryException;

import javax.jcr.ItemExistsException;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;

/**
 * Created by sjoerdadema on 22-05-15.
 */
public interface PageContentHandler {

    public String getPage(String contextPath, String customerName, String nodePath);

    public boolean pageExists(String nodePath);

    public String createPage(String pageNodePath, String id, String content) throws PathNotFoundException, AppRepositoryException, ItemExistsException;

    public boolean removePage(String nodePath) throws PathNotFoundException;

}
