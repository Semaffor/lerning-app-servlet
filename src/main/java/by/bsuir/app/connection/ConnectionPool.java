package by.bsuir.app.connection;

import by.bsuir.app.exception.ConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private static final int INITIAL_POOL_SIZE = 10;

    private static ConnectionPool INSTANCE;
    private final BlockingQueue<ProxyConnection> availableConnections;
    private final BlockingQueue<ProxyConnection> connectionsInUse;

    private static final Lock lock = new ReentrantLock();
    private static final ConnectionFactory connectionFactory = new ConnectionFactory();

    public ConnectionPool(BlockingQueue<ProxyConnection> availableConnections) {
        this.availableConnections = availableConnections;
        this.connectionsInUse = new ArrayBlockingQueue<>(availableConnections.size());
    }

    public static ConnectionPool getInstance() {
        ConnectionPool localInstance = INSTANCE;
        if (localInstance == null) {
            lock.lock();
            localInstance = INSTANCE;
            try {
                if (localInstance == null) {
                    BlockingQueue<ProxyConnection> proxyConnections = connectionFactory.createProxyConnections(
                            INITIAL_POOL_SIZE);
                    INSTANCE = localInstance = new ConnectionPool(proxyConnections);
                }
            } catch (ConnectionException e) {
                LOGGER.error(e.getMessage(), e);
            } finally {
                lock.unlock();
            }
        }
        return localInstance;
    }

    public void returnConnection(ProxyConnection proxyConnection) {
        lock.lock();
        try {
            if (connectionsInUse.contains(proxyConnection)) {
                availableConnections.add(proxyConnection);
                connectionsInUse.remove(proxyConnection);
            }
        } finally {
            lock.unlock();
        }
    }

    public ProxyConnection getConnection() {
        lock.lock();
        ProxyConnection connection = null;
        try {
                connection = availableConnections.take();
                connectionsInUse.put(connection);
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            lock.unlock();
        }
        return connection;
    }
}
