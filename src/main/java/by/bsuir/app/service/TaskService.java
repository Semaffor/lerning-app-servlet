package by.bsuir.app.service;

import by.bsuir.app.entity.Task;
import by.bsuir.app.exception.ServiceException;

import java.util.List;
import java.util.Optional;

/**
 * This interface is concerned with using DAO objects to get Tasks from the database and performing logical operations
 * with them.
 */
public interface TaskService {
    /**
     * Saves specified object in repository.
     *
     * @param task - object to be saved.
     * @return true, if entity has been saved.
     * @throws ServiceException if a DaoException occurs.
     */
    boolean save(Task task);

    /**
     * Finds task by id in database.
     *
     * @param taskId - task PK in db.
     * @return {@code Optional} if exists, not nullable value.
     * @throws ServiceException if a DaoException occurs.
     */
    Optional<Task> findById(Long taskId);

    /**
     * Finds tasks, which available for current course using course id.
     *
     * @param courseId - course PK in db.
     * @return list of available tasks.
     * @throws ServiceException if a DaoException occurs.
     */
    List<Task> findCourseAvailableTasks(Long courseId);

    /**
     * Finds task by it title.
     *
     * @param title - title of the task.
     * @return {@code Optional} if exists, not nullable value.
     * @throws ServiceException if a DaoException occurs.
     */
    Optional<Task> findByTitle(String title);
}
