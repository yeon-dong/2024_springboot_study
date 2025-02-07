package com.hyundaiautoever.beexample.screen.application.port.out;

import com.hyundaiautoever.beexample.screen.application.domain.ScreenAdmin;

public interface CreateScreenAdminPort {
    ScreenAdmin save(ScreenAdmin screenAdmin);
}
