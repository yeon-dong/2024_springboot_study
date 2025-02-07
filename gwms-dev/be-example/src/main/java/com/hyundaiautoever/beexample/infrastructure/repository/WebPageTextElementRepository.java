package com.hyundaiautoever.beexample.infrastructure.repository;


import com.hyundaiautoever.beexample.language.application.domain.WebPageTextElement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WebPageTextElementRepository extends JpaRepository<WebPageTextElement, Long> {
}
