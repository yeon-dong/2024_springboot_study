package com.hyundaiautoever.beexample.screen.adapter.out;

import com.hyundaiautoever.beexample.screen.adapter.out.persistence.ScreenAdminRepository;
import com.hyundaiautoever.beexample.screen.application.domain.ScreenAdmin;
import com.hyundaiautoever.beexample.screen.application.port.out.ModifyScreenAdminPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ModifyScreenAdminAdapter implements ModifyScreenAdminPort {

    private final ScreenAdminRepository repository;

    @Override
    public void save(ScreenAdmin screenAdmin) {
        repository.save(screenAdmin);
    }

    @Override
    public ScreenAdmin findById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
