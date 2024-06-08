/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package rest;

import admin.AdminEJBLocal;
import entities.Category;
import entities.Product;
import entities.Sizemaster;
import java.util.Collection;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;

/**
 * REST Web Service
 *
 * @author Administrator
 */
@Path("SecureAdmin")
@RequestScoped
public class SecureAdminResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of SecureAdminResource
     */
    public SecureAdminResource() {
    }

    /**
     * Retrieves representation of an instance of rest.SecureAdminResource
     * @return an instance of java.lang.String
     */
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
    
    @RolesAllowed("admin")
    @POST
    @Path("addparentcategory/{type}")
    public void addparentcategory(@PathParam("type") String type){
        adminEJB.addParentCategory(type);
    }
    
    @RolesAllowed("admin")
    @POST
    @Path("addsubcategory/{type}/{pcid}")
    public void addsubcategory(@PathParam("type") String type,@PathParam("pcid") Integer pcid){
        adminEJB.addSubCategory(type,pcid);
    }
    
    @RolesAllowed("admin")
    @GET
    @Path("getcategorybyid/{cid}")
    @Produces("application/json")
    public Collection<Category> getcategorybyid(@PathParam("cid") Integer cid){
        return (Collection<Category>) adminEJB.getCategoryById(cid);
    }
    
    
    @RolesAllowed("admin")
    @DELETE
    @Path("deletecategory/{cid}")
    public void deletecategory(@PathParam("cid") Integer cid){
        adminEJB.removeCategory(cid);
    }
    
    @RolesAllowed("admin")
    @POST
    @Path("retrievecategory/{cid}")
    public void retrieveCategory(@PathParam("cid") Integer cid){
        adminEJB.retrieveCategory(cid);
    }

    @RolesAllowed("admin")
    @PUT
    @Path("updateparentcategory/{cid}/{type}")
    public void updateparentcategory(@PathParam("cid") Integer cid,@PathParam("type") String type){
        adminEJB.updateParentCatgeory(cid, type);
    }
    
    @RolesAllowed("admin")
    @PUT
    @Path("updatesubcategory/{cid}/{type}/{pcid}")
    public void updatesubcategory(@PathParam("cid") Integer cid,@PathParam("type") String type,@PathParam("pcid") Integer pcid){
        adminEJB.updateSubCategory(cid, type,pcid);
    }
    
    @RolesAllowed("admin")
    @GET
    @Path("allsize")
    @Produces("application/json")
    public Collection<Sizemaster> getsize(){
        Collection<Sizemaster> allsize =  adminEJB.getAllSize();
        return allsize;
    }
    
    @POST
    @Path("addsize/{size}")
    public void addsize(@PathParam("size") String size){
        adminEJB.addSize(size);
    }
    
    @RolesAllowed("admin")
    @GET
    @Path("getsizebyid/{sid}")
    @Produces("application/json")
    public Collection<Sizemaster> getsizebyid(@PathParam("sid") Integer sid){
        return (Collection<Sizemaster>) adminEJB.getSizeById(sid);
    }
    
    @RolesAllowed("admin")
    @DELETE
    @Path("deletesize/{sid}")
    public void deletesize(@PathParam("sid") Integer sid){
        adminEJB.removeSize(sid);
    }
    
    @RolesAllowed("admin")
    @POST
    @Path("retrievesize/{sid}")
    public void retrieveSize(@PathParam("sid") Integer sid){
        adminEJB.retrieveSize(sid);
    }
    
    @RolesAllowed("admin")
    @PUT
    @Path("updatesize/{sid}/{size}")
    public void updatesize(@PathParam("sid") Integer sid,@PathParam("size") String size){
        adminEJB.updateSize(sid, size);
    }

    
    
    @RolesAllowed("admin")
    @GET
    @Path("products")
    @Produces("application/json")
    public Collection<Product> getProduct(){
        Collection<Product> products =  adminEJB.getAllProduct();
        System.err.println(products);
        return products;
    }
    
    @RolesAllowed("admin")
    @GET
    @Path("getproductbycid/{cid}")
    @Produces("application/json")
    public Collection<Product> getproductbycid(@PathParam("cid") Integer cid){
        return (Collection<Product>) adminEJB.getProductByCId(cid);
    }
    
    @RolesAllowed("admin")
    @GET
    @Path("getproductbycolor/{color}")
    @Produces("application/json")
    public Collection<Product> getproductbycolor(@PathParam("color") String color){
        return (Collection<Product>) adminEJB.getProductByColor(color);
    }
    
    @RolesAllowed("admin")
    @POST
    @Path("addproduct/{cid}/{sid}/{pname}/{quantity}/{price}/{color}/{description}/{pimage}/{discount}")
    public void addproduct(@PathParam("cid") Integer cid,@PathParam("sid") Integer sid,@PathParam("pname") String pname,@PathParam("quantity") Integer quantity,@PathParam("price") Integer price,@PathParam("color") String color,@PathParam("description") String description,@PathParam("pimage") String pimage,@PathParam("discount") Integer discount){
        adminEJB.addProduct(cid, sid, pname, quantity, price, color, description, pimage, discount);
    }
    
    @RolesAllowed("admin")
    @DELETE
    @Path("deleteproduct/{pid}")
    public void deleteproduct(@PathParam("pid") Integer pid){
        adminEJB.removeProduct(pid);
    }
   
    @RolesAllowed("admin")
    @GET
    @Path("getproductbyid/{pid}")
    @Produces("application/json")
    public Collection<Product> getproductbyid(@PathParam("pid") Integer pid){
        return (Collection<Product>) adminEJB.getProductById(pid);
    }
    
    @RolesAllowed("admin")
    @PUT
    @Path("updateproduct/{pid}/{cid}/{sid}/{pname}/{quantity}/{price}/{color}/{description}/{pimage}/{discount}/{isavailable}/{ishide}")
    public void updateproduct(@PathParam("pid") Integer pid,@PathParam("cid") Integer cid,@PathParam("sid") Integer sid,@PathParam("pname") String pname,@PathParam("quantity") Integer qyantity,@PathParam("price") Integer price,@PathParam("color") String color,@PathParam("description") String description,@PathParam("pimage") String pimage,@PathParam("discount") Integer discount,@PathParam("isavailable") Boolean isavailable,@PathParam("ishide") Boolean ishide){
        adminEJB.updateProduct(pid,cid, sid, pname, qyantity, price, color, description, pimage, discount,isavailable,ishide);
    }

    @RolesAllowed("admin")
    @GET
    @Path("viewalluser")
    @Produces("application/json")
    public List viewalluser(){
        return adminEJB.viewAllUsers();
    }
    
    @RolesAllowed("admin")
    @GET
    @Path("viewalladmin")
    @Produces("application/json")
    public List viewalladmin(){
        return adminEJB.viewAllAdmin();
    }
    
    @RolesAllowed("admin")
    @GET
    @Path("getusers")
    @Produces("application/json")
    public List getuser(){
        return adminEJB.getUsers();
    }
    
    @RolesAllowed("admin")
    @GET
    @Path("getallorder")
    @Produces("application/json")
    public List getallorder(){
        return adminEJB.getAllOrderDetails();
    }

    @RolesAllowed("admin")
    @GET
    @Path("getallorderbyname/{name}")
    @Produces("application/json")
    public List getorderbyname(@PathParam("name") String name){
        return adminEJB.getOrdersByUserId(name);
    }


}
