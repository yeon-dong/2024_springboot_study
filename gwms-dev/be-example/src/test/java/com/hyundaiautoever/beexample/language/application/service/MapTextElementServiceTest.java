package com.hyundaiautoever.beexample.language.application.service;

import com.hyundaiautoever.beexample.language.application.domain.TextElement;
import com.hyundaiautoever.beexample.language.application.domain.WebPage;
import com.hyundaiautoever.beexample.language.application.domain.WebPageTextElement;
import com.hyundaiautoever.beexample.language.application.port.out.MapTextElementPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Locale;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MapTextElementServiceTest {

    @Mock
    MapTextElementPort mapTextElementPort;

    @Test
    @DisplayName("Text Element 와 Web Page의 여러개의 연결정보를 저장하는 Port를 정상적으로 호출한다.")
    void testGetTextElementBatch() {
        //given
        ArrayList<WebPageTextElement> webPageTextElements = new ArrayList<>();
        webPageTextElements.add(WebPageTextElement.of(WebPage.of("test/test"),
                     TextElement.of("TEXT-001", new Locale("US"), "TEST")));
        webPageTextElements.add(WebPageTextElement.of(WebPage.of("test/test"),
                TextElement.of("TEXT-002", new Locale("US"), "TEST")));
        webPageTextElements.add(WebPageTextElement.of(WebPage.of("test/test"),
                TextElement.of("TEXT-003", new Locale("US"), "TEST")));

        MapTextElementService mapTextElementService = new MapTextElementService(mapTextElementPort);

        //when
        mapTextElementService.map(webPageTextElements);

        //then
        verify(mapTextElementPort, times(1)).save(webPageTextElements);
    }

    @Test
    @DisplayName("Text Element 와 Web Page의 단건의 연결정보를 저장하는 Port를 정상적으로 호출한다.")
    void testGetTextElementSingle() {
        //given
        WebPageTextElement webPageTextElement = WebPageTextElement.of(WebPage.of("test/test"),
                TextElement.of("TEXT-001", new Locale("US"), "TEST"));

        MapTextElementService mapTextElementService = new MapTextElementService(mapTextElementPort);

        //when
        mapTextElementService.map(webPageTextElement);

        //then
        verify(mapTextElementPort, times(1)).save(webPageTextElement);
    }
}