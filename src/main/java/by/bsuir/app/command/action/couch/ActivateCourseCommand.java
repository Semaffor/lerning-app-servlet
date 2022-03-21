package by.bsuir.app.command.action.couch;

import by.bsuir.app.command.Command;
import by.bsuir.app.command.CommandEnum;
import by.bsuir.app.command.CommandResult;
import by.bsuir.app.exception.ServiceException;
import by.bsuir.app.service.CourseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActivateCourseCommand implements Command {
    private final CourseService courseService;
    private static final String REDIRECT_MANAGE_COURSE_PAGE = "/controller?command=manageCourse";

    public ActivateCourseCommand(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Long courseId = Long.parseLong(request.getParameter("courseId"));
        courseService.changeIsActiveStatus(courseId);
        return CommandResult.redirect(REDIRECT_MANAGE_COURSE_PAGE);
    }
}
