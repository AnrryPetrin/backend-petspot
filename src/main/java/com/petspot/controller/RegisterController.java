package com.petspot.controller;

import com.petspot.dto.login.EmailDTO;
import com.petspot.dto.register.RegisterDTO;
import com.petspot.exceptions.*;
import com.petspot.model.Login;
import com.petspot.model.PetOwner;
import com.petspot.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/petspot/register")
public class RegisterController {
    @Autowired
    private LoginRepository loginRepository;

    @PostMapping
    public ResponseEntity<EmailDTO> register(@RequestBody @Validated RegisterDTO registerDTO,
                                             UriComponentsBuilder uriBuilder) throws PasswordsNotMatchingException, DuplicateEmailException, PasswordSizeException {

        // Verifica se o e-mail já está cadastrado
        boolean emailExists = loginRepository.existsByEmail(registerDTO.email());
        if (emailExists) {
            throw new DuplicateEmailException("Email is already registered.");
        }

        // Verifica se as senhas coincidem
        if (!registerDTO.password().equals(registerDTO.repeatPassword())) {
            throw new PasswordsNotMatchingException("Passwords do not match.");
        }

        if (registerDTO.password().length() < 8) {
            throw new PasswordSizeException("Password must be at least 8 characters long.");
        }

        // Cria um novo objeto PetOwner a partir do DTO
        PetOwner petOwner = new PetOwner(registerDTO);

        // Cria um novo objeto Login a partir do DTO e associa o PetOwner
        Login login = new Login(registerDTO);
        login.setPetOwner(petOwner);

        // Salva o login no repositório
        loginRepository.save(login);

        // Cria a URI para o novo recurso criado
        var uri = uriBuilder.path("/profile/{id}").buildAndExpand(login.getId()).toUri();

        // Retorna a resposta com a URI do novo recurso e o e-mail do usuário
        return ResponseEntity.created(uri).body(new EmailDTO(login.getEmail()));
    }
}