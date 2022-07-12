package by.bsuir.app.mapper;

import by.bsuir.app.entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseTaskRowMapper implements RowMapper<CourseTask>{
    @Override
    public CourseTask map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(BaseEntity.ID);
        Long courseId = resultSet.getLong(CourseTask.COURSE_ID);
        Long taskId = resultSet.getLong(CourseTask.TASK_ID);
        boolean deleted = resultSet.getBoolean(Task.DELETED);

        return new CourseTask(courseId, taskId, deleted);
    }
}
