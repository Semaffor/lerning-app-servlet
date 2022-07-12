package by.bsuir.app.service;

import by.bsuir.app.entity.UserCourse;

import java.util.List;
import java.util.Optional;

/**
 * This interface is concerned with using DAO objects to get UserCourse from the database and performing logical
 * operations with them.
 */
public interface UserCourseService {

    /**
     * Finds course by id in subscription entity, then increase or decrease
     * number of current subscribed users.
     *
     * @param subscription - {@code UserCourse}.
     * @return true, if count of subscribed users has been changed.
     **/
    boolean changeSubscription(UserCourse subscription);

    /**
     * Finds is user subscribed on this course or not, using composite key.
     *
     * @param userId   - user PK in db.
     * @param courseId - course PK in db.
     * @return {@code Optional} if exists, not nullable.
     */
    Optional<UserCourse> findSubscription(Long userId, Long courseId);
}
