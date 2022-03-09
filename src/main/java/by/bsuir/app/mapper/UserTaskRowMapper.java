package by.bsuir.app.mapper;

import by.bsuir.app.entity.BaseEntity;
import by.bsuir.app.entity.UserCourse;
import by.bsuir.app.entity.UserTask;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class UserTaskRowMapper implements RowMapper<UserTask> {
    @Override
    public UserTask map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(BaseEntity.ID);
        Long userId = resultSet.getLong(UserTask.USER_ID);
        Long taskId = resultSet.getLong(UserTask.TASK_ID);
        int mark = resultSet.getInt(UserTask.MARK);
        String answer = resultSet.getString(UserTask.ANSWER);
        String feedback = resultSet.getString(UserTask.FEEDBACK);
        Date checkDate = resultSet.getDate(UserTask.CHECK_DATE);
        Date submittedDate = resultSet.getDate(UserTask.SUBMITTED_DATE);
        boolean isDeleted = resultSet.getBoolean(UserCourse.DELETED);

        return UserTask.getBuilder()
                .setId(id)
                .setUserId(userId)
                .setTaskId(taskId)
                .setMark(mark)
                .setAnswer(answer)
                .setFeedback(feedback)
                .setCheckDate(checkDate)
                .setSubmittedDate(submittedDate)
                .setDeleted(isDeleted)
                .build();
    }
}
