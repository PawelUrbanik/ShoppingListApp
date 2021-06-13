package pl.prk.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.HttpMethodConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pl.prk.model.ShoppingList;
import pl.prk.service.ListService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet class - listing the shopping lists for a given user.
 *
 * @author Paweł Urbanik, Radosław Szajdak
 */
@WebServlet("/myLists")
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
public class MyListServlet extends HttpServlet {

    private ListService listService = new ListService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getUserPrincipal().getName();
        List<ShoppingList> lists = listService.getListByUser(username);
        req.setAttribute("lists", lists);
        req.getRequestDispatcher("/WEB-INF/views/myLists.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
