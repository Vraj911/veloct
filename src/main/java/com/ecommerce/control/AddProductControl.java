package com.ecommerce.control;

import java.io.IOException;
import java.io.InputStream;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import com.ecommerce.dao.ProductDao;
import com.ecommerce.entity.Account;

@WebServlet(name = "AddProductControl", value = "/add-product")
@MultipartConfig
public class AddProductControl extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("product-name");
        double price = Double.parseDouble(request.getParameter("product-price"));
        String desc = request.getParameter("product-description");
        int category = Integer.parseInt(request.getParameter("product-category"));
        int amount = Integer.parseInt(request.getParameter("product-amount"));

        Part part = request.getPart("product-image");
        InputStream inputStream = part.getInputStream();

        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        int sellerId = account.getId();

        ProductDao dao = new ProductDao();
        dao.addProduct(name, inputStream, price, desc, category, sellerId, amount);

        response.sendRedirect(request.getContextPath() + "/product-management");
    }
}