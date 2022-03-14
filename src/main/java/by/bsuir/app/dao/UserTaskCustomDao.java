package by.bsuir.app.dao;

import by.bsuir.app.entity.UserTaskDTO;

import java.util.List;
import java.util.Optional;

public interface UserTaskCustomDao extends Dao<UserTaskDTO> {
    List<UserTaskDTO> findCourseTasksOnReviewByCouchId(Long couchId);
    Optional<UserTaskDTO> findUserTaskByTaskId(Long userTaskId);

    List<UserTaskDTO> findConfirmedUserCourseTasks(String username, Long courseId);
}
