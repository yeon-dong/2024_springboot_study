package com.hyundaiautoever.beexample.language.adapter.in;

import com.hyundaiautoever.beexample.language.application.domain.TextElement;
import com.hyundaiautoever.beexample.language.application.port.in.SearchTextElementUseCase;
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

import java.util.List;
import java.util.Locale;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = SearchTextElementController.class)
@AutoConfigureRestDocs
@ExtendWith(MockitoExtension.class)
class SearchTextElementControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    SearchTextElementUseCase searchTextElementUseCase;

    @Test
    @DisplayName("검색조건에 맞는 Text Element를 정상적으로 조회한다.")
    void searchTest() throws Exception {
        //given
        given(searchTextElementUseCase.findBy(any(), any())).willReturn(
            List.of(new TextElement(1L, "TEXT-001", Locale.KOREAN, "정상적으로 처리하였다."),
                    new TextElement(2L, "TEXT-001", Locale.ENGLISH, "It is processed successfully."),
                    new TextElement(3L, "TEXT-001", Locale.JAPANESE, "それは成功しました"))
        );

        //when
        ResultActions resultActions = mockMvc.perform(get("/api/v1/language/text/search")// RestDocumentationRequestBuilders의 static method 이용
                .param("uri", "any_path")
                .param("locale", (String) null)
                .with(SecurityMockMvcRequestPostProcessors.jwt())
                .characterEncoding(StandardCharset.UTF_8)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharset.UTF_8));

        // then
        resultActions.andExpect(status().isOk())
                .andDo(document("text-search", // test 수행시 ./build/generated-snippets/ 하위에 지정한 문자열의 폴더 하위에 문서가 작성됨.
                        preprocessResponse(prettyPrint()), // 생성될 문서의 indent를 자동정렬해주는 메소드
                        responseFields(
                                fieldWithPath("returnCode").type(JsonFieldType.STRING).description("응답코드"),
                                fieldWithPath("returnMessage").type(JsonFieldType.STRING).description("응답 메시지"),
                                fieldWithPath("data[].id").type(JsonFieldType.NUMBER).description("텍스트요소 ID"),
                                fieldWithPath("data[].textCode").type(JsonFieldType.STRING).description("텍스트요소"),
                                fieldWithPath("data[].locale").type(JsonFieldType.STRING).description("언어"),
                                fieldWithPath("data[].description").type(JsonFieldType.STRING).description("내역")
                        )

                )).andExpectAll(jsonPath("$.returnCode").value("S"));
    }
}