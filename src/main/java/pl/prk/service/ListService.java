package pl.prk.service;

import pl.prk.dao.ShoppingListDao;
import pl.prk.dao.ShoppingListDaoImpl;
import pl.prk.model.ShoppingList;

public class ListService {

    private ShoppingListDao shoppingListDao;
    private UserService userService;

    public ListService() {
        this.shoppingListDao= new ShoppingListDaoImpl();
        this.userService = new UserService();
    }

    public void addShoppingList(String name, String description, String owner){
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setName(name);
        shoppingList.setDescription(description);
        Integer owner_id= userService.getUser(owner).getId();
        shoppingList.setOwner(owner_id);
        shoppingListDao.save(shoppingList);
    }
}