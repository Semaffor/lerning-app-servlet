package by.bsuir.app.dao.impl;

import by.bsuir.app.dao.AbstractDao;
import by.bsuir.app.dao.UserDao;
import by.bsuir.app.entity.User;
import by.bsuir.app.exception.DaoException;
import by.bsuir.app.mapper.UserRowMapper;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    private static final String SQL_FIND_BY_LOGIN_AND_PASSWORD = "select * from %s where %s=? and %s=MD5(?);";
    private static final String SQL_FIND_BY_USERNAME = "select * from %s where %s = ?";

    public UserDaoImpl(Connection connection) {
        super(connection, new UserRowMapper(), User.TABLE);
    }

    @Override
    public Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException{
        String query = String.format(SQL_FIND_BY_LOGIN_AND_PASSWORD,User.TABLE, User.NAME, User.PASSWORD);
        return executeForSingleResultString(query, login, password);
    }

    @Override
    public Optional<User> findByUsername(String username) throws DaoException{
        String query = String.format(SQL_FIND_BY_USERNAME, User.TABLE, User.NAME);
        return executeForSingleResultString(query, username);
    }

    @Override
    protected Map<String, Object> getFields(User item) {
        Map<String, Object> fields = new LinkedHashMap<>();
        fields.put(User.NAME, item.getUsername());
        fields.put(User.PASSWORD, item.getPassword());
        fields.put(User.ROLE, item.getRole().getCode());
        fields.put(User.BLOCKED, item.isBlocked());
        fields.put(User.DELETED, item.isDeleted());

        return fields;
    }
}
