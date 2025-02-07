package com.hyundaiautoever.beexample.admin.adapter.in;

import com.nimbusds.jose.util.StandardCharset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = IndexController.class)
@AutoConfigureRestDocs
@ExtendWith(MockitoExtension.class)
class IndexControllerTest {

    @Autowired
    MockMvc mockMvc;


    @Test
    @DisplayName("Hello World 인스턴스 생성을 위한 웹 컨트롤러를 정상적으로 호출한다.")
    void helloWorld() throws Exception {
        //when
        ResultActions resultActions = mockMvc.perform(get("/api/v1/index/hello")// RestDocumentationRequestBuilders의 static method 이용
                .param("id", "100")
                .param("name", "My World")
                .with(SecurityMockMvcRequestPostProcessors.jwt())
                .characterEncoding(StandardCharset.UTF_8)
                .accept(MediaType.APPLICATION_JSON));

        // then
        resultActions.andExpect(status().isOk())
                .andDo(document("index-hello", // test 수행시 ./build/generated-snippets/ 하위에 지정한 문자열의 폴더 하위에 문서가 작성됨.
                        preprocessResponse(prettyPrint()), // 생성될 문서의 indent를 자동정렬해주는 메소드
                        responseFields(
                                fieldWithPath("greetings").type(JsonFieldType.STRING).description("인사말"),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("인사대상")
                        )
                )).andExpectAll(jsonPath("$.greetings").value("Hello"),
                                jsonPath("$.name").value("My World"));
    }

}