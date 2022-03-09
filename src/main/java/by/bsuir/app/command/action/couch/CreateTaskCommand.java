package by.bsuir.app.command.action.couch;

import by.bsuir.app.command.Command;
import by.bsuir.app.command.CommandEnum;
import by.bsuir.app.command.CommandResult;
import by.bsuir.app.entity.Task;
import by.bsuir.app.exception.ServiceException;
import by.bsuir.app.service.DateHandler;
import by.bsuir.app.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.util.Date;

public class CreateTaskCommand implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateTaskCommand.class);
    private static final int MAX_TITLE_LENGTH = 45;
    private static final int MAX_DESCRIPTION_LENGTH = 255;
    private static final String REDIRECT_MANAGE_COURSE_PAGE =  "/controller?command=" + CommandEnum.SHOW_MANAGEMENT_COURSE.getCommand();
    private static final String INCORRECT_VALUES = "incorrectValues";
    private static final String SUCCESS_VALUE = "success";
    private final TaskService taskService;
    private final DateHandler dateHandler;

    public CreateTaskCommand(TaskService taskService, DateHandler dateHandler) {
        this.taskService = taskService;
        this.dateHandler = dateHandler;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        try {
            Long courseId = Long.valueOf(request.getParameter("courseId"));
            String title = request.getParameter("title_task");
            String description = request.getParameter("description_task");
            String dateString = request.getParameter("deadline");
            Date date = dateHandler.convertFromString(dateString);

            if (title.length() > MAX_TITLE_LENGTH || description.length() > MAX_DESCRIPTION_LENGTH) {
                throw new IllegalArgumentException("Over the prescribed length.");
            }
            if (dateHandler.checkOnOldDate(date)) {
                throw new IllegalArgumentException("Incorrect date.");
            }
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
