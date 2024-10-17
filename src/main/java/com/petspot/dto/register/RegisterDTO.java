package com.petspot.dto.register;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public record RegisterDTO(
        @Email String email,
        @NotBlank String password,
        @NotBlank String repeatPassword,
        @NotBlank String user,
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank String dateOfBirth,
        @NotBlank String country,
        @NotBlank String phone,
        boolean newsletterCheck) {

    public Date getDate() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        try {
            return format.parse(dateOfBirth);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use dd/MM/yyyy.", e);
        }
    }

    public String password() {
        return password;
    }

    public String repeatPassword() {
        return repeatPassword;
    }
}