package pl.prk.dao;

import pl.prk.model.Product;
import pl.prk.util.ConnectionProvider;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao{

    private final String CREATE_PRODUCT = "INSERT INTO products (name, listId, bought, addedBy) VALUES (?,?,?,?)";
    private final String GET_PRODUCT_BY_LISTID = "SELECT * FROM products WHERE listId=?";
    private final DataSource dataSource;

    public ProductDaoImpl() {
        this.dataSource = ConnectionProvider.getDataSource();
    }

    @Override
    public Product save(Product newObject) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_PRODUCT, Statement.RETURN_GENERATED_KEYS))
        {
            statement.setString(1,newObject.getName());
            statement.setInt(2, newObject.getListId());
            statement.setBoolean(3, newObject.isBought());
            statement.setString(4, newObject.getAddedBy());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                newObject.setId(generatedKeys.getInt(1));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return newObject;
    }

    @Override
    public List<Product> getProductsById(Integer listId) {
        List<Product> products = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_PRODUCT_BY_LISTID, Statement.RETURN_GENERATED_KEYS))
        {
            statement.setInt(1, listId);
            ResultSet resultSet = statement.executeQuery();
            products = mapRow(resultSet);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return products;
    }

    private List<Product> mapRow(ResultSet resultSet) throws SQLException {
        List<Product> products = new ArrayList<>();
        while (resultSet.next())
        {
            Product product = new Product();
            product.setId(resultSet.getInt("id"));
            product.setName(resultSet.getString("name"));
            product.setBought(resultSet.getBoolean("bought"));
            product.setAddedBy(resultSet.getString("addedBy"));
            product.setListId(resultSet.getInt("listId"));

            products.add(product);
        }
        return products;
    }

    @Override
    public Product read(Integer primaryKey) {
        return null;
    }

    @Override
    public boolean update(Product updatedObject) {
        return false;
    }

    @Override
    public boolean delete(Integer key) {
        return false;
    }

    @Override
    public List<Product> getAll() {
        return null;
    }
}
