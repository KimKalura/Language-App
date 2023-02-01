package com.spring.languageapp.service;

import com.spring.languageapp.dto.QuoteRequestDTO;
import com.spring.languageapp.model.Quote;
import com.spring.languageapp.model.User;
import com.spring.languageapp.repository.LiteraryWorkRepository;
import com.spring.languageapp.repository.QuoteRepository;
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


    //adaug un citat
    //in acest moment se va trimite notificare pe mail fiecarui admin din aplicatie
    //citatul se va adauga in baza de date, cu statusul unapproved
    public Quote addQuote(Long userId, QuoteRequestDTO quoteRequestDTO) throws MessagingException {//de verificat; repository nu poate fi gasit; mail??
        User foundUser = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
        Quote quote = new Quote();
        quote.setText(quoteRequestDTO.getText());
        quote.setId(quoteRequestDTO.getQuoteId());
        quote.setUser(foundUser); //pentru a avea mereu un alt user trebuie sa setez din postman un al token +user

        quote.setApproved(false);
        //gasim toti userii cu rol admin
        //trimitem mail la toti cu cerere de approve qute
        //admins.forEach((admin)->mailService.sendApproveForQuote(admin, quote))
        mailService.sendApproveForQuote(foundUser.getEmail(), quote);
        return quoteRepository.save(quote);

        //DE REVAZUT; in postman arata: ; nu crec ca e nevoie in DTO de text, doar de LWid
        //    "id": 85,
        //    "text": "To go wrong in one's own way is better than to go right in someone else's. - Fyodor Dostoevsky, Crime and Punishment",
        //    "numberOfLikes": null,
        //    "numberOfDislikes": null,
        //    "commentList": null,
        //    "approved": false

    }

    //aprob un citat (ADMIN)
    //va seta isApproved pe true
    public Quote approveQuote(Long quoteId) throws MessagingException {//de verificat  nu poate gasi repository; mail??
        Quote foundQuote = quoteRepository.findById(quoteId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "quote not found"));

        foundQuote.setApproved(true);
        //mailService.sendApproveForQuote(foundUser.getEmail(), foundQuote);
        return quoteRepository.save(foundQuote);

        //{   POSTMAN
        //    "id": 90,
        //    "text": "Do not pity the dead, Harry. Pity the living, and, above all, those who live without love. ― Albus Dumbledore",
        //    "numberOfLikes": null,
        //    "numberOfDislikes": null,
        //    "commentList": [],
        //    "approved": true
        //}
    }

    //vad toate citatele
    //se vor afisa doar citatele aprobate (IsApproved=false)
    public List<Quote> getAllApprovedQuotes() { // quoteRep nu poate fi gasit -din controller
        //vreau sa apara si id

        return quoteRepository.findAll().stream()

                 .filter(quote->quote.getApproved())
                .collect(Collectors.toList());
    }

    //vad toate citatele neaprobate (doar ca ADMIN)

}
