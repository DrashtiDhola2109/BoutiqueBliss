package boutiquestore.resources;

import admin.AdminEJBLocal;
import entities.Category;
import java.util.Collection;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 * @author 
 */
@Path("rest")
public class JakartaEE8Resource {
    
    @EJB
    private AdminEJBLocal adminEJB;
   
    
    @RolesAllowed("admin")
    @GET
    @Path("categories")
    @Produces("application/json")
    public Collection<Category> getCategories(){
        Collection<Category> categories =  adminEJB.getAllCategories();
        return categories;
    }
}
