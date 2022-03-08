package by.bsuir.app.service;

import by.bsuir.app.entity.UserCourse;

import java.util.Optional;

public interface UserCourseService {
    boolean changeSubscription(UserCourse subscription);
    Optional<UserCourse> findSubscription(Long userId, Long courseId);
}