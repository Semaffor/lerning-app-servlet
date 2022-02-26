package by.bsuir.app.command;

public enum Commands {
    LOGIN("login"),
    SHOW_MAIN_PAGE("showMain");

    private String command;

    Commands(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    static Commands convertCommandFromString(String commandString) {
        for(Commands command: Commands.values()) {
            if (commandString.equals(command.getCommand())) {
                return command;
            }
        }
        throw new IllegalArgumentException("Can't convert unknown command: " + commandString);
    }
}
