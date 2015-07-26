package nl.kristalsoftware.kristalweb.main;

import nl.kristalsoftware.kristalweb.page.PageContentHandler;

/**
 * Created by sjoerdadema on 24-05-15.
 */
public interface BaseRepository {

    public void checkForContent(String s, PageContentHandler pageContentHandler);

}
