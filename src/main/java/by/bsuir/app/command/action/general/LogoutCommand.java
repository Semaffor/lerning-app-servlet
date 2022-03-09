package by.bsuir.app.command.action.general;

import by.bsuir.app.command.Command;
import by.bsuir.app.command.CommandResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {
    private static final String WELCOME_PAGE = "/welcome.jsp";
    private static final String LOGOUT_ATTR = "logout";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.invalidate();
        request.setAttribute(LOGOUT_ATTR, LOGOUT_ATTR);
        return CommandResult.forward(WELCOME_PAGE);
    }
}
