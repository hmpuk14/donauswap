package com.donauswap.donauswap.repository;


import com.donauswap.donauswap.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

//Springboot Repository für den Datenbankzugriff
public interface UserRepository extends JpaRepository<User, Long> {
    //Suche der E-Mail Adresse des eingeloggten Users
    User findByEmail(String email);
}
