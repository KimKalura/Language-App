package com.spring.languageapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq")
    @SequenceGenerator(name = "category_seq",
            sequenceName = "category_seq",
            initialValue = 1,
            allocationSize = 1)
    private Long id;

    @Column
    private String comment;

    @Column
    private LocalDateTime createdDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "user-comment")
    private User user;

    @ManyToOne
    @JoinColumn(name = "quote_id")
    @JsonBackReference(value = "quote_id-comment")
    private Quote quote;

    @ManyToOne
    @JoinColumn(name = "literaryWorkPost_id")
    @JsonBackReference(value = "literaryWorkPost-comment")
    private LiteraryWorkPost literaryWorkPost;

    @ManyToOne
    @JoinColumn(name = "photo_id")
    @JsonBackReference(value = "photo-comment")
    private PhotoPost photo;


    public Comment(){}

    public Comment(Long id, String comment, LocalDateTime createdDate, User user, Quote quote, LiteraryWorkPost literaryWorkPost, PhotoPost photo) {
        this.id = id;
        this.comment = comment;
        this.createdDate = createdDate;
        this.user = user;
        this.quote = quote;
        this.literaryWorkPost = literaryWorkPost;
        this.photo = photo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Quote getQuote() {
        return quote;
    }

    public void setQuote(Quote quote) {
        this.quote = quote;
    }

    public LiteraryWorkPost getLiteraryWorkPost() {
        return literaryWorkPost;
    }

    public void setLiteraryWorkPost(LiteraryWorkPost literaryWorkPost) {
        this.literaryWorkPost = literaryWorkPost;
    }

    public PhotoPost getPhoto() {
        return photo;
    }

    public void setPhoto(PhotoPost photo) {
        this.photo = photo;
    }
}
