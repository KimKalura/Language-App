package com.spring.languageapp.controller;

import com.spring.languageapp.dto.FavoriteLiteraryWorkRequestDTO;
import com.spring.languageapp.model.FavoriteLiteraryWorkList;
import com.spring.languageapp.service.FavoriteLiteraryWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;

@RestController
@RequestMapping("/favoriteLiteraryWork")
public class FavoriteLiteraryWorkController {
    private FavoriteLiteraryWorkService favoriteLiteraryWorkService;

    @Autowired
    public FavoriteLiteraryWorkController(FavoriteLiteraryWorkService favoriteLiteraryWorkService) {
        this.favoriteLiteraryWorkService = favoriteLiteraryWorkService;
    }


    @PostMapping("/add")
    public FavoriteLiteraryWorkList addLiteraryWorkToFavoriteList(@RequestBody FavoriteLiteraryWorkRequestDTO favoriteLiteraryWorkRequestDTO) throws MessagingException {
        return favoriteLiteraryWorkService.addLiteraryWorkToFavoriteList(favoriteLiteraryWorkRequestDTO);
    }

    @GetMapping("/{userId}")
    public List<FavoriteLiteraryWorkList> getAllFavoriteLiteraryWorkByUser(@PathVariable Long userId) {
        return favoriteLiteraryWorkService.getAllFavoriteLiteraryWorkByUser(userId);
    }

    @DeleteMapping("/{id}")
    public void deleteSubreddit(@PathVariable Long id){
        favoriteLiteraryWorkService.deleteFromFavoriteList(id);
    }
}