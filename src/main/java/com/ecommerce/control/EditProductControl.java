package com.ecommerce.control;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import com.ecommerce.dao.CategoryDao;
import com.ecommerce.dao.ProductDao;
import com.ecommerce.entity.Category;
import com.ecommerce.entity.Product;

@WebServlet(name = "EditProductControl", value = "/edit-product")
@MultipartConfig
public class EditProductControl extends HttpServlet {

    ProductDao productDao = new ProductDao();
    CategoryDao categoryDao = new CategoryDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int productId = Integer.parseInt(request.getParameter("product-id"));

        Product product = productDao.getProduct(productId);
        List<Category> categoryList = categoryDao.getAllCategories();

        request.setAttribute("product", product);
        request.setAttribute("category_list", categoryList);

        RequestDispatcher rd = request.getRequestDispatcher("edit-product.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int productId = Integer.parseInt(request.getParameter("product-id"));

        String name = request.getParameter("product-name");
        double price = Double.parseDouble(request.getParameter("product-price"));
        String desc = request.getParameter("product-description");
        int category = Integer.parseInt(request.getParameter("product-category"));
        int amount = Integer.parseInt(request.getParameter("product-amount"));

        Part part = request.getPart("product-image");
        InputStream inputStream = part.getInputStream();

        productDao.editProduct(productId, name, inputStream, price, desc, category, amount);

        response.sendRedirect(request.getContextPath() + "/product-management");
    }
}