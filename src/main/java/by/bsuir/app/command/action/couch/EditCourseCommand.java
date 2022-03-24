package by.bsuir.app.command.action.couch;

import by.bsuir.app.command.Command;
import by.bsuir.app.command.CommandEnum;
import by.bsuir.app.command.CommandResult;
import by.bsuir.app.encoder.Utf8Handler;
import by.bsuir.app.entity.Course;
import by.bsuir.app.entity.enums.CourseFormat;
import by.bsuir.app.entity.enums.TechnologyType;
import by.bsuir.app.exception.ServiceException;
import by.bsuir.app.service.CourseService;
import by.bsuir.app.validator.FormDataValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditCourseCommand implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(EditCourseCommand.class);
    private static final String REDIRECT_MANAGE_COURSE_PAGE = LINK_COMMAND + CommandEnum.SHOW_MANAGEMENT_COURSE
            .getCommand();
    private final CourseService courseService;
    private final FormDataValidator dataValidator;

    public EditCourseCommand(CourseService courseService, FormDataValidator dataValidator) {
        this.courseService = courseService;
        this.dataValidator = dataValidator;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String attribute = null;
        try {
            Long courseId = Long.parseLong(request.getParameter("courseId"));
            Utf8Handler encoderHandler = new Utf8Handler();
            String title = encoderHandler.reEncode(request, "title_course");
            String description = encoderHandler.reEncode(request, "description_course");
            int duration = getInt(request.getParameter("duration"));
            int chosenTechnologyCode = getInt(request.getParameter("chosenTechnology"));
            int chosenFormatCode = getInt(request.getParameter("chosenFormat"));
            int maxPupilsQuantity = getInt(request.getParameter("maxPupilsQuantity"));

            dataValidator.checkCourseInputData(title, description, duration, maxPupilsQuantity);

            Course course = Course.getBuilder()
                    .setId(courseId)
                    .setTitle(title)
                    .setDescription(description)
                    .setDuration(duration)
                    .setTechnology(TechnologyType.getTechnologyTypeFromCode(chosenTechnologyCode))
                    .setCourseFormat(CourseFormat.getFormatFromCode(chosenFormatCode))
                    .setMaxPupilsQuantity(maxPupilsQuantity)
                    .build();

            courseService.save(course);
            attribute = SUCCESS_VALUE;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            attribute = INCORRECT_VALUE;
        }
        return CommandResult.redirect(REDIRECT_MANAGE_COURSE_PAGE + "&" + attribute);
    }

    private int getInt(String str) {
        return Integer.parseInt(str);
    }
}
