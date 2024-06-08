/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package admin;

import entities.Category;
import entities.Product;
import entities.Sizemaster;
import entities.Usermaster;
import java.util.Collection;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Administrator
 */
@Local
public interface AdminEJBLocal {
    
    //category
    Collection<Category> getAllCategories();
    void addParentCategory(String type);
    void addSubCategory(String type,Integer pcid);
    Category getCategoryById(Integer cid);
    void removeCategory(Integer cid);
    void retrieveCategory(Integer cid);
    void updateParentCatgeory(Integer cid,String type);
    void updateSubCategory(Integer cid,String type,Integer pcid);
    
    
    //size
    Collection<Sizemaster> getAllSize();
    void addSize(String size);
    Sizemaster getSizeById(Integer sid);
    void removeSize(Integer sid);
    void retrieveSize(Integer sid);
    void updateSize(Integer sid,String size);
    
    //product
    Collection<Product> getAllProduct();
    Collection getProductByCId(Integer cid);
    Collection<Product> getProductByColor(String color);
    void addProduct(Integer cid,Integer sid,String pname,Integer quantity,Integer price,String color,String description,String pImage,Integer discount);
    Category getProductById(Integer pid);
    void removeProduct(Integer pid);
    void updateProduct(Integer pid,Integer cid,Integer sid,String pname,Integer quantity,Integer price,String color,String description,String pImage,Integer discount,Boolean isAvailable,Boolean isHide);
    
    //usermaster
    List viewAllUsers();
    List viewAllAdmin();
    List getUsers();
    Collection<Usermaster> addAdmin();
    
    
    //orders
    List getAllOrderDetails();
    List getOrdersByUserId(String username);
    
    //appointment
    List viewAllApppointment();
    List viewAppointmentByDate();
    
    
    
    
}
