package nl.kristalsoftware.kristalcms.cms;

import javax.jcr.RepositoryException;

/**
 * Created by sjoerdadema on 22-05-15.
 */
public interface CmsNodeHandler {

    boolean nodeExists(String path);

    boolean createFileNode(String nodePath, String content);

    String getPage(String path) throws RepositoryException;

}
