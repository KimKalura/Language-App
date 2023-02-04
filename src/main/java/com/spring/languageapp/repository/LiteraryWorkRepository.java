package com.spring.languageapp.repository;

import com.spring.languageapp.dto.LiteraryWorkRequestDTO;
import com.spring.languageapp.model.LanguageType;
import com.spring.languageapp.model.LiteraryWorkPost;
import com.spring.languageapp.model.LiteraryWorkType;
import com.spring.languageapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LiteraryWorkRepository extends JpaRepository<LiteraryWorkPost, Long> {

    List<LiteraryWorkPost> findAllByLiteraryWorkType(LiteraryWorkType literaryWorkType);

    List<LiteraryWorkPost> findAllByTitleAndLiteraryWorkType(String title, LiteraryWorkType literaryWorkType);

    List<LiteraryWorkPost> findAllByText(String text);


}
