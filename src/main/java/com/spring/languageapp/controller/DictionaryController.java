package com.spring.languageapp.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.spring.languageapp.dto.TranslatedWordDTO;
import com.spring.languageapp.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/dictionary")
public class DictionaryController {

    private DictionaryService dictionaryService;

    @Autowired
    public DictionaryController(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }


    @GetMapping("/translation")
    public  List<TranslatedWordDTO>  getTranslatedWord(@RequestParam (value="lang", required = false, defaultValue = "en-ru") String languageType, @RequestParam (value="text", required = false, defaultValue = "code") String word) {
        try {
            return dictionaryService.getTranslatedWord(languageType, word);
        } catch (IOException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(),e);
        }
    }

    @GetMapping
    public String getTranslation(@RequestParam (value="lang", required = false, defaultValue = "en-ru") String languageType,  @RequestParam (value="text", required = false, defaultValue = "code") String word) throws IOException {
        return dictionaryService.getTranslation(languageType, word);
    }
}
