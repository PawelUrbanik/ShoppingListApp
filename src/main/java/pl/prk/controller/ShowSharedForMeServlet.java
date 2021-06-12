package pl.prk.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pl.prk.model.ShoppingList;
import pl.prk.service.SharedListService;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@WebServlet("/sharedForMe")
public class ShowSharedForMeServlet extends HttpServlet {

    SharedListService sharedListService = new SharedListService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String user = req.getUserPrincipal().getName();
//        System.out.println(user);

        List<ShoppingList> listsSharedForUser = sharedListService.getAllSharedForUser(user);
        req.setAttribute("sharedLists", listsSharedForUser);


        req.getRequestDispatcher("/WEB-INF/views/showSharedForMe.jsp").forward(req,resp);

    }
}
