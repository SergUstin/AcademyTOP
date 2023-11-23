package company.HibernateAndORM.HomeWork_Three.src.main.java;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table (name = "PurchaseList")
public class Purchase {
    @EmbeddedId
    private SubscriptionNameKey key;

    @Column (name = "student_name", insertable = false, updatable = false)
    private String studentName;

    @Column (name = "course_name", insertable = false, updatable = false)
    private String courseName;

    private int price;

    @Column(name = "subscription_date")
    private Date subscriptionDate;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courceName) {
        this.courseName = courceName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public SubscriptionNameKey getKey() {
        return key;
    }

    public void setKey(SubscriptionNameKey key) {
        this.key = key;
    }
}
