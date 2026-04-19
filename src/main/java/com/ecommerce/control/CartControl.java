package com.ecommerce.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.ecommerce.dao.ProductDao;
import com.ecommerce.entity.CartProduct;
import com.ecommerce.entity.Order;
import com.ecommerce.entity.Product;

@WebServlet(name = "CartControl", value = "/cart")
public class CartControl extends HttpServlet {

    ProductDao productDao = new ProductDao();

    private double removeCartProduct(int productId, Order order, double totalPrice) {
        List<CartProduct> list = order.getCartProducts();

        for (Iterator<CartProduct> it = list.iterator(); it.hasNext();) {
            CartProduct cp = it.next();

            if (cp.getProduct().getId() == productId) {
                totalPrice -= (cp.getPrice() * cp.getQuantity());
                it.remove();
            }
        }
        return totalPrice; // 🔴 FIXED
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        if (request.getParameter("remove-product-id") != null) {

            Order order = (Order) session.getAttribute("order");
            double totalPrice = (double) session.getAttribute("total_price");

            int productId = Integer.parseInt(request.getParameter("remove-product-id"));

            totalPrice = removeCartProduct(productId, order, totalPrice); // 🔴 FIXED

            session.setAttribute("total_price", totalPrice);

            response.sendRedirect(request.getContextPath() + "/cart.jsp");
            return;
        }

        int quantity = 1;
        double totalPrice = session.getAttribute("total_price") == null ? 0
                : (double) session.getAttribute("total_price");

        if (request.getParameter("product-id") != null) {

            int productId = Integer.parseInt(request.getParameter("product-id"));

            Product product = productDao.getProduct(productId);

            if (product != null) {

                if (request.getParameter("quantity") != null) {
                    quantity = Integer.parseInt(request.getParameter("quantity"));

                    if (product.getAmount() - quantity < 0) {
                        response.sendRedirect(request.getContextPath()
                                + "/product-detail?id=" + product.getId() + "&invalid-quantity=1");
                        return;
                    }
                }

                if (session.getAttribute("order") == null) {

                    Order order = new Order();
                    List<CartProduct> list = new ArrayList<>();

                    CartProduct cp = new CartProduct();
                    cp.setQuantity(quantity);
                    cp.setProduct(product);
                    cp.setPrice(product.getPrice());

                    totalPrice += product.getPrice() * quantity;

                    list.add(cp);
                    order.setCartProducts(list);

                    session.setAttribute("order", order);

                } else {

                    Order order = (Order) session.getAttribute("order");
                    List<CartProduct> list = order.getCartProducts();

                    boolean found = false;

                    for (CartProduct cp : list) {
                        if (cp.getProduct().getId() == product.getId()) {
                            cp.setQuantity(cp.getQuantity() + quantity);
                            totalPrice += product.getPrice() * quantity;
                            found = true;
                        }
                    }

                    if (!found) {
                        CartProduct cp = new CartProduct();
                        cp.setQuantity(quantity);
                        cp.setProduct(product);
                        cp.setPrice(product.getPrice());

                        totalPrice += product.getPrice() * quantity;
                        list.add(cp);
                    }

                    session.setAttribute("order", order);
                }

                session.setAttribute("total_price", totalPrice);
            }

            response.sendRedirect(request.getContextPath() + "/product-detail?id=" + productId);
        }
    }
}