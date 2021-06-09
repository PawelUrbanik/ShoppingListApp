package pl.prk.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pl.prk.model.SharedList;
import pl.prk.service.SharedListService;

import java.io.IOException;
import java.util.List;

@WebServlet("/sharedLists")
public class sharedListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.getRequestDispatcher();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        List<SharedList> sharedLists
//        req.setAttribute("sharedList", sharedLists);
        System.out.println(req.getParameter("listId"));
        req.setAttribute("listId",req.getParameter("listId"));
        req.getRequestDispatcher("/WEB-INF/views/addSharedList.jsp").forward(req,resp);
    }
}
