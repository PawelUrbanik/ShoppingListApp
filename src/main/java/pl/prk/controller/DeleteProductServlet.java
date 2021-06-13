package pl.prk.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pl.prk.service.ProductService;

import java.io.IOException;

/**
 * Servlet class - the product removal.
 *
 * @author Paweł Urbanik, Radosław Szajdak
 */
@WebServlet("/deleteProduct")
public class DeleteProductServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ProductService productService = new ProductService();


        req.setCharacterEncoding("UTF-8");

        int listId = Integer.parseInt(req.getParameter("list_id"));
        int productId = Integer.parseInt(req.getParameter("product_id"));

        productService.delete(productId);

        boolean sharedReq = req.getParameter("sharedReq") != null;

        String url="";
        if (sharedReq){
            url = "/showOneSharedList?listId=" +listId;
        }else {
            url = "/showList?listId=" + listId;
        }

        resp.sendRedirect(req.getContextPath()+url);
    }
}
