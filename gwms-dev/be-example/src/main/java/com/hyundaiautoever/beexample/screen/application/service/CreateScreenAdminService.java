package com.hyundaiautoever.beexample.screen.application.service;

import com.hyundaiautoever.beexample.screen.application.domain.ScreenAdmin;
import com.hyundaiautoever.beexample.screen.application.port.in.CreateScreenAdminUseCase;
import com.hyundaiautoever.beexample.screen.application.port.out.CreateScreenAdminPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateScreenAdminService implements CreateScreenAdminUseCase {

    private final CreateScreenAdminPort createScreenAdminPort;

    @Override
    public ScreenAdmin create(ScreenAdmin screenAdmin) {
        return createScreenAdminPort.save(screenAdmin);
    }
}
