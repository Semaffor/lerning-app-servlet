package by.bsuir.app.dao.impl;

import by.bsuir.app.dao.AbstractDao;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoImplTest {

    @Test
    void findUserByLoginAndPassword() {
        AbstractDao abstractDao = Mockito.mock(AbstractDao.class, Mockito.CALLS_REAL_METHODS);
//        Mockito.when(abstractDao.getAll()).thenReturn("Abstract");
//        assertEquals();
    }

    @Test
    void getFields() {
    }
}