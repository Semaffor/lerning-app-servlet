package by.bsuir.app.dao.impl;

import by.bsuir.app.dao.AbstractDao;
import by.bsuir.app.dao.TaskDao;
import by.bsuir.app.entity.Task;
import by.bsuir.app.mapper.TaskRowMapper;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.Map;

public class TaskDaoImpl extends AbstractDao<Task> implements TaskDao {

    public TaskDaoImpl(Connection connection) {
        super(connection, new TaskRowMapper(), Task.TABLE);
    }

    @Override
    protected Map<String, Object> getFields(Task item) {
        Map<String, Object> fields = new LinkedHashMap<>();
        fields.put(Task.TITLE, item.getTitle());
        fields.put(Task.DESCRIPTION, item.getDescription());
        fields.put(Task.COURSE_ID, item.getCourseId());
        fields.put(Task.DEADLINE, item.getDeadline());
        fields.put(Task.DELETED, item.isDeleted());
        return fields;
    }
}
