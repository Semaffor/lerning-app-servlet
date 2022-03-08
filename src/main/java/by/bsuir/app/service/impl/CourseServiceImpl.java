package by.bsuir.app.service.impl;

import by.bsuir.app.dao.CourseDao;
import by.bsuir.app.dao.DaoHelper;
import by.bsuir.app.dao.DaoHelperFactory;
import by.bsuir.app.entity.Course;
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
            List<Course> courses = dao.getCourses();
            helper.endTransaction();
            return courses;
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<Course> getCourse(Long id) throws ServiceException{
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            CourseDao dao = helper.createCourseDao();
            Optional<Course> course = dao.getCourse(id);
            helper.endTransaction();
            return course;
        } catch (Exception e) {
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
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void changeIsActiveStatus(Long courseId) {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            CourseDao dao = helper.createCourseDao();
            Optional<Course> courseOptional = dao.getCourse(courseId);
            if (courseOptional.isPresent()) {
                Course course = courseOptional.get();
                course.setActive(!course.isActive());
                dao.save(course);
            }
            helper.endTransaction();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void changeIsDeleteStatus(Long courseId) {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            CourseDao dao = helper.createCourseDao();
            Optional<Course> courseOptional = dao.getCourse(courseId);
            if (courseOptional.isPresent()) {
                Course course = courseOptional.get();
                course.setDeleted(!course.isDeleted());
                dao.save(course);
            }
            helper.endTransaction();
        } catch (Exception e) {
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
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int getNumberOfUndeletedRows() throws ServiceException{
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            CourseDao dao = helper.createCourseDao();
            int rowsCount = dao.getTableUndeletedRowsCount();
            helper.endTransaction();
            return rowsCount;
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

}
