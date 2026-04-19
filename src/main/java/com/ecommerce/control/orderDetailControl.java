package com.ecommerce.control;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.ecommerce.dao.OrderDao;
import com.ecommerce.entity.CartProduct;

@WebServlet(name = "orderDetailControl", value = "/order-detail")
public class orderDetailControl extends HttpServlet {
    // Call DAO class to access with database.
    OrderDao orderDao = new OrderDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get order id from request.
        int orderId = Integer.parseInt(request.getParameter("order-id"));
        // Get order by id from database.
        List<CartProduct> list = orderDao.getOrderDetailHistory(orderId);

        request.setAttribute("order_detail_list", list);
        // Get request dispatcher and render to order-detail page.
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("order-detail.jsp");
        requestDispatcher.forward(request, response);
    }
}
