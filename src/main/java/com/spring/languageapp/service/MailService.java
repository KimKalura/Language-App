package com.spring.languageapp.service;

import com.spring.languageapp.model.LiteraryWorkPost;
import com.spring.languageapp.model.Post;
import com.spring.languageapp.model.Quote;
import com.spring.languageapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailService {

    private JavaMailSender emailSender;


    @Autowired
    public MailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }


    public void sendCommentMessage(User loggedInUser, User postCreator, String comment, Post post) throws MessagingException {
        MimeMessageHelper helper = prepareMail(String.valueOf(postCreator));
        helper.setSubject("You have a comment from " + loggedInUser.getUsername());
        helper.setText("Your post: " + post.getId() + " has a new comment: " + comment);
        helper.setTo(loggedInUser.getEmail());
        emailSender.send(helper.getMimeMessage());
    }

    public void sendApproveMessageForTranslation(String recipientMail, LiteraryWorkPost literaryWorkPost) throws MessagingException {
        MimeMessageHelper helper = prepareMail(recipientMail);
        helper.setSubject("You have a suggestion for translation/romanization - " + literaryWorkPost.getTitle());
        helper.setText(literaryWorkPost.getUser().getUsername() + " wants to translate your literary work - " + literaryWorkPost.getTitle() + ". " + "Do you want to approve or deny?");
        helper.setTo(recipientMail);
        emailSender.send(helper.getMimeMessage());
    }

    public void sendApprovementForQuoteToAdmin(String recipientMail, Quote quote) throws MessagingException {
        MimeMessageHelper helper = prepareMail(recipientMail);
        helper.setSubject(quote.getUser().getUsername() + " saw your creation.");
        helper.setText(quote.getUser().getUsername() + " wants to add a quote." + " < " + quote.getText() + " >. " + " You can approve or unapprove the adding.");
        helper.setTo(recipientMail);
        emailSender.send(helper.getMimeMessage());
    }

    public void sendMessegeForFavoriteAddedLiteraryWork(String recipientMail, LiteraryWorkPost literaryWorkPost) throws MessagingException {
        MimeMessageHelper helper = prepareMail(recipientMail);
        helper.setSubject("< " + literaryWorkPost.getTitle() + " >" + " was added in Favorite List");
        helper.setText(literaryWorkPost.getUser().getUsername() + " added your creation " + "< " + literaryWorkPost.getTitle() + " >" + " in Favorite List.");
        helper.setTo(recipientMail);
        emailSender.send(helper.getMimeMessage());
    }

    public MimeMessageHelper prepareMail(String to) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("florianaralucadeftu@gmail.com");
        helper.setTo(to);
        return helper;
    }
}
