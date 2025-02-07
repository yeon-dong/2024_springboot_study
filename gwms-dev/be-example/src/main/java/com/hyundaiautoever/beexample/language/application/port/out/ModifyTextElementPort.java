package com.hyundaiautoever.beexample.language.application.port.out;

import com.hyundaiautoever.beexample.language.application.domain.TextElement;

public interface ModifyTextElementPort {
    TextElement save(TextElement textElement);
}
