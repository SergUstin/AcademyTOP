package company.HibernateAndORM.HomeWork_Six.src.main.java.factory;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.ListJoin;
import jakarta.persistence.criteria.Root;
import models.Course;
import models.Student;
import org.hibernate.Session;

import java.util.List;

public class JoinQuery implements QuerySelection {

//    public static void performJoinQuery(Session session) {
//        CriteriaBuilder builder = session.getCriteriaBuilder();
//        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
//        Root<Student> studentRoot = query.from(Student.class);
//        ListJoin<Student, Course> courseListJoin = studentRoot.joinList("courses");
//
//        query.multiselect(
//                studentRoot.get("name"),
//                courseListJoin.get("name"));
//
//        List<Object[]> resultList = session.createQuery(query).getResultList();
//
//        for (Object[] result : resultList) {
//            String studentName = (String) result[0];
//            String courseName = (String) result[1];
//            System.out.println("For student: " + studentName + " - " + courseName + " course");
//        }
//    }

    @Override
    public void performQuery(Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        Root<Student> studentRoot = query.from(Student.class);
        ListJoin<Student, Course> courseListJoin = studentRoot.joinList("courses");

        query.multiselect(
                studentRoot.get("name"),
                courseListJoin.get("name"));

        List<Object[]> resultList = session.createQuery(query).getResultList();

        for (Object[] result : resultList) {
            String studentName = (String) result[0];
            String courseName = (String) result[1];
            System.out.println("For student: " + studentName + " - " + courseName + " course");
        }
    }
}
