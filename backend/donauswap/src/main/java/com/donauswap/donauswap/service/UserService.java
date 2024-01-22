package com.donauswap.donauswap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.donauswap.donauswap.model.User;
import com.donauswap.donauswap.repository.UserRepository;

// Service zur Unterstützung der Registrierung und Authentifizierung
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean validateLogin(String email, String password) {
        User existingUser = getUserByEmail(email);
        return existingUser != null && passwordEncoder.matches(password, existingUser.getPassword());
    }

    public User updateDcoinSaldo(Long userId, Double newSaldo) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Benutzer nicht gefunden"));
        user.setDcoinSaldo(newSaldo);
        return userRepository.save(user);
    }
}

