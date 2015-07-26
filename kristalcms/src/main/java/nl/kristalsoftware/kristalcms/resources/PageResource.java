package nl.kristalsoftware.kristalcms.resources;

import nl.kristalsoftware.kristalcms.cms.CmsContentHandler;
import nl.kristalsoftware.kristalcms.resourcedata.PageData;

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
 * Created by sjoerdadema on 15-07-15.
 */
public class PageResource implements IPageResource {

    @Inject
    private Logger log;

    @Inject
    private CmsContentHandler contentHandler;

    @Override
    public Response createPage(String customerId, PageData data, @Context UriInfo uriInfo) {
        Response response = null;
        String parentNodePath = uriInfo.getPath();
        String id = data.getNodename();
        String content = data.getPageContent();
        String newPath = null;
        /*
        log.info(new StringBuilder("create page: ").append(id).toString());
        try {
            newPath = contentHandler.createPage(parentNodePath, id, content);
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
        */
        return response;
    }

    @Override
    public void updatePage(String customerId, String pageId, PageData data, @Context UriInfo uriInfo) {

    }
}
