package by.bsuir.app.service.impl;

import by.bsuir.app.dao.CourseDao;
import by.bsuir.app.dao.DaoHelper;
import by.bsuir.app.dao.DaoHelperFactory;
import by.bsuir.app.dao.UserCourseDao;
import by.bsuir.app.entity.Course;
import by.bsuir.app.exception.DaoException;
import by.bsuir.app.exception.ServiceException;
import by.bsuir.app.service.CourseService;
import by.bsuir.app.service.Service;

import java.util.List;
import java.util.Optional;

public class CourseServiceImpl extends Service implements CourseService {

    public CourseServiceImpl(DaoHelperFactory daoHelperFactory) {
        super(daoHelperFactory);
    }

    public List<Course> getAllCourses() throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            CourseDao dao = helper.createCourseDao();
            List<Course> courses = dao.getAll();
            helper.endTransaction();
            return courses;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<Course> findCourseByCouchUsername(String login) {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            CourseDao dao = helper.createCourseDao();
            Optional<Course> course = dao.findCourseByCouchUsername(login);
            helper.endTransaction();
            return course;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Course> findSubscriptionsByUsername(String username) {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            CourseDao dao = helper.createCourseDao();
            List<Course> userCourses = dao.findSubscriptionsByUsername(username);
            helper.endTransaction();
            return userCourses;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<Course> getCourse(Long id) throws ServiceException{
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            CourseDao dao = helper.createCourseDao();
            Optional<Course> course = dao.getById(id);
            helper.endTransaction();
            return course;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Course> getCourses(int currentPage, int recordsPerPage) throws ServiceException{
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            CourseDao dao = helper.createCourseDao();
            List<Course> courses = dao.getCourses(currentPage, recordsPerPage);
            helper.endTransaction();
            return courses;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void changeIsActiveStatus(Long courseId) {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            CourseDao dao = helper.createCourseDao();
            Optional<Course> courseOptional = dao.getById(courseId);
            if (courseOptional.isPresent()) {
                Course course = courseOptional.get();
                course.setActive(!course.isActive());
                dao.save(course);
            }
            helper.endTransaction();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void changeIsDeleteStatus(Long courseId) {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            CourseDao dao = helper.createCourseDao();
            Optional<Course> courseOptional = dao.getById(courseId);
            if (courseOptional.isPresent()) {
                Course course = courseOptional.get();
                course.setDeleted(!course.isDeleted());
                course.setActive(false);
                dao.save(course);
            }
            helper.endTransaction();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean isUserSubscribedByUsername(String username, Long courseId) {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            CourseDao dao = helper.createCourseDao();
            boolean isSubscribed = dao.isUserSubscribed(username, courseId);
            helper.endTransaction();
            return isSubscribed;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean save(Course course) {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            CourseDao dao = helper.createCourseDao();
            boolean isSubscribed = dao.save(course);
            helper.endTransaction();
            return isSubscribed;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int getNumberOfUndeletedAndActiveRows() throws ServiceException{
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            CourseDao dao = helper.createCourseDao();
            int rowsCount = dao.getNumberOfUndeletedAndActiveRows();
            helper.endTransaction();
            return rowsCount;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

}
