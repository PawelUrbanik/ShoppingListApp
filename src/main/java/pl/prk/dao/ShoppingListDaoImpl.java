package pl.prk.dao;

import pl.prk.model.ShoppingList;
import pl.prk.util.ConnectionProvider;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class ShoppingListDaoImpl implements ShoppingListDao{
    private final String CREATE_LIST = "INSERT INTO lists (list_name, list_desc, list_owner) VALUES(?, ?, ?)";
    private final DataSource dataSource;

    public ShoppingListDaoImpl() {
        this.dataSource = ConnectionProvider.getDataSource();
    }
    @Override
    public ShoppingList save(ShoppingList newObject) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_LIST, Statement.RETURN_GENERATED_KEYS))
        {
            statement.setString(1, newObject.getName());
            statement.setString(2, newObject.getDescription());
            statement.setInt(3, newObject.getOwner());
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
    public ShoppingList read(Integer primaryKey) {
        return null;
    }

    @Override
    public boolean update(ShoppingList updatedObject) {
        return false;
    }

    @Override
    public boolean delete(Integer key) {
        return false;
    }

    @Override
    public List<ShoppingList> getAll() {
        return null;
    }
}
