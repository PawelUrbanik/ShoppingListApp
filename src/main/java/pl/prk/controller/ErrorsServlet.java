package pl.prk.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/error")
public class ErrorsServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String forwardUrl="";

        switch (action)
        {
            case "reg":
            {
                forwardUrl="/register";
                req.setAttribute("errorRegister", "Wystąpił błąd przy rejestracji. Spróbuj ponownie używając innej nazwy użytkownika lub adresu e-mail.");
            }
        }
        req.getRequestDispatcher(forwardUrl).forward(req,resp);

    }
}
