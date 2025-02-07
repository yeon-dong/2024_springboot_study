package com.hyundaiautoever.beexample.language.application.service;

import com.hyundaiautoever.beexample.language.application.domain.TextElement;
import com.hyundaiautoever.beexample.language.application.port.in.RegisterTextElementUseCase;
import com.hyundaiautoever.beexample.language.application.port.in.SearchTextElementUseCase;
import com.hyundaiautoever.beexample.language.application.port.out.RegisterTextElementPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class RegisterTextElementService implements RegisterTextElementUseCase {

    private final SearchTextElementUseCase searchTextElementUseCase;
    private final RegisterTextElementPort registerTextElementPort;


    @Override
    public TextElement register(TextElement textElement) {
        assert textElement != null;

        TextElement multilingualDictionary =
                searchTextElementUseCase.existBy(textElement.getTextCode(), textElement.getLocale());
        if (multilingualDictionary != null) {
            multilingualDictionary.updateDescription(textElement.getDescription());
            return registerTextElementPort.save(multilingualDictionary);
        }

        return registerTextElementPort.save(textElement);
    }
}
