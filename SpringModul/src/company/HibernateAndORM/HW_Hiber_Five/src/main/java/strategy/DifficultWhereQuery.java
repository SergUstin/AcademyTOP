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

import java.util.List;

public class DifficultWhereQuery extends SessionFactoryBuilder implements QueryStrategy {
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
                builder.greaterThan(teacherJoin.get("age"), 30),
                builder.lessThan(root.get("duration"), 40));

        List<Course> resultList = session.createQuery(query).getResultList();

        System.out.println("Сложное WHERE условие");
        resultList.stream().map(course -> "Teacher name " + course.getTeacher().getName() + " - " +
                        "age: " + course.getTeacher().getAge() + " duration: " + course.getDuration())
                .forEach(System.out::println);

        transaction.commit();
        session.close();
        sessionFactory.close();
    }
}
