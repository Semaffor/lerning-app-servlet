package by.bsuir.app.command.action.user;

import by.bsuir.app.command.Command;
import by.bsuir.app.command.CommandEnum;
import by.bsuir.app.command.CommandResult;
import by.bsuir.app.entity.Task;
import by.bsuir.app.exception.ServiceException;
import by.bsuir.app.service.TaskService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class DoTaskCommand implements Command {
    private final static String FORWARD_SHOW_AVAILABLE_COURSE_TASKS = "/WEB-INF/view/change-task.jsp";
    private final static String REDIRECT_CONFIRM_TASK = LINK_COMMAND + CommandEnum.SHOW_COURSE_TASKS.getCommand();

    private final TaskService taskService;

    public DoTaskCommand(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Long userTaskId = Long.valueOf(request.getParameter("taskId"));
        Long courseId = Long.valueOf(request.getParameter("courseId"));
        Optional<Task> optionalTask = taskService.findById(userTaskId);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            request.setAttribute("task", task);
            request.setAttribute("command", CommandEnum.CONFIRM_TASK.getCommand());
            request.setAttribute("courseId", courseId);
            return CommandResult.forward(FORWARD_SHOW_AVAILABLE_COURSE_TASKS);
        }
        return CommandResult.redirect(REDIRECT_CONFIRM_TASK);
    }
}
