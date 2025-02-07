package com.hyundaiautoever.beexample.language.adapter.in;

import com.hyundaiautoever.beexample.infrastructure.response.DataResponse;
import com.hyundaiautoever.beexample.language.adapter.in.dto.TextElementResponseDto;
import com.hyundaiautoever.beexample.language.application.domain.TextElement;
import com.hyundaiautoever.beexample.language.application.port.in.SearchTextElementUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/language")
public class SearchTextElementController {

    private final SearchTextElementUseCase searchTextElementUseCase;

    @GetMapping("/text/search")
    public DataResponse<List<TextElementResponseDto>> search(String uri, String locale) {

        List<TextElement> textElements
                = searchTextElementUseCase.findBy(uri, generateLocale(locale));

        return new DataResponse<>("S",
                "정상적으로 조회하였습니다.",
                textElements.stream().map(TextElementResponseDto::from).toList()
        );
    }

    private Locale generateLocale(String locale) {
        if (locale == null || locale.isEmpty()) {
            return Locale.KOREAN;
        } else {
            return new Locale(locale);
        }
    }

}
