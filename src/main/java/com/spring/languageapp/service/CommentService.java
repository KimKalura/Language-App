package com.spring.languageapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.languageapp.dto.CommentRequestDTO;
import com.spring.languageapp.dto.CommentResponseDTO;
import com.spring.languageapp.model.*;
import com.spring.languageapp.repository.CommentRepository;
import com.spring.languageapp.repository.LiteraryWorkRepository;
import com.spring.languageapp.repository.PhotoRepository;
import com.spring.languageapp.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriTemplate;

import javax.mail.MessagingException;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentService {

    private CommentRepository commentRepository;
    private UserService userService;
    private LiteraryWorkService literaryWorkService;

    private MailService mailService;

    private RestTemplate restTemplate;
    private PhotoRepository photoRepository;
    private LiteraryWorkRepository literaryWorkRepository;
    private QuoteRepository quoteRepository;
    private QuoteService quoteService;
    private PhotoService photoService;

    private static final String CONTAINS_PROFANITY_TEXT_URL = "https://www.purgomalum.com/service/containsprofanity?text={wordsToCheck}";


    @Autowired

    public CommentService(CommentRepository commentRepository, UserService userService, LiteraryWorkService literaryWorkService, MailService mailService, RestTemplate restTemplate, PhotoRepository photoRepository, LiteraryWorkRepository literaryWorkRepository, QuoteRepository quoteRepository, QuoteService quoteService, PhotoService photoService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.literaryWorkService = literaryWorkService;
        this.mailService = mailService;
        this.restTemplate = restTemplate;
        this.photoRepository = photoRepository;
        this.literaryWorkRepository = literaryWorkRepository;
        this.quoteRepository = quoteRepository;
        this.quoteService = quoteService;
        this.photoService = photoService;
    }


    public Comment addComment(CommentRequestDTO commentRequestDTO) throws JsonProcessingException, MessagingException {
        String response = getResponseBodyJson(CONTAINS_PROFANITY_TEXT_URL, commentRequestDTO.getComment());
        if (Boolean.parseBoolean(response)) {
            throw new ResponseStatusException(HttpStatus.LOCKED, "the comment contains profanity words");
        }

        Comment comment = new Comment();
        comment.setComment(commentRequestDTO.getComment());
        comment.setCreatedDate(LocalDateTime.now());
        comment.setUser(userService.findLoggedInUser());

        User postCreator = new User();

        if (commentRequestDTO.getLiteraryWorkPostId() != null) {
            LiteraryWorkPost foundLiteraryWork = literaryWorkService.findLiteraryWork(commentRequestDTO.getLiteraryWorkPostId());
            foundLiteraryWork.getCommentList().add(comment);
            comment.setLiteraryWorkPost(foundLiteraryWork);
            postCreator = foundLiteraryWork.getUser();
            mailService.sendCommentMessage(userService.findLoggedInUser(), postCreator,  comment.getComment(), foundLiteraryWork );
        } else if (commentRequestDTO.getQuoteId() != null) {
            Quote foundQuote = quoteService.findQuote(commentRequestDTO.getQuoteId());
            foundQuote.getCommentList().add(comment);
            comment.setQuote(foundQuote);
            postCreator = foundQuote.getUser();
            mailService.sendCommentMessage(userService.findLoggedInUser(), postCreator,  comment.getComment(), foundQuote );
        } else if (commentRequestDTO.getPhotoId() != null) {
            PhotoPost foundPhotoPost = photoService.findPhoto(commentRequestDTO.getPhotoId());
            foundPhotoPost.getCommentList().add(comment);
            comment.setPhoto(foundPhotoPost);
            postCreator = foundPhotoPost.getUser();
            mailService.sendCommentMessage(userService.findLoggedInUser(), postCreator,  comment.getComment(), foundPhotoPost );
        }
        return commentRepository.save(comment);
    }

    public String getResponseBodyJson(String requestBaseUrl, String wordsToCheck) throws JsonProcessingException {
        URI url = new UriTemplate(requestBaseUrl).expand(wordsToCheck);
        return makeAPICall(url);
    }

    private String makeAPICall(URI url) throws JsonProcessingException {
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response.getBody();
    }

    public void deleteComment(Long commentId) {
        Comment foundComment = commentRepository.findById(commentId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "comment was not found"));
        commentRepository.delete(foundComment);
    }
}
