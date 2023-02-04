package com.spring.languageapp.controller;

import com.spring.languageapp.dto.QuoteRequestDTO;
import com.spring.languageapp.model.Quote;
import com.spring.languageapp.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;

@RestController
@RequestMapping("/quote")
public class QuoteController {
    QuoteService quoteService;

    @Autowired
    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }


    @PostMapping("/add/{userId}")
    public Quote addQuote(@PathVariable Long userId, @RequestBody QuoteRequestDTO quoteRequestDTO) throws MessagingException {
        return quoteService.addQuote(userId, quoteRequestDTO);
    }

    @GetMapping("/{quoteId}")
    public Quote approvedQuote(@PathVariable Long quoteId) throws MessagingException {
        return quoteService.approveQuote(quoteId);
    }

    @GetMapping("/getAllApprovedQuotes")
    public List<Quote> getAllApprovedQuotes() {
        return quoteService.getAllApprovedQuotes();
    }

    @GetMapping("/getAllUnapprovedQuotes")
    public List<Quote> getAllUnapprovedQuotes() {
        return quoteService.getAllUnapprovedQuotes();
    }
}
