package by.bsuir.app.command.action.courses;

import by.bsuir.app.command.Command;
import by.bsuir.app.command.CommandResult;
import by.bsuir.app.entity.Course;
import by.bsuir.app.exception.ServiceException;
import by.bsuir.app.service.CourseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class CoursePageCommand implements Command {

    private final static String FORWARD_COURSE_PAGE = "/WEB-INF/view/course-page.jsp";
    private final static String FORWARD_ERROR_PAGE = "/WEB-INF/view/errors/error404.jsp";
    private final CourseService courseService;

    public CoursePageCommand(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String username = (String) request.getSession().getAttribute("username");
        Long id = Long.valueOf(request.getParameter("number"));
        Optional<Course> courseOptional = courseService.getCourse(id);

        if (courseOptional.isPresent()) {
            Course course = courseOptional.get();
            request.setAttribute("course", course);

            boolean isSubscribed = courseService.isUserSubscribedByUsername(username, course.getId());
            String message = isSubscribed ? "unsubscribe" : "subscribe";
            request.setAttribute(message, message);

            return CommandResult.forward(FORWARD_COURSE_PAGE);
        } else {
            return CommandResult.forward(FORWARD_ERROR_PAGE);
        }
    }
}
