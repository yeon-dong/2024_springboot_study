package com.hyundaiautoever.beexample.language.application.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TextElementTest {

    @Test
    @DisplayName("update를 하기위한 Description의 값이 null인 경우 IllegalArgument Exception이 발생한다.")
    void updateDescriptionNull() {
        //given
        TextElement textElement = TextElement.of("TEXT-001", new Locale("KOREAN"), "테스트");

        //when //then
        assertThrows(IllegalArgumentException.class, ()-> textElement.updateDescription(null));
    }

    @Test
    @DisplayName("Description을 정상적으로 변경할 수 있다.")
    void updateDescription() {
        //given
        TextElement textElement = TextElement.of("TEXT-001", new Locale("KOREAN"), "테스트");

        //when
        textElement.updateDescription("변경테스트");

        //then
        assertThat(textElement.getDescription()).isEqualTo("변경테스트");

    }
}