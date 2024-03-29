package by.bsuir.app.mapper;

import by.bsuir.app.entity.BaseEntity;
import by.bsuir.app.entity.User;
import by.bsuir.app.entity.enums.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User>{
    @Override
    public User map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(BaseEntity.ID);
        String username = resultSet.getString(User.NAME);
        String password = resultSet.getString(User.PASSWORD);
        String roleString = resultSet.getString(User.ROLE);
        Role role = Role.getRoleFromString(roleString);
        boolean isBlocked = resultSet.getBoolean(User.BLOCKED);
        boolean isDeleted = resultSet.getBoolean(User.DELETED);

        return User.getBuilder()
                .setId(id)
                .setUsername(username)
                .setPassword(password)
                .setRole(role)
                .setBlocked(isBlocked)
                .setDeleted(isDeleted)
                .build();
    }
}
