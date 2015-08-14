package nl.kristalsoftware.jcrutils.main;

import nl.kristalsoftware.jcrutils.exception.AppRepositoryException;

import javax.jcr.ItemExistsException;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;

/**
 * Created by sjoerdadema on 22-05-15.
 */
public interface NodeHandler {

    boolean nodeExists(String path);

    String createFileNode(String parentNodePath, String nodename, String content) throws PathNotFoundException, ItemExistsException, AppRepositoryException;

    String getPage(String path) throws RepositoryException;

    boolean removeFileNode(String nodePath) throws PathNotFoundException;

    boolean updateFileNode(String nodePath, String content) throws PathNotFoundException;
}
