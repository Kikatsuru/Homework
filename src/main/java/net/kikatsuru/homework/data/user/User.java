package net.kikatsuru.homework.data.user;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {
    private static final UserService service = new UserService();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "login")
    private String login;
    private String password;

    private User() { }

    public User(String name, String password) {
        this.login = name.toLowerCase();
        this.password = password;
    }

    public String getName() {
        return this.login;
    }

    public String getPassword() {
        return this.password;
    }

    public int getId() {
        return this.id;
    }

    public static UserService getService() {
        return User.service;
    }
}
