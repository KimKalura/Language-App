package com.spring.languageapp.dto;

import com.spring.languageapp.model.LanguageType;

import java.time.LocalDateTime;
import java.util.List;

public class LiteraryWorkResponseDTO {

    private LanguageType originalLanguage;
    private LocalDateTime createdDate;
    private String title;
    private String text;
    private LanguageType translatedLanguage;
    private List<Long> otherProsePartsIds;


    public LiteraryWorkResponseDTO() {}

    public LiteraryWorkResponseDTO(LanguageType originalLanguage, LocalDateTime createdDate, String title, String text, LanguageType translatedLanguage, List<Long> otherProsePartsIds) {
        this.originalLanguage = originalLanguage;
        this.createdDate = createdDate;
        this.title = title;
        this.text = text;
        this.translatedLanguage = translatedLanguage;
        this.otherProsePartsIds = otherProsePartsIds;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LanguageType getTranslatedLanguage() {
        return translatedLanguage;
    }

    public void setTranslatedLanguage(LanguageType translatedLanguage) {
        this.translatedLanguage = translatedLanguage;
    }

    public List<Long> getOtherProsePartsIds() {
        return otherProsePartsIds;
    }

    public void setOtherProsePartsIds(List<Long> otherProsePartsIds) {
        this.otherProsePartsIds = otherProsePartsIds;
    }
}
