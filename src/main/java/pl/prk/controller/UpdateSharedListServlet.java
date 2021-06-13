package pl.prk.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pl.prk.service.SharedListService;

import java.io.IOException;
/**
 * Servlet class - shared status modification - the change of permissions for a given user.
 *
 * @author Paweł Urbanik, Radosław Szajdak
 */
@WebServlet("/updateSharedList")
public class UpdateSharedListServlet extends HttpServlet {
    
    SharedListService sharedListService = new SharedListService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer sharedListId = Integer.valueOf(req.getParameter("shared_list_id_m"));
//        System.out.println(sharedListId);
        Integer listId = Integer.parseInt(req.getParameter("list_id_m"));

        boolean updateList = req.getParameter("updateList_m") != null;
        boolean addProduct = req.getParameter("addProduct_m") != null;
        boolean updateProduct = req.getParameter("updateProduct_m") != null;
        boolean changeState = req.getParameter("changeState_m") != null;
        boolean deleteProduct = req.getParameter("deleteProduct_m") != null;


        boolean updated= sharedListService.update(sharedListId, updateList, addProduct, updateProduct, changeState, deleteProduct);
//        System.out.println("Shared List updated: "+ updated);
        req.getRequestDispatcher("/sharedLists").forward(req,resp);
    }
}
