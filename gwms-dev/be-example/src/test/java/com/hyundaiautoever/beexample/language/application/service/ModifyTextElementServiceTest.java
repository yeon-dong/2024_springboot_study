package com.hyundaiautoever.beexample.language.application.service;

import com.hyundaiautoever.beexample.language.application.domain.TextElement;
import com.hyundaiautoever.beexample.language.application.port.out.ModifyTextElementPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ModifyTextElementServiceTest {

    @Mock
    ModifyTextElementPort modifyTextElementPort;

    @Test
    @DisplayName("Text Element 변경을 위한 Port를 정상적으로 호출한다.")
    void testModifyTextElement() {
        //given
        TextElement dictionary = TextElement.of("TEXT-001", new Locale("US"), "Test");
        ModifyTextElementService service = new ModifyTextElementService(modifyTextElementPort);

        //when
        service.modify(dictionary);

        verify(modifyTextElementPort).save(dictionary);
    }

    @Test
    @DisplayName("Text Element 변경을 위한 객체가 Null인 경우 에러를 발생한다.")
    void testModifyTextElementWithNull() {
        //given
        ModifyTextElementService service = new ModifyTextElementService(modifyTextElementPort);

        //when //then
        assertThrows(AssertionError.class, () -> service.modify(null));
    }
}