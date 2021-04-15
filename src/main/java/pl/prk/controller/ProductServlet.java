package pl.prk.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pl.prk.service.ProductService;

import java.io.IOException;

@WebServlet("/addProduct")
public class ProductServlet extends HttpServlet {

    private ProductService productService;

    public ProductServlet() {
        this.productService = new ProductService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        productService.addProductToList("Prdukt1", "Pawel", 6);
        req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        productService.addProductToList("Prdukt1", "Pawel", 6);
        super.doPost(req, resp);
    }
}
