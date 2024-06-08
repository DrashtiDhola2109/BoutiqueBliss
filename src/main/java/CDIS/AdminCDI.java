/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIS;

import admin.AdminEJB;
import client.AdminRestClient;
import entities.Category;
import entities.Product;
import entities.Sizemaster;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Administrator
 */
@Named(value = "adminCDI")
@RequestScoped
public class AdminCDI {

    Category c;
    Collection<Category> cats;
    GenericType<Collection<Category>> gcats;
    
    Sizemaster size;
    Collection<Sizemaster> sizes;
    GenericType<Collection<Sizemaster>> gsizes;
    Collection<Product> products;
    GenericType<Collection<Product>> gproducts;
    AdminRestClient arc;
    Response rs;
    String message = "";
    
    
    
    public AdminCDI() {
        arc = new AdminRestClient();
        cats = new ArrayList<>();
        gcats = new GenericType<Collection<Category>>() {
        };
        sizes = new ArrayList<>();
        gsizes = new GenericType<Collection<Sizemaster>>() {
        };
        products = new ArrayList<>();
        gproducts = new GenericType<Collection<Product>>() {
        };
        c = new Category();
        size = new Sizemaster();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Category getC() {
        return c;
    }

    public void setC(Category c) {
        this.c = c;
    }

    public void openNew() {
        this.c = new Category();
        this.size = new Sizemaster();
    }


    public Collection<Category> getCats() throws IOException {
        try {
            arc = new AdminRestClient();
            rs = arc.getCategories(Response.class);
            cats = rs.readEntity(gcats);
            message="";
            return cats;
        } catch (Exception e) {

            message = "You are Forbidden to access";
            
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect(externalContext.getRequestContextPath()+ "/login-signup.jsf");

        }
        return null;
    }

    public void setCats(Collection<Category> cats) {
        this.cats = cats;
    }

    public Collection<Product> getProducts() {

        rs = arc.getProduct(Response.class);
        products = rs.readEntity(gproducts);
        return products;
    }

    public void setProducts(Collection<Product> products) {
        this.products = products;
    }


    public Collection<Category> getAllCategories() {
        rs = arc.getCategories(Response.class);
        cats = rs.readEntity(gcats);

        return cats;
    }

    Integer pid;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public void saveProduct() {
        System.err.println("save");

        if (getPid() == null) {
            this.arc.addparentcategory(c.getType());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Category Added"));

            PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
            PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
        } else {
            this.arc.addsubcategory(c.getType(), pid.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Category Added"));

            PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
            PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
        }

    }

    public void deleteCategory(Integer id) {
        System.err.println("delete");

        this.arc.deletecategory(id.toString());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Product Deleted", null));

        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }
    
    
    
    public void retrieveCategory(Integer id) {
        System.err.println("retrieve");

        this.arc.retrieveCategory(id.toString());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Category Retrieved", null));

        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }
    
    
    public void updateProduct() {
        System.err.println("update");
        
        

        if (getPid() == null) {
            System.err.println(c.getCategoryId().toString()+c.getType());
            this.arc.updateparentcategory(c.getCategoryId().toString(), c.getType());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Category Updated"));

            PrimeFaces.current().executeScript("PF('updateCategoryDialog').hide()");
            PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
        } else {
            System.err.println("else update");
            System.out.println(c.getCategoryId().toString()+c.getType()+pid);
            this.arc.updatesubcategory(c.getCategoryId().toString(), c.getType(), pid.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Category Updated"));

            PrimeFaces.current().executeScript("PF('updateCategoryDialog').hide()");
            PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
        }

    }
    
    
    //sizemaster methods

    public Collection<Sizemaster> getSizes() {
        
            arc = new AdminRestClient();
            rs = arc.getsize(Response.class);
            sizes = rs.readEntity(gsizes);
            message="";
            return sizes;
    }

    public void setSizes(Collection<Sizemaster> sizes) {
        this.sizes = sizes;
    }

    public Sizemaster getSize() {
        return size;
    }

    public void setSize(Sizemaster size) {
        this.size = size;
    }
    
    
    public void deleteSize(Integer id) {
        System.err.println("delete");

        this.arc.deletesize(id.toString());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Size Deleted", null));

        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }
    
    
    public void retrieveSize(Integer id) {
        System.err.println("retrieve");

        this.arc.retrieveSize(id.toString());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Size Retrieved", null));

        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }
    
    public void updateSize() {
        System.err.println("update size");

        this.arc.updatesize(size.getSizeId().toString(),size.getSize());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Size Updated", null));

        PrimeFaces.current().executeScript("PF('updatesizeDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }
    
    public void addSize() {
        System.err.println("add size");

        this.arc.addsize(size.getSize());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Size Added"));

        PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }
    
    
    
    
    

}
