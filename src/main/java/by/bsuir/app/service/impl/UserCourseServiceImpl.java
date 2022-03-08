package by.bsuir.app.service.impl;

import by.bsuir.app.dao.DaoHelper;
import by.bsuir.app.dao.DaoHelperFactory;
import by.bsuir.app.dao.UserCourseDao;
import by.bsuir.app.entity.UserCourse;
import by.bsuir.app.exception.ServiceException;
import by.bsuir.app.service.Service;
import by.bsuir.app.service.UserCourseService;

import java.util.Optional;

public class UserCourseServiceImpl  extends Service implements UserCourseService {
    public UserCourseServiceImpl(DaoHelperFactory daoHelperFactory) {
        super(daoHelperFactory);
    }

    @Override
    public boolean changeSubscription(UserCourse subscription) {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            UserCourseDao dao = helper.createUserCourseDao();
            boolean result = dao.subscribe(subscription);
            helper.endTransaction();
            return result;
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<UserCourse> findSubscription(Long userId, Long courseId) {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            UserCourseDao dao = helper.createUserCourseDao();
            Optional<UserCourse> userCourse = dao.findByCompositeKey(userId, courseId);
            helper.endTransaction();
            return userCourse;
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
}
