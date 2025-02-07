package com.hyundaiautoever.beexample.language.application.domain;

import com.hyundaiautoever.beexample.infrastructure.config.auditing.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor @ToString
public class WebPageTextElement extends BaseEntity {

    @Id @GeneratedValue
    Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    WebPage webPage;

    @ManyToOne(cascade = CascadeType.PERSIST)
    TextElement textElement;

    public WebPageTextElement(WebPage webPage, TextElement textElement) {
        this.webPage = webPage;
        this.textElement = textElement;
    }

    public static WebPageTextElement of(WebPage webPage, TextElement textElement) {
        return new WebPageTextElement(webPage, textElement);
    }
}
