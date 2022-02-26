package by.bsuir.app.dao;

import by.bsuir.app.connection.ConnectionPool;

public class DaoHelperFactory {
    public DaoHelper create() {
        return new DaoHelper(ConnectionPool.getInstance());
    }
}
