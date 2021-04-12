package pl.prk.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao <T, PK extends Serializable>{
    T create(T newObject);
    T read(PK primaryKey);
    boolean update(T updatedObject);
    boolean delete(PK key);
    List<T> getAll();
}
