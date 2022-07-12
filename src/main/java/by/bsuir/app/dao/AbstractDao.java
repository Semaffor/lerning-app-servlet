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
import java.text.ParseException;
import java.util.*;

public abstract class AbstractDao<T extends Identifiable> implements Dao<T> {

    private final static String SQL_SELECT_ALL = "select * from %s;";
    private final static String SQL_SELECT_BY_PARAM = "select * from %s where %s=?;";
    private final static String SQL_SELECT_BY_PARAMS = "select * from %s where %s;";
    private final static String SQL_INSERT = "insert into %s (%s) values (%s);";
    private final static String SQL_UPDATE = "update %s set %s where id=%d;";
    private final static String SQL_ALIAS = "%s.%s as %s";
    protected final static String SQL_FIND_WHERE = "select * from %s where %s=?";


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
            ps.setObject(i, params[i - 1]);
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
        } catch (SQLException | ParseException e) {
            throw new DaoException(e);
        }
    }

    protected Optional<T> executeForSingleResultString(String query, Object... params) throws
            DaoException {
        List<T> items = executeQuery(query, params);
        if (items.size() == 1) {
            return Optional.of(items.get(0));
        } else if (items.size() > 1) {
            throw new IllegalArgumentException("More one record found.");
        } else {
            return Optional.empty();
        }
    }

    protected int executeForSingleResultInt(String query, Object... params) throws DaoException {
        try (PreparedStatement statement = createStatement(query, params);
             ResultSet resultSet = statement.executeQuery()) {
            resultSet.next();
            return resultSet.getInt("count");
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    protected int executeUpdate(String query, Object... params) throws DaoException {
        try (PreparedStatement statement = createStatement(query, params)) {
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    protected String generateInsertQuery(Map<String, Object> fields) {
        LOGGER.trace("Insert row in table: " + table);
        return String.format(SQL_INSERT,
                table,
                String.join(", ", fields.keySet()),
                String.join(", ", Collections.nCopies(fields.size(), "?"))
        );
    }

    protected String generateUpdateQuery(Map<String, Object> fields, Long id) {
        LOGGER.trace("Update row in table: " + table);
        StringJoiner params = generateConditionFields(fields.keySet(), ", ");
        return String.format(SQL_UPDATE, table, params, id);
    }

    protected String generateSingleAlias(String rowName, String aliasTablePrefix) {
        return String.format(SQL_ALIAS, aliasTablePrefix, rowName, rowName);
    }

    protected String generateAliases(String aliasTablePrefix, String... fieldNames) {
        LOGGER.trace("Generate aliases for prefix: " + aliasTablePrefix);
        StringJoiner aliases = new StringJoiner(", ");
        for (String fieldName : fieldNames) {
            aliases.add(generateSingleAlias(fieldName, aliasTablePrefix));
        }
        return aliases.toString();
    }

    protected String generateSelectQuery(Set<String> fields) {
        LOGGER.trace("Selecting all fields from table: " + table);
        StringJoiner params = generateConditionFields(fields, " and ");
        return String.format(SQL_SELECT_BY_PARAMS, table, params);
    }

    private StringJoiner generateConditionFields(Set<String> fields, String delimiter) {
        StringJoiner params = new StringJoiner(delimiter);
        for (String fieldName : fields) {
            params.add(fieldName + "=?");
        }
        return params;
    }

    public List<T> getAll() throws DaoException {
        return executeQuery(String.format(SQL_SELECT_ALL, table));
    }

    @Override
    public boolean save(T item) {
        Map<String, Object> fields = getFields(item);
        Long itemId = item.getId();
        String query = itemId == null ? generateInsertQuery(fields) : generateUpdateQuery(fields, itemId);

        final int resultRowCount = executeUpdate(query, fields.values().toArray());
        return resultRowCount == 1;
    }

    public Optional<T> getById(Long id) {
        LOGGER.trace(String.format("Get row with id = %d from table = %s", id, table));
        return executeForSingleResultString(String.format(SQL_SELECT_BY_PARAM, table, BaseEntity.ID), id);
    }

    public boolean removeById(Long id) {
        LOGGER.trace("Trying to delete row with id = " + id);
        Optional<T> entity = getById(id);
        if (entity.isPresent()) {
            executeUpdate(String.format(SQL_INSERT, table, BaseEntity.DELETED, BaseEntity.ID), true, id);
            return true;
        }
        return false;
    }

    protected abstract Map<String, Object> getFields(T item);
}
