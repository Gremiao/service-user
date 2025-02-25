package br.com.jatai.service_user.service;

import br.com.jatai.security.service.AuthenticationService;
import br.com.jatai.service_user.dto.UserRegistrationRequestDTO;
import br.com.jatai.service_user.dto.UserRegistrationResponseDTO;
import br.com.jatai.service_user.model.UserAccess;
import br.com.jatai.service_user.model.UserModel;
import br.com.jatai.service_user.model.UserPreferences;
import br.com.jatai.service_user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserRepository repository;

    @Autowired
    private KafkaService kafkaService;

    @Transactional
    public UserRegistrationResponseDTO save(UserRegistrationRequestDTO userSent) {
        try {
            var user = new UserModel(userSent);

            user.setUuid(UUID.randomUUID());
            user.setPassword(authenticationService.passwordEncoder(userSent.password()));

            user.getUserDetails().setUuid(UUID.randomUUID());
            user.getUserLocation().setUuid(UUID.randomUUID());

            user.setUserAccess(new UserAccess(LocalDateTime.now(),null));
            user.getUserAccess().setUuid(UUID.randomUUID());

            user.setUserPreferences(new UserPreferences());
            user.getUserPreferences().setUuid(UUID.randomUUID());

            repository.save(user);

            kafkaService.sendMessage(UserService.class.getSimpleName(), user,"Sucess - add new user");

            return new UserRegistrationResponseDTO(user);
        } catch (Exception e) {
            kafkaService.sendMessage(UserService.class.getSimpleName(), userSent,"Sucess - add new user");
            throw new RuntimeException(e);
        }
    }
}
