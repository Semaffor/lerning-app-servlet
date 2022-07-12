package by.bsuir.app.dao.impl;

import by.bsuir.app.dao.AbstractDao;
import by.bsuir.app.dao.TaskDao;
import by.bsuir.app.entity.BaseEntity;
import by.bsuir.app.entity.Course;
import by.bsuir.app.entity.Task;
import by.bsuir.app.entity.UserTask;
import by.bsuir.app.mapper.TaskRowMapper;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TaskDaoImpl extends AbstractDao<Task> implements TaskDao {
    private final static String SQL_TASK_TABLE_ALIAS = "t";
    private final static String SQL_FIND_COURSE_TASKS_NOT_CONFIRMED_BY_USER = "select %s from %s t " +
            "join %s c on t.course_id = c.id " +
            "left join %s ut on ut.task_id = t.id " +
            "where solution IS NULL and c.id = ?;";

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

    @Override
    public List<Task> findUnconfirmedByUserCourseTasks(Long courseId) {
        String id = BaseEntity.ID;
        return executeQuery(String.format(SQL_FIND_COURSE_TASKS_NOT_CONFIRMED_BY_USER,
                generateAliases(SQL_TASK_TABLE_ALIAS, id, Task.TITLE, Task.DESCRIPTION, Task.DEADLINE, Task.DELETED),
                Task.TABLE, Course.TABLE, UserTask.TABLE), courseId);
    }

    @Override
    public Optional<Task> findByTitle(String title) {
        return executeForSingleResultString(String.format(SQL_FIND_WHERE, Task.TABLE, Task.TITLE), title);
    }
}
