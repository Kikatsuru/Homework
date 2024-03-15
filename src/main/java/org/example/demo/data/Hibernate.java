package org.example.demo.data;

import org.example.demo.data.city.City;
import org.example.demo.data.user.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Hibernate {
    private static SessionFactory sessionFactory;

    private Hibernate() { }

    public static SessionFactory getSessionFactory() {
        if (Hibernate.sessionFactory == null) {
            try (StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .applySetting("hibernate.connection.url", "jdbc:sqlite:database.db")
                    .applySetting("hibernate.hbm2ddl.auto", "update")
                    .build()) {
                Hibernate.sessionFactory = new MetadataSources(registry)
                        .addAnnotatedClasses(User.class, City.class)
                        .buildMetadata()
                        .buildSessionFactory();
            }
        }
        return Hibernate.sessionFactory;
    }

}
