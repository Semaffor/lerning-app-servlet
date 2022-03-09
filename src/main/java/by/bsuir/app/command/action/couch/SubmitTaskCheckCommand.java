package by.bsuir.app.command.action.couch;

import by.bsuir.app.command.Command;
import by.bsuir.app.command.CommandEnum;
import by.bsuir.app.command.CommandResult;
import by.bsuir.app.dao.UserTaskDao;
import by.bsuir.app.entity.Task;
import by.bsuir.app.exception.ServiceException;
import by.bsuir.app.service.TaskService;
import by.bsuir.app.service.UserTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SubmitTaskCheckCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubmitTaskCheckCommand.class);
    private static final String REDIRECT_SHOW_CHECK_TASKS_PAGE =
            "/controller?command=" + CommandEnum.SHOW_SUBMITTED_TASKS.getCommand();;
    private static final String FORWARD_SUBMIT_TASK_PAGE = "/WEB-INF/view/check-task.jsp";
    private static final String ERROR_ATTRIBUTE = "invalidData";
    private static final int MAX_DESCRIPTION_LENGTH = 255;

    private final UserTaskService userTaskService;

    public SubmitTaskCheckCommand(UserTaskService userTaskService) {
        this.userTaskService = userTaskService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        try {
            Long taskId = Long.parseLong(request.getParameter("taskId"));
            int mark = Integer.parseInt(request.getParameter("mark"));
            String feedback = request.getParameter("feedback");

            if (mark > 10 || mark < 0 || feedback.length() > MAX_DESCRIPTION_LENGTH || feedback.isEmpty()) {
                throw new IllegalArgumentException(ERROR_ATTRIBUTE);
            }
            userTaskService.confirmTask(taskId, mark, feedback);

        } catch (Exception e) {
        LOGGER.error(e.getMessage(), e);
        request.setAttribute(ERROR_ATTRIBUTE,ERROR_ATTRIBUTE);
        return CommandResult.forward(FORWARD_SUBMIT_TASK_PAGE);
        }
        return CommandResult.redirect(REDIRECT_SHOW_CHECK_TASKS_PAGE);
    }
}
