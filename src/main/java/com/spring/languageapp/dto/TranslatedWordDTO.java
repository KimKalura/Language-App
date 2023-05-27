package com.spring.languageapp.dto;

public class TranslatedWordDTO {

    private String text;
    private String partOfSpeech;
    private String transliteration;
    private String textTranslation;
    private String textSynonymous;
    private String textMeaning;


    public TranslatedWordDTO(String text, String partOfSpeech, String transliteration, String textTranslation, String textSynonymous, String textMeaning) {
        this.text = text;
        this.partOfSpeech = partOfSpeech;
        this.transliteration = transliteration;
        this.textTranslation = textTranslation;
        this.textSynonymous = textSynonymous;
        this.textMeaning = textMeaning;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    public String getTransliteration() {
        return transliteration;
    }

    public void setTransliteration(String transliteration) {
        this.transliteration = transliteration;
    }

    public String getTextTranslation() {
        return textTranslation;
    }

    public void setTextTranslation(String textTranslation) {
        this.textTranslation = textTranslation;
    }

    public String getTextSynonymous() {
        return textSynonymous;
    }

    public void setTextSynonymous(String textSynonymous) {
        this.textSynonymous = textSynonymous;
    }

    public String getTextMeaning() {
        return textMeaning;
    }

    public void setTextMeaning(String textMeaning) {
        this.textMeaning = textMeaning;
    }
}
