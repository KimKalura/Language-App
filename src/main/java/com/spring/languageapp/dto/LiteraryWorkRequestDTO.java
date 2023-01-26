package com.spring.languageapp.dto;

import com.spring.languageapp.model.LanguageType;
import com.spring.languageapp.model.LiteraryWorkType;

public class LiteraryWorkRequestDTO {

    private LiteraryWorkType literaryWorkType;
    private String originalLanguage;
    private String title;
    private String text;
    private String translatedLanguage;
    private String translatedTitle;
    private String translatedText;
    private String romanizationText;

    public LiteraryWorkRequestDTO(){}

    public LiteraryWorkRequestDTO(LiteraryWorkType literaryWorkType, String originalLanguage, String title, String text, String translatedLanguage, String translatedTitle, String translatedText, String romanizationText) {
        this.literaryWorkType = literaryWorkType;
        this.originalLanguage = originalLanguage;
        this.title = title;
        this.text = text;
        this.translatedLanguage = translatedLanguage;
        this.translatedTitle = translatedTitle;
        this.translatedText = translatedText;
        this.romanizationText = romanizationText;
    }

    public LiteraryWorkType getLiteraryWorkType() {
        return literaryWorkType;
    }

    public void setLiteraryWorkType(LiteraryWorkType literaryWorkType) {
        this.literaryWorkType = literaryWorkType;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
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

    public String getTranslatedLanguage() {
        return translatedLanguage;
    }

    public void setTranslatedLanguage(String translatedLanguage) {
        this.translatedLanguage = translatedLanguage;
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
}
