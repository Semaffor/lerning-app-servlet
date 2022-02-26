package by.bsuir.app.dao;

import by.bsuir.app.connection.ConnectionPool;
import by.bsuir.app.connection.ProxyConnection;
import by.bsuir.app.dao.impl.UserDaoImpl;
import by.bsuir.app.exception.DaoException;

import java.sql.SQLException;

public class DaoHelper implements AutoCloseable{
    private ProxyConnection connection;

    public DaoHelper(ConnectionPool pool) {
        this.connection = pool.getConnection();
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }

    public void startTransaction() throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void endTransaction() throws DaoException {
        try {
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public UserDao createUserDao() {
        return new UserDaoImpl(connection);
    }
}