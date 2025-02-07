package com.hyundaiautoever.authorizationserver.adapter.in;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequiredArgsConstructor
public class LoginController {

    @GetMapping("/login")
    public String login(HttpServletRequest request, Model model) {
        String errorMsg = (String) request.getSession().getAttribute("errorMsg");
        if (errorMsg != null) {
            request.getSession().removeAttribute("errorMsg");
            model.addAttribute("errorMsg", errorMsg);
        }
        return "login";
    }

}