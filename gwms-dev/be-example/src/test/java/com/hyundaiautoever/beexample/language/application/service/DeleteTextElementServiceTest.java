package com.hyundaiautoever.beexample.language.application.service;

import com.hyundaiautoever.beexample.language.application.domain.TextElement;
import com.hyundaiautoever.beexample.language.application.port.out.DeleteTextElementPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeleteTextElementServiceTest {

    @Mock
    DeleteTextElementPort deleteTextElementPort;

    @Test
    @DisplayName("Text Element를 삭제하기 위한 Port를 정상적으로 호출한다.")
    void deleteTextElement() {
        //given
        TextElement textElement = TextElement.of("TEXT-001", new Locale("KOREA"), "테스트");
        DeleteTextElementService deleteTextElementService = new DeleteTextElementService(deleteTextElementPort);

        //when
        deleteTextElementService.delete(textElement);

        // then
        verify(deleteTextElementPort).delete(textElement);

    }

    @Test
    @DisplayName("Text Element 삭제를 위한 객체가 Null인 경우 에러를 발생한다.")
    void testDeleteTextElementWithNull() {
        //given
        DeleteTextElementService service = new DeleteTextElementService(deleteTextElementPort);

        //when //then
        assertThrows(AssertionError.class, () -> service.delete(null));
    }


}