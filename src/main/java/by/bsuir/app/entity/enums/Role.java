package by.bsuir.app.entity.enums;

public enum Role {
    ADMIN(1),
    USER(2),
    COUCH(3);

    private int code;

    Role(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static Role getRoleFromString(String codeString) {
        int code = Integer.parseInt(codeString);

        for (Role role: Role.values()) {
            if (role.getCode() == code) {
                return role;
            }
        }
        throw new IllegalArgumentException("Incorrect role code: " + code);
    }
}
