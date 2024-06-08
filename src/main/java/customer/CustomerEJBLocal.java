/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package customer;

import entities.Product;
import entities.Usermaster;
import java.util.Collection;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Administrator
 */
@Local
public interface CustomerEJBLocal {
    
    //usermaster
    void registerUser();
    Usermaster viewProfile();
    Usermaster editProfile();
    
    
    //product
    Collection<Product> viewAllProduct();
    Collection<Product> viewProductBySize(Integer sid);
    Collection<Product> viewProductByColor(String color);
    Collection<Product> viewProductByCategory(Integer cid);
    
    //order
    List viewOrderHistory(String username);
    
    void addToCart(String username,Integer pid,Integer qty);
    void placeOrder(String username);

    
    
}
