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
-- Table structure for table `EXAMEN_HECES`
--

DROP TABLE IF EXISTS `EXAMEN_HECES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EXAMEN_HECES` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `id_expediente` int(20) NOT NULL,
  `fecha_laboratorio` date NOT NULL,
  `aspecto` varchar(50) NOT NULL,
  `otro_aspecto` varchar(50) DEFAULT NULL,
  `moco` varchar(50) DEFAULT NULL,
  `sangre` varchar(50) DEFAULT NULL,
  `restos_alimenticios` varchar(50) DEFAULT NULL,
  `parasitos` varchar(500) DEFAULT NULL,
  `almidones` varchar(50) DEFAULT NULL,
  `grasas` varchar(50) DEFAULT NULL,
  `fibras_musculares` varchar(50) DEFAULT NULL,
  `jabones` varchar(50) DEFAULT NULL,
  `texto_otros` varchar(50) DEFAULT NULL,
  `otros` varchar(50) DEFAULT NULL,
  `id_quimico_biologo` bigint(20) NOT NULL,
  `estado` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_EXAMEN_HECES_QUIMICO` (`id_quimico_biologo`),
  KEY `Index121` (`id_expediente`),
  CONSTRAINT `FK_EXAMEN_HECES_EXPEDIENTE` FOREIGN KEY (`id_expediente`) REFERENCES `EXPEDIENTE_LABORATORIO` (`id`),
  CONSTRAINT `FK_EXAMEN_HECES_QUIMICO` FOREIGN KEY (`id_quimico_biologo`) REFERENCES `APP_USER` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EXAMEN_HECES`
--

