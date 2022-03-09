package by.bsuir.app.dao;

import by.bsuir.app.entity.User;
import by.bsuir.app.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface UserDao extends Dao<User>{
    Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException;
    Optional<User> findByUsername(String username) throws DaoException;
}
