package nl.kristalsoftware.kristalweb.resource;

import nl.kristalsoftware.kristalweb.resourcedata.PageData;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * Created by sjoerdadema on 10-07-15.
 */
@Path("/site/prima")
@Consumes("application/xml")
@Produces("application/xml")
public interface PageResource {

    @POST
    Response createPage(PageData data, @Context UriInfo uriInfo);
}
