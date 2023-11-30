package company.HibernateAndORM.HW_Hiber_Five.src.main.java.strategy;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import models.Course;
import models.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class UsingOr extends SessionFactoryBuilder implements QueryStrategy {
    @Override
    public void execute(Session session) {
        SessionFactory sessionFactory = buildSessionFactory();
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Course> query = builder.createQuery(Course.class);
        Root<Course> root = query.from(Course.class);
        Join<Course, Teacher> teacherJoin = root.join("teacher");

        query.where(
                builder.or(
                        builder.greaterThan(root.get("duration"), 40),
                        builder.greaterThan(teacherJoin.get("age"), 35)
                ));

        System.out.println("Использование OR");
        session.createQuery(query).getResultList()
                .stream()
                .map(course ->
                        "Course name: " + course.getName() +
                                " duration- " + course.getDuration() +
                                " Teacher name: " + course.getTeacher().getName() +
                                " age- " + course.getTeacher().getAge())
                .forEach(System.out::println);

        transaction.commit();
        session.close();
        sessionFactory.close();

    }
}
