import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class Main1 {

    public static void main(String[] args) {
        getInformationFromCourse();
        getInformationFromPurchaselist();
        getInformationFromStudents();
        getInformationFromTeachers();
    }

    private static void getInformationFromCourse() {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfq.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();

        List<String> courses = session.createQuery("SELECT name from Course", String.class).list();

        for (String course : courses) {
            System.out.println("Course: " + course);
        }
        sessionFactory.close();
    }

    private static void getInformationFromPurchaselist() {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfq.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();

        List<String> purchaselist = session.createQuery("SELECT studentName from Purchaselist", String.class).list();

        for (String string : purchaselist) {
            System.out.println("Student: " + string);
        }
        sessionFactory.close();
    }

    private static void getInformationFromStudents() {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfq.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();

        List<String> students = session.createQuery("select name from Student", String.class).list();

        for (String string : students) {
            System.out.println("Student: " + string);
        }
        sessionFactory.close();
    }

    private static void getInformationFromTeachers() {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfq.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();

        List<String> subscriptions = session.createQuery("select name from Teacher ", String.class).list();

        for (String string : subscriptions) {
            System.out.println("Teacher: " + string);
        }
        sessionFactory.close();
    }
}
