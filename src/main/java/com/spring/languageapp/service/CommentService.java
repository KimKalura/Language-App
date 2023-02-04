package com.spring.languageapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.languageapp.dto.CommentRequestDTO;
import com.spring.languageapp.dto.CommentResponseDTO;
import com.spring.languageapp.model.Comment;
import com.spring.languageapp.model.LiteraryWorkPost;
import com.spring.languageapp.model.PhotoPost;
import com.spring.languageapp.model.Quote;
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

@Service
public class CommentService {

    private CommentRepository commentRepository;
    private UserService userService;
    private LiteraryWorkService literaryWorkService;

    private MailService mailService;

    /*@Value("${api.   .key}")
    private String apiKey;*/

    private RestTemplate restTemplate;
    private PhotoRepository photoRepository;
    private LiteraryWorkRepository literaryWorkRepository;
    private QuoteRepository quoteRepository;
    private QuoteService quoteService;
    private PhotoService photoService;

    private static final String CONTAINS_PROFANITY_TEXT_URL = "https://www.purgomalum.com/service/containsprofanity?text={wordsToCheck}";

    // http://api1-eu.webpurify.com/services/rest/

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

        LiteraryWorkPost foundLiteraryWork = literaryWorkService.findLiteraryWork(commentRequestDTO.getLiteraryWorkPostId());
        Quote foundQuote = quoteService.findQuote(commentRequestDTO.getQuoteId());
        PhotoPost foundPhoto = photoService.findPhoto(commentRequestDTO.getPhotoId());

        if (foundLiteraryWork.getId() != null) {
            literaryWorkRepository.findById(foundLiteraryWork.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "literary work not found"));
        }
        if (foundQuote.getId() != null) {
            quoteRepository.findById(foundQuote.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "guote not found"));
        }
        if (foundPhoto.getId() != null) {
            photoRepository.findAllById(foundPhoto.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "photo not found"));
        }

        if (Boolean.parseBoolean(response)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "the comment contains profanity words");
        }
        Comment comment = new Comment();
        comment.setComment(commentRequestDTO.getComment());
        comment.setLiteraryWorkPost(foundLiteraryWork);
        comment.setQuote(foundQuote);
        comment.setCreatedDate(LocalDateTime.now());

//        foundLiteraryWork.getCommentList().add(comment);
//        comment.setLiteraryWorkPost(foundLiteraryWork);
        // mailService.sendCommentMessage(foundUser.getEmail(), ?);
        return commentRepository.save(comment);
    }


    public String getResponseBodyJson(String requestBaseUrl, String wordsToCheck) throws JsonProcessingException {
        URI url = new UriTemplate(requestBaseUrl).expand(wordsToCheck);//apiKey
        //ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        //ObjectMapper objectMapper = new ObjectMapper();
        return makeAPICall(url);
    }

    private String makeAPICall(URI url) throws JsonProcessingException {
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        //ObjectMapper objectMapper = new ObjectMapper();
        //return objectMapper.readTree(response.getBody());
        return response.getBody();
    }

    public CommentResponseDTO constructNewCommentResponseDTO(Comment comment) {
        CommentResponseDTO commentResponseDTO = new CommentResponseDTO();
        commentResponseDTO.setId(comment.getId());
        commentResponseDTO.setLiteraryWorkPostId(comment.getLiteraryWorkPost().getId());
        commentResponseDTO.setPhotoId(comment.getPhoto().getId());
        commentResponseDTO.setQuoteId(comment.getQuote().getId());
        commentResponseDTO.setCreatedDate(comment.getCreatedDate());
        commentResponseDTO.setComment(comment.getComment());
        commentResponseDTO.setUsername(comment.getUser().getUsername());
        return commentResponseDTO;
    }
    //delete comment
    //get all commentsBy user sau LiteraryWork sau Quote sau Translation

}
