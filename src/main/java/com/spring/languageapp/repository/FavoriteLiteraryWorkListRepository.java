package com.spring.languageapp.repository;

import com.spring.languageapp.model.FavoriteLiteraryWorkList;
import com.spring.languageapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteLiteraryWorkListRepository extends JpaRepository<FavoriteLiteraryWorkList, Long> {

    List<FavoriteLiteraryWorkList> findAllByUser(User user);
    List<FavoriteLiteraryWorkList> findAllByUserOrderByLiteraryWorkPost(User user);
}
