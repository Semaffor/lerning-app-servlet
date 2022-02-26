package by.bsuir.app.command.action;

import by.bsuir.app.command.Command;
import by.bsuir.app.command.CommandResult;
import by.bsuir.app.entity.User;
import by.bsuir.app.entity.enums.Role;
import by.bsuir.app.exception.ServiceException;
import by.bsuir.app.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class LoginCommand implements Command {

    private final static Logger LOGGER = LogManager.getLogger(LoginCommand.class);
    private final static String MAIN_PAGE_URL = "/controller?command=showMain";
    private final static String LOGIN_PAGE = "/welcome.jsp";

    private final UserService service;

    public LoginCommand(UserService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String login = request.getParameter("username");
        String password = request.getParameter("password");

        LOGGER.info(String.format("Inputs - login: %s, password: %s", login, password));
        Optional<User> optionalUser = service.login(login, password);

        String errorMessageLabel = "errorInvalid";
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (!user.isBlocked()) {
                String username = user.getUsername();
                Role userRole = user.getRole();
                request.getSession().setAttribute("username", user.getUsername());
                request.getSession().setAttribute("role", userRole);

                LOGGER.info(String.format("Successful auth - login: %s, role: %s", username, userRole));
                return CommandResult.redirect(MAIN_PAGE_URL);
            }
            errorMessageLabel = "errorBlocked";
        }
        request.setAttribute(errorMessageLabel, errorMessageLabel);
        LOGGER.info("Invalid auth.");
        return CommandResult.forward(LOGIN_PAGE);

    }
}
