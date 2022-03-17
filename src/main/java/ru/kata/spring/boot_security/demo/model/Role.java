package ru.kata.spring.boot_security.demo.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;

    public Role() {
    }

    public Role(long id, String name) {
        this.id = id;
        this.name = name;
    }
//    @ManyToMany
//    @JoinTable(name = "users_roles"
//    , joinColumns = @JoinColumn(name = "roles_id")
//    , inverseJoinColumns = @JoinColumn(name = "users_id"))
//    private List<User> users;

    @Override
    public String getAuthority() {
        return getName();
    }
}
