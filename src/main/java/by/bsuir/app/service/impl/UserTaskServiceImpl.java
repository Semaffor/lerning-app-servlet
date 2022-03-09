package by.bsuir.app.service.impl;

import by.bsuir.app.dao.*;
import by.bsuir.app.entity.User;
import by.bsuir.app.entity.UserTask;
import by.bsuir.app.entity.UserTaskDTO;
import by.bsuir.app.exception.ServiceException;
import by.bsuir.app.service.Service;
import by.bsuir.app.service.UserTaskService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class UserTaskServiceImpl extends Service implements UserTaskService {

    public UserTaskServiceImpl(DaoHelperFactory daoHelperFactory) {
        super(daoHelperFactory);
    }

    @Override
    public List<UserTaskDTO> findCourseTasksOnReviewByCouchUsername(String login) {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            UserDao userDao = helper.createUserDao();
            Optional<User> couch = userDao.findByUsername(login);
            List<UserTaskDTO> tasks = new ArrayList<>();
            if (couch.isPresent()) {
                Long couchId = couch.get().getId();
                UserTaskCustomDao userTaskDao = helper.createUserTaskCustomDao();           //Can I do in this way?
                tasks = userTaskDao.findCourseTasksOnReviewByCouchId(couchId);
            }
            helper.endTransaction();
            return tasks;
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void confirmTask(Long taskId, int mark, String feedback) {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            UserTaskDao userTaskDao = helper.createUserTaskDao();
            Optional<UserTask> userTaskOptional = userTaskDao.getById(taskId);
            if (userTaskOptional.isPresent()) {
                UserTask userTask = userTaskOptional.get();
                userTask.setMark(mark);
                userTask.setFeedback(feedback);
                userTask.setSubmittedDate(new Date());
            }
            helper.endTransaction();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
}
