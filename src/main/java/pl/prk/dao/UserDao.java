package pl.prk.dao;

import pl.prk.model.User;

import java.util.List;

public interface UserDao extends GenericDao<User, Integer> {
    @Override
    User save(User newObject);

    @Override
    User read(Integer primaryKey);
    User read(String username);

    @Override
    boolean update(User updatedObject);

    @Override
    boolean delete(Integer key);

    @Override
    List<User> getAll();
}
