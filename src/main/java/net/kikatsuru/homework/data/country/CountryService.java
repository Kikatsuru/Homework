package net.kikatsuru.homework.data.country;

import net.kikatsuru.homework.data.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CountryService {

    public Country findById(int id) {
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            return session.get(Country.class, id);
        }
    }

    public void merge(Country country) {
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(country);
            session.flush();
            transaction.commit();
        }
    }

    public void delete(Country country) {
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(country);
            session.flush();
            transaction.commit();
        }
    }

    public List<Country> find(int offset, int count) {
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            return session.createQuery("FROM Country", Country.class)
                    .setFirstResult(offset)
                    .setMaxResults(count)
                    .getResultList();
        }
    }

    public List<Country> findAll() {
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            return session.createQuery("FROM Country", Country.class)
                    .getResultList();
        }
    }
}
