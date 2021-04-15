package pl.prk.service;

import pl.prk.dao.ProductDao;
import pl.prk.dao.ProductDaoImpl;
import pl.prk.model.Product;

import java.util.List;

public class ProductService {

    ProductDao productDao = new ProductDaoImpl();

    public void addProductToList(String name, String username, Integer listId)
    {
        Product productToAdd = new Product();
        productToAdd.setName(name);
        productToAdd.setAddedBy(username);
        productToAdd.setBought(false);
        productToAdd.setListId(listId);

        productDao.save(productToAdd);
    }

    public List<Product> getProductsByList(Integer listId) {
        List<Product> products = productDao.getProductsById(listId);
        return products;
    }
}
