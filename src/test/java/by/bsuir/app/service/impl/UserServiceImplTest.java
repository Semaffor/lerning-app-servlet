package by.bsuir.app.service.impl;

import by.bsuir.app.dao.DaoHelper;
import by.bsuir.app.dao.DaoHelperFactory;
import by.bsuir.app.dao.UserDao;
import by.bsuir.app.entity.User;
import by.bsuir.app.entity.enums.Role;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class UserServiceImplTest {

    private final DaoHelperFactory daoHelperFactory = Mockito.mock(DaoHelperFactory.class);
    private final DaoHelper daoHelper = Mockito.mock(DaoHelper.class);
    private final UserServiceImpl userService = new UserServiceImpl(daoHelperFactory);
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
    public void testChangeBlockedStatusShouldChangeStatusWhenUserActive() {
        User user = Mockito.mock(User.class);
        Mockito.when(userDao.getById(Mockito.anyLong())).thenReturn(Optional.of(user));

        userService.changeBlockedStatus(10L);
        Mockito.verify(user, Mockito.times(1)).setBlocked(Mockito.anyBoolean());
        Mockito.verify(userDao, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    public void testChangeBlockedStatusShouldNotChangeStatusWhenUserNotExists() {
        Mockito.when(userDao.getById(Mockito.anyLong())).thenReturn(Optional.empty());

        userService.changeBlockedStatus(10L);
        Mockito.verify(userDao, Mockito.times(0)).save(Mockito.any());
    }

    @Test
    public void testChangeDeletedStatusShouldChangeStatusWhenUserExists() {
        User user = Mockito.mock(User.class);
        Mockito.when(userDao.getById(Mockito.anyLong())).thenReturn(Optional.of(user));

        userService.changeDeletedStatus(10L);
        Mockito.verify(user, Mockito.times(1)).setDeleted(Mockito.anyBoolean());
        Mockito.verify(userDao, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    public void testChangeRoleShouldChangeRoleWhenUserExists() {
        User user = Mockito.mock(User.class);
        Mockito.when(userDao.getById(Mockito.anyLong())).thenReturn(Optional.of(user));

        userService.changeRole(10L, Role.COUCH);
        Mockito.verify(user, Mockito.times(1)).setRole(Mockito.any());
        Mockito.verify(userDao, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    public void testChangeRoleShouldNotChangeRoleWhenUserNotExists() {
        Mockito.when(userDao.getById(Mockito.anyLong())).thenReturn(Optional.empty());

        userService.changeRole(10L, Role.COUCH);
        Mockito.verify(userDao, Mockito.times(0)).save(Mockito.any());
    }
}