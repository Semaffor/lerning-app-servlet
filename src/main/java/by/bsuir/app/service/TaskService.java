package by.bsuir.app.service;

import by.bsuir.app.entity.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    boolean save(Task task);

    Optional<Task> findById(Long taskId);
    List<Task> findCourseAvailableTasks(Long courseId);

    Optional<Task> findByTitle(String title);
}
