package com.hyundaiautoever.beexample.language.adapter.in.dto;

import com.hyundaiautoever.beexample.language.application.domain.TextElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Locale;

@Data @NoArgsConstructor
@AllArgsConstructor
public class TextElementResponseDto {
    Long id;
    String textCode;
    Locale locale;
    String description;

    public static TextElementResponseDto from(TextElement textElement) {
        return new TextElementResponseDto(
                textElement.getId(),
                textElement.getTextCode(),
                textElement.getLocale(),
                textElement.getDescription()
        );
   }

}
