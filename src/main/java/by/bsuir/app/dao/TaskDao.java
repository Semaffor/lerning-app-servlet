package by.bsuir.app.dao;

import by.bsuir.app.entity.Task;

import java.util.List;
import java.util.Optional;

/**
 * One of DAO interface that extends main Dao, contains specific class methods.
 *
 * @see Task
 */
public interface TaskDao extends Dao<Task> {
    /**
     * Finds course tasks which user hasn't confirmed before.
     *
     * @param courseId - primary key of course in database.
     * @return list of tasks which user can do.
     */
    List<Task> findUnconfirmedByUserCourseTasks(Long courseId);

    /**
     * Finds course tasks which has special title.
     *
     * @param title - natural key of task in database.
     * @return {@code Optional} if exists not nullable optional.
     */
    Optional<Task> findByTitle(String title);
}
