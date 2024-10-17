package com.petspot.model;

import com.petspot.dto.register.RegisterDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "pet_owner")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class PetOwner {
    @Id
    @GeneratedValue
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column
    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false, unique = true)
    private String phone;

    @Column(name = "newsletter_check")
    private boolean newsletterCheck;

    @OneToOne(mappedBy = "petOwner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Login login;

    @ManyToMany
    @JoinTable(
            name = "pets_client_tutor",
            joinColumns = @JoinColumn(name = "pet_owner_id"),
            inverseJoinColumns = @JoinColumn(name = "pet_id")
    )
    private Set<Pet> pets = new HashSet<>();

    public PetOwner(RegisterDTO registerDTO) {
        this.name = registerDTO.nome();
        this.lastName = registerDTO.sobrenome();
        this.birthday = registerDTO.getDate();
        this.country = registerDTO.pais();
        this.phone = registerDTO.telefone();
        this.newsletterCheck = registerDTO.newsletterCheck();
    }
}