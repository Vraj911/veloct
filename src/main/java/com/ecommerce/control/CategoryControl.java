package com.ecommerce.control;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.ecommerce.dao.CategoryDao;
import com.ecommerce.dao.ProductDao;
import com.ecommerce.entity.Category;
import com.ecommerce.entity.Product;

@WebServlet(name = "CategoryControl", value = "/category")
public class CategoryControl extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final ProductDao productDao = new ProductDao();
    private final CategoryDao categoryDao = new CategoryDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Get parameter safely
        String categoryParam = request.getParameter("category_id");

        // 2. Validate null / empty
        if (categoryParam == null || categoryParam.trim().isEmpty()) {
            response.sendRedirect("shop");
            return;
        }

        int categoryId;

        // 3. Validate numeric input
        try {
            categoryId = Integer.parseInt(categoryParam);
        } catch (NumberFormatException e) {
            response.sendRedirect("shop");
            return;
        }

        // 4. Fetch data (with basic safety)
        List<Product> productList = productDao.getAllCategoryProducts(categoryId);
        List<Category> categoryList = categoryDao.getAllCategories();

        // 5. Avoid null issues in JSP
        if (productList == null) {
            productList = List.of();
        }
        if (categoryList == null) {
            categoryList = List.of();
        }

        // 6. Set attributes
        request.setAttribute("product_list", productList);
        request.setAttribute("category_list", categoryList);
        request.setAttribute("shop_active", "active");

        // 7. Forward to JSP
        RequestDispatcher rd = request.getRequestDispatcher("shop.jsp");
        rd.forward(request, response);
    }
}