import enumaration.CourseType;
import models.Course;
import models.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Отображение id для курсов и студентов
        Map<String, Integer> courseMap;
        Map<String, Integer> studentMap;
        // Конфигурация Hibernate
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        // Добавление нового курса
        // Добавьте данные о новом курсе, такие как название, описание, продолжительность,
        // стоимость и преподаватель.

//        Course course = new Course();
//        Teacher teacher = new Teacher();
//        teacher.setName("Апалков Ростислав");
//
//        course.setName("Scrum");
//        course.setDescription("Лучший курс по Scrum");
//        course.setDuration(50);
//        course.setPrice(33000);
//        course.setType(CourseType.PROGRAMMING);
//        course.setTeacher(teacher);
//
//        session.persist(course);

//     -----------------------------------------------------------------------------------------------------------------
        // Работа с преподавателями:
        // Добавьте возможность добавления, просмотра и удаления преподавателей.
        // Свяжите курсы с преподавателями, чтобы каждый курс имел преподавателя.

        // Добавить преподавателя
//        Teacher teacher = new Teacher();
//        teacher.setName("Лучший Преподаватель");
//        teacher.setAge(30);
//        teacher.setSalary(1000);
//
//        session.persist(teacher);

        // Просмотр преподавателей
//        List<Teacher> teacher = session.createQuery("FROM Teacher", Teacher.class).getResultList();
//
//        teacher.stream().map(teachers -> "Name: " + teachers.getName() +
//                " salary: " + teachers.getSalary() +
//                " age: " + teachers.getAge()).forEach(System.out::println);

        // Удаление преподавателя
//        int teacherId = 54;
//        Teacher teacher = session.get(Teacher.class, teacherId);
//
//        if (teacher != null) {
//            session.remove(teacher);
//        }


//     -----------------------------------------------------------------------------------------------------------------
        // Реализуйте вывод о студентах, зарегистрированных на курс, и общей стоимости обучения:
        // Например, вывод о студентах, зарегистрированных на курс "Java программирование",
        // с их именами, возрастами и датами регистрации.

//        String courseName = "Java-разработчик";
//
//        Course = session.createQuery("FROM models.Course where name = :name", models.Course.class)
//                .setParameter("name", courseName).uniqueResult();
//
//        if (course != null) {
//            List<models.Student> students = course.getStudent();
//            if (students.isEmpty()) {
//                System.out.println("Courses " + courseName + " not found");
//            } else {
//                System.out.println("Courses for student " + courseName + ": ");
//
//                students.forEach(student -> {
//                    String name = student.getName();
//                    int age = student.getAge();
//                    Date registrationDate = student.getRegistrationDate();
//                    System.out.println("Name: " + name + " age: " + age + " registrationDate: " + registrationDate);
//                });
//            }
//        }

//     -----------------------------------------------------------------------------------------------------------------
        transaction.commit();
        session.close();
        sessionFactory.close();
    }
}
