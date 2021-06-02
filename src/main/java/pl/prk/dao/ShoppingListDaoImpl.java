package pl.prk.dao;

import pl.prk.model.ShoppingList;
import pl.prk.util.ConnectionProvider;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShoppingListDaoImpl implements ShoppingListDao{
    private final String CREATE_LIST = "INSERT INTO lists (list_name, list_desc, list_owner, list_type) VALUES(?, ?, ?, ?)";
//    MYSQL
//    private final String GET_LISTS_BY_USER = "SELECT lists.id, list_name, list_desc, list_owner, list_type, username FROM lists INNER JOIN  user  ON lists.list_owner= user.id AND user.username=?";
//    ORACLE
    private final String GET_LISTS_BY_USER = "SELECT lists.id, list_name, list_desc, list_owner, list_type, username FROM lists INNER JOIN  user_l  ON lists.list_owner= user_l.id AND user_l.username=?";
    private final DataSource dataSource;

    public ShoppingListDaoImpl() {
        this.dataSource = ConnectionProvider.getDataSource();
    }
    @Override
    public ShoppingList save(ShoppingList newObject) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_LIST))
        {
            statement.setString(1, newObject.getName());
            statement.setString(2, newObject.getDescription());
            statement.setInt(3, newObject.getOwner());
            statement.setString(4, newObject.getType());
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

    @Override
    public List<ShoppingList> getListsByUser(String username) {
        List<ShoppingList> lists = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_LISTS_BY_USER))
        {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            lists = mapRow(resultSet);
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return lists;
    }

    private List<ShoppingList> mapRow(ResultSet resultSet) throws SQLException {
        List<ShoppingList> resultList = new ArrayList<>();
        while (resultSet.next())
        {
            ShoppingList shoppingList = new ShoppingList();
            shoppingList.setId(resultSet.getInt("id"));
            shoppingList.setName(resultSet.getString("list_name"));
            shoppingList.setDescription(resultSet.getString("list_desc"));
            shoppingList.setOwner(resultSet.getInt("list_owner"));
            shoppingList.setType(resultSet.getString("list_type"));
            resultList.add(shoppingList);
        }
        return resultList;
    }
}
