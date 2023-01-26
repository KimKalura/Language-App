package com.spring.languageapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class FavoritePhotoList {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq")
    @SequenceGenerator(name = "category_seq",
            sequenceName = "category_seq",
            initialValue = 1,
            allocationSize = 1)
    private Long id;

    @Column
    private LocalDateTime savedDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "user-favoritePhotoList")
    private User user;

    @ManyToOne
    @JsonBackReference(value = "photoPost-favoritePhotoList")
    @JoinColumn(name= "photoPost_id")
    private PhotoPost photoPost;



    public FavoritePhotoList(){}

    public FavoritePhotoList(Long id, LocalDateTime savedDate, User user, PhotoPost photoPost) {
        this.id = id;
        this.savedDate = savedDate;
        this.user = user;
        this.photoPost = photoPost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSavedDate(LocalDateTime savedDate) {
        this.savedDate = savedDate;
    }

    public PhotoPost getPhotoPost() {
        return photoPost;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getSavedDate() {
        return savedDate;
    }


    public void setPhotoPost(PhotoPost photoPost) {
        this.photoPost = photoPost;
    }
}
