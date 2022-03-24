package by.bsuir.app.dao;

import by.bsuir.app.entity.UserTaskDto;

import java.util.List;
import java.util.Optional;

/**
 * One of DAO interface that extends main Dao, contains specific class methods.
 *
 * @see UserTaskDto
 */
public interface UserTaskCustomDao extends Dao<UserTaskDto> {
    /**
     * Finds tasks that couch hasn't reviewed before.
     *
     * @param couchId - equals user PK.
     * @return list of available to review courses.
     */
    List<UserTaskDto> findCourseTasksOnReviewByCouchId(Long couchId);

    /**
     * Finds task special user task in many-to-many table.
     *
     * @param userTaskId - row PK.
     * @return if exists, not nullable optional value.
     */
    Optional<UserTaskDto> findUserTaskByTaskId(Long userTaskId);

    /**
     * Finds course tasks that user has confirmed before.
     *
     * @param username - user username.
     * @param courseId - course PK.
     * @return list of custom entities.
     */
    List<UserTaskDto> findConfirmedUserCourseTasks(String username, Long courseId);
}
