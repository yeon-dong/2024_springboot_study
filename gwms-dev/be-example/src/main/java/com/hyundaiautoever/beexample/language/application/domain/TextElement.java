package com.hyundaiautoever.beexample.language.application.domain;

import com.hyundaiautoever.beexample.infrastructure.config.auditing.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @ToString
public class TextElement extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String textCode;
    Locale locale;
    String description;

    @OneToMany(mappedBy = "textElement")
    private final List<WebPageTextElement> webPages = new ArrayList<>();

    public void updateDescription(String description) {
        if (description == null) {
            throw new IllegalArgumentException("Description cannot be null");
        }
        this.description = description;
    }

    public void updateFrom(TextElement target) {
        this.textCode = target.getTextCode();
        this.locale = target.getLocale();
        this.description = target.getDescription();
    }

    private TextElement(String textCode, Locale locale, String description) {
        this.textCode = textCode;
        this.locale = locale;
        this.description = description;
    }

    public static TextElement of (String textCode, Locale locale, String description) {
        return new TextElement(textCode, locale, description);
    }

}
