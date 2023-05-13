package com.spring.languageapp.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.spring.languageapp.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.mail.MessagingException;
import java.io.IOException;

@RestController
@RequestMapping("/dictionary")
public class DictionaryController {

    private DictionaryService dictionaryService;

    @Autowired
    public DictionaryController(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }


    @GetMapping
    public String getDefinition(@RequestParam (value="word", required = false, defaultValue = "code") String word) {
        try {
            return dictionaryService.getWord(word);
        } catch (IOException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(),e);
        }
    }

    //pentru a citi k-v din JSON
    /*@GetMapping
    public String getDefinition(@RequestParam (value="word", required = false, defaultValue = "code") String word) throws IOException {
        return dictionaryService.getWord(word);
    }*/
}
