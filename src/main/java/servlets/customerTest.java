/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import customer.CustomerEJBLocal;
import entities.Orderitem;
import entities.Ordermaster;
import entities.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "customerTest", urlPatterns = {"/customerTest"})
public class customerTest extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @EJB
    private CustomerEJBLocal customerEJB;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet customerTest</title>");
            out.println("</head>");
            out.println("<body>");

            Collection<Product> products = customerEJB.viewAllProduct();
            out.println("<h2>Products:</h2>");
            for (Product p : products) {
                out.println("<p>Product Name: " + p.getProductname() + "</p>");
                out.println("<p>Product Color: " + p.getColor() + "</p>");
                out.println("<p>Product Discount: " + p.getDiscount() + "</p>");
                out.println("<hr>");
            }

            Collection<Product> ps = customerEJB.viewProductByCategory(5);
            out.println("<h2>Products By Category:</h2>");
            for (Product p : ps) {
                out.println("<p>Product Name: " + p.getProductname() + "</p>");
                out.println("<p>Product Color: " + p.getColor() + "</p>");
                out.println("<p>Product Discount: " + p.getDiscount() + "</p>");
                out.println("<hr>");
            }

            Collection<Product> pc = customerEJB.viewProductByColor("pink");
            if (pc != null && !pc.isEmpty()) {
                out.println("<h2>Products with Color: " + "pink" + "</h2>");

                // Iterate over the products and print their details
                for (Product product : pc) {
                    out.println("<div style=\"border: 1px solid #ccc; padding: 10px; margin-bottom: 20px;\">");
                    out.println("<p><strong>Product Name:</strong> " + product.getProductname() + "</p>");
                    out.println("<p><strong>Price:</strong> " + product.getPrice() + "</p>");
                    // Add more product details as needed
                    out.println("</div>");
                }
            } else {
                out.println("<p>No products found with color " + "black" + ".</p>");
            }

            List<Object[]> resultList = customerEJB.viewOrderHistory("drashti");

            String orderId = null;
            Date orderDate = null; // Assuming Ordermaster has a getOrderDate() method

            out.println("<h2>Order History: </h2>");
            out.println("<table border='1'>");
            out.println("<tr>");
            out.println("<th>Order ID</th>");
            out.println("<th>Order Date</th>");
            out.println("<th>Product Name</th>");
            out.println("<th>Product Quantity</th>");
            out.println("<th>Product Total Amount</th>");
            out.println("<th>Product Grand Total</th>");
            out.println("</tr>");

            for (Object[] result : resultList) {
                Ordermaster order = (Ordermaster) result[0];
                Orderitem orderItem = (Orderitem) result[1];
                Product product = (Product) result[2];

                // Update order details only if they differ from previous iteration
                if (orderId == null || !orderId.equals(order.getOrderId())) {
                    orderId = order.getOrderId();
                    orderDate = order.getOrderDate();
                    out.println("<tr>");
                    out.println("<td rowspan='" + order.getOrderitemCollection().size() + "'>" + orderId + "</td>");
                    out.println("<td rowspan='" + order.getOrderitemCollection().size() + "'>" + orderDate + "</td>");
                    
                }

                out.println("<td>" + product.getProductname() + "</td>");
                out.println("<td>" + orderItem.getProductQuantity() + "</td>");
                out.println("<td>" + orderItem.getTotalAmount() + "</td>");
                out.println("<td rowspan='" + order.getOrderitemCollection().size() + "'>" + order.getGrandTotal() + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");
//            customerEJB.placeOrder("vishwa");

            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
