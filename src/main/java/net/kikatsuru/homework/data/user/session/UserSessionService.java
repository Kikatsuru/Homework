package net.kikatsuru.homework.data.user.session;

import net.kikatsuru.homework.data.Hibernate;
import net.kikatsuru.homework.data.user.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserSessionService {

    public UserSession findById(int id) {
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            return session.get(UserSession.class, id);
        }
    }

    public UserSession getByToken(String token)  {
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            Query<UserSession> query = session.createQuery("FROM UserSession WHERE token = :token", UserSession.class);
            query.setParameter("token", token);
            return query.uniqueResult();
        }
    }

    public void persist(UserSession userSession) {
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(userSession);
            session.flush();
            transaction.commit();
        }
    }

    public void delete(UserSession userSession) {
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(userSession);
            session.flush();
            transaction.commit();
        }
    }

    public List<UserSession> findAll() {
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            return session.createQuery("FROM UserSession", UserSession.class).getResultList();
        }
    }
}