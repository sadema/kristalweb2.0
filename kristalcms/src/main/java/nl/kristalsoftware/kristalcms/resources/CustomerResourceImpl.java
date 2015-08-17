package nl.kristalsoftware.kristalcms.resources;

import nl.kristalsoftware.kristalcms.resourcedata.CustomerData;

import javax.inject.Inject;
import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.ws.rs.core.UriInfo;

/**
 * Created by sjoerdadema on 11-06-15.
 */
public class CustomerResourceImpl implements ICustomerResource {

    @Inject
    private Session session;

    @Override
    public CustomerData getCustomer(String customerId, UriInfo uriInfo)  {
        System.out.println(uriInfo.getPath());
        CustomerData customerData = new CustomerData();
        String path = uriInfo.getPath();
        try {
            Node node = session.getNode(path);
            customerData.setNodename(node.getName());
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        return customerData;
    }
}
