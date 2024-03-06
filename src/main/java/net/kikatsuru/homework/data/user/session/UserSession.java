package net.kikatsuru.homework.data.user.session;

import jakarta.persistence.*;
import net.kikatsuru.homework.utils.Token;

import java.time.LocalDateTime;

@Entity
@Table(name = "session")
public class UserSession {
    private static final UserSessionService service = new UserSessionService();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "user_id")
    private int userId;
    private String token;
    private String mask;
    @Column(name = "creation_time")
    private LocalDateTime creationTime;
    @Column(name = "expiration_time")
    private LocalDateTime expirationTime;

    private UserSession() { }

    public UserSession(int userId, Token token) {
        this.userId = userId;
        this.token = token.getToken();
        this.mask = token.getMask();
        this.creationTime = token.getCreationTime();
        this.expirationTime = token.getExpirationTime();
    }

    public int getId() {
        return this.id;
    }

    public int getUserId() {
        return this.userId;
    }

    public String getToken() {
        return this.token;
    }

    public String getMask() {
        return this.mask;
    }

    public LocalDateTime getCreationTime() {
        return this.creationTime;
    }

    public LocalDateTime getExpirationTime() {
        return this.expirationTime;
    }

    public static UserSessionService getService() {
        return UserSession.service;
    }
}
