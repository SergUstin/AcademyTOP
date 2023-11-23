package company.HibernateAndORM.HomeWork_Three.src.main.java;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SubscriptionNameKey implements Serializable {

    public SubscriptionNameKey() {}

    public SubscriptionNameKey(String studentName, String courseName) {
        this.studentName = studentName;
        this.courseName = courseName;
    }

    @Column (name = "student_name")
    private String studentName;

    @Column (name = "course_name")
    private String courseName;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubscriptionNameKey)) return false;
        SubscriptionNameKey that = (SubscriptionNameKey) o;
        return getStudentName().equals(that.getStudentName()) && getCourseName().equals(that.getCourseName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStudentName(), getCourseName());
    }
}
