package com.petspot.service;

import com.petspot.dto.pet.RegisterPetDTO;
import com.petspot.dto.pet.SavedDatasPetDTO;
import com.petspot.dto.pet.UpdatePetDTO;
import com.petspot.dto.pet.UpdatePetWeightDTO;
import com.petspot.exceptions.DuplicatePetException;
import com.petspot.model.Pet;
import com.petspot.model.PetOwner;
import com.petspot.repository.PetOwnerRepository;
import com.petspot.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private PetOwnerRepository ownerRepository;

    @Transactional
    public SavedDatasPetDTO registerPet(RegisterPetDTO petDTO, String ownerId) {
        PetOwner owner = ownerRepository.findById(UUID.fromString(ownerId))
                .orElseThrow(() -> new RuntimeException("Owner not found with id: " + ownerId));

        boolean petExists = petRepository.existsByPetNameAndPetSpeciesAndPetGenderAndPetBreedAndNeuteredAndBehaviorAndPetSizeAndVaccinatedAndPetBirthday(
                petDTO.name(), petDTO.species(), petDTO.gender(), petDTO.breed(), petDTO.neutered(),
                petDTO.behavior(), petDTO.size(), petDTO.vaccinated(), petDTO.getDate());

        if (petExists) {
            throw new DuplicatePetException("Pet with the same attributes already exists.");
        }

        Pet pet = new Pet(petDTO);
        petRepository.save(pet);

        owner.getPets().add(pet);
        ownerRepository.save(owner);

        return new SavedDatasPetDTO(pet);
    }

    public List<SavedDatasPetDTO> getAllPetsByOwner(String ownerId) {
        PetOwner owner = ownerRepository.findById(UUID.fromString(ownerId))
                .orElseThrow(() -> new RuntimeException("Owner not found with id: " + ownerId));

        return owner.getPets().stream()
                .map(SavedDatasPetDTO::new)
                .collect(Collectors.toList());
    }

    public List<SavedDatasPetDTO> getPetsByName(String ownerId, String petName) {
        PetOwner owner = ownerRepository.findById(UUID.fromString(ownerId))
                .orElseThrow(() -> new RuntimeException("Owner not found with id: " + ownerId));

        return owner.getPets().stream()
                .filter(pet -> pet.getPetName().toLowerCase().contains(petName.toLowerCase()))
                .map(SavedDatasPetDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void renamePet(String ownerId, String petId, UpdatePetDTO updatePetDTO) {
        Pet pet = petRepository.findById(UUID.fromString(petId))
                .orElseThrow(() -> new RuntimeException("Pet not found with id: " + petId));

        boolean isOwner = pet.getPetOwners().stream().anyMatch(owner -> owner.getId().equals(ownerId));

        if (!isOwner) {
            throw new RuntimeException("You do not have permission to rename this pet.");
        }

        pet.setPetName(updatePetDTO.name());
        petRepository.save(pet);
    }

    @Transactional
    public void updatePetWeight(String ownerId, String petId, UpdatePetWeightDTO updatePetWeightDTO) {
        Pet pet = petRepository.findById(UUID.fromString(petId))
                .orElseThrow(() -> new RuntimeException("Pet not found with id: " + petId));

        boolean isOwner = pet.getPetOwners().stream().anyMatch(owner -> owner.getId().equals(ownerId));

        if (!isOwner) {
            throw new RuntimeException("You do not have permission to update the weight of this pet.");
        }

        pet.setPetWeight(updatePetWeightDTO.weight());
        petRepository.save(pet);
    }
}