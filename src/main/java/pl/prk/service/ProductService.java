package pl.prk.service;

import pl.prk.dao.ProductDao;
import pl.prk.dao.ProductDaoImpl;
import pl.prk.model.Product;

import java.util.List;

public class ProductService {

    ProductDao productDao = new ProductDaoImpl();

    public void addProductToList(String name, String username, Integer listId, int count)
    {
        Product productToAdd = new Product();
        productToAdd.setName(name);
        productToAdd.setAddedBy(username);
        productToAdd.setBought(false);
        productToAdd.setListId(listId);
        productToAdd.setCount(count);

        productDao.save(productToAdd);
    }

    public List<Product> getProductsByList(Integer listId) {
        List<Product> products = productDao.getProductsById(listId);
        return products;
    }

    public void delete(int productId) {
        boolean deleted= productDao.delete(productId);
        if (deleted) System.out.println("Row deleted");
        if (!deleted) System.out.println("Row not deleted");
    }

    public void update(Product product) {
       boolean updated = productDao.update(product);
        if (updated) System.out.println("Row updated");
        if (!updated) System.out.println("Row not updated");
    }

    public void changeBought(int productId, boolean bought) {
        boolean updated = productDao.changeBought(productId, !bought);
        if (updated) System.out.println("Row updated");
        if (!updated) System.out.println("Row not updated");
    }
}
