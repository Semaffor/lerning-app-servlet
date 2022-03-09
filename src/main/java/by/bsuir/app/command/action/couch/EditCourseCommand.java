package by.bsuir.app.command.action.couch;

import by.bsuir.app.command.Command;
import by.bsuir.app.command.CommandEnum;
import by.bsuir.app.command.CommandResult;
import by.bsuir.app.entity.Course;
import by.bsuir.app.entity.enums.CourseFormat;
import by.bsuir.app.entity.enums.TechnologyType;
import by.bsuir.app.exception.ServiceException;
import by.bsuir.app.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

public class EditCourseCommand implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(EditCourseCommand.class);
    private static final int MAX_TITLE_LENGTH = 90;
    private static final int MAX_DESCRIPTION_LENGTH = 255;
    private static final int MAX_DURATION = 100;
    private static final int MAX_MAX_PUPILS = 100;
    private static final String REDIRECT_MANAGE_COURSE_PAGE =  "/controller?command=" + CommandEnum.SHOW_MANAGEMENT_COURSE.getCommand();;
    private static final String INCORRECT_VALUES = "incorrectValues";
    private static final String SUCCESS_VALUE = "success";
    private final CourseService courseService;

    public EditCourseCommand(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        try {
            Enumeration<String> parameterNames = request.getParameterNames();
            System.out.println(parameterNames);
            Long courseId = Long.parseLong(request.getParameter("courseId"));
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            int duration = getInt(request.getParameter("duration"));
            int chosenTechnologyCode = getInt(request.getParameter("chosenTechnology"));
            int chosenFormatCode = getInt(request.getParameter("chosenFormat"));
            int maxPupilsQuantity = getInt(request.getParameter("maxPupilsQuantity"));

            if (title.length() > MAX_TITLE_LENGTH ||
                    description.length() > MAX_DESCRIPTION_LENGTH ||
                    duration > MAX_DURATION ||
                    maxPupilsQuantity > MAX_MAX_PUPILS) {
                throw new IllegalArgumentException();
            }

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
            request.setAttribute(SUCCESS_VALUE, SUCCESS_VALUE);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            request.setAttribute(INCORRECT_VALUES, INCORRECT_VALUES);
        }
        return CommandResult.redirect(REDIRECT_MANAGE_COURSE_PAGE);
    }

    private int getInt(String str) {
        return Integer.parseInt(str);
    }
}
