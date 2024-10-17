package com.petspot.dto.pet;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public record RegisterPetDTO(
        @NotBlank(message = "Nome é obrigatório") String name,
        @NotBlank(message = "Espécie é obrigatória") String species,
        @NotNull(message = "Gênero é obrigatório") Integer gender,
        @NotBlank(message = "Raça é obrigatória") String breed,
        String weight,
        @NotNull(message = "Deve específicar se o Animal foi castrado") Boolean neutered,
        @NotBlank(message = "Comportamento é obrigatório") String behavior,
        @NotBlank(message = "Porte é obrigatório") String size,
        @NotNull(message = "Deve específicar se o animal foi vacinado") Boolean vaccinated,
        @NotBlank(message = "Data de nascimento é obrigatória")
        @Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}", message = "Data deve estar no formato dd/MM/yyyy") String birthDate) {

    public Date getDate() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        try {
            return format.parse(birthDate);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use dd/MM/yyyy.", e);
        }
    }
}