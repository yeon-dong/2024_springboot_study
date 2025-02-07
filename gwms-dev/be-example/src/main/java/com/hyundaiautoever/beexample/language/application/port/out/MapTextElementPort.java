package com.hyundaiautoever.beexample.language.application.port.out;


import com.hyundaiautoever.beexample.language.application.domain.WebPageTextElement;

import java.util.List;

public interface MapTextElementPort {
    List<WebPageTextElement> save(List<WebPageTextElement> textElements);
    WebPageTextElement save(WebPageTextElement textElement);
}
