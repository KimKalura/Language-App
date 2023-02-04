package com.spring.languageapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.spring.languageapp.dto.CommentRequestDTO;
import com.spring.languageapp.model.Comment;
import com.spring.languageapp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/create")
    public Comment addComment(@RequestBody CommentRequestDTO commentRequestDTO) throws JsonProcessingException, MessagingException {
        //return status(HttpStatus.OK).body(commentService.addComment(commentRequestDTO));
        return commentService.addComment(commentRequestDTO);
    }
}
