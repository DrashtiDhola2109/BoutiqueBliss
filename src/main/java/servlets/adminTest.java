package servlets;

import admin.AdminEJBLocal;
import entities.Category;
import entities.Groupmaster;
import entities.Orderitem;
import entities.Ordermaster;
import entities.Product;
import entities.Sizemaster;
import entities.Usermaster;
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

@WebServlet(name = "adminTest", urlPatterns = {"/adminTest"})
public class adminTest extends HttpServlet {

    @EJB
    private AdminEJBLocal adminEJB;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Admin Test</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Admin Test</h1>");

            // Call EJB methods
//            adminEJB.addParentCategory("New Parent Category");
//            adminEJB.addSubCategory("New Sub Category", 1);
//            adminEJB.updateParentCatgeory(1, "Updated Parent Category");
//            adminEJB.updateSubCategory(3, "Updated Sub Category", 1);
//            adminEJB.removeCategory(3);
            Collection<Category> categories = adminEJB.getAllCategories();
            out.println("<h2>Categories:</h2>");
            for (Category cat : categories) {
                out.println("<p>Category: " + cat.getType() + "</p>");
            }

            Category category = adminEJB.getCategoryById(6);

// Check if the category exists
            if (category != null) {
                out.println("<h2>Category Details:</h2>");
                out.println("<p><strong>Category ID:</strong> " + category.getCategoryId() + "</p>");
                out.println("<p><strong>Category Type:</strong> " + category.getType() + "</p>");
                if (category.getParentCategoryId() != null) {
                    out.println("<p><strong>Parent Category:</strong> " + category.getParentCategoryId().getType() + "</p>");
                } else {
                    out.println("<p><strong>Parent Category:</strong> None</p>");
                }
            } else {
                out.println("<p>Category with ID " + 6 + " does not exist.</p>");
            }

//            adminEJB.addSize("XXL");
//            adminEJB.updateSize(2, "New Size");
//            adminEJB.removeSize(2);
            Collection<Sizemaster> sizes = adminEJB.getAllSize();
            out.println("<h2>Available Sizes:</h2>");
            for (Sizemaster size : sizes) {
                out.println("<p>" + size.getSize() + "</p>");
            }

            Sizemaster size = adminEJB.getSizeById(4);

// Check if the size exists
            if (size != null) {
                out.println("<h2>Size Details:</h2>");
                out.println("<p><strong>Size:</strong> " + size.getSize() + "</p>");
            } else {
                out.println("<p>Size with ID " + 4 + " does not exist.</p>");
            }

//            adminEJB.addProduct(1, 1, "New Product", 10, 100, "Red", "Description", "image.jpg", 0);
//            adminEJB.updateProduct(1, 1, 1, "Updated Product", 20, 200, "Blue", "Updated Description", "updated_image.jpg", 10, true, false);
//            adminEJB.removeProduct(2);
            Collection<Product> products = adminEJB.getAllProduct();
            out.println("<h2>Products:</h2>");
            for (Product p : products) {
                out.println("<p>Product Name: " + p.getProductname() + "</p>");
                out.println("<p>Product Color: " + p.getColor() + "</p>");
                out.println("<p>Product Discount: " + p.getDiscount() + "</p>");
                out.println("<hr>");
            }
            
            Collection<Product> ps = adminEJB.getProductByCId(5);
            out.println("<h2>Products By Category:</h2>");
            for(Product p : ps)
            {
                out.println("<p>Product Name: " + p.getProductname() + "</p>");
                out.println("<p>Product Color: " + p.getColor() + "</p>");
                out.println("<p>Product Discount: " + p.getDiscount() + "</p>");
                out.println("<hr>");
            }

            Collection<Product> productsByColor = adminEJB.getProductByColor("black");

            if (productsByColor != null && !productsByColor.isEmpty()) {
                out.println("<h2>Products with Color: " + "black" + "</h2>");

                // Iterate over the products and print their details
                for (Product product : productsByColor) {
                    out.println("<div style=\"border: 1px solid #ccc; padding: 10px; margin-bottom: 20px;\">");
                    out.println("<p><strong>Product Name:</strong> " + product.getProductname() + "</p>");
                    out.println("<p><strong>Price:</strong> " + product.getPrice() + "</p>");
                    // Add more product details as needed
                    out.println("</div>");
                }
            } else {
                out.println("<p>No products found with color " + "black" + ".</p>");
            }

            out.println("<h2>Users:</h2>");
            List<Object[]> resultList = adminEJB.viewAllUsers();

            for (Object[] result : resultList) {
                Usermaster user = (Usermaster) result[0];
                Groupmaster group = (Groupmaster) result[1];

                // Access user and group properties and do whatever you need with them
                String userName = user.getUsername();
                String email = user.getEmail();
                String groupName = group.getGroupname();

                // Output the user and group details (you can modify this as needed)
                out.println("<p>User Name: " + userName + "</p>");
                out.println("<p>Email: " + email + "</p>");
                out.println("<p>Group Name: " + groupName + "</p>");
                out.println("<hr>");
                // Additional order details as needed (e.g., payment details, total amount)
                System.out.println("...");
            }

