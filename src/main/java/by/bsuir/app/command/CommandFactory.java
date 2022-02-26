package by.bsuir.app.command;

import by.bsuir.app.command.action.LoginCommand;
import by.bsuir.app.command.action.ShowMainPageCommand;
import by.bsuir.app.dao.DaoHelperFactory;
import by.bsuir.app.service.UserServiceImpl;

public class CommandFactory {

    public Command createCommand(String commandString) {
        Commands command = Commands.convertCommandFromString(commandString);
        switch (command) {
            case LOGIN:
                return new LoginCommand(new UserServiceImpl(new DaoHelperFactory()));
            case SHOW_MAIN_PAGE:
                return new ShowMainPageCommand();
            default:
                throw new IllegalArgumentException("Illegal command: " + command);
        }
    }
}
