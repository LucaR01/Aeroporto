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
-- Dumping data for table `aereo`
--

LOCK TABLES `aereo` WRITE;
/*!40000 ALTER TABLE `aereo` DISABLE KEYS */;
INSERT INTO `aereo` VALUES (1,1,'Boeing 787',12,227930,'wide-body twin engined turbofan',250,137,0,1,NULL),(2,2,'Boeing 777',12,351535,'twinjet',300,160,0,1,NULL);
/*!40000 ALTER TABLE `aereo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `assicurazione`
--

LOCK TABLES `assicurazione` WRITE;
/*!40000 ALTER TABLE `assicurazione` DISABLE KEYS */;
INSERT INTO `assicurazione` VALUES (1,'Assicurazioni srl','08177334210','08:00:00','12:00:00');
/*!40000 ALTER TABLE `assicurazione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `bagaglio`
--

LOCK TABLES `bagaglio` WRITE;
/*!40000 ALTER TABLE `bagaglio` DISABLE KEYS */;
INSERT INTO `bagaglio` VALUES (1,15,'PPPGNI69R07D705H'),(2,13,'BNCNTN77E12F839P'),(3,18,'RNCNCL86T16H620F');
/*!40000 ALTER TABLE `bagaglio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `cargo`
--

LOCK TABLES `cargo` WRITE;
/*!40000 ALTER TABLE `cargo` DISABLE KEYS */;
INSERT INTO `cargo` VALUES (1,25,1,1,'08:00:00','17:00:00');
/*!40000 ALTER TABLE `cargo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `centro_controllo_area`
--

LOCK TABLES `centro_controllo_area` WRITE;
/*!40000 ALTER TABLE `centro_controllo_area` DISABLE KEYS */;
INSERT INTO `centro_controllo_area` VALUES (1,250,'08:00:00','16:00:00');
/*!40000 ALTER TABLE `centro_controllo_area` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `compagnia_aerea`
--

LOCK TABLES `compagnia_aerea` WRITE;
/*!40000 ALTER TABLE `compagnia_aerea` DISABLE KEYS */;
INSERT INTO `compagnia_aerea` VALUES (1,'OTA Airlines','08133357826',250,75,1,'09:00:00','00:00:00');
/*!40000 ALTER TABLE `compagnia_aerea` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `componente_aereo`
--

LOCK TABLES `componente_aereo` WRITE;
/*!40000 ALTER TABLE `componente_aereo` DISABLE KEYS */;
INSERT INTO `componente_aereo` VALUES (1,'Fusoliera',1,1,'Fusoliera',1);
/*!40000 ALTER TABLE `componente_aereo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `gate`
--

LOCK TABLES `gate` WRITE;
/*!40000 ALTER TABLE `gate` DISABLE KEYS */;
INSERT INTO `gate` VALUES (1,1);
/*!40000 ALTER TABLE `gate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ground_support_equipment`
--

LOCK TABLES `ground_support_equipment` WRITE;
/*!40000 ALTER TABLE `ground_support_equipment` DISABLE KEYS */;
INSERT INTO `ground_support_equipment` VALUES (1,35,'tugs & trucks'),(2,40,'dollies');
/*!40000 ALTER TABLE `ground_support_equipment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `hangar`
--

LOCK TABLES `hangar` WRITE;
/*!40000 ALTER TABLE `hangar` DISABLE KEYS */;
INSERT INTO `hangar` VALUES (1,3),(2,5);
/*!40000 ALTER TABLE `hangar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `logistica`
--

LOCK TABLES `logistica` WRITE;
/*!40000 ALTER TABLE `logistica` DISABLE KEYS */;
INSERT INTO `logistica` VALUES (1,'Logistics srl',32,'Kerosene',200,1,'12:00:00','23:00:00');
/*!40000 ALTER TABLE `logistica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `mantenimento`
--

LOCK TABLES `mantenimento` WRITE;
/*!40000 ALTER TABLE `mantenimento` DISABLE KEYS */;
INSERT INTO `mantenimento` VALUES (1,1,1,'00:01:00','00:00:00');
/*!40000 ALTER TABLE `mantenimento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `negozio`
--

LOCK TABLES `negozio` WRITE;
/*!40000 ALTER TABLE `negozio` DISABLE KEYS */;
INSERT INTO `negozio` VALUES (1,'Servizi','09:00:00','20:00:00','Lounge',1);
/*!40000 ALTER TABLE `negozio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `persona`
--

LOCK TABLES `persona` WRITE;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
INSERT INTO `persona` VALUES ('BNCNTN77E12F839P','Antonio','Bianchi',44,'Passeggero',NULL,NULL,NULL,NULL,NULL,1,NULL,1,NULL,1,NULL,NULL,NULL,NULL),('GLLFNC93B61H501D','Francesca','Gialli',28,'Commessa','10:00:00','16:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('NREMRA91P27L483P','Mario','Neri',30,'Controllore','08:00:00','12:00:00',NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL),('PPPGNI69R07D705H','Gino','Peppino',52,'Passeggero',NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL),('RNCNCL86T16H620F','Nicola','Arancioni',34,'Passeggero',NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL),('RSSCHR83R47F205S','Chiara','Rossi',38,'Assicurazione','15:00:00','21:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('VRDFPP87H05L736D','Filippo','Verdi',34,'Dipendente Logistica','14:00:00','19:00:00',NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `pista`
--

LOCK TABLES `pista` WRITE;
/*!40000 ALTER TABLE `pista` DISABLE KEYS */;
INSERT INTO `pista` VALUES (1),(2),(3);
/*!40000 ALTER TABLE `pista` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `radar`
--

LOCK TABLES `radar` WRITE;
/*!40000 ALTER TABLE `radar` DISABLE KEYS */;
INSERT INTO `radar` VALUES (1,96,3,42,'PSR',1);
/*!40000 ALTER TABLE `radar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `servizio_clienti`
--

LOCK TABLES `servizio_clienti` WRITE;
/*!40000 ALTER TABLE `servizio_clienti` DISABLE KEYS */;
INSERT INTO `servizio_clienti` VALUES (1,'08:00:00','12:00:00');
/*!40000 ALTER TABLE `servizio_clienti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `soccorsi`
--

LOCK TABLES `soccorsi` WRITE;
/*!40000 ALTER TABLE `soccorsi` DISABLE KEYS */;
INSERT INTO `soccorsi` VALUES (1,'00:01:00','00:00:00');
/*!40000 ALTER TABLE `soccorsi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `terminal`
--

LOCK TABLES `terminal` WRITE;
/*!40000 ALTER TABLE `terminal` DISABLE KEYS */;
INSERT INTO `terminal` VALUES (1),(2),(3);
/*!40000 ALTER TABLE `terminal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `torre_di_controllo`
--

LOCK TABLES `torre_di_controllo` WRITE;
/*!40000 ALTER TABLE `torre_di_controllo` DISABLE KEYS */;
INSERT INTO `torre_di_controllo` VALUES (1,24,120,'00:01:00','00:00:00');
/*!40000 ALTER TABLE `torre_di_controllo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tratta`
--

LOCK TABLES `tratta` WRITE;
/*!40000 ALTER TABLE `tratta` DISABLE KEYS */;
INSERT INTO `tratta` VALUES (1,'Milano','Roma','Malpensa','Fiumicino','11:00:00','12:12:00');
/*!40000 ALTER TABLE `tratta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `via_di_rullaggio`
--

LOCK TABLES `via_di_rullaggio` WRITE;
/*!40000 ALTER TABLE `via_di_rullaggio` DISABLE KEYS */;
INSERT INTO `via_di_rullaggio` VALUES (1,7),(2,5),(3,3);
/*!40000 ALTER TABLE `via_di_rullaggio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `volo`
--

LOCK TABLES `volo` WRITE;
/*!40000 ALTER TABLE `volo` DISABLE KEYS */;
INSERT INTO `volo` VALUES (1,1,1,'Lufthansa 373');
/*!40000 ALTER TABLE `volo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-12 22:19:08
