package by.bsuir.app.dao.impl;

import by.bsuir.app.dao.AbstractDao;
import by.bsuir.app.dao.CourseDao;
import by.bsuir.app.entity.Course;
import by.bsuir.app.mapper.CourseRowMapper;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.Map;

public class CourseDaoImpl extends AbstractDao<Course> implements CourseDao {

    public CourseDaoImpl(Connection connection) {
        super(connection, new CourseRowMapper(), Course.TABLE);
    }

    @Override
    protected Map<String, Object> getFields(Course item) {
        Map<String, Object> fields = new LinkedHashMap<>();
        fields.put(Course.TITLE, item.getTitle());
        fields.put(Course.DESCRIPTION, item.getDescription());
        fields.put(Course.DURATION, item.getDuration());
        fields.put(Course.TECHNOLOGY, item.getTechnology());
        fields.put(Course.COURSE_FORMAT, item.getCourseFormat());
        fields.put(Course.CURRENT_PUPILS_QUANTITY, item.getCurrentPupilsQuantity());
        fields.put(Course.MAX_PUPILS_QUANTITY, item.getMaxPupilsQuantity());
        fields.put(Course.ACTIVE, item.isActive());
        fields.put(Course.DELETED, item.isDeleted());

        return fields;
    }
}
