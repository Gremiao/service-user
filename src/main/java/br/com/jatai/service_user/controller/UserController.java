package br.com.jatai.service_user.controller;

import br.com.jatai.service_user.dto.UserRegistrationRequestDTO;
import br.com.jatai.service_user.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/userController")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/userRegistration")
    @Secured({"ROLE_ADMINISTRATOR"})
    public ResponseEntity registerNewUser(@RequestBody @Valid UserRegistrationRequestDTO userSent, UriComponentsBuilder uriBuilder){
        try {
            var userRegistrationRequestDTO = userService.save(userSent);
            var uri = uriBuilder.path("/userController/userRegistration/{id}").buildAndExpand(userRegistrationRequestDTO.uuid()).toUri();
            return ResponseEntity.created(uri).body(userRegistrationRequestDTO);
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
