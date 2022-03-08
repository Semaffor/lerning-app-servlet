package by.bsuir.app.dao.impl;

import by.bsuir.app.dao.AbstractDao;
import by.bsuir.app.dao.CourseDao;
import by.bsuir.app.entity.BaseEntity;
import by.bsuir.app.entity.Course;
import by.bsuir.app.entity.User;
import by.bsuir.app.entity.UserCourse;
import by.bsuir.app.mapper.CourseRowMapper;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CourseDaoImpl extends AbstractDao<Course> implements CourseDao {

    private static final String SQL_SELECT_LIMIT = "SELECT * FROM %s LIMIT ?, ?;";
    private final static String SQL_GET_ROW_COUNT = "SELECT COUNT(id) as count FROM %s where %s != true;";
    private final static String SQL_IS_USER_SUBSCRIBED = "Select count(*) as count from %s uc " +
            "join %s c on uc.%s = c.%s " +
            "join %s u on u.%s = uc.%s " +
            "where u.%s = ? and c.%s = ? and uc.%s =?;";

    public CourseDaoImpl(Connection connection) {
        super(connection, new CourseRowMapper(), Course.TABLE);
    }

    @Override
    protected Map<String, Object> getFields(Course item) {
        Map<String, Object> fields = new LinkedHashMap<>();
        fields.put(Course.TITLE, item.getTitle());
        fields.put(Course.DESCRIPTION, item.getDescription());
        fields.put(Course.DURATION, item.getDuration());
        fields.put(Course.TECHNOLOGY, item.getTechnology().getCode());
        fields.put(Course.COURSE_FORMAT, item.getCourseFormat().getCode());
        fields.put(Course.CURRENT_PUPILS_QUANTITY, item.getCurrentPupilsQuantity());
        fields.put(Course.MAX_PUPILS_QUANTITY, item.getMaxPupilsQuantity());
        fields.put(Course.ACTIVE, item.isActive());
        fields.put(Course.DELETED, item.isDeleted());

        return fields;
    }

    @Override
    public List<Course> getCourses() {
        return getAll();
    }

    @Override
    public Optional<Course> getCourse(Long id) {
        return getById(id);
    }

    @Override
    public List<Course> getCourses(int currentPage, int recordsPerPage) {
        int start = currentPage * recordsPerPage - recordsPerPage;
        return executeQuery(String.format(SQL_SELECT_LIMIT, Course.TABLE), start, recordsPerPage);
    }

    @Override
    public int getTableUndeletedRowsCount() {
        return executeForSingleResultInt(String.format(SQL_GET_ROW_COUNT, Course.TABLE, Course.DELETED));
    }

    @Override
    public boolean isUserSubscribed(String username, Long courseId) {
        String id = BaseEntity.ID;
        int result = executeForSingleResultInt(String.format(SQL_IS_USER_SUBSCRIBED, UserCourse.TABLE, Course.TABLE,
                BaseEntity.COURSE_ID, id, User.TABLE, id, BaseEntity.USER_ID, User.NAME, id, UserCourse.DELETED),
                username, courseId, false);
        return result == 1;
    }


}
