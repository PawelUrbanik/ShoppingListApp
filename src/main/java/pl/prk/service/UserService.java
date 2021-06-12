package pl.prk.service;

import org.apache.commons.codec.digest.DigestUtils;
import pl.prk.dao.UserDao;
import pl.prk.dao.UserDaoImpl;
import pl.prk.model.User;
import pl.prk.model.UserRegistration;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class UserService {

    private UserDao userDao = new UserDaoImpl();

    public boolean register(UserRegistration userRegistration)
    {
        User userToSave = UserMapper.map(userRegistration);
        try {
            hashPasswordWithSha256(userToSave);
            return userDao.saveU(userToSave);
        } catch (NoSuchAlgorithmException | SQLException e) {
            System.err.println("Problem to save user");
            return false;
        }

    }

    public User getUser(String username)
    {
        User user= userDao.read(username);
        return user;
    }

    private void hashPasswordWithSha256(User user) throws NoSuchAlgorithmException {
        String sha256Password = DigestUtils.sha256Hex(user.getPassword());
        user.setPassword(sha256Password);
    }

    private static class UserMapper {
        static User map(UserRegistration userRegistration) {
            return new User(
                    userRegistration.getUsername(),
                    userRegistration.getEmail(),
                    userRegistration.getPassword()
            );
        }
    }
}
