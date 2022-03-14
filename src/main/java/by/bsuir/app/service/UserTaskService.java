package by.bsuir.app.service;

import by.bsuir.app.entity.UserTaskDTO;

import java.util.List;
import java.util.Optional;

public interface UserTaskService {
    void reviewTask(Long taskId, int mark, String feedback);

    Optional<UserTaskDTO> findUserTaskByTaskId(Long taskId);

    List<UserTaskDTO> findCourseTasksOnReviewByCouchUsername(String login);

    List<UserTaskDTO> findConfirmedUserCourseTasks(String username, Long courseId);

    void confirmTask(String username, Long taskId, String solution);
}
