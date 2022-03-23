package by.bsuir.app.dao;

import by.bsuir.app.entity.Identifiable;
import by.bsuir.app.exception.DaoException;

import java.util.List;
import java.util.Optional;

/**
 * Main interface for all DAO objects with operations of creating, updating,
 * deleting and finding some specific object by their id.
 *
 * @param <T> type of the object which should extends interface {@link Identifiable}.
 */
public interface Dao<T extends Identifiable> {
    /**
     * Finds entity in database by id.
     *
     * @param id - primary key of entity.
     * @return optional generic object.
     */
    Optional<T> getById(Long id);

    /**
     * Finds all entities from a specific table in database.
     *
     * @return list of generic entities.
     */
    List<T> getAll() throws DaoException;

    /**
     * Saves specified object in database.
     *
     * @param item - object to be saved
     * @return if data has been saved true, otherwise false.
     */
    boolean save(T item);

    /**
     * Removes entity from database by id.
     *
     * @param id - primary key of entity.
     * @return if row has been deleted true, otherwise false.
     */
    boolean removeById(Long id);
}
