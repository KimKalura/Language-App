package com.spring.languageapp.repository;

import com.spring.languageapp.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {

}
