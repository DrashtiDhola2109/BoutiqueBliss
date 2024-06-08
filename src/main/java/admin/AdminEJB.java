/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package admin;

import entities.Category;
import entities.Groupmaster;
import entities.Orderitem;
import entities.Ordermaster;
import entities.Product;
import entities.Sizemaster;
import entities.Usermaster;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Administrator
 */
@DeclareRoles({"admin"})
@Stateless
public class AdminEJB implements AdminEJBLocal {

    @PersistenceContext(name = "bstoreUnit")
    EntityManager em;

    Collection<Category> categories;
    Collection<Product> products;

    @RolesAllowed("admin")
    @Override
    public Collection<Category> getAllCategories() {

        categories = em.createNamedQuery("Category.findAll").getResultList();

        return categories;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public void addParentCategory(String type) {
        Category category = new Category();
        category.setType(type);
        em.persist(category);
    }

    @Override
    public void addSubCategory(String type, Integer pcid) {
        Category pc = em.find(Category.class, pcid);
        Category category = new Category();
        category.setType(type);
        category.setParentCategoryId(pc);
        em.persist(category);
    }

    @Override
    public Category getCategoryById(Integer cid) {
        Category cat = em.find(Category.class, cid);

        return cat;
    }

    @Override
    public void removeCategory(Integer cid) {
        Category cat = em.find(Category.class, cid);

        cat.setIsHide(true);
        Collection<Product> productsbycategory = em.createNamedQuery("Product.findByCategoryId").setParameter("categoryId", cat).getResultList();

        for (Product p : productsbycategory) {
            p.setIsHide(true);
            em.merge(p);
        }
        em.merge(cat);
    }
    
    @Override
    public void retrieveCategory(Integer cid) {
        Category cat = em.find(Category.class, cid);

        cat.setIsHide(false);
        Collection<Product> productsbycategory = em.createNamedQuery("Product.findByCategoryId").setParameter("categoryId", cat).getResultList();

        for (Product p : productsbycategory) {
            p.setIsHide(false);
            em.merge(p);
        }
        em.merge(cat);
    }

    @Override
    public void updateParentCatgeory(Integer cid, String type) {
        Category cat = em.find(Category.class, cid);

        cat.setType(type);
        cat.setParentCategoryId(null);
        em.merge(cat);
    }

    @Override
    public void updateSubCategory(Integer cid, String type, Integer pcid) {
        Category cat = em.find(Category.class, cid);
        Category pcat = em.find(Category.class, pcid);
        cat.setType(type);
        cat.setParentCategoryId(pcat);
        em.merge(cat);
    }

    @Override
    public Collection<Sizemaster> getAllSize() {
        Collection<Sizemaster> sizes = em.createNamedQuery("Sizemaster.findAll").getResultList();

        return sizes;
    }

    @Override
    public void addSize(String size) {

        Sizemaster newSize = new Sizemaster();

        newSize.setSize(size);

        em.persist(newSize);

    }

    @Override
    public Sizemaster getSizeById(Integer sid) {
        Sizemaster size = em.find(Sizemaster.class, sid);
        return size;
    }

    @Override
    public void removeSize(Integer sid) {
        Sizemaster size = em.find(Sizemaster.class, sid);

        size.setIsHide(true);
        
        em.merge(size);
    }
    
    @Override
    public void retrieveSize(Integer sid) {
        Sizemaster size = em.find(Sizemaster.class, sid);

        size.setIsHide(false);
        
        em.merge(size);
    }

    @Override
    public void updateSize(Integer sid, String size) {
        Sizemaster s = em.find(Sizemaster.class, sid);
        s.setSize(size);
        em.merge(s);
    }

    @RolesAllowed("admin")
    @Override
    public Collection<Product> getAllProduct() {
        products = em.createNamedQuery("Product.findByIsHide").setParameter("isHide", false).getResultList();

        return products;
    }

    @Override
    public Collection getProductByCId(Integer cid) {

        Category c = em.find(Category.class, cid);
        Collection<Product> productsbycategory = em.createNamedQuery("Product.findByCategoryId").setParameter("categoryId", c).getResultList();
        return productsbycategory;
    }

    Collection<Product> productByColor = new ArrayList<>();

    @Override
    public Collection<Product> getProductByColor(String color) {
//        Collection<Product> productByColor = em.createNamedQuery("Product.findByColor").setParameter("color", color).getResultList();

        for (Product p : products) {

//            System.err.println("1"+p);
            if (p.getColor().contentEquals(color)) {
//                System.err.println(p);
                productByColor.add(p);
            }
        }
        return productByColor;
    }

    @Override
    public void addProduct(Integer cid, Integer sid, String pname, Integer quantity, Integer price, String color, String description, String pImage, Integer discount) {
        Category c = em.find(Category.class, cid);
        Sizemaster s = em.find(Sizemaster.class, sid);
        Product newProduct = new Product();
        newProduct.setCategoryId(c);
        newProduct.setSizeId(s);
        newProduct.setProductname(pname);
        newProduct.setQuantity(quantity);
        newProduct.setPrice(price);
        newProduct.setColor(color);
        newProduct.setDescription(description);
        newProduct.setProductImage(pImage);
        newProduct.setDiscount(discount);
        
        
         // Add the new product to the category's product collection
    c.getProductCollection().add(newProduct);

        // Add the new product to the sizemaster's product collection
        s.getProductCollection().add(newProduct);

        em.persist(newProduct);
    }

    @Override
    public Category getProductById(Integer pid) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void removeProduct(Integer pid) {
        Product p = em.find(Product.class, pid);
        p.setIsHide(true);
        em.merge(p);
    }

    @Override
    public void updateProduct(Integer pid, Integer cid, Integer sid, String pname, Integer quantity, Integer price, String color, String description, String pImage, Integer discount, Boolean isAvailable, Boolean isHide) {
        Category c = em.find(Category.class, cid);
        Sizemaster s = em.find(Sizemaster.class, sid);
        Product newProduct = em.find(Product.class, pid);
        newProduct.setCategoryId(c);
        newProduct.setSizeId(s);
        newProduct.setProductname(pname);
        newProduct.setQuantity(quantity);
        newProduct.setPrice(price);
        newProduct.setColor(color);
        newProduct.setDescription(description);
        newProduct.setProductImage(pImage);
        newProduct.setDiscount(discount);
        newProduct.setIsAvailable(isAvailable);
        newProduct.setIsHide(isHide);

        em.merge(newProduct);
    }

    @Override
    public List viewAllUsers() {

        String jpql = "SELECT u, g FROM Usermaster u LEFT JOIN u.groupmaster g";
        Query query = em.createQuery(jpql);

//        Query query = em.createQuery(queryString);
        List<Object[]> resultList = query.getResultList();

        return resultList;
    }

    @Override
    public List viewAllAdmin() {
        String jpql = "SELECT u, g FROM Usermaster u LEFT JOIN u.groupmaster g WHERE g.groupname = \"admin\"";
        Query query = em.createQuery(jpql);

//        Query query = em.createQuery(queryString);
        List<Object[]> resultList = query.getResultList();

        return resultList;
    }

    @Override
    public Collection<Usermaster> addAdmin() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List getUsers() {
        String jpql = "SELECT u, g FROM Usermaster u LEFT JOIN u.groupmaster g WHERE g.groupname = \"customer\"";
        Query query = em.createQuery(jpql);

//        Query query = em.createQuery(queryString);
        List<Object[]> resultList = query.getResultList();

        return resultList;
    }

    @Override
    public List getAllOrderDetails() {
        String queryString = "SELECT o, oi, p "
                + "FROM Ordermaster o "
                + "JOIN o.orderitemCollection oi "
                + "JOIN oi.productId p ";
        Query query = em.createQuery(queryString);

        List<Object[]> resultList = query.getResultList();

        return resultList;
    }

    @Override
    public List getOrdersByUserId(String username) {
        String queryString = "SELECT o, oi, p "
                + "FROM Ordermaster o "
                + "JOIN o.orderitemCollection oi "
                + "JOIN oi.productId p "
                + "WHERE o.username.username = :username";
        Query query = em.createQuery(queryString);
        query.setParameter("username", username);

        List<Object[]> resultList = query.getResultList();
        return resultList;
    }

    @Override
    public List viewAllApppointment() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List viewAppointmentByDate() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
