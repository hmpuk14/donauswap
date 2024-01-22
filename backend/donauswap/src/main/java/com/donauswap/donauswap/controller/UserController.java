package com.donauswap.donauswap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.donauswap.donauswap.model.User;
import com.donauswap.donauswap.service.UserService;
import com.donauswap.donauswap.repository.UserRepository;

//Controller für die Authentifizierung
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserService userService, BCryptPasswordEncoder passwordEncoder,UserRepository userRepository) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    //Übergabe der Registrierungsinformation an die Datenbank
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User registeredUser = userService.registerUser(user);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    //Logindaten Übergabe zur Prüfung
    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody User user) {
        boolean isValidUser = userService.validateLogin(user.getEmail(), user.getPassword());
        if (isValidUser) {
            // Anmeldung erfolgreich
            return ResponseEntity.ok(userService.getUserByEmail(user.getEmail()));
        } else {
            // Anmeldung fehlgeschlagen
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    //routinen zu Donaucoin Saldo Bildung bei Kauf und Aktualisierung der Anzahl Dcoin nach Kauf
    @PutMapping("/{userId}/saldo")
    public ResponseEntity<User> updateSaldo(@PathVariable Long userId, @RequestBody Double newSaldo) {
        User updatedUser = userService.updateDcoinSaldo(userId, newSaldo);
        return ResponseEntity.ok(updatedUser);
    }
    @GetMapping("/{userId}/saldo")
    public ResponseEntity<Double> getSaldo(@PathVariable Long userId) {
        return userRepository.findById(userId)
                .map(user -> ResponseEntity.ok(user.getDcoinSaldo())) // Benutzer gefunden Donaucoin anzeigen
                .orElse(ResponseEntity.notFound().build()); // Falls der Benutzer nicht gefunden wird
    }

}

