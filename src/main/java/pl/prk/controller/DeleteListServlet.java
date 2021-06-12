package pl.prk.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pl.prk.service.ListService;

import java.io.IOException;

@WebServlet("/deleteList")
public class DeleteListServlet extends HttpServlet {
    ListService listService = new ListService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int listId = Integer.parseInt(req.getParameter("list_id"));

        boolean deleted = listService.delete(listId);
        System.out.println("List deleted: " + deleted);

        resp.sendRedirect(req.getContextPath()+"/myLists");
    }
}
