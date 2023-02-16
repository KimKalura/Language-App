package com.spring.languageapp.service;

import com.spring.languageapp.model.LiteraryWorkPost;
import com.spring.languageapp.model.PhotoPost;
import com.spring.languageapp.repository.LiteraryWorkRepository;
import com.spring.languageapp.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedService {

    private LiteraryWorkRepository literaryWorkRepository;
    private PhotoRepository photoRepository;

    @Autowired
    public FeedService(LiteraryWorkRepository literaryWorkRepository, PhotoRepository photoRepository) {
        this.literaryWorkRepository = literaryWorkRepository;
        this.photoRepository = photoRepository;
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
