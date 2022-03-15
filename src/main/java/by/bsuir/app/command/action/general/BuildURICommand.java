package by.bsuir.app.command.action.general;

import by.bsuir.app.command.Command;
import by.bsuir.app.command.CommandResult;
import by.bsuir.app.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BuildURICommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String urlParamsWithHooks = request.getParameter("params");
        String urlParamsWithoutHooks = urlParamsWithHooks.replaceAll("[{},]", "");

        if (urlParamsWithoutHooks.length() != 0) {
            String urlParams = urlParamsWithoutHooks.replaceAll("\\s", "&");
            String url = "controller?" + urlParams;
            return CommandResult.redirect(url);
        }
        return CommandResult.redirect("/");
    }
}
