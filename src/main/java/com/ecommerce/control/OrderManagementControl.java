package com.ecommerce.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.ecommerce.dao.OrderDao;
import com.ecommerce.dao.ProductDao;
import com.ecommerce.entity.Account;
import com.ecommerce.entity.CartProduct;
import com.ecommerce.entity.Product;

@WebServlet(name = "OrderManagementControl", value = "/order-management")
public class OrderManagementControl extends HttpServlet {

    ProductDao productDao = new ProductDao();
    OrderDao orderDao = new OrderDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        if (account == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        int accountId = account.getId();

        List<Product> productList = productDao.getSellerProducts(accountId);
        List<CartProduct> cartProductList = new ArrayList<>();

        for (Product product : productList) {
            cartProductList.addAll(orderDao.getSellerOrderDetail(product.getId()));
        }

        request.setAttribute("order_detail_list", cartProductList);
        request.setAttribute("order_management_active", "active");

        RequestDispatcher rd = request.getRequestDispatcher("order-management.jsp");
        rd.forward(request, response);
    }
}
