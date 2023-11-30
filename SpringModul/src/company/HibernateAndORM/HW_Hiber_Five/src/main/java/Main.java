package company.HibernateAndORM.HW_Hiber_Five.src.main.java;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import strategy.*;

public class Main extends SessionFactoryBuilder {
    public static void main(String[] args) {
        // Задание 1: Простой WHERE запрос
        executeQuery(new SimpleWhereQuery());
        System.out.println();

        //Задание 2: Сложное WHERE условие
        executeQuery(new DifficultWhereQuery());
        System.out.println();

        // Задание 3: Использование параметров
        executeQuery(new UsingParameters());
        System.out.println();

        // Задание 4: Ограничение результатов
        executeQuery(new LimitingResults());
        System.out.println();

        //Задание 5: Использование OR
        executeQuery(new UsingOr());
        System.out.println();

        // Задание 6: Использование BETWEEN
        executeQuery(new UsingBetween());
        System.out.println();
    }

    private static void executeQuery(QueryStrategy queryStrategy) {
        SessionFactory sessionFactory = buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        queryStrategy.execute(session);

        transaction.commit();
        session.close();
        sessionFactory.close();
    }
}
