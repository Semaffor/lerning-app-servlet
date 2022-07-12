package by.bsuir.app.dao;

import by.bsuir.app.connection.ConnectionPool;
import by.bsuir.app.connection.ProxyConnection;
import by.bsuir.app.dao.impl.*;
import by.bsuir.app.exception.DaoException;

import java.sql.SQLException;

public class DaoHelper implements AutoCloseable {

    private final ProxyConnection connection;

    public DaoHelper(ConnectionPool pool) {
        this.connection = pool.getConnection();
    }

    @Override
    public void close() throws DaoException {
        try {
            connection.close();
        } catch (Exception e) {
            throw new DaoException(e);
        }
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

    public CourseDao createCourseDao() {
        return new CourseDaoImpl(connection);
    }

    public TaskDao createTaskDao() {
        return new TaskDaoImpl(connection);
    }

    public UserCourseDao createUserCourseDao() {
        return new UserCourseDaoImpl(connection);
    }

    public UserTaskDao createUserTaskDao() {
        return new UserTaskDaoImpl(connection);
    }

    public UserTaskCustomDao createUserTaskCustomDao() {
        return new UserTaskCustomDaoImpl(connection);
    }
}
