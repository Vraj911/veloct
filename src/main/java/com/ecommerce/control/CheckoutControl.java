package com.ecommerce.control;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.ecommerce.dao.AccountDao;
import com.ecommerce.dao.OrderDao;
import com.ecommerce.entity.Account;
import com.ecommerce.entity.Order;

@WebServlet(name = "CheckoutControl", value = "/checkout")
public class CheckoutControl extends HttpServlet {

    OrderDao orderDao = new OrderDao();
    AccountDao accountDao = new AccountDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String firstName = request.getParameter("first-name");
        String lastName = request.getParameter("last-name");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        if (session.getAttribute("account") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        Order order = (Order) session.getAttribute("order");
        Double totalPrice = (Double) session.getAttribute("total_price");
        Account account = (Account) session.getAttribute("account");

        if (order == null || order.getCartProducts() == null || order.getCartProducts().isEmpty() || totalPrice == null) {
            response.sendRedirect(request.getContextPath() + "/cart.jsp");
            return;
        }

        accountDao.updateProfileInformation(account.getId(), firstName, lastName, address, email, phone);
        orderDao.createOrder(account.getId(), totalPrice, order.getCartProducts());

        session.removeAttribute("order");
        session.removeAttribute("total_price");

        RequestDispatcher rd = request.getRequestDispatcher("thankyou.jsp");
        rd.forward(request, response);
    }
}
