package com.hyundaiautoever.beexample.language.adapter.in;

import com.hyundaiautoever.beexample.infrastructure.response.DataResponse;
import com.hyundaiautoever.beexample.language.application.domain.TextElement;
import com.hyundaiautoever.beexample.language.application.port.in.DeleteTextElementUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/language")
public class DeleteTextElementController {

    private final DeleteTextElementUseCase deleteTextElementUseCase;

    @DeleteMapping("/dictionary/delete/{id}")
    public DataResponse<Boolean> deleteTextElement(@PathVariable("id") TextElement textElement) {
        deleteTextElementUseCase.delete(textElement);
        return new DataResponse<>("S", "정상적으로 삭제하였습니다.",true);

    }

}
