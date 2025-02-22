package br.com.jatai.service_user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(
        @NotBlank @Email(message = "É necessario informar um email válido")
        String userEmail,

        @NotBlank(message = "É necessário indformar a senha")
        String userPassword) {
}
