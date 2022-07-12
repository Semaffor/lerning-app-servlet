package by.bsuir.app.service.impl;

import by.bsuir.app.dao.CourseDao;
import by.bsuir.app.dao.DaoHelper;
import by.bsuir.app.dao.DaoHelperFactory;
import by.bsuir.app.entity.Course;
import by.bsuir.app.service.CourseService;
import by.bsuir.app.service.TaskService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.Assert.*;

public class CourseServiceImplTest {

    private final DaoHelperFactory daoHelperFactory = Mockito.mock(DaoHelperFactory.class);
    private final DaoHelper daoHelper = Mockito.mock(DaoHelper.class);
    private final CourseService courseService = new CourseServiceImpl(daoHelperFactory);
    private CourseDao courseDao;

    @Before
    public void init() {
        Mockito.when(daoHelperFactory.create()).thenReturn(daoHelper);
        Mockito.doNothing().when(daoHelper).startTransaction();
        courseDao = Mockito.mock(CourseDao.class);
        Mockito.when(daoHelper.createCourseDao()).thenReturn(courseDao);
    }

    @Test
    public void testChangeIsActiveStatusShouldChangeStatusWhenCourseDisabledAndExists() {
        Course course = Course.getBuilder()
                .setId(10L)
                .build();

        Mockito.when(courseDao.getById(Mockito.anyLong())).thenReturn(Optional.of(course));
        Mockito.when(courseDao.save(Mockito.any())).thenReturn(true);
        Mockito.doNothing().when(daoHelper).endTransaction();

        courseService.changeIsActiveStatus(10L);
        Mockito.verify(courseDao, Mockito.times(1)).save(course);
    }

    @Test
    public void testChangeIsActiveStatusShouldChangeStatusWhenCourseNotExists() {
        Mockito.when(courseDao.getById(Mockito.anyLong())).thenReturn(Optional.empty());
        Mockito.doNothing().when(daoHelper).endTransaction();

        courseService.changeIsActiveStatus(10L);
        Mockito.verify(courseDao, Mockito.times(0)).save(Mockito.any());
    }

    @Test
    public void testChangeDeletedStatusShouldNotChangeStatusWhenCourseNotExists() {
        Mockito.when(courseDao.getById(Mockito.anyLong())).thenReturn(Optional.empty());
        Mockito.doNothing().when(daoHelper).endTransaction();

        courseService.changeIsDeleteStatus(10L);
        Mockito.verify(courseDao, Mockito.times(0)).save(Mockito.any());
    }

    @Test
    public void testChangeDeletedStatusShouldChangeStatusWhenCourseExists() {
        Course course = Mockito.mock(Course.class);
        Mockito.when(courseDao.getById(Mockito.anyLong())).thenReturn(Optional.of(course));
        Mockito.doNothing().when(daoHelper).endTransaction();

        courseService.changeIsDeleteStatus(10L);
        Mockito.verify(course, Mockito.times(1)).setDeleted(Mockito.anyBoolean());
        Mockito.verify(course, Mockito.times(1)).setActive(false);
    }
}