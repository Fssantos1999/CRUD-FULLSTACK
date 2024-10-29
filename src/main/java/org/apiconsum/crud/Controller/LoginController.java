package org.apiconsum.crud.Controller;

import org.apiconsum.crud.Entidade.Login;
import org.apiconsum.crud.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@CrossOrigin(origins = "http://localhost:63342")
@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    private UserRepository loginRepository;

    // Endpoint para registro de novo usuário
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Login login) {
        if (loginRepository.findByEmail(login.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email já cadastrado.");
        }

        loginRepository.save(login);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário registrado com sucesso.");
    }

    // Endpoint para login
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Login login) {
        Optional<Login> usuarioExistente = loginRepository.findByEmail(login.getEmail());

        if (usuarioExistente.isPresent() && usuarioExistente.get().getPassword().equals(login.getPassword())) {
            return ResponseEntity.ok("Login realizado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas.");
        }
    }
}
