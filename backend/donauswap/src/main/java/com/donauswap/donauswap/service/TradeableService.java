package com.donauswap.donauswap.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import com.donauswap.donauswap.repository.ImageRepository;
import com.donauswap.donauswap.repository.TradeableRepository;
import com.donauswap.donauswap.repository.TServiceRepository;
import com.donauswap.donauswap.repository.TItemRepository;
import com.donauswap.donauswap.repository.CategoryRepository;
import com.donauswap.donauswap.repository.UserRepository;
import com.donauswap.donauswap.repository.TradingRepository;
import com.donauswap.donauswap.model.Image;
import com.donauswap.donauswap.model.TItem;
import com.donauswap.donauswap.model.TService;
import com.donauswap.donauswap.model.Tradeable;
import com.donauswap.donauswap.model.Category;
import com.donauswap.donauswap.model.User;
import com.donauswap.donauswap.model.Trading;
import com.donauswap.donauswap.model.TradeData;
import java.time.LocalDateTime;

import java.util.Optional;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

// Service zur Unterstützung des TradeableControllers beim Speichern von Informationen eines vom User erstellten Angebots
@Service
public class TradeableService {
    private static final Logger logger = LoggerFactory.getLogger(TradeableService.class);

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private TradeableRepository tradeableRepository;
    @Autowired
    private TServiceRepository tServiceRepository;
    @Autowired
    private TItemRepository tItemRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TradingRepository tradingRepository;

    public List<Image> getRandomMainImages() {
        return imageRepository.findRandomMainImages();
    }

    public Image saveFile(MultipartFile file, boolean isMain, Long tradeableId) throws IOException {
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir, fileName);

        Files.copy(file.getInputStream(), filePath);

        Image image = new Image(filePath.toString(), isMain);
        if (tradeableId != null) {
            Tradeable tradeable = tradeableRepository.findById(tradeableId)
                    .orElseThrow(() -> new RuntimeException("Tradeable mit ID " + tradeableId + " nicht gefunden."));
            image.setTradeable(tradeable);
        }
        return imageRepository.save(image);
    }

    public Tradeable saveTradeable(String name, String description, Double priceDcoin, String tradeType, String condition, Long categoryId, String userEmail, String rateUnit) {
        User user = userRepository.findByEmail(userEmail);
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Kategorie nicht gefunden"));

        Tradeable tradeable;
        if ("TandlerSpot: Tausch von Gütern".equals(tradeType)) {
            TItem item = new TItem();
            item.setName(name);
            item.setDescription(description);
            item.setPriceDcoin(priceDcoin);
            item.setItemCondition(condition);
            item.setTradeType(tradeType);
            item.setCategory(category);
            item.setRateUnit(rateUnit);
            if (user != null) item.setUser(user);
            tradeable = tItemRepository.save(item);
        } else if ("TagwerkHub: Tausch von Dienstleistungen".equals(tradeType)) {
            TService service = new TService();
            service.setName(name);
            service.setDescription(description);
            service.setPriceDcoin(priceDcoin);
            service.setServiceCondition(condition);
            service.setTradeType(tradeType);
            service.setCategory(category);
            service.setRateUnit(rateUnit);
            if (user != null) service.setUser(user);
            tradeable = tServiceRepository.save(service);
        } else {
            throw new IllegalArgumentException("Ungültiger tradeType");
        }
        return tradeable;
    }

    public List<Image> searchImagesByKeyword(String keyword) {
        List<Tradeable> tradeables = tradeableRepository.searchByKeyword(keyword);
        return tradeables.stream()
                .flatMap(tradeable -> imageRepository.findByTradeable(tradeable).stream())
                .collect(Collectors.toList());
    }

    public Optional<Tradeable> getTradeableByName(String name) {
        logger.info("Suche nach Tradeable mit dem Namen: {}", name);
        Optional<Tradeable> tradeableOptional = tradeableRepository.findByName(name);

        if (tradeableOptional.isPresent()) {
            logger.info("Gefundenes Tradeable: {}", tradeableOptional.get());
        } else {
            logger.warn("Kein Tradeable mit dem Namen {} gefunden", name);
        }

        return tradeableOptional;
    }
    public Optional<Tradeable> getTradeableById(Long id) {
        return tradeableRepository.findById(id);
    }

    public Trading createTrade(TradeData tradeData) {
        Trading trade = new Trading();
        trade.setBuyerId(tradeData.getBuyerId());
        trade.setSellerId(tradeData.getSellerId());
        trade.setItemOrServiceId(tradeData.getItemOrServiceId());
        trade.setMainPictureId(tradeData.getMainPictureId());
        trade.setPrice(tradeData.getPrice());
        trade.setTimeStamp(LocalDateTime.now());
        return tradingRepository.save(trade);
    }
}
