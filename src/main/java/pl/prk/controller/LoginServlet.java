package pl.prk.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpMethodConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
/**
 * Servlet class - checking the user login.
 *
 * @author Paweł Urbanik, Radosław Szajdak
 */

@WebServlet("/login")
@ServletSecurity(
        httpMethodConstraints = {@HttpMethodConstraint(value = "GET", rolesAllowed = "USER")}
)
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("username", req.getUserPrincipal().getName());
        resp.sendRedirect(req.getContextPath()+"/myLists");
    }
}
