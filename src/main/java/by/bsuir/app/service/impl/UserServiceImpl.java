package by.bsuir.app.service.impl;

import by.bsuir.app.dao.DaoHelper;
import by.bsuir.app.dao.DaoHelperFactory;
import by.bsuir.app.dao.UserDao;
import by.bsuir.app.entity.User;
import by.bsuir.app.entity.enums.Role;
import by.bsuir.app.exception.DaoException;
import by.bsuir.app.exception.ServiceException;
import by.bsuir.app.service.Service;
import by.bsuir.app.service.UserService;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl extends Service implements UserService {

    public UserServiceImpl(DaoHelperFactory daoHelperFactory) {
        super(daoHelperFactory);
    }

    @Override
    public Optional<User> login(String login, String password) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            UserDao dao = helper.createUserDao();
            Optional<User> user = dao.findUserByLoginAndPassword(login, password);
            helper.endTransaction();
            return user;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<User> findByUsername(String username) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            UserDao dao = helper.createUserDao();
            Optional<User> user = dao.findByUsername(username);
            helper.endTransaction();
            return user;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> findAll() {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            UserDao dao = helper.createUserDao();
            List<User> users = dao.getAll();
            helper.endTransaction();
            return users;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void changeIsBlockedStatus(Long userId) {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            UserDao dao = helper.createUserDao();
            Optional<User> userOptional = dao.getById(userId);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                user.setBlocked(!user.isBlocked());
                dao.save(user);
            }
            helper.endTransaction();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void changeIsDeletedStatus(Long userId) {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            UserDao dao = helper.createUserDao();
            Optional<User> userOptional = dao.getById(userId);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                user.setDeleted(!user.isBlocked());
                dao.save(user);
            }
            helper.endTransaction();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void changeRole(Long userId, Role role) {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            UserDao dao = helper.createUserDao();
            Optional<User> userOptional = dao.getById(userId);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                user.setRole(role);
                dao.save(user);
            }
            helper.endTransaction();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
