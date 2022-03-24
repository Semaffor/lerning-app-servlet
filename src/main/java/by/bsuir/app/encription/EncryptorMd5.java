package by.bsuir.app.encription;

import by.bsuir.app.connection.ConnectionPool;
import by.bsuir.app.exception.ConnectionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//TODO MAKE SAFE_THREAD
public class EncryptorMd5 implements Encryptor {
    private final static Logger LOGGER = LoggerFactory.getLogger(ConnectionPool.class);
    private final static Lock lock = new ReentrantLock();
    private static EncryptorMd5 instance;

    private static EncryptorMd5 getInstance() {
        EncryptorMd5 localInstance = instance;
        if (localInstance == null) {
            lock.lock();
            localInstance = instance;
            try {
                if (localInstance == null) {
                    instance = localInstance = new EncryptorMd5();
                }
            } catch (ConnectionException e) {
                LOGGER.error(e.getMessage(), e);
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    @Override
    public String encrypt(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
