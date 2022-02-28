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

    public static CourseFormat getStatusFromCode(int code) {
        for (CourseFormat format: CourseFormat.values()) {
            if (format.getCode() == code) {
                return format;
            }
        }
        throw new IllegalArgumentException("Incorrect status code: " + code);
    }
}
