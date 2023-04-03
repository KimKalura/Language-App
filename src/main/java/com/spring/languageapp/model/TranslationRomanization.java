package com.spring.languageapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class TranslationRomanization {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq")
    @SequenceGenerator(name = "category_seq",
            sequenceName = "category_seq",
            initialValue = 1,
            allocationSize = 1)
    private Long id;

    @Column
    private LanguageType language;

    @Column
    private String translatedTitle;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String translatedText;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String romanizationText;

    @Column
    //@ApiModelProperty(hidden = true)
    private LocalDateTime createdDate;

    @ManyToOne
    @JoinColumn(name = "literaryWorkPost")
    @JsonBackReference(value = "literaryWorkPost-translationRomanization")
    private LiteraryWorkPost literaryWorkPost;


    public TranslationRomanization(){}

    public TranslationRomanization(Long id, LanguageType language, String translatedTitle, String translatedText, String romanizationText, LocalDateTime createdDate, LiteraryWorkPost literaryWorkPost) {
        this.id = id;
        this.language = language;
        this.translatedTitle = translatedTitle;
        this.translatedText = translatedText;
        this.romanizationText = romanizationText;
        this.createdDate = createdDate;
        this.literaryWorkPost = literaryWorkPost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LanguageType getLanguage() {
        return language;
    }

    public void setLanguage(LanguageType language) {
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

    public LiteraryWorkPost getLiteraryWorkPost() {
        return literaryWorkPost;
    }

    public void setLiteraryWorkPost(LiteraryWorkPost literaryWorkPost) {
        this.literaryWorkPost = literaryWorkPost;
    }
}
