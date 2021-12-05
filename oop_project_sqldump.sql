-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: oop_project
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `patients`
--

DROP TABLE IF EXISTS `patients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patients` (
  `REG_NO` int NOT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `AGE` int DEFAULT NULL,
  `SEVERITY` varchar(255) DEFAULT NULL,
  `RECOVERED` tinyint(1) DEFAULT NULL,
  `VACCINATED` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`REG_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patients`
--

LOCK TABLES `patients` WRITE;
/*!40000 ALTER TABLE `patients` DISABLE KEYS */;
INSERT INTO `patients` VALUES (0,'Karan',46,'mild',0,0),(1,'Kiran',23,'Low',0,1),(2,'Santosh',68,'severe',0,1),(3,'Reiss',23,'mild',1,1),(4,'Tanuja',48,'mild',0,0),(5,'Nial',27,'Low',1,1),(6,'Srinivas',44,'mild',0,1),(7,'Noah',48,'mild',1,1),(8,'Wilson',51,'severe',0,0),(9,'Harrison',63,'mild',0,0),(10,'Emporio',7,'Low',1,1),(11,'Ursula',68,'Low',0,0),(12,'Pucci',81,'severe',0,1),(13,'John',31,'Low',1,1),(14,'Gertrude',36,'mild',1,1),(15,'Nicholas',37,'severe',0,1),(16,'Xavier',24,'mild',1,1),(17,'Gintoki',22,'mild',1,1),(18,'Ylva',68,'Low',0,0),(19,'Miles',72,'mild',0,0),(20,'Carina',26,'Low',1,1),(21,'Guido',27,'mild',1,0),(22,'Bruno',49,'mild',0,1),(23,'Giorno',19,'Low',1,0),(24,'Rohan',75,'severe',0,1),(25,'Joseph',43,'mild',0,0),(26,'Fugo',63,'severe',0,0),(27,'Mista',3,'Low',1,0),(28,'Pierre',78,'severe',0,1),(29,'Mohammed',46,'mild',1,0),(30,'Avdol',25,'severe',0,1),(31,'Narancia',22,'mild',1,1),(32,'Hector',29,'Low',1,1),(33,'Brian',99,'severe',0,0),(34,'Viktor',12,'mild',1,1),(35,'Jayce',32,'mild',0,0),(36,'Caitlin',53,'mild',0,0),(37,'Michael',41,'Low',0,0),(38,'Quentin',39,'Low',1,1),(39,'Ermes',17,'Low',1,0),(40,'Maxim',76,'severe',0,1),(41,'Costello',12,'mild',1,1),(42,'Jolyne',50,'Low',0,0),(43,'Thomas',65,'mild',0,1),(44,'Pascal',10,'severe',1,1),(45,'Orwell',67,'severe',0,0),(46,'Richard',22,'Low',1,0),(47,'Harry',34,'severe',0,1),(48,'Sanira',23,'Low',1,1),(49,'Eustass',12,'mild',0,1);
/*!40000 ALTER TABLE `patients` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-05 11:31:36
