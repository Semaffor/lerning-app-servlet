package by.bsuir.app.connection;

import by.bsuir.app.exception.ConnectionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private final static Logger LOGGER = LoggerFactory.getLogger(ConnectionPool.class);
    private final static int INITIAL_POOL_SIZE = 10;

    private static ConnectionPool instance;
    private final BlockingQueue<ProxyConnection> availableConnections;
    private final BlockingQueue<ProxyConnection> connectionsInUse;

    private final static Lock lock = new ReentrantLock();
    private final static ConnectionFactory connectionFactory = new ConnectionFactory();

    public ConnectionPool(BlockingQueue<ProxyConnection> availableConnections) {
        this.availableConnections = availableConnections;
        this.connectionsInUse = new ArrayBlockingQueue<>(availableConnections.size());
    }

    public static ConnectionPool getInstance() {
        ConnectionPool localInstance = instance;
        if (localInstance == null) {
            lock.lock();
            localInstance = instance;
            try {
                if (localInstance == null) {
                    BlockingQueue<ProxyConnection> proxyConnections = connectionFactory.createProxyConnections(
                            INITIAL_POOL_SIZE);
                    instance = localInstance = new ConnectionPool(proxyConnections);
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
