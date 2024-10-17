package com.petspot.repository;

import com.petspot.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LoginRepository extends JpaRepository<Login, UUID> {
    Login findByEmailAndPassword(String email, String password);

    boolean existsByEmail(String email);
}