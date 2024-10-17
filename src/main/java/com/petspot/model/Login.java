package com.petspot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import com.petspot.dto.register.RegisterDTO;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "email"})
@Entity
@Table(name = "login")
public class Login {

    @Id
    @GeneratedValue
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private UUID id;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "user_type", nullable = false)
    private String userType;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_owner_id", referencedColumnName = "id")
    private PetOwner petOwner;

    public Login(RegisterDTO registerDTO) {
        this.email = registerDTO.email();
        this.password = registerDTO.password();
        this.userType = registerDTO.user();
    }

    public UUID getOwnerId() {
        return petOwner != null ? petOwner.getId() : null;
    }
}