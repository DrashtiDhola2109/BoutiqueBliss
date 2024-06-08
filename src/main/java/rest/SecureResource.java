/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package rest;

import admin.AdminEJBLocal;
import admin.SecureBean;
import entities.Category;
import java.util.Collection;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Administrator
 */
@Path("SecureResource")
@RequestScoped
public class SecureResource {

    @EJB SecureBean sb;
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of SecureResource
     */
    public SecureResource() {
    }

    /**
     * Retrieves representation of an instance of rest.SecureResource
     * @return an instance of java.lang.String
     */
    @RolesAllowed("customer")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayHello() {
        //TODO return proper representation object
       // throw new UnsupportedOperationException();
       return sb.saySecureHello()+ " from Rest Client";
    }
    
    
    @EJB
    private AdminEJBLocal adminEJB;
    @RolesAllowed("admin")
    @GET
    @Path("categories")
    @Produces("application/json")
    public Collection<Category> getCategories(){
        Collection<Category> categories =  adminEJB.getAllCategories();
        System.err.println(categories);
        return categories;
    }

}
