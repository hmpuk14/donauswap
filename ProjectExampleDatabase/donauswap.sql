-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 02. Jan 2024 um 13:42
-- Server-Version: 10.4.28-MariaDB
-- PHP-Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `donauswap`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `category`
--

CREATE TABLE `category` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Daten für Tabelle `category`
--

INSERT INTO `category` (`id`, `name`) VALUES
(1, 'Digitale Güter'),
(2, 'Selbstgemachtes'),
(3, 'Immobilien'),
(4, 'Kleidung'),
(5, 'Bücher'),
(6, 'Konsumgüter'),
(7, 'Handwerk'),
(8, 'Gartenarbeit'),
(9, 'Hausarbeit'),
(10, 'Babysitting'),
(11, 'Fotografie'),
(12, 'Kosmetik');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `category_seq`
--

CREATE TABLE `category_seq` (
  `next_not_cached_value` bigint(21) NOT NULL,
  `minimum_value` bigint(21) NOT NULL,
  `maximum_value` bigint(21) NOT NULL,
  `start_value` bigint(21) NOT NULL COMMENT 'start value when sequences is created or value if RESTART is used',
  `increment` bigint(21) NOT NULL COMMENT 'increment value',
  `cache_size` bigint(21) UNSIGNED NOT NULL,
  `cycle_option` tinyint(1) UNSIGNED NOT NULL COMMENT '0 if no cycles are allowed, 1 if the sequence should begin a new cycle when maximum_value is passed',
  `cycle_count` bigint(21) NOT NULL COMMENT 'How many cycles have been done'
) ENGINE=InnoDB;

--
-- Daten für Tabelle `category_seq`
--

INSERT INTO `category_seq` (`next_not_cached_value`, `minimum_value`, `maximum_value`, `start_value`, `increment`, `cache_size`, `cycle_option`, `cycle_count`) VALUES
(101, 1, 9223372036854775806, 1, 50, 0, 0, 0);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `image`
--

