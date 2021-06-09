package pl.prk.dao;

import pl.prk.exception.RowExistYetException;
import pl.prk.model.SharedList;

import java.util.List;

public interface SharedListDao extends GenericDao<SharedList, Integer> {

    @Override
    default SharedList save(SharedList newObject) throws RowExistYetException {
        return null;
    }

    @Override
    default SharedList read(Integer primaryKey) {
        return null;
    }

    @Override
    default boolean update(SharedList updatedObject) {
        return false;
    }

    @Override
    default boolean delete(Integer key) {
        return false;
    }

    @Override
    default List<SharedList> getAll() {
        return null;
    }
}
