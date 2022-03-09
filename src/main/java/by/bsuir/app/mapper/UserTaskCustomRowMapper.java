package by.bsuir.app.mapper;

import by.bsuir.app.entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class UserTaskCustomRowMapper implements RowMapper<UserTaskDTO> {
    @Override
    public UserTaskDTO map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(BaseEntity.ID);
        String title = resultSet.getString(Task.TITLE);
        String studentUsername = resultSet.getString(User.NAME);
        int mark = resultSet.getInt(UserTask.MARK);
        Date submittedDate = resultSet.getDate(UserTask.SUBMITTED_DATE);
        boolean isDeleted = resultSet.getBoolean(UserCourse.DELETED);

        return new UserTaskDTO(id, title, studentUsername,
                mark, submittedDate, isDeleted);
    }
}
