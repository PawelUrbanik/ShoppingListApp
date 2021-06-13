package pl.prk.dao;

import pl.prk.exception.RowExistYetException;

import java.io.Serializable;
import java.util.List;
/**
 * The interface with methods for communication with the database.
 *
 * @author Paweł Urbanik, Radosław Szajdak
 */
public interface GenericDao <T, PK extends Serializable>{
    T save(T newObject) throws RowExistYetException;
    T read(PK primaryKey);
    boolean update(T updatedObject);
    boolean delete(PK key);
    List<T> getAll();
}
