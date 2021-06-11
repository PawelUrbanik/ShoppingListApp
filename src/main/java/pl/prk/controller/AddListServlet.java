package pl.prk.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.HttpMethodConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pl.prk.dao.ShoppingListDao;
import pl.prk.dao.ShoppingListDaoImpl;
import pl.prk.exception.AddListException;
import pl.prk.model.ShoppingList;
import pl.prk.service.ListService;

import java.io.IOException;
import java.util.Random;

@WebServlet("/addList")
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"USER"}),
        httpMethodConstraints = {
                @HttpMethodConstraint(
                        value = "GET",
                        rolesAllowed = {"USER"}),
                @HttpMethodConstraint(
                        value = "POST",
                        rolesAllowed = {"USER"}
                )
        }
)
public class AddListServlet extends HttpServlet {

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
        String listType = req.getParameter("inputType");
        String username = req.getUserPrincipal().getName();
        boolean succes = listService.addShoppingList(name, description, username, listType);
        String alertMessage = "";

        if (succes)
        {
            alertMessage = "Poprawnie dodano listę o nazwie " + name;
        }else {
                alertMessage = "Wystąpił błąd przy dodawaniu listy!";
        }

        req.setAttribute("succes", succes);
        req.setAttribute("alertMessage", alertMessage);

        req.getRequestDispatcher("/myLists").forward(req, resp);
    }
}
