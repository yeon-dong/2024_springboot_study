package com.hyundaiautoever.beexample.language.adapter.in;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyundaiautoever.beexample.language.adapter.in.dto.WebPageTextElementRequestDto;
import com.hyundaiautoever.beexample.language.application.domain.TextElement;
import com.hyundaiautoever.beexample.language.application.domain.WebPage;
import com.hyundaiautoever.beexample.language.application.domain.WebPageTextElement;
import com.hyundaiautoever.beexample.language.application.port.in.MapTextElementUseCase;
import com.nimbusds.jose.util.StandardCharset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MapTextElementController.class)
@AutoConfigureRestDocs
@ExtendWith(MockitoExtension.class)
class MapTextElementControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    MapTextElementUseCase mapTextElementUseCase;


    @Test
    @DisplayName("WebPage와 TextElement를 정상적으로 연결한다.")
    void testMappingWebPage() throws Exception {
        //given
        ArrayList<TextElement> textElements = new ArrayList<>(Arrays.asList(
                new TextElement(1L, "TEXT-001", Locale.KOREAN, "정상적으로 처리하였다."),
                new TextElement(2L, "TEXT-001", Locale.ENGLISH, "It is processed successfully."),
                new TextElement(3L, "TEXT-001", Locale.JAPANESE, "それは成功しました")
        ));

        WebPageTextElementRequestDto requestDto = new WebPageTextElementRequestDto();
        requestDto.setWebPage(new WebPage("uri"));
        requestDto.setTextElements(textElements);

        WebPageTextElement mockWebPageTextElement = new WebPageTextElement(requestDto.getWebPage(), requestDto.getTextElements().get(0));

        given(mapTextElementUseCase.map(any(WebPageTextElement.class))).willReturn(mockWebPageTextElement);

        //when
        ResultActions resultActions = mockMvc.perform(post("/api/v1/language/text/mapping")// RestDocumentationRequestBuilders의 static method 이용
                .content(objectMapper.writeValueAsString(requestDto))
                .with(SecurityMockMvcRequestPostProcessors.jwt())
                .characterEncoding(StandardCharset.UTF_8)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        // then
        resultActions.andExpect(status().isOk())
                .andDo(document("text-mapping", // test 수행시 ./build/generated-snippets/ 하위에 지정한 문자열의 폴더 하위에 문서가 작성됨.
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()), // 생성될 문서의 indent를 자동정렬해주는 메소드
                        requestFields(
                                fieldWithPath("webPage").type(JsonFieldType.OBJECT).description("webPage"),
                                fieldWithPath("webPage.registerDate").type(JsonFieldType.STRING).optional().description("registerDate"),
                                fieldWithPath("webPage.updateDate").type(JsonFieldType.STRING).optional().description("updateDate"),
                                fieldWithPath("webPage.registerUserSeq").type(JsonFieldType.NUMBER).optional().description("registerUserSeq"),
                                fieldWithPath("webPage.updateUserSeq").type(JsonFieldType.NUMBER).optional().description("updateUserSeq"),
                                fieldWithPath("webPage.deleteDate").type(JsonFieldType.STRING).optional().description("deleteDate"),
                                fieldWithPath("webPage.deleteUserSeq").type(JsonFieldType.NUMBER).optional().description("deleteUserSeq"),
                                fieldWithPath("webPage.id").type(JsonFieldType.NUMBER).optional().description("webPage ID"),
                                fieldWithPath("webPage.uri").type(JsonFieldType.STRING).description("webPage URI"),
                                fieldWithPath("webPage.textElements").type(JsonFieldType.ARRAY).description("textElements"),
                                fieldWithPath("textElements[]").type(JsonFieldType.ARRAY).description("textElements"),
                                fieldWithPath("textElements[].registerDate").type(JsonFieldType.STRING).optional().description("registerDate"),
                                fieldWithPath("textElements[].updateDate").type(JsonFieldType.STRING).optional().description("updateDate"),
                                fieldWithPath("textElements[].registerUserSeq").type(JsonFieldType.NUMBER).optional().description("registerUserSeq"),
                                fieldWithPath("textElements[].updateUserSeq").type(JsonFieldType.NUMBER).optional().description("updateUserSeq"),
                                fieldWithPath("textElements[].deleteDate").type(JsonFieldType.STRING).optional().description("deleteDate"),
                                fieldWithPath("textElements[].deleteUserSeq").type(JsonFieldType.NUMBER).optional().description("deleteUserSeq"),
                                fieldWithPath("textElements[].id").type(JsonFieldType.NUMBER).description("ID"),
                                fieldWithPath("textElements[].textCode").type(JsonFieldType.STRING).description("텍스트요소"),
                                fieldWithPath("textElements[].locale").type(JsonFieldType.STRING).description("언어"),
                                fieldWithPath("textElements[].description").type(JsonFieldType.STRING).description("내역"),
                                fieldWithPath("textElements[].webPages").type(JsonFieldType.ARRAY).description("webPages")
                        ),
                        responseFields(
                                fieldWithPath("returnCode").type(JsonFieldType.STRING).description("응답 코드"),
                                fieldWithPath("returnMessage").type(JsonFieldType.STRING).description("응답 메시지"),
                                fieldWithPath("data").type(JsonFieldType.OBJECT).description("응답 데이터"),
                                fieldWithPath("data.registerDate").type(JsonFieldType.STRING).optional().description("registerDate"),
                                fieldWithPath("data.updateDate").type(JsonFieldType.STRING).optional().description("updateDate"),
                                fieldWithPath("data.registerUserSeq").type(JsonFieldType.NUMBER).optional().description("registerUserSeq"),
                                fieldWithPath("data.updateUserSeq").type(JsonFieldType.NUMBER).optional().description("registerUserSeq"),
                                fieldWithPath("data.deleteDate").type(JsonFieldType.STRING).optional().description("deleteDate"),
                                fieldWithPath("data.deleteUserSeq").type(JsonFieldType.NUMBER).optional().description("deleteUserSeq"),
                                fieldWithPath("data.id").type(JsonFieldType.NUMBER).optional().description("id"),
                                fieldWithPath("data.webPage").type(JsonFieldType.OBJECT).description("webPage"),
                                fieldWithPath("data.webPage.registerDate").type(JsonFieldType.STRING).optional().description("registerDate"),
                                fieldWithPath("data.webPage.updateDate").type(JsonFieldType.STRING).optional().description("updateDate"),
                                fieldWithPath("data.webPage.registerUserSeq").type(JsonFieldType.NUMBER).optional().description("registerUserSeq"),
                                fieldWithPath("data.webPage.updateUserSeq").type(JsonFieldType.NUMBER).optional().description("updateUserSeq"),
                                fieldWithPath("data.webPage.deleteDate").type(JsonFieldType.STRING).optional().description("deleteDate"),
                                fieldWithPath("data.webPage.deleteUserSeq").type(JsonFieldType.NUMBER).optional().description("deleteUserSeq"),
                                fieldWithPath("data.webPage.id").type(JsonFieldType.NUMBER).optional().description("webPage ID"),
                                fieldWithPath("data.webPage.uri").type(JsonFieldType.STRING).description("webPage URI"),
                                fieldWithPath("data.webPage.textElements").type(JsonFieldType.ARRAY).description("textElements"),
                                fieldWithPath("data.textElement").type(JsonFieldType.OBJECT).description("textElement"),
                                fieldWithPath("data.textElement.registerDate").type(JsonFieldType.STRING).optional().description("registerDate"),
                                fieldWithPath("data.textElement.updateDate").type(JsonFieldType.STRING).optional().description("updateDate"),
                                fieldWithPath("data.textElement.registerUserSeq").type(JsonFieldType.NUMBER).optional().description("registerUserSeq"),
                                fieldWithPath("data.textElement.updateUserSeq").type(JsonFieldType.NUMBER).optional().description("updateUserSeq"),
                                fieldWithPath("data.textElement.deleteDate").type(JsonFieldType.STRING).optional().description("deleteDate"),
                                fieldWithPath("data.textElement.deleteUserSeq").type(JsonFieldType.NUMBER).optional().description("deleteUserSeq"),
                                fieldWithPath("data.textElement.id").type(JsonFieldType.NUMBER).description("textElement ID"),
                                fieldWithPath("data.textElement.textCode").type(JsonFieldType.STRING).description("텍스트요소"),
                                fieldWithPath("data.textElement.locale").type(JsonFieldType.STRING).description("언어"),
                                fieldWithPath("data.textElement.description").type(JsonFieldType.STRING).description("내역"),
                                fieldWithPath("data.textElement.webPages").type(JsonFieldType.ARRAY).description("webPages")
                        )

                )).andExpectAll(jsonPath("$.returnCode").value("S"));
    }

    @Test
    @DisplayName("WebPageTextElementList를 정상적으로 저장한다.")
    void testMappingWebPageList() throws Exception {
        //given

        ArrayList<TextElement> textElements = new ArrayList<>(Arrays.asList(
                new TextElement(1L, "TEXT-001", Locale.KOREAN, "정상적으로 처리하였다."),
                new TextElement(2L, "TEXT-001", Locale.ENGLISH, "It is processed successfully."),
                new TextElement(3L, "TEXT-001", Locale.JAPANESE, "それは成功しました")
        ));

        WebPageTextElementRequestDto requestDto = new WebPageTextElementRequestDto();
        requestDto.setWebPage(new WebPage("uri"));
        requestDto.setTextElements(textElements);

        List<WebPageTextElement> webPageTextElements = requestDto.getTextElements().stream()
                .map(textElement -> new WebPageTextElement(requestDto.getWebPage(), textElement))
                .toList();

        given(mapTextElementUseCase.map(anyList())).willReturn(webPageTextElements);

        //when
        ResultActions resultActions = mockMvc.perform(post("/api/v1/language/text/mapping/batch")// RestDocumentationRequestBuilders의 static method 이용
                .content(objectMapper.writeValueAsString(requestDto))
                .with(SecurityMockMvcRequestPostProcessors.jwt())
                .characterEncoding(StandardCharset.UTF_8)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        // then
        resultActions.andExpect(status().isOk())
                .andDo(document("text-mapping-list", // test 수행시 ./build/generated-snippets/ 하위에 지정한 문자열의 폴더 하위에 문서가 작성됨.
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()), // 생성될 문서의 indent를 자동정렬해주는 메소드
                        requestFields(
                                fieldWithPath("webPage").type(JsonFieldType.OBJECT).description("웹 페이지 정보"),
                                fieldWithPath("webPage.registerDate").type(JsonFieldType.STRING).optional().description("registerDate"),
                                fieldWithPath("webPage.updateDate").type(JsonFieldType.STRING).optional().description("updateDate"),
                                fieldWithPath("webPage.registerUserSeq").type(JsonFieldType.NUMBER).optional().description("registerUserSeq"),
                                fieldWithPath("webPage.updateUserSeq").type(JsonFieldType.NUMBER).optional().description("updateUserSeq"),
                                fieldWithPath("webPage.deleteDate").type(JsonFieldType.STRING).optional().description("deleteDate"),
                                fieldWithPath("webPage.deleteUserSeq").type(JsonFieldType.NUMBER).optional().description("deleteUserSeq"),
                                fieldWithPath("webPage.id").type(JsonFieldType.NUMBER).optional().description("webPage ID"),
                                fieldWithPath("webPage.uri").type(JsonFieldType.STRING).description("webPage URI"),
                                fieldWithPath("webPage.textElements").type(JsonFieldType.ARRAY).description("textElements"),
                                fieldWithPath("textElements[]").type(JsonFieldType.ARRAY).description("textElements"),
                                fieldWithPath("textElements[].registerDate").type(JsonFieldType.STRING).optional().description("registerDate"),
                                fieldWithPath("textElements[].updateDate").type(JsonFieldType.STRING).optional().description("updateDate"),
                                fieldWithPath("textElements[].registerUserSeq").type(JsonFieldType.NUMBER).optional().description("registerUserSeq"),
                                fieldWithPath("textElements[].updateUserSeq").type(JsonFieldType.NUMBER).optional().description("updateUserSeq"),
                                fieldWithPath("textElements[].deleteDate").type(JsonFieldType.STRING).optional().description("deleteDate"),
                                fieldWithPath("textElements[].deleteUserSeq").type(JsonFieldType.NUMBER).optional().description("deleteUserSeq"),
                                fieldWithPath("textElements[].id").type(JsonFieldType.NUMBER).description("textElements ID"),
                                fieldWithPath("textElements[].textCode").type(JsonFieldType.STRING).description("텍스트요소"),
                                fieldWithPath("textElements[].locale").type(JsonFieldType.STRING).description("언어"),
                                fieldWithPath("textElements[].description").type(JsonFieldType.STRING).description("내역"),
                                fieldWithPath("textElements[].webPages").type(JsonFieldType.ARRAY).description("webPages")
                        ),
                        responseFields(
                                fieldWithPath("returnCode").type(JsonFieldType.STRING).description("응답 코드"),
                                fieldWithPath("returnMessage").type(JsonFieldType.STRING).description("응답 메시지"),
                                fieldWithPath("data").type(JsonFieldType.NUMBER).description("결과 size")
                        )

                )).andExpectAll(jsonPath("$.returnCode").value("S"));
    }

}