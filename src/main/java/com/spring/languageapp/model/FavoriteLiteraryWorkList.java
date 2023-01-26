package com.spring.languageapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class FavoriteLiteraryWorkList {

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
    @JsonBackReference(value = "user-favoriteLiteraryWorkList")
    private User user;

    @ManyToOne
    @JsonBackReference(value = "literaryWorkPost-favoriteLiteraryWorkList")
    @JoinColumn(name= "literaryWorkPost_id")
    private LiteraryWorkPost literaryWorkPost;




    public FavoriteLiteraryWorkList(){}

    public FavoriteLiteraryWorkList(Long id, LocalDateTime savedDate, User user, LiteraryWorkPost literaryWorkPost) {
        this.id = id;
        this.savedDate = savedDate;
        this.user = user;
        this.literaryWorkPost = literaryWorkPost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getSavedDate() {
        return savedDate;
    }

    public void setSavedDate(LocalDateTime savedDate) {
        this.savedDate = savedDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LiteraryWorkPost getLiteraryWorkPost() {
        return literaryWorkPost;
    }

    public void setLiteraryWorkPost(LiteraryWorkPost literaryWorkPost) {
        this.literaryWorkPost = literaryWorkPost;
    }
}
