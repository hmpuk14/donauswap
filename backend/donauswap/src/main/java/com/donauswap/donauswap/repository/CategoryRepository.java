package com.donauswap.donauswap.repository;

import com.donauswap.donauswap.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

//Springboot Standard - Repository für den Datenbankzugriff
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);
}
