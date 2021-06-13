package pl.prk.dao;

import pl.prk.exception.RowExistYetException;
import pl.prk.model.SharedList;
import pl.prk.model.ShoppingList;

import java.util.List;
/**
 * The interface with methods for SharedList class handling.
 *
 * @author Paweł Urbanik, Radosław Szajdak
 */
public interface SharedListDao extends GenericDao<SharedList, Integer> {

    @Override
    default SharedList save(SharedList newObject) throws RowExistYetException {
        return null;
    }

    @Override
    default SharedList read(Integer primaryKey) {
        return null;
    }

    @Override
    default boolean update(SharedList updatedObject) {
        return false;
    }

    @Override
    default boolean delete(Integer key) {
        return false;
    }

    @Override
    default List<SharedList> getAll() {
        return null;
    }

    public List<SharedList> getAllByOwnerId(Integer ownnerId, Integer listId);

    public boolean update(Integer sharedListId, boolean updateList, boolean addProduct, boolean updateProduct, boolean changeState, boolean deleteProduct);

    public List<ShoppingList> getAllSharedFor(String user);

    SharedList getOneByIdAndUsername(Integer listId, String user);
}
