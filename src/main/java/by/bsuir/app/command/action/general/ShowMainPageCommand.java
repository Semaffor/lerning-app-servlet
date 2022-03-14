package by.bsuir.app.command.action.general;

import by.bsuir.app.command.Command;
import by.bsuir.app.command.CommandEnum;
import by.bsuir.app.command.CommandResult;
import by.bsuir.app.entity.Course;
import by.bsuir.app.exception.ServiceException;
import by.bsuir.app.service.CourseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowMainPageCommand implements Command {

    private final static String FORWARD_MAIN_PAGE = "/WEB-INF/view/main-page.jsp";
    private final static int INITIAL_PAGINATION_PAGE = 1;
    private final static int INITIAL_PAGINATION_RECORDS_COUNT = 5;
    private final CourseService courseService;

    public ShowMainPageCommand(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String currentPageString = request.getParameter("currentPage");
        String recordsPerPageString = request.getParameter("recordsPerPage");
        int currentPage = currentPageString == null ? INITIAL_PAGINATION_PAGE : Integer.parseInt(currentPageString);
        int recordsPerPage = recordsPerPageString == null ? INITIAL_PAGINATION_RECORDS_COUNT : Integer.parseInt(recordsPerPageString);

        List<Course> courses = courseService.getCourses(currentPage, recordsPerPage);
        int rows = courseService.getNumberOfUndeletedAndActiveRows();
        int nOfPages = (int) Math.ceil(Math.ceil((double) rows / recordsPerPage));
        request.setAttribute("noOfPages", nOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);
        request.setAttribute("courses", courses);
        request.setAttribute("command", CommandEnum.ITEMS.getCommand());

        return CommandResult.forward(FORWARD_MAIN_PAGE);
    }
}
