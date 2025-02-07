package com.hyundaiautoever.beexample.language.application.port.in;


import com.hyundaiautoever.beexample.language.application.domain.WebPageTextElement;

import java.util.List;

public interface MapTextElementUseCase {
    List<WebPageTextElement> map(List<WebPageTextElement> textElements);
    WebPageTextElement map(WebPageTextElement textElements);
}
