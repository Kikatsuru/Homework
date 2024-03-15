package org.example.demo.data.user;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {
    private static final UserService service = new UserService();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String login;
    private String password;

    private User() { }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public int getId() {
        return this.id;
    }

    public String getLogin() {
        return this.login;
    }

    public String getPassword() {
        return this.password;
    }

    public static UserService getService() {
        return User.service;
    }
}
