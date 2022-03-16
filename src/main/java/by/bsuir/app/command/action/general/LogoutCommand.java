package by.bsuir.app.command.action.general;

import by.bsuir.app.command.Command;
import by.bsuir.app.command.CommandResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {
    private final static String REDIRECT_LOGIN_PAGE = "/?logout=true";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.invalidate();
        return CommandResult.redirect(REDIRECT_LOGIN_PAGE);
    }
}
