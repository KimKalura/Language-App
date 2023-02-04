package com.spring.languageapp.dto;

import java.time.LocalDateTime;

public class TranslationRomanizationRequestDTO {

    private Long id;
    private String language;
    private String translatedTitle;
    private String translatedText;
    private String romanizationText;
    private LocalDateTime createdDate;

    public TranslationRomanizationRequestDTO(Long id, String language, String translatedTitle, String translatedText, String romanizationText, LocalDateTime createdDate) {
        this.id = id;
        this.language = language;
        this.translatedTitle = translatedTitle;
        this.translatedText = translatedText;
        this.romanizationText = romanizationText;
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


    public String getRomanizationText() {
        return romanizationText;
    }

    public void setRomanizationText(String romanizationText) {
        this.romanizationText = romanizationText;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
