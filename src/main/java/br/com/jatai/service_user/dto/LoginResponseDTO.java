package br.com.jatai.service_user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginResponseDTO(
        String jwt) {
}
