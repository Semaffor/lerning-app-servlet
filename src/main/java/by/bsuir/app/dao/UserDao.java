package by.bsuir.app.dao;

import by.bsuir.app.entity.User;
import by.bsuir.app.entity.UserCourse;
import by.bsuir.app.exception.DaoException;

import java.util.List;
import java.util.Optional;

/**
 * One of DAO interface that extends main Dao, contains specific class methods.
 *
 * @see User
 */
public interface UserDao extends Dao<User> {
    /**
     * Finds existing user in database.
     *
     * @param login    - username.
     * @param password - account user password.
     * @return if row with user's data exists, not nullable optional value.
     * @throws DaoException wrapper for {@link java.sql.SQLException}.
     */
    Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException;

    /**
     * Finds existing user in database.
     *
     * @param username - username in db.
     * @return if username exists, not nullable optional value with user data.
     * @throws DaoException wrapper for {@link java.sql.SQLException}.
     */
    Optional<User> findByUsername(String username) throws DaoException;
}
