package com.spring.languageapp.controller;

import com.spring.languageapp.dto.QuoteRequestDTO;
import com.spring.languageapp.model.Quote;
import com.spring.languageapp.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/quote")
public class QuoteController {
    QuoteService quoteService;

    @Autowired
    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @PostMapping("/add/{id}")
    public Quote addQuote(@PathVariable Long id, @RequestBody QuoteRequestDTO quoteRequestDTO) throws MessagingException {
        return quoteService.addQuote(id, quoteRequestDTO);
    }

}
