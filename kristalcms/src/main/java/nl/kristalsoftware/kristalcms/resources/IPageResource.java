package nl.kristalsoftware.kristalcms.resources;

import nl.kristalsoftware.kristalcms.resourcedata.PageData;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * Created by sjoerdadema on 10-07-15.
 */
@Path("/cms")
@Consumes("application/xml")
@Produces("application/xml")
public interface IPageResource {

    @POST
    @Path("{customerId}/pages")
    Response createPage(@PathParam("customerId") String customerId, PageData data, @Context UriInfo uriInfo);

    @PUT
    @Path("{customerId}/pages/{pageId}")
    void updatePage(@PathParam("customerId") String customerId, @PathParam("pageId") String pageId, PageData data, @Context UriInfo uriInfo);

}
