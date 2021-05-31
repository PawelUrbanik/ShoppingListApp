package pl.prk.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pl.prk.service.ProductService;

import java.io.IOException;

@WebServlet("/changeProductStatus")
public class changeProductStatusServlet extends HttpServlet {

    ProductService productService = new ProductService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int listId = Integer.parseInt(req.getParameter("list_id"));
        int productId = Integer.parseInt(req.getParameter("product_id"));
        boolean bought = Boolean.parseBoolean(req.getParameter("bought"));
        productService.changeBought(productId ,bought);

        resp.sendRedirect("/showList?listId=" + listId);
    }
}
