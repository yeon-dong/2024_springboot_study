package com.hyundaiautoever.beexample.language.application.service;


import com.hyundaiautoever.beexample.language.application.domain.TextElement;
import com.hyundaiautoever.beexample.language.application.port.in.DeleteTextElementUseCase;
import com.hyundaiautoever.beexample.language.application.port.out.DeleteTextElementPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class DeleteTextElementService implements DeleteTextElementUseCase {

    private final DeleteTextElementPort deleteTextElementPort;

    @Override
    public void delete(TextElement textElement) {
        assert textElement != null;

        deleteTextElementPort.delete(textElement);
    }
}
