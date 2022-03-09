package by.bsuir.app.command.action.couch;

import by.bsuir.app.command.Command;
import by.bsuir.app.command.CommandEnum;
import by.bsuir.app.command.CommandResult;
import by.bsuir.app.entity.Task;
import by.bsuir.app.exception.ServiceException;
import by.bsuir.app.service.TaskService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class ShowCheckTaskCommand implements Command {
    private static final String FORWARD_CHECK_TASK_PAGE = "/WEB-INF/view/check-task.jsp";
    private static final String REDIRECT_SHOW_SUBMITTED_TASKS_PAGE =
            "/controller?command=" + CommandEnum.SHOW_SUBMITTED_TASKS.getCommand();

    private final TaskService taskService;

    public ShowCheckTaskCommand(TaskService taskService) {
        this.taskService = taskService;
    }
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Long taskId = Long.valueOf(request.getParameter("taskId"));
        Optional<Task> taskOptional = taskService.findById(taskId);
        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            request.setAttribute("task", task);
            return CommandResult.forward(FORWARD_CHECK_TASK_PAGE);
        }
        return CommandResult.redirect(REDIRECT_SHOW_SUBMITTED_TASKS_PAGE);
    }
}
