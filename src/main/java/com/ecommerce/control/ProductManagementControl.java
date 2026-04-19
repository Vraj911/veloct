package com.ecommerce.control;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.ecommerce.dao.CategoryDao;
import com.ecommerce.dao.ProductDao;
import com.ecommerce.entity.Account;
import com.ecommerce.entity.Category;
import com.ecommerce.entity.Product;

@WebServlet(name = "ProductManagementControl", value = "/product-management")
public class ProductManagementControl extends HttpServlet {

    ProductDao productDao = new ProductDao();
    CategoryDao categoryDao = new CategoryDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        if (account == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        int sellerId = account.getId();

        List<Product> productList = productDao.getSellerProducts(sellerId);
        List<Category> categoryList = categoryDao.getAllCategories();

        request.setAttribute("category_list", categoryList);
        request.setAttribute("product_list", productList);
        request.setAttribute("product_management_active", "active");

        RequestDispatcher rd = request.getRequestDispatcher("product-management.jsp");
        rd.forward(request, response);
    }
}
