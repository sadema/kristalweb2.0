package nl.kristalsoftware.kristalweb.page;

import nl.kristalsoftware.kristalweb.exception.AppRepositoryException;

import javax.jcr.ItemExistsException;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;

/**
 * Created by sjoerdadema on 22-05-15.
 */
public interface PageNodeHandler {

    boolean nodeExists(String path);

    String createFileNode(String parentNodePath, String nodename, String content) throws PathNotFoundException, ItemExistsException, AppRepositoryException;

    String getPage(String path) throws RepositoryException;

}
