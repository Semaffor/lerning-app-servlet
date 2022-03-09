package by.bsuir.app.command.action.admin;

import by.bsuir.app.command.Command;
import by.bsuir.app.command.CommandEnum;
import by.bsuir.app.command.CommandResult;
import by.bsuir.app.entity.enums.FormAction;
import by.bsuir.app.exception.ServiceException;
import by.bsuir.app.service.CourseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ManageCoursesCommand implements Command {
    private static final String REDIRECT_SHOW_MANAGE_COURSES_PAGE =
            "/controller?command=" + CommandEnum.SHOW_MANAGEMENT_COURSES.getCommand();
    private final CourseService courseService;

    public ManageCoursesCommand(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String actionString = request.getParameter("formAction");
        FormAction action = FormAction.getFormActionFromString(actionString);
        String courseIdString = request.getParameter("courseId");
        Long courseId = Long.valueOf(courseIdString);

        if (FormAction.ENABLE.equals(action) || FormAction.DISABLE.equals(action)) {
            courseService.changeIsActiveStatus(courseId);
        } else {
            courseService.changeIsDeleteStatus(courseId);
        }
        return CommandResult.redirect(REDIRECT_SHOW_MANAGE_COURSES_PAGE);
    }
}
