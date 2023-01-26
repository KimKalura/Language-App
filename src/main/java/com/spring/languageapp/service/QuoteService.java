package com.spring.languageapp.service;

import com.spring.languageapp.dto.QuoteRequestDTO;
import com.spring.languageapp.model.LiteraryWorkPost;
import com.spring.languageapp.model.Quote;
import com.spring.languageapp.model.User;
import com.spring.languageapp.repository.LiteraryWorkRepository;
import com.spring.languageapp.repository.QuoteRepository;
import com.spring.languageapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import javax.mail.MessagingException;
import java.util.List;
import java.util.Optional;
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
    public Quote addQuote(Long id, QuoteRequestDTO quoteRequestDTO) throws MessagingException {
        LiteraryWorkPost foundLiteraryWork = literaryWorkRepository.findById(quoteRequestDTO.getLiteraryWorkPostId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "literary work not found"));
        User foundUser = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));

        Quote quote = new Quote();
        quote.setText(quoteRequestDTO.getText());
        quote.setId(quoteRequestDTO.getLiteraryWorkPostId());
        quote.setUser(foundUser);

        /*if (!foundLiteraryWork.getApproved()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Quote was not approved");
        }*/
        foundLiteraryWork.setApproved(true);
        //mailService.sendApproveForQuote(foundUser.getEmail(), quote, foundLiteraryWork);
        // "numberOfLikes": null,
        //    "numberOfDislikes": null,
        //    "commentList": null
        return quoteRepository.save(quote);
    }

    public boolean unnaprovedQuotesFromLiteraryWork() {
        Quote quote = new Quote();
        boolean isApproved = false;
        if (quote.getText().equals(isApproved)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "the quote was not approved");
        }
        return true;
    }


    //aprob un citat (ADMIN)
    //va seta isApproved pe true
    /*public Quote approvedQuote(Long quoteId) {
        Quote quote = quoteRepository.findById(quoteId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "literary work not found"));
        LiteraryWorkPost literaryWorkPost = ;
        literaryWorkPost.setApproved(true);
        boolean isApproved = true;


        //literaryWorkPost.contains(true);
        return quoteRepository.save();
    }*/


    //vad toate citatele
    //se vor afisa doar citatele aprobate (IsApproved=false)
    public List<Quote> getAllApprovedQuotes() {//Long quoteId
        /*LiteraryWorkPost foundQuote = literaryWorkRepository.findById(quoteId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "the quote was not found"));
        //Quote foundQuote = quoteRepository.findById(quoteId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "the quote was not found"));
        return foundQuote.getUser().getQuoteList().stream()
                .filter(literaryWorkPost-> Boolean.parseBoolean(literaryWorkPost.getText()))
                .filter(quote-> hasLiteraryWorkApproved(quote))
                .collect(Collectors.toList());*/
        List<LiteraryWorkPost> literaryWorkPost = literaryWorkRepository.findAll();

        List<Quote> foundQuotes = quoteRepository.findAll();
        return foundQuotes.stream()
                .filter(quote -> hasLiteraryWorkApproved(quote))
                .collect(Collectors.toList());

    }

    public boolean hasLiteraryWorkApproved(Quote quote) {
        List<LiteraryWorkPost> foundText = literaryWorkRepository.findAllByText(quote.getText());
        return foundText.stream().anyMatch(literaryWorkPost -> literaryWorkPost.getApproved());
    }

}
