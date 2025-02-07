package com.hyundaiautoever.beexample.language.application.port.in;


import com.hyundaiautoever.beexample.language.application.domain.TextElement;

import java.util.List;
import java.util.Locale;

public interface SearchTextElementUseCase {
    TextElement existBy(String textCode, Locale locale);
    List<TextElement> findBy(String uri, Locale locale);
}
