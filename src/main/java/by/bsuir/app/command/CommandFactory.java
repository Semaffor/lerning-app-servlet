package by.bsuir.app.command;

import by.bsuir.app.command.action.admin.*;
import by.bsuir.app.command.action.couch.*;
import by.bsuir.app.command.action.general.CoursePageCommand;
import by.bsuir.app.command.action.general.LoginCommand;
import by.bsuir.app.command.action.general.LogoutCommand;
import by.bsuir.app.command.action.general.ShowMainPageCommand;
import by.bsuir.app.command.action.user.*;
import by.bsuir.app.dao.DaoHelperFactory;
import by.bsuir.app.dao.UserTaskDao;
import by.bsuir.app.dao.impl.UserTaskDaoImpl;
import by.bsuir.app.service.DateHandler;
import by.bsuir.app.service.impl.*;

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
            case SHOW_MANAGEMENT_COURSE:
                return new ShowManageCourseCommand(new CourseServiceImpl(daoHelper));
            case SHOW_SUBMITTED_TASKS:
                return new ShowSubmittedTasksCommand(new UserTaskServiceImpl(daoHelper));
            case EDIT_COURSE:
                return new EditCourseCommand(new CourseServiceImpl(daoHelper));
            case CREATE_TASK:
                return new CreateTaskCommand(new TaskServiceImpl(daoHelper), new DateHandler());
            case CHECK_TASK:
                return new ShowCheckTaskCommand(new UserTaskServiceImpl(daoHelper));
            case SUBMIT_CHECK:
                return new SubmitTaskCheckCommand(new UserTaskServiceImpl(daoHelper));
            case SHOW_MY_COURSES:
                return new ShowMyCoursesCommand(new CourseServiceImpl(daoHelper));
            case SHOW_COURSE_TASKS:
                return new ShowAvailableCourseTasks(new TaskServiceImpl(daoHelper), new UserTaskServiceImpl(daoHelper));
            case DO_TASK:
                return new DoTaskCommand(new TaskServiceImpl(daoHelper));
            case CONFIRM_TASK:
                return new ConfirmTaskCommand(new UserTaskServiceImpl(daoHelper));
            case LOGOUT:
                return new LogoutCommand();
            default:
                throw new IllegalArgumentException("Illegal command: " + command);
        }
    }
}
