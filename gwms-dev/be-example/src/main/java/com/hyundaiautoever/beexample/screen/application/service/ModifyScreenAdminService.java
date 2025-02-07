package com.hyundaiautoever.beexample.screen.application.service;

import com.hyundaiautoever.beexample.screen.application.domain.ScreenAdmin;
import com.hyundaiautoever.beexample.screen.application.port.in.ModifyScreenAdminUseCase;
import com.hyundaiautoever.beexample.screen.application.port.out.ModifyScreenAdminPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ModifyScreenAdminService implements ModifyScreenAdminUseCase {

    private final ModifyScreenAdminPort modifyScreenAdminPort;

    @Override
    public void modify(Long screenAdminId, ScreenAdmin updatescreenAdmin) {
        ScreenAdmin modifyScreenAdmin = modifyScreenAdminPort.findById(screenAdminId);

        modifyScreenAdmin.updateScreenAdmin(updatescreenAdmin);
        modifyScreenAdminPort.save(modifyScreenAdmin);
    }
}
