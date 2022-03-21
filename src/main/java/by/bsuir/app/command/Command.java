package by.bsuir.app.command;

import by.bsuir.app.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    String FORWARD_ERROR_404_PAGE = "/WEB-INF/view/errors/error404.jsp";
    String LINK_COMMAND = "/controller?command=";

    CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException;
}
