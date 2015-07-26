package nl.kristalsoftware.kristalweb.resource;

import nl.kristalsoftware.kristalweb.exception.AppRepositoryException;
import nl.kristalsoftware.kristalweb.page.PageContentHandler;
import nl.kristalsoftware.kristalweb.resourcedata.PageData;

import javax.inject.Inject;
import javax.jcr.ItemExistsException;
import javax.jcr.PathNotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.logging.Logger;

/**
 * Created by sjoerdadema on 10-07-15.
 */
public class PageResourceImpl implements PageResource {

    @Inject
    private Logger log;

    @Inject
    private PageContentHandler pageContentHandler;

    @Override
    public Response createPage(PageData data, @Context UriInfo uriInfo) {
        Response response = null;
        String parentNodePath = uriInfo.getPath();
        String id = data.getNodename();
        String content = data.getPageContent();
        String newPath = null;
        log.info(new StringBuilder("create page: ").append(id).toString());
        try {
            newPath = pageContentHandler.createPage(parentNodePath, id, content);
            if (newPath != null) {
                response = Response.created(URI.create(newPath)).build();
            }
        } catch (PathNotFoundException e) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        } catch (AppRepositoryException e) {
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        } catch (ItemExistsException e) {
            throw new WebApplicationException(422);     // unprocessable entity
        }
        return response;
    }
}
