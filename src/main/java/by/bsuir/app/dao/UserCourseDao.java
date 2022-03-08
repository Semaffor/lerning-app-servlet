package by.bsuir.app.dao;

import by.bsuir.app.entity.UserCourse;

import java.util.Optional;

public interface UserCourseDao {

    boolean subscribe(UserCourse subscription);
    Optional<UserCourse> findByCompositeKey(Long userId, Long courseId);
}
