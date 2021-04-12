package pl.prk.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pl.prk.dao.ShoppingListDao;
import pl.prk.dao.ShoppingListDaoImpl;
import pl.prk.model.ShoppingList;

import java.io.IOException;

@WebServlet("/addList")
public class addListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/addList.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ShoppingListDao shoppingListDao = new ShoppingListDaoImpl();
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setName("Name12");
        shoppingList.setDescription("DESSC");
        shoppingListDao.create(shoppingList);
        req.getRequestDispatcher("/").forward(req, resp);
    }
}
