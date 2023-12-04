package company.HibernateAndORM.HomeWork_Six.src.main.java.factory;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.ListJoin;
import jakarta.persistence.criteria.Root;
import models.Course;
import models.Student;
import org.hibernate.Session;

import java.util.List;

public class AggregatedFunction implements QuerySelection {

//    public static void performAggregatedFunction(Session session) {
//        CriteriaBuilder builder = session.getCriteriaBuilder();
//        CriteriaQuery<Object[]> courseQuery = builder.createQuery(Object[].class);
//        Root<Course> courseRoot = courseQuery.from(Course.class);
//        ListJoin<Course, Student> studentListJoin = courseRoot.joinList("students");
//
//
//        courseQuery.multiselect(
//                        courseRoot.get("name"),
//                        builder.count(studentListJoin))
//                .groupBy(courseRoot.get("name"));
//
//
//        List<Object[]> resultList = session.createQuery(courseQuery).getResultList();
//
//        for (Object[] result : resultList) {
//            String courseName = (String) result[0];
//            long countStudent = (long) result[1];
//            System.out.println("On course: " + courseName + " - " + countStudent + " students");
//        }
//    }

    @Override
    public void performQuery(Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> courseQuery = builder.createQuery(Object[].class);
        Root<Course> courseRoot = courseQuery.from(Course.class);
        ListJoin<Course, Student> studentListJoin = courseRoot.joinList("students");


        courseQuery.multiselect(
                        courseRoot.get("name"),
                        builder.count(studentListJoin))
                .groupBy(courseRoot.get("name"));


        List<Object[]> resultList = session.createQuery(courseQuery).getResultList();

        for (Object[] result : resultList) {
            String courseName = (String) result[0];
            long countStudent = (long) result[1];
            System.out.println("On course: " + courseName + " - " + countStudent + " students");
        }

    }
}
