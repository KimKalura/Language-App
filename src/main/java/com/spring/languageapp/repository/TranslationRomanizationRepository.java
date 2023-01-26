package com.spring.languageapp.repository;

import com.spring.languageapp.model.LanguageType;
import com.spring.languageapp.model.TranslationRomanization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranslationRomanizationRepository extends JpaRepository<TranslationRomanization,Long> {

}
