package com.sturgeon.photobook.bo;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(schema = "content", name = "users")
public class User {

    @Id
    @GeneratedValue(generator = "content.user_id", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "user_id", schema = "content", sequenceName = "content.user_id", allocationSize = 1)
    private Long id;
    private String email;
    private String password;
    private String username;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(schema = "content", name = "user_roles")
    private List<Role> role;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Role> getRole() {
        return role;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }
}
