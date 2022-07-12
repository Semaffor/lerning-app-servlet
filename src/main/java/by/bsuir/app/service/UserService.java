package by.bsuir.app.service;

import by.bsuir.app.entity.User;
import by.bsuir.app.entity.enums.Role;
import by.bsuir.app.exception.ServiceException;

import java.util.List;
import java.util.Optional;

/**
 * This interface is concerned with manipulating Users in the database and performing other User-related logic
 */
public interface UserService {

    /**
     * Searches for a User in the database with equals login and password.
     *
     * @param login    - String representing the User's login.
     * @param password - String representing the User's password.
     * @return Optional.empty() if no such User was found. Otherwise, Optional of found User
     * @throws ServiceException if a DaoException occurs
     */
    Optional<User> authorize(String login, String password) throws ServiceException;

    /**
     * Gets User by him username from the database.
     *
     * @param username - name of the user.
     * @return User object from the database.
     * @throws ServiceException if User by such id does not exist in the database or if a DaoException occurs.
     */
    Optional<User> findByUsername(String username) throws ServiceException;

    /**
     * Gets all Users from the database.
     *
     * @return List object containing all Users found in the database.
     * @throws ServiceException if a DaoException occurs.
     */
    List<User> findAll();

    /**
     * Change user blocked status by changing value in field 'is_active'.
     *
     * @param userId - user PK.
     * @throws ServiceException if a DaoException occurs.
     */
    void changeBlockedStatus(Long userId);

    /**
     * Change user deleted status by changing value in field 'is_deleted'.
     *
     * @param userId - user PK.
     * @throws ServiceException if a DaoException occurs.
     */
    void changeDeletedStatus(Long userId);

    /**
     * Change role of the user in the system.
     *
     * @param userId - user PK.
     * @param role   - role assigned to the user.
     * @throws ServiceException if a DaoException occurs.
     */
    void changeRole(Long userId, Role role);

    /**
     * Inserts or updates the User in the database.
     *
     * @param user - object to be saved.
     * @throws ServiceException if a DaoException occurs.
     */
    boolean createUser(User user);
}
