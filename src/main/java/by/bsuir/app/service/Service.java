package by.bsuir.app.service;

import by.bsuir.app.dao.DaoHelperFactory;

public abstract class Service {

    protected final DaoHelperFactory daoHelperFactory;

    public Service(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }
}
