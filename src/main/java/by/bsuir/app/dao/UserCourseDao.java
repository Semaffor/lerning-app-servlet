package by.bsuir.app.dao;

import by.bsuir.app.entity.UserCourse;

import java.util.Optional;

/**
 * One of DAO interface that extends main Dao, contains specific class methods.
 *
 * @see UserCourse
 */
public interface UserCourseDao {
    /**
     * Subscribe the user on the course.
     *
     * @param subscription - entity, which contains composite key to subscribe the user.
     * @return false if user can't subscribe.
     */
    boolean subscribe(UserCourse subscription);

    /**
     * Finds the row in database with subscription data.
     *
     * @param userId   - user PK in database.
     * @param courseId - course PK in db.
     * @return if row exists, not nullable optional value.
     */
    Optional<UserCourse> findByCompositeKey(Long userId, Long courseId);
}
