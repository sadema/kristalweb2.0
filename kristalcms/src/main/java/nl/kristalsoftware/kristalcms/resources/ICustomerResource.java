package nl.kristalsoftware.kristalcms.resources;

import nl.kristalsoftware.kristalcms.resourcedata.CustomerData;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 * Created by sjoerdadema on 11-06-15.
 */
@Path("/cms")
@Consumes("application/json,application/xml")
@Produces("application/json,application/xml")
public interface ICustomerResource {

    @GET
    @Path("{customerId}")
    CustomerData getCustomer(@PathParam("customerId") String customerId, @Context UriInfo uriInfo);

}
