package by.bsuir.app.service.impl;

import by.bsuir.app.dao.*;
import by.bsuir.app.entity.Course;
import by.bsuir.app.entity.Task;
import by.bsuir.app.exception.ServiceException;
import by.bsuir.app.service.Service;
import by.bsuir.app.service.TaskService;

import java.util.List;
import java.util.Optional;

public class TaskServiceImpl extends Service implements TaskService {

    public TaskServiceImpl(DaoHelperFactory daoHelperFactory) {
        super(daoHelperFactory);
    }

    @Override
    public boolean save(Task task) {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            TaskDao dao = helper.createTaskDao();
            boolean result = dao.save(task);
            helper.endTransaction();
            return result;
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<Task> findById(Long taskId) {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            TaskDao dao = helper.createTaskDao();
            Optional<Task> task = dao.getById(taskId);
            helper.endTransaction();
            return task;
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Task> findCourseAvailableTasks(Long courseId) {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            TaskDao dao = helper.createTaskDao();
            List<Task> tasks = dao.findUnconfirmedByUserCourseTasks(courseId);
            helper.endTransaction();
            return tasks;
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
}
