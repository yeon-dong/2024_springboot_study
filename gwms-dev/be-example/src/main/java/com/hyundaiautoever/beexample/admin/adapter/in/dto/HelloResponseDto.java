package com.hyundaiautoever.beexample.admin.adapter.in.dto;

import lombok.Data;

@Data
public class HelloResponseDto {
    String greetings;
    String name;

    private HelloResponseDto(String greetings, String name) {
        this.greetings = greetings;
        this.name = name;
    }

    public static HelloResponseDto of(String greetings, String name) {
        return new HelloResponseDto(greetings, name);
    }
}
