package com.social.media.model;


import jakarta.persistence.*;

@Table(name = "reels")
@Entity
public class Reels {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String title;

//    private String comment;

    private String video;

    @ManyToOne
    private User user;


    public Reels() {
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

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Reels(Integer id, String title, String video, User user) {
        this.id = id;
        this.title = title;
        this.video = video;
        this.user = user;
    }
}
