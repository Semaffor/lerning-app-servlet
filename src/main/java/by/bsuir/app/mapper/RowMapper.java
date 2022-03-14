package by.bsuir.app.mapper;

import by.bsuir.app.entity.Identifiable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

public interface RowMapper<T extends Identifiable> {
    T map(ResultSet resultSet) throws SQLException, ParseException;
}
