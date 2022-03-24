package by.bsuir.app.command.action.general;

import by.bsuir.app.command.Command;
import by.bsuir.app.command.CommandEnum;
import by.bsuir.app.command.CommandResult;
import by.bsuir.app.encoder.Utf8Handler;
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
    private final static String REDIRECT_MAIN_PAGE = "/controller?command=" + CommandEnum.SHOW_MAIN_PAGE.getCommand();
    private final static String FORWARD_LOGIN_PAGE = "/welcome.jsp";
    private final static String PARAM_NAME_LOGIN = "username";
    private final static String PARAM_NAME_PASSWORD = "password";
    private final static String PARAM_NAME_ROLE = "userRole";

    private final UserService service;

    public LoginCommand(UserService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Utf8Handler encoderHandler = new Utf8Handler();
        String login = encoderHandler.reEncode(request, PARAM_NAME_LOGIN);
        String password = encoderHandler.reEncode(request, PARAM_NAME_PASSWORD);

        Optional<User> optionalUser = service.login(login, password);

        String errorMessageLabel = "errorInvalid";
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (!user.isBlocked()) {
                String username = user.getUsername();
                Role userRole = user.getRole();
                request.getSession().setAttribute(PARAM_NAME_LOGIN, user.getUsername());
                request.getSession().setAttribute(PARAM_NAME_ROLE, userRole);

                LOGGER.info(String.format("Successful auth - login: %s, role: %s", username, userRole));
                return CommandResult.redirect(REDIRECT_MAIN_PAGE);
            }
            errorMessageLabel = "errorBlocked";
        }
        request.setAttribute(errorMessageLabel, errorMessageLabel);
        LOGGER.info("Invalid auth.");
        return CommandResult.forward(FORWARD_LOGIN_PAGE);

    }
}
