package net.kikatsuru.homework.data.user;

import net.kikatsuru.homework.data.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserService {

    public User findById(int id) {
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            return session.get(User.class, id);
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

    public void persist(User user) {
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(user);
            session.flush();
            transaction.commit();
        }
    }

    public void delete(User user) {
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(user);
            session.flush();
            transaction.commit();
        }
    }

    public List<User> findAll() {
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            return session.createQuery("FROM User", User.class).getResultList();
        }
    }
}