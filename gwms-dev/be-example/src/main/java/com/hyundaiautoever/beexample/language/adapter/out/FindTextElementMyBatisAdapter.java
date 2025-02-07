package com.hyundaiautoever.beexample.language.adapter.out;

import com.hyundaiautoever.beexample.infrastructure.mapper.TextElementMapper;
import com.hyundaiautoever.beexample.infrastructure.mapper.dto.TextElementDto;
import com.hyundaiautoever.beexample.language.application.domain.TextElement;
import com.hyundaiautoever.beexample.language.application.port.out.FindTextElementPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Locale;

@Repository
@RequiredArgsConstructor
public class FindTextElementMyBatisAdapter implements FindTextElementPort {

    private final TextElementMapper mapper;

    @Override
    public TextElement existBy(Locale locale, String textCode) {
        TextElementDto result = mapper.existBy(locale, textCode);
        return result.toEntity();
    }

    @Override
    public List<TextElement> findBy(String uri, Locale locale) {
        List<TextElementDto> results = mapper.findBy(uri, locale.toString());
        return results.stream().map(TextElementDto::toEntity).toList();
    }

}
