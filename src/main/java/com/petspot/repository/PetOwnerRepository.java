package com.petspot.repository;

import com.petspot.model.PetOwner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PetOwnerRepository extends JpaRepository<PetOwner, UUID> {
}