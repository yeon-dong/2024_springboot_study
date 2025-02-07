package com.hyundaiautoever.beexample.language.adapter.in;

import com.hyundaiautoever.beexample.infrastructure.response.DataResponse;
import com.hyundaiautoever.beexample.language.adapter.in.dto.TextElementRequestDto;
import com.hyundaiautoever.beexample.language.adapter.in.dto.TextElementResponseDto;
import com.hyundaiautoever.beexample.language.application.port.in.RegisterTextElementUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/language")
public class RegisterTextElementController {

    private final RegisterTextElementUseCase registerTextElementUseCase;

    @PostMapping("/text/register")
    public DataResponse<TextElementResponseDto> register(TextElementRequestDto requestDto) {

        return new DataResponse<>("S",
                "정상적으로 등록하였습니다.",
                TextElementResponseDto.from(registerTextElementUseCase.register(requestDto.toEntity()))
            );
    }
}
