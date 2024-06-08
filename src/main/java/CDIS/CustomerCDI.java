/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIS;

import client.AdminRestClient;
import client.CustomerRestClient;
import entities.Product;
import java.util.ArrayList;
import java.util.Collection;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Administrator
 */
@Named(value = "customerCDI")
@RequestScoped
public class CustomerCDI {

    static Collection<Product> products;
    GenericType<Collection<Product>> gproducts;
    CustomerRestClient crc;
    Response rs;
    String message="";

    
    public void loadAllProducts(){
        try{
            message="";
        rs = crc.viewproducts(Response.class);
        products = rs.readEntity(gproducts);
        }
        catch(Exception e){
             message="You are Forbidden to access";
        }
    }
    /**
     * Creates a new instance of CustomerCDI
     */
    public CustomerCDI() {
        crc = new CustomerRestClient();
        products = new ArrayList<>();
        gproducts = new GenericType<Collection<Product>>() {};
        
        loadAllProducts();
    }
    
    

    public Collection<Product> getProducts() {
//        rs = crc.viewproducts(Response.class);
//        products = rs.readEntity(gproducts);
        return products;
    }

    public void setProducts(Collection<Product> products) {
        this.products = products;
    }

    Collection<Product> search = new ArrayList<>();

    Collection<Product> allprod = new ArrayList<>();

    public Collection<Product> getAllprod() {
        return allprod;
    }

    public void setAllprod(Collection<Product> allprod) {
        this.allprod = allprod;
    }

    
    public Collection<Product> getSearch() {
        rs = crc.viewproducts(Response.class);
        search = rs.readEntity(gproducts);
        return search;
    }

    public void setSearch(Collection<Product> search) {
        this.search = search;
    }
    
//    public String findP(){
//        return 
//    }
    
    public Collection<Product> filterProductsByCategory(String category) {
        System.err.println("in");
        Collection<Product> filteredProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getCategoryId().getType().equalsIgnoreCase(category)) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }
    
    public void test(){
        System.err.println("CDIS.CustomerCDI.test()");
    }
    
    String searctText;

    public String getSearctText() {
        return searctText;
    }

    public void setSearctText(String searctText) {
        this.searctText = searctText;
    }
    

    

}
