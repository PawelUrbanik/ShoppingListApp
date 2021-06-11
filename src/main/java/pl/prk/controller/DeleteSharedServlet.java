package pl.prk.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pl.prk.service.SharedListService;

import java.io.IOException;

@WebServlet("/deleteShared")
public class DeleteSharedServlet extends HttpServlet {

    SharedListService sharedListService = new SharedListService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer sharedListId = Integer.parseInt(req.getParameter("shared_id_d"));

        boolean deleted= sharedListService.deleteSharedListById(sharedListId);
        System.out.println("Delete sh: " + req.getParameter("listId"));

//        System.out.println("Deleted sharedList: " + deleted);

        req.getRequestDispatcher("/sharedLists").forward(req, resp);

    }
}
