package com.spring.languageapp.dto;

public class FavoriteLiteraryWorkRequestDTO {

    private Long literaryWorkPostId;
    private Long userId;


    public FavoriteLiteraryWorkRequestDTO(Long literaryWorkPostId, Long userId) {
        this.literaryWorkPostId = literaryWorkPostId;
        this.userId = userId;
    }

    public Long getLiteraryWorkPostId() {
        return literaryWorkPostId;
    }

    public void setLiteraryWorkPostId(Long literaryWorkPostId) {
        this.literaryWorkPostId = literaryWorkPostId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
