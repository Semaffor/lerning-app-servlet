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

    private static final String SQL_SELECT_LIMIT = "SELECT * FROM %s WHERE %s = %s and %s = %s LIMIT ?, ?;";
    private static final String SQL_SELECT_COURSE_BY_COUCH_USERNAME = "SELECT * FROM %s c " +
            "JOIN %s u on c.couch_id = u.id WHERE u.%s = ?";
    private final static String SQL_GET_ACTIVE_AND_UNDELETED_ROW_COUNT = "SELECT COUNT(id) as count FROM %s " +
            "where is_deleted = false and is_active = true;";
    private final static String SQL_IS_USER_SUBSCRIBED = "Select count(*) as count from %s uc " +
            "join %s c on uc.course_id = c.id " +
            "join %s u on u.id = uc.user_id " +
            "where u.username = ? and c.id = ? and uc.is_deleted =?;";
    private static final String SQL_FIND_USER_SUBSCRIPTIONS = "select * from %s as c\n" +
            "join %s uc on c.id = uc.course_id\n" +
            "join %s u on u.id = uc.user_id\n" +
            "where username = ?;";

    public CourseDaoImpl(Connection connection) {
        super(connection, new CourseRowMapper(), Course.TABLE);
    }

    @Override
    public List<Course> getCourses(int currentPage, int recordsPerPage) {
        int start = currentPage * recordsPerPage - recordsPerPage;
        return executeQuery(String.format(SQL_SELECT_LIMIT, Course.TABLE,
                Course.ACTIVE, "true",
                Course.DELETED, "false"),
                start, recordsPerPage);
    }

    @Override
    public int getNumberOfUndeletedAndActiveRows() {
        return executeForSingleResultInt(String.format(SQL_GET_ACTIVE_AND_UNDELETED_ROW_COUNT, Course.TABLE));
    }

    @Override
    public boolean isUserSubscribed(String username, Long courseId) {
        int result = executeForSingleResultInt(String.format(SQL_IS_USER_SUBSCRIBED, UserCourse.TABLE, Course.TABLE,
                User.TABLE), username, courseId, false);
        return result == 1;
    }

    @Override
    public Optional<Course> findCourseByCouchUsername(String login) {
        return executeForSingleResultString(String.format(SQL_SELECT_COURSE_BY_COUCH_USERNAME,
                Course.TABLE, User.TABLE, User.NAME), login);
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
    public List<Course> findSubscriptionsByUsername(String username) {
        return executeQuery(String.format(SQL_FIND_USER_SUBSCRIPTIONS, Course.TABLE, UserCourse.TABLE, User.TABLE),
                username);
    }

}
