package by.bsuir.app.filter;

import by.bsuir.app.command.CommandEnum;
import by.bsuir.app.entity.enums.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class AuthFilter implements Filter {

    private static final String REDIRECT_MAIN_PAGE = "controller?command=" + CommandEnum.SHOW_MAIN_PAGE.getCommand();
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthFilter.class);
    private final Set<CommandEnum> commonCommands = new HashSet<>();
    private final Set<CommandEnum> userCommands = new HashSet<>();
    private final Set<CommandEnum> adminCommands = new HashSet<>();
    private final Set<CommandEnum> couchCommands = new HashSet<>();

    public void init(FilterConfig fConfig) throws ServletException {
        commonCommands.addAll(Arrays.asList(
                CommandEnum.LOGIN,
                CommandEnum.SHOW_MAIN_PAGE,
                CommandEnum.BUILD_URI));

        userCommands.addAll(commonCommands);
        userCommands.addAll(Arrays.asList(
                CommandEnum.LOGOUT,
                CommandEnum.SHOW_COURSE_PAGE,
                CommandEnum.SUBSCRIBE,
                CommandEnum.UNSUBSCRIBE,
                CommandEnum.SHOW_MY_COURSES,
                CommandEnum.SHOW_COURSE_TASKS,
                CommandEnum.DO_TASK,
                CommandEnum.CONFIRM_TASK,
                CommandEnum.LOGOUT
        ));

        adminCommands.addAll(userCommands);
        adminCommands.addAll(Arrays.asList(
                CommandEnum.SHOW_MANAGEMENT_COUCHES,
                CommandEnum.SHOW_MANAGEMENT_COURSES,
                CommandEnum.SHOW_MANAGEMENT_USERS,
                CommandEnum.MANAGEMENT_USERS_ACTION,
                CommandEnum.EDIT_USER_ROLE,
                CommandEnum.MANAGEMENT_COURSES_ACTION
        ));

        couchCommands.addAll(userCommands);
        couchCommands.addAll(Arrays.asList(
                CommandEnum.SHOW_MANAGEMENT_COURSE,
                CommandEnum.SHOW_SUBMITTED_TASKS,
                CommandEnum.SHOW_MANAGEMENT_COURSE,
                CommandEnum.EDIT_COURSE,
                CommandEnum.CREATE_TASK,
                CommandEnum.ITEMS,
                CommandEnum.CHECK_TASK,
                CommandEnum.SUBMIT_CHECK,
                CommandEnum.ACTIVATE_COURSE
        ));
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        HttpSession session = httpServletRequest.getSession();
        String commandString = request.getParameter("command");

        boolean isAvailable = true;
        Role userRole = null;


        try {
            if (commandString != null) {
                CommandEnum command = CommandEnum.convertCommandFromString(commandString);
                if (!commonCommands.contains(command)) {
                    userRole = (Role) session.getAttribute("userRole");
                    isAvailable = checkCommandAccessByRole(userRole, command);
                }
            }

            if (isAvailable) {
                chain.doFilter(request, response);
            } else {
                LOGGER.info(String.format("Unauthorized access attempt role %s, command %s.",
                        userRole, commandString));
                httpServletResponse.sendRedirect(REDIRECT_MAIN_PAGE);
            }
        } catch (IllegalArgumentException e) {
            LOGGER.error("Trying to use incorrect command: " + commandString);
            httpServletResponse.sendRedirect(REDIRECT_MAIN_PAGE);
        }

    }

    private boolean checkCommandAccessByRole(Role role, CommandEnum command) {
        if (role != null) {
            switch (role) {
                case USER:
                    return userCommands.contains(command);
                case ADMIN:
                    return adminCommands.contains(command);
                case COUCH:
                    return couchCommands.contains(command);
            }
        }
        return false;
    }
}


