package com.hyundaiautoever.beexample.language.adapter.out;

import com.hyundaiautoever.beexample.infrastructure.repository.TextElementRepository;
import com.hyundaiautoever.beexample.language.application.domain.TextElement;
import com.hyundaiautoever.beexample.language.application.port.out.ModifyTextElementPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ModifyTextElementAdapter implements ModifyTextElementPort {

    private final TextElementRepository repository;

    @Override
    public TextElement save(TextElement textElement) {
        return repository.save(textElement);
    }
}
