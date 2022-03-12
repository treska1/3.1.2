package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {

    List getAllUsers();

    void saveUser(User user);

    User getUserById(long id);

    void removeUser(long id);

    void updateUser(User user);
}
