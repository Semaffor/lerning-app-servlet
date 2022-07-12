package by.bsuir.app.command.action.user;

import by.bsuir.app.command.Command;
import by.bsuir.app.command.CommandEnum;
import by.bsuir.app.command.CommandResult;
import by.bsuir.app.entity.Course;
import by.bsuir.app.entity.User;
import by.bsuir.app.exception.ServiceException;
import by.bsuir.app.service.CourseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowMyCoursesCommand implements Command {
    private final static String FORWARD_SHOW_MY_COURSE_PAGE = "/WEB-INF/view/user-subscribed-courses.jsp";
    private final CourseService courseService;

    public ShowMyCoursesCommand(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String username = (String) request.getSession().getAttribute(User.NAME);
        List<Course> courses = courseService.findSubscriptionsByUsername(username);
        request.setAttribute("courses", courses);
        request.setAttribute("command", CommandEnum.SHOW_COURSE_TASKS.getCommand());
        return CommandResult.forward(FORWARD_SHOW_MY_COURSE_PAGE);
    }
}
