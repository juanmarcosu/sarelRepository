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
-- Table structure for table `APP_USER`
--

DROP TABLE IF EXISTS `APP_USER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `APP_USER` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sso_id` varchar(30) NOT NULL,
  `password` varchar(100) NOT NULL,
  `first_name` varchar(30) NOT NULL,
  `last_name` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `url_sign` varchar(250) DEFAULT NULL,
  `state` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `sso_id` (`sso_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `APP_USER`
--

LOCK TABLES `APP_USER` WRITE;
/*!40000 ALTER TABLE `APP_USER` DISABLE KEYS */;
INSERT INTO `APP_USER` VALUES (1,'juanmarcos','$2a$10$70rj5fADrPb.VHuNzezwPehvghosh3cUHLWGr1shcpnZHvPEppL0G','juanmarcos','AdminYLaboratorista','juanmarcos@xyz.com','juan.chacon.png','Active'),(3,'martha.campos','$2a$10$Ano6pw0kMLkBp0pZkZ9DPOX7/NiESUu1EoV/cgdOelmC/2j8OHame','Martha Eugenia','Campos Urizar','mecamposu@gmail.com','martha.campos.png','Active'),(4,'astrid.robles','$2a$10$SW4usdwIhUTMESglQGHo9OozK9yUdgocGTW/LLjmb/Unn7wKxerJa',' Astrid Nicté','Robles Giron','anrg30@gmail.com','astrid.robles.png','Active'),(5,'sandra.armas','$2a$10$yTbKR2tOk2t4EfLRvuc28OB7Xu3og0z7jbbjZKhh6goHE.RvvbNT.','María Sandra','Armas Bonilla','armasmarisandra27@gmail.com','sandra.armas.png','Active'),(6,'rita.figueroa','$2a$10$LZKS6q6BGJ37PHH/xMS//uLJzpyrzg0TcBhl98e/zs7QSo.PI5cfi','Rita Ma. de los Angeles','Figueroa Figueroa','figueroa.ritamaria@gmail.com','','Active'),(7,'danilo.morales','$2a$10$Bzu6yqdvJ0xAyKlK9EcCiOtH9TaDpZLPzCCNzpOmg3DvjIO2EePvS','Danilo Oliverio','Morales Andrade','dmorales@usac.edu.gt','','Active'),(8,'ligia.arevalo','$2a$10$2wt42onuCu95fyzI3cdyc.1I6.R/5WjFdSnsWVEB3ooa0luFbkSOW','Ligia Ester','Arevalo Veras','aligiaester@yahoo.com','','Active'),(9,'ingrid.hernandez','$2a$10$SXT5fcX8CaQoc4tIWbbnJeKsa4vA2Lk3sw/4ngjhl7frHwB50jDqW','Ingrid Solei ','Hernández Caracun','tanuc87@hotmail.com','ingrid.hernandez.png','Active'),(11,'ingrid.hernandezc','$2a$10$1Ki5pYYXSoaljGE58dpViOlz0IQS5oqhhcdqR.k2XlOoRjZnE7wvW','Ingrid Solei ','Hernández Caracun','ihernandezqb@hotmail.com','ingrid.hernandez.png','Active'),(12,'carlos.catalán','$2a$10$ROARRMcQSh6yTQu973oOruCQRYFDzYupDLfORzcnpxeigCSgReok2','Carlos Enrique','Catalán Gómez','carlosen@usac.edu.gt','','Active'),(13,'saul.rojas','$2a$10$.HSLfItjjOoWw3QGExyvge7d47RICyJYBNcxyx91Nfa3EhWKOVHsC','Saul Ernesto ','Rojas Castillo','srojas@usac.edu.gt','','Active');
/*!40000 ALTER TABLE `APP_USER` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-05 10:51:46
