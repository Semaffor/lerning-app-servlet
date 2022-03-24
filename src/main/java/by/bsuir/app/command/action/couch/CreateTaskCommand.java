package by.bsuir.app.command.action.couch;

import by.bsuir.app.command.Command;
import by.bsuir.app.command.CommandEnum;
import by.bsuir.app.command.CommandResult;
import by.bsuir.app.encoder.Utf8Handler;
import by.bsuir.app.entity.Task;
import by.bsuir.app.exception.ServiceException;
import by.bsuir.app.validator.DateHandler;
import by.bsuir.app.service.TaskService;
import by.bsuir.app.validator.FormDataValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Optional;

public class CreateTaskCommand implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateTaskCommand.class);

    private static final String REDIRECT_MANAGE_COURSE_PAGE = LINK_COMMAND + CommandEnum.SHOW_MANAGEMENT_COURSE
            .getCommand();
    private static final String INCORRECT_VALUES = "successTask=false";
    private static final String INCORRECT_DATE = "successTask=date";
    private static final String DUPLICATE_TASK_TITLE = "successTask=title";
    private static final String SUCCESS_VALUE = "successTask=true";
    private final FormDataValidator dataValidator;
    private final TaskService taskService;

    public CreateTaskCommand(TaskService taskService, FormDataValidator dataValidator) {
        this.dataValidator = dataValidator;
        this.taskService = taskService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String attribute = null;
        try {
            Long courseId = Long.valueOf(request.getParameter("courseId"));
            String dateString = request.getParameter("deadline");
            Utf8Handler encoderHandler = new Utf8Handler();
            String title = encoderHandler.reEncode(request, "title_task");
            String description = encoderHandler.reEncode(request, "description_task");

            DateHandler dateHandler = new DateHandler();
            Date date = dateHandler.convertFromString(dateString);

            Optional<Task> taskOptional = taskService.findByTitle(title);
            dataValidator.checkTaskInputData(title, description, date);

            if (!taskOptional.isPresent()) {

                Task task = Task.getBuilder()
                        .setCourse_id(courseId)
                        .setTitle(title)
                        .setDescription(description)
                        .setDeadline(date)
                        .build();

                taskService.save(task);
                attribute = SUCCESS_VALUE;
            } else {
                attribute = DUPLICATE_TASK_TITLE;
            }
        } catch (IllegalArgumentException e) {
            LOGGER.error(e.getMessage(), e);
            attribute = INCORRECT_DATE;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            attribute = INCORRECT_VALUES;
        }
        return CommandResult.redirect(REDIRECT_MANAGE_COURSE_PAGE + "&" + attribute);
    }


}