LOCK TABLES `EXAMEN_HECES` WRITE;
/*!40000 ALTER TABLE `EXAMEN_HECES` DISABLE KEYS */;
INSERT INTO `EXAMEN_HECES` VALUES (1,1,'2016-09-14','PASTOSA','','_','_','REGULAR_CANTIDAD','No se encontraron','REGULAR_CANTIDAD','_','_','ESCASA','','_',5,'0'),(2,8,'2016-09-27','PASTOSA','','_','_','ESCASA','No se encontro','ESCASA','_','REGULAR_CANTIDAD','ESCASA','','_',4,'0'),(3,10,'2016-09-29','PASTOSA','','_','_','REGULAR_CANTIDAD','No se encontro','_','_','REGULAR_CANTIDAD','REGULAR_CANTIDAD','','_',4,'1'),(4,12,'2016-09-29','PASTOSA','','_','_','ABUNDANTE','Quistes Endolimax nana escasos','_','_','ESCASA','ESCASA','','_',4,'0'),(5,10,'2016-09-29','PASTOSA','','_','_','REGULAR_CANTIDAD','No se encontro','ESCASA','_','_','ESCASA','','_',4,'1'),(6,13,'2016-09-29','PASTOSA','','_','_','ESCASA','No se encontro','ESCASA','ESCASA','ESCASA','ESCASA','','_',4,'0'),(7,10,'2016-09-29','PASTOSA','','_','_','_','No se enciontro','ESCASA','_','_','ESCASA','','_',3,'0'),(8,19,'2016-09-29','SEMI_DIARREICA','','_','_','ESCASA','No se encontro','ESCASA','REGULAR_CANTIDAD','_','ESCASA','','_',4,'0'),(9,26,'2016-10-01','PASTOSA','','_','_','REGULAR_CANTIDAD','NO SE ENCONTRARON ','ESCASA','ESCASA','ESCASA','ESCASA','levaduras','ABUNDANTE',11,'0'),(10,34,'2016-10-04','PASTOSA','','_','_','ESCASA','Quistes de Iodamoeba butschlii abundantes','ESCASA','_','_','ESCASA','','_',5,'0'),(11,45,'2016-10-05','PASTOSA','','_','_','_','no se encontraron ','ESCASA','_','_','ESCASA','','_',11,'0'),(12,49,'2016-10-06','PASTOSA','','ESCASA','_','_','No se encontró','ESCASA','_','_','ESCASA','','_',5,'0'),(13,51,'2016-10-12','PASTOSA','','ESCASA','_','_','NO SE ENCONTRARON ','ESCASA','_','REGULAR_CANTIDAD','REGULAR_CANTIDAD','','_',11,'0'),(14,53,'2016-10-13','PASTOSA','','ESCASA','_','ESCASA','','REGULAR_CANTIDAD','_','_','ESCASA','','_',11,'0'),(15,54,'2016-10-13','PASTOSA','','_','_','ESCASA','','ESCASA','REGULAR_CANTIDAD','_','ESCASA','','_',11,'0'),(16,57,'2016-10-14','OTRO','Formado','_','_','ESCASA','no se encontraron','ESCASA','ESCASA','ESCASA','ESCASA','','_',11,'0'),(17,58,'2016-10-14','PASTOSA','','_','_','ESCASA','no se encontraron ','ESCASA','ESCASA','_','ESCASA','','_',11,'0'),(18,59,'2016-10-15','SEMI_DIARREICA','','_','_','REGULAR_CANTIDAD','no se encontraron ','ESCASA','ESCASA','ESCASA','ESCASA','','_',11,'0'),(19,60,'2016-10-15','PASTOSA','','_','_','ESCASA','no se encontraron ','ESCASA','_','ESCASA','ESCASA','','_',11,'0'),(20,61,'2016-10-15','PASTOSA','','_','_','ESCASA','no se encontraron','ESCASA','_','ESCASA','ESCASA','','_',11,'0'),(21,62,'0016-10-18','PASTOSA','','_','_','REGULAR_CANTIDAD','No se encontrò','REGULAR_CANTIDAD','_','_','ESCASA','','_',3,'0'),(22,64,'0016-10-18','PASTOSA','','_','_','REGULAR_CANTIDAD','No se encontrò','ESCASA','_','_','REGULAR_CANTIDAD','','_',5,'1'),(23,64,'0016-10-18','PASTOSA','','_','_','REGULAR_CANTIDAD','No se encontro','ESCASA','_','_','REGULAR_CANTIDAD','','_',5,'1'),(24,68,'0016-10-18','PASTOSA','','_','_','REGULAR_CANTIDAD','No se encontro','REGULAR_CANTIDAD','_','_','ESCASA','','_',5,'0'),(25,70,'2016-10-17','PASTOSA','','_','_','REGULAR_CANTIDAD','no se encontraron ','ESCASA','_','_','ESCASA','','_',11,'0'),(26,71,'2016-10-17','PASTOSA','','_','_','ESCASA','no se encontraron','ESCASA','_','_','ESCASA','','_',11,'0'),(27,72,'2016-10-17','PASTOSA','','_','_','_','no se encontraron ','ESCASA','ESCASA','ESCASA','ESCASA','','_',11,'0'),(28,77,'0016-10-19','PASTOSA','','_','_','ESCASA','no se encontro','ESCASA','_','_','ESCASA','','_',5,'0'),(29,78,'0016-10-19','SEMI_DIARREICA','','_','_','ESCASA','No se encontrò','REGULAR_CANTIDAD','_','ESCASA','ESCASA','','_',5,'0'),(30,66,'2016-10-18','PASTOSA','','_','_','REGULAR_CANTIDAD','No se encontro','ESCASA','_','_','_','','_',4,'0'),(31,98,'2016-10-26','PASTOSA','','_','_','REGULAR_CANTIDAD','No se encontrò','REGULAR_CANTIDAD','_','ESCASA','ESCASA','','_',5,'0'),(32,39,'2016-10-26','PASTOSA','','_','_','ABUNDANTE','No se encontrò','REGULAR_CANTIDAD','_','_','REGULAR_CANTIDAD','','_',5,'0'),(33,100,'2016-10-26','PASTOSA','','ESCASA','ESCASA','ESCASA','no se encontraron ','ESCASA','_','ESCASA','ESCASA','eritrocitos ','REGULAR_CANTIDAD',11,'0'),(34,99,'2016-10-26','PASTOSA','','_','_','ESCASA','no se encontraron ','ESCASA','ESCASA','ESCASA','ESCASA','','_',11,'0'),(35,102,'2016-10-25','PASTOSA','','_','_','_','Quistes de Endolimax nana, escasos ','ESCASA','_','ESCASA','ESCASA','','_',11,'0'),(36,103,'2016-10-25','SEMI_DIARREICA','','_','_','_','no se encontraron ','ESCASA','ESCASA','ESCASA','ESCASA','','_',11,'0'),(37,106,'2016-10-27','DIARREICA','','_','_','REGULAR_CANTIDAD','No se encontrò','ABUNDANTE','REGULAR_CANTIDAD','_','REGULAR_CANTIDAD','','_',5,'0'),(38,110,'2016-10-25','PASTOSA','','_','_','ESCASA','no se encontraron ','ESCASA','ESCASA','ESCASA','ESCASA','','_',11,'0'),(39,111,'2016-10-25','PASTOSA','','_','_','ESCASA','no se encontraron','ESCASA','_','ESCASA','ESCASA','','_',11,'0'),(40,112,'2016-10-25','PASTOSA','','_','_','_','no se encontraron ','ESCASA','_','ESCASA','ESCASA','','_',11,'0'),(41,109,'2016-10-27','PASTOSA','','_','_','ESCASA','no se encontraron ','ESCASA','ESCASA','_','ESCASA','','_',3,'0'),(42,114,'2016-10-27','PASTOSA','','_','_','ESCASA','no se encontraron ','ESCASA','_','ESCASA','ESCASA','','_',11,'0'),(43,116,'2016-10-28','SEMI_DIARREICA','','REGULAR_CANTIDAD','_','REGULAR_CANTIDAD','No se encontrò','ESCASA','_','_','ESCASA','Leucocitos','REGULAR_CANTIDAD',5,'0'),(44,119,'2016-10-24','PASTOSA','','_','_','ESCASA','no se encontraron ','ESCASA','_','REGULAR_CANTIDAD','REGULAR_CANTIDAD','','_',11,'0'),(45,120,'2016-10-24','PASTOSA','','_','_','ESCASA','no se encontraron ','ESCASA','_','REGULAR_CANTIDAD','ESCASA','','_',11,'0'),(46,121,'2016-10-24','PASTOSA','','_','_','ESCASA','no se encontraron','ESCASA','_','ESCASA','ESCASA','','_',11,'0'),(47,122,'2016-10-28','PASTOSA','','_','_','_','Quistes de Entamoeba coli, escasos','ESCASA','ESCASA','ESCASA','ESCASA','','_',11,'0'),(48,125,'2016-10-28','PASTOSA','','_','_','REGULAR_CANTIDAD','no se encontraron ','ESCASA','ESCASA','_','ESCASA','','_',11,'0'),(49,126,'2016-10-28','SEMI_DIARREICA','','_','_','_','no se encontraron ','ESCASA','_','_','ESCASA','','_',11,'0'),(50,94,'2016-10-29','PASTOSA','','_','_','ESCASA','no se encontraron ','ESCASA','ESCASA','ESCASA','ESCASA','','_',11,'0'),(51,127,'2016-10-29','PASTOSA','','_','_','_','no se encontraron ','REGULAR_CANTIDAD','ESCASA','ESCASA','ESCASA','','_',11,'0'),(52,130,'2016-11-03','SEMI_DIARREICA','','_','_','REGULAR_CANTIDAD','No se encontro','REGULAR_CANTIDAD','_','_','ESCASA','','_',5,'0'),(53,132,'2016-11-03','PASTOSA','','_','_','ABUNDANTE','no se encontraron','REGULAR_CANTIDAD','_','_','ESCASA','','_',11,'0'),(54,137,'2016-11-04','PASTOSA','','_','_','REGULAR_CANTIDAD','No se encontrò','ESCASA','_','ESCASA','ESCASA','','_',5,'0'),(55,135,'2016-11-04','PASTOSA','','_','_','ESCASA','no se encontraron','REGULAR_CANTIDAD','_','_','ESCASA','','_',11,'0'),(56,142,'2016-11-04','PASTOSA','','_','_','_','no se encontraron','ESCASA','ESCASA','ESCASA','ESCASA','','_',11,'0');
/*!40000 ALTER TABLE `EXAMEN_HECES` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-05 10:51:40
