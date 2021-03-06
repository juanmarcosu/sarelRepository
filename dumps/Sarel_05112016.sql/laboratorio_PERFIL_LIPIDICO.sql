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
-- Table structure for table `PERFIL_LIPIDICO`
--

DROP TABLE IF EXISTS `PERFIL_LIPIDICO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PERFIL_LIPIDICO` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `id_expediente` int(20) NOT NULL,
  `fecha_laboratorio` date NOT NULL,
  `colesterol_total` double NOT NULL,
  `colesterol_alta_densidad` double NOT NULL,
  `colesterol_baja_densidad` double NOT NULL,
  `colesterol_muy_baja_densidad` double NOT NULL,
  `trigliceridos` double NOT NULL,
  `indice_riesgo` double NOT NULL,
  `resistencia_insulina` double NOT NULL,
  `id_quimico_biologo` bigint(20) NOT NULL,
  `estado` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_PERFIL_LIPIDICO_QUIMICO` (`id_quimico_biologo`),
  KEY `Index110` (`id_expediente`),
  CONSTRAINT `FK_PERFIL_LIPIDICO_EXPEDIENTE` FOREIGN KEY (`id_expediente`) REFERENCES `EXPEDIENTE_LABORATORIO` (`id`),
  CONSTRAINT `FK_PERFIL_LIPIDICO_QUIMICO` FOREIGN KEY (`id_quimico_biologo`) REFERENCES `APP_USER` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PERFIL_LIPIDICO`
--

LOCK TABLES `PERFIL_LIPIDICO` WRITE;
/*!40000 ALTER TABLE `PERFIL_LIPIDICO` DISABLE KEYS */;
INSERT INTO `PERFIL_LIPIDICO` VALUES (1,18,'2016-09-29',280,36.8,202.9,40.3,201.5,7.6,5.5,5,'0'),(2,17,'2016-09-29',150.4,35.7,98.6,16.1,80.5,4.2,2.3,5,'0'),(3,22,'2016-09-30',253,39.7,179.9,33.4,167,6.4,4.2,5,'0'),(4,21,'2016-09-30',177.5,72.3,91,14.2,70.8,2.5,1,5,'0'),(5,24,'2016-09-30',185.3,41.3,112.1,31.9,159.4,4.5,3.9,5,'0'),(6,32,'2016-10-03',190.2,52.3,111.2,26.7,133.5,3.6,2.6,5,'0'),(7,40,'2016-10-03',193.6,75.4,101,17.2,86.2,2.6,1.1,5,'0'),(8,35,'2016-10-03',189.3,53.3,107.2,28.8,144,3.6,2.7,5,'0'),(9,41,'2016-10-04',172.7,68,33.9,70.8,354,2.5,5.2,5,'0'),(10,34,'2016-10-04',144.5,57.3,72.3,14.9,74.3,2.5,1.3,5,'0'),(11,43,'2016-10-05',174.8,54.1,100.8,19.9,99.6,3.2,1.8,3,'0'),(12,79,'0016-10-18',152.1,36.9,83.1,32.1,160.3,4.1,4.3,3,'0'),(13,68,'2016-10-18',157,40,63.9,53.1,265.7,3.9,6.6,3,'0'),(14,67,'2016-10-18',182.7,35.4,67.5,79.8,399.2,5.2,11.3,3,'0'),(15,93,'2016-10-12',196.8,34,137.5,25.3,126.5,5.8,3.7,3,'0'),(16,94,'2016-10-25',204.9,42.6,132.7,29.6,148.1,4.8,3.5,3,'0'),(17,85,'2016-10-25',238.7,62.3,155.3,21.1,105.6,3.8,1.7,3,'0'),(18,97,'2016-10-21',259.6,36.5,178.8,44.3,221.6,7.1,6.1,3,'0'),(19,98,'2016-10-26',199.5,64.2,107.6,27.7,138.3,3.1,2.2,3,'0'),(20,99,'2016-10-26',161.6,38.6,69.9,53.1,265.7,4.2,6.9,3,'0'),(21,107,'2016-10-28',163.9,70.1,57.8,36,179.9,2.3,2.6,5,'0'),(22,108,'2010-10-28',306.1,43.4,227.6,35.1,175.3,7.1,4,5,'0'),(23,109,'2016-10-28',145.3,35.1,65.1,45.1,225.4,4.1,6.4,5,'0'),(24,117,'2016-10-28',181,46.1,85.8,49.1,245.6,3.9,5.3,5,'0'),(25,118,'2016-10-28',175.4,39.1,116.8,19.5,97.6,4.5,2.5,5,'0'),(26,127,'2016-11-04',94.1,29,46.7,18.4,92,3.2,3.1,5,'0'),(27,131,'2016-11-04',158.9,38.2,74.7,46,230.2,4.2,6,4,'0'),(28,139,'2016-11-04',169.4,47.8,81.5,40.1,200.6,3.5,4.2,5,'0'),(29,135,'2016-11-04',251.2,49,69.6,132.6,662.8,5.1,13.5,5,'0'),(30,136,'2016-11-04',150.6,40.3,72.8,37.5,187.4,3.7,4.7,5,'0'),(31,137,'2016-11-04',158.5,33.5,94.2,30.8,153.8,4.7,4.6,5,'0'),(32,138,'2016-11-04',149.2,35.1,36.3,77.8,389,4.3,11.1,5,'0');
/*!40000 ALTER TABLE `PERFIL_LIPIDICO` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-05 10:51:17
