/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package customer;

import entities.Cart;
import entities.Category;
import entities.Orderitem;
import entities.Ordermaster;
import entities.Product;
import entities.Sizemaster;
import entities.Usermaster;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Administrator
 */
@Stateless
public class CustomerEJB implements CustomerEJBLocal {

    @PersistenceContext(name = "bstoreUnit")
    EntityManager em;

    @Override
    public void registerUser() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Usermaster viewProfile() {
        Usermaster user = em.createNamedQuery("Usermaster.findByUsername",Usermaster.class).setParameter("username", "drashti").getSingleResult();
        
        
        return user;
    }

    @Override
    public Usermaster editProfile() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    Collection<Product> products;

    @Override
    public Collection<Product> viewAllProduct() {
        System.err.println("1" + products);

        products = em.createNamedQuery("Product.findByIsHide").setParameter("isHide", false).getResultList();
        return products;
    }

    Collection<Product> productbysize;

    @Override
    public Collection<Product> viewProductBySize(Integer sid) {
        Sizemaster s = em.find(Sizemaster.class, sid);
        productbysize = em.createNamedQuery("Product.findBySizeId").setParameter("sizeId", s).getResultList();
        return productbysize;
    }

    Collection<Product> productByColor = new ArrayList<>();

    @Override
    public Collection<Product> viewProductByColor(String color) {
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
    public Collection<Product> viewProductByCategory(Integer cid) {
        Category c = em.find(Category.class, cid);
        Collection<Product> productsbycategory = em.createNamedQuery("Product.findByCategoryId").setParameter("categoryId", c).getResultList();
        return productsbycategory;
    }

    @Override
    public List viewOrderHistory(String username) {
        System.out.println("...");
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

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    public static String generateRandomString(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(randomIndex));
        }
        return sb.toString();
    }

    @Override
    public void placeOrder(String username) {
        String orderId = generateRandomString(10);
        //inserting into ordermaster
        Usermaster user =  em.find(Usermaster.class, username);
        Ordermaster order = new Ordermaster();
        order.setUsername(user);
        order.setOrderDate(new Date());
        order.setCgst(2.5f);
        order.setSgst(2.5f);
        order.setOrderId(orderId);
        em.persist(order);
        
        Ordermaster oid = em.find(Ordermaster.class, order.getOrderId());
        System.out.println("Recently inserted Ordermaster ID: " + oid);
        System.err.println("xyz------");
        
        
        float pTotal;
        Product p;
        int gTotal = 0;
        String queryString = "SELECT c FROM Cart c WHERE c.username.username = :username";
        Query query = em.createQuery(queryString);
        query.setParameter("username", username);
        
        Collection<Cart> c = query.getResultList();
        
        for(Cart ci : c)
        {
            
            System.out.println(ci.getUsername());
            //counting per product total
            p = em.find(Product.class, ci.getProductId().getProductId());
            Integer AmountBD = ci.getQuantity()*p.getPrice();
            pTotal = (ci.getQuantity()*p.getPrice())-(ci.getQuantity()*p.getPrice()*(float)p.getDiscount()/100);
            
            //inserting into orderitem
            Orderitem orderitem = new Orderitem();
            orderitem.setOrderId(oid);
            orderitem.setProductId(p);
            orderitem.setProductQuantity(ci.getQuantity());
            orderitem.setTotalAmount((int)pTotal);
            em.persist(orderitem);
            System.out.println(pTotal);
            
            gTotal += (int)pTotal;
            
            
        }
        
        Ordermaster updateOrder = em.find(Ordermaster.class, order.getOrderId());
            float cgst = gTotal * (updateOrder.getSgst() / 100);
            float sgst = gTotal * (updateOrder.getSgst() / 100);
            System.out.println(cgst);
            System.out.println(sgst);
            gTotal += cgst + sgst;
            updateOrder.setGrandTotal(gTotal);
            System.out.println(updateOrder.getGrandTotal());
            em.merge(updateOrder);
            
        
//        Integer gTotal = 
        
        
    }

    @Override
    public void addToCart(String username,Integer pid,Integer qty) {
        Usermaster user =  em.find(Usermaster.class, username);
        Product p = em.find(Product.class,pid);
        Cart newItem = new Cart();
        newItem.setProductId(p);
        newItem.setQuantity(qty);
        newItem.setUsername(user);
        
        em.persist(newItem);
    }
}
