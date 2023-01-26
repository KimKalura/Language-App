package com.spring.languageapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "photoData")
public class PhotoPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "category_seq")
    @SequenceGenerator(name = "category_seq",
            sequenceName = "category_seq",
            initialValue = 1,
            allocationSize = 1)
    private Long id;

    private String name;
    private String type;
    @Column
    private String description;

    @Column(name = "photoData", length = 1000)
    private byte[] photoData;

    @Column
    private Integer numberOfLikes;


    @Column
    private Integer numberOfDislikes;

    //locatie googleMap
    //created date
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "user-photo")
    private User user;

    @OneToMany(mappedBy = "photo", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "photo-comment")
    private List<Comment> commentList;

    @OneToMany(mappedBy = "photoPost", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "photoPost-favoritePhotoListList")
    private List<FavoritePhotoList> favoritePhotoListList;


    public PhotoPost() {
    }

    public PhotoPost(Long id, String name, String type, String description, byte[] photoData, Integer numberOfLikes, Integer numberOfDislikes, User user, List<Comment> commentList, List<FavoritePhotoList> favoritePhotoListList) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
        this.photoData = photoData;
        this.numberOfLikes = numberOfLikes;
        this.numberOfDislikes = numberOfDislikes;
        this.user = user;
        this.commentList = commentList;
        this.favoritePhotoListList = favoritePhotoListList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public byte[] getPhotoData() {
        return photoData;
    }

    public void setPhotoData(byte[] photoData) {
        this.photoData = photoData;
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

    public List<FavoritePhotoList> getFavoritePhotoListList() {
        return favoritePhotoListList;
    }

    public void setFavoritePhotoListList(List<FavoritePhotoList> favoritePhotoListList) {
        this.favoritePhotoListList = favoritePhotoListList;
    }
}
