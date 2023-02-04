package com.spring.languageapp.dto;

import java.time.LocalDateTime;

public class CommentResponseDTO {
    private Long id;
    private Long literaryWorkPostId;
    private Long quoteId;
    private Long photoId;
    private LocalDateTime createdDate;
    private String comment;
    private String username;

    public CommentResponseDTO() {
    }

    public CommentResponseDTO(Long id, Long literaryWorkPostId, Long quoteId, Long photoId, LocalDateTime createdDate, String comment, String username) {
        this.id = id;
        this.literaryWorkPostId = literaryWorkPostId;
        this.quoteId = quoteId;
        this.photoId = photoId;
        this.createdDate = createdDate;
        this.comment = comment;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLiteraryWorkPostId() {
        return literaryWorkPostId;
    }

    public void setLiteraryWorkPostId(Long literaryWorkPostId) {
        this.literaryWorkPostId = literaryWorkPostId;
    }

    public Long getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(Long quoteId) {
        this.quoteId = quoteId;
    }

    public Long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Long photoId) {
        this.photoId = photoId;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String text) {
        this.comment = comment;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
