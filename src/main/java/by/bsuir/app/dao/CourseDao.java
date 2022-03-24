package by.bsuir.app.dao;

import by.bsuir.app.entity.Course;
import by.bsuir.app.entity.Identifiable;

import java.util.List;
import java.util.Optional;

/**
 * One of DAO interface that extends main Dao, contains specific class methods.
 * All method can throw DaoException.
 *
 * @see Course
 */
public interface CourseDao extends Dao<Course> {
    /**
     * Finds number of undeleted and active rows, need for pagination.
     *
     * @return primitive int value.
     */
    int getNumberOfUndeletedAndActiveRows();

    /**
     * Defines is user subscribed. Searching for the row in database.
     *
     * @param courseId - primary key of the course.
     * @param username - name of the user, whose subscription we are searching for.
     * @return primitive int value.
     */
    boolean isUserSubscribed(String username, Long courseId);

    /**
     * Searching for a couch course.
     *
     * @param login - username of the user.
     * @return optional value.
     */
    Optional<Course> findCourseByCouchUsername(String login);

    /**
     * Finds courses with limit, need for pagination.
     *
     * @param currentPage    - current page, with user has chosen.
     * @param recordsPerPage - count of cards, which will be display on the page.
     * @return list of courses in specific range.
     */
    List<Course> getCourses(int currentPage, int recordsPerPage);

    /**
     * Finds user subscriptions.
     *
     * @param username - username of the user.
     * @return list of user subscribed courses.
     */
    List<Course> findSubscriptionsByUsername(String username);

}
