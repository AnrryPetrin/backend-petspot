package com.petspot.dto.pet;

import com.petspot.model.Pet;
import java.util.Date;
import java.util.UUID;

public record SavedDatasPetDTO(
        UUID id,
        String name,
        String species,
        String weight,
        Integer gender,
        String breed,
        Boolean neutered,
        String behavior,
        String size,
        Boolean vaccinated,
        Date birthDate) {

    public SavedDatasPetDTO(Pet pet) {
        this(pet.getId(), pet.getPetName(), pet.getPetSpecies(), pet.getPetWeight(), pet.getPetGender(), pet.getPetBreed(),
                pet.getNeutered(), pet.getBehavior(), pet.getPetSize(), pet.getVaccinated(), pet.getPetBirthday());
    }

    public UUID getId() {
        return id;
    }
}