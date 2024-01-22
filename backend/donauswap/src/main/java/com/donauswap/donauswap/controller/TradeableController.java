package com.donauswap.donauswap.controller;

import com.donauswap.donauswap.model.Tradeable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.donauswap.donauswap.service.TradeableService;
import com.donauswap.donauswap.model.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;

//Controller zum Hochladen der Informationen beim Erstellen eines Tauschangebots
@RestController
@RequestMapping("/api/images")
public class TradeableController {
    private static final Logger logger = LoggerFactory.getLogger(TradeableController.class);

    @Autowired
    private TradeableService tradeableService;

    //hochzuladende Inhalte
    @PostMapping
    public ResponseEntity<?> uploadImages(@RequestParam("mainImage") MultipartFile mainImage,
                                          @RequestParam(value = "additionalImages", required = false) MultipartFile[] additionalImages,
                                          @RequestParam("description") String description,
                                          @RequestParam("price_dcoin") Double priceDcoin,
                                          @RequestParam("name") String name,
                                          @RequestParam("tradeType") String tradeType,
                                          @RequestParam("condition") String condition,
                                          @RequestParam("categoryId") Long categoryId,
                                          @RequestParam("userEmail") String userEmail,
                                          @RequestParam("rateUnit") String rateUnit) {
        try {
            Tradeable tradeable = tradeableService.saveTradeable(name, description, priceDcoin, tradeType, condition, categoryId, userEmail, rateUnit);
            Image savedMainImage = tradeableService.saveFile(mainImage, true, tradeable.getId());

            if (additionalImages != null) {
                for (MultipartFile additionalImage : additionalImages) {
                    Image savedAdditionalImage = tradeableService.saveFile(additionalImage, false, tradeable.getId());
                }
            }

            return ResponseEntity.ok("{\"message\":\"Tradeable and images uploaded successfully\"}");
        } catch (IOException e) {
            logger.error("Error uploading tradeable and images", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\":\"Failed to upload tradeable and images\"}");
        }
    }
}
