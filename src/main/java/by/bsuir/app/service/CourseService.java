package by.bsuir.app.service;

import by.bsuir.app.entity.Course;
import by.bsuir.app.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    void changeIsActiveStatus(Long courseId);
    void changeIsDeleteStatus(Long courseId);
    boolean isUserSubscribedByUsername(String username, Long courseId);
    int getNumberOfUndeletedRows();
    Optional<Course> getCourse(Long id) throws ServiceException;
    List<Course> getCourses(int currentPage, int recordsPerPage) throws ServiceException;
    List<Course> getAllCourses() throws ServiceException;
}
