package com.spring.languageapp.service;

import com.spring.languageapp.model.Comment;
import com.spring.languageapp.model.LiteraryWorkPost;
import com.spring.languageapp.model.Quote;
import com.spring.languageapp.repository.LiteraryWorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailService {

    private JavaMailSender emailSender;
    private UserService userService;
    private LiteraryWorkRepository literaryWorkRepository;


    @Autowired
    public MailService(JavaMailSender emailSender, UserService userService, LiteraryWorkRepository literaryWorkRepository) {
        this.emailSender = emailSender;
        this.userService = userService;
        this.literaryWorkRepository = literaryWorkRepository;
    }

    // pt COMENTARIU
    public void sendCommentMessage(String recipientMail, String comment, Long literaryWorkPostId) throws MessagingException {

        MimeMessage message = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("raluca.deftu@yahoo.com");
        helper.setTo(recipientMail);

        helper.setSubject("You have a comment from " + userService.findLoggedInUser().getUsername());
        helper.setText("Your post: " + literaryWorkRepository.findById(literaryWorkPostId) + " has a new comment: " + comment);
        emailSender.send(message);
    }

    //pt aprobare TRADUCERE/TRANSLATARE
    public void sendApproveMessageForTranslation(String recipientMail, LiteraryWorkPost literaryWorkPost) throws MessagingException {

        MimeMessage message = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("raluca.deftu@yahoo.com");
        helper.setTo(recipientMail);

        helper.setSubject("You have a translation suggestion for" + literaryWorkPost.getTitle());
        helper.setText(literaryWorkPost.getUser().getUsername() + " wants to translate your literary work - " + literaryWorkPost.getTitle() + "Do you want do approve or deny?");
        emailSender.send(message);
    }

    //aprobare pt QUOTE
    public void sendApproveForQuote(String recipientMail, Quote quote) throws MessagingException {

        MimeMessage message = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("raluca.deftu@yahoo.com");
        helper.setTo(recipientMail);

        //helper.setSubject(quote.getUser().getUsername() + " saw your creation.");
        helper.setText(quote.getUser().getUsername() + " wants to add a quote." + " < " + quote.getText() + " > " +  " You can approve or unapprove the adding.");
        emailSender.send(message);
    }

    // de trimis mail pt adaugare la favorite a unei opere/poze de la un user
    public void sendMessegeForFavoriteAddedLiteraryWork(String recipientMail, LiteraryWorkPost literaryWorkPost) throws MessagingException {

        MimeMessage message = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("raluca.deftu@yahoo.com");
        helper.setTo(recipientMail);

        helper.setSubject("< " + literaryWorkPost.getTitle() + " >" + " was added in Favorite List");
        helper.setText(literaryWorkPost.getUser().getUsername() + " added your creation " + "< " + literaryWorkPost.getTitle() + " >" + " in Favorite List.");
        emailSender.send(message);
    }
}
