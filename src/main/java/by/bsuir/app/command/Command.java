package by.bsuir.app.command;

import by.bsuir.app.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Each new class that implements this interface is
 * created to extend the web app's functionality
 * without changing the already existing code
 */
public interface Command {
    String FORWARD_ERROR_404_PAGE = "/WEB-INF/view/errors/error404.jsp";
    String LINK_COMMAND = "/controller?command=";
    String INCORRECT_VALUE = "incorrectValues=true";
    String SUCCESS_VALUE = "success=true";

    /**
     * Execution method for command. Returns path to go to based on the client
     * request. If Command is specific to some user role, then subclasses in
     * this method should perform validation and grant or not permissions to
     * proceed.
     *
     * @param request - client request
     * @param response - server response
     * @return Class with address to go once the command is executed.
     * @throws ServiceException
     * @see CommandResult
     */
    CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException;
}
