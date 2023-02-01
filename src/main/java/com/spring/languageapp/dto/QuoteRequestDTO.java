package com.spring.languageapp.dto;

import com.spring.languageapp.model.Comment;

import java.util.List;

public class QuoteRequestDTO {
    private String text;
    private Long quoteId;

    public QuoteRequestDTO(String text, Long quoteId) {
        this.text = text;
        this.quoteId = quoteId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(Long quoteId) {
        this.quoteId = quoteId;
    }
}
