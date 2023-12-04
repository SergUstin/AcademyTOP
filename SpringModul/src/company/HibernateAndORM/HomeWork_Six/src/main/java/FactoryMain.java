package company.HibernateAndORM.HomeWork_Six.src.main.java;

import factory.*;
import org.hibernate.Session;

public class FactoryMain {
    public static void main(String[] args) {
        DatabaseSessionFactory sessionFactory = createDatabaseSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            System.out.println("Получение количества студентов на каждом курсе");
            QuerySelectionFactory querySelectionFactory = createQueryByQuery("Получение количества студентов на каждом курсе");
            QuerySelection querySelection = querySelectionFactory.createQuery();
            querySelection.performQuery(session);
            System.out.println();

            System.out.println("Получение списка курсов для конкретного студента");
            QuerySelectionFactory querySelectionFactory1 = createQueryByQuery("Получение списка курсов для конкретного студента");
            QuerySelection querySelection1 = querySelectionFactory1.createQuery();
            querySelection1.performQuery(session);
        }
    }

    private static DatabaseSessionFactory createDatabaseSessionFactory() {
        return new HibernateDatabaseSessionFactory("hibernate.cfg.xml");
    }

    static QuerySelectionFactory createQueryByQuery(String string) {
        if (string.equalsIgnoreCase("Получение количества студентов на каждом курсе")) {
            return new AggregatedFunctionFactory();
        } else if (string.equalsIgnoreCase("Получение списка курсов для конкретного студента")) {
            return new JoinQueryFactory();
        } else {
            throw new RuntimeException("Command " + string + " was not recognized!");
        }
    }
}
