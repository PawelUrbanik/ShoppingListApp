package pl.prk.controller;

import com.sun.security.auth.UserPrincipal;
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
import java.security.Principal;

@WebServlet("/addProduct")
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
public class ProductServlet extends HttpServlet {

    private ProductService productService;

    public ProductServlet() {
        this.productService = new ProductService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println(req.getParameter("listId"));
        req.setAttribute("listId", req.getParameter("listId"));
        req.getRequestDispatcher("/WEB-INF/views/addProduct.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name =req.getParameter("inputName");
        String addedBy =req.getUserPrincipal().getName();
        Integer listId = Integer.valueOf(req.getParameter("listId"));
        productService.addProductToList(name, addedBy,listId);
        resp.sendRedirect("/showList?listId="+listId);
    }
}
