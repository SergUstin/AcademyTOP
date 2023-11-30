package company.HibernateAndORM.HW_Hiber_Five.src.main.java.strategy;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import models.Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class UsingBetween extends SessionFactoryBuilder implements QueryStrategy {
    @Override
    public void execute(Session session) {
        SessionFactory sessionFactory = buildSessionFactory();
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Course> query = builder.createQuery(Course.class);
        Root<Course> root = query.from(Course.class);

        query.select(root).where(builder.between(root.get("price"), 50_000, 150_000));

        System.out.println("Использование BETWEEN");
        session.createQuery(query).getResultList()
                .stream()
                .map(course ->
                        "Course name: " + course.getName() +
                                " price: " + course.getPrice())
                .forEach(System.out::println);

        transaction.commit();
        session.close();
        sessionFactory.close();

    }
}
