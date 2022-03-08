package by.bsuir.app.mapper;

import by.bsuir.app.entity.Course;
import by.bsuir.app.entity.Identifiable;
import by.bsuir.app.entity.Task;
import by.bsuir.app.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T extends Identifiable> {
    T map(ResultSet resultSet) throws SQLException;

    static RowMapper<? extends Identifiable> create(String table) {
        switch (table) {
            case User.TABLE:
                return new UserRowMapper();
            case Task.TABLE:
                return new TaskRowMapper();
            case Course.TABLE:
                return new CourseRowMapper();
            default:
                throw new IllegalArgumentException("Unknown table = " + table);
        }
    }
}
