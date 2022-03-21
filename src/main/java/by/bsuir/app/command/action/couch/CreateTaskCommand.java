package by.bsuir.app.command.action.couch;

import by.bsuir.app.command.Command;
import by.bsuir.app.command.CommandEnum;
import by.bsuir.app.command.CommandResult;
import by.bsuir.app.entity.Task;
import by.bsuir.app.exception.ServiceException;
import by.bsuir.app.validator.DateHandler;
import by.bsuir.app.service.TaskService;
import by.bsuir.app.validator.DataValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class CreateTaskCommand implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateTaskCommand.class);

    private static final String REDIRECT_MANAGE_COURSE_PAGE = LINK_COMMAND + CommandEnum.SHOW_MANAGEMENT_COURSE
            .getCommand();
    private static final String INCORRECT_VALUES = "incorrectValues";
    private static final String SUCCESS_VALUE = "success";
    private final DataValidator dataValidator;
    private final TaskService taskService;

    public CreateTaskCommand(TaskService taskService, DataValidator dataValidator) {
        this.dataValidator = dataValidator;
        this.taskService = taskService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        try {
            Long courseId = Long.valueOf(request.getParameter("courseId"));
            String title = request.getParameter("title_task");
            String description = request.getParameter("description_task");
            String dateString = request.getParameter("deadline");

            DateHandler dateHandler = new DateHandler();
            Date date = dateHandler.convertFromString(dateString);

            dataValidator.checkTaskInputData(title, description, date);

            Task task = Task.getBuilder()
                    .setCourse_id(courseId)
                    .setTitle(title)
                    .setDescription(description)
                    .setDeadline(date)
                    .build();

            taskService.save(task);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            request.setAttribute(e.getMessage(), INCORRECT_VALUES);
        }
        return CommandResult.redirect(REDIRECT_MANAGE_COURSE_PAGE);
    }


}
