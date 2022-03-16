package ru.kata.spring.boot_security.demo.userDAO;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {
    @PersistenceContext
    private EntityManager manager;

    private final PasswordEncoder encoder;

    public UserDAOImpl(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public void updateUser(User user) {
        manager.merge(user);
    }

    @Override
    public List<User> getAllUsers() {
        return manager.createQuery("FROM User", User.class).getResultList();
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setUsername(user.getUsername());
        manager.persist(user);
    }

    @Override
    public User getUserById(long id) {
        return manager.find(User.class, id);
    }

    @Override
    public void removeUser(long id) {
        manager.remove(manager.find(User.class, id));
    }

    @Override
    public User getUserByUsername(String username) {
        Query query = manager.createQuery("select u from User u where u.username = :username", User.class)
                .setParameter("username", username);
        return (User) query.getSingleResult();
    }
}
