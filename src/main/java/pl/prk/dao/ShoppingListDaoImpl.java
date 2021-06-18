package pl.prk.dao;

import pl.prk.model.ShoppingList;
import pl.prk.util.ConnectionProvider;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The implantation of ShoppingListDao interface.
 *
 * @author Paweł Urbanik, Radosław Szajdak
 */
public class ShoppingListDaoImpl implements ShoppingListDao{
    private final String CREATE_LIST = "INSERT INTO lists (list_name, list_desc, list_owner, list_type, last_update) VALUES(?, ?, ?, ?, ?)";
//    MYSQL
//    private final String GET_LISTS_BY_USER = "SELECT lists.id, list_name, list_desc, list_owner, list_type, username FROM lists INNER JOIN  user  ON lists.list_owner= user.id AND user.username=?";
//    ORACLE
    private final String GET_LISTS_BY_USER = "SELECT lists.id, list_name, list_desc, list_owner, list_type, username FROM lists INNER JOIN  user_l  ON lists.list_owner= user_l.id AND user_l.username=?";
    private final String UPDATE_LIST = "UPDATE lists SET list_name = ?, list_desc = ?, last_update=? WHERE id = ?";
    private final String DELETE_LIST = "DELETE FROM lists WHERE id=?";
    private final String DELETE_PRODUCTS_FROM_LIST = "DELETE FROM products WHERE listid = ?";
    private final String DELETE_SHARED = "DELETE FROM shared WHERE list_id = ?";
    private final String GET_LIST_BY_ID = "SELECT id, list_name, list_desc, list_owner, list_type FROM lists WHERE id =?";
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
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            statement.setTimestamp(5, timestamp);
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
        ShoppingList list = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_LIST_BY_ID))
        {
            statement.setInt(1, primaryKey);
            ResultSet resultSet = statement.executeQuery();
            list = mapOneRow(resultSet);
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return list;
    }

    private ShoppingList mapOneRow(ResultSet resultSet) throws SQLException {
        ShoppingList result= new ShoppingList();
        while (resultSet.next())
        {
            result.setId(resultSet.getInt("id"));
            result.setName(resultSet.getString("list_name"));
            result.setDescription(resultSet.getString("list_desc"));
            result.setOwner(resultSet.getInt("list_owner"));
            result.setType(resultSet.getString("list_type"));
        }
        return result;
    }

    @Override
    public boolean update(ShoppingList updatedObject) {

        int rowUpdated = 0;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_LIST))
        {
            statement.setString(1,updatedObject.getName());
            statement.setString(2,updatedObject.getDescription());
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            statement.setTimestamp(3, timestamp);
            statement.setInt(4, updatedObject.getId());
            rowUpdated= statement.executeUpdate();
        }catch (SQLException e)
        {
            System.out.println("catch");
            System.out.println(e.getMessage());
            return false;
        }

        if (rowUpdated>0) return true;
        return false;
    }

    @Override
    public boolean delete(Integer key) {

        int deletedLists = 0;
        int deletedProducts = 0;
        int deletedShared = 0;
        Connection connection = null;
        PreparedStatement deleteListStatement = null;
        PreparedStatement deleteProductsStatement = null;
        PreparedStatement deleteSharedStatement = null;
        try
        {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            deleteListStatement = connection.prepareStatement(DELETE_LIST);
            deleteProductsStatement = connection.prepareStatement(DELETE_PRODUCTS_FROM_LIST);
            deleteSharedStatement = connection.prepareStatement(DELETE_SHARED);


            deleteProductsStatement.setInt(1,key);
            deletedProducts = deleteProductsStatement.executeUpdate();

            deleteSharedStatement.setInt(1, key);
            deletedShared = deleteSharedStatement.executeUpdate();


            deleteListStatement.setInt(1, key);
            deletedLists = deleteListStatement.executeUpdate();

            System.out.println("Deleted products: " + deletedProducts + "Deleted lists: " + deletedLists+", deletedShared: " + deletedShared);
            connection.commit();

        }catch (SQLException e)
        {
            try {
                connection.rollback();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
            System.out.println("catch");
            System.out.println(e.getMessage());
            return false;
        }finally {
            if (deleteProductsStatement!=null) {
                try {
                    deleteProductsStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (deleteListStatement!= null) {
                try {
                    deleteListStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection!= null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        if (deletedLists>0) return true;
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

    public void updateLastUpdate(Integer listId) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement())
        {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            String SQL = "UPDATE lists SET last_update="+timestamp+" WHERE id ="+listId;
            statement.executeUpdate(SQL);
        }
    }

    @Override
    public Timestamp getLastUpdate(Integer listId) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement())
        {
            String SQL = "SELECT last_update FROM lists WHERE id="+listId;
            ResultSet resultSet = statement.executeQuery(SQL);
            Timestamp timestamp = null;
            while (resultSet.next())
            {
                timestamp =resultSet.getTimestamp(1);
            }

            return timestamp;
        }
    }
}
