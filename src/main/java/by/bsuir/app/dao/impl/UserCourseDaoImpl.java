package by.bsuir.app.dao.impl;

import by.bsuir.app.dao.AbstractDao;
import by.bsuir.app.dao.UserCourseDao;
import by.bsuir.app.entity.UserCourse;
import by.bsuir.app.mapper.UserCourseRowMapper;

import java.sql.Connection;
import java.util.*;

public class UserCourseDaoImpl extends AbstractDao<UserCourse> implements UserCourseDao {

    public UserCourseDaoImpl(Connection connection) {
        super(connection, new UserCourseRowMapper(), UserCourse.TABLE);
    }

    @Override
    public boolean subscribe(UserCourse subscription) {
        return save(subscription);
    }

    @Override
    public Optional<UserCourse> findByCompositeKey(Long userId, Long courseId) {
        Set<String> fields = new LinkedHashSet<>();
        fields.add(UserCourse.USER_ID);
        fields.add(UserCourse.COURSE_ID);
        String query = generateSelectQuery(fields);
        return executeForSingleResultString(query, userId, courseId);
    }

    @Override
    protected Map<String, Object> getFields(UserCourse item) {
        Map<String, Object> fields = new LinkedHashMap<>();
//        fields.put(UserCourse.ID, item.getId());
        fields.put(UserCourse.USER_ID, item.getUserId());
        fields.put(UserCourse.COURSE_ID, item.getCourseId());
        fields.put(UserCourse.DELETED, item.isDeleted());
        return fields;
    }
}
