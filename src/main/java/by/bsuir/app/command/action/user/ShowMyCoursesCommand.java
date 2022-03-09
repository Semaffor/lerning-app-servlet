package by.bsuir.app.command.action.user;

import by.bsuir.app.command.Command;
import by.bsuir.app.command.CommandResult;
import by.bsuir.app.exception.ServiceException;
import by.bsuir.app.service.impl.UserCourseServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowMyCoursesCommand implements Command {

    private final UserCourseServiceImpl userCourseService;

    public ShowMyCoursesCommand(UserCourseServiceImpl userCourseService) {
        this.userCourseService = userCourseService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return null;
    }
}
