package by.bsuir.app.dao;

import by.bsuir.app.entity.Task;

import java.util.List;

public interface TaskDao extends Dao<Task>{
    List<Task> findUnconfirmedByUserCourseTasks(Long courseId);
}
