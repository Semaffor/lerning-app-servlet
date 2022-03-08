package by.bsuir.app.command.action.users;

import by.bsuir.app.command.Command;
import by.bsuir.app.command.CommandEnum;
import by.bsuir.app.command.CommandResult;
import by.bsuir.app.entity.enums.Role;
import by.bsuir.app.exception.ServiceException;
import by.bsuir.app.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditRoleCommand implements Command {
    private static final String REDIRECT_SHOW_MANAGE_USERS_PAGE =
            "/controller?command=" + CommandEnum.SHOW_MANAGEMENT_USERS.getCommand();
    private final UserService userService;

    public EditRoleCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Long userId = Long.valueOf(request.getParameter("userId"));
        String newRoleString = request.getParameter("chosenRole");
        Role role = Role.getRoleFromString(newRoleString);
        userService.changeRole(userId, role);
        return CommandResult.redirect(REDIRECT_SHOW_MANAGE_USERS_PAGE);
    }
}
