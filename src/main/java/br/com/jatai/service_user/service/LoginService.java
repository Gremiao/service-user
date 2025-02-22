package br.com.jatai.service_user.service;

import br.com.jatai.service_user.dto.LoginRequestDTO;
import br.com.jatai.service_user.dto.LoginResponseDTO;
import br.com.jatai.service_user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private KafkaService kafkaService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity processLogin(LoginRequestDTO loginRequestDTO){
        if(isPasswordCheck(loginRequestDTO)){
            var tokenJWT = tokenService.generateToken(userRepository.findUuidByEmail(loginRequestDTO.userEmail()).get());
            kafkaService.sendMessage(LoginService.class.getSimpleName(), loginRequestDTO,"Sucess - login");
            return ResponseEntity.ok(new LoginResponseDTO(tokenJWT));
        }else{
            kafkaService.sendMessage(LoginService.class.getSimpleName(), loginRequestDTO,"Error - login/password are invalid");
            return ResponseEntity.badRequest().body("Login or passwor are invalid!");
        }
    }

    private boolean isPasswordCheck(LoginRequestDTO loginRequestDTO){
        return passwordEncoder.matches(
                loginRequestDTO.userPassword(),
                userRepository.findPasswordByEmail(loginRequestDTO.userEmail()).get());
    }
}
