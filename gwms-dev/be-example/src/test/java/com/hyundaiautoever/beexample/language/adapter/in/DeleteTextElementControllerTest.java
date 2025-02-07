package com.hyundaiautoever.beexample.language.adapter.in;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyundaiautoever.beexample.language.application.domain.TextElement;
import com.hyundaiautoever.beexample.language.application.port.in.DeleteTextElementUseCase;
import com.hyundaiautoever.beexample.language.application.port.in.RegisterTextElementUseCase;
import com.nimbusds.jose.util.StandardCharset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Locale;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
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
class DeleteTextElementControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    DeleteTextElementUseCase deleteTextElementUseCase;

    @Autowired
    RegisterTextElementUseCase registerTextElementUseCase;

    @Test
    @DisplayName("Text Element를 정상적으로 삭제한다.")
    void deleteTest() throws Exception {

        //given
        TextElement textElement = new TextElement(1L, "TEXT-001", Locale.KOREAN, "텍스트요소 1");
        TextElement source = registerTextElementUseCase.register(textElement);

        //when
        ResultActions resultActions = mockMvc.perform(delete("/api/v1/language/dictionary/delete/{id}", source.getId())// RestDocumentationRequestBuilders의 static method 이용
                .with(SecurityMockMvcRequestPostProcessors.jwt())
                .characterEncoding(StandardCharset.UTF_8)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        // then
        resultActions.andExpect(status().isOk())
                .andDo(document("text-delete", // test 수행시 ./build/generated-snippets/ 하위에 지정한 문자열의 폴더 하위에 문서가 작성됨.
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()), // 생성될 문서의 indent를 자동정렬해주는 메소드
                        pathParameters(
                                parameterWithName("id").description("삭제할 Text Element의 ID")
                        ),
                        responseFields(
                                fieldWithPath("returnCode").type(JsonFieldType.STRING).description("응답코드"),
                                fieldWithPath("returnMessage").type(JsonFieldType.STRING).description("응답 메시지"),
                                fieldWithPath("data").type(JsonFieldType.BOOLEAN).description("true")
                        )

                )).andExpectAll(jsonPath("$.returnCode").value("S"));
    }

}