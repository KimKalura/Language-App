package com.spring.languageapp.repository;

import com.spring.languageapp.model.PhotoPost;
import com.spring.languageapp.model.Role;
import com.spring.languageapp.model.User;
import org.apache.el.stream.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends JpaRepository<PhotoPost,Long> {

//  List<PhotoPost> findByUser(User user);
//  Optional<PhotoPost> findByName(String fileName);
//  PhotoPost findByName(String name);

}
