-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: aeroporto
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `aereo`
--

DROP TABLE IF EXISTS `aereo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `aereo` (
  `CodAereo` int NOT NULL,
  `CodPista` int DEFAULT NULL,
  `Nome` char(50) NOT NULL,
  `Num_Equipaggio` int NOT NULL,
  `Peso` int NOT NULL,
  `Tipologia` varchar(50) NOT NULL,
  `Num_Passeggeri` int DEFAULT NULL,
  `Num_Merci` int DEFAULT NULL,
  `Commerciale` tinyint DEFAULT '0',
  `CodHangar` int DEFAULT NULL,
  `CodVia` int DEFAULT NULL,
  PRIMARY KEY (`CodAereo`),
  UNIQUE KEY `FKAVVIARSI_ID` (`CodPista`),
  KEY `FKRISIEDERE` (`CodHangar`),
  KEY `FKTRANSITARE` (`CodVia`),
  CONSTRAINT `FKAVVIARSI_FK` FOREIGN KEY (`CodPista`) REFERENCES `pista` (`CodPista`),
  CONSTRAINT `FKRISIEDERE` FOREIGN KEY (`CodHangar`) REFERENCES `hangar` (`CodHangar`),
  CONSTRAINT `FKTRANSITARE` FOREIGN KEY (`CodVia`) REFERENCES `via_di_rullaggio` (`CodVia`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `assicurazione`
--

DROP TABLE IF EXISTS `assicurazione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assicurazione` (
  `CodAssicurazione` int NOT NULL,
  `Nome` char(50) NOT NULL,
  `Partita_IVA` varchar(11) NOT NULL,
  `Ora_inizio` time DEFAULT NULL,
  `Ora_fine` time DEFAULT NULL,
  PRIMARY KEY (`CodAssicurazione`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `bagaglio`
--

DROP TABLE IF EXISTS `bagaglio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bagaglio` (
  `CodBagaglio` int NOT NULL AUTO_INCREMENT,
  `Peso` int NOT NULL,
  `CodiceFiscale` varchar(16) NOT NULL,
  PRIMARY KEY (`CodBagaglio`),
  KEY `FKAPPARTENERE` (`CodiceFiscale`),
  CONSTRAINT `FKAPPARTENERE` FOREIGN KEY (`CodiceFiscale`) REFERENCES `persona` (`CodiceFiscale`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cargo`
--

