package by.bsuir.app.service;

import by.bsuir.app.entity.User;
import by.bsuir.app.entity.enums.Role;
import by.bsuir.app.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> login(String login, String password) throws ServiceException;
    Optional<User> findByUsername(String username) throws ServiceException;

    List<User> findAll();

    void changeIsBlockedStatus(Long userId);

    void changeIsDeletedStatus(Long userId);

    void changeRole(Long userId, Role role);
}
