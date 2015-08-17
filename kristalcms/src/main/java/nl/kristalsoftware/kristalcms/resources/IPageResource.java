package nl.kristalsoftware.kristalcms.resources;

import nl.kristalsoftware.kristalcms.resourcedata.PageCollectionData;
import nl.kristalsoftware.kristalcms.resourcedata.PageData;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * Created by sjoerdadema on 10-07-15.
 */
@Path("/cms")
@Consumes("application/json,application/xml")
@Produces("application/json,application/xml")
public interface IPageResource {

    @GET
    @Path("{customerId}/pages")
    PageCollectionData getPageCollection(@PathParam("customerId") String customerId, @Context UriInfo uriInfo);

    @GET
    @Path("{customerId}/pages/{pageId}")
    PageData getPage(@PathParam("customerId") String customerId, @PathParam("pageId") String pageId, @Context UriInfo uriInfo);

    @POST
    @Path("{customerId}/pages")
    Response createPage(@PathParam("customerId") String customerId, PageData data, @Context UriInfo uriInfo);

    @PUT
    @Path("{customerId}/pages/{pageId}")
    void updatePage(@PathParam("customerId") String customerId, @PathParam("pageId") String pageId, PageData data, @Context UriInfo uriInfo);

}
