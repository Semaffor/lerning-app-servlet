package by.bsuir.app.dao.impl;

import by.bsuir.app.connection.ProxyConnection;
import by.bsuir.app.dao.AbstractDao;
import by.bsuir.app.dao.UserTaskDao;
import by.bsuir.app.entity.UserTask;
import by.bsuir.app.mapper.UserTaskRowMapper;

import java.util.LinkedHashMap;
import java.util.Map;

public class UserTaskDaoImpl extends AbstractDao<UserTask> implements UserTaskDao {

    public UserTaskDaoImpl(ProxyConnection connection) {
        super(connection, new UserTaskRowMapper(), UserTask.TABLE);
    }

    @Override
    protected Map<String, Object> getFields(UserTask item) {
        Map<String, Object> fields = new LinkedHashMap<>();
        fields.put(UserTask.USER_ID, item.getUserId());
        fields.put(UserTask.TASK_ID, item.getTaskId());
        fields.put(UserTask.MARK, item.getMark());
        fields.put(UserTask.SOLUTION, item.getAnswer());
        fields.put(UserTask.FEEDBACK, item.getFeedback());
        fields.put(UserTask.SUBMITTED_DATE, item.getSubmittedDate());
        fields.put(UserTask.CHECK_DATE, item.getCheckDate());
        fields.put(UserTask.DELETED, item.isDeleted());
        return fields;
    }


}
