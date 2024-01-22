package com.donauswap.donauswap.service;

import com.donauswap.donauswap.model.Category;
import com.donauswap.donauswap.model.Image;
import com.donauswap.donauswap.model.Tradeable;
import com.donauswap.donauswap.repository.CategoryRepository;
import com.donauswap.donauswap.repository.ImageRepository;
import com.donauswap.donauswap.repository.TradeableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//Service zur Unterstützung des CategoryControllers beim holen der Kategorie-Bezeichnungen, -Bider und der Angebots-Bilder
@Service
public class CategoryService {

    private static final Logger log = LoggerFactory.getLogger(CategoryService.class);
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private TradeableRepository tradeableRepository;

    @Autowired
    private ImageRepository imageRepository;

    public List<Category> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        log.info("Loaded categories: {}", categories);
        return categories;
    }

    public List<Tradeable> getTradeablesByCategoryId(Long categoryId) {
        return tradeableRepository.findByCategoryId(categoryId);
    }
    public void setMainImageForTradeables(List<Tradeable> tradeables) {
        for (Tradeable tradeable : tradeables) {
            Image mainImage = imageRepository.findMainImageByTradeable(tradeable.getId());
            if (mainImage != null) {
                tradeable.setMainImagePath(mainImage.getPicturePath());
            }
        }
    }
}
