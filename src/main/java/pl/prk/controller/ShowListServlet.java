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
import java.util.List;
/**
 * Servlet class - displaying the given shopping list.
 *
 * @author Paweł Urbanik, Radosław Szajdak
 */
@WebServlet("/showList")
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"USER"}),
        httpMethodConstraints = {
                @HttpMethodConstraint(
                        value = "GET",
                        rolesAllowed = {"USER"}),
                @HttpMethodConstraint(
                        value = "POST",
                        rolesAllowed = {"USER"}
                )
        }
)
public class ShowListServlet extends HttpServlet {

    private ProductService productService;
    private ListService listService;

    public ShowListServlet() {
        this.productService = new ProductService();
        listService = new ListService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            saveProductsInRequest(req);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String error = req.getParameter("error");
        if (!error.isEmpty()){
            req.setAttribute("error", error);
        }
        req.getRequestDispatcher("/WEB-INF/views/showlist.jsp").forward(req, resp);
    }


    private void saveProductsInRequest(HttpServletRequest request) throws SQLException {
        Integer listId = Integer.valueOf(request.getParameter("listId"));
        List<Product> products = productService.getProductsByList(listId);
        request.setAttribute("products", products);
        request.setAttribute("listId", listId);
        Timestamp lastUpdate = listService.getLastUpdate(listId);
        request.setAttribute("lastUpdate", lastUpdate);
    }
}
