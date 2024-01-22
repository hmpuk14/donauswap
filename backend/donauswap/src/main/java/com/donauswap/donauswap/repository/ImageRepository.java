package com.donauswap.donauswap.repository;

import com.donauswap.donauswap.model.Image;
import com.donauswap.donauswap.model.Tradeable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//Springboot Repository für den Datenbankzugriff
@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    //Abfrage zur Identifizierung des Hauptbildes
    @Query(value = "SELECT * FROM Image WHERE is_main = true ORDER BY RAND() LIMIT 12", nativeQuery = true)
    List<Image> findRandomMainImages();

    List<Image> findByTradeable(Tradeable tradeable);

    @Query("SELECT i FROM Image i WHERE i.tradeable.id = :tradeableId AND i.isMain = true")
    Image findMainImageByTradeable(@Param("tradeableId") Long tradeableId);
}
