package com.spring.languageapp.service;

import com.spring.languageapp.dto.FavoriteLiteraryWorkRequestDTO;
import com.spring.languageapp.model.FavoriteUserLiteraryWorkPost;
import com.spring.languageapp.model.LiteraryWorkPost;
import com.spring.languageapp.model.User;
import com.spring.languageapp.repository.FavoriteLiteraryWorkListRepository;
import com.spring.languageapp.repository.LiteraryWorkRepository;
import com.spring.languageapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.mail.MessagingException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoriteLiteraryWorkService {

    private FavoriteLiteraryWorkListRepository favoriteLiteraryWorkListRepository;
    private UserService userService;
    private UserRepository userRepository;
    private MailService mailService;
    private LiteraryWorkRepository literaryWorkRepository;

    @Autowired
    public FavoriteLiteraryWorkService(FavoriteLiteraryWorkListRepository favoriteLiteraryWorkListRepository, UserService userService, UserRepository userRepository, MailService mailService, LiteraryWorkRepository literaryWorkRepository) {
        this.favoriteLiteraryWorkListRepository = favoriteLiteraryWorkListRepository;
        this.userService = userService;
        this.userRepository = userRepository;
        this.mailService = mailService;
        this.literaryWorkRepository = literaryWorkRepository;
    }

    public FavoriteUserLiteraryWorkPost addLiteraryWorkToFavoriteList(FavoriteLiteraryWorkRequestDTO favoriteLiteraryWorkRequestDTO) throws MessagingException {
        LiteraryWorkPost foundLiteraryWork = literaryWorkRepository.findById(favoriteLiteraryWorkRequestDTO.getLiteraryWorkPostId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "literary work not found"));
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User foundUser = userRepository.findUserByUsername(userDetails.getUsername()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));

        FavoriteUserLiteraryWorkPost favoriteLiteraryWork = new FavoriteUserLiteraryWorkPost();
        favoriteLiteraryWork.setLiteraryWorkPost(foundLiteraryWork);
        favoriteLiteraryWork.setUser(foundUser);
        favoriteLiteraryWork.setSavedDate(foundLiteraryWork.getCreatedDate());
        mailService.sendMessegeForFavoriteAddedLiteraryWork(foundUser.getEmail(), foundLiteraryWork);
        return favoriteLiteraryWorkListRepository.save(favoriteLiteraryWork);
    }

    public List<LiteraryWorkPost> getAllFavoriteLiteraryWorkByUser(Long userId) {
        User foundUser = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
        List<FavoriteUserLiteraryWorkPost> favoriteUserLiteraryWorkPosts = favoriteLiteraryWorkListRepository.findAllByUserOrderByLiteraryWorkPost(foundUser);
        return favoriteUserLiteraryWorkPosts.stream().map(favoriteUserLiteraryWorkPost -> favoriteUserLiteraryWorkPost.getLiteraryWorkPost()).collect(Collectors.toList());
    }

    public void deleteFromFavoriteList(Long id) {
        FavoriteUserLiteraryWorkPost foundFavLiteraryWork = favoriteLiteraryWorkListRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "literary work was not found"));
        favoriteLiteraryWorkListRepository.delete(foundFavLiteraryWork);
    }
}
