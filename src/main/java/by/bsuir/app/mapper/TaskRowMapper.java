package by.bsuir.app.mapper;

import by.bsuir.app.entity.BaseEntity;
import by.bsuir.app.entity.Task;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class TaskRowMapper implements RowMapper<Task> {
    @Override
    public Task map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(BaseEntity.ID);
        String title = resultSet.getString(Task.TITLE);
        String description = resultSet.getString(Task.DESCRIPTION);
        Date deadline = resultSet.getDate(Task.DEADLINE);
        boolean deleted = resultSet.getBoolean(Task.DELETED);

        return Task.getBuilder()
                .setId(id)
                .setTitle(title)
                .setDescription(description)
                .setDeadline(deadline)
                .setDeleted(deleted)
                .build();
    }
}
