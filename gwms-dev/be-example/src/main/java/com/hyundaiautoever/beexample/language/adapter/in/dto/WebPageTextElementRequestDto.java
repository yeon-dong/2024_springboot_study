package com.hyundaiautoever.beexample.language.adapter.in.dto;

import com.hyundaiautoever.beexample.language.application.domain.TextElement;
import com.hyundaiautoever.beexample.language.application.domain.WebPage;
import com.hyundaiautoever.beexample.language.application.domain.WebPageTextElement;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class WebPageTextElementRequestDto {
    WebPage webPage;
    ArrayList<TextElement> textElements = new ArrayList<>();

    public List<WebPageTextElement> toEntityList() {
        return textElements.stream().map(
                textElement -> WebPageTextElement.of(this.webPage, textElement)
        ).toList();
    }

    public WebPageTextElement toEntity() {
        if (textElements.isEmpty()) {
            throw new IllegalArgumentException("textElements is empty");
        }
        return WebPageTextElement.of(this.webPage, this.textElements.get(0));
    }
}
