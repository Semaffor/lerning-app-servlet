package by.bsuir.app.dao.impl;

import by.bsuir.app.dao.AbstractDao;
import by.bsuir.app.dao.TaskDao;
import by.bsuir.app.entity.Task;
import by.bsuir.app.mapper.TaskRowMapper;

import java.sql.Connection;
import java.util.Map;

public class TaskDaoImpl extends AbstractDao<Task> implements TaskDao {

    public TaskDaoImpl(Connection connection) {
        super(connection, new TaskRowMapper(), Task.TABLE);
    }

    @Override
    protected Map<String, Object> getFields(Task item) {
        return null;
    }
}
