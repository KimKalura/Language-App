package com.spring.languageapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class LiteraryWorkPost {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq")
    @SequenceGenerator(name = "category_seq",
            sequenceName = "category_seq",
            initialValue = 1,
            allocationSize = 1)
    private Long id;

    @Column
    private LiteraryWorkType literaryWorkType;

    @Column
    private LanguageType originalLanguage;
    @Column
    private LocalDateTime createdDate;

    @Column
    private String title;

    @Column
    private String text;

    @Column
    private Integer numberOfLikes;

    @Column
    private Integer numberOfDislikes;


    //private Boolean isApproved;

    @OneToMany(mappedBy = "literaryWorkPost", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "literaryWorkPost-translationRomanization")
    private List<TranslationRomanization> translationRomanizationList;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "user-literaryWorkPost")
    private User user;

    @OneToMany(mappedBy = "literaryWorkPost", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "literaryWorkPost-comment")
    private List<Comment> commentList;

    @OneToMany(mappedBy = "literaryWorkPost", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "literaryWorkPost-favoriteLiteraryWorkList")
    private List<FavoriteLiteraryWorkList> favoriteLiteraryWorkList;



    public LiteraryWorkPost(){}

    public LiteraryWorkPost(Long id, LiteraryWorkType literaryWorkType, LanguageType originalLanguage, LocalDateTime createdDate, String title, String text, Integer numberOfLikes, Integer numberOfDislikes, List<TranslationRomanization> translationRomanizationList, User user, List<Comment> commentList, List<FavoriteLiteraryWorkList> favoriteLiteraryWorkList) {
        this.id = id;
        this.literaryWorkType = literaryWorkType;
        this.originalLanguage = originalLanguage;
        this.createdDate = createdDate;
        this.title = title;
        this.text = text;
        this.numberOfLikes = numberOfLikes;
        this.numberOfDislikes = numberOfDislikes;
        this.translationRomanizationList = translationRomanizationList;
        this.user = user;
        this.commentList = commentList;
        this.favoriteLiteraryWorkList = favoriteLiteraryWorkList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LiteraryWorkType getLiteraryWorkType() {
        return literaryWorkType;
    }

    public void setLiteraryWorkType(LiteraryWorkType literaryWorkType) {
        this.literaryWorkType = literaryWorkType;
    }

    public LanguageType getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(LanguageType originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String textTitle) {
        this.title = textTitle;
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

    public List<TranslationRomanization> getTranslationRomanizationList() {
        if (translationRomanizationList == null) {
            translationRomanizationList = new ArrayList<>();
        }
        return translationRomanizationList;
    }

    public void setTranslationRomanizationList(List<TranslationRomanization> translationRomanizationList) {
        this.translationRomanizationList = translationRomanizationList;
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

    public List<FavoriteLiteraryWorkList> getFavoriteLiteraryWorkList() {
        return favoriteLiteraryWorkList;
    }

    public void setFavoriteLiteraryWorkList(List<FavoriteLiteraryWorkList> favoriteLiteraryWorkList) {
        this.favoriteLiteraryWorkList = favoriteLiteraryWorkList;
    }

}
