package com.spring.languageapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq")
    @SequenceGenerator(name = "category_seq",
            sequenceName = "category_seq",
            initialValue = 1,
            allocationSize = 1)
    private Long id;

    @Column
    private String text;

    @Column
    private Integer numberOfLikes;

    @Column
    private Integer numberOfDislikes;

    @Column
    private Boolean isApproved;


    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "user-quote")
    private User user;

    @OneToMany(mappedBy = "quote", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "quote-comment")
    private List<Comment> commentList;


    public Quote(){}

    public Quote(Long id, String text, Integer numberOfLikes, Integer numberOfDislikes, Boolean isApproved, User user, List<Comment> commentList) {
        this.id = id;
        this.text = text;
        this.numberOfLikes = numberOfLikes;
        this.numberOfDislikes = numberOfDislikes;
        this.isApproved = isApproved;
        this.user = user;
        this.commentList = commentList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getNumberOfLikes() {
        return numberOfLikes;
    }

    public void setNumberOfLikes(Integer numberOfLikes) {
        this.numberOfLikes = numberOfLikes;
    }

    public Integer getNumberOfDislikes() {
        return numberOfDislikes;
    }

    public void setNumberOfDislikes(Integer numberOfDislikes) {
        this.numberOfDislikes = numberOfDislikes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public Boolean getApproved() {
        return isApproved;
    }

    public void setApproved(Boolean approved) {
        isApproved = approved;
    }
}
