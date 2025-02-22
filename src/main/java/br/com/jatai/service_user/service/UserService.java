package br.com.jatai.service_user.service;

import br.com.jatai.service_user.dto.UserRegistrationRequestDTO;
import br.com.jatai.service_user.dto.UserRegistrationResponseDTO;
import br.com.jatai.service_user.model.UserAccess;
import br.com.jatai.service_user.model.UserModel;
import br.com.jatai.service_user.model.UserPreferences;
import br.com.jatai.service_user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    UserRepository repository;

    @Transactional
    public UserRegistrationResponseDTO save(UserRegistrationRequestDTO userSent) {
        var user = new UserModel(userSent);

        user.setUuid(UUID.randomUUID());
        user.setPassword(passwordEncoder.encode(userSent.password()));

        user.getUserDetails().setUuid(UUID.randomUUID());
        user.getUserLocation().setUuid(UUID.randomUUID());

        user.setUserAccess(new UserAccess(LocalDateTime.now(),null));
        user.getUserAccess().setUuid(UUID.randomUUID());

        user.setUserPreferences(new UserPreferences());
        user.getUserPreferences().setUuid(UUID.randomUUID());

        repository.save(user);
        return new UserRegistrationResponseDTO(user);
    }
}