CREATE TABLE `image` (
  `id` bigint(20) NOT NULL,
  `picture_path` varchar(255) DEFAULT NULL,
  `is_main` bit(1) NOT NULL,
  `tradeable_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Daten für Tabelle `image`
--

INSERT INTO `image` (`id`, `picture_path`, `is_main`, `tradeable_id`) VALUES
(33, '..\\uploaded_images\\1703710105293_1.png', b'1', 14),
(34, '..\\uploaded_images\\1703710758376_8.png', b'1', 15),
(35, '..\\uploaded_images\\1703710758376_8_5.png', b'0', 15),
(36, '..\\uploaded_images\\1703710758392_8_4.png', b'0', 15),
(37, '..\\uploaded_images\\1703710758392_8_3.png', b'0', 15),
(38, '..\\uploaded_images\\1703710939417_lebkuchenskin.png', b'1', 16),
(39, '..\\uploaded_images\\1703710939417_lebkuchenskin_2.jpg', b'0', 16),
(40, '..\\uploaded_images\\1703710939417_lebkuchenskin_3.jpg', b'0', 16),
(41, '..\\uploaded_images\\1703711025403_fortnite_ninja_skin_2.png', b'1', 17),
(42, '..\\uploaded_images\\1703711025406_fortnite_ninja_skin_3.jpg', b'0', 17),
(43, '..\\uploaded_images\\1703711025406_fortnite_ninja_skin_4.jpg', b'0', 17),
(44, '..\\uploaded_images\\1703711025406_fortnite_ninja_skin.jpg', b'0', 17),
(45, '..\\uploaded_images\\1703711256991_7.png', b'1', 18),
(46, '..\\uploaded_images\\1703711256991_7_2.png', b'0', 18),
(47, '..\\uploaded_images\\1703711257007_7_4.png', b'0', 18),
(48, '..\\uploaded_images\\1703711257007_7_5.png', b'0', 18),
(49, '..\\uploaded_images\\1703711514756_9.png', b'1', 19),
(50, '..\\uploaded_images\\1703711665290_3_3.png', b'1', 20),
(51, '..\\uploaded_images\\1703711665304_3.png', b'0', 20),
(52, '..\\uploaded_images\\1703711665304_3_4.png', b'0', 20),
(53, '..\\uploaded_images\\1703711665304_3_2.png', b'0', 20),
(54, '..\\uploaded_images\\1703711832698_5.png', b'1', 21),
(55, '..\\uploaded_images\\1703711942662_funky_ops.jpg', b'1', 22),
(56, '..\\uploaded_images\\1703711942677_funky_ops_2.png', b'0', 22),
(57, '..\\uploaded_images\\1703711942677_funky_ops_3.jpg', b'0', 22),
(58, '..\\uploaded_images\\1703712167435_2.png', b'1', 23),
(59, '..\\uploaded_images\\1703712263051_10.png', b'1', 24),
(60, '..\\uploaded_images\\1703712263065_10_2.png', b'0', 24),
(61, '..\\uploaded_images\\1703712263068_10_3.png', b'0', 24),
(62, '..\\uploaded_images\\1703712263068_10_5.png', b'0', 24),
(63, '..\\uploaded_images\\1703712375215_4.png', b'1', 25),
(64, '..\\uploaded_images\\1703713648841_6.png', b'1', 26),
(65, '..\\uploaded_images\\1703714349846_minecraf_hoodie_2.png', b'1', 27),
(66, '..\\uploaded_images\\1703714534918_11_2.png', b'1', 28);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `tradeable`
--

CREATE TABLE `tradeable` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `price_dcoin` double DEFAULT NULL,
  `rate_unit` varchar(255) DEFAULT NULL,
  `trade_type` varchar(255) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Daten für Tabelle `tradeable`
--

INSERT INTO `tradeable` (`id`, `name`, `description`, `price_dcoin`, `rate_unit`, `trade_type`, `category_id`, `user_id`) VALUES
(14, 'Haube und Handschuhe', 'Moderne, selbstgestrickte Haube mit Quaste und geschneiderte Handschuhe. Nur gemeinsam abzugeben. ', 18, 'pauschal', 'TandlerSpot: Tausch von Gütern', 2, 1),
(15, 'Nageldesign', 'Maniküre und kunstvolles Lackieren. ', 50, 'pauschal', 'TagwerkHub: Tausch von Dienstleistungen', 12, 1),
(16, 'Fortnite Game Skin', 'Fortnite Lebkuchenskin männlich oder weiblich', 25, 'pro Stück', 'TandlerSpot: Tausch von Gütern', 1, 2),
(17, 'Fortnite Game Skin', 'Fortnite Ninja Skin, rare', 80, 'pro Stück', 'TandlerSpot: Tausch von Gütern', 1, 2),
(18, 'Haus Vermietung', 'schönes Haus in ruhiger Lage am Mondsee wochenweise zu vermieten. Es ist 500m vom Seeufer entfernt. Zum nächsten Ort sind es 3 km. Auch ein schöner Geh-bzw. Radfahrweg führt in den Ort. Es gibt eine Zugverbindung zum Mondsee. Eine Pauschale für Strom/Heiz', 980, 'pro Woche', 'TandlerSpot: Tausch von Gütern', 3, 3),
(19, 'Podcast zu Trendthemen', 'Podcast zu verschiedenen Trendthemen wie Mode, Life-Style, Work-Life-Balance, Future Trends, In Lokale in Wien, Kochen, Reisen, usw. eine genaue Liste schicke ich gerne via mail. Die Podcast werden zum Download zur Verfügung gestellt. Jeder Podcast dauert', 27, 'pro Stück', 'TandlerSpot: Tausch von Gütern', 1, 3),
(20, 'Drohne mit Kamera', 'Drohne mit Kamera. Flughöhe 120m, Drohnenführerschein erforderlich.', 330, 'pro Stück', 'TandlerSpot: Tausch von Gütern', 1, 4),
(21, 'Heckenschneiden', 'Heckenschneiden und trimmen. Auch unterschiedliche einfache geometrische Formen.', 70, 'pro Stunde', 'TagwerkHub: Tausch von Dienstleistungen', 8, 4),
(22, 'Fortnite Game Skin', 'Fortnite Skin Funky Ops. Sehr selten!', 90, 'pro Stück', 'TandlerSpot: Tausch von Gütern', 1, 4),
(23, 'Kinderbetreuung', 'Kinderbetreuung. Babys, Kleinkinder und auch Beaufsichtigung von Jugendlichen während Deines Urlaubs (Partybremse).', 60, 'pro Stunde', 'TagwerkHub: Tausch von Dienstleistungen', 10, 1),
(24, 'Lederschuhe', 'schön gearbeitete, echte italienische Lederschuhe. Qualitätsarbeit. Gut erhalten, kaum getragen. ', 120, 'pro Stück', 'TandlerSpot: Tausch von Gütern', 4, 4),
(25, 'Malerarbeit', 'Ich helfe sehr gerne beim ausmalen. Ich rühre Farbe, male Wände und Decken und mache auch gerne die feinen Malerarbeiten rund um Fenster, Türstücke etc. Natürlich helfe ich auch beim Abdecken. ', 40, 'pro Stunde', 'TagwerkHub: Tausch von Dienstleistungen', 7, 2),
(26, 'Kaffee', 'Kaffee aus eigener Rösterei. Aromatisch. Premium Produkt. Der angegebene Preis ist pro 500g Packung.', 28, 'pro Stück', 'TandlerSpot: Tausch von Gütern', 6, 2),
(27, 'Minecraft Gaming Skin', 'minecraft skin', 46, 'pro Stück', 'TandlerSpot: Tausch von Gütern', 1, 4),
(28, 'Landschaftsfotografie', 'Landschaftsfotografie, gerne kannst Du mich beauftragen. Ich bin auch für längere Reisen buchbar. Pauschale für eine Reise nach Vereinbarung. ', 80, 'pro Stück', 'TagwerkHub: Tausch von Dienstleistungen', 11, 3);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `trading`
--

CREATE TABLE `trading` (
  `id` bigint(20) NOT NULL,
  `main_picture_id` bigint(20) DEFAULT NULL,
  `item_or_service_id` bigint(20) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `seller_id` bigint(20) DEFAULT NULL,
  `buyer_id` bigint(20) DEFAULT NULL,
  `time_stamp` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `t_item`
--

CREATE TABLE `t_item` (
  `id` bigint(20) NOT NULL,
  `item_condition` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Daten für Tabelle `t_item`
--

INSERT INTO `t_item` (`id`, `item_condition`) VALUES
(14, 'neu/noch nicht benutzt'),
(16, 'neuwertig/kaum Gebrauchsspuren'),
(17, 'neuwertig/kaum Gebrauchsspuren'),
(18, 'gebraucht/mit einigen Gebrauchsspuren'),
(19, 'neu/noch nicht benutzt'),
(20, 'gebraucht/mit einigen Gebrauchsspuren'),
(22, 'neu/noch nicht benutzt'),
(24, 'gebraucht/mit einigen Gebrauchsspuren'),
(26, 'neu/noch nicht benutzt'),
(27, 'neu/noch nicht benutzt');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `t_service`
--

CREATE TABLE `t_service` (
  `id` bigint(20) NOT NULL,
  `service_condition` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Daten für Tabelle `t_service`
--

INSERT INTO `t_service` (`id`, `service_condition`) VALUES
(15, 'Profi'),
(21, 'Hobby'),
(23, 'Hobby'),
(25, 'helper'),
(28, 'Hobby');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `postal_code` int(11) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `dcoin_saldo` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Daten für Tabelle `user`
--

INSERT INTO `user` (`id`, `email`, `password`, `first_name`, `last_name`, `street`, `postal_code`, `city`, `dcoin_saldo`) VALUES
(1, 'k.h@gmx.at', '$2a$10$tKckfgzseqcrc5piyhbHVu6/RWaVpBbhdFSMIxQUrhMvIPaZ.9xmK', 'Karin', 'Hammer', 'Seilerstätterstraße 51/4/7', 1160, 'Wien', 5),
(2, 't.robinson@gmail.com', '$2a$10$HjPdN9/blXgyfPlCe0ytL.RvegXnnhfZMxSfBLSGeV2dVKEO6pP36', 'Tom', 'Robinson', 'am Ring 13/1', 1010, 'Wien', 190),
(3, 's.malden@gmx.at', '$2a$10$8NADntVsV4f7MWLNTeNzZebaiIUDfVSKz031ZSAdb8Off6AJB0wTi', 'Sybille', 'Malden', 'Sternweg 17/5/3', 2500, 'Baden', NULL),
(4, 'rudi.lustig@outlook.com', '$2a$10$kmoQe91YeUaeu1gX2Ft9XeEETyu5WbuofT26hKzbUK3qyDbmItZde', 'Rudi', 'Lustig', 'Weizengasse 5', 2544, 'Leobersdorf', 590);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `user_seq`
--

CREATE TABLE `user_seq` (
  `next_not_cached_value` bigint(21) NOT NULL,
  `minimum_value` bigint(21) NOT NULL,
  `maximum_value` bigint(21) NOT NULL,
  `start_value` bigint(21) NOT NULL COMMENT 'start value when sequences is created or value if RESTART is used',
  `increment` bigint(21) NOT NULL COMMENT 'increment value',
  `cache_size` bigint(21) UNSIGNED NOT NULL,
  `cycle_option` tinyint(1) UNSIGNED NOT NULL COMMENT '0 if no cycles are allowed, 1 if the sequence should begin a new cycle when maximum_value is passed',
  `cycle_count` bigint(21) NOT NULL COMMENT 'How many cycles have been done'
) ENGINE=InnoDB;

--
-- Daten für Tabelle `user_seq`
--

INSERT INTO `user_seq` (`next_not_cached_value`, `minimum_value`, `maximum_value`, `start_value`, `increment`, `cache_size`, `cycle_option`, `cycle_count`) VALUES
(501, 1, 9223372036854775806, 1, 50, 0, 0, 0);

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `image`
--
ALTER TABLE `image`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK1cdiify0cacd1fgalyrtwn9gy` (`tradeable_id`);

--
-- Indizes für die Tabelle `tradeable`
--
ALTER TABLE `tradeable`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKf5xrkjhykcp41n5dh2hie2fhr` (`category_id`),
  ADD KEY `FK1hw0i98qwfpgt4q1iimechne8` (`user_id`);

--
-- Indizes für die Tabelle `trading`
--
ALTER TABLE `trading`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `t_item`
--
ALTER TABLE `t_item`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `t_service`
--
ALTER TABLE `t_service`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `image`
--
ALTER TABLE `image`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=81;

--
-- AUTO_INCREMENT für Tabelle `tradeable`
--
ALTER TABLE `tradeable`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT für Tabelle `trading`
--
ALTER TABLE `trading`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `image`
--
ALTER TABLE `image`
  ADD CONSTRAINT `FK1cdiify0cacd1fgalyrtwn9gy` FOREIGN KEY (`tradeable_id`) REFERENCES `tradeable` (`id`);

--
-- Constraints der Tabelle `tradeable`
--
ALTER TABLE `tradeable`
  ADD CONSTRAINT `FK1hw0i98qwfpgt4q1iimechne8` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKf5xrkjhykcp41n5dh2hie2fhr` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`);

--
-- Constraints der Tabelle `t_item`
--
ALTER TABLE `t_item`
  ADD CONSTRAINT `FK57wh0fs0uukofqm51o2qj1gqq` FOREIGN KEY (`id`) REFERENCES `tradeable` (`id`);

--
-- Constraints der Tabelle `t_service`
--
ALTER TABLE `t_service`
  ADD CONSTRAINT `FKg6mf1ghgggsw145g968pe2po` FOREIGN KEY (`id`) REFERENCES `tradeable` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
