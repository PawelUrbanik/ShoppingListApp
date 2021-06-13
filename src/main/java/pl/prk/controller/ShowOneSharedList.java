package pl.prk.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pl.prk.model.Product;
import pl.prk.model.SharedList;
import pl.prk.model.ShoppingList;
import pl.prk.service.ListService;
import pl.prk.service.ProductService;
import pl.prk.service.SharedListService;
import pl.prk.service.UserService;

import java.io.IOException;
import java.util.List;
/**
 * Servlet class - displaying the shared list for the given user.
 *
 * @author Paweł Urbanik, Radosław Szajdak
 */
@WebServlet("/showOneSharedList")
public class ShowOneSharedList extends HttpServlet {

    private ProductService productService = new ProductService();
    private SharedListService sharedListService = new SharedListService();
    private UserService userService = new UserService();
    private ListService listService = new ListService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        saveProductsInRequest(req);
        Integer listId = Integer.valueOf(req.getParameter("listId"));
        String user = req.getUserPrincipal().getName();
        SharedList sharedList = sharedListService.getOneByListIdAndUsername(listId, user);
        String ownerName = userService.getUserById(sharedList.getOwnerId());
        ShoppingList shoppingList = listService.getListbyId(sharedList.getListId());
        req.setAttribute("list", shoppingList);
        req.setAttribute("ownerName", ownerName);
        req.setAttribute("privileges", sharedList);

        req.getRequestDispatcher("/WEB-INF/views/showSharedList.jsp").forward(req, resp);
    }

    private void saveProductsInRequest(HttpServletRequest request)
    {
        Integer listId = Integer.valueOf(request.getParameter("listId"));
        List<Product> products = productService.getProductsByList(listId);
        request.setAttribute("products", products);
        request.setAttribute("listId", listId);
    }
}
