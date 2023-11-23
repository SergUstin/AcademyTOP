package company.HibernateAndORM.HomeWork_Three.src.main.java;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SubscriptionIdKey implements Serializable {

    public SubscriptionIdKey() {
    }

    public SubscriptionIdKey(int studentId, int courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    @Column (name = "student_id")
    private int studentId;

    @Column (name = "course_id")
    private int courseId;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubscriptionIdKey)) return false;
        SubscriptionIdKey that = (SubscriptionIdKey) o;
        return getStudentId() == that.getStudentId() && getCourseId() == that.getCourseId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStudentId(), getCourseId());
    }
}
