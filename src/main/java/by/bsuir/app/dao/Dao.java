package by.bsuir.app.dao;

import by.bsuir.app.entity.Identifiable;
import by.bsuir.app.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface Dao<T extends Identifiable> {
    Optional<T> getById(Long id);
    List<T> getAll() throws DaoException;
    boolean save(T item);
    boolean removeById(Long id);
}
