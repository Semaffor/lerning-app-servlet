package by.bsuir.app.service;

import by.bsuir.app.entity.UserTask;

import java.util.List;

public interface UserTaskService {
    List<UserTask> findCourseTasksOnReviewByCouchUsername(String login);
}
