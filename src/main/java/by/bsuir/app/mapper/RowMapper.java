package by.bsuir.app.mapper;

import by.bsuir.app.entity.Identifiable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

/**
 * The interface concerned with creating objects from ResultSets got from executing SQL scripts
 *
 * @param <T> class of object to be created
 */
public interface RowMapper<T extends Identifiable> {
    T map(ResultSet resultSet) throws SQLException, ParseException;
}
