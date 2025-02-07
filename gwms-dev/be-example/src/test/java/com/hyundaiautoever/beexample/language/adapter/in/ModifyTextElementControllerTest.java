package com.hyundaiautoever.beexample.language.adapter.in;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyundaiautoever.beexample.language.adapter.in.dto.TextElementRequestDto;
import com.hyundaiautoever.beexample.language.application.domain.TextElement;
import com.hyundaiautoever.beexample.language.application.port.in.ModifyTextElementUseCase;
import com.hyundaiautoever.beexample.language.application.port.in.RegisterTextElementUseCase;
import com.nimbusds.jose.util.StandardCharset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Locale;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureRestDocs
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class ModifyTextElementControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    ModifyTextElementUseCase modifyTextElementUseCase;

    @Autowired
    RegisterTextElementUseCase registerTextElementUseCase;

    @Test
    @DisplayName("Text Element를 정상적으로 수정한다.")
    void modifyTest() throws Exception {
        //given
        TextElement textElement = new TextElement(1L, "TEXT-001", Locale.KOREAN, "텍스트요소 1");
        TextElement source = registerTextElementUseCase.register(textElement);

        TextElementRequestDto requestDto = TextElementRequestDto.of("TEXT-002", Locale.ENGLISH, "텍스트요소 2");

        given(modifyTextElementUseCase.modify(any())).willReturn(
                new TextElement(1L, requestDto.getTextCode(),
                        requestDto.getLocale(),
                        requestDto.getDescription())
        );

        //when
        ResultActions resultActions = mockMvc.perform(put("/api/v1/language/text/modify/{id}", source.getId())// RestDocumentationRequestBuilders의 static method 이용
                .content(objectMapper.writeValueAsString(requestDto))
                .with(SecurityMockMvcRequestPostProcessors.jwt())
                .characterEncoding(StandardCharset.UTF_8)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        // then
        resultActions.andExpect(status().isOk())
                .andDo(document("text-modify", // test 수행시 ./build/generated-snippets/ 하위에 지정한 문자열의 폴더 하위에 문서가 작성됨.
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()), // 생성될 문서의 indent를 자동정렬해주는 메소드
                        pathParameters(
                                parameterWithName("id").description("수정할 Text Element의 ID")
                        ),
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
                                fieldWithPath("data.description").type(JsonFieldType.STRING).description("내역"),
                                fieldWithPath("data.registerDate").type(JsonFieldType.STRING).optional().description("registerDate"),
                                fieldWithPath("data.updateDate").type(JsonFieldType.STRING).optional().description("updateDate"),
                                fieldWithPath("data.deleteDate").type(JsonFieldType.STRING).optional().description("deleteDate"),
                                fieldWithPath("data.updateUserSeq").type(JsonFieldType.STRING).optional().description("updateUserSeq"),
                                fieldWithPath("data.deleteUserSeq").type(JsonFieldType.STRING).optional().description("deleteUserSeq"),
                                fieldWithPath("data.registerUserSeq").type(JsonFieldType.STRING).optional().description("registerUserSeq"),
                                fieldWithPath("data.deleteUserSeq").type(JsonFieldType.STRING).optional().description("deleteUserSeq"),
                                fieldWithPath("data.webPages").type(JsonFieldType.ARRAY).optional().description("webPages")

                        )

                )).andExpectAll(jsonPath("$.returnCode").value("S"));
    }
}