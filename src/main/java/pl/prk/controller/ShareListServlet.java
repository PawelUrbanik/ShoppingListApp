package pl.prk.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pl.prk.exception.RowExistYetException;
import pl.prk.exception.UserNotExistException;
import pl.prk.service.SharedListService;

import java.io.IOException;
/**
 * Servlet class - listing of the shared shopping lists.
 *
 * @author Paweł Urbanik, Radosław Szajdak
 */
@WebServlet("/shareList")
public class ShareListServlet extends HttpServlet {

    SharedListService sharedListService = new SharedListService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String ownerUsername = req.getUserPrincipal().getName();
        String username = req.getParameter("inputName");
        boolean updateList = req.getParameter("updateList") != null;
        boolean addProduct = req.getParameter("addProduct") != null;
        boolean updateProduct = req.getParameter("updateProduct") != null;
        boolean changeState = req.getParameter("changeState") != null;
        boolean deleteProduct = req.getParameter("deleteProduct") != null;

        Integer listId = Integer.valueOf(req.getParameter("listId"));

        boolean shared = false;
        try {
            shared = sharedListService.addSharedList(listId, ownerUsername, username, updateList, addProduct, updateProduct, changeState, deleteProduct);
        } catch (UserNotExistException e) {
            req.getRequestDispatcher("/sharedLists").forward(req,resp);
        } catch (RowExistYetException e) {
            req.getRequestDispatcher("/sharedLists").forward(req,resp);
        }

        if (shared)
        {
            req.getRequestDispatcher("/sharedLists").forward(req,resp);
        }
    }
}
