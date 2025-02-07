package com.hyundaiautoever.beexample.admin.adapter.in;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Auth2Controller {

    @GetMapping("/code")
    public String code(String code) {
        return code;
    }
}
