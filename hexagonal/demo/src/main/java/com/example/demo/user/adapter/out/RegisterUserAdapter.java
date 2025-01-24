package com.example.demo.user.adapter.out;

import com.example.demo.infrastructure.repository.HexagonalUserRepository;
import com.example.demo.user.application.domain.HexagonalUser;
import com.example.demo.user.application.port.out.RegisterUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegisterUserAdapter implements RegisterUserPort {

    private final HexagonalUserRepository repository;

    @Override
    public Long save(HexagonalUser hexagonalUser){
        return repository.save(hexagonalUser).getId();
    }
}
