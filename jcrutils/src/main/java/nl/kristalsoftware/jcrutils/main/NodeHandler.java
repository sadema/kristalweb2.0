package nl.kristalsoftware.jcrutils.main;

import nl.kristalsoftware.jcrutils.exception.AppRepositoryException;

import javax.jcr.ItemExistsException;
import javax.jcr.NodeIterator;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import java.util.Collection;

/**
 * Created by sjoerdadema on 22-05-15.
 */
public interface NodeHandler {

    boolean nodeExists(String path);

    String createFileNode(String parentNodePath, String nodename, String content) throws PathNotFoundException, ItemExistsException, AppRepositoryException;

    String getPageContent(String path) throws RepositoryException;

    NodeIterator getPageIterator(String collectionPath) throws AppRepositoryException;

    boolean removeFileNode(String nodePath) throws PathNotFoundException;

    boolean updateFileNode(String nodePath, String content) throws PathNotFoundException;
}
