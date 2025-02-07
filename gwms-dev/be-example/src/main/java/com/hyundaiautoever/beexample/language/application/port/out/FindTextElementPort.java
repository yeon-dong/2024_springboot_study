package com.hyundaiautoever.beexample.language.application.port.out;

import com.hyundaiautoever.beexample.language.application.domain.TextElement;

import java.util.List;
import java.util.Locale;

public interface FindTextElementPort {
    TextElement existBy(Locale locale, String textCode);
    List<TextElement> findBy(String uri, Locale locale);
}
