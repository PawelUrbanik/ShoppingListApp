package pl.prk.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pl.prk.model.UserRegistration;
import pl.prk.service.UserService;

import java.io.IOException;

@WebServlet("/register")
public class registerController extends HttpServlet {

    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/register.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserRegistration userRegistration = getUserData(req);
        userService.register(userRegistration);
        resp.sendRedirect("/");
    }

    private UserRegistration getUserData(HttpServletRequest request) {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        return new UserRegistration(username, email, password);
    }
}