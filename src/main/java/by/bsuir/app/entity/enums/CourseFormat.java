package by.bsuir.app.entity.enums;

public enum CourseFormat {
    OFFLINE(1),
    ONLINE(2),
    COMBINE(3);

    private int code;

    CourseFormat(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static CourseFormat getFormatFromCode(int code) {
        for (CourseFormat format: CourseFormat.values()) {
            if (format.getCode() == code) {
                return format;
            }
        }
        throw new IllegalArgumentException("Incorrect course format code: " + code);
    }
}
