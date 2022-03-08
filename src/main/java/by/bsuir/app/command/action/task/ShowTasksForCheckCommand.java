package by.bsuir.app.command.action.task;

import by.bsuir.app.command.Command;
import by.bsuir.app.command.CommandResult;
import by.bsuir.app.exception.ServiceException;
import by.bsuir.app.service.TaskService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowTasksForCheckCommand implements Command {
    private static final String FORWARD_MANAGE_USERS_PAGE = "/WEB-INF/view/check-tasks.jsp";
    private final TaskService taskService;

    public ShowTasksForCheckCommand(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return null;
    }
}
