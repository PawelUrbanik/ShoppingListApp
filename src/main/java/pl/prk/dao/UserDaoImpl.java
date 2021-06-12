package pl.prk.dao;

import pl.prk.model.User;
import pl.prk.util.ConnectionProvider;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class UserDaoImpl implements UserDao {
//    MYSQL
//    private final String CREATE_USER = "INSERT INTO user (username, email, password) VALUES (?, ?, ?);";
//    ORACLE
    private final String CREATE_USER = "INSERT INTO user_l (username, email, password) VALUES (?, ?, ?)";
    private final String CREATE_USER_ROLE = "INSERT INTO user_role (username) VALUES (?)";
//    MYSQL
//    private final String GET_USER = "SELECT id, username, password FROM user WHERE username = ?";
//    ORACLE
    private final String GET_USER = "SELECT id, username, password FROM user_l WHERE username = ?";
    private final String GET_USER_BY_ID = "SELECT id, username, password FROM user_l WHERE id = ?";

    private final DataSource dataSource;

    public UserDaoImpl() {
        this.dataSource = ConnectionProvider.getDataSource();
    }

    @Override
    public boolean saveU(User newUser) throws SQLException {
        saveUser(newUser);
        saveUserRole(newUser);
        return true;
    }

    @Override
    public User save(User newObject) {
        return null;
    }

    @Override
    public User read(Integer primaryKey) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_USER_BY_ID))
        {
            statement.setInt(1, primaryKey);
            ResultSet resultSet = statement.executeQuery();
            User user = mapRow(resultSet);
            return user;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public User read(String username) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_USER))
        {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            User user = mapRow(resultSet);
            return user;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    private User mapRow(ResultSet resultSet) throws SQLException {
        User user = new User();
        if (resultSet.next()){
            Integer id = resultSet.getInt("id");
            String username = resultSet.getString("username");
            user.setId(id);
            user.setUsername(username);}

            return user;
    }

    @Override
    public boolean update(User updatedObject) {
        return false;
    }

    @Override
    public boolean delete(Integer key) {
        return false;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    private void saveUser(User newUser) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_USER)) {
            statement.setString(1, newUser.getUsername());
            statement.setString(2, newUser.getEmail());
            statement.setString(3, newUser.getPassword());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                newUser.setId(generatedKeys.getInt(1));
            }
        }
    }

    private void saveUserRole(User newUser) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_USER_ROLE)) {
            statement.setString(1, newUser.getUsername());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
