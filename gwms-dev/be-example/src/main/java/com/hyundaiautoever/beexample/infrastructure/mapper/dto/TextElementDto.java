package com.hyundaiautoever.beexample.infrastructure.mapper.dto;

import com.hyundaiautoever.beexample.language.application.domain.TextElement;
import lombok.Data;

import java.util.Locale;

@Data
public class TextElementDto {
    Long id;
    String textCode;
    String locale;
    String description;

    public TextElement toEntity() {
        return new TextElement(id,textCode,new Locale(locale),description);
    }
}
