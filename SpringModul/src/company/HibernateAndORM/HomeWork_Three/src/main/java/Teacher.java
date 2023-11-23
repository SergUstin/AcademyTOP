package company.HibernateAndORM.HomeWork_Three.src.main.java;

import jakarta.persistence.*;

@Entity
@Table (name = "Teachers")
public class Teacher {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private int salary;
    private int age;

}
