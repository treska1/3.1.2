package ru.kata.spring.boot_security.demo.userDAO;

import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class RoleDAOImpl implements RoleDAO{

    @PersistenceContext
    private EntityManager manager;



    @Override
    public List<Role> getAllRoles() {
        return manager.createQuery("FROM * Role",Role.class).getResultList();
    }

    @Override
    public Role getRoleById(long id) {
        return manager.find(Role.class,id);
    }
}
