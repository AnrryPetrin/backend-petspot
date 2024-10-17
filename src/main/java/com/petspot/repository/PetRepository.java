package com.petspot.repository;

import com.petspot.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface PetRepository extends JpaRepository<Pet, UUID> {
    List<Pet> findByPetNameContainingIgnoreCase(String petName);

    boolean existsByPetNameAndPetSpeciesAndPetGenderAndPetBreedAndNeuteredAndBehaviorAndPetSizeAndVaccinatedAndPetBirthday(
            String petName, String petSpecies, Integer petGender, String petBreed, Boolean neutered, String behavior, String petSize, Boolean vaccinated, Date petBirthday);
}