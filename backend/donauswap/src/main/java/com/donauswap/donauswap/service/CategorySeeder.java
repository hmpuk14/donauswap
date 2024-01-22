package com.donauswap.donauswap.service;

import com.donauswap.donauswap.model.Category;
import com.donauswap.donauswap.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Optional;

//dieser Seeder befüllt die Tabelle category mit Daten, bzw. pflegt Änderungen ein
@Component
public class CategorySeeder implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    @Value("${file.category-images-dir}")
    private String categoryImagesBasePath;
    public CategorySeeder(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        String[][] categoryDetails = {
                {"Digitale Güter", "E-Books, Software, digitale Kunstwerke, Gutscheincodes, Podcasts, Hörbücher, Video-Tutorials, Stock-Fotografie, In-Game-Währungen, Skins, Charakter-Upgrades", "digitale_gueter.png"},
                {"Selbstgemachtes", "Schmuck, Kunstwerke, Handarbeiten, selbstgemachte Lebensmittel, Dekoration, Geschenke, T-Shirts", "selbstgemachtes.png"},
                {"Immobilien", "Tausche zum Beispiel deine Wohnung oder dein Haus für den Urlaub, oder auch für längere Aufenthalte", "immobilien.png"},
                {"Kleidung", "Damen-, Herren- und Kinderbekleidung, Schuhe, Accessoires, Handtaschen, nachhaltige Kleidungsstücke, Mode", "kleidung.png"},
                {"Bücher", "Alle Genres von Büchern, wie Epik, Dramatik und Lyrik aber auch Roman, Krimi, Adventure- oder Horrorliteratur und Fachliteratur", "buecher.png"},
                {"Konsumgüter", "Alles, was konsumiert wird, von erlesenen Köstlichkeiten über Haushaltsgeräte bis hin zu Freizeitprodukten und Luxusartikeln. Auch alles, was in keine der anderen Kategorien passt, kann hier Platz finden.", "konsumgueter.png"},
                {"Handwerk", "Alle handwerklichen Dienstleistungen wie bspw. Reparaturen, Malerarbeiten, Möbelaufbau, Küchenaufbau, Übersiedelung, Holzschnitzerei, Kunsthandwerk", "handwerk.png"},
                {"Gartenarbeit", "Jede Art von Arbeit im Haus- Außenbereich, wie bspw. Heckenschneiden, Unkrautjäten, Pflanzen setzen, Gartenpflege, Bäume entfernen, Teich- oder Poolpflege", "gartenarbeit.png"},
                {"Hausarbeit", "Alle Arbeiten rund um den Hausinnenbereich und alle Dienstleistungen Haustiere betreffend. Bspw. Fensterputzen, Raumpflege, Bügeln, Nachhilfe, Besuchsdienste, Hundesitting", "hausarbeit.png"},
                {"Babysitting", "Betreuung für Kinder jeder Altersstufe, auch Nachhilfe und Musik-, oder Sportangebote", "babysitting.png"},
                {"Fotografie", "Portrait-, Landschaft-, Event- und Drohnenfotografie, Videoschnitt, Videografie, Videoproduktion, Grafikdesign", "fotografie.png"},
                {"Kosmetik", "Dienstleistungen wie Gesichtspflege, Nageldesign, Pediküre, Maniküre und alle weiteren Schönheits- und Wellnessdienstleistungen", "kosmetik.png"}
        };

        for (String[] details : categoryDetails) {
            Optional<Category> existingCategory = categoryRepository.findByName(details[0]);
            if (!existingCategory.isPresent()) {
                String fullImagePath = categoryImagesBasePath + "/" + details[2];
                Category newCategory = new Category(details[0], details[1], fullImagePath);
                categoryRepository.save(newCategory);
            }
        }
    }
}
