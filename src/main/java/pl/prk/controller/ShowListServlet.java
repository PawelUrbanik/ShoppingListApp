package pl.prk.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pl.prk.model.Product;
import pl.prk.service.ProductService;

import java.io.IOException;
import java.util.List;

@WebServlet("/showList")
public class ShowListServlet extends HttpServlet {

    private ProductService productService;

    public ShowListServlet() {
        this.productService = new ProductService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        saveProductsInRequest(req);
        req.getRequestDispatcher("/WEB-INF/views/showlist.jsp").forward(req, resp);
    }


    private void saveProductsInRequest(HttpServletRequest request)
    {
        Integer listId = Integer.valueOf(request.getParameter("listId"));
        List<Product> products = productService.getProductsByList(listId);
        request.setAttribute("products", products);
    }
}
