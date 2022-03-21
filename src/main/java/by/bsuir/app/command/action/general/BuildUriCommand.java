package by.bsuir.app.command.action.general;

import by.bsuir.app.command.Command;
import by.bsuir.app.command.CommandResult;
import by.bsuir.app.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BuildUriCommand implements Command {

    private final static String DELIMITERS = "[{},]";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String urlParamsWithHooks = request.getParameter("params");
        String urlParamsWithoutHooks = urlParamsWithHooks.replaceAll(DELIMITERS, "");

        if (urlParamsWithoutHooks.contains("command")) {
            String urlParams = urlParamsWithoutHooks.replaceAll("\\s", "&");
            String url = "controller?" + urlParams;
            return CommandResult.redirect(url);
        }
        return CommandResult.redirect("/");
    }
}
