package com.ecommerce.control;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.ecommerce.dao.AccountDao;
import com.ecommerce.entity.Account;

@WebServlet(name = "LoginControl", value = "/login")
public class LoginControl extends HttpServlet {

    AccountDao accountDao = new AccountDao();

    private Account getAccountCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        if (cookies == null) return null;

        String username = "";
        String password = "";

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("username")) username = cookie.getValue();
            if (cookie.getName().equals("password")) password = cookie.getValue();
        }

        if (username.isEmpty() || password.isEmpty()) return null;

        return accountDao.checkLoginAccount(username, password);
    }

    private void executeLogin(HttpServletRequest request, HttpServletResponse response, Account account)
            throws IOException {

        HttpSession session = request.getSession();
        boolean rememberMe = request.getParameter("remember-me-checkbox") != null;

        session.setAttribute("account", account);

        if (rememberMe) {
            String cookiePath = request.getContextPath().isEmpty() ? "/" : request.getContextPath();

            Cookie u = new Cookie("username", account.getUsername());
            u.setMaxAge(600);
            u.setPath(cookiePath);
            response.addCookie(u);

            Cookie p = new Cookie("password", account.getPassword());
            p.setMaxAge(600);
            p.setPath(cookiePath);
            response.addCookie(p);
        }

        response.sendRedirect(request.getContextPath() + "/");
    }

    private void checkLoginAccountFirstTime(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String status = request.getParameter("status") != null ? request.getParameter("status") : "";

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Account account = accountDao.checkLoginAccount(username, password);

        if (account == null && status.equals("typed")) {
            request.setAttribute("alert", "Wrong username or password!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else if (account == null) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            executeLogin(request, response, account);
        }
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Account account = getAccountCookie(request);

        if (account == null) {
            checkLoginAccountFirstTime(request, response);
        } else {
            executeLogin(request, response, account);
        }
    }
}
