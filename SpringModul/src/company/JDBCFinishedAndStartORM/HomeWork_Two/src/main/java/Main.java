package company.JDBCFinishedAndStartORM.HomeWork_Two.src.main.java;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("Все имена студентов");
        printNames("select name from Student", "Name student");
        System.out.println();
        System.out.println("Все названия курсов");
        printNames("SELECT name from Course", "Name course");
        System.out.println();
        System.out.println("Имена всех учителей");
        printNames("select name from Teacher", "Teacher name");
        System.out.println();
        System.out.println("Общее количество студентов");
        printCount("select count(id) from Student");
        System.out.println();
        System.out.println("Средний возраст студентов");
        printAverage("select avg(age) from Student");

    }

    private static SessionFactory buildSessionFactory() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfq.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        return metadata.getSessionFactoryBuilder().build();
    }

    private static void printNames(String query, String prefix) {
        SessionFactory sessionFactory = buildSessionFactory();
        Session session = sessionFactory.openSession();

        List<String> names = session.createQuery(query, String.class).list();

        for (String name : names) {
            System.out.println(prefix + ": " + name);
        }

        session.close();
        sessionFactory.close();
    }

    private static void printCount(String query) {
        SessionFactory sessionFactory = buildSessionFactory();
        Session session = sessionFactory.openSession();

        Long count = session.createQuery(query, Long.class).getSingleResult();
        System.out.println(count);

        session.close();
        sessionFactory.close();
    }

    private static void printAverage(String query) {
        SessionFactory sessionFactory = buildSessionFactory();
        Session session = sessionFactory.openSession();

        Double average = session.createQuery(query, Double.class).uniqueResult();
        System.out.println(average);

        session.close();
        sessionFactory.close();
    }
}
