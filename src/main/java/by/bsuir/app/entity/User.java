package by.bsuir.app.entity;

import by.bsuir.app.entity.enums.Role;

import java.util.Objects;

/**
 * Database table mapping.
 *
 * @see BaseEntity
 */
public class User extends BaseEntity {
    private static final long serialVersionUID = 42L;

    public static final String TABLE = "user";
    public static final String NAME = "username";
    public static final String PASSWORD = "password";
    public static final String ROLE = "role";
    public static final String BLOCKED = "is_blocked";
    public static final String DELETED = "is_deleted";

    private String username;
    private String password;
    private Role role;
    private boolean blocked;

    private User() {
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

    public boolean isDeleted() {
        return deleted;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public static Builder getBuilder() {
        return new User().new Builder();
    }

    public class Builder {
        private Builder() {
        }

        public Builder setId(Long id) {
            User.this.id = id;
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

        public Builder setDeleted(boolean deleted) {
            User.this.deleted = deleted;
            return this;
        }

        public User build() {
            return User.this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        User user = (User) o;
        return blocked == user.blocked && Objects.equals(username, user.username) && Objects
                .equals(password, user.password) && role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), username, password, role, blocked);
    }
}
