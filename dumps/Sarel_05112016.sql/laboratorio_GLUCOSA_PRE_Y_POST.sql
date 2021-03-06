CREATE DATABASE  IF NOT EXISTS `laboratorio` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `laboratorio`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: 10.50.50.54    Database: laboratorio
-- ------------------------------------------------------
-- Server version	5.5.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `GLUCOSA_PRE_Y_POST`
--

DROP TABLE IF EXISTS `GLUCOSA_PRE_Y_POST`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `GLUCOSA_PRE_Y_POST` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `id_expediente` int(20) NOT NULL,
  `fecha_laboratorio` date NOT NULL,
  `pre_prandial` double DEFAULT NULL,
  `post_prandial` double DEFAULT NULL,
  `id_quimico_biologo` bigint(20) NOT NULL,
  `estado` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_GLUCOSA_PRE_Y_POST_QUIMICO` (`id_quimico_biologo`),
  KEY `Index117` (`id_expediente`),
  CONSTRAINT `FK_GLUCOSA_PRE_Y_POST_EXPEDIENTE` FOREIGN KEY (`id_expediente`) REFERENCES `EXPEDIENTE_LABORATORIO` (`id`),
  CONSTRAINT `FK_GLUCOSA_PRE_Y_POST_QUIMICO` FOREIGN KEY (`id_quimico_biologo`) REFERENCES `APP_USER` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `GLUCOSA_PRE_Y_POST`
--

LOCK TABLES `GLUCOSA_PRE_Y_POST` WRITE;
/*!40000 ALTER TABLE `GLUCOSA_PRE_Y_POST` DISABLE KEYS */;
INSERT INTO `GLUCOSA_PRE_Y_POST` VALUES (1,18,'2016-09-29',100.2,60,4,'0'),(2,19,'2016-09-29',90.6,NULL,4,'0'),(3,17,'2016-09-29',82.9,103.3,4,'0'),(4,22,'2016-09-30',68.3,NULL,4,'0'),(5,21,'2016-09-30',78.7,NULL,3,'0'),(6,24,'2016-09-30',77.1,NULL,3,'0'),(7,40,'2016-10-03',81,NULL,3,'0'),(8,35,'2016-10-03',77.9,75.6,3,'0'),(9,43,'2016-10-05',75.9,88.3,3,'0'),(10,42,'2016-10-05',461,NULL,5,'0'),(11,44,'2016-10-05',NULL,140,5,'0'),(12,46,'2016-10-05',NULL,142,11,'0'),(13,63,'0016-10-18',NULL,138,5,'0'),(14,79,'0016-10-18',81.8,NULL,3,'0'),(15,64,'0016-10-18',82.4,NULL,3,'1'),(16,66,'2016-10-18',82.4,NULL,3,'0'),(17,68,'2016-10-18',89.1,85.3,3,'0'),(18,67,'2016-10-18',96.5,NULL,3,'0'),(19,69,'2016-10-18',97.8,107.5,3,'0'),(20,93,'2016-10-12',82.3,NULL,4,'0'),(21,94,'2016-10-25',90,78.1,3,'0'),(22,97,'2016-10-21',89.5,79.9,3,'0'),(23,98,'2016-10-26',75.1,73.8,3,'0'),(24,99,'2016-10-26',81.4,NULL,3,'0'),(25,108,'2016-10-28',88.7,109.3,5,'0'),(26,107,'2016-10-28',77.5,NULL,5,'0'),(27,109,'2016-10-28',314.1,264,5,'0'),(28,117,'2016-10-28',79.3,91.3,5,'0'),(29,118,'2016-10-28',87.7,92.8,5,'0'),(30,127,'2016-11-02',102.7,72.8,3,'0'),(31,131,'2016-11-03',87.3,NULL,3,'0'),(32,135,'2016-11-04',88.6,89.7,3,'0'),(33,136,'2016-11-04',75.1,97.8,3,'0'),(34,137,'2016-11-04',84.6,89.5,3,'0'),(35,138,'2016-11-04',92.5,98.5,3,'0');
/*!40000 ALTER TABLE `GLUCOSA_PRE_Y_POST` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-05 10:51:23
