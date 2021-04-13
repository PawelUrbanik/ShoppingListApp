package pl.prk.service;

import pl.prk.dao.UserDao;
import pl.prk.dao.UserDaoImpl;
import pl.prk.model.User;
import pl.prk.model.UserRegistration;

public class UserService {

    private UserDao userDao = new UserDaoImpl();

    public void register(UserRegistration userRegistration)
    {
        User userToSave = UserMapper.map(userRegistration);
        userDao.save(userToSave);
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
