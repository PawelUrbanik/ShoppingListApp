package pl.prk.dao;

import pl.prk.model.SharedList;
import pl.prk.util.ConnectionProvider;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SharedListDaoImpl implements SharedListDao {

    private final String CREATE_SHARED_LIST = "INSERT INTO shared (list_id, owner_id, user_id, update_list, add_product, " +
            "update_product, change_state, delete_product) values (?, ?, ?, ?, ?, ?, ?, ?)";
    private final String CHECK_IF_ROW_EXIST = "SELECT * FROM shared WHERE list_id = ? AND user_id = ?";
    private final DataSource dataSource;

    public SharedListDaoImpl() {
        this.dataSource = ConnectionProvider.getDataSource();
    }


    @Override
    public SharedList save(SharedList newObject) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement checkIfExistStatement = connection.prepareStatement(CHECK_IF_ROW_EXIST);
             PreparedStatement statement = connection.prepareStatement(CREATE_SHARED_LIST))
        {


            ResultSet resultSet =  checkIfExistStatement.executeQuery();
            if (resultSet.next())
            {
                System.out.println("Exist!");
                return null;
            }

            statement.setInt(1, newObject.getListId());
            statement.setInt(2, newObject.getOwnerId());
            statement.setInt(3, newObject.getUserId());
            statement.setBoolean(4, newObject.isUpdateList());
            statement.setBoolean(5, newObject.isAddingProducts());
            statement.setBoolean(6, newObject.isUpdateProducts());
            statement.setBoolean(7, newObject.isChangingState());
            statement.setBoolean(8, newObject.isDeleteProducts());

            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys!=null) {
                newObject.setId(generatedKeys.getInt(1));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return newObject;
    }

        @Override
        public SharedList read (Integer primaryKey){
            return SharedListDao.super.read(primaryKey);
        }

        @Override
        public boolean update (SharedList updatedObject){
            return SharedListDao.super.update(updatedObject);
        }

        @Override
        public boolean delete (Integer key){
            return SharedListDao.super.delete(key);
        }

        @Override
        public List<SharedList> getAll () {
            return SharedListDao.super.getAll();
        }
    }
