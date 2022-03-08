package by.bsuir.app.dao.impl;

import by.bsuir.app.connection.ProxyConnection;
import by.bsuir.app.dao.AbstractDao;
import by.bsuir.app.dao.UserTaskDao;
import by.bsuir.app.entity.*;
import by.bsuir.app.mapper.UserTaskRowMapper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class UserTaskDaoImpl extends AbstractDao<UserTask> implements UserTaskDao {
    private static final String SQL_FIND_TASKS_BY_COUCH_USERNAME = "SELECT ut.%s, u.%s, ut.%s, ut.%s, ut.%s from %s ut\n" +
            "JOIN %s t on t.%s = ut.%s\n" +
            "JOIN %s c on t.%s = c.%s\n" +
            "JOIN %s u on u.%s = ut.%s\n" +
            "where c.%s = ?;";

    public UserTaskDaoImpl(ProxyConnection connection) {
        super(connection, new UserTaskRowMapper(), UserTask.TABLE);
    }

    @Override
    protected Map<String, Object> getFields(UserTask item) {
        Map<String, Object> fields = new LinkedHashMap<>();
        fields.put(UserTask.USER_ID, item.getUserId());
        fields.put(UserTask.TASK_ID, item.getTaskId());
        fields.put(UserTask.MARK, item.getMark());
        fields.put(UserTask.DELETED, item.isDeleted());
        return fields;
    }

    @Override
    public List<UserTask> findCourseTasksOnReviewByCouchId(Long couchId) {
        String id = BaseEntity.ID;
        return executeQuery(String.format(SQL_FIND_TASKS_BY_COUCH_USERNAME,
                id, User.NAME, UserTask.MARK, UserTask.CHECK_DATE, BaseEntity.DELETED,
                UserTask.TABLE,
                Task.TABLE, id, BaseEntity.TASK_ID,
                Course.TABLE, Course.COURSE_ID, id,
                User.TABLE, id, BaseEntity.USER_ID,
                Course.COUCH_ID), couchId);
    }
}
