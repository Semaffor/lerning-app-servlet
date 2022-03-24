package by.bsuir.app.dao.impl;

import by.bsuir.app.dao.AbstractDao;
import by.bsuir.app.dao.UserTaskCustomDao;
import by.bsuir.app.entity.*;
import by.bsuir.app.mapper.UserTaskCustomRowMapper;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserTaskCustomDaoImpl extends AbstractDao<UserTaskDto> implements UserTaskCustomDao {
    private static final String COMMON_JOINS =
            "JOIN %s t on t.id = ut.task_id " +
                    "JOIN %s u on u.id = ut.user_id ";
    private static final String SQL_FIND_TASKS_BY_COUCH_USERNAME = "SELECT * from %s ut " + COMMON_JOINS +
            "JOIN %s c on c.id = t.course_id " +
            "where c.couch_id = ?;";
    private static final String SQL_USER_TASK_BY_ID = "SELECT * FROM %s ut " + COMMON_JOINS + " WHERE ut.id = ?;";
    private static final String SQL_FIND_CONFIRMED_COURSE_TASKS = "select * from %s ut\n" + COMMON_JOINS +
            "where u.%s = ? and t.%s = ?;";

    public UserTaskCustomDaoImpl(Connection connection) {
        super(connection, new UserTaskCustomRowMapper(), User.TABLE);
    }

    @Override
    protected Map<String, Object> getFields(UserTaskDto item) {
        throw new UnsupportedOperationException("Don't need this one method for DTO class.");
    }

    @Override
    public List<UserTaskDto> findCourseTasksOnReviewByCouchId(Long couchId) {
        return executeQuery(String.format(SQL_FIND_TASKS_BY_COUCH_USERNAME,
                UserTask.TABLE, Task.TABLE, User.TABLE, Course.TABLE), couchId);
    }

    @Override
    public Optional<UserTaskDto> findUserTaskByTaskId(Long userTaskId) {
        return executeForSingleResultString(String.format(SQL_USER_TASK_BY_ID,
                UserTask.TABLE, Task.TABLE, User.TABLE), userTaskId);
    }

    @Override
    public List<UserTaskDto> findConfirmedUserCourseTasks(String username, Long courseId) {
        return executeQuery(String.format(SQL_FIND_CONFIRMED_COURSE_TASKS,
                UserTask.TABLE, Task.TABLE, User.TABLE, User.NAME, BaseEntity.COURSE_ID), username, courseId);
    }
}
