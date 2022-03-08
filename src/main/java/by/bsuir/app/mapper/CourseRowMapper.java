package by.bsuir.app.mapper;

import by.bsuir.app.entity.Course;
import by.bsuir.app.entity.enums.CourseFormat;
import by.bsuir.app.entity.enums.TechnologyType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseRowMapper implements RowMapper<Course> {
    @Override
    public Course map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(Course.ID);
        Long couchId = resultSet.getLong(Course.COUCH_ID);
        String title = resultSet.getString(Course.TITLE);
        String description = resultSet.getString(Course.DESCRIPTION);
        int technologyCode = resultSet.getInt(Course.TECHNOLOGY);
        TechnologyType technology = TechnologyType.getTechnologyTypeFromCode(technologyCode);
        int duration = resultSet.getInt(Course.DURATION);
        int courseFormatCode = resultSet.getInt(Course.COURSE_FORMAT);
        CourseFormat courseFormat = CourseFormat.getFormatFromCode(courseFormatCode);
        int currentPupilsQuantity = resultSet.getInt(Course.CURRENT_PUPILS_QUANTITY);
        int maxPupilsQuantity = resultSet.getInt(Course.MAX_PUPILS_QUANTITY);
        boolean isActive = resultSet.getBoolean(Course.ACTIVE);
        boolean isDeleted = resultSet.getBoolean(Course.DELETED);

        return Course.getBuilder()
                .setId(id)
                .setTitle(title)
                .setDescription(description)
                .setDuration(duration)
                .setTechnology(technology)
                .setCourseFormat(courseFormat)
                .setCurrentPupilsQuantity(currentPupilsQuantity)
                .setMaxPupilsQuantity(maxPupilsQuantity)
                .setActive(isActive)
                .setCouchId(couchId)
                .setDeleted(isDeleted)
                .build();
    }
}
