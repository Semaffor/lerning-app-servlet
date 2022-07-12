package by.bsuir.app.mapper;

import by.bsuir.app.entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

public class UserTaskCustomRowMapper implements RowMapper<UserTaskDto> {
    @Override
    public UserTaskDto map(ResultSet resultSet) throws SQLException, ParseException {
        Long id = resultSet.getLong(BaseEntity.ID);
        String title = resultSet.getString(Task.TITLE);
        String description = resultSet.getString(Task.DESCRIPTION);
        String studentUsername = resultSet.getString(User.NAME);
        String answer = resultSet.getString(UserTask.SOLUTION);
        String feedback = resultSet.getString(UserTask.FEEDBACK);
        int mark = resultSet.getInt(UserTask.MARK);

        Date deadline = resultSet.getDate(UserTask.DEADLINE);
        Date checkDate = resultSet.getDate(UserTask.CHECK_DATE);
        Date submittedDate =  resultSet.getDate(UserTask.SUBMITTED_DATE);

        return UserTaskDto.getBuilder()
                .setId(id)
                .setTitle(title)
                .setDescription(description)
                .setStudentUsername(studentUsername)
                .setAnswer(answer)
                .setFeedback(feedback)
                .setMark(mark)
                .setDeadline(deadline)
                .setCheckDate(checkDate)
                .setSubmittedDate(submittedDate)
                .build();
    }
}
