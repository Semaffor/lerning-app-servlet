package by.bsuir.app.dao.impl;

import by.bsuir.app.dao.AbstractDao;
import by.bsuir.app.dao.UserTaskCustomDao;
import by.bsuir.app.entity.*;
import by.bsuir.app.mapper.UserTaskCustomRowMapper;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserTaskCustomDaoImpl extends AbstractDao<UserTaskDTO> implements UserTaskCustomDao {
    private static final String COMMON_JOINS =
            "JOIN %s t on t.%s = ut.%s " +
            "JOIN %s u on u.%s = ut.%s ";
    private static final String SQL_FIND_TASKS_BY_COUCH_USERNAME = "SELECT * from %s ut " + COMMON_JOINS +
            "JOIN %s c on c.%s = t.%s " +
            "where c.%s = ?;";
    private static final String SQL_USER_TASK_BY_ID = "SELECT * FROM %s ut " + COMMON_JOINS + " WHERE ut.%s = ?;";
    private static final String SQL_FIND_CONFIRMED_COURSE_TASKS = "select * from %s ut\n" + COMMON_JOINS +
            "where u.%s = ? and t.%s = ?;";

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
                UserTask.TABLE,
                Task.TABLE, id, BaseEntity.TASK_ID,
                User.TABLE, id, BaseEntity.USER_ID,
                Course.TABLE, id, BaseEntity.COURSE_ID,
                Course.COUCH_ID), couchId);
    }

    @Override
    public Optional<UserTaskDTO> findUserTaskByTaskId(Long userTaskId) {
        String id = BaseEntity.ID;
        return executeForSingleResultString(String.format(SQL_USER_TASK_BY_ID,
                UserTask.TABLE,
                Task.TABLE, id, BaseEntity.TASK_ID,
                User.TABLE, id, BaseEntity.USER_ID,
                id), userTaskId);
    }

    @Override
    public List<UserTaskDTO> findConfirmedUserCourseTasks(String username, Long courseId) {
        String id = BaseEntity.ID;
        return executeQuery(String.format(SQL_FIND_CONFIRMED_COURSE_TASKS,
                UserTask.TABLE,
                Task.TABLE, id, BaseEntity.TASK_ID,
                User.TABLE, id, BaseEntity.USER_ID,
                User.NAME, BaseEntity.COURSE_ID), username, courseId);
    }
}