            // Call other EJB methods
            out.println("<h2>Orders:</h2>");
            List<Object[]> orderlist = adminEJB.getAllOrderDetails();
            String orderId = null;
            Date orderDate = null; // Assuming Ordermaster has a getOrderDate() method

            for (Object[] result : orderlist) {
                Ordermaster order = (Ordermaster) result[0];
                Orderitem orderItem = (Orderitem) result[1];
                Product product = (Product) result[2];

                // Update order details only if they differ from previous iteration
                if (orderId == null || !orderId.equals(order.getOrderId())) {
                    orderId = order.getOrderId();
                    orderDate = order.getOrderDate();
                    out.println("<h3>Order ID: " + orderId + "</h3>");
                    out.println("<p>Order Date: " + orderDate + "</p>");
                    out.println("<p><strong>User Name:</strong> " + order.getUsername().getUsername() + "</p>");
                }

                out.println("<div style=\"margin-left: 20px;\">");

                out.println("<p><strong>Product Name:</strong> " + product.getProductname() + "</p>");
                out.println("<p><strong>Product Quantity:</strong> " + orderItem.getProductQuantity() + "</p>");
                out.println("<p><strong>Product Total Amount:</strong> " + orderItem.getTotalAmount() + "</p>");
                out.println("<p><strong>Product Grand Total:</strong> " + order.getGrandTotal() + "</p>");
                out.println("</div>");

                // Additional order details as needed (e.g., payment details, total amount)
                out.println("...");
            }

            //order by user
            List<Object[]> userOrders = adminEJB.getOrdersByUserId("vishwa");

            String oId = null;
            Date oD = null; // Assuming Ordermaster has a getOrderDate() method

            out.println("<h2>Orders by User:</h2>");
            for (Object[] result : userOrders) {
                Ordermaster order = (Ordermaster) result[0];
                Orderitem orderItem = (Orderitem) result[1];
                Product product = (Product) result[2];

                // Update order details only if they differ from previous iteration
                if (oId == null || !oId.equals(order.getOrderId())) {
                    oId = order.getOrderId();
                    oD = order.getOrderDate();
                    out.println("<h3>Order ID: " + oId + "</h3>");
                    out.println("<p>Order Date: " + oD + "</p>");
                    out.println("<p><strong>User Name:</strong> " + order.getUsername().getUsername() + "</p>");
                }

                out.println("<div style=\"margin-left: 20px;\">");

                out.println("<p><strong>Product Name:</strong> " + product.getProductname() + "</p>");
                out.println("<p><strong>Product Quantity:</strong> " + orderItem.getProductQuantity() + "</p>");
                out.println("<p><strong>Product Total Amount:</strong> " + orderItem.getTotalAmount() + "</p>");
                out.println("<p><strong>Product Grand Total:</strong> " + order.getGrandTotal() + "</p>");
                out.println("</div>");

                // Additional order details as needed (e.g., payment details, total amount)
                out.println("...");
            }

            //all admin
            out.println("<h2>Admin Users:</h2>");
            List<Object[]> adminUsers = adminEJB.viewAllAdmin();

            for (Object[] result : adminUsers) {
                Usermaster user = (Usermaster) result[0];
                Groupmaster group = (Groupmaster) result[1];

                out.println("<div style=\"border: 1px solid #ccc; padding: 10px; margin-bottom: 20px;\">");
                out.println("<p><strong>User Name:</strong> " + user.getUsername() + "</p>");
                out.println("<p><strong>Email:</strong> " + user.getEmail() + "</p>");
                out.println("<p><strong>Password:</strong> " + user.getPassword() + "</p>");
                out.println("<p><strong>Group Name:</strong> " + group.getGroupname() + "</p>");
                out.println("</div>");
            }

            //all customers
            out.println("<h2>Customers:</h2>");
            List<Object[]> customers = adminEJB.getUsers();

            for (Object[] result : customers) {
                Usermaster user = (Usermaster) result[0];
                Groupmaster group = (Groupmaster) result[1];

                out.println("<div style=\"border: 1px solid #ccc; padding: 10px; margin-bottom: 20px;\">");
                out.println("<p><strong>User Name:</strong> " + user.getUsername() + "</p>");
                out.println("<p><strong>Email:</strong> " + user.getEmail() + "</p>");
                out.println("<p><strong>Password:</strong> " + user.getPassword() + "</p>");
                out.println("<p><strong>Group Name:</strong> " + group.getGroupname() + "</p>");
                out.println("</div>");
            }
            

            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Admin Test Servlet";
    }
}
