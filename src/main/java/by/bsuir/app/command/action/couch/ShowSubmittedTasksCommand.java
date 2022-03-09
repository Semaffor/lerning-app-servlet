package by.bsuir.app.command.action.couch;

import by.bsuir.app.command.Command;
import by.bsuir.app.command.CommandResult;
import by.bsuir.app.entity.UserTaskDTO;
import by.bsuir.app.exception.ServiceException;
import by.bsuir.app.service.UserTaskService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowSubmittedTasksCommand implements Command {
    private static final String FORWARD_SHOW_CHECK_TASKS_PAGE = "/WEB-INF/view/show-tasks.jsp";
    private final UserTaskService userTaskService;

    public ShowSubmittedTasksCommand(UserTaskService userTaskService) {
        this.userTaskService = userTaskService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String login = (String) request.getSession().getAttribute("username");
        List<UserTaskDTO> tasksOnReview = userTaskService.findCourseTasksOnReviewByCouchUsername(login);
        request.setAttribute("tasks", tasksOnReview);
        return CommandResult.forward(FORWARD_SHOW_CHECK_TASKS_PAGE);
    }
}