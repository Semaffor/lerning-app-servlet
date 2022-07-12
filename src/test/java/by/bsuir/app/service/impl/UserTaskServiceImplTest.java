package by.bsuir.app.service.impl;

import by.bsuir.app.dao.*;
import by.bsuir.app.dao.impl.UserTaskDaoImpl;
import by.bsuir.app.entity.User;
import by.bsuir.app.entity.UserTask;
import by.bsuir.app.entity.UserTaskDto;
import by.bsuir.app.service.UserTaskService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class UserTaskServiceImplTest {

    private final DaoHelperFactory daoHelperFactory = Mockito.mock(DaoHelperFactory.class);
    private final DaoHelper daoHelper = Mockito.mock(DaoHelper.class);
    private final UserTaskService userTaskService = new UserTaskServiceImpl(daoHelperFactory);
    private UserDao userDao;

    @Before
    public void init() {
        Mockito.when(daoHelperFactory.create()).thenReturn(daoHelper);
        Mockito.doNothing().when(daoHelper).startTransaction();
        userDao = Mockito.mock(UserDao.class);
        Mockito.when(daoHelper.createUserDao()).thenReturn(userDao);
        Mockito.when(userDao.save(Mockito.any())).thenReturn(true);
        Mockito.doNothing().when(daoHelper).endTransaction();
    }

    @Test
    public void testFindCourseTasksOnReviewByCouchUsernameShouldReturnTasksWhenTheyExist() {
        User user = Mockito.mock(User.class);
        Mockito.when(userDao.findByUsername(Mockito.anyString())).thenReturn(Optional.of(user));
        UserTaskCustomDao userTaskCustomDao = Mockito.mock(UserTaskCustomDao.class);
        Mockito.when(daoHelper.createUserTaskCustomDao()).thenReturn(userTaskCustomDao);
        Mockito.when(userTaskCustomDao.findCourseTasksOnReviewByCouchId(Mockito.anyLong())).thenReturn(new ArrayList<>());

        userTaskService.findCourseTasksOnReviewByCouchUsername("couch");
        Mockito.verify(userTaskCustomDao, Mockito.times(1))
                .findCourseTasksOnReviewByCouchId(Mockito.anyLong());
    }

    @Test
    public void testFindCourseTasksOnReviewByCouchUsernameShouldReturnEmptyListWhenTheyNotExist() {
        Mockito.when(userDao.findByUsername(Mockito.anyString())).thenReturn(Optional.empty());
        List<UserTaskDto> courseTasks = userTaskService.findCourseTasksOnReviewByCouchUsername("couch");
        Assert.assertTrue(courseTasks.isEmpty());
    }

    @Test
    public void testReviewTaskShouldReviewTaskWhenUserTaskExists() {
        UserTask userTask = Mockito.mock(UserTask.class);
        UserTaskDao userTaskDao = Mockito.mock(UserTaskDaoImpl.class);
        Mockito.when(daoHelper.createUserTaskDao()).thenReturn(userTaskDao);
        Mockito.when(userTaskDao.getById(Mockito.anyLong())).thenReturn(Optional.of(userTask));

        int mark = 10;
        String feedback = "Feedback";
        userTaskService.reviewTask(10L, mark, feedback);
        Mockito.verify(userTask, Mockito.times(1)).setMark(mark);
        Mockito.verify(userTask, Mockito.times(1)).setFeedback(feedback);
        Mockito.verify(userTask, Mockito.times(1)).setCheckDate(Mockito.any());
        Mockito.verify(userTaskDao, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    public void testReviewTaskShouldNotReviewTaskWhenUserTaskNotExists() {
        UserTask userTask = Mockito.mock(UserTask.class);
        UserTaskDao userTaskDao = Mockito.mock(UserTaskDaoImpl.class);
        Mockito.when(daoHelper.createUserTaskDao()).thenReturn(userTaskDao);
        Mockito.when(userTaskDao.getById(Mockito.anyLong())).thenReturn(Optional.empty());

        Mockito.verify(userTask, Mockito.times(0)).setCheckDate(Mockito.any());
        Mockito.verify(userTaskDao, Mockito.times(0)).save(Mockito.any());
    }
}