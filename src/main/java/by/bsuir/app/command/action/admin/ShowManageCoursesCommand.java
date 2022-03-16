package by.bsuir.app.command.action.admin;

import by.bsuir.app.command.Command;
import by.bsuir.app.command.CommandResult;
import by.bsuir.app.entity.Course;
import by.bsuir.app.exception.ServiceException;
import by.bsuir.app.service.CourseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ShowManageCoursesCommand implements Command {
    private static final String FORWARD_MANAGE_COURSES_PAGE = "/WEB-INF/view/manage-courses.jsp";
    private final CourseService courseService;

    public ShowManageCoursesCommand(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        List<Course> allCourses = courseService.getAllCourses();
        List<Course> notDeletedCourses = new ArrayList<>();
        List<Course> deletedCourses = new ArrayList<>();

        for (Course course : allCourses) {
            if (course.isDeleted()) {
                deletedCourses.add(course);
            } else {
                notDeletedCourses.add(course);
            }
        }
        request.setAttribute("courses", notDeletedCourses);
        request.setAttribute("deletedCourses", deletedCourses);
        return CommandResult.forward(FORWARD_MANAGE_COURSES_PAGE);
    }
}
