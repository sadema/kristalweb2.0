package nl.kristalsoftware.kristalcms.cms;

/**
 * Created by sjoerdadema on 22-05-15.
 */
public interface CmsContentHandler {

    public String getPage(String contextPath, String customerName, String nodePath);

    public boolean pageExists(String nodePath);

    public boolean createPage(String nodePath, String content);

}
