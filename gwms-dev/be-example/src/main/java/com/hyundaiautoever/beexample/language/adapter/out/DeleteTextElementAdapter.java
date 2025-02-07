package com.hyundaiautoever.beexample.language.adapter.out;

import com.hyundaiautoever.beexample.infrastructure.repository.TextElementRepository;
import com.hyundaiautoever.beexample.language.application.domain.TextElement;
import com.hyundaiautoever.beexample.language.application.port.out.DeleteTextElementPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DeleteTextElementAdapter implements DeleteTextElementPort {

    private final TextElementRepository repository;

    @Override
    public void delete(TextElement textElement) {
        repository.delete(textElement);
    }
}
