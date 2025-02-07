package com.example.demo.user.adapter.in.dto;

import com.example.demo.user.application.domain.HexagonalUser;
import lombok.Data;

@Data
public class UserRequestDto {
    private String username;
    private String password;
    private String role;
    private String email;

    public HexagonalUser toEntity() {
        return HexagonalUser.builder()
                .email(this.email)
                .role(this.role)
                .username(this.username)
                .password(this.password)
                .build();
    }

    public HexagonalUser from(HexagonalUser user) {

    }

}
