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
-- Table structure for table `PRUEBA_VDRL`
--

DROP TABLE IF EXISTS `PRUEBA_VDRL`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PRUEBA_VDRL` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `id_expediente` int(20) NOT NULL,
  `fecha_laboratorio` date NOT NULL,
  `resultado` varchar(30) NOT NULL,
  `rea_VDRL` int(20) DEFAULT NULL,
  `nivel_VDRL` int(20) DEFAULT NULL,
  `id_quimico_biologo` bigint(20) NOT NULL,
  `estado` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_PRUEBA_VDRL_QUIMICO` (`id_quimico_biologo`),
  KEY `Index113` (`id_expediente`),
  CONSTRAINT `FK_PRUEBA_VDRL_EXPEDIENTE` FOREIGN KEY (`id_expediente`) REFERENCES `EXPEDIENTE_LABORATORIO` (`id`),
  CONSTRAINT `FK_PRUEBA_VDRL_QUIMICO` FOREIGN KEY (`id_quimico_biologo`) REFERENCES `APP_USER` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PRUEBA_VDRL`
--

LOCK TABLES `PRUEBA_VDRL` WRITE;
/*!40000 ALTER TABLE `PRUEBA_VDRL` DISABLE KEYS */;
INSERT INTO `PRUEBA_VDRL` VALUES (1,8,'0016-09-27','0',NULL,NULL,5,'0'),(2,10,'2016-09-29','0',NULL,NULL,3,'0'),(3,17,'2016-09-29','0',NULL,NULL,5,'0'),(4,51,'2016-10-12','0',NULL,NULL,11,'0'),(5,53,'2016-10-13','0',NULL,NULL,11,'0'),(6,54,'2016-10-13','0',NULL,NULL,11,'0'),(7,57,'2016-10-14','0',NULL,NULL,11,'0'),(8,58,'2016-10-14','0',NULL,NULL,11,'0'),(9,59,'2016-10-15','0',NULL,NULL,11,'0'),(10,60,'2016-10-15','0',NULL,NULL,11,'0'),(11,70,'2016-10-17','0',NULL,NULL,11,'0'),(12,71,'2016-10-17','0',NULL,NULL,11,'0'),(13,72,'2016-10-17','0',NULL,NULL,11,'0'),(14,94,'0016-10-25','0',NULL,NULL,5,'0'),(15,100,'2016-10-26','0',NULL,NULL,11,'0'),(16,103,'2016-10-25','0',NULL,NULL,11,'0'),(17,110,'2016-10-25','0',NULL,NULL,11,'0'),(18,111,'2016-10-25','0',NULL,NULL,11,'0'),(19,112,'2016-10-25','0',NULL,NULL,11,'0'),(20,113,'2016-10-27','0',NULL,NULL,11,'0'),(21,114,'2016-10-27','0',NULL,NULL,11,'0'),(22,109,'2016-10-27','0',NULL,NULL,5,'0'),(23,119,'2016-10-24','0',NULL,NULL,11,'0'),(24,120,'2016-10-24','0',NULL,NULL,11,'0'),(25,121,'2016-10-24','0',NULL,NULL,11,'0'),(26,122,'2016-10-28','0',NULL,NULL,11,'0'),(27,132,'2016-11-03','0',NULL,NULL,11,'0'),(28,135,'2016-11-04','0',NULL,NULL,11,'0'),(29,142,'2016-11-04','0',NULL,NULL,11,'0');
/*!40000 ALTER TABLE `PRUEBA_VDRL` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-05 10:51:28
