package by.bsuir.app.connection;

import by.bsuir.app.exception.ConnectionException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionFactory {

    private final static String PROPERTIES_FILE_NAME = "db.properties";
    private String url;
    private String name;
    private String password;
    private int initialConnectionsSize;

    public BlockingQueue<ProxyConnection> createProxyConnections(int connectionsCount) throws
            ConnectionException {
        BlockingQueue<ProxyConnection> proxyConnections = new ArrayBlockingQueue<>(connectionsCount);
        try {
            readProperties();
            for (int i = 0; i < connectionsCount; i++) {
                proxyConnections.add(createProxyConnection());
            }
        } catch (ConnectionException | SQLException e) {
            throw new ConnectionException(e.getMessage(), e);
        }
        return proxyConnections;
    }

    public BlockingQueue<ProxyConnection> createProxyConnections() throws
            ConnectionException {
        return createProxyConnections(initialConnectionsSize);
    }

    protected ProxyConnection createProxyConnection() throws SQLException {
        return new ProxyConnection(createConnection());
    }

    protected Connection createConnection() throws SQLException {
        return DriverManager.getConnection(url, name, password);
    }

    protected Properties readProperties() throws ConnectionException {
        return readProperties(PROPERTIES_FILE_NAME);
    }
    protected Properties readProperties(String propertiesPath) throws ConnectionException {
        Properties props = new Properties();
        String driverName = null;

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propertiesPath)) {

            props.load(inputStream);
            driverName = (props.getProperty("jdbc.driver"));
            Class.forName(driverName);

            url = props.getProperty("jdbc.url");
            name = props.getProperty("jdbc.name");
            password = props.getProperty("jdbc.password");
            initialConnectionsSize = Integer.parseInt(props.getProperty("jdbc.initial_pool_size"));

            return props;
        } catch (ClassNotFoundException e) {
            throw new ConnectionException("Cannot find driver: " + driverName, e);
        } catch (FileNotFoundException e) {
            throw new ConnectionException("File doesn't exist: " + PROPERTIES_FILE_NAME, e);
        } catch (IOException e) {
            throw new ConnectionException(e.getMessage(), e);
        }
    }
}
