package by.bsuir.app.dao;

import by.bsuir.app.entity.Task;

import java.util.List;

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
}
