package by.bsuir.app.command.action.couch;

import by.bsuir.app.command.Command;
import by.bsuir.app.command.CommandResult;
import by.bsuir.app.entity.Course;
import by.bsuir.app.entity.enums.CourseFormat;
import by.bsuir.app.entity.enums.TechnologyType;
import by.bsuir.app.exception.ServiceException;
import by.bsuir.app.service.CourseService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class ShowManageCourseCommand implements Command {
    private static final String FORWARD_MANAGE_COURSE_PAGE = "/WEB-INF/view/manage-course.jsp";
    private final CourseService courseService;

    public ShowManageCourseCommand(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String login = (String) request.getSession().getAttribute("username");
        Optional<Course> courseOptional = courseService.findCourseByCouchUsername(login);
        if (courseOptional.isPresent()) {
            Course course = courseOptional.get();
            request.setAttribute("course", course);
        }
        request.setAttribute("technologies", TechnologyType.values());
        request.setAttribute("formats", CourseFormat.values());
        return CommandResult.forward(FORWARD_MANAGE_COURSE_PAGE);
    }
}
