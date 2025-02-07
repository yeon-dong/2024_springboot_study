package com.hyundaiautoever.beexample.language.adapter.in.dto;

import com.hyundaiautoever.beexample.language.application.domain.TextElement;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Locale;

@Data
@AllArgsConstructor
public class TextElementRequestDto {
    String textCode;
    Locale locale;
    String description;

    public static TextElementRequestDto of(String textCode, Locale locale, String description) {
        return new TextElementRequestDto(textCode, locale, description);
    }

    public TextElement toEntity() {
        return TextElement.of(this.textCode, this.locale, this.description);
   }

}
