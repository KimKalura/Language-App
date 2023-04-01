package com.spring.languageapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "photoPost")
public class PhotoPost extends  Post{

//    private String name;
//    private String type;
    @Column
    private String description;

    @Column(name = "photoData", length = 1000)
    private byte[] photoData;

    @Column//(columnDefinition = "integer default 0")
    private Integer numberOfLikes = 0;

    @Column//(columnDefinition = "integer default 0")
    private Integer numberOfDislikes = 0;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "user-photo")
    private User user;

    @OneToMany(mappedBy = "photo", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "photo-comment")
    private List<Comment> commentList;

    @OneToMany(mappedBy = "photoPost", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "photoPost-favoritePhotoList")
    private List<FavoritePhotoList> favoritePhotoList;


    public PhotoPost() {
    }

    public PhotoPost(Long id, String description, byte[] photoData, Integer numberOfLikes, Integer numberOfDislikes, User user, List<Comment> commentList, List<FavoritePhotoList> favoritePhotoList) {
       super(id);
        this.description = description;
        this.photoData = photoData;
        this.numberOfLikes = numberOfLikes;
        this.numberOfDislikes = numberOfDislikes;
        this.user = user;
        this.commentList = commentList;
        this.favoritePhotoList = favoritePhotoList;
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

    public List<FavoritePhotoList> getFavoritePhotoList() {
        return favoritePhotoList;
    }

    public void setFavoritePhotoList(List<FavoritePhotoList> favoritePhotoList) {
        this.favoritePhotoList = favoritePhotoList;
    }
}
