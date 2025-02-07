package com.hyundaiautoever.beexample.language.adapter.out;

import com.hyundaiautoever.beexample.infrastructure.repository.WebPageTextElementRepository;
import com.hyundaiautoever.beexample.language.application.domain.WebPageTextElement;
import com.hyundaiautoever.beexample.language.application.port.out.MapTextElementPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MapTextElementAdapter implements MapTextElementPort {

    private final WebPageTextElementRepository repository;

    @Override
    public List<WebPageTextElement> save(List<WebPageTextElement> textElements) {
        return repository.saveAll(textElements);
    }

    @Override
    public WebPageTextElement save(WebPageTextElement textElement) {
        return repository.save(textElement);
    }
}
