package by.bsuir.app.command;

public enum CommandEnum {
    LOGIN("login"),
    SHOW_MAIN_PAGE("showMain"),
    SHOW_COURSE_PAGE("showCoursePage"),
    ITEMS("items"),
    SUBSCRIBE("subscribe"),
    UNSUBSCRIBE("unsubscribe"),
    SHOW_MANAGEMENT_COURSES("manageCourses"),
    SHOW_MANAGEMENT_USERS("manageUsers"),
    SHOW_MANAGEMENT_COUCHES("manageCouches"),
    MANAGEMENT_COURSES_ACTION("manageCoursesAction"),
    MANAGEMENT_USERS_ACTION("manageUsersAction"),
    EDIT_USER_ROLE("editUserRole"),
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
