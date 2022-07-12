package by.bsuir.app.mapper;

import by.bsuir.app.entity.BaseEntity;
import by.bsuir.app.entity.User;
import by.bsuir.app.entity.UserCourse;
import by.bsuir.app.entity.enums.Status;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class UserCourseRowMapper implements RowMapper<UserCourse> {
    @Override
    public UserCourse map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(BaseEntity.ID);
        Long userId = resultSet.getLong(User.USER_ID);
        Long courseId = resultSet.getLong(User.COURSE_ID);
        Date date = resultSet.getDate(UserCourse.START_DATE);
        boolean isDeleted = resultSet.getBoolean(UserCourse.DELETED);

        return UserCourse.getBuilder()
                .setId(id)
                .setUserId(userId)
                .setCourseId(courseId)
                .setStartDate(date)
                .setDeleted(isDeleted)
                .build();
    }
}
