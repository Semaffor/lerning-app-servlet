package by.bsuir.app.command.action.user;

import by.bsuir.app.command.Command;
import by.bsuir.app.command.CommandEnum;
import by.bsuir.app.command.CommandResult;
import by.bsuir.app.command.action.couch.EditCourseCommand;
import by.bsuir.app.encoder.EncoderHandler;
import by.bsuir.app.exception.ServiceException;
import by.bsuir.app.service.UserTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConfirmTaskCommand implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(EditCourseCommand.class);
    private final static String REDIRECT_CONFIRM_TASK = LINK_COMMAND + CommandEnum.SHOW_COURSE_TASKS.getCommand();
    private final UserTaskService userTaskService;

    public ConfirmTaskCommand(UserTaskService userTaskService) {
        this.userTaskService = userTaskService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String attribute = null;
        Long courseId = Long.valueOf(request.getParameter("courseId"));
        Long taskId = Long.valueOf(request.getParameter("userTaskId"));
        String username = (String) request.getSession().getAttribute("username");

        try {
            EncoderHandler encoderHandler = new EncoderHandler();
            String solution = encoderHandler.reEncode(request, "solution");
            userTaskService.confirmTask(username, taskId, solution);
            attribute = SUCCESS_VALUE;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            attribute = INCORRECT_VALUE;
        }
        return CommandResult.redirect(REDIRECT_CONFIRM_TASK + "&number=" + courseId + "&" + attribute);
    }
}
