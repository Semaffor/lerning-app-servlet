package by.bsuir.app.command.action.couch;

import by.bsuir.app.command.Command;
import by.bsuir.app.command.CommandEnum;
import by.bsuir.app.command.CommandResult;
import by.bsuir.app.entity.UserTaskDTO;
import by.bsuir.app.exception.ServiceException;
import by.bsuir.app.service.UserTaskService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class ShowCheckTaskCommand implements Command {
    private static final String FORWARD_CHECK_TASK_PAGE = "/WEB-INF/view/change-task.jsp";
    private static final String REDIRECT_SHOW_SUBMITTED_TASKS_PAGE =
            "/controller?command=" + CommandEnum.SHOW_SUBMITTED_TASKS.getCommand();

    private final UserTaskService userTaskService;

    public ShowCheckTaskCommand(UserTaskService userTaskService) {
        this.userTaskService = userTaskService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Long userTaskId = Long.valueOf(request.getParameter("userTaskId"));
        Optional<UserTaskDTO> taskOptional = userTaskService.findUserTaskByTaskId(userTaskId);
        if (taskOptional.isPresent()) {
            UserTaskDTO task = taskOptional.get();
            request.setAttribute("task", task);
            request.setAttribute("command", CommandEnum.SUBMIT_CHECK.getCommand());
            return CommandResult.forward(FORWARD_CHECK_TASK_PAGE);
        }
        return CommandResult.redirect(REDIRECT_SHOW_SUBMITTED_TASKS_PAGE);
    }
}
