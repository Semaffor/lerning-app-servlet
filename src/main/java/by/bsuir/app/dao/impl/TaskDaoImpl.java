package by.bsuir.app.dao.impl;

import by.bsuir.app.dao.AbstractDao;
import by.bsuir.app.dao.CourseDao;
import by.bsuir.app.entity.Course;
import by.bsuir.app.entity.Task;
import by.bsuir.app.mapper.CourseRowMapper;
import by.bsuir.app.mapper.RowMapper;
import by.bsuir.app.mapper.TaskRowMapper;

import java.sql.Connection;
import java.util.Map;

public class TaskDaoImpl extends AbstractDao<Task> implements CourseDao {

    protected TaskDaoImpl(Connection connection) {
        super(connection, new TaskRowMapper(), Task.TABLE);
    }

    @Override
    protected Map<String, Object> getFields(Task item) {
        return null;
    }
}
