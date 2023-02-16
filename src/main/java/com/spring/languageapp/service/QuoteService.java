package com.spring.languageapp.service;

import com.spring.languageapp.dto.QuoteRequestDTO;
import com.spring.languageapp.model.*;
import com.spring.languageapp.repository.LiteraryWorkRepository;
import com.spring.languageapp.repository.QuoteRepository;
import com.spring.languageapp.repository.RoleRepository;
import com.spring.languageapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private RoleRepository roleRepository;



    @Autowired
    public QuoteService(QuoteRepository quoteRepository, UserService userService, LiteraryWorkService literaryWorkService, LiteraryWorkRepository literaryWorkRepository, UserRepository userRepository, MailService mailService, RoleRepository roleRepository) {
        this.quoteRepository = quoteRepository;
        this.userService = userService;
        this.literaryWorkService = literaryWorkService;
        this.literaryWorkRepository = literaryWorkRepository;
        this.userRepository = userRepository;
        this.mailService = mailService;
        this.roleRepository = roleRepository;
    }


    public Quote addQuote(Long userId, QuoteRequestDTO quoteRequestDTO) throws MessagingException {
        User foundUser = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));

        Quote quote = new Quote();
        quote.setText(quoteRequestDTO.getText());
        quote.setId(quoteRequestDTO.getQuoteId());
        quote.setUser(foundUser);
        quote.setApproved(false);

        Role adminRole = roleRepository.findByRoleType(RoleType.ROLE_ADMIN);

        List<User> users = userRepository.findAll();
        users.stream()
                .filter(user -> user.getRoleList().contains(adminRole))
                .forEach(admin -> {
            try {
                mailService.sendApproveForQuote_Admin(admin.getEmail(), quote);
            } catch (MessagingException e) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "failed to send e-mails");
            }
        });
        return quoteRepository.save(quote);
    }


    public Quote approveQuote(Long quoteId){
        Quote foundQuote = quoteRepository.findById(quoteId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "quote was not found"));
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


    public Quote findQuote(Long id) {
        return quoteRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "quote was not found"));
    }
}
