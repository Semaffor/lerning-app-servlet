package by.bsuir.app.dao;

import by.bsuir.app.entity.Course;

import java.util.List;
import java.util.Optional;

public interface CourseDao extends Dao<Course> {

    List<Course> getCourses();
    List<Course> getCourses(int currentPage,int recordsPerPage);
    Optional<Course> getCourse(Long id);
    int getTableUndeletedRowsCount();
    boolean isUserSubscribed(String username, Long courseId);
}
