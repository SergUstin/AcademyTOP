package company.HibernateAndORM.HomeWork_Three.src.main.java;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table (name = "LinkedPurchaseList")
public class LinkedPurchaseList {
    public LinkedPurchaseList() {
    }

    @EmbeddedId
    private SubscriptionIdKey id;

    @Column (name = "student_id", insertable = false, updatable = false)
    private int studentId;
    @Column (name = "course_id", insertable = false, updatable = false)
    private int courseId;

    public SubscriptionIdKey getId() {
        return id;
    }

    public void setId(SubscriptionIdKey id) {
        this.id = id;
    }

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
}