DROP TABLE IF EXISTS `cargo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cargo` (
  `CodCargo` int NOT NULL AUTO_INCREMENT,
  `Num_dipendenti` int NOT NULL,
  `CodAereo` int DEFAULT NULL,
  `CodLogistica` int DEFAULT NULL,
  `Ora_inizio` time DEFAULT NULL,
  `Ora_fine` time DEFAULT NULL,
  PRIMARY KEY (`CodCargo`),
  KEY `FKCARICARE` (`CodAereo`),
  KEY `FKFORNIRE` (`CodLogistica`),
  CONSTRAINT `FKCARICARE` FOREIGN KEY (`CodAereo`) REFERENCES `aereo` (`CodAereo`),
  CONSTRAINT `FKFORNIRE` FOREIGN KEY (`CodLogistica`) REFERENCES `logistica` (`CodLogistica`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `centro_controllo_aerea`
--

DROP TABLE IF EXISTS `centro_controllo_aerea`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `centro_controllo_aerea` (
  `CodCentro` int NOT NULL,
  `Num_Personale` int NOT NULL,
  `Orario_inizio` time NOT NULL,
  `Orario_fine` time NOT NULL,
  PRIMARY KEY (`CodCentro`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `compagnia_aerea`
--

DROP TABLE IF EXISTS `compagnia_aerea`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `compagnia_aerea` (
  `CodCompagnia` int NOT NULL,
  `Nome` char(50) NOT NULL,
  `Partita_Iva` varchar(11) NOT NULL,
  `Num_Personale` int NOT NULL,
  `Num_Aerei` int NOT NULL,
  `CodAssicurazione` int NOT NULL,
  `Ora_inizio` time DEFAULT NULL,
  `Ora_fine` time DEFAULT NULL,
  PRIMARY KEY (`CodCompagnia`),
  KEY `FKASSICURA` (`CodAssicurazione`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `componente_aereo`
--

DROP TABLE IF EXISTS `componente_aereo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `componente_aereo` (
  `CodComponente` int NOT NULL,
  `Nome` char(50) NOT NULL,
  `Quantità` int NOT NULL,
  `Funzionante` tinyint DEFAULT '1',
  `Tipologia` varchar(50) NOT NULL,
  `CodAereo` int NOT NULL,
  UNIQUE KEY `IDCOMPONENTIAEREO` (`CodComponente`),
  KEY `FKCOMPORRE` (`CodAereo`),
  CONSTRAINT `FKCOMPORRE` FOREIGN KEY (`CodAereo`) REFERENCES `aereo` (`CodAereo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `gate`
--

DROP TABLE IF EXISTS `gate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gate` (
  `CodGate` int NOT NULL AUTO_INCREMENT,
  `CodTerminal` int NOT NULL,
  PRIMARY KEY (`CodGate`),
  KEY `FKCOSTITUIRE` (`CodTerminal`),
  CONSTRAINT `FKCOSTITUIRE` FOREIGN KEY (`CodTerminal`) REFERENCES `terminal` (`CodTerminal`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ground_support_equipment`
--

DROP TABLE IF EXISTS `ground_support_equipment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ground_support_equipment` (
  `CodMacchinario` int NOT NULL,
  `Quantità` int NOT NULL,
  `Tipologia` varchar(50) NOT NULL,
  PRIMARY KEY (`CodMacchinario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `hangar`
--

DROP TABLE IF EXISTS `hangar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hangar` (
  `CodHangar` int NOT NULL,
  `Num_aerei` int NOT NULL,
  PRIMARY KEY (`CodHangar`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `logistica`
--

DROP TABLE IF EXISTS `logistica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `logistica` (
  `CodLogistica` int NOT NULL,
  `Nome` char(50) NOT NULL,
  `Num_Personale` int NOT NULL,
  `Materiali` varchar(50) NOT NULL,
  `Quantità` int NOT NULL,
  `CodCompagnia` int DEFAULT NULL,
  `Ora_inizio` time DEFAULT NULL,
  `Ora_fine` time DEFAULT NULL,
  PRIMARY KEY (`CodLogistica`),
  KEY `FKOTTENERE` (`CodCompagnia`),
  CONSTRAINT `FKOTTENERE` FOREIGN KEY (`CodCompagnia`) REFERENCES `compagnia_aerea` (`CodCompagnia`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mantenimento`
--

DROP TABLE IF EXISTS `mantenimento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mantenimento` (
  `CodMantenimento` int NOT NULL,
  `CodAereo` int DEFAULT NULL,
  `CodMacchinario` int DEFAULT NULL,
  `Ora_inizio` time DEFAULT NULL,
  `Ora_fine` time DEFAULT NULL,
  PRIMARY KEY (`CodMantenimento`),
  KEY `FKPRESERVARE` (`CodAereo`),
  KEY `FKESEGUIRE` (`CodMacchinario`),
  CONSTRAINT `FKESEGUIRE` FOREIGN KEY (`CodMacchinario`) REFERENCES `ground_support_equipment` (`CodMacchinario`),
  CONSTRAINT `FKPRESERVARE` FOREIGN KEY (`CodAereo`) REFERENCES `aereo` (`CodAereo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `negozio`
--

DROP TABLE IF EXISTS `negozio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `negozio` (
  `CodNegozio` int NOT NULL,
  `Prodotti` varchar(50) NOT NULL,
  `Orario_inizio` time NOT NULL,
  `Orario_fine` time NOT NULL,
  `Tipologia` varchar(50) NOT NULL,
  `CodCompagnia` int DEFAULT NULL,
  PRIMARY KEY (`CodNegozio`),
  KEY `FKPOSSEDERE` (`CodCompagnia`),
  CONSTRAINT `FKPOSSEDERE` FOREIGN KEY (`CodCompagnia`) REFERENCES `compagnia_aerea` (`CodCompagnia`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `persona`
--

DROP TABLE IF EXISTS `persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `persona` (
  `CodiceFiscale` varchar(16) NOT NULL,
  `Nome` char(50) NOT NULL,
  `Cognome` char(50) NOT NULL,
  `Età` int NOT NULL,
  `Ruolo` varchar(50) NOT NULL,
  `Ora_inizio` time DEFAULT NULL,
  `Ora_fine` time DEFAULT NULL,
  `CodCentro` int DEFAULT NULL,
  `CodLogistica` int DEFAULT NULL,
  `CodMantenimento` int DEFAULT NULL,
  `CodNegozio` int DEFAULT NULL,
  `CodServizio` int DEFAULT NULL,
  `CodTerminal` int DEFAULT NULL,
  `CodTorre` int DEFAULT NULL,
  `CodVolo` int DEFAULT NULL,
  `CodCompagnia` int DEFAULT NULL,
  `CodAereo` int DEFAULT NULL,
  `CodRadar` int DEFAULT NULL,
  `CodSoccorso` int DEFAULT NULL,
  PRIMARY KEY (`CodiceFiscale`),
  KEY `FKRIFORNIRE` (`CodLogistica`),
  KEY `FKVISITARE` (`CodNegozio`),
  KEY `FKSERVIRE` (`CodServizio`),
  KEY `FKRECARSI` (`CodTerminal`),
  KEY `FKCOMUNICAZIONE` (`CodTorre`),
  KEY `FKTUTELARE` (`CodCompagnia`),
  KEY `FKINFORMARE` (`CodRadar`),
  KEY `FKCONTATTARE` (`CodSoccorso`),
  KEY `FKADEMPIERE` (`CodMantenimento`),
  KEY `FKINTERVENIRE` (`CodCentro`),
  KEY `FKMANTENERE` (`CodAereo`),
  KEY `FKPRENDERE` (`CodVolo`),
  CONSTRAINT `FKADEMPIERE` FOREIGN KEY (`CodMantenimento`) REFERENCES `mantenimento` (`CodMantenimento`),
  CONSTRAINT `FKCOMUNICAZIONE` FOREIGN KEY (`CodTorre`) REFERENCES `torre_di_controllo` (`CodTorre`),
  CONSTRAINT `FKCONTATTARE` FOREIGN KEY (`CodSoccorso`) REFERENCES `soccorsi` (`CodSoccorso`),
  CONSTRAINT `FKINFORMARE` FOREIGN KEY (`CodRadar`) REFERENCES `radar` (`CodRadar`),
  CONSTRAINT `FKINTERVENIRE` FOREIGN KEY (`CodCentro`) REFERENCES `centro_controllo_aerea` (`CodCentro`),
  CONSTRAINT `FKMANTENERE` FOREIGN KEY (`CodAereo`) REFERENCES `aereo` (`CodAereo`),
  CONSTRAINT `FKPRENDERE` FOREIGN KEY (`CodVolo`) REFERENCES `volo` (`CodVolo`),
  CONSTRAINT `FKRECARSI` FOREIGN KEY (`CodTerminal`) REFERENCES `terminal` (`CodTerminal`),
  CONSTRAINT `FKRIFORNIRE` FOREIGN KEY (`CodLogistica`) REFERENCES `logistica` (`CodLogistica`),
  CONSTRAINT `FKSERVIRE` FOREIGN KEY (`CodServizio`) REFERENCES `servizio_clienti` (`CodServizio`),
  CONSTRAINT `FKTUTELARE` FOREIGN KEY (`CodCompagnia`) REFERENCES `compagnia_aerea` (`CodCompagnia`),
  CONSTRAINT `FKVISITARE` FOREIGN KEY (`CodNegozio`) REFERENCES `negozio` (`CodNegozio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `pista`
--

DROP TABLE IF EXISTS `pista`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pista` (
  `CodPista` int NOT NULL,
  PRIMARY KEY (`CodPista`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `radar`
--

DROP TABLE IF EXISTS `radar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `radar` (
  `CodRadar` int NOT NULL,
  `Raggio` int NOT NULL,
  `Frequenza` int NOT NULL,
  `Altitudine` int NOT NULL,
  `Tipologia` varchar(50) NOT NULL,
  `CodAereo` int DEFAULT NULL,
  PRIMARY KEY (`CodRadar`),
  KEY `FKRILEVAZIONE` (`CodAereo`),
  CONSTRAINT `FKRILEVAZIONE` FOREIGN KEY (`CodAereo`) REFERENCES `aereo` (`CodAereo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `servizio_clienti`
--

DROP TABLE IF EXISTS `servizio_clienti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `servizio_clienti` (
  `CodServizio` int NOT NULL,
  `Orario_inizio` time NOT NULL,
  `Orario_fine` time NOT NULL,
  PRIMARY KEY (`CodServizio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `soccorsi`
--

DROP TABLE IF EXISTS `soccorsi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `soccorsi` (
  `CodSoccorso` int NOT NULL,
  `Orario_inizio` time NOT NULL,
  `Orario_fine` time NOT NULL,
  PRIMARY KEY (`CodSoccorso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `terminal`
--

DROP TABLE IF EXISTS `terminal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `terminal` (
  `CodTerminal` int NOT NULL,
  PRIMARY KEY (`CodTerminal`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `torre_di_controllo`
--

DROP TABLE IF EXISTS `torre_di_controllo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `torre_di_controllo` (
  `CodTorre` int NOT NULL,
  `Num_dipendenti` int NOT NULL,
  `Num_Aerei_in_comunicazione` int NOT NULL,
  `Orario_inizio` time NOT NULL,
  `Orario_fine` time NOT NULL,
  PRIMARY KEY (`CodTorre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tratta`
--

DROP TABLE IF EXISTS `tratta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tratta` (
  `CodTratta` int NOT NULL,
  `Città_partenza` varchar(50) NOT NULL,
  `Città_destinazione` varchar(50) NOT NULL,
  `Aeroporto_partenza` varchar(50) NOT NULL,
  `Aeroporto_destinazione` varchar(50) NOT NULL,
  `Ora_partenza` time NOT NULL,
  `Ora_fine` time NOT NULL,
  PRIMARY KEY (`CodTratta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `via_di_rullaggio`
--

DROP TABLE IF EXISTS `via_di_rullaggio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `via_di_rullaggio` (
  `CodVia` int NOT NULL,
  `Num_Aerei` int NOT NULL,
  PRIMARY KEY (`CodVia`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `volo`
--

DROP TABLE IF EXISTS `volo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `volo` (
  `CodVolo` int NOT NULL,
  `CodTratta` int NOT NULL,
  `CodAereo` int NOT NULL,
  `Nome` varchar(50) NOT NULL,
  PRIMARY KEY (`CodVolo`),
  UNIQUE KEY `FKDETENERE_ID` (`CodAereo`),
  UNIQUE KEY `CodTratta_UNIQUE` (`CodTratta`),
  CONSTRAINT `FKDETENERE_FK` FOREIGN KEY (`CodAereo`) REFERENCES `aereo` (`CodAereo`),
  CONSTRAINT `FKSERVIZIO_FK` FOREIGN KEY (`CodTratta`) REFERENCES `tratta` (`CodTratta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-04 22:09:36
