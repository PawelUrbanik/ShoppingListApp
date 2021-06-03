package pl.prk.service;

import pl.prk.dao.ShoppingListDao;
import pl.prk.dao.ShoppingListDaoImpl;
import pl.prk.model.ShoppingList;

import java.util.ArrayList;
import java.util.List;

public class ListService {

    private ShoppingListDao shoppingListDao;
    private UserService userService;

    public ListService() {
        this.shoppingListDao= new ShoppingListDaoImpl();
        this.userService = new UserService();
    }

    public void addShoppingList(String name, String description, String owner, String type){
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setName(name);
        shoppingList.setDescription(description);
        shoppingList.setType(type);
        Integer owner_id= userService.getUser(owner).getId();
        shoppingList.setOwner(owner_id);
        shoppingListDao.save(shoppingList);
    }

    public List<ShoppingList> getListByUser(String username) {
        List<ShoppingList> lists = shoppingListDao.getListsByUser(username);
        return lists;
    }

    public boolean update(ShoppingList updatedList) {
        return shoppingListDao.update(updatedList);
    }
}
