package org.example.demo.data.city;

import org.example.demo.data.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CityService {

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

    public List<City> findAll() {
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            return session.createQuery("FROM City", City.class).getResultList();
        }
    }

}
