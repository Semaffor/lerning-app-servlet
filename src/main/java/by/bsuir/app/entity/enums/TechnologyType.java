package by.bsuir.app.entity.enums;

public enum TechnologyType {
    JAVA(1, "Java"),
    C(2, "C"),
    C__(3, "C++"),
    PYTHON(4, "Python"),
    GOLAND(5, "Goland");

    private int code;
    private String decodingValue;

    TechnologyType(int code, String decodingValue) {
        this.code = code;
        this.decodingValue = decodingValue;
    }

    public int getCode() {
        return code;
    }

    public String getDecodingValue() {
        return decodingValue;
    }

    public static TechnologyType getTechnologyTypeFromCode(int code) {
        for (TechnologyType type: TechnologyType.values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        throw new IllegalArgumentException("Incorrect technology type code: " + code);
    }
}
