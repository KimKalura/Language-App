package com.spring.languageapp.service;

import com.spring.languageapp.dto.FavoriteLiteraryWorkRequestDTO;
import com.spring.languageapp.model.FavoriteLiteraryWorkList;
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

    //adaug la favorite o opera sau o poza
         //autorul poeziei, de exemplu, va primi o notificare pe mail ca poezia lui a fost adaugata la favorite de catre cele care a adaugat-o
    public FavoriteLiteraryWorkList addLiteraryWorkToFavoriteList(FavoriteLiteraryWorkRequestDTO favoriteLiteraryWorkRequestDTO) throws MessagingException {
        LiteraryWorkPost foundLiteraryWork = literaryWorkRepository.findById(favoriteLiteraryWorkRequestDTO.getLiteraryWorkPostId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "literary work not found"));
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User foundUser = userRepository.findUserByUsername(userDetails.getUsername()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));

        // User foundUser = userRepository.findById(favoriteLiteraryWorkDTO.getUserId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
        //List<FavoriteLiteraryWorkList> foundFavoriteLiteraryWork =  favoriteLiteraryWorkListRepository.findAllByUser(foundUser);
        //** List<FavoriteLiteraryWorkList> favoriteLiteraryWorkList = foundUser.getFavoriteLiteraryWorkList();
        //foundFavLW.setUser(foundUser);

        FavoriteLiteraryWorkList favoriteLiteraryWork = new FavoriteLiteraryWorkList();
        favoriteLiteraryWork.setLiteraryWorkPost(foundLiteraryWork);
        favoriteLiteraryWork.setUser(foundUser);
        favoriteLiteraryWork.setSavedDate(foundLiteraryWork.getCreatedDate());//se salveaza cu data in care a fost creat LiteraryWork

       // mailService.sendMessegeForFavoriteAddedLiteraryWork(foundUser.getEmail(), foundLiteraryWork);

        return favoriteLiteraryWorkListRepository.save(favoriteLiteraryWork);
    }

    public List<FavoriteLiteraryWorkList> getAllFavoriteLiteraryWorkByUser(Long userId){
        //metoda pt a vedea toata lista de Favorite adaugata (prose + poetry)

        //*se salveaza cu data in care a fost creat LiteraryWork
        //vreau sa apara si titlul operei
        User foundUser = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
        return favoriteLiteraryWorkListRepository.findAllByUserOrderByLiteraryWorkPost(foundUser);
    }
}
