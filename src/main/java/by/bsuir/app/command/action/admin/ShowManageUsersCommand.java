package by.bsuir.app.command.action.admin;

import by.bsuir.app.command.Command;
import by.bsuir.app.command.CommandResult;
import by.bsuir.app.entity.User;
import by.bsuir.app.entity.enums.Role;
import by.bsuir.app.exception.ServiceException;
import by.bsuir.app.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

public class ShowManageUsersCommand implements Command {
    private static final String FORWARD_MANAGE_USERS_PAGE = "/WEB-INF/view/manage-users.jsp";
    private final UserService userService;

    public ShowManageUsersCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        List<User> users = userService.findAll();
        users.remove(0);
        List<User> notDeletedUsers = users.stream().filter(user -> !user.isDeleted()).collect(Collectors.toList());
        request.setAttribute("users", notDeletedUsers);
        request.setAttribute("roles", Role.values());
        return CommandResult.forward(FORWARD_MANAGE_USERS_PAGE);
    }
}
