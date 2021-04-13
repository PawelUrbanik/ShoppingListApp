package pl.prk.dao;

import pl.prk.model.User;
import pl.prk.util.ConnectionProvider;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private final String CREATE_USER = "INSERT INTO user (username, email, password) VALUES (?, ?, ?);";
    private final String CREATE_USER_ROLE = "INSERT INTO user_role (username) VALUES (?);";

    private final DataSource dataSource;

    public UserDaoImpl() {
        this.dataSource = ConnectionProvider.getDataSource();
    }

    @Override
    public User save(User newUser) {
        saveUser(newUser);
        saveUserRole(newUser);
        return null;
    }

    @Override
    public User read(Integer primaryKey) {
        return null;
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

    private void saveUser(User newUser) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_USER, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, newUser.getUsername());
            statement.setString(2, newUser.getEmail());
            statement.setString(3, newUser.getPassword());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                newUser.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
