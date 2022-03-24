package by.bsuir.app.service;

import by.bsuir.app.entity.UserTaskDto;
import by.bsuir.app.exception.ServiceException;

import java.util.List;
import java.util.Optional;

/**
 * This interface is concerned with using DAO objects to get User Tasks from the database and performing logical
 * operations with them.
 */
public interface UserTaskService {
    /**
     * Reviews task by couch id, submits mark and gets feedback on user task.
     *
     * @param taskId   - user PK.
     * @param mark     - mark by the task.
     * @param feedback - couch answer on task.
     * @throws ServiceException if a DaoException occurs.
     */
    void reviewTask(Long taskId, int mark, String feedback);

    /**
     * Get user task from db by id of entity row.
     *
     * @param taskId - userTask PK.
     * @return @{code Optional} if exists, not nullable value.
     * @throws ServiceException if a DaoException occurs.
     */
    Optional<UserTaskDto> findUserTaskByTaskId(Long taskId);

    /**
     * Finds list if tasks that can be reviewed by couch, who mentoring course.
     *
     * @param login - couch username.
     * @return list of custom entity UserTaskDto with special fields.
     * @throws ServiceException if a DaoException occurs.
     * @see UserTaskDto
     */
    List<UserTaskDto> findCourseTasksOnReviewByCouchUsername(String login);

    /**
     * Finds tasks, that were confirmed by user on special course using username
     * and course id.
     *
     * @param username - couch username.
     * @param courseId - course PK.
     * @return list of custom entity UserTaskDto with special fields.
     * @throws ServiceException if a DaoException occurs.
     * @see UserTaskDto
     */
    List<UserTaskDto> findConfirmedUserCourseTasks(String username, Long courseId);

    /**
     * Confirms user solution on the task.
     *
     * @param username - couch username.
     * @param taskId   - task PK.
     * @param solution - user answer on the task.
     * @throws ServiceException if a DaoException occurs.
     */
    void confirmTask(String username, Long taskId, String solution);
}
