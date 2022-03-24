package by.bsuir.app.service;

import by.bsuir.app.entity.Course;
import by.bsuir.app.exception.ServiceException;

import java.util.List;
import java.util.Optional;

/**
 * This interface is concerned with using DAO objects to get Courses from the database and performing logical
 * operations with them.
 */
public interface CourseService {

    /**
     * Change value in field `is_active` to the opposite.
     *
     * @param courseId - course PK in db.
     * @throws ServiceException if a DaoException occurs.
     **/
    void changeIsActiveStatus(Long courseId);

    /**
     * Change value in field `is_deleted` to the opposite.
     *
     * @param courseId - course PK in db.
     * @throws ServiceException if a DaoException occurs.
     **/
    void changeIsDeleteStatus(Long courseId);

    /**
     * Checks user subscription on the course.
     *
     * @param courseId - course PK in db.
     * @return true, if user has subscription.
     * @throws ServiceException if a DaoException occurs.
     **/
    boolean isUserSubscribedByUsername(String username, Long courseId);

    /**
     * Saves specified object in repository.
     *
     * @param course - object to be saved.
     * @return true, if entity has been saved.
     * @throws ServiceException if a DaoException occurs.
     */
    boolean save(Course course);

    /**
     * Counts rows in table with courses.
     *
     * @return total number of courses.
     * @throws ServiceException if a DaoException occurs
     */
    int getNumberOfUndeletedAndActiveRows();

    /**
     * Finds course by it id.
     *
     * @param id - course PK in database.
     * @return Entrant record of this User.
     * @throws ServiceException if a DaoException occurs.
     */
    Optional<Course> getCourse(Long id) throws ServiceException;

    /**
     * Finds all courses in special range.
     *
     * @param currentPage    - page, where user is.
     * @param recordsPerPage - count of courses on one page.
     * @return list of courses.
     * @throws ServiceException if a DaoException occurs.
     */
    List<Course> getCourses(int currentPage, int recordsPerPage);

    /**
     * Finds all courses.
     *
     * @return list of courses.
     * @throws ServiceException if a DaoException occurs.
     */
    List<Course> getAllCourses() throws ServiceException;

    /**
     * Searching for a couch course.
     *
     * @param login - username of the couch.
     * @return {@code Optional} if exists, not nullable.
     * @throws ServiceException if a DaoException occurs.
     */
    Optional<Course> findCourseByCouchUsername(String login);

    /**
     * Finds user subscriptions.
     *
     * @param username - username of the user.
     * @return list of user subscribed courses.
     * @throws ServiceException if a DaoException occurs.
     */
    List<Course> findSubscriptionsByUsername(String username);
}
