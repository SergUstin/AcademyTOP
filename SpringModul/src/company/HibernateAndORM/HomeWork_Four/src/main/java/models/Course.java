package models;

import java.util.List;

import enumaration.CourseType;
import jakarta.persistence.*;

@Entity
@Table (name = "Courses")
public class Course {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private  Integer id;
    private  String name;
    private  Integer duration;
    @Enumerated (EnumType.STRING)
    @Column (columnDefinition = "enum")
    private CourseType type;
    private String description;

    @ManyToOne (cascade = CascadeType.ALL)
    private Teacher teacher;
    @Column(name = "students_count", nullable = true)
    private Integer studentsCount;
    private Integer price;
    @Column(name = "price_per_hour")
    private Float pricePerHour;

    @ManyToMany
    @JoinTable (name = "Subscriptions",
            joinColumns = {@JoinColumn(name = "course_id")},
            inverseJoinColumns = {@JoinColumn(name = "student_id")}
    )
    private List<Student> student;

    @OneToMany(mappedBy = "Teachers", cascade = CascadeType.REMOVE)
    private List<Course> courses;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public CourseType getType() {
        return type;
    }

    public void setType(CourseType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Integer getStudentsCount() {
        return studentsCount;
    }

    public void setStudentsCount(Integer studentsCount) {
        this.studentsCount = studentsCount;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Float getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(Float pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public List<Student> getStudent() {
        return student;
    }

    public void setStudent(List<Student> students) {
        this.student = students;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
