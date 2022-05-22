-- phpMyAdmin SQL Dump
-- version 4.9.7
-- https://www.phpmyadmin.net/
--
-- Anamakine: localhost:3306
-- Üretim Zamanı: 10 May 2022, 13:23:32
-- Sunucu sürümü: 5.7.38

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `voilacar_home_db`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `cardvisits`
--

CREATE TABLE `cardvisits` (
  `id` int(11) NOT NULL,
  `url` varchar(255) NOT NULL DEFAULT '',
  `cardvisit_note_for_admins` text,
  `permission_company_logo` int(11) DEFAULT '0',
  `company_logo_path` varchar(500) DEFAULT ' 	/CardvisitUI/Dash/media/avatars/blank.png',
  `profile_photo` varchar(500) DEFAULT '/CardvisitUI/Dash/media/avatars/blank.png',
  `name` varchar(255) DEFAULT '',
  `surname` varchar(255) DEFAULT '',
  `email` varchar(255) DEFAULT '',
  `email1` varchar(255) DEFAULT '',
  `email2` varchar(255) DEFAULT '',
  `password` varchar(255) DEFAULT '',
  `is_pin` tinyint(4) DEFAULT '0',
  `pin_code` int(11) DEFAULT NULL,
  `profille_visits` int(11) DEFAULT '0',
  `save_button_clicks` int(11) DEFAULT '0',
  `phone` varchar(255) DEFAULT NULL,
  `phone2` varchar(255) DEFAULT NULL,
  `phone3` varchar(255) DEFAULT NULL,
  `whatsapp` varchar(255) DEFAULT NULL,
  `fax` varchar(255) DEFAULT NULL,
  `save_button_text` varchar(255) DEFAULT NULL,
  `company_name` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `company_web_site` varchar(255) DEFAULT NULL,
  `web_site_1` varchar(255) DEFAULT NULL,
  `web_site_2` varchar(255) DEFAULT NULL,
  `web_site_3` varchar(255) DEFAULT NULL,
  `web_site_4` varchar(255) DEFAULT NULL,
  `company_phone` varchar(255) DEFAULT NULL,
  `company_email` varchar(255) DEFAULT NULL,
  `company_address` varchar(255) DEFAULT NULL,
  `iban` varchar(255) DEFAULT NULL,
  `iban_bank` varchar(255) DEFAULT NULL,
  `crypto_account` varchar(255) DEFAULT NULL,
  `crypto_network` varchar(255) DEFAULT NULL,
  `crypto_address` varchar(255) DEFAULT NULL,
  `note` text,
  `lang` varchar(10) DEFAULT 'tr_TR',
  `profile_lock` tinyint(4) DEFAULT '0',
  `facebook` text,
  `telegram` text,
  `twitter` text,
  `instagram` text,
  `tiktok` text,
  `linkedin` text,
  `youtube` text,
  `behance` text,
  `dribbble` text,
  `pinterest` text,
  `github` text,
  `snapchat` text,
  `dark_mode` int(11) NOT NULL DEFAULT '0',
  `theme_color` int(11) NOT NULL DEFAULT '0',
  `tab1` varchar(255) DEFAULT 'profile',
  `tab2` varchar(255) DEFAULT 'social',
  `tab3` varchar(255) DEFAULT 'company',
  `show_qr_in_page` int(11) DEFAULT '0',
  `is_cardvisit_print` tinyint(4) DEFAULT '0',
  `is_cardvisit_ship` tinyint(4) DEFAULT '0',
  `is_cardvisit_url_lock` tinyint(4) DEFAULT '1',
  `is_cardvisit_active` tinyint(4) DEFAULT '0',
  `cardvisit_session_token` text,
  `password_reset_token` text,
  `ban` int(11) DEFAULT '0',
  `approved` int(11) DEFAULT '0',
  `agreement_approved` int(11) NOT NULL DEFAULT '0',
  `first_login_user_agent` text,
  `first_login_ip` varchar(255) DEFAULT NULL,
  `first_login_date` datetime DEFAULT NULL,
  `last_login_user_agent` text,
  `last_login_ip` varchar(255) DEFAULT NULL,
  `last_login_date` datetime DEFAULT NULL,
  `last_login_device` varchar(255) DEFAULT NULL,
  `owner_company` varchar(255) DEFAULT 'voila',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `cardvisits`
--
ALTER TABLE `cardvisits`
  ADD PRIMARY KEY (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
