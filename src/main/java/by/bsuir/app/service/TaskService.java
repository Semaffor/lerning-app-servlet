package by.bsuir.app.service;

import by.bsuir.app.entity.Task;

import java.util.Optional;

public interface TaskService {
    boolean save(Task task);

    Optional<Task> findById(Long taskId);
}
