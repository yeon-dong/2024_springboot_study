package com.hyundaiautoever.beexample.language.application.service;

import com.hyundaiautoever.beexample.language.application.domain.WebPageTextElement;
import com.hyundaiautoever.beexample.language.application.port.in.MapTextElementUseCase;
import com.hyundaiautoever.beexample.language.application.port.out.MapTextElementPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class MapTextElementService implements MapTextElementUseCase {

    private final MapTextElementPort mapTextElementPort;

    @Override
    public List<WebPageTextElement> map(List<WebPageTextElement> textElements) {
        return mapTextElementPort.save(textElements);
    }

    @Override
    public WebPageTextElement map(WebPageTextElement textElement) {
        return mapTextElementPort.save(textElement);
    }
}
