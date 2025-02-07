package com.hyundaiautoever.beexample.screen.adapter.out;

import com.hyundaiautoever.beexample.screen.adapter.out.persistence.ScreenAdminRepository;
import com.hyundaiautoever.beexample.screen.application.domain.ScreenAdmin;
import com.hyundaiautoever.beexample.screen.application.port.out.CreateScreenAdminPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CreateScreenAdminAdapter implements CreateScreenAdminPort {
    private final ScreenAdminRepository repository;

    @Override
    public ScreenAdmin save(ScreenAdmin screenAdmin) {
        return repository.save(screenAdmin);
    }
}
