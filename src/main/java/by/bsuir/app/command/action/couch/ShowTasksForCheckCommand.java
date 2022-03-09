package by.bsuir.app.command.action.couch;

import by.bsuir.app.command.Command;
import by.bsuir.app.command.CommandResult;
import by.bsuir.app.entity.UserTask;
import by.bsuir.app.exception.ServiceException;
import by.bsuir.app.service.UserTaskService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowTasksForCheckCommand implements Command {
    private static final String FORWARD_CHECK_TASKS_PAGE = "/WEB-INF/view/manage-course.jsp";
    private final UserTaskService userTaskService;

    public ShowTasksForCheckCommand(UserTaskService userTaskService) {
        this.userTaskService = userTaskService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String login = (String) request.getSession().getAttribute("username");
        List<UserTask> tasksOnReview = userTaskService.findCourseTasksOnReviewByCouchUsername(login);
        request.setAttribute("tasks", tasksOnReview);
        return CommandResult.forward(FORWARD_CHECK_TASKS_PAGE);
    }
}
