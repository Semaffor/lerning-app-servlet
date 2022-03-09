package by.bsuir.app.dao.impl;

import by.bsuir.app.dao.AbstractDao;
import by.bsuir.app.dao.UserTaskCustomDao;
import by.bsuir.app.entity.*;
import by.bsuir.app.mapper.UserTaskCustomRowMapper;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class UserTaskCustomDaoImpl extends AbstractDao<UserTaskDTO> implements UserTaskCustomDao {
    private static final String SQL_FIND_TASKS_BY_COUCH_USERNAME = "SELECT ut.%s, t.%s, u.%s, ut.%s, ut.%s, ut" +
            ".%s" +
            " " +
            "from %s ut\n" +
            "JOIN %s t on t.%s = ut.%s\n" +
            "JOIN %s c on t.%s = c.%s\n" +
            "JOIN %s u on u.%s = ut.%s\n" +
            "where c.%s = ?;";

    public UserTaskCustomDaoImpl(Connection connection) {
        super(connection, new UserTaskCustomRowMapper(), User.TABLE);
    }

    @Override
    protected Map<String, Object> getFields(UserTaskDTO item) {
        throw new UnsupportedOperationException();      //Is it correct if I don't need this one method?
    }

    @Override
    public List<UserTaskDTO> findCourseTasksOnReviewByCouchId(Long couchId) {
        String id = BaseEntity.ID;
        return executeQuery(String.format(SQL_FIND_TASKS_BY_COUCH_USERNAME,
                id, Task.TITLE, User.NAME, UserTask.MARK, UserTask.SUBMITTED_DATE, BaseEntity.DELETED,
                UserTask.TABLE,
                Task.TABLE, id, BaseEntity.TASK_ID,
                Course.TABLE, Course.COURSE_ID, id,
                User.TABLE, id, BaseEntity.USER_ID,
                Course.COUCH_ID), couchId);
    }
}
