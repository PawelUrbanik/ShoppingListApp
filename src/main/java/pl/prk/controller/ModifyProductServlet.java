package pl.prk.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.HttpMethodConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pl.prk.model.Product;
import pl.prk.service.ProductService;

import java.io.IOException;

@WebServlet("/updateProduct")
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"USER"}),
        httpMethodConstraints = {
                @HttpMethodConstraint(
                        value = "POST",
                        rolesAllowed = {"USER"})
        }
)
public class ModifyProductServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ProductService productService  = new ProductService();

        int listId = Integer.parseInt(req.getParameter("list_id_m"));
        int productId = Integer.parseInt(req.getParameter("product_id_m"));
        String productName = req.getParameter("product_name_m");
        int productCount = Integer.parseInt(req.getParameter("product_count_m"));



        Product product = new Product();
        product.setListId(listId);
        product.setId(productId);
        product.setName(productName);
        product.setCount(productCount);

        productService.update(product);
        boolean sharedReq = req.getParameter("sharedReq") != null;

        String url="";
        if (sharedReq){
            url = "/showOneSharedList?listId=" +listId;
        }else {
            url = "/showList?listId=" + listId;
        }

        resp.sendRedirect(url);
    }

}
