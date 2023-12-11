package com.example.configurate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateTemplate {
    public static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public static <T> T performDatabaseOperation(DatabaseOperation<T> operation) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                T result = operation.execute(session);
                transaction.commit();
                return result;
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                throw e;
            }
        }
    }

    public interface DatabaseOperation<T> {
        T execute(Session session);
    }
}
