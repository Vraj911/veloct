package com.ecommerce.control;

import java.io.IOException;
import java.io.InputStream;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import com.ecommerce.dao.AccountDao;

@WebServlet(name = "RegisterControl", value = "/register")
@MultipartConfig
public class RegisterControl extends HttpServlet {

    AccountDao accountDao = new AccountDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String repeatPassword = request.getParameter("repeat-password");

        Part part = request.getPart("profile-image");
        InputStream inputStream = part.getInputStream();

        if (!password.equals(repeatPassword)) {
            request.setAttribute("alert", "Incorrect password!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else if (accountDao.checkUsernameExists(username)) {
            request.setAttribute("alert", "Username already exists!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else {
            accountDao.createAccount(username, password, inputStream);
            request.setAttribute("alert", "Account created successfully!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}