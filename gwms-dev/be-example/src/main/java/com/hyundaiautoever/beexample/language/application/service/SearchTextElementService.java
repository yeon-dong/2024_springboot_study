package com.hyundaiautoever.beexample.language.application.service;

import com.hyundaiautoever.beexample.language.application.domain.TextElement;
import com.hyundaiautoever.beexample.language.application.port.in.SearchTextElementUseCase;
import com.hyundaiautoever.beexample.language.application.port.out.FindTextElementPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
class SearchTextElementService implements SearchTextElementUseCase {

    private final FindTextElementPort findTextElementPort;

    @Override
    public TextElement existBy(String textCode, Locale locale) {
        return findTextElementPort.existBy(locale, textCode);
    }

    @Override
    public List<TextElement> findBy(String uri, Locale locale) {
        return findTextElementPort.findBy(uri, locale);
    }
}
