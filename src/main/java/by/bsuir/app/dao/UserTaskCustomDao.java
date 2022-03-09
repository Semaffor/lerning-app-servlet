package by.bsuir.app.dao;

import by.bsuir.app.entity.UserTaskDTO;

import java.util.List;

public interface UserTaskCustomDao extends Dao<UserTaskDTO> {
    List<UserTaskDTO> findCourseTasksOnReviewByCouchId(Long couchId);
}
