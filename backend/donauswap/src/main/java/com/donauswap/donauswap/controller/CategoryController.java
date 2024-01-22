package com.donauswap.donauswap.controller;

import com.donauswap.donauswap.model.Category;
import com.donauswap.donauswap.model.Tradeable;
import com.donauswap.donauswap.model.Image;
import com.donauswap.donauswap.service.CategoryService;
import com.donauswap.donauswap.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

// Controller um Bilder und Kategoriebezeichnungen aus der Datenbank abzurufen
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ImageRepository imageRepository;

    // url des backend - servers wird benötigt zum transfer von Daten zw. Front - und Backend
    private String baseUrl = "http://localhost:8080";

    // Abfrage aller Kategoriebezeichnungen
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories()
                .stream()
                .map(this::convertCategoryImagePath)
                .collect(Collectors.toList());
    }

    // Abfrage der entsprechenden Kategorie - Bilder
    private Category convertCategoryImagePath(Category category) {
        String imagePath = category.getPicturePath();
        imagePath = imagePath.replace("\\", "/");
        String fullPath = baseUrl + "/categories" + imagePath.substring(imagePath.lastIndexOf("/"));
        category.setPicturePath(fullPath);
        return category;
    }

    // Abfrage der Waren bzw. Dienstleistungsbilder
    @GetMapping("/{categoryId}/tradeables")
    public ResponseEntity<List<Tradeable>> getTradeablesByCategory(@PathVariable Long categoryId) {
        List<Tradeable> tradeables = categoryService.getTradeablesByCategoryId(categoryId);
        if (tradeables.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        tradeables.forEach(this::convertTradeableImagePath);

        return ResponseEntity.ok(tradeables);
    }

    // Filterung der Hauptbilder
    private void convertTradeableImagePath(Tradeable tradeable) {
        Image mainImage = imageRepository.findMainImageByTradeable(tradeable.getId());
        if (mainImage != null) {
            String imagePath = mainImage.getPicturePath();
            imagePath = imagePath.replace("\\", "/");
            if (imagePath.contains("../uploaded_images/")) {
                imagePath = imagePath.replace("../uploaded_images/", "");
            }
            String fullPath = baseUrl + "/uploaded_images/" + imagePath;
            tradeable.setMainImagePath(fullPath);
        }
    }
}
