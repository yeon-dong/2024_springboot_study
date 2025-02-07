package com.hyundaiautoever.beexample.language.adapter.in;

import com.hyundaiautoever.beexample.infrastructure.response.DataResponse;
import com.hyundaiautoever.beexample.language.adapter.in.dto.TextElementRequestDto;
import com.hyundaiautoever.beexample.language.application.domain.TextElement;
import com.hyundaiautoever.beexample.language.application.port.in.ModifyTextElementUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/language")
public class ModifyTextElementController {
    private final ModifyTextElementUseCase modifyTextElementUseCase;

    @PutMapping("/text/modify/{id}")
    public DataResponse<TextElement> modify(@PathVariable("id") TextElement source,
                                            TextElementRequestDto requestDto) {
        source.updateFrom(requestDto.toEntity());
        return new DataResponse<>("S",
                "정상적으로 수정하였습니다.",
                modifyTextElementUseCase.modify(source));
    }
}
