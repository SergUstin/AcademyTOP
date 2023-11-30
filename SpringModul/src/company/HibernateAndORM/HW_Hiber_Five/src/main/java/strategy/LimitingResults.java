package company.HibernateAndORM.HW_Hiber_Five.src.main.java.strategy;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import models.Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class LimitingResults extends SessionFactoryBuilder implements QueryStrategy {
    @Override
    public void execute(Session session) {
        SessionFactory sessionFactory = buildSessionFactory();
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Course> query = builder.createQuery(Course.class);
        Root<Course> root = query.from(Course.class);

        query.select(root).orderBy(builder.desc(root.get("duration")));

        List<Course> resultList = session.createQuery(query).setMaxResults(5).getResultList();

        System.out.println("Ограничение результатов");
        resultList.stream().map(course -> "Course name " + course.getName() + " - " + course.getDuration())
                .forEach(System.out::println);

        transaction.commit();
        session.close();
        sessionFactory.close();

    }
}
