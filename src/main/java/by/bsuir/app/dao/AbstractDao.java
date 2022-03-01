package by.bsuir.app.dao;

import by.bsuir.app.entity.BaseEntity;
import by.bsuir.app.entity.Identifiable;
import by.bsuir.app.exception.DaoException;
import by.bsuir.app.mapper.RowMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public abstract class AbstractDao<T extends Identifiable> implements Dao<T> {

    private final static String SQL_SELECT_ALL = "select * from %s;";
    private final static String SQL_FIND_BY_PARAM = "select * from %s where %s = ?;";
    private final static String SQL_INSERT = "INSERT INTO %s (%s) VALUES (%s);";
    private final static String SQL_UPDATE = "UPDATE %s SET %s WHERE id=%d;";


    private final static Logger LOGGER = LogManager.getLogger(AbstractDao.class);
    private final Connection connection;
    private final RowMapper<T> mapper;
    private final String table;

    protected AbstractDao(Connection connection, RowMapper<T> mapper, String table) {
        this.connection = connection;
        this.mapper = mapper;
        this.table = table;
    }

    private PreparedStatement createStatement(String query, Object... params) throws SQLException {
        LOGGER.trace("Query: " + query);
        PreparedStatement ps = connection.prepareStatement(query);

        for (int i = 1; i <= params.length; i++) {
            ps.setObject(i, params[i - 1]);             //TODO Why is here 1?
        }
        return ps;
    }

    protected List<T> executeQuery(String query, Object... params) throws DaoException {
        try (PreparedStatement statement = createStatement(query, params);
             ResultSet resultSet = statement.executeQuery()) {
            List<T> entities = new ArrayList<>();
            while (resultSet.next()) {
                T entity = mapper.map(resultSet);
                entities.add(entity);
            }
            return entities;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    protected Optional<T> executeForSingleResultString(String query, Object... params) throws
            DaoException {
        List<T> items = executeQuery(query, mapper, params);
        if (items.size() == 1) {
            return Optional.of(items.get(0));
        } else if (items.size() > 1) {
            throw new IllegalArgumentException("More one record found.");
        } else {
            return Optional.empty();
        }
    }

    protected int executeUpdate(String query, Object... params) throws DaoException {
        try (PreparedStatement statement = createStatement(query, params)) {
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public String generateInsertQuery(Map<String, Object> fields) {
        return String.format(SQL_INSERT,
                table,
                String.join(", ", fields.keySet()),
                String.join(", ", Collections.nCopies(fields.size(), "?"))
        );
    }

    private String generateUpdateQuery(Map<String, Object> fields, Long id) {
        StringJoiner params = new StringJoiner(", ");
        for (String fieldName: fields.keySet()) {
            params.add(fieldName + "=?");
        }
        return String.format(SQL_UPDATE, table, params, id);
    }

    public List<T> getAll() throws DaoException {
        return executeQuery(String.format(SQL_SELECT_ALL, table), mapper);
    }

    @Override
    public boolean save(T item) {
        Map<String, Object> fields = getFields(item);
        Long itemId = item.getId();
        String query = itemId == null ? generateInsertQuery(fields) : generateUpdateQuery(fields, itemId);

        final int resultRowCount = executeUpdate(query, fields.values());
        return resultRowCount == 1;
    }

    public Optional<T> getById(Long id) {
        return executeForSingleResultString(String.format(SQL_FIND_BY_PARAM, table, BaseEntity.ID), id);
    }

    public boolean removeById(Long id) {
        Optional<T> entity = getById(id);
        if (entity.isPresent()) {
            executeUpdate(String.format(SQL_INSERT, table, BaseEntity.DELETED, BaseEntity.ID), true, id);
            return true;
        }
        return false;
    }

    protected abstract Map<String, Object> getFields(T item);
}
