package by.bsuir.app.command;

import by.bsuir.app.command.action.*;
import by.bsuir.app.command.action.courses.*;
import by.bsuir.app.command.action.users.EditRoleCommand;
import by.bsuir.app.command.action.users.ManageUsersCommand;
import by.bsuir.app.command.action.users.ShowManageUsersCommand;
import by.bsuir.app.dao.DaoHelperFactory;
import by.bsuir.app.service.impl.CourseServiceImpl;
import by.bsuir.app.service.impl.UserCourseServiceImpl;
import by.bsuir.app.service.impl.UserServiceImpl;

public class CommandFactory {

    private final static DaoHelperFactory daoHelper = new DaoHelperFactory();

    public Command createCommand(String commandString) {
        CommandEnum command = CommandEnum.convertCommandFromString(commandString);
        switch (command) {
            case LOGIN:
                return new LoginCommand(new UserServiceImpl(daoHelper));
            case SHOW_MAIN_PAGE:
                return new ShowMainPageCommand(new CourseServiceImpl(daoHelper));
            case ITEMS:
                return new CoursePageCommand(new CourseServiceImpl(daoHelper));
            case UNSUBSCRIBE:
            case SUBSCRIBE:
                return new SubscriptionCommand(new UserCourseServiceImpl(daoHelper), new UserServiceImpl(daoHelper));
            case SHOW_MANAGEMENT_COURSES:
                return new ShowManageCoursesCommand(new CourseServiceImpl(daoHelper));
            case SHOW_MANAGEMENT_USERS:
                return new ShowManageUsersCommand(new UserServiceImpl(daoHelper));
            case MANAGEMENT_COURSES_ACTION:
                return new ManageCoursesCommand(new CourseServiceImpl(daoHelper));
            case MANAGEMENT_USERS_ACTION:
                return new ManageUsersCommand(new UserServiceImpl(daoHelper));
            case EDIT_USER_ROLE:
                return new EditRoleCommand(new UserServiceImpl(daoHelper));
            case LOGOUT:
                return new LogoutCommand();
            default:
                throw new IllegalArgumentException("Illegal command: " + command);
        }
    }
}
