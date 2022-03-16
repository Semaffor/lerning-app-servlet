package by.bsuir.app;

import by.bsuir.app.command.Command;
import by.bsuir.app.command.CommandFactory;
import by.bsuir.app.command.CommandResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);
    private static final String FORWARD_ERROR_PAGE = "/errors/error404.jsp";
    private final CommandFactory commandFactory = new CommandFactory();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String command = req.getParameter("command");

        try {
            Command action = commandFactory.createCommand(command);
            LOGGER.trace("Request command: " + command);

            CommandResult result = action.execute(req, resp);
            dispatch(req, resp, result);
        } catch (Exception e) {
            LOGGER.error(e + ": " + e.getMessage());
            req.setAttribute("errorMessage", e.getMessage());
            dispatch(req, resp, CommandResult.forward(FORWARD_ERROR_PAGE));
        }

    }

    private void dispatch(HttpServletRequest req, HttpServletResponse resp, CommandResult result) throws IOException,
            ServletException {
        String page = result.getPage();
        if (!result.isRedirect()) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(req, resp);
        } else {
            resp.sendRedirect(page);
        }
    }
}
