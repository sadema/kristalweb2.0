package nl.kristalsoftware.kristalcms.cms;

import nl.kristalsoftware.jcrutils.exception.AppRepositoryException;
import nl.kristalsoftware.kristalcms.resourcedata.PageCollectionData;

import javax.jcr.ItemExistsException;
import javax.jcr.PathNotFoundException;

/**
 * Created by sjoerdadema on 22-05-15.
 */
public interface CmsContentHandler {

    PageCollectionData getPageCollection(String nodePath) throws PathNotFoundException, AppRepositoryException;

    String getPage(String nodePath) throws PathNotFoundException;

    boolean pageExists(String nodePath);

    String createPage(String parentNodePath, String id, String content) throws PathNotFoundException, AppRepositoryException, ItemExistsException;

}
