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
import pl.prk.service.ListService;
import pl.prk.service.ProductService;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Servlet class - the product modification.
 *
 * @author Paweł Urbanik, Radosław Szajdak
 */
@WebServlet("/updateProduct")
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"USER"}),
        httpMethodConstraints = {
                @HttpMethodConstraint(
                        value = "POST",
                        rolesAllowed = {"USER"})
        }
)
public class ModifyProductServlet extends HttpServlet {

    ProductService productService  = new ProductService();
    ListService listService = new ListService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        int listId = Integer.parseInt(req.getParameter("list_id_m"));

        Timestamp timestamp=null;
        Timestamp dbLastUpdate=null;
        String timestampString = req.getParameter("last_update");
        if (!timestampString.isEmpty()) {
            timestamp = Timestamp.valueOf(timestampString);
        }

        try {
            dbLastUpdate = listService.getLastUpdate(listId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        boolean sameUpdate = timestamp.equals(dbLastUpdate);
        System.out.println( "SU :"+sameUpdate);

        int productId = Integer.parseInt(req.getParameter("product_id_m"));
        String productName = req.getParameter("product_name_m");
        int productCount = Integer.parseInt(req.getParameter("product_count_m"));



        Product product = new Product();
        product.setListId(listId);
        product.setId(productId);
        product.setName(productName);
        product.setCount(productCount);

        if (sameUpdate){
            productService.update(product);
            try {
                listService.updateLastModified(listId);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        boolean sharedReq = req.getParameter("sharedReq") != null;

        String url="";
        if (sharedReq){
            url = "/showOneSharedList?listId=" +listId;
        }else {
            url = "/showList?listId=" + listId;
        }

        if (!sameUpdate){
            url = url+"&error=true";
        }else {
            url = url+"&error=false";
        }

        resp.sendRedirect(req.getContextPath()+url);
    }

}
