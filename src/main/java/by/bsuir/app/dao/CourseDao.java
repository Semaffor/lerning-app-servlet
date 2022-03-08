package by.bsuir.app.dao;

import by.bsuir.app.entity.Course;

import java.util.List;
import java.util.Optional;

public interface CourseDao extends Dao<Course> {

    List<Course> getCourses(int currentPage,int recordsPerPage);
    int getNumberOfUndeletedAndActiveRows();
    boolean isUserSubscribed(String username, Long courseId);

    Optional<Course> findCourseByCouchUsername(String login);
}
