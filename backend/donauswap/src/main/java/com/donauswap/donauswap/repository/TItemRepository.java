package com.donauswap.donauswap.repository;

import com.donauswap.donauswap.model.TItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


//Springboot Standard - Repository für den Datenbankzugriff

@Repository
public interface TItemRepository extends JpaRepository<TItem, Long> {
}
