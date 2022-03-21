package by.bsuir.app.command;

import by.bsuir.app.command.action.admin.*;
import by.bsuir.app.command.action.couch.*;
import by.bsuir.app.command.action.general.*;
import by.bsuir.app.command.action.user.*;
import by.bsuir.app.dao.DaoHelperFactory;
import by.bsuir.app.service.impl.*;
import by.bsuir.app.validator.DataValidator;

public class CommandFactory {

    private final DaoHelperFactory daoHelper = new DaoHelperFactory();

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
                return new EditCourseCommand(new CourseServiceImpl(daoHelper), new DataValidator());
            case CREATE_TASK:
                return new CreateTaskCommand(new TaskServiceImpl(daoHelper), new DataValidator());
            case CHECK_TASK:
                return new ShowCheckTaskCommand(new UserTaskServiceImpl(daoHelper));
            case SUBMIT_CHECK:
                return new SubmitTaskCheckCommand(new UserTaskServiceImpl(daoHelper), new DataValidator());
            case SHOW_MY_COURSES:
                return new ShowMyCoursesCommand(new CourseServiceImpl(daoHelper));
            case SHOW_COURSE_TASKS:
                return new ShowAvailableCourseTasksCommand(new TaskServiceImpl(daoHelper), new UserTaskServiceImpl(daoHelper));
            case DO_TASK:
                return new DoTaskCommand(new TaskServiceImpl(daoHelper));
            case CONFIRM_TASK:
                return new ConfirmTaskCommand(new UserTaskServiceImpl(daoHelper));
            case BUILD_URI:
                return new BuildUriCommand();
            case ACTIVATE_COURSE:
                return new ActivateCourseCommand(new CourseServiceImpl(daoHelper));
            case LOGOUT:
                return new LogoutCommand();
            default:
                throw new IllegalArgumentException("Illegal command: " + command);
        }
    }
}
