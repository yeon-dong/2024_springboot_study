package com.hyundaiautoever.beexample.language.adapter.in;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyundaiautoever.beexample.language.adapter.in.dto.TextElementRequestDto;
import com.hyundaiautoever.beexample.language.application.domain.TextElement;
import com.hyundaiautoever.beexample.language.application.port.in.RegisterTextElementUseCase;
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

import java.util.Locale;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = RegisterTextElementController.class)
@AutoConfigureRestDocs
@ExtendWith(MockitoExtension.class)
class RegisterTextElementControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    RegisterTextElementUseCase registerTextElementUseCase;


    @Test
    @DisplayName("Text Element를 정상적으로 등록한다.")
    void registerTest() throws Exception {
        //given
        TextElementRequestDto requestDto = TextElementRequestDto.of("TEXT-002", Locale.KOREAN, "텍스트요소 2");

        given(registerTextElementUseCase.register(any())).willReturn(
            new TextElement(1L, requestDto.getTextCode(),
                    requestDto.getLocale(),
                    requestDto.getDescription())
        );

        //when
        ResultActions resultActions = mockMvc.perform(post("/api/v1/language/text/register")// RestDocumentationRequestBuilders의 static method 이용
                .content(objectMapper.writeValueAsString(requestDto))
                .with(SecurityMockMvcRequestPostProcessors.jwt())
                .characterEncoding(StandardCharset.UTF_8)
                .accept(MediaType.APPLICATION_JSON));

        // then
        resultActions.andExpect(status().isOk())
                .andDo(document("text-register", // test 수행시 ./build/generated-snippets/ 하위에 지정한 문자열의 폴더 하위에 문서가 작성됨.
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()), // 생성될 문서의 indent를 자동정렬해주는 메소드
                        requestFields(
                                fieldWithPath("textCode").type(JsonFieldType.STRING).description("텍스트요소"),
                                fieldWithPath("locale").type(JsonFieldType.STRING).description("언어"),
                                fieldWithPath("description").type(JsonFieldType.STRING).description("내역")
                        ),
                        responseFields(
                                fieldWithPath("returnCode").type(JsonFieldType.STRING).description("응답코드"),
                                fieldWithPath("returnMessage").type(JsonFieldType.STRING).description("응답 메시지"),
                                fieldWithPath("data.id").type(JsonFieldType.NUMBER).description("텍스트요소 ID"),
                                fieldWithPath("data.textCode").type(JsonFieldType.STRING).description("텍스트요소"),
                                fieldWithPath("data.locale").type(JsonFieldType.STRING).description("언어"),
                                fieldWithPath("data.description").type(JsonFieldType.STRING).description("내역")
                        )

                )).andExpectAll(jsonPath("$.returnCode").value("S"));
    }

}