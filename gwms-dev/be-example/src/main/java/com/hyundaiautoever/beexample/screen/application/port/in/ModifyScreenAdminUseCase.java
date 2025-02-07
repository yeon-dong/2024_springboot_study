package com.hyundaiautoever.beexample.screen.application.port.in;

import com.hyundaiautoever.beexample.screen.application.domain.ScreenAdmin;

public interface ModifyScreenAdminUseCase {

    void modify(Long senderId, ScreenAdmin screenAdmin);
}
