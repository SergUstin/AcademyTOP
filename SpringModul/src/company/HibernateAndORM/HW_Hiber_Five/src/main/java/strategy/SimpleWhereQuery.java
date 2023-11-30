package company.HibernateAndORM.HW_Hiber_Five.src.main.java.strategy;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import models.Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class SimpleWhereQuery extends SessionFactoryBuilder implements QueryStrategy {
    @Override
    public void execute(Session session) {
        SessionFactory sessionFactory = buildSessionFactory();
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Course> query = builder.createQuery(Course.class);
        Root<Course> root = query.from(Course.class);

        query.select(root).where(builder.greaterThan(root.get("duration"), 50));

        List<Course> resultList = session.createQuery(query).getResultList();

        System.out.println("Простой WHERE запрос");
        resultList.stream()
                .map(course -> "Course: " + course.getName() + " - " + course.getDuration())
                .forEach(System.out::println);

        transaction.commit();
        session.close();
        sessionFactory.close();
    }
}
