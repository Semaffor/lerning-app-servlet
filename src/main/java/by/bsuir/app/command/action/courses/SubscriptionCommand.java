package by.bsuir.app.command.action.courses;

import by.bsuir.app.command.Command;
import by.bsuir.app.command.CommandEnum;
import by.bsuir.app.command.CommandResult;
import by.bsuir.app.entity.BaseEntity;
import by.bsuir.app.entity.User;
import by.bsuir.app.entity.UserCourse;
import by.bsuir.app.exception.ServiceException;
import by.bsuir.app.service.UserCourseService;
import by.bsuir.app.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class SubscriptionCommand implements Command {
    private static final String REDIRECT_ITEM_PAGE = "controller?command=%s&number=%d";
    private final static String FORWARD_ERROR_PAGE = "/WEB-INF/view/errors/error404.jsp";

    private static final String ATTRIBUTE_SUBSCRIBE = "subscribe";
    private static final String ATTRIBUTE_UNSUBSCRIBE = "unsubscribe";
    private final UserCourseService userCourseService;
    private final UserService userService;

    public SubscriptionCommand(UserCourseService userCourseService, UserService userService) {
        this.userCourseService = userCourseService;
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String username = (String) request.getSession().getAttribute("username");
        Optional<User> userOptional = userService.findByUsername(username);
        Long courseId = Long.valueOf(request.getParameter("courseId"));

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            String commandString = request.getParameter("command");
            CommandEnum commandType = CommandEnum.convertCommandFromString(commandString);
            boolean unsubscribe = CommandEnum.UNSUBSCRIBE.equals(commandType);

            Optional<UserCourse> subscription = userCourseService.findSubscription(user.getId(), courseId);
            Long subscriptionId = subscription.map(BaseEntity::getId).orElse(null);

            UserCourse userCourse = UserCourse.getBuilder()
                    .setId(subscriptionId)
                    .setUserId(user.getId())
                    .setCourseId(courseId)
                    .setDeleted(unsubscribe)
                    .build();

            userCourseService.changeSubscription(userCourse);
            String message = unsubscribe ? ATTRIBUTE_SUBSCRIBE : ATTRIBUTE_UNSUBSCRIBE;
            request.setAttribute(message, message);
            return CommandResult.redirect(String.format(REDIRECT_ITEM_PAGE, CommandEnum.ITEMS.getCommand(), courseId));
        }
        return CommandResult.forward(FORWARD_ERROR_PAGE);
    }
}
