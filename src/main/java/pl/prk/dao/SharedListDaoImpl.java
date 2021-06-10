package pl.prk.dao;

import pl.prk.exception.RowExistYetException;
import pl.prk.model.Product;
import pl.prk.model.SharedList;
import pl.prk.model.ShoppingList;
import pl.prk.util.ConnectionProvider;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SharedListDaoImpl implements SharedListDao {

    private final String CREATE_SHARED_LIST = "INSERT INTO shared (list_id, owner_id, user_id, username, update_list, add_product, " +
            "update_product, change_state, delete_product) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String CHECK_IF_ROW_EXIST = "SELECT * FROM shared WHERE list_id = ? AND user_id = ?";
    private final String GET_SHARED_BY_OWNER_ID = "SELECT * FROM shared WHERE owner_id=? AND list_id = ?";
    private final String UPDATE_SHARED = "UPDATE shared SET update_list=?, add_product=?, update_product=?," +
            "change_state=?, delete_product=? WHERE id=?";
    private final String DELETE_SHARED = "DELETE FROM shared WHERE id=?";
    private final String GET_SHARED_FOR = "SELECT lists.id, lists.list_name, lists.list_desc, lists.list_owner, lists.list_type FROM lists" +
            " INNER JOIN shared ON shared.username = ? AND shared.list_id = lists.id";
    private final String GET_ONE_BY_ID_AND_USERNAME = "SELECT * FROM shared WHERE list_id = ? AND username = ?";
    private final DataSource dataSource;

    public SharedListDaoImpl() {
        this.dataSource = ConnectionProvider.getDataSource();
    }


    @Override
    public SharedList save(SharedList newObject) throws RowExistYetException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement checkIfExistStatement = connection.prepareStatement(CHECK_IF_ROW_EXIST);
             PreparedStatement statement = connection.prepareStatement(CREATE_SHARED_LIST, new String[]{"id"})) {


            checkIfExistStatement.setInt(1, newObject.getListId());
            checkIfExistStatement.setInt(2, newObject.getUserId());
            ResultSet resultSet = checkIfExistStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println(resultSet.getInt(1));
                System.out.println("Exist!");
                throw new RowExistYetException();
            }


            statement.setInt(1, newObject.getListId());
            statement.setInt(2, newObject.getOwnerId());
            statement.setInt(3, newObject.getUserId());
            statement.setString(4, newObject.getUsername());
            statement.setBoolean(5, newObject.isUpdateList());
            statement.setBoolean(6, newObject.isAddingProducts());
            statement.setBoolean(7, newObject.isUpdateProducts());
            statement.setBoolean(8, newObject.isChangingState());
            statement.setBoolean(9, newObject.isDeleteProducts());

            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                Integer id = generatedKeys.getInt(1);
                newObject.setId(id);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return newObject;
    }

    @Override
    public SharedList read(Integer primaryKey) {
        return SharedListDao.super.read(primaryKey);
    }

    @Override
    public boolean update(SharedList updatedObject) {
        return SharedListDao.super.update(updatedObject);
    }

    @Override
    public boolean delete(Integer key) {
        int deletedRows = 0;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_SHARED))
        {
            statement.setInt(1,key);
            deletedRows = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("catch");
            System.out.println(e.getMessage());
            return false;
        }
        return deletedRows > 0;
    }

    @Override
    public List<SharedList> getAll() {
        return SharedListDao.super.getAll();
    }

    @Override
    public List<SharedList> getAllByOwnerId(Integer ownnerId, Integer listId) {
        List<SharedList> shared = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_SHARED_BY_OWNER_ID, Statement.RETURN_GENERATED_KEYS))
        {
            statement.setInt(1, ownnerId);
            statement.setInt(2, listId);
            ResultSet resultSet = statement.executeQuery();
            shared = mapRow(resultSet);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return shared;
    }

    private List<SharedList> mapRow(ResultSet resultSet) throws SQLException {
        List<SharedList> sharedLists = new ArrayList<>();
        while (resultSet.next())
        {
            SharedList sharedList = new SharedList();
            sharedList.setId(resultSet.getInt("id"));
            sharedList.setListId(resultSet.getInt("list_id"));
            sharedList.setOwnerId(resultSet.getInt("owner_id"));
            sharedList.setUserId(resultSet.getInt("user_id"));
            sharedList.setUsername(resultSet.getString("username"));
            sharedList.setUpdateList(resultSet.getBoolean("update_list"));
            sharedList.setAddingProducts(resultSet.getBoolean("add_product"));
            sharedList.setUpdateProducts(resultSet.getBoolean("update_product"));
            sharedList.setChangingState(resultSet.getBoolean("change_state"));
            sharedList.setDeleteProducts(resultSet.getBoolean("delete_product"));


            sharedLists.add(sharedList);
        }
        return sharedLists;
    }

    @Override
    public boolean update(Integer sharedListId, boolean updateList, boolean addProduct, boolean updateProduct, boolean changeState, boolean deleteProduct) {
        int rowUpdated;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_SHARED))
        {
            statement.setBoolean(1,updateList);
            statement.setBoolean(2,addProduct);
            statement.setBoolean(3,updateProduct);
            statement.setBoolean(4,changeState);
            statement.setBoolean(5,deleteProduct);

            statement.setInt(6,sharedListId);

            rowUpdated= statement.executeUpdate();
        }catch (SQLException e)
        {
            System.out.println("catch");
            System.out.println(e.getMessage());
            return false;
        }

        return rowUpdated > 0;
    }


    @Override
    public List<ShoppingList> getAllSharedFor(String user) {
        List<ShoppingList> sharedFor = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_SHARED_FOR))
        {
            statement.setString(1, user);
            ResultSet resultSet = statement.executeQuery();
            sharedFor = mapRowSL(resultSet);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return sharedFor;


    }

    private List<ShoppingList> mapRowSL(ResultSet resultSet) throws SQLException {
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

    @Override
    public SharedList getOneByIdAndUsername(Integer listId, String user) {
        SharedList sharedList =null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ONE_BY_ID_AND_USERNAME))
        {

            statement.setInt(1, listId);
            statement.setString(2, user);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                sharedList = new SharedList();
                sharedList.setId(resultSet.getInt("id"));
                sharedList.setListId(resultSet.getInt("list_id"));
                sharedList.setOwnerId(resultSet.getInt("owner_id"));
                sharedList.setUserId(resultSet.getInt("user_id"));
                sharedList.setUsername(resultSet.getString("username"));
                sharedList.setUpdateList(resultSet.getBoolean("update_list"));
                sharedList.setAddingProducts(resultSet.getBoolean("add_product"));
                sharedList.setUpdateProducts(resultSet.getBoolean("update_product"));
                sharedList.setChangingState(resultSet.getBoolean("change_state"));
                sharedList.setDeleteProducts(resultSet.getBoolean("delete_product"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sharedList;

    }
}
