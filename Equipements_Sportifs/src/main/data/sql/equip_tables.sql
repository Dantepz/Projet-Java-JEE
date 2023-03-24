-- MySQL dump 10.13  Distrib 8.0.32, for macos13 (x86_64)
--
-- Host: 127.0.0.1    Database: equipements_sportifs
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `equipement_sportif`
--

DROP TABLE IF EXISTS `equipement_sportif`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `equipement_sportif` (
  `equipment_id` varchar(15) NOT NULL,
  `equ_nom` varchar(200) NOT NULL,
  `com_insee` varchar(6) NOT NULL,
  `ins_numero_install` varchar(11) NOT NULL,
  `ins_nom` varchar(500) NOT NULL,
  `nature_libelle` varchar(100) NOT NULL,
  `equ_surface_evolution` float DEFAULT NULL,
  `equip_gpsx` float NOT NULL,
  `equip_gpsy` float NOT NULL,
  `equipment_type_code_id` varchar(5) NOT NULL,
  PRIMARY KEY (`equipment_id`),
  KEY `equipement_sportif_equipment_type_equipment_type_code_fk` (`equipment_type_code_id`),
  KEY `equipement_sportif_mairie_mairie_insee_fk` (`com_insee`),
  CONSTRAINT `equipement_sportif_equipment_type_equipment_type_code_fk` FOREIGN KEY (`equipment_type_code_id`) REFERENCES `equipment_type` (`equipment_type_code`),
  CONSTRAINT `equipement_sportif_mairie_mairie_insee_fk` FOREIGN KEY (`com_insee`) REFERENCES `mairie` (`mairie_insee`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `equipment_type`
--

DROP TABLE IF EXISTS `equipment_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `equipment_type` (
  `equipment_type_code` varchar(5) NOT NULL,
  `equipment_type_lib` varchar(200) NOT NULL,
  `equipment_famille` varchar(200) NOT NULL,
  `equipment_categorie` varchar(200) NOT NULL,
  PRIMARY KEY (`equipment_type_code`),
  UNIQUE KEY `equipment_other_info_uk` (`equipment_type_lib`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `m_o`
--

DROP TABLE IF EXISTS `m_o`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `m_o` (
  `mairie_insee` varchar(6) NOT NULL,
  `ouverture` int NOT NULL,
  PRIMARY KEY (`mairie_insee`,`ouverture`),
  KEY `m_o_mairie_ouverture_ouverture_id_fk` (`ouverture`),
  CONSTRAINT `m_o_mairie_mairie_insee_fk` FOREIGN KEY (`mairie_insee`) REFERENCES `mairie` (`mairie_insee`),
  CONSTRAINT `m_o_mairie_ouverture_ouverture_id_fk` FOREIGN KEY (`ouverture`) REFERENCES `mairie_ouverture` (`ouverture_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mairie`
--

DROP TABLE IF EXISTS `mairie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mairie` (
  `mairie_insee` varchar(6) NOT NULL,
  `mairie_nom` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`mairie_insee`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mairie_adresse`
--

DROP TABLE IF EXISTS `mairie_adresse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mairie_adresse` (
  `adresse_id` int NOT NULL AUTO_INCREMENT,
  `adresse_ligne` varchar(200) DEFAULT NULL,
  `adresse_codePostal` varchar(100) DEFAULT NULL,
  `adresse_commune` varchar(200) DEFAULT NULL,
  `adresse_latitude` varchar(100) DEFAULT NULL,
  `adresse_longitude` varchar(100) DEFAULT NULL,
  `adresse_precision` varchar(100) DEFAULT NULL,
  `mairie_insee` varchar(6) NOT NULL,
  PRIMARY KEY (`adresse_id`),
  KEY `mairie_adresse_mairie_mairie_insee_fk` (`mairie_insee`),
  CONSTRAINT `mairie_adresse_mairie_mairie_insee_fk` FOREIGN KEY (`mairie_insee`) REFERENCES `mairie` (`mairie_insee`)
) ENGINE=InnoDB AUTO_INCREMENT=36072 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mairie_coordonnee`
--

DROP TABLE IF EXISTS `mairie_coordonnee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mairie_coordonnee` (
  `coordonnees_id` int NOT NULL AUTO_INCREMENT,
  `coordonnees_telephone` varchar(25) DEFAULT NULL,
  `coordonnees_mail` varchar(200) DEFAULT NULL,
  `coordonnees_url` varchar(200) DEFAULT NULL,
  `mairie_insee` varchar(6) NOT NULL,
  PRIMARY KEY (`coordonnees_id`),
  KEY `mairie_coordonnee_mairie_mairie_insee_fk` (`mairie_insee`),
  CONSTRAINT `mairie_coordonnee_mairie_mairie_insee_fk` FOREIGN KEY (`mairie_insee`) REFERENCES `mairie` (`mairie_insee`)
) ENGINE=InnoDB AUTO_INCREMENT=36072 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mairie_ouverture`
--

DROP TABLE IF EXISTS `mairie_ouverture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mairie_ouverture` (
  `ouverture_id` int NOT NULL AUTO_INCREMENT,
  `ouverture_plageJ_debut` varchar(10) NOT NULL,
  `ouverture_plageJ_fin` varchar(10) NOT NULL,
  `ouverture_plageH_debut` timestamp NOT NULL,
  `ouverture_plageH_fin` timestamp NOT NULL,
  PRIMARY KEY (`ouverture_id`),
  UNIQUE KEY `UNIQUE` (`ouverture_plageJ_debut`,`ouverture_plageJ_fin`,`ouverture_plageH_debut`,`ouverture_plageH_fin`)
) ENGINE=InnoDB AUTO_INCREMENT=63767 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-24 20:58:59
