package by.bsuir.app.dao;

import by.bsuir.app.entity.Course;

import java.util.List;
import java.util.Optional;

public interface CourseDao extends Dao<Course> {

    int getNumberOfUndeletedAndActiveRows();
    boolean isUserSubscribed(String username, Long courseId);
    Optional<Course> findCourseByCouchUsername(String login);

    List<Course> getCourses(int currentPage,int recordsPerPage);
    List<Course> findSubscriptionsByUsername(String username);

}
