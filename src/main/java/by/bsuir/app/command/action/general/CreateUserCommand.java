package by.bsuir.app.command.action.general;

import by.bsuir.app.command.Command;
import by.bsuir.app.command.CommandResult;
import by.bsuir.app.encoder.EncoderHandler;
import by.bsuir.app.encription.EncryptorMd5;
import by.bsuir.app.entity.User;
import by.bsuir.app.entity.enums.Role;
import by.bsuir.app.exception.IncorrectPasswordException;
import by.bsuir.app.exception.ServiceException;
import by.bsuir.app.service.UserService;
import by.bsuir.app.validator.DataValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class CreateUserCommand implements Command {
    private final EncoderHandler encoderHandler = new EncoderHandler();
    private final static String FORWARD_REGISTRATION_PAGE = "/controller?command=registration";
    private final static String SUCCESS_ATTRIBUTE = "registration=true";
    private final static String ALREADY_EXISTS = "alreadyExists=true";
    private final static String PASSWORD_ERROR = "invalidPassword=true";
    private final DataValidator dataValidator = new DataValidator();
    private final UserService userService;

    public CreateUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String attribute = null;
        try {
            String username = encoderHandler.reEncode(request, "username");
            String password = encoderHandler.reEncode(request, "password");
            String repeatedPassword = encoderHandler.reEncode(request, "repeatedPassword");

            dataValidator.checkPasswords(password, repeatedPassword);
            Optional<User> userOptional = userService.findByUsername(username);

            if (userOptional.isPresent()) {
                throw new IllegalArgumentException();
            }
            //TODO NOT SAFE_THREAD
            EncryptorMd5 encryptorMd5 = new EncryptorMd5();
            User user = User.getBuilder()
                    .setUsername(username)
                    .setPassword(encryptorMd5.encrypt(password))
                    .setRole(Role.USER)
                    .build();

            userService.createUser(user);
            return CommandResult.redirect("/" + "?" + SUCCESS_ATTRIBUTE);

        } catch (IncorrectPasswordException e) {
            attribute=PASSWORD_ERROR;
        } catch (IllegalArgumentException e) {
            attribute=ALREADY_EXISTS;
        }
        return CommandResult.redirect(FORWARD_REGISTRATION_PAGE + "&" + attribute);
    }
}
