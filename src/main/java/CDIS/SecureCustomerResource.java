/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package CDIS;

import customer.CustomerEJBLocal;
import entities.Product;
import java.util.Collection;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.enterprise.context.RequestScoped;

/**
 * REST Web Service
 *
 * @author Administrator
 */
@Path("SecureCustomer")
@RequestScoped
public class SecureCustomerResource {

    @Context
    private UriInfo context;
    
    @EJB
    private CustomerEJBLocal customerEjb;


    /**
     * Creates a new instance of SecureCustomerResource
     */
    public SecureCustomerResource() {
    }

    /**
     * Retrieves representation of an instance of CDIS.SecureCustomerResource
     * @return an instance of java.lang.String
     */
    @GET
    @Path("viewproduct")
    @Produces("application/json")
    public Collection<Product> viewproducts(){
        Collection<Product> allproduct =  customerEjb.viewAllProduct();
        return allproduct;
    }

}
