package by.bsuir.app.command.action.users;

import by.bsuir.app.command.Command;
import by.bsuir.app.command.CommandEnum;
import by.bsuir.app.command.CommandResult;
import by.bsuir.app.entity.enums.FormAction;
import by.bsuir.app.exception.ServiceException;
import by.bsuir.app.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ManageUsersCommand implements Command {
    private static final String REDIRECT_SHOW_MANAGE_COURSES_PAGE =
            "/controller?command=" + CommandEnum.SHOW_MANAGEMENT_USERS.getCommand();
    private final UserService userService;

    public ManageUsersCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String actionString = request.getParameter("formAction");
        FormAction action = FormAction.getFormActionFromString(actionString);
        String userIdString = request.getParameter("userId");
        Long userId = Long.valueOf(userIdString);

        if (FormAction.ENABLE.equals(action) || FormAction.DISABLE.equals(action)) {
            userService.changeIsBlockedStatus(userId);
        } else {
            userService.changeIsDeletedStatus(userId);
        }
        return CommandResult.redirect(REDIRECT_SHOW_MANAGE_COURSES_PAGE);
    }
}
