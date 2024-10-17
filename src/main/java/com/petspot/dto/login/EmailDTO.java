package com.petspot.dto.login;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmailDTO(
        @Email @NotBlank(message = "Email cannot be blank") String email) {
}