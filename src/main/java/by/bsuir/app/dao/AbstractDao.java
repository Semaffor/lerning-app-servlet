package by.bsuir.app.dao;

import by.bsuir.app.entity.Identifiable;
import by.bsuir.app.exception.DaoException;
import by.bsuir.app.mapper.QueryGenerator;
import by.bsuir.app.mapper.RowMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class AbstractDao <T extends Identifiable> implements Dao<T> {

    private final static Logger LOGGER = LogManager.getLogger(AbstractDao.class);
    private final static QueryGenerator queryGenerator = new QueryGenerator();
    private final Connection connection;
    private final RowMapper<T> rowMapper;

    private final static String SQL_SELECT_ALL = "select * from ";

    protected AbstractDao(Connection connection, RowMapper<T> rowMapper) {
        this.connection = connection;
        this.rowMapper = rowMapper;
    }

    protected List<T> executeQuery(String query, RowMapper<T> mapper, Object... params) throws DaoException {
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

    protected Optional<T> executeForSingleResultString(String query, RowMapper<T> mapper, Object... params) throws
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
        try (PreparedStatement statement = createStatement(query, params)){
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private PreparedStatement createStatement(String query, Object... params) throws SQLException {
        LOGGER.trace("Query: " + query);
        PreparedStatement ps = connection.prepareStatement(query);

        for (int i = 1; i <= params.length; i++) {
            ps.setObject(i, params[i - 1]);             //TODO Why is here 1?
        }
        return ps;
    }

    @SuppressWarnings("unchecked")
    public List<T> getAll() throws DaoException {       //TODO 21 min, we can also create generic select by id
        String table = getTableName();
        RowMapper<T> mapper = (RowMapper<T>) RowMapper.create(table);       //TODO Think why
        return executeQuery(SQL_SELECT_ALL + table, mapper);
    }

    @Override
    public void save(T item) {
//        Map<String, Object> fields = getFields(item);
//        String query = item.getId() == null ? queryGenerator.generateInsertQuery(fields) :
//                queryGenerator.generateUpdateQuery(fields);
//        executeUpdate(query, fields.values());
//        if (item.getId() == null) {
//            String query = queryGenerator.generateInsertQuery(fields);
//        } else {
//            //update
//        }
    }
    protected abstract Map<String, Object> getFields(T item);
    protected abstract String getTableName();
}
