package by.bsuir.app.entity.enums;

public enum Status {
    ACTIVE(1),
    DISABLED(2),
    SUBMITTED(3);

    private int code;

    Status(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static Status getStatusFromCode(int code) {
        for (Status status: Status.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Incorrect status code: " + code);
    }
}
