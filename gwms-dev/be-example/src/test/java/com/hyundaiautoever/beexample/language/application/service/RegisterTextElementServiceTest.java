package com.hyundaiautoever.beexample.language.application.service;

import com.hyundaiautoever.beexample.language.application.domain.TextElement;
import com.hyundaiautoever.beexample.language.application.port.in.SearchTextElementUseCase;
import com.hyundaiautoever.beexample.language.application.port.out.RegisterTextElementPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RegisterTextElementServiceTest {

    @Mock
    RegisterTextElementPort registerTextElementPort;

    @Mock
    SearchTextElementUseCase searchTextElementUseCase;

    @Test
    @DisplayName("다국어 사전에 등록할때 중복이 없는 경우 등록 Port를 정상적으로 호출한다.")
    void testRegisterTextElement() {
        //given
        TextElement textElement = TextElement.of("TEXT-001", Locale.US, "Test");
        given(searchTextElementUseCase.existBy(textElement.getTextCode(), textElement.getLocale())).willReturn(null);
        RegisterTextElementService registerTextElementService
                = new RegisterTextElementService(searchTextElementUseCase, registerTextElementPort);

        //when
        registerTextElementService.register(textElement);

        //then
        verify(registerTextElementPort).save(textElement);
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("등록 파라미터로 Null이 전달되면 AssertionError가 발생한다.")
    void testRegisterTextElementWithNull(TextElement textElement) {
        //given
        RegisterTextElementService registerTextElementService
                = new RegisterTextElementService(searchTextElementUseCase, registerTextElementPort);

        //when //then
        assertThrows(AssertionError.class, () -> registerTextElementService.register(textElement));
    }

    @Test
    @DisplayName("Locale과 Text Code가 이미 등록된 경우 Description만 수정한다.")
    void testRegisterTextElementWithExistElement() {
        //given
        TextElement textElement = TextElement.of("TEXT-001", Locale.US, "Text");
        TextElement existElement = TextElement.of("TEXT-001", Locale.US, "Exist Text");
        given(searchTextElementUseCase.existBy(textElement.getTextCode(), textElement.getLocale())).willReturn(existElement);
        RegisterTextElementService registerTextElementService
                = new RegisterTextElementService(searchTextElementUseCase, registerTextElementPort);

        //when
        registerTextElementService.register(textElement);

        //then
        verify(registerTextElementPort).save(existElement);
        assertThat(existElement.getDescription()).isEqualTo(textElement.getDescription());
    }


}