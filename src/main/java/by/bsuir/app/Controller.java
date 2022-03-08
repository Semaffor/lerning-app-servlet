package by.bsuir.app;

import by.bsuir.app.command.Command;
import by.bsuir.app.command.CommandFactory;
import by.bsuir.app.command.CommandResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {

    private static final CommandFactory commandFactory = new CommandFactory();
    private final Logger LOGGER = LogManager.getLogger(this);

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
        Command action = commandFactory.createCommand(command);

        LOGGER.trace("Request command: " + command);
        try {
            CommandResult result = action.execute(req, resp);
            dispatch(req, resp, result);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            req.setAttribute("errorMessage", e.getMessage());
            dispatch(req, resp, CommandResult.forward("/errors/error404.jsp"));
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