package by.bsuir.app.service;

import by.bsuir.app.entity.UserTask;
import by.bsuir.app.entity.UserTaskDTO;

import java.util.List;

public interface UserTaskService {
    List<UserTaskDTO> findCourseTasksOnReviewByCouchUsername(String login);

    void confirmTask(Long taskId, int mark, String feedback);
}
