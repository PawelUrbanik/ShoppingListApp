package pl.prk.service;

import pl.prk.dao.ShoppingListDao;
import pl.prk.dao.ShoppingListDaoImpl;
import pl.prk.model.ShoppingList;

import java.util.List;

/**
 * A class that generates and writes a shopping list into the database.
 * The data are stored in the university database.
 *
 * @version v1
 * @author ...
 */
public class ListService {

    /**
     * Creating object instances in the parameterless constructor.
     */
    private ShoppingListDao shoppingListDao;
    private UserService userService;

    public ListService() {
        this.shoppingListDao= new ShoppingListDaoImpl();
        this.userService = new UserService();
    }
    /**
     * Creates and saves a shopping list.
     *
     * @param name the name of the shopping list.
     * @param description the description of the shopping list.
     * @param owner shopping list owner.
     * @param type the type of the shopping list.
     */
    public boolean addShoppingList(String name, String description, String owner, String type){
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setName(name);
        shoppingList.setDescription(description);
        shoppingList.setType(type);
        Integer owner_id= userService.getUser(owner).getId();
        shoppingList.setOwner(owner_id);
        return shoppingListDao.save(shoppingList) != null;
    }
    /**
     * Returns the shopping lists according to a given user.
     *
     * @param username shopping list user.
     * @return shopping lists of particular user.
     */
    public List<ShoppingList> getListByUser(String username) {
        List<ShoppingList> lists = shoppingListDao.getListsByUser(username);
        return lists;
    }
    /**
     * Updates list
     *
     * @param updatedList shopping list object to be updated.
     * @return returns the status of the operation.
     * @see ShoppingListDao
     */
    public boolean update(ShoppingList updatedList) {
        return shoppingListDao.update(updatedList);
    }
    /**
     * Deletes list
     *
     * @param listId the shopping list id.
     * @return returns the status of the operation.
     * @see ShoppingListDao
     */
    public boolean delete(int listId) {
        return shoppingListDao.delete(listId);
    }

    /**
     * Returns the shopping lists according to a given list id.
     *
     * @param listId shopping list user.
     * @return shopping list of particular id.
     */
    public ShoppingList getListbyId(Integer listId) {
        return shoppingListDao.read(listId);
    }
}
