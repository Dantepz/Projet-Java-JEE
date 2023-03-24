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
-- Dumping data for table `equipment_type`
--

LOCK TABLES `equipment_type` WRITE;
/*!40000 ALTER TABLE `equipment_type` DISABLE KEYS */;
INSERT INTO `equipment_type` VALUES ('0101','Bassin mixte de natation','Bassin de natation','Bassins aquatiques'),('0102','Bassin sportif de natation','Bassin de natation','Bassins aquatiques'),('0103','Bassin ludique de natation','Bassin de natation','Bassins aquatiques'),('0104','Fosse à plongeon','Bassin de natation','Bassins aquatiques'),('0105','Fosse à plongée','Bassin de natation','Bassins aquatiques'),('0106','Bassin de réception de toboggan','Bassin de natation','Bassins aquatiques'),('0201','Terrain de boules','Boulodrome','Equipements Ext.'),('0202','Terrain de pétanque','Boulodrome','Equipements Ext.'),('0203','Terrain de boules traditionnelles','Boulodrome','Equipements Ext.'),('0301','Bowling','Bowling','Autres équipements'),('0401','Circuit de vitesse','Circuit/piste de sports mécaniques','Autres équipements'),('0404','Circuit de motocross','Circuit/piste de sports mécaniques','Autres équipements'),('0405','Courses sur piste','Circuit/piste de sports mécaniques','Autres équipements'),('0407','Terrain de trial','Circuit/piste de sports mécaniques','Nature'),('0409','Piste de kart','Circuit/piste de sports mécaniques','Autres équipements'),('0410','Terrain de moto-ball','Circuit/piste de sports mécaniques','Autres équipements'),('0501','Court de tennis','Court de tennis','Courts de tennis'),('0601','Anneau / piste de cyclisme','Equipement de cyclisme','Autres équipements'),('0603','Espace trial','Equipement de cyclisme','Nature'),('0604','Piste de bicross','Equipement de cyclisme','Nature'),('0605','Piste de descente','Equipement de cyclisme','Nature'),('0606','Relais rando-vélo','Equipement de cyclisme','Nature'),('0607','Vélodrome','Equipement de cyclisme','Autres équipements'),('0608','Terrain de cyclocross','Equipement de cyclisme','Autres équipements'),('0609','Terrain de polo-vélo','Equipement de cyclisme','Autres équipements'),('0701','Domaine de ski alpin','Equipement & piste de ski','Nature'),('0703','Tremplin à ski','Equipement & piste de ski','Nature'),('0704','Piste de ski indoor','Equipement & piste de ski','Autres équipements'),('0705','Domaine nordique','Equipement & piste de ski','Nature'),('0706','Piste de luge','Equipement & piste de ski','Nature'),('0801','Salle de cours collectifs','Equipement d\'activités de forme et de santé','Salles de pratiques collectives'),('0802','Salle de musculation/cardiotraining','Equipement d\'activités de forme et de santé','Salles de pratiques collectives'),('0803','Bassin d\'exercices aquatiques','Equipement d\'activités de forme et de santé','Bassins aquatiques'),('0901','Carrière','Equipement équestre','Autres équipements'),('0902','Carrière de dressage/rond de longe','Equipement équestre','Autres équipements'),('0903','Manège','Equipement équestre','Autres équipements'),('0904','Parcours d\'obstacles','Equipement équestre','Autres équipements'),('0905','Piste de course sur le plat','Equipement équestre','Autres équipements'),('0906','Structure de tourisme équestre','Equipement équestre','Nature'),('0907','Terrain de horse-ball','Equipement équestre','Equipements Ext.'),('0908','Terrain de polo','Equipement équestre','Equipements Ext.'),('0909','Parcours de cross','Equipement équestre','Nature'),('1001','Stade d’athlétisme','Equipement d\'athlétisme','Autres équipements'),('1002','Aire de lancer','Equipement d\'athlétisme','Autres équipements'),('1003','Aire de saut','Equipement d\'athlétisme','Autres équipements'),('1004','Piste d\'athlétisme isolée','Equipement d\'athlétisme','Autres équipements'),('1101','Mur ou fronton mixte','Mur et fronton','Autres équipements'),('1102','Mur à gauche','Mur et fronton','Autres équipements'),('1103','Fronton place libre','Mur et fronton','Autres équipements'),('1104','Trinquet','Mur et fronton','Autres équipements'),('1105','Mur de tennis','Mur et fronton','Autres équipements'),('1106','Jaï-Laï','Mur et fronton','Autres équipements'),('1201','Parcours d\'initiation','Parcours de golf','Autres équipements'),('1202','Parcours 18 trous','Parcours de golf','Autres équipements'),('1204','Parcours 9 trous','Parcours de golf','Autres équipements'),('1205','Practice','Parcours de golf','Autres équipements'),('1206','Swingolf','Parcours de golf','Autres équipements'),('1301','Parcours sportif/santé','Parcours sportif/santé','Equipements Ext.'),('1401','Pas de tir à l\'arc','Pas de tir','Autres équipements'),('1402','Pas de tir à la cible','Pas de tir','Autres équipements'),('1403','Pas de tir aux plateaux','Pas de tir','Autres équipements'),('1501','Aire de sports de glace sportive','Aire de sports de glaces','Autres équipements'),('1503','Aire de sports de glace ludique','Aire de sports de glaces','Autres équipements'),('1504','Anneau de vitesse','Aire de sports de glaces','Autres équipements'),('1505','Piste de bobsleigh','Aire de sports de glaces','Autres équipements'),('1506','Salle de curling','Aire de sports de glaces','Autres équipements'),('1601','Plaine de jeux','Plaine de jeux','Autres équipements'),('1701','Plateau EPS/Multisports/city-stades','Plateau EPS','Equipements Ext.'),('1702','But/panier isolé de sport collectif','Plateau EPS','Autres équipements'),('1801','Dojo / Salle d\'arts martiaux','Salle de combat','Salles de pratiques collectives'),('1802','Salle de boxe','Salle de combat','Salles de pratiques collectives'),('1901','Salle multisports (gymnase)','Salle multisports','Salles de pratiques collectives'),('2101','Salle de basket','Salle ou terrain spécialisé','Salles de pratiques collectives'),('2102','Salle de beach-volley','Salle ou terrain spécialisé','Equipements Ext.'),('2103','Salle de billard','Salle ou terrain spécialisé','Salles de pratiques collectives'),('2104','Salle de culturisme','Salle ou terrain spécialisé','Salles de pratiques collectives'),('2105','Salle de danse','Salle ou terrain spécialisé','Salles de pratiques collectives'),('2106','Salle de gymnastique sportive','Salle ou terrain spécialisé','Salles de pratiques collectives'),('2107','Salle de handball','Salle ou terrain spécialisé','Salles de pratiques collectives'),('2108','Salle de lutte','Salle ou terrain spécialisé','Salles de pratiques collectives'),('2109','Salle de patinage sur roulette','Salle ou terrain spécialisé','Salles de pratiques collectives'),('2110','Salle de raquetball','Salle ou terrain spécialisé','Salles de pratiques collectives'),('2111','Salle de tennis de table','Salle ou terrain spécialisé','Salles de pratiques collectives'),('2112','Salle de trampoline','Salle ou terrain spécialisé','Salles de pratiques collectives'),('2113','Salle de volley-ball','Salle ou terrain spécialisé','Salles de pratiques collectives'),('2114','Salle d\'échecs','Salle ou terrain spécialisé','Salles de pratiques collectives'),('2115','Salle d\'escrime','Salle ou terrain spécialisé','Salles de pratiques collectives'),('2116','Salle d\'haltérophilie','Salle ou terrain spécialisé','Salles de pratiques collectives'),('2117','Salle ou terrain de squash','Salle ou terrain spécialisé','Salles de pratiques collectives'),('2118','Terrain de padel','Salle ou terrain spécialisé','Equipements Ext.'),('2119','Salle ou terrain de badminton','Salle ou terrain spécialisé','Salles de pratiques collectives'),('2123','Terrain de quilles','Salle ou terrain spécialisé','Equipements Ext.'),('2124','Terrain de balle au tambourin','Salle ou terrain spécialisé','Equipements Ext.'),('2125','Salle ou terrain de paintball','Salle ou terrain spécialisé','Equipements Ext.'),('2126','Salle ou terrain de jorkyball','Salle ou terrain spécialisé','Salles de pratiques collectives'),('2127','Terrain de ballon au poing/long paume','Salle ou terrain spécialisé','Equipements Ext.'),('2128','Terrain de soccer','Salle ou terrain spécialisé','Salles de pratiques collectives'),('2201','Salles polyvalentes / des fêtes / non spécialisées','Salle non spécialisée','Salles de pratiques collectives'),('2301','Aire mixte (décollage et atterissage)','Site d\'activités aériennes','Nature'),('2302','Aire de décollage','Site d\'activités aériennes','Nature'),('2303','Aire d\'atterrissage','Site d\'activités aériennes','Nature'),('2304','Piste d’aérodrome / d\'aéroport','Site d\'activités aériennes','Autres équipements'),('2305','Site d\'aérostation','Site d\'activités aériennes','Nature'),('2306','Piste ULM','Site d\'activités aériennes','Nature'),('2307','Site de glisse aérotractée','Site d\'activités aériennes','Nature'),('2401','Site d\'activités aquatiques et nautiques','Site d\'activités aquatiques et nautiques','Nature'),('2402','Baignade aménagée','Site d\'activités aquatiques et nautiques','Nature'),('2403','Circuit de motonautisme','Site d\'activités aquatiques et nautiques','Nature'),('2404','Stade de ski nautique','Site d\'activités aquatiques et nautiques','Nature'),('2405','Stade d’eau vive','Site d\'activités aquatiques et nautiques','Nature'),('2406','Stade d’aviron','Site d\'activités aquatiques et nautiques','Nature'),('2407','Stade de canoë-kayak de vitesse','Site d\'activités aquatiques et nautiques','Nature'),('2408','Stade mixte','Site d\'activités aquatiques et nautiques','Nature'),('2409','Terrain de kayak polo','Site d\'activités aquatiques et nautiques','Nature'),('2410','Point d\'embarquement et de débarquement isolé','Site d\'activités aquatiques et nautiques','Nature'),('2414','Site de plongée','Site d\'activités aquatiques et nautiques','Nature'),('2415','Tank à ramer','Site d\'activités aquatiques et nautiques','Autres équipements'),('2416','Téléski nautique','Site d\'activités aquatiques et nautiques','Nature'),('2417','Port de plaisance','Site d\'activités aquatiques et nautiques','Nature'),('2418','Zone de mouillage','Site d\'activités aquatiques et nautiques','Nature'),('2419','Dispositif de franchissement','Site d\'activités aquatiques et nautiques','Nature'),('2420','Site de pêche','Site d\'activités aquatiques et nautiques','Nature'),('2501','Site de modélisme automobile','Site de modélisme','Autres équipements'),('2502','Site de voile radio commandée','Site de modélisme','Autres équipements'),('2503','Site d\'aéromodélisme','Site de modélisme','Autres équipements'),('2504','Site de modèle réduit (motonautisme)','Site de modélisme','Autres équipements'),('2601','Anneau de Roller','Skate park & vélo Freestyle','Autres équipements'),('2602','Espace de vélo-freestyle','Skate park & vélo Freestyle','Equipements Ext.'),('2603','Skate park','Skate park & vélo Freestyle','Equipements Ext.'),('2701','Structure Artificielle d\'Escalade','Structure Artificielle d\'Escalade','Autres équipements'),('2702','Salle de blocs artificiels d\'escalade','Structure Artificielle d\'Escalade','Autres équipements'),('2801','Terrain mixte','Terrain de grands jeux','Terrains de grands jeux'),('2802','Terrain de football','Terrain de grands jeux','Terrains de grands jeux'),('2803','Terrain de rugby','Terrain de grands jeux','Terrains de grands jeux'),('2804','Terrain de football américain','Terrain de grands jeux','Terrains de grands jeux'),('2805','Terrain de rugby à XIII','Terrain de grands jeux','Terrains de grands jeux'),('2806','Terrain de baseball /softball','Terrain de grands jeux','Terrains de grands jeux'),('2808','Terrain de cricket','Terrain de grands jeux','Terrains de grands jeux'),('2809','Terrain de hockey sur gazon','Terrain de grands jeux','Terrains de grands jeux'),('2901','Terrain de basket-ball','Terrain extérieur de petits jeux collectifs','Equipements Ext.'),('2902','Terrain de beach-volley','Terrain extérieur de petits jeux collectifs','Equipements Ext.'),('2903','Terrain de handball','Terrain extérieur de petits jeux collectifs','Equipements Ext.'),('2904','Terrain de volley-ball','Terrain extérieur de petits jeux collectifs','Equipements Ext.'),('3001','Canyon','Divers équipements Sports de nature','Nature'),('3002','Equipement pour saut à l’élastique','Divers équipements Sports de nature','Nature'),('3003','Parcours Acrobatique en Hauteur/Site d\'accrobranche','Divers équipements Sports de nature','Nature'),('3004','Parcours de chasse / en campagne','Divers équipements Sports de nature','Nature'),('3005','Parcours fixe de course d’orientation','Divers équipements Sports de nature','Nature'),('3006','Piste de pulka / traineau à chiens','Divers équipements Sports de nature','Nature'),('3007','Site d\'escalade en falaise','Divers équipements Sports de nature','Nature'),('3008','Site de spéléologie sportive et/ou éducative','Divers équipements Sports de nature','Nature'),('3009','Site de char à voile','Divers équipements Sports de nature','Nature'),('3010','Via ferrata / Via corda','Divers équipements Sports de nature','Nature'),('3011','Refuge de montagne','Divers équipements Sports de nature','Nature'),('3012','Boucle de randonnée','Divers équipements Sports de nature','Nature'),('3013','Cascade de glace','Divers équipements Sports de nature','Nature'),('3014','Site de blocs d\'escalade','Divers équipements Sports de nature','Nature'),('3015','Espace de pratique spéléologique','Divers équipements Sports de nature','Nature'),('3101','Arènes','Divers équipements','Autres équipements'),('3104','Club de plage','Divers équipements','Autres équipements'),('3106','Salle de spectacle / Zenith','Divers équipements','Autres équipements'),('3107','Hippodrome','Divers équipements','Autres équipements'),('3108','Simulateur de chute libre','Divers équipements','Autres équipements');
/*!40000 ALTER TABLE `equipment_type` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-20 18:44:26
