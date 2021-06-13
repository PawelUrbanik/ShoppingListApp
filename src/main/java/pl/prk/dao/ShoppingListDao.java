package pl.prk.dao;

import pl.prk.model.ShoppingList;
import pl.prk.util.ConnectionProvider;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.List;
/**
 * The interface with methods for ShoppingList class handling.
 *
 * @author Paweł Urbanik, Radosław Szajdak
 */
public interface ShoppingListDao extends GenericDao<ShoppingList, Integer> {
    @Override
    ShoppingList save(ShoppingList newObject);

    @Override
    ShoppingList read(Integer primaryKey);

    @Override
    boolean update(ShoppingList updatedObject);

    @Override
    boolean delete(Integer key);

    @Override
    List<ShoppingList> getAll();

    List<ShoppingList> getListsByUser(String username);
}

