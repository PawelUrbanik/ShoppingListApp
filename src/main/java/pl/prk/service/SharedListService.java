package pl.prk.service;

import pl.prk.dao.SharedListDao;
import pl.prk.dao.SharedListDaoImpl;
import pl.prk.exception.RowExistYetException;
import pl.prk.exception.UserNotExistException;
import pl.prk.model.SharedList;
import pl.prk.model.ShoppingList;
import pl.prk.model.User;

import java.util.ArrayList;
import java.util.List;

public class SharedListService {

    SharedListDao sharedListDao;
    UserService userService;

    public SharedListService() {
        this.sharedListDao = new SharedListDaoImpl();
        this.userService = new UserService();
    }


    public boolean addSharedList(Integer listId, String ownerUsername, String username,
                                 boolean updateList, boolean addProduct,
                                 boolean updateProduct, boolean changeState,
                                 boolean deleteProduct) throws UserNotExistException, RowExistYetException {

        User user = userService.getUser(username);
        if (user.getId()==null) throw new UserNotExistException();

        Integer ownerId = userService.getUser(ownerUsername).getId();

        SharedList sharedList = new SharedList();
        sharedList.setListId(listId);
        sharedList.setOwnerId(ownerId);
        sharedList.setUserId(user.getId());
        sharedList.setUsername(username);
        sharedList.setUpdateList(updateList);
        sharedList.setAddingProducts(addProduct);
        sharedList.setUpdateProducts(updateProduct);
        sharedList.setChangingState(changeState);
        sharedList.setDeleteProducts(deleteProduct);
        SharedList listShared = sharedListDao.save(sharedList);
        if (listShared== null) return false;


        if (listShared.getId()!= null) return true;
        return false;
    }

    public List<SharedList> getAllByOwnerId(Integer ownerId){
        List<SharedList> sharedLists= null;
        sharedLists= sharedListDao.getAllByOwnerId(ownerId);
        if (sharedLists == null){
            sharedLists = new ArrayList<>();

        }
        return sharedLists;
    }

    public boolean update(Integer sharedListId, boolean updateList, boolean addProduct, boolean updateProduct, boolean changeState, boolean deleteProduct) {
        return sharedListDao.update(sharedListId, updateList, addProduct, updateProduct, changeState, deleteProduct);
    }

    public boolean deleteSharedListById(Integer sharedListId) {
        return sharedListDao.delete(sharedListId);
    }

    public List<ShoppingList> getAllSharedForUser(String user) {
        return sharedListDao.getAllSharedFor(user);
    }

    public SharedList getOneByListIdAndUsername(Integer listId, String user) {
        return sharedListDao.getOneByIdAndUsername(listId, user);
    }
}
