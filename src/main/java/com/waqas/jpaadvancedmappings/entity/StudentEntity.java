package com.waqas.jpaadvancedmappings.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "student")
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable (
            name = "course_student",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<CourseEntity> coursesList = new HashSet<>();;

    public StudentEntity() {
    }

    public StudentEntity(String name, String email) {
        this.name = name;
        this.email = email;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<CourseEntity> getCoursesList() {
        return coursesList;
    }

    public void setCoursesList(Set<CourseEntity> coursesList) {
        this.coursesList = coursesList;
    }

    public void addCourse(CourseEntity course) {
        if (coursesList == null) {
            coursesList = new HashSet<>();
        }

        coursesList.add(course);
    }

    @Override
    public String toString() {
        return "StudentEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
