package by.bsuir.app.command.action.couch;

import by.bsuir.app.command.Command;
import by.bsuir.app.command.CommandEnum;
import by.bsuir.app.command.CommandResult;
import by.bsuir.app.encoder.EncoderHandler;
import by.bsuir.app.exception.ServiceException;
import by.bsuir.app.service.UserTaskService;
import by.bsuir.app.validator.DataValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

public class SubmitTaskCheckCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubmitTaskCheckCommand.class);
    private static final String REDIRECT_SHOW_CHECK_TASKS_PAGE =
            LINK_COMMAND + CommandEnum.SHOW_SUBMITTED_TASKS.getCommand();
    private static final String FORWARD_SUBMIT_TASK_PAGE = "/WEB-INF/view/change-task.jsp";
    private static final String ERROR_ATTRIBUTE = "invalidData";
    private final UserTaskService userTaskService;
    private final DataValidator dataValidator;

    public SubmitTaskCheckCommand(UserTaskService userTaskService, DataValidator dataValidator) {
        this.userTaskService = userTaskService;
        this.dataValidator = dataValidator;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        try {
            Long taskId = Long.parseLong(request.getParameter("userTaskId"));
            int mark = Integer.parseInt(request.getParameter("mark"));
            EncoderHandler encoderHandler = new EncoderHandler();
            String feedback = encoderHandler.reEncode(request, "feedback");

            dataValidator.checkDescription(feedback);
            dataValidator.checkMark(mark);

            userTaskService.reviewTask(taskId, mark, feedback);

        } catch (Exception e) {
        LOGGER.error(e.getMessage(), e);
        request.setAttribute(ERROR_ATTRIBUTE,ERROR_ATTRIBUTE);
        return CommandResult.forward(FORWARD_SUBMIT_TASK_PAGE);
        }
        return CommandResult.redirect(REDIRECT_SHOW_CHECK_TASKS_PAGE);
    }
}
