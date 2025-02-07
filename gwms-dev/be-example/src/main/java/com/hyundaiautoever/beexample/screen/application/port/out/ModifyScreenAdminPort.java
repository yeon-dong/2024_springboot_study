package com.hyundaiautoever.beexample.screen.application.port.out;

import com.hyundaiautoever.beexample.screen.application.domain.ScreenAdmin;

public interface ModifyScreenAdminPort {

    void save(ScreenAdmin screenAdmin);
    ScreenAdmin findById(Long id);
}
