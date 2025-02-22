package br.com.jatai.service_user.dto;

import br.com.jatai.service_user.model.UserModel;

public record UserRegistrationResponseDTO(
        String uuid,
        String firstName,
        String lastName,
        String email
) {
    public UserRegistrationResponseDTO(UserModel user){
        this(user.getUuid().toString(),
                user.getUserDetails().getFirstName(),
                user.getUserDetails().getLastName(),
                user.getUserDetails().getEmail());
    }
}
