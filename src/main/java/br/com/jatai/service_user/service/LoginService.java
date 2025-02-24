package br.com.jatai.service_user.service;

import br.com.jatai.security.dto.AuthenticationRequestDTO;
import br.com.jatai.security.exception.InvalidPasswordException;
import br.com.jatai.security.service.AuthenticationService;
import br.com.jatai.service_user.dto.LoginRequestDTO;
import br.com.jatai.service_user.dto.LoginResponseDTO;
import br.com.jatai.service_user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private KafkaService kafkaService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationService authenticationService;

    public ResponseEntity processLogin(LoginRequestDTO loginRequestDTO){
        Optional<AuthenticationRequestDTO> user = userRepository.findUserByEmail(loginRequestDTO.userEmail());

        if(user.isPresent()){
            try {
                var tokenJWT =  authenticationService.authenticate(
                        new AuthenticationRequestDTO(
                                user.map(AuthenticationRequestDTO::getUserEmail).orElse("Email not found"),
                                loginRequestDTO.userPassword(),
                                user.map(AuthenticationRequestDTO::getUserPassworEncripted).orElse("Passwor not found"),
                                user.map(AuthenticationRequestDTO::getUserUuid).orElse(null),
                                user.map(AuthenticationRequestDTO::getUserRole).orElse("User role not found")));
                kafkaService.sendMessage(LoginService.class.getSimpleName(), loginRequestDTO,"Sucess - login");
                return ResponseEntity.ok(new LoginResponseDTO(tokenJWT));
            } catch (InvalidPasswordException e) {
                return ResponseEntity.badRequest().body("Login or passwor are invalid!");
            }
        }else{
            kafkaService.sendMessage(LoginService.class.getSimpleName(), loginRequestDTO,"Error - login/password are invalid");
            return ResponseEntity.badRequest().body("Login or passwor are invalid!");
        }

    }
}
