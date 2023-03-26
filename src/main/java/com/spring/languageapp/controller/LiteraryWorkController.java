package com.spring.languageapp.controller;

import com.spring.languageapp.dto.*;
import com.spring.languageapp.model.Comment;
import com.spring.languageapp.model.LiteraryWorkPost;
import com.spring.languageapp.model.TranslationRomanization;
import com.spring.languageapp.service.LiteraryWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;

@RestController
@RequestMapping("/literaryWork")
public class LiteraryWorkController {
    private LiteraryWorkService literaryWorkService;

    @Autowired
    public LiteraryWorkController(LiteraryWorkService literaryWorkService) {
        this.literaryWorkService = literaryWorkService;
    }

    @PostMapping("/add")
    public LiteraryWorkPost addLiteraryWork(@RequestBody LiteraryWorkRequestDTO literaryWorkRequestDTO) {
        return literaryWorkService.addLiteraryWork(literaryWorkRequestDTO);
    }

    @PostMapping("/addtranslation")
    public TranslationRomanization addTranslationForALiteraryworkOfAUser(@RequestBody TranslationRomanizationRequestDTO translationRomanizationRequestDTO) throws MessagingException {
        return literaryWorkService.addTranslationForALiteraryworkOfAUser(translationRomanizationRequestDTO);
    }

    @GetMapping("/allProse")
    public List<LiteraryWorkPost> getAllProse() {
        return literaryWorkService.getAllProse();
    }

    @GetMapping("/allPoetry")
    public List<LiteraryWorkPost> getAllPoetry() {
        return literaryWorkService.getAllPoetry();
    }

    @GetMapping("/{id}/{language}")
    public LiteraryWorkResponseDTO getProseByLanguage(@PathVariable Long id, @PathVariable String language) {
        return literaryWorkService.getProseByLanguage(id, language);
    }

    @GetMapping("/all")
    public List<LiteraryWorkPost> getAllProseAndPoetry() {
        return literaryWorkService.getAllProseAndPoetry();
    }

    @DeleteMapping("/{id}")
    public void deleteLiteraryWork(@PathVariable Long id) {
        literaryWorkService.deleteLiteraryWork(id);
    }

    @PostMapping("/like/{literaryWorkId}")
    public LiteraryWorkPost addLike(@PathVariable Long literaryWorkId) throws MessagingException {
         return literaryWorkService.addLike(literaryWorkId);
    }

    @PostMapping("/dislike/{literaryWorkId}")
    public LiteraryWorkPost addDislike(@PathVariable Long literaryWorkId){
        return literaryWorkService.addDislike(literaryWorkId);
    }
}


