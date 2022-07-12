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
    private final static String DRIVER = "jdbc.driver";
    private final static String URL = "jdbc.url";
    private final static String NAME = "jdbc.name";
    private final static String PASSWORD = "jdbc.password";
    private final static String POOL_SIZE = "jdbc.initial_pool_size";

    private String url;
    private String name;
    private String password;
    private int initialConnectionsSize;

    public ConnectionFactory() {
        this(PROPERTIES_FILE_NAME);
    }

    public ConnectionFactory(String propertiesPath) {
        readProperties(propertiesPath);
    }

    //Replace on one
    public BlockingQueue<ProxyConnection> createProxyConnections(int connectionsCount) throws ConnectionException {
        BlockingQueue<ProxyConnection> proxyConnections = new ArrayBlockingQueue<>(connectionsCount);
        try {
            for (int i = 0; i < connectionsCount; i++) {
                proxyConnections.add(createProxyConnection());
            }
        } catch (ConnectionException | SQLException e) {
            throw new ConnectionException(e.getMessage(), e);
        }
        return proxyConnections;
    }

    public BlockingQueue<ProxyConnection> createProxyConnections() throws ConnectionException {
        return createProxyConnections(initialConnectionsSize);
    }

    private ProxyConnection createProxyConnection() throws SQLException {
        return new ProxyConnection(createConnection());
    }

    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection(url, name, password);
    }

    private Properties readProperties(String propertiesPath) throws ConnectionException {
        Properties props = new Properties();
        String driverName = null;

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propertiesPath)) {

            props.load(inputStream);

            //TODO Not here
            driverName = (props.getProperty(DRIVER));
            Class.forName(driverName);

            url = props.getProperty(URL);
            name = props.getProperty(NAME);
            password = props.getProperty(PASSWORD);
            initialConnectionsSize = Integer.parseInt(props.getProperty(POOL_SIZE));

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
