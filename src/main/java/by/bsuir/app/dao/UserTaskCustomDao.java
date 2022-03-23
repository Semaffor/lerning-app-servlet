package by.bsuir.app.dao;

import by.bsuir.app.entity.UserTaskDTO;
import by.bsuir.app.exception.DaoException;

import java.util.List;
import java.util.Optional;

/**
 * One of DAO interface that extends main Dao, contains specific class methods.
 *
 * @see UserTaskDTO
 */
public interface UserTaskCustomDao extends Dao<UserTaskDTO> {
    /**
     * Finds tasks that couch hasn't reviewed before.
     *
     * @param couchId - equals user PK.
     * @return list of available to review courses.
     */
    List<UserTaskDTO> findCourseTasksOnReviewByCouchId(Long couchId);

    /**
     * Finds task special user task in many-to-many table.
     *
     * @param userTaskId - row PK.
     * @return if exists, not nullable optional value.
     */
    Optional<UserTaskDTO> findUserTaskByTaskId(Long userTaskId);

    /**
     * Finds course tasks that user has confirmed before.
     *
     * @param username - user username.
     * @param courseId - course PK.
     * @return list of custom entities.
     */
    List<UserTaskDTO> findConfirmedUserCourseTasks(String username, Long courseId);
}
