package pl.prk.dao;

import pl.prk.model.Product;

import java.util.List;
/**
 * The interface with methods for Product class handling.
 *
 * @author Paweł Urbanik, Radosław Szajdak
 */
public interface ProductDao extends GenericDao<Product, Integer> {
    @Override
    Product save(Product newObject);

    @Override
    Product read(Integer primaryKey);

    @Override
    boolean update(Product updatedObject);

    @Override
    boolean delete(Integer key);

    @Override
    List<Product> getAll();

    List<Product> getProductsById(Integer listId);

    boolean changeBought(int productId, boolean bought);
}
