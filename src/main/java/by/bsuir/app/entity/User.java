package by.bsuir.app.entity;

import by.bsuir.app.entity.enums.Role;

public class User extends BaseEntity {

    public static final String TABLE = "user";
    public static final String NAME = "username";
    public static final String PASSWORD = "password";
    public static final String ROLE = "role";
    public static final String BLOCKED = "is_blocked";

    private String username;
    private String password;
    private Role role;
    private boolean blocked;

    private User() {}

    @Override
    public Long getId() {
        return null;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public static Builder getBuilder() {
        return new User().new Builder();
    }

    public class Builder {
        private Builder() {}

        public Builder setId(Long id) {
            User.super.setId(id);
            return this;
        }

        public Builder setUsername(String username) {
            User.this.username = username;
            return this;
        }

        public Builder setPassword(String password) {
            User.this.password = password;
            return this;
        }

        public Builder setRole(Role role) {
            User.this.role = role;
            return this;
        }

        public Builder setBlocked(boolean blocked) {
            User.this.blocked = blocked;
            return this;
        }

        public User build() {
            return User.this;
        }
    }
}
