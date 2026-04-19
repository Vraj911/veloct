package com.ecommerce.control;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.ecommerce.dao.ProductDao;
import com.ecommerce.entity.Product;

@WebServlet(name = "ProductDetail", value = "/product-detail")
public class ProductDetail extends HttpServlet {

    ProductDao productDao = new ProductDao();

   @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    boolean alert = request.getParameter("invalid-quantity") != null;

    String idRaw = request.getParameter("id");

    if (idRaw == null) {
        response.sendRedirect(request.getContextPath() + "/shop");
        return;
    }

    int id = Integer.parseInt(idRaw);

    Product product = productDao.getProduct(id);

    if (product == null) {
        response.sendRedirect(request.getContextPath() + "/shop");
        return;
    }

    String disabled = product.getAmount() <= 0 ? "disabled" : "";

    List<Product> productList = productDao.getAllProducts();

    request.setAttribute("alert", alert);
    request.setAttribute("disabled", disabled);
    request.setAttribute("shop_active", "active");
    request.setAttribute("product", product);
    request.setAttribute("product_list", productList);

    request.getRequestDispatcher("product-detail.jsp").forward(request, response);
}
}