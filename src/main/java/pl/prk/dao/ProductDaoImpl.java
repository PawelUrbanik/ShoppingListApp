package pl.prk.dao;

import pl.prk.model.Product;
import pl.prk.util.ConnectionProvider;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class ProductDaoImpl implements ProductDao{

    private final String CREATE_PRODUCT = "INSERT INTO products (name, listId, bought, addedBy) VALUES (?,?,?,?)";
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
