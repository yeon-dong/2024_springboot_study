package com.hyundaiautoever.beexample.language.adapter.in;

import com.hyundaiautoever.beexample.infrastructure.response.DataResponse;
import com.hyundaiautoever.beexample.language.adapter.in.dto.WebPageTextElementRequestDto;
import com.hyundaiautoever.beexample.language.application.domain.WebPageTextElement;
import com.hyundaiautoever.beexample.language.application.port.in.MapTextElementUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/language")
public class MapTextElementController {

    private final MapTextElementUseCase mapTextElementUseCase;

    @PostMapping("/text/mapping")
    public DataResponse<WebPageTextElement> textMappingSingle(@RequestBody WebPageTextElementRequestDto requestDto) {
        return new DataResponse<>("S",
                "정상적으로 연결하였습니다.",
                mapTextElementUseCase.map(requestDto.toEntity()));
    }

    @PostMapping("/text/mapping/batch")
    public DataResponse<Integer> textMappingBatch(@RequestBody WebPageTextElementRequestDto requestDto) {
        List<WebPageTextElement> result = mapTextElementUseCase.map(requestDto.toEntityList());
        return new DataResponse<>("S",
                "정상적으로 연결하였습니다.",
                result.size());
    }



}
