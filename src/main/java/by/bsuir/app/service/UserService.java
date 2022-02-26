package by.bsuir.app.service;

import by.bsuir.app.entity.User;
import by.bsuir.app.exception.ServiceException;

import java.util.Optional;

public interface UserService {
    Optional<User> login(String login, String password) throws ServiceException;
}
