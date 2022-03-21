package by.bsuir.app.command.action.user;

import by.bsuir.app.command.Command;
import by.bsuir.app.command.CommandEnum;
import by.bsuir.app.command.CommandResult;
import by.bsuir.app.exception.ServiceException;
import by.bsuir.app.service.UserTaskService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConfirmTaskCommand implements Command {
    private final static String REDIRECT_CONFIRM_TASK = LINK_COMMAND + CommandEnum.SHOW_COURSE_TASKS.getCommand();
    private final UserTaskService userTaskService;

    public ConfirmTaskCommand(UserTaskService userTaskService) {
        this.userTaskService = userTaskService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String username = (String) request.getSession().getAttribute("username");
        Long taskId = Long.valueOf(request.getParameter("userTaskId"));
        String solution = request.getParameter("solution");
        Long courseId = Long.valueOf(request.getParameter("courseId"));

        userTaskService.confirmTask(username, taskId, solution);
        return CommandResult.redirect(REDIRECT_CONFIRM_TASK + "&number=" + courseId);
    }
}
