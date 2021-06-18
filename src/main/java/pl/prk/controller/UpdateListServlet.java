package pl.prk.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pl.prk.model.ShoppingList;
import pl.prk.service.ListService;

import java.io.IOException;
/**
 * Servlet class - update of the modified shopping list.
 *
 * @author Paweł Urbanik, Radosław Szajdak
 */
@WebServlet("/updateList")
public class UpdateListServlet extends HttpServlet {

    ListService listService = new ListService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int listId = Integer.parseInt(req.getParameter("list_id_u"));
        String newListName = req.getParameter("list_name_m");
        String newListDesc = req.getParameter("list_desc_m");


        ShoppingList updatedList = new ShoppingList();
        updatedList.setId(listId);
        updatedList.setName(newListName);
        updatedList.setDescription(newListDesc);

        listService.update(updatedList);

        boolean sharedReq = req.getParameter("sharedReq") != null;

        String url="";
        if (sharedReq){
            url = "/showOneSharedList?listId=" +listId+"&error=false";
        }else {
            url = "/myLists";
        }

        System.out.println("update lists");
        resp.sendRedirect(req.getContextPath()+url);
    }
}
