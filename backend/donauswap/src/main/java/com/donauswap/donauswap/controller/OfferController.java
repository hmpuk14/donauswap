package com.donauswap.donauswap.controller;


import com.donauswap.donauswap.model.*;
import com.donauswap.donauswap.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.donauswap.donauswap.service.TradeableService;

import java.util.Optional;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.logging.Logger;
import org.springframework.http.HttpStatus;


//steuert die Darstellung der Angebote in der Übersicht und einzeln und stellt die Informationen für trading bei erfolgtem Tausch zusammen
@RestController
@RequestMapping("/api/offers")
public class OfferController {

    @Autowired
    private TradeableService tradeableService;

    @Autowired
    private ImageRepository imageRepository;
    private static final Logger logger = Logger.getLogger(TradeableService.class.getName());

    //zufälliges Abrufen der Bilder um Angebote im Frontend zu zeigen
    @GetMapping("/random")
    public ResponseEntity<List<Image>> getRandomMainImages() {
        logger.info("Fetching random main images");
        List<Image> images = tradeableService.getRandomMainImages()
                .stream()
                .map(this::convertImagePath)
                .collect(Collectors.toList());

        if (images.isEmpty()) {
            logger.warning("No images found");
            return ResponseEntity.noContent().build();
        }
        logger.info("Returning " + images.size() + " images");
        return ResponseEntity.ok(images);
    }

    //Suchfunktion Angebote
    @GetMapping("/search")
    public ResponseEntity<List<Image>> searchImages(@RequestParam String keyword) {
        List<Image> images = tradeableService.searchImagesByKeyword(keyword)
                .stream()
                .map(this::convertImagePath)
                .collect(Collectors.toList());

        if (images.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(images);
    }

    //Bildwiedergabe
    private Image convertImagePath(Image image) {
        String baseUrl = "http://localhost:8080";
        String imagePath = image.getPicturePath();
        imagePath = imagePath.replace("\\", "/");
        String fullPath = baseUrl + "/uploaded_images" + imagePath.substring(imagePath.lastIndexOf("/"));
        image.setPicturePath(fullPath);
        return image;
    }

    //Widergabe von Inhalten eines angeklickten Einzelangebots nach id
    @GetMapping("/byid/{id}")
    public ResponseEntity<?> getOfferById(@PathVariable Long id) {
        Optional<Tradeable> tradeableOptional = tradeableService.getTradeableById(id);
        if (tradeableOptional.isPresent()) {
            Tradeable tradeable = tradeableOptional.get();

            // Bilder holen
            List<Image> images = imageRepository.findByTradeable(tradeable);
            Image mainImage = images.stream()
                    .filter(Image::isMain)
                    .map(this::convertImagePath)
                    .findFirst()
                    .orElse(null);
            List<Image> thumbnails = images.stream()
                    .filter(img -> !img.isMain())
                    .map(this::convertImagePath)
                    .collect(Collectors.toList());

            // Antwortobjekt vorbereiten
            Map<String, Object> response = new HashMap<>();
            response.put("mainImage", mainImage);
            response.put("thumbnails", thumbnails);
            response.put("description", tradeable.getDescription());
            response.put("price_dcoin", tradeable.getPriceDcoin());
            response.put("rate_unit", tradeable.getRateUnit());
            response.put("name", tradeable.getName());
            response.put("trade_type", tradeable.getTradeType());

            // Zustand abhängig vom Typ des Tradeable
            if (tradeable instanceof TItem) {
                response.put("item_condition", ((TItem) tradeable).getItemCondition());
            } else if (tradeable instanceof TService) {
                response.put("service_condition", ((TService) tradeable).getServiceCondition());
            }

            // Kategorie-Informationen
            Category category = tradeable.getCategory();
            if (category != null) {
                response.put("category_name", category.getName());
            }

            // Benutzerinformationen
            User user = tradeable.getUser();
            if (user != null) {
                response.put("email", user.getEmail());
                response.put("sellerId", user.getId());
            }

            response.put("itemOrServiceId", tradeable.getId());

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Erstellung des Handels, wenn Einzelangebot gefällt und getauscht wird
    @PostMapping("/trade")
    public ResponseEntity<?> createTrade(@RequestBody TradeData tradeData) {
        try {
            Trading createdTrade = tradeableService.createTrade(tradeData);
            return ResponseEntity.ok(createdTrade);
        } catch (Exception e) {
            logger.severe("Fehler beim Erstellen des Handels: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Fehler beim Erstellen des Handels");
        }
    }
}
