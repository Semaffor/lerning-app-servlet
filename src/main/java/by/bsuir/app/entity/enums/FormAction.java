package by.bsuir.app.entity.enums;

public enum FormAction {
    ENABLE("enable"),
    DISABLE("disable"),
    DELETE("delete"),
    RESTORE("restore");

    private final String value;

    FormAction(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static FormAction getFormActionFromString(String actionString) {
        for (FormAction format: FormAction.values()) {
            if (format.getValue().equals(actionString)) {
                return format;
            }
        }
        throw new IllegalArgumentException("Incorrect form action: " + actionString);
    }
}
