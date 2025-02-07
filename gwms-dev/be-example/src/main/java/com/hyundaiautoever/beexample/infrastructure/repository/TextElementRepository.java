package com.hyundaiautoever.beexample.infrastructure.repository;


import com.hyundaiautoever.beexample.language.application.domain.TextElement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TextElementRepository extends JpaRepository<TextElement, Long> {
}
