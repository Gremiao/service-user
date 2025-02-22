package br.com.jatai.service_user.dto;

import jakarta.validation.constraints.*;

public record UserRegistrationRequestDTO(
        @NotBlank
        String firstName,

        @NotBlank
        String lastName,

        @NotBlank
        @Min(18) @Max(90)
        String age,

        @NotBlank
        String gender,

        @Email
        String email,

        @NotBlank
        String phoneNumber,

        @NotBlank
        String country,

        @NotBlank
        String state,

        String city,

        @Pattern(regexp = "\\d{8}")
        String postalCode,

        @NotBlank
        String password
) {
}
