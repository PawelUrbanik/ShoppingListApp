package pl.prk.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pl.prk.dao.ShoppingListDao;
import pl.prk.dao.ShoppingListDaoImpl;
import pl.prk.model.ShoppingList;
import pl.prk.service.ListService;

import java.io.IOException;
import java.util.Random;

@WebServlet("/addList")
public class addListServlet extends HttpServlet {

    private ListService listService = new ListService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/addList.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("inputName");
        String description = req.getParameter("inputDescription");
        String username = req.getUserPrincipal().getName();
        listService.addShoppingList(name, description, username);

        req.getRequestDispatcher("/WEB-INF/views/myLists.jsp").forward(req, resp);
    }
}
