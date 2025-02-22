package br.com.jatai.service_user.controller;

import br.com.jatai.service_user.dto.LoginRequestDTO;
import br.com.jatai.service_user.service.LoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid LoginRequestDTO loginRequest){
        return loginService.processLogin(loginRequest);
    }
}
