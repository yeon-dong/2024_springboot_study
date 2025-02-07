package com.hyundaiautoever.beexample.language.application.service;

import com.hyundaiautoever.beexample.language.application.domain.TextElement;
import com.hyundaiautoever.beexample.language.application.domain.WebPage;
import com.hyundaiautoever.beexample.language.application.domain.WebPageTextElement;
import com.hyundaiautoever.beexample.language.application.port.out.FindTextElementPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class SearchTextElementServiceTest {

    @Mock
    FindTextElementPort findTextElementPort;

    @ParameterizedTest
    @CsvSource({"KOREAN, TEXT-001", "ENGLISH, TEXT-001"})
    @DisplayName("Locale과 Text-ID에 Matching 되는 element를 검색한다.")
    void testSearchTextElementByLocaleAndTextCode(String locale, String textCode) {
        //given
        ArrayList<TextElement> list = getTextElements();
        given(findTextElementPort.existBy(new Locale(locale), textCode)).willReturn(
                list.stream()
                        .filter(item -> item.getLocale().equals(new Locale(locale)) &&
                                item.getTextCode().equals(textCode)).findFirst().orElse(null));

        //when
        SearchTextElementService service = new SearchTextElementService(findTextElementPort);
        TextElement dictionary = service.existBy(textCode, new Locale(locale));

        //then
        assertNotNull(dictionary);
        assertEquals(dictionary.getTextCode(), textCode);
        assertEquals(dictionary.getLocale(), new Locale(locale));
    }

    @ParameterizedTest
    @CsvSource({"aaa/bbb, US, 2", "bbb/ccc, US, 1"})
    @DisplayName("uri와 Text-ID에 Matching 되는 element를 검색한다.")
    void testSearchTextElementByUriAndLocale(String uri, String locale, String expectedSize) {
        //given
        ArrayList<WebPageTextElement> list = getWebPageTextElements();
        List<TextElement> foundList = list.stream().filter(
                        item -> item.getWebPage().getUri().equals(uri) &&
                                item.getTextElement().getLocale().equals(new Locale(locale))
                ).map(WebPageTextElement::getTextElement)
                .toList();
        given(findTextElementPort.findBy(uri, new Locale(locale))).willReturn(foundList);

        //when
        SearchTextElementService service = new SearchTextElementService(findTextElementPort);
        List<TextElement> textElements = service.findBy(uri, new Locale(locale));

        //then
        assertEquals(textElements.size(), Integer.valueOf(expectedSize));
    }


    private ArrayList<TextElement> getTextElements() {
        ArrayList<TextElement> list = new ArrayList<>();
        TextElement korea = TextElement.of("TEXT-001",new Locale("KOREAN"), "");
        TextElement us = TextElement.of("TEXT-001", new Locale("ENGLISH"), "");
        list.add(korea);
        list.add(us);
        return list;
    }

    private ArrayList<WebPageTextElement> getWebPageTextElements() {
        ArrayList<WebPageTextElement> webPageTextElements = new ArrayList<>();
        webPageTextElements.add(WebPageTextElement.of(WebPage.of("aaa/bbb"),
                TextElement.of("TEXT-001", new Locale("US"), "TEST")));
        webPageTextElements.add(WebPageTextElement.of(WebPage.of("aaa/bbb"),
                TextElement.of("TEXT-002", new Locale("US"), "TEST")));
        webPageTextElements.add(WebPageTextElement.of(WebPage.of("bbb/ccc"),
                TextElement.of("TEXT-003", new Locale("US"), "TEST")));
        return webPageTextElements;
    }

}