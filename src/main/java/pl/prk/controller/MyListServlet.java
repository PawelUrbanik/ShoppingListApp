package pl.prk.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pl.prk.model.ShoppingList;
import pl.prk.service.ListService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/myLists")
public class MyListServlet extends HttpServlet {

    private ListService listService = new ListService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getUserPrincipal().getName();
        List<ShoppingList> lists = listService.getListByUser(username);
        req.setAttribute("lists", lists);
        req.getRequestDispatcher("WEB-INF/views/myLists.jsp").forward(req, resp);
    }
}
