package com.petspot.controller;

import com.petspot.dto.pet.RegisterPetDTO;
import com.petspot.dto.pet.SavedDatasPetDTO;
import com.petspot.dto.pet.UpdatePetDTO;
import com.petspot.dto.pet.UpdatePetWeightDTO;

import com.petspot.service.PetService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/petspot")
public class PetController {

        @Autowired
        private PetService petService;

        @PostMapping("/pet-register/{id}")
        @Transactional
        public ResponseEntity<SavedDatasPetDTO> registerPet(@Valid @RequestBody RegisterPetDTO petDTO,
                                                            @PathVariable(name = "id") String ownerId,
                                                            UriComponentsBuilder uriBuilder) {
                SavedDatasPetDTO savedPet = petService.registerPet(petDTO, ownerId);

                var uri = uriBuilder.path("/pet/{id}").buildAndExpand(savedPet.getId()).toUri();
                return ResponseEntity.created(uri).body(savedPet);
        }

        @GetMapping("/meuspets/{ownerId}")
        public ResponseEntity<List<SavedDatasPetDTO>> getAllPetsByOwner(@PathVariable String ownerId) {
                List<SavedDatasPetDTO> pets = petService.getAllPetsByOwner(ownerId);
                return ResponseEntity.ok(pets);
        }

        @GetMapping("/meuspets/{ownerId}/buscarpet")
        public ResponseEntity<List<SavedDatasPetDTO>> getPetsByName(@PathVariable String ownerId,
                                                                    @RequestParam String petName) {
                List<SavedDatasPetDTO> pets = petService.getPetsByName(ownerId, petName);
                return pets.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(pets);
        }

        @PatchMapping("/meuspets/{ownerId}/renomear/{petId}")
        public ResponseEntity<String> renamePet(@PathVariable String ownerId,
                                                @PathVariable String petId,
                                                @RequestBody UpdatePetDTO updatePetDTO) {
                try {
                        petService.renamePet(ownerId, petId, updatePetDTO);
                        return ResponseEntity.ok("Nome atualizado com sucesso.");
                } catch (RuntimeException e) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body("Erro ao atualizar o nome do pet: " + e.getMessage());
                }
        }

        @PatchMapping("/meuspets/{ownerId}/atualizarpeso/{petId}")
        public ResponseEntity<String> updatePetWeight(@PathVariable String ownerId,
                                                      @PathVariable String petId,
                                                      @RequestBody UpdatePetWeightDTO updatePetWeightDTO) {
                try {
                        petService.updatePetWeight(ownerId, petId, updatePetWeightDTO);
                        return ResponseEntity.ok("Peso atualizado com sucesso.");
                } catch (RuntimeException e) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body("Erro ao atualizar o peso do pet: " + e.getMessage());
                }
        }
}
