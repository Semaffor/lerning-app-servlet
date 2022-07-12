package by.bsuir.app.command.action.general;

import by.bsuir.app.command.Command;
import by.bsuir.app.command.CommandResult;
import by.bsuir.app.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowRegistrationCommand implements Command {
    private final static String FORWARD_CHECK_TASK_PAGE = "/WEB-INF/view/registration.jsp";
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return CommandResult.forward(FORWARD_CHECK_TASK_PAGE);
    }
}
