package com.spring.languageapp.service;

import com.spring.languageapp.dto.LiteraryWorkRequestDTO;
import com.spring.languageapp.dto.LiteraryWorkResponseDTO;
import com.spring.languageapp.dto.TranslationRomanizationRequestDTO;
import com.spring.languageapp.model.*;
import com.spring.languageapp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class LiteraryWorkService {

    private LiteraryWorkRepository literaryWorkRepository;
    private UserService userService;
    private UserRepository userRepository;
    private TranslationRomanizationRepository translationRomanizationRepository;
    private MailService mailService;
    private PhotoRepository photoRepository;

    @Autowired
    public LiteraryWorkService(LiteraryWorkRepository literaryWorkRepository, UserService userService, UserRepository userRepository, TranslationRomanizationRepository translationRomanizationRepository, MailService mailService, PhotoRepository photoRepository) {
        this.literaryWorkRepository = literaryWorkRepository;
        this.userService = userService;
        this.userRepository = userRepository;
        this.translationRomanizationRepository = translationRomanizationRepository;
        this.mailService = mailService;
        this.photoRepository = photoRepository;
    }


    public LiteraryWorkPost addLiteraryWork(LiteraryWorkRequestDTO literaryWorkRequestDTO) {
        LiteraryWorkPost literaryWorkPost = new LiteraryWorkPost();

        if (literaryWorkRequestDTO.getLiteraryWorkType().equals(LiteraryWorkType.PROSE) && literaryWorkRequestDTO.getText().equals(maxWordsForProse(literaryWorkRequestDTO.getText(), 1000))) {
            literaryWorkRepository.save(literaryWorkPost);
        } else if (literaryWorkRequestDTO.getLiteraryWorkType().equals(LiteraryWorkType.POETRY) && literaryWorkRequestDTO.getText().equals(maxWordsForPoem(literaryWorkRequestDTO.getText(), 250))) {
            literaryWorkRepository.save(literaryWorkPost);
        } else {
            throw new ResponseStatusException((HttpStatus.NO_CONTENT), "You reached the maxim words for your literary work or you did not chose the type of your literary work!");
        }
        literaryWorkPost.setLiteraryWorkType(literaryWorkRequestDTO.getLiteraryWorkType());
        literaryWorkPost.setOriginalLanguage(LanguageType.valueOf(literaryWorkRequestDTO.getOriginalLanguage()));
        literaryWorkPost.setTitle(literaryWorkRequestDTO.getTitle());
        literaryWorkPost.setCreatedDate(LocalDateTime.now());
        literaryWorkPost.setText(literaryWorkRequestDTO.getText());
        addTranslationOrRomanization(literaryWorkRequestDTO, literaryWorkPost);
        literaryWorkPost.setUser(userService.findLoggedInUser());
        return literaryWorkRepository.save(literaryWorkPost);
    }

    private static String maxWordsForProse(String text, int length) {
        return text.codePoints()
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    private static String maxWordsForPoem(String text, int length) {
        return text.codePoints()
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public void addTranslationOrRomanization(LiteraryWorkRequestDTO literaryWorkRequestDTO, LiteraryWorkPost literaryWorkPost) {
        TranslationRomanization transRomText = new TranslationRomanization();
        if (literaryWorkRequestDTO.getLiteraryWorkType() == LiteraryWorkType.PROSE || literaryWorkRequestDTO.getLiteraryWorkType() == LiteraryWorkType.POETRY) {
            transRomText.setLanguage(LanguageType.valueOf(literaryWorkRequestDTO.getTranslatedLanguage()));
            transRomText.setTranslatedTitle(literaryWorkRequestDTO.getTranslatedTitle());
            transRomText.setTranslatedText(literaryWorkRequestDTO.getTranslatedText());
            transRomText.setRomanizationText(literaryWorkRequestDTO.getRomanizationText());
            transRomText.setCreatedDate(LocalDateTime.now());
            transRomText.setLiteraryWorkPost(literaryWorkPost);
            literaryWorkPost.getTranslationRomanizationList().add(transRomText);
        }
    }

    public TranslationRomanization addTranslationForALiteraryworkOfAUser(TranslationRomanizationRequestDTO translationRomanizationRequestDTO) throws MessagingException {
        LiteraryWorkPost literaryWorkPost = literaryWorkRepository.findById(translationRomanizationRequestDTO.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "literary work was not found"));
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User foundUser = userRepository.findUserByUsername(userDetails.getUsername()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));

        TranslationRomanization translationRomanization = new TranslationRomanization();
        translationRomanization.setLanguage(LanguageType.valueOf(translationRomanizationRequestDTO.getLanguage()));
        translationRomanization.setTranslatedTitle(translationRomanizationRequestDTO.getTranslatedTitle());
        translationRomanization.setTranslatedText(translationRomanizationRequestDTO.getTranslatedText());
        translationRomanization.setRomanizationText(translationRomanizationRequestDTO.getRomanizationText());
        translationRomanization.setCreatedDate(LocalDateTime.now());
        literaryWorkPost.getTranslationRomanizationList().add(translationRomanization);
        translationRomanization.setLiteraryWorkPost(literaryWorkPost);
        mailService.sendApproveMessageForTranslation(foundUser.getEmail(), literaryWorkPost);
        return translationRomanizationRepository.save(translationRomanization);
    }

    public List<LiteraryWorkPost> getAllProse() {
        return literaryWorkRepository.findAllByLiteraryWorkType(LiteraryWorkType.PROSE);
    }

    public List<LiteraryWorkPost> getAllPoetry() {
        return literaryWorkRepository.findAllByLiteraryWorkType(LiteraryWorkType.POETRY);
    }

    public LiteraryWorkResponseDTO getProseByLanguage(Long id, String language) {
        LiteraryWorkPost foundProse = literaryWorkRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "the prose was not found"));

        if (!isTranslated(foundProse, LanguageType.valueOf(language))) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "translation for this language does not exist");
        }
        List<LiteraryWorkPost> allLiteraryWorks = literaryWorkRepository.findAllByTitleAndLiteraryWorkType(foundProse.getTitle(), LiteraryWorkType.PROSE);
        List<Long> allProsePartsIds = getOtherProsePartsIdsByLanguage(language, foundProse, allLiteraryWorks);
        LiteraryWorkResponseDTO literaryWorkResponseDTO = new LiteraryWorkResponseDTO();
        literaryWorkResponseDTO.setOriginalLanguage(foundProse.getOriginalLanguage());
        literaryWorkResponseDTO.setCreatedDate(foundProse.getCreatedDate());
        literaryWorkResponseDTO.setTitle(foundProse.getTitle());
        literaryWorkResponseDTO.setText(foundProse.getText());
        literaryWorkResponseDTO.setOtherProsePartsIds(allProsePartsIds);
        return literaryWorkResponseDTO;
    }

    private List<Long> getOtherProsePartsIdsByLanguage(String language, LiteraryWorkPost foundProse, List<LiteraryWorkPost> allLiteraryWorks) {
        List<Long> allProsePartsIds = allLiteraryWorks.stream()
                .filter(literaryWorkPost -> !literaryWorkPost.getId().equals(foundProse.getId()))
                .filter(literaryWorkPost -> isTranslated(literaryWorkPost, LanguageType.valueOf(language)))
                .sorted(Comparator.comparing(LiteraryWorkPost::getCreatedDate).reversed())
                .map(literaryWorkPost -> literaryWorkPost.getId())
                .collect(Collectors.toList());
        return allProsePartsIds;
    }

    public boolean isTranslated(LiteraryWorkPost literaryWorkPost, LanguageType language) {
        List<LanguageType> translationLanguages = literaryWorkPost.getTranslationRomanizationList().stream()
                .map(translationRomanization -> translationRomanization.getLanguage()).collect(Collectors.toList());
        if (literaryWorkPost.getOriginalLanguage().equals(language) || translationLanguages.contains(language)) {
            return true;
        }
        return false;
    }

    public List<LiteraryWorkPost> getAllProseAndPoetry() {
        List<LiteraryWorkPost> literaryWorkPosts = literaryWorkRepository.findAll();

        return literaryWorkPosts.stream()
                .filter(literaryWorkPost -> literaryWorkPost.getLiteraryWorkType().equals(LiteraryWorkType.PROSE) || literaryWorkPost.getLiteraryWorkType().equals(LiteraryWorkType.POETRY))
                .collect(Collectors.toList());
    }

    public void deleteLiteraryWork(Long id) {
        LiteraryWorkPost foundLiteraryWork = literaryWorkRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "literary work was not found"));
        literaryWorkRepository.delete(foundLiteraryWork);
    }

    public LiteraryWorkPost addLike(Long literaryWorkId) {
        LiteraryWorkPost foundLiteraryWork = literaryWorkRepository.findById(literaryWorkId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "literary work not found"));
        // User likeUser = userService.findLoggedInUser();
        // User postCreator = foundLiteraryWork.getUser();
        foundLiteraryWork.setNumberOfLikes(foundLiteraryWork.getNumberOfLikes() + 1);
        return literaryWorkRepository.save(foundLiteraryWork);
    }

    public LiteraryWorkPost findLiteraryWork(Long id) {
        return literaryWorkRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "literary work was not found"));
    }

    public List<Long> getAllLiteraryWorkAndPhotos() {
        List<LiteraryWorkPost> allLiteraryWorks = literaryWorkRepository.findAll();
        List<PhotoPost> allPhotos = photoRepository.findAll();
        List<Long> allLiteraryWorksIds = allLiteraryWorks.stream().map(literaryWorkPost -> literaryWorkPost.getId()).collect(Collectors.toList());
        List<Long> allPhotosIds = allPhotos.stream().map(photoPost -> photoPost.getId()).collect(Collectors.toList());
        List<Long> allPostsIds = new ArrayList<>();
        allPostsIds.addAll(allLiteraryWorksIds);
        allPostsIds.addAll(allPhotosIds);
        return allPostsIds;
    }
}