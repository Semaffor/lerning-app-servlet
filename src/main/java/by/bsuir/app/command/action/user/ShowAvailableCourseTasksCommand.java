package by.bsuir.app.command.action.user;

import by.bsuir.app.command.Command;
import by.bsuir.app.command.CommandResult;
import by.bsuir.app.entity.Task;
import by.bsuir.app.entity.UserTaskDto;
import by.bsuir.app.exception.ServiceException;
import by.bsuir.app.service.TaskService;
import by.bsuir.app.service.UserTaskService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowAvailableCourseTasksCommand implements Command {
    private final static String FORWARD_SHOW_AVAILABLE_COURSE_TASKS = "/WEB-INF/view/show-tasks-user.jsp";
    private final TaskService taskService;
    private final UserTaskService userTaskService;

    public ShowAvailableCourseTasksCommand(TaskService taskService, UserTaskService userTaskService) {
        this.taskService = taskService;
        this.userTaskService = userTaskService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Long courseId = Long.valueOf(request.getParameter("number"));
        String username = (String) request.getSession().getAttribute("username");

        List<Task> tasks = taskService.findCourseAvailableTasks(courseId);
        List<UserTaskDto> confirmedTasks = userTaskService.findConfirmedUserCourseTasks(username, courseId);
        request.setAttribute("tasks", tasks);
        request.setAttribute("userTasks", confirmedTasks);
        return CommandResult.forward(FORWARD_SHOW_AVAILABLE_COURSE_TASKS);
    }
}
