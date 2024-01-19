package com.waqas.jpaadvancedmappings.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "instructor_detail")
public class InstructorDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "youtube_channel")
    private String youtubeChannel;

    @Column(name = "youtube_channel_url", length = 300)
    private String youtubeChannelUrl;

    @Column(name = "hobby")
    private String hobby;

    @OneToOne(mappedBy = "instructorDetails",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private InstructorEntity instructor;

    public InstructorDetailsEntity() {
    }

    public InstructorDetailsEntity(String youtubeChannel, String youtubeChannelUrl, String hobby) {
        this.youtubeChannel = youtubeChannel;
        this.youtubeChannelUrl = youtubeChannelUrl;
        this.hobby = hobby;
    }

    public InstructorDetailsEntity(String youtubeChannel, String youtubeChannelUrl, String hobby, InstructorEntity instructor) {
        this.youtubeChannel = youtubeChannel;
        this.youtubeChannelUrl = youtubeChannelUrl;
        this.hobby = hobby;
        this.instructor = instructor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getYoutubeChannel() {
        return youtubeChannel;
    }

    public void setYoutubeChannel(String youtubeChannel) {
        this.youtubeChannel = youtubeChannel;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getYoutubeChannelUrl() {
        return youtubeChannelUrl;
    }

    public void setYoutubeChannelUrl(String youtubeChannelUrl) {
        this.youtubeChannelUrl = youtubeChannelUrl;
    }

    public InstructorEntity getInstructor() {
        return instructor;
    }

    public void setInstructor(InstructorEntity instructor) {
        this.instructor = instructor;
    }

    @Override
    public String toString() {
        return "InstructorDetailsEntity{" +
                "id=" + id +
                ", youtubeChannel='" + youtubeChannel + '\'' +
                ", youtubeChannelUrl='" + youtubeChannelUrl + '\'' +
                ", hobby='" + hobby + '\'' +
                '}';
    }
}
