package pl.prk.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pl.prk.model.SharedList;
import pl.prk.model.User;
import pl.prk.service.SharedListService;
import pl.prk.service.UserService;

import java.io.IOException;
import java.util.List;

@WebServlet("/sharedLists")
public class sharedListServlet extends HttpServlet {

    UserService userService = new UserService();
    SharedListService sharedListService = new SharedListService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.getRequestDispatcher();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getUserPrincipal().getName();
        User user = userService.getUser(username);
        System.out.println("Shared: "+req.getParameter("listId"));
        Integer lsitId = Integer.valueOf(req.getParameter("listId"));


        List<SharedList> sharedLists = sharedListService.getAllByOwnerId(user.getId(), lsitId);
//        System.out.println(sharedLists);
        req.setAttribute("sharedList", sharedLists);
        req.setAttribute("listId",req.getParameter("listId"));
        req.getRequestDispatcher("/WEB-INF/views/addSharedList.jsp").forward(req,resp);
    }
}
