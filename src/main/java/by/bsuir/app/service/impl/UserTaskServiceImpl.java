package by.bsuir.app.service.impl;

import by.bsuir.app.dao.DaoHelper;
import by.bsuir.app.dao.DaoHelperFactory;
import by.bsuir.app.dao.UserDao;
import by.bsuir.app.dao.UserTaskDao;
import by.bsuir.app.entity.User;
import by.bsuir.app.entity.UserTask;
import by.bsuir.app.exception.ServiceException;
import by.bsuir.app.service.Service;
import by.bsuir.app.service.UserTaskService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserTaskServiceImpl extends Service implements UserTaskService {

    public UserTaskServiceImpl(DaoHelperFactory daoHelperFactory) {
        super(daoHelperFactory);
    }

    @Override
    public List<UserTask> findCourseTasksOnReviewByCouchUsername(String login) {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            UserDao userDao = helper.createUserDao();
            Optional<User> couch = userDao.findByUsername(login);
            List<UserTask> tasks = new ArrayList<>();
            if (couch.isPresent()) {
                Long couchId = couch.get().getId();
                UserTaskDao userTaskDao = helper.createUserTaskDao();           //Can I do in this way?
                tasks = userTaskDao.findCourseTasksOnReviewByCouchId(couchId);
            }
            helper.endTransaction();
            return tasks;
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
}
