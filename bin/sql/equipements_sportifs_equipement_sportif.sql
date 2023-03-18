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
  `equ_nom` varchar(48) NOT NULL,
  `com_insee` varchar(6) NOT NULL,
  `ins_numero_install` varchar(11) NOT NULL,
  `ins_nom` varchar(48) NOT NULL,
  `nature_libelle` varchar(48) NOT NULL,
  `equ_surface_evolution` float DEFAULT NULL,
  `equip_gpsx` float NOT NULL,
  `equip_gpsy` float NOT NULL,
  `equipment_type_code_id` varchar(5) NOT NULL,
  PRIMARY KEY (`equipment_id`),
  KEY `equipement_sportif_equipment_type_equipment_type_code_fk` (`equipment_type_code_id`),
  CONSTRAINT `equipement_sportif_equipment_type_equipment_type_code_fk` FOREIGN KEY (`equipment_type_code_id`) REFERENCES `equipment_type` (`equipment_type_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipement_sportif`
--

LOCK TABLES `equipement_sportif` WRITE;
/*!40000 ALTER TABLE `equipement_sportif` DISABLE KEYS */;
/*!40000 ALTER TABLE `equipement_sportif` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-18 10:59:03
