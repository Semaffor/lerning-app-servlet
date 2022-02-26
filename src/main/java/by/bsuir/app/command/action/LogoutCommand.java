package by.bsuir.app.command.action;

import by.bsuir.app.command.Command;
import by.bsuir.app.command.CommandResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {
    private static final String WELCOME_PAGE = "/welcome.jsp";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.invalidate();
        return CommandResult.forward(WELCOME_PAGE);
    }
}
