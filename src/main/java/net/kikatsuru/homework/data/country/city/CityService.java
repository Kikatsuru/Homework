package net.kikatsuru.homework.data.country.city;

import net.kikatsuru.homework.data.Hibernate;
import net.kikatsuru.homework.data.country.Country;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CityService {

    public City findById(int id) {
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            return session.get(City.class, id);
        }
    }

    public void merge(City city) {
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(city);
            session.flush();
            transaction.commit();
        }
    }

    public void delete(City user) {
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(user);
            session.flush();
            transaction.commit();
        }
    }

    public List<City> find(int offset, int count) {
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            return session.createQuery("FROM City", City.class)
                    .setFirstResult(offset)
                    .setMaxResults(count)
                    .getResultList();
        }
    }

    public List<City> findAll() {
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            return session.createQuery("FROM City", City.class).getResultList();
        }
    }
}
