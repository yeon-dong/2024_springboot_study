package com.hyundaiautoever.beexample.admin.adapter.in;

import com.hyundaiautoever.beexample.admin.adapter.in.dto.HelloRequestDto;
import com.hyundaiautoever.beexample.admin.adapter.in.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/index")
public class IndexController {

    @GetMapping("/hello")
    public HelloResponseDto hello(HelloRequestDto requestDto) {
        return HelloResponseDto.of("Hello", requestDto.getName());
    }

}
