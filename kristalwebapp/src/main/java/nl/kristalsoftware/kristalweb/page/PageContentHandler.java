package nl.kristalsoftware.kristalweb.page;

import nl.kristalsoftware.jcrutils.exception.AppRepositoryException;

import javax.jcr.ItemExistsException;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;

/**
 * Created by sjoerdadema on 22-05-15.
 */
public interface PageContentHandler {

    String getPage(String contextPath, String customerName, String nodePath);

    boolean pageExists(String nodePath);

    String createPage(String pageNodePath, String id, String content) throws PathNotFoundException, AppRepositoryException, ItemExistsException;

    boolean updatePage(String pageNodePath, String content) throws PathNotFoundException;

    boolean removePage(String nodePath) throws PathNotFoundException;

}
