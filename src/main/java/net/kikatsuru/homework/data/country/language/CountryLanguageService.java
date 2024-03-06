package net.kikatsuru.homework.data.country.language;

import net.kikatsuru.homework.data.Hibernate;
import net.kikatsuru.homework.data.country.city.City;
import net.kikatsuru.homework.data.country.language.columns.CountryLanguageId;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CountryLanguageService {

    public CountryLanguage findById(CountryLanguageId id) {
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            return session.get(CountryLanguage.class, id);
        }
    }

    public void merge(CountryLanguage language) {
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(language);
            session.flush();
            transaction.commit();
        }
    }

    public void delete(CountryLanguage countryLanguage) {
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(countryLanguage);
            session.flush();
            transaction.commit();
        }
    }

    public List<CountryLanguage> find(int offset, int count) {
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            return session.createQuery("FROM CountryLanguage", CountryLanguage.class)
                    .setFirstResult(offset)
                    .setMaxResults(count)
                    .getResultList();
        }
    }

    public List<CountryLanguage> findAll() {
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            return session.createQuery("FROM CountryLanguage", CountryLanguage.class).getResultList();
        }
    }
}
