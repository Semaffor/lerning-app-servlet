package by.bsuir.app.service.impl;

import by.bsuir.app.dao.*;
import by.bsuir.app.entity.User;
import by.bsuir.app.entity.UserTask;
import by.bsuir.app.entity.UserTaskDto;
import by.bsuir.app.exception.DaoException;
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
    public List<UserTaskDto> findCourseTasksOnReviewByCouchUsername(String login) {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            UserDao userDao = helper.createUserDao();
            Optional<User> couch = userDao.findByUsername(login);
            List<UserTaskDto> tasks = new ArrayList<>();
            if (couch.isPresent()) {
                Long couchId = couch.get().getId();
                UserTaskCustomDao userTaskDao = helper.createUserTaskCustomDao();
                tasks = userTaskDao.findCourseTasksOnReviewByCouchId(couchId);
            }
            helper.endTransaction();
            return tasks;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<UserTaskDto> findConfirmedUserCourseTasks(String username, Long courseId) {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            UserTaskCustomDao userTaskDao = helper.createUserTaskCustomDao();
            List<UserTaskDto> tasks = userTaskDao.findConfirmedUserCourseTasks(username, courseId);
            helper.endTransaction();
            return tasks;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void confirmTask(String username, Long taskId, String solution) {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            UserDao userDao = helper.createUserDao();
            Optional<User> userOptional = userDao.findByUsername(username);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                UserTaskDao userTaskDao = helper.createUserTaskDao();
                UserTask userTask = UserTask.getBuilder()
                        .setUserId(user.getId())
                        .setTaskId(taskId)
                        .setAnswer(solution)
                        .setSubmittedDate(new Date())
                        .build();
                userTaskDao.save(userTask);
            }
            helper.endTransaction();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void reviewTask(Long taskId, int mark, String feedback) {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            UserTaskDao userTaskDao = helper.createUserTaskDao();
            Optional<UserTask> userTaskOptional = userTaskDao.getById(taskId);
            if (userTaskOptional.isPresent()) {
                UserTask userTask = userTaskOptional.get();
                userTask.setMark(mark);
                userTask.setFeedback(feedback);
                userTask.setCheckDate(new Date());
                userTaskDao.save(userTask);
            }
            helper.endTransaction();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<UserTaskDto> findUserTaskByTaskId(Long userTaskId) {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            UserTaskCustomDao dao = helper.createUserTaskCustomDao();
            Optional<UserTaskDto> task = dao.findUserTaskByTaskId(userTaskId);
            helper.endTransaction();
            return task;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
