package com.waqas.jpaadvancedmappings.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "review")
public class ReviewsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "comment")
    private String comment;

    public ReviewsEntity() {
    }

    public ReviewsEntity(String comment) {
        this.comment = comment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "ReviewsEntity{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                '}';
    }
}
