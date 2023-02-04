package com.spring.languageapp.dto;

public class CommentRequestDTO {
    private String comment;

    private Long literaryWorkPostId;
    private Long quoteId;
    private Long photoId;

    public CommentRequestDTO(String comment, Long photoId, Long literaryWorkPostId, Long quoteId) {
        this.comment = comment;
        this.photoId = photoId;
        this.literaryWorkPostId = literaryWorkPostId;
        this.quoteId = quoteId;
    }


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Long photoId) {
        this.photoId = photoId;
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
}
