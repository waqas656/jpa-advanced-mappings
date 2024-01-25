package com.waqas.jpaadvancedmappings.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course")
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title", unique = true)
    private String title;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "instructor_id") //defines the name of this foreign key (and it looks inside InstructorEntity to find its primary key)
    private InstructorEntity instructor;

    @OneToMany(
    fetch = FetchType.LAZY,
    cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private List<ReviewsEntity> reviewsList;

    public CourseEntity() {
    }

    public CourseEntity(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public InstructorEntity getInstructor() {
        return instructor;
    }

    public void setInstructor(InstructorEntity instructor) {
        this.instructor = instructor;
    }

    public List<ReviewsEntity> getReviewsList() {
        return reviewsList;
    }

    public void setReviewsList(List<ReviewsEntity> reviewsList) {
        this.reviewsList = reviewsList;
    }

    public void add(ReviewsEntity review) {
        if (reviewsList == null) {
            reviewsList = new ArrayList<>();
        }

        reviewsList.add(review);
    }

    @Override
    public String toString() {
        return "CourseEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", instructor=" + instructor +
                ", reviewsList=" + reviewsList +
                '}';
    }
}
