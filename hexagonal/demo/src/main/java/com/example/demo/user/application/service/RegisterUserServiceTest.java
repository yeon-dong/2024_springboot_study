package com.example.demo.user.application.service;

import com.example.demo.user.application.domain.HexagonalUser;
import com.example.demo.user.application.port.out.RegisterUserPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RegisterUserServiceTest {

    @Mock
    RegisterUserPort registerUserPort;

    @Test
    @DisplayName("헥사고날 User Entity가 정상적으로 등록된다")
    void registerUser(){
        //given
        HexagonalUser user = HexagonalUser.builder()
                .username("admin")
                .email("")
                .password("")
                .role("")
                .build();
    //when
        RegisterUserService registerUserService = new RegisterUserService(registerUserPort);

        given(registerUserPort.save(user)).willReturn(1L);

        //then
        assertEquals(1L, registerUserService.register(user));

    }
}
