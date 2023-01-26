package com.spring.languageapp.dto;

import java.time.LocalDateTime;

public class TranslationRomanizationRequestDTO {

    private Long id;
    private String language;
    private String translatedTitle;
    private String translatedText;
    private LocalDateTime createdDate;

    public TranslationRomanizationRequestDTO(Long id, String language, String translatedTitle, String translatedText, LocalDateTime createdDate) {
        this.id = id;
        this.language = language;
        this.translatedTitle = translatedTitle;
        this.translatedText = translatedText;
        this.createdDate = createdDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTranslatedTitle() {
        return translatedTitle;
    }

    public void setTranslatedTitle(String translatedTitle) {
        this.translatedTitle = translatedTitle;
    }

    public String getTranslatedText() {
        return translatedText;
    }

    public void setTranslatedText(String translatedText) {
        this.translatedText = translatedText;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
