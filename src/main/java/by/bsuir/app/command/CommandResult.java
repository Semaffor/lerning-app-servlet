package by.bsuir.app.command;

/**
 * Helps to redirect and forward user between pages and actions.
 */
public class CommandResult {
    private final String page;
    private final boolean isRedirect;

    public CommandResult(String page, boolean isRedirect) {
        this.page = page;
        this.isRedirect = isRedirect;
    }

    public static CommandResult forward(String page) {
        return new CommandResult(page, false);
    }

    public static CommandResult redirect(String page) {
        return new CommandResult(page, true);
    }

    public String getPage() {
        return page;
    }

    public boolean isRedirect() {
        return isRedirect;
    }
}