package com.spring.languageapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq")
    @SequenceGenerator(name = "category_seq",
            sequenceName = "category_seq",
            initialValue = 1,
            allocationSize = 1)
    private Long id;

    @Column
    private String username;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String country;

    @Column
    private String nationality;

    @Column
    private String nativeLanguage;

    @Column
    private String practicedLanguage;

    @Column
    private LocalDate dateOfBirth;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "user-literaryWorkPost")
    private List<LiteraryWorkPost> literaryWorkPostList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "user-photo")
    private List<PhotoPost> photoList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "user-favoritePhotoList")
    private List<FavoritePhotoList> favoritePhotoList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "user-favoriteLiteraryWorkList")
    private List<FavoriteLiteraryWorkList> favoriteLiteraryWorkList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "user-quote")
    private List<Quote> quoteList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "user-comment")
    private List<Comment> commentList;

    @ManyToMany
    @JsonIgnoreProperties("userList")
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roleList;



    public User() {}

    public User(Long id, String username, String email, String password, String country, String nationality, String nativeLanguage, String practicedLanguage, LocalDate dateOfBirth, List<LiteraryWorkPost> literaryWorkPostList, List<PhotoPost> photoList, List<FavoritePhotoList> favoritePhotoList, List<FavoriteLiteraryWorkList> favoriteLiteraryWorkList, List<Quote> quoteList, List<Comment> commentList, List<Role> roleList) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.country = country;
        this.nationality = nationality;
        this.nativeLanguage = nativeLanguage;
        this.practicedLanguage = practicedLanguage;
        this.dateOfBirth = dateOfBirth;
        this.literaryWorkPostList = literaryWorkPostList;
        this.photoList = photoList;
        this.favoritePhotoList = favoritePhotoList;
        this.favoriteLiteraryWorkList = favoriteLiteraryWorkList;
        this.quoteList = quoteList;
        this.commentList = commentList;
        this.roleList = roleList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNativeLanguage() {
        return nativeLanguage;
    }

    public void setNativeLanguage(String nativeLanguage) {
        this.nativeLanguage = nativeLanguage;
    }

    public String getPracticedLanguage() {
        return practicedLanguage;
    }

    public void setPracticedLanguage(String practicedLanguage) {
        this.practicedLanguage = practicedLanguage;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<LiteraryWorkPost> getLiteraryWorkPostList() {
        return literaryWorkPostList;
    }

    public void setLiteraryWorkPostList(List<LiteraryWorkPost> literaryWorkPostList) {
        this.literaryWorkPostList = literaryWorkPostList;
    }

    public List<PhotoPost> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<PhotoPost> imageList) {
        this.photoList = imageList;
    }

    public List<FavoritePhotoList> getFavoritePhotoList() {
        return favoritePhotoList;
    }

    public void setFavoritePhotoList(List<FavoritePhotoList> favoritePhotoList) {
        this.favoritePhotoList = favoritePhotoList;
    }

    public List<FavoriteLiteraryWorkList> getFavoriteLiteraryWorkList() {
        return favoriteLiteraryWorkList;
    }

    public void setFavoriteLiteraryWorkList(List<FavoriteLiteraryWorkList> favoriteLiteraryWorkList) {
        this.favoriteLiteraryWorkList = favoriteLiteraryWorkList;
    }

    public List<Quote> getQuoteList() {
        return quoteList;
    }

    public void setQuoteList(List<Quote> quoteList) {
        this.quoteList = quoteList;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public List<Role> getRoleList() {
        if (roleList == null) {
            roleList = new ArrayList<>();
        }
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

}
