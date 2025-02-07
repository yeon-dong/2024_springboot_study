package com.hyundaiautoever.beexample.language.application.service;

import com.hyundaiautoever.beexample.language.application.domain.TextElement;
import com.hyundaiautoever.beexample.language.application.port.in.ModifyTextElementUseCase;
import com.hyundaiautoever.beexample.language.application.port.out.ModifyTextElementPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class ModifyTextElementService implements ModifyTextElementUseCase {

    private final ModifyTextElementPort modifyTextElementPort;

    @Override
    public TextElement modify(TextElement textElement) {

        assert textElement != null;

        return modifyTextElementPort.save(textElement);
    }
}
