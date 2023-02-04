package com.spring.languageapp.service;

import com.spring.languageapp.dto.QuoteRequestDTO;
import com.spring.languageapp.model.*;
import com.spring.languageapp.repository.LiteraryWorkRepository;
import com.spring.languageapp.repository.QuoteRepository;
import com.spring.languageapp.repository.RoleRepository;
import com.spring.languageapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import javax.mail.MessagingException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuoteService {
    private QuoteRepository quoteRepository;
    private UserService userService;
    private LiteraryWorkService literaryWorkService;
    private LiteraryWorkRepository literaryWorkRepository;
    private UserRepository userRepository;
    private MailService mailService;


    @Autowired
    public QuoteService(QuoteRepository quoteRepository, UserService userService, LiteraryWorkService literaryWorkService, LiteraryWorkRepository literaryWorkRepository, UserRepository userRepository, MailService mailService) {
        this.quoteRepository = quoteRepository;
        this.userService = userService;
        this.literaryWorkService = literaryWorkService;
        this.literaryWorkRepository = literaryWorkRepository;
        this.userRepository = userRepository;
        this.mailService = mailService;
    }


    public Quote addQuote(Long userId, QuoteRequestDTO quoteRequestDTO) throws MessagingException { //nu functioneaza
        User foundUser = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
        Quote quote = new Quote();
        quote.setText(quoteRequestDTO.getText());
        quote.setId(quoteRequestDTO.getQuoteId());
        quote.setUser(foundUser);
        quote.setApproved(false);
        List<User> admins = userRepository.findAll();//gasim toti userii cu rol admin
        //admins.forEach(admin -> mailService.sendApproveForQuote_Admin(admin, quote));//trimitem mail la toti cu cerere de approve quote
        /*admins.forEach((admin) -> {
            try {
                mailService.sendApproveForQuote_Admin(String.valueOf(admin), quote);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        });*/
        return quoteRepository.save(quote);
        //cand adaug Quote, atunci notific adminul ca e un nou quote; iar adminul face approve
    }

    public Quote approveQuote(Long quoteId){
        Quote foundQuote = quoteRepository.findById(quoteId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "quote not found"));
        foundQuote.setApproved(true);
        return quoteRepository.save(foundQuote);
    }

    public List<Quote> getAllApprovedQuotes() {
        return quoteRepository.findAll().stream()
                .filter(quote -> quote.getApproved())
                .collect(Collectors.toList());
    }

    public List<Quote> getAllUnapprovedQuotes() {
        return quoteRepository.findAll().stream()
                .filter(quote -> !quote.getApproved())
                .collect(Collectors.toList());
    }


    //met ajutatoare
    public Quote findQuote(Long id) {
        return quoteRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "quote was not found"));
    }
}
