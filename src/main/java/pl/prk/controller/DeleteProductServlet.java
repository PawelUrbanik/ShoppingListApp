package pl.prk.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pl.prk.service.ListService;
import pl.prk.service.ProductService;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Servlet class - the product removal.
 *
 * @author Paweł Urbanik, Radosław Szajdak
 */
@WebServlet("/deleteProduct")
public class DeleteProductServlet extends HttpServlet {

    ProductService productService = new ProductService();
    ListService listService = new ListService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int listId = Integer.parseInt(req.getParameter("list_id"));

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


        req.setCharacterEncoding("UTF-8");


        int productId = Integer.parseInt(req.getParameter("product_id"));

        if (sameUpdate)
        {
            productService.delete(productId);
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
