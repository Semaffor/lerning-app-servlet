package by.bsuir.app.service.impl;

import by.bsuir.app.dao.DaoHelperFactory;
import by.bsuir.app.dao.TaskDao;
import by.bsuir.app.service.Service;
import by.bsuir.app.service.TaskService;

public class TaskServiceImpl extends Service implements TaskService {

    public TaskServiceImpl(DaoHelperFactory daoHelperFactory) {
        super(daoHelperFactory);
    }
}
