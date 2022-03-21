package by.bsuir.app.command;

public enum CommandEnum {
    LOGIN("login"),
    SHOW_MAIN_PAGE("showMain"),
    SHOW_COURSE_PAGE("showCoursePage"),
    ITEMS("items"),
    SUBSCRIBE("subscribe"),
    UNSUBSCRIBE("unsubscribe"),
    SHOW_MANAGEMENT_COURSES("manageCourses"),
    SHOW_MANAGEMENT_COURSE("manageCourse"),
    SHOW_SUBMITTED_TASKS("checkTasks"),
    SHOW_MANAGEMENT_USERS("manageUsers"),
    SHOW_MANAGEMENT_COUCHES("manageCouches"),
    MANAGEMENT_COURSES_ACTION("manageCoursesAction"),
    MANAGEMENT_USERS_ACTION("manageUsersAction"),
    EDIT_USER_ROLE("editUserRole"),
    EDIT_COURSE("editCourse"),
    CREATE_TASK("createTask"),
    CHECK_TASK("checkTask"),
    SUBMIT_CHECK("submitCheck"),
    SHOW_MY_COURSES("showMyCourses"),
    SHOW_COURSE_TASKS("showUnconfirmedTasks"),
    DO_TASK("doTask"),
    CONFIRM_TASK("submitTask"),
    BUILD_URI("buildURI"),
    ACTIVATE_COURSE("activateCourse"),

    LOGOUT("logout");

    private final String command;

    CommandEnum(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public static CommandEnum convertCommandFromString(String commandString) {
        for(CommandEnum command: CommandEnum.values()) {
            if (commandString.equals(command.getCommand())) {
                return command;
            }
        }
        throw new IllegalArgumentException("Can't convert unknown command: " + commandString);
    }
}
