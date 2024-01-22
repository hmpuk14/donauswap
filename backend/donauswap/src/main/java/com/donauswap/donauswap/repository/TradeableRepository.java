package com.donauswap.donauswap.repository;

import com.donauswap.donauswap.model.Tradeable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

//Springboot Repository für den Datenbankzugriff
public interface TradeableRepository extends JpaRepository<Tradeable, Long> {

    //Suchfunktion, um bestimmte Angebot nach Namen zu finden
    @Query("SELECT t FROM Tradeable t WHERE t.name LIKE %:keyword% OR t.description LIKE %:keyword%")
    List<Tradeable> searchByKeyword(@Param("keyword") String keyword);

    Optional<Tradeable> findByName(String name);

    // Methode, um Tradeables basierend auf der Kategorie-ID zu finden
    List<Tradeable> findByCategoryId(Long categoryId);
}
