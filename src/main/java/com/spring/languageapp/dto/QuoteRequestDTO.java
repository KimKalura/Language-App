package com.spring.languageapp.dto;

public class QuoteRequestDTO {
    private String text;
    private Long literaryWorkPostId;

    public QuoteRequestDTO(String text, Long literaryWorkPostId) {
        this.text = text;
        this.literaryWorkPostId = literaryWorkPostId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getLiteraryWorkPostId() {
        return literaryWorkPostId;
    }

    public void setLiteraryWorkPostId(Long literaryWorkPostId) {
        this.literaryWorkPostId = literaryWorkPostId;
    }
}
