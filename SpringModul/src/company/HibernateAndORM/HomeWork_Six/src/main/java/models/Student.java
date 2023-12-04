package company.HibernateAndORM.HomeWork_Six.src.main.java.models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table (name = "students")
public class Student {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int age;

    @Column (name = "registration_date")
    private Date registrationDate;

    @ManyToMany(mappedBy = "students")
    private List<Course> courses;

    public List<Course> getCourses() {
        return courses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
