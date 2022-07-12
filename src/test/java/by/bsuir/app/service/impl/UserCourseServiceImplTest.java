package by.bsuir.app.service.impl;

import by.bsuir.app.dao.CourseDao;
import by.bsuir.app.dao.DaoHelper;
import by.bsuir.app.dao.DaoHelperFactory;
import by.bsuir.app.dao.UserCourseDao;
import by.bsuir.app.dao.impl.CourseDaoImpl;
import by.bsuir.app.dao.impl.UserCourseDaoImpl;
import by.bsuir.app.entity.Course;
import by.bsuir.app.entity.UserCourse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class UserCourseServiceImplTest {

    private final DaoHelperFactory daoHelperFactory = Mockito.mock(DaoHelperFactory.class);
    private final DaoHelper daoHelper = Mockito.mock(DaoHelper.class);
    private final UserCourseServiceImpl userCourseService = new UserCourseServiceImpl(daoHelperFactory);
    private CourseDao courseDao;
    private UserCourseDao userCourseDao;

    @Before
    public void init() {
        Mockito.when(daoHelperFactory.create()).thenReturn(daoHelper);
        Mockito.doNothing().when(daoHelper).startTransaction();
        courseDao = Mockito.mock(CourseDaoImpl.class);
        userCourseDao = Mockito.mock(UserCourseDaoImpl.class);
        Mockito.when(daoHelper.createCourseDao()).thenReturn(courseDao);
    }

    @Test
    public void testChangeSubscriptionShouldIncreaseSubscribedUserCountCountWhenCourseExistsAndCanSubscribe() {
        UserCourse userCourse = UserCourse.getBuilder()
                .setCourseId(10L)
                .build();
        Course course = Course.getBuilder()
                .setId(10L)
                .setCurrentPupilsQuantity(10)
                .build();


        Mockito.when(courseDao.getById(Mockito.anyLong())).thenReturn(Optional.of(course));
        Mockito.when(courseDao.save(Mockito.any())).thenReturn(true);
        UserCourseDao userCourseDao = Mockito.mock(UserCourseDaoImpl.class);
        Mockito.when(daoHelper.createUserCourseDao()).thenReturn(userCourseDao);
        Mockito.when(userCourseDao.subscribe(Mockito.any())).thenReturn(true);
        Mockito.doNothing().when(daoHelper).endTransaction();

        boolean result = userCourseService.changeSubscription(userCourse);
        Assert.assertTrue(result);
    }

    @Test
    public void testChangeSubscriptionShouldIncreaseSubscribedUserCountCountWhenCourseNotExists() {
        UserCourse userCourse = UserCourse.getBuilder()
                .setCourseId(10L)
                .build();

        Mockito.when(courseDao.getById(Mockito.anyLong())).thenReturn(Optional.empty());
        Mockito.doNothing().when(daoHelper).endTransaction();

        boolean result = userCourseService.changeSubscription(userCourse);
        Assert.assertFalse(result);
    }

    @Test
    public void testFindSubscriptionShouldReturnNullableEntityWhenSubscriptionNotExists() {
        Mockito.when(daoHelper.createUserCourseDao()).thenReturn(userCourseDao);
        Mockito.when(userCourseDao.findByCompositeKey(Mockito.anyLong(), Mockito.anyLong()))
                .thenReturn(Optional.empty());
        Mockito.doNothing().when(daoHelper).endTransaction();

        Optional<UserCourse> result = userCourseService.findSubscription(10L, 10L);
        Assert.assertFalse(result.isPresent());
    }

    @Test
    public void testFindSubscriptionShouldReturnNotNullableEntityWhenSubscriptionExists() {
        UserCourse userCourse = UserCourse.getBuilder().build();
        Mockito.when(daoHelper.createUserCourseDao()).thenReturn(userCourseDao);
        Mockito.when(userCourseDao.findByCompositeKey(Mockito.anyLong(), Mockito.anyLong()))
                .thenReturn(Optional.of(userCourse));
        Mockito.doNothing().when(daoHelper).endTransaction();

        Optional<UserCourse> result = userCourseService.findSubscription(10L, 10L);
        Assert.assertTrue(result.isPresent());
    }
}