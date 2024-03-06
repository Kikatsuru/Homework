package net.kikatsuru.homework.data;

import net.kikatsuru.homework.data.country.city.City;
import net.kikatsuru.homework.data.country.Country;
import net.kikatsuru.homework.data.country.language.CountryLanguage;
import net.kikatsuru.homework.data.user.User;
import net.kikatsuru.homework.data.user.session.UserSession;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Hibernate setup class.
 */
public class Hibernate {
    /**
     * Session factory.
     */
    private static SessionFactory sessionFactory;

    /**
     * Default constructor.
     */
    private Hibernate() { }

    /**
     * Get or create session factory.
     *
     * @return Session factory.
     */
    public static SessionFactory getSessionFactory() {
        if (Hibernate.sessionFactory == null) {
            try (StandardServiceRegistry registry = new StandardServiceRegistryBuilder().build()) {
                Hibernate.sessionFactory = new MetadataSources(registry)
                        .addAnnotatedClasses(Country.class, City.class, CountryLanguage.class,
                                             User.class, UserSession.class)
                        .buildMetadata()
                        .buildSessionFactory();
            }
        }
        return Hibernate.sessionFactory;
    }
}
