package com.petspot.model;

import com.petspot.dto.pet.RegisterPetDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "pet")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Pet {
    @Id
    @GeneratedValue
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private UUID id;

    @Column(name = "pet_name", nullable = false)
    private String petName;

    @Column(name = "pet_weight")
    private String petWeight;

    @Column(name = "pet_birthday")
    @Temporal(TemporalType.DATE)
    private Date petBirthday;

    @Column(name = "pet_species", nullable = false)
    private String petSpecies;

    @Column(name = "pet_breed")
    private String petBreed;

    @Column(name = "pet_gender")
    private Integer petGender;

    @Column(nullable = false)
    private Boolean neutered;

    @Column
    private String behavior;

    @Column(name = "pet_size")
    private String petSize;

    @Column(nullable = false)
    private Boolean vaccinated;

    @JsonIgnore
    @ManyToMany(mappedBy = "pets", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<PetOwner> petOwners = new HashSet<>();

    public Pet(RegisterPetDTO petDTO) {
        this.petName = petDTO.nome();
        this.petBirthday = petDTO.getDate();
        this.petWeight = petDTO.peso();
        this.petSpecies = petDTO.especie();
        this.petBreed = petDTO.raca();
        this.petGender = petDTO.genero();
        this.neutered = petDTO.castrado();
        this.behavior = petDTO.comportamento();
        this.petSize = petDTO.porte();
        this.vaccinated = petDTO.vacinado();
    }
}