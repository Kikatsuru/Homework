package org.example.demo.data.user;

import org.example.demo.data.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class UserService {

    protected UserService() { }

    public void persist(User user) {
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(user);
            session.flush();
            transaction.commit();
        }
    }

    public User findByLogin(String login) {
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("FROM User WHERE LOWER(login) = LOWER(:login)", User.class);
            query.setParameter("login", login);
            return query.uniqueResult();
        }
    }

    public User findByLoginAndPassword(String login, String password) {
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("FROM User WHERE login = :login AND password = :password", User.class);
            query.setParameter("login", login);
            query.setParameter("password", password);
            return query.uniqueResult();
        }
    }
}
