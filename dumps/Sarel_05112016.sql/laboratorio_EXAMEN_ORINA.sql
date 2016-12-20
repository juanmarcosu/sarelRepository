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
-- Table structure for table `EXAMEN_ORINA`
--

DROP TABLE IF EXISTS `EXAMEN_ORINA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EXAMEN_ORINA` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `id_expediente` int(20) NOT NULL,
  `fecha_laboratorio` date NOT NULL,
  `color` varchar(50) NOT NULL,
  `otro_color` varchar(50) DEFAULT NULL,
  `aspecto` varchar(50) NOT NULL,
  `ph` double DEFAULT NULL,
  `densidad` double DEFAULT NULL,
  `bioquimico` varchar(250) DEFAULT NULL,
  `leucositos` varchar(50) DEFAULT NULL,
  `eritrositos` varchar(50) DEFAULT NULL,
  `celulas_epiteliales` varchar(50) DEFAULT NULL,
  `moco` varchar(50) DEFAULT NULL,
  `cristales` varchar(250) DEFAULT NULL,
  `texto_cilindros` varchar(50) DEFAULT NULL,
  `cilindros` varchar(50) DEFAULT NULL,
  `texto_bacterias` varchar(50) DEFAULT NULL,
  `bacterias` varchar(50) DEFAULT NULL,
  `texto_otros` varchar(50) DEFAULT NULL,
  `otros` varchar(50) DEFAULT NULL,
  `id_quimico_biologo` bigint(20) NOT NULL,
  `estado` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_EXAMEN_ORINA_QUIMICO` (`id_quimico_biologo`),
  KEY `Index119` (`id_expediente`),
  CONSTRAINT `FK_EXAMEN_ORINA_EXPEDIENTE` FOREIGN KEY (`id_expediente`) REFERENCES `EXPEDIENTE_LABORATORIO` (`id`),
  CONSTRAINT `FK_EXAMEN_ORINA_QUIMICO` FOREIGN KEY (`id_quimico_biologo`) REFERENCES `APP_USER` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EXAMEN_ORINA`
--

LOCK TABLES `EXAMEN_ORINA` WRITE;
/*!40000 ALTER TABLE `EXAMEN_ORINA` DISABLE KEYS */;
INSERT INTO `EXAMEN_ORINA` VALUES (1,8,'2016-09-27','AMARILLO','','LIGERAMENTE_TURBIA',6,1.01,'','eventuales','eventuales','REGULAR_CANTIDAD','ESCASA','Uratos amorfos Regular cantidad','','_','','ESCASA','','_',5,'0'),(2,10,'2016-09-29','AMARILLO','','TURBIA',5,1.03,'Nitritos: Positivo\r\nLeucocitos 70 uL','18','','REGULAR_CANTIDAD','ESCASA','','','_','','ABUNDANTE','','_',3,'0'),(3,19,'2016-09-29','AMARILLO','','LIMPIDO',6,1.025,'','eventuales','','ESCASA','ESCASA','','','_','','ESCASA','','_',5,'0'),(4,21,'2016-09-29','AMARILLO','','LIGERAMENTE_TURBIA',5,1.02,'','Eventuales','0','ESCASA','REGULAR_CANTIDAD','','','_','','ESCASA','','_',4,'0'),(5,12,'2016-09-29','AMARILLO','','TURBIA',5,1.01,'','4','eventuales','REGULAR_CANTIDAD','ESCASA','','','_','','ABUNDANTE','','_',5,'0'),(6,27,'2016-10-03','AMARILLO','','LIGERAMENTE_TURBIA',6,1.02,'','eventuales','0','ESCASA','ESCASA','Uratos amorfos escasos','','_','escasas','_','','_',3,'0'),(7,29,'2016-10-03','AMARILLO','','LIGERAMENTE_TURBIA',6,1.02,'leucocitos, 70/uL \r\nproteínas, 15 mg/dL \r\n','8','0','ESCASA','ESCASA','oxalato de calcio, escasos','','_','','_','','_',11,'0'),(8,30,'2016-09-29','AMARILLO','','TURBIA',5,1.03,'Leucocitos 70 uL, Proteínas 15 mg/dl, Cetonas 5 mg/dl','38','eventiuales','ESCASA','ESCASA','','','_','escasas','_','','_',5,'0'),(9,33,'2016-10-04','AMARILLO','','TURBIA',5,1.03,'Nitritos: Positivo\r\nSangre: ++\r\nLeucocitos 70/uL','61','40','REGULAR_CANTIDAD','ESCASA','','','_','','REGULAR_CANTIDAD','','_',5,'0'),(10,34,'2016-10-04','AMARILLO','','LIGERAMENTE_TURBIA',5,1.02,'','2','0','REGULAR_CANTIDAD','ABUNDANTE','','','_','','ESCASA','','_',3,'0'),(11,35,'2016-10-03','AMARILLO','','LIGERAMENTE_TURBIA',6,1.02,'','eventuales','0','ESCASA','ESCASA','Uratos amorfos escasos','','_','','ESCASA','','_',3,'0'),(12,37,'2016-10-05','AMARILLO','','TURBIA',6,1.01,'Sangre +++\r\nLeucocitos 125/uL','53','Campos llenos','ESCASA','ESCASA','','','_','','ABUNDANTE','','_',5,'0'),(13,49,'2016-10-06','AMBAR','','LIGERAMENTE_TURBIA',6,1.02,'','eventuales','eventuales','REGULAR_CANTIDAD','ABUNDANTE','oxalato de calcio abundante','','_','','ESCASA','','_',3,'0'),(14,51,'2016-10-12','AMARILLO','','LIGERAMENTE_TURBIA',6,1.02,'','1','','ESCASA','ESCASA','','','_','Escasas','ESCASA','','_',11,'0'),(15,53,'2016-10-13','AMARILLO','','LIGERAMENTE_TURBIA',5,1.02,'','','','ESCASA','ESCASA','','','_','escasas','ESCASA','','_',11,'0'),(16,54,'2016-10-13','AMARILLO','','LIGERAMENTE_TURBIA',6,1.025,'','','','ESCASA','ESCASA','','','_','','_','','_',11,'0'),(17,57,'2016-10-14','AMARILLO','','LIGERAMENTE_TURBIA',7,1.015,'','','','ESCASA','ESCASA','','','_','','_','','_',11,'0'),(18,58,'2016-10-14','AMARILLO','','LIGERAMENTE_TURBIA',6,1.025,'','1','','ESCASA','ABUNDANTE','','','_','escasas','ESCASA','','_',11,'0'),(19,59,'2016-10-15','AMARILLO','','LIGERAMENTE_TURBIA',5,1.03,'','eventuales','2','ABUNDANTE','ABUNDANTE','','','_','abundantes','ABUNDANTE','','_',11,'0'),(20,60,'2016-10-15','AMARILLO','','TURBIA',6,1.015,'leucocitos, trazas','eventuales','','ABUNDANTE','REGULAR_CANTIDAD','','','_','Regular cantidad','REGULAR_CANTIDAD','','_',11,'0'),(21,61,'2016-10-15','AMARILLO','','TURBIA',6,1.01,'leucocitos, 500/uL; \r\neritrocitos, 50/uL','12','2','REGULAR_CANTIDAD','REGULAR_CANTIDAD','','','_','escasas','ESCASA','acúmulos leucocitarios','ESCASA',11,'0'),(22,64,'0016-10-18','AMARILLO','','LIMPIDO',6,1.02,'','eventuales','0','ESCASA','ESCASA','','','_','Escasas','_','','_',5,'1'),(23,68,'0016-10-18','AMARILLO','','LIGERAMENTE_TURBIA',6.5,1.015,'','eventuales','1','ESCASA','ABUNDANTE','','','_','Regular cantidad','REGULAR_CANTIDAD','','_',5,'0'),(24,70,'2016-10-17','AMARILLO','','LIGERAMENTE_TURBIA',5,1.03,'','','','ESCASA','ESCASA','','','_','escasas','ESCASA','','_',11,'0'),(25,71,'2016-10-17','AMARILLO','','TURBIA',5,1.03,'','2','eventuales','REGULAR_CANTIDAD','REGULAR_CANTIDAD','oxalato de calcio, regular cantidad','','_','escasas','ABUNDANTE','','_',11,'0'),(26,72,'2016-10-17','AMARILLO','','LIGERAMENTE_TURBIA',6.5,1.015,'eritrocitos 5-10/uL','','','ABUNDANTE','ESCASA','','','_','Regular cantidad','REGULAR_CANTIDAD','','_',11,'0'),(27,66,'2016-10-18','AMARILLO','','LIMPIDO',6,1.02,'','eventuales','0','ESCASA','ESCASA','','','_','Escasas','_','','_',3,'0'),(28,81,'0016-10-19','AMARILLO','','LIGERAMENTE_TURBIA',6,1.01,'leucocitos, trazas','1','','ESCASA','ESCASA','','','_','escasas','ESCASA','','_',11,'0'),(29,83,'0016-10-19','AMARILLO','','LIGERAMENTE_TURBIA',6,1.005,'leucocitos, 125/uL;\r\neritrocitos, 50/uL','5','7','ABUNDANTE','ESCASA','','','_','escasas','ESCASA','','_',11,'0'),(30,81,'0016-10-21','AMARILLO','','LIGERAMENTE_TURBIA',6.5,1.015,'leucocitos, 70/uL\r\n\r\n**Volumen de muestra recibido: menor a 10 mL. Paciente refiere oliguria y ardor al orinar desde el día de ayer 20/10/16.','7','','REGULAR_CANTIDAD','REGULAR_CANTIDAD','','','_','escasas','ESCASA','acúmulos leucocitarios','ESCASA',11,'0'),(31,81,'0016-10-21','AMARILLO','','LIGERAMENTE_TURBIA',6.5,1.015,'leucocitos, 70/uL\r\n\r\n**Volumen de muestra recibido: menor a 10 mL. Paciente refiere oliguria y ardor al orinar desde el día de ayer 20/10/16.','7','','REGULAR_CANTIDAD','REGULAR_CANTIDAD','','','_','escasas','ESCASA','acúmulos leucocitarios','ESCASA',11,'0'),(32,91,'2016-10-24','AMARILLO','','LIGERAMENTE_TURBIA',6,1.03,'Proteinas 15mg/dl\r\nSangre +++','2','15','ESCASA','ESCASA','','','_','escasas','_','','_',4,'0'),(33,98,'2016-10-26','AMARILLO','','LIGERAMENTE_TURBIA',5,1.02,'Sangre ++','eventuales','4','REGULAR_CANTIDAD','ESCASA','','','_','','ESCASA','','_',3,'0'),(34,39,'2016-10-26','AMARILLO','','LIMPIDO',5,1.03,'','eventuales','0','ESCASA','ESCASA','','','_','','ESCASA','','_',5,'0'),(35,100,'2016-10-26','AMARILLO','','LIGERAMENTE_TURBIA',6,1.01,'','','','ESCASA','ESCASA','','','_','','_','','_',11,'0'),(36,99,'2016-10-26','AMARILLO','','TURBIA',7.5,1.01,'leucocitos, trazas','eventuales','eventuales','ABUNDANTE','ABUNDANTE','','','_','abundante','ABUNDANTE','','_',11,'0'),(37,103,'2016-10-25','AMARILLO','','LIGERAMENTE_TURBIA',5,1.02,'leucocitos, trazas','eventuales','','ESCASA','ESCASA','','','_','escasas','ESCASA','','_',11,'0'),(38,104,'2016-10-27','AMARILLO','','TURBIA',6.5,1.015,'Nitritos Positivo\r\nLeucocitos 100/uL\r\nSangre +','38','1','ESCASA','REGULAR_CANTIDAD','','','_','','ABUNDANTE','Leucositos en grupos escasos','_',3,'0'),(39,105,'2016-10-27','AMARILLO','','LIMPIDO',7,1.01,'','eventuales','0','ESCASA','_','','','_','','ESCASA','','_',3,'0'),(40,110,'2016-10-25','AMARILLO','','TURBIA',6,1.03,'proteínas, 15 mg/dL ','4','','ABUNDANTE','ABUNDANTE','','','_','abundantes','ABUNDANTE','','_',11,'0'),(41,111,'2016-10-25','AMARILLO','','LIGERAMENTE_TURBIA',5,1.025,'','eventuales','','ESCASA','REGULAR_CANTIDAD','','','_','escasas','ESCASA','','_',11,'0'),(42,112,'2016-10-25','AMARILLO','','LIGERAMENTE_TURBIA',7,1.015,'leucocitos, trazas','eventuales','eventuales','REGULAR_CANTIDAD','ESCASA','fosfatos amorfos, escasos','','_','escasas','ESCASA','','_',11,'0'),(43,109,'2016-10-27','AMARILLO','','TURBIA',5,1.025,'cetonas, 40 mg/dL;\r\nglucosa, 500 mg/dL; \r\nleucocitos, 70/uL ','30','3','ABUNDANTE','ABUNDANTE','','','_','abundante','ABUNDANTE','','_',3,'0'),(44,114,'2016-10-27','AMARILLO','','LIGERAMENTE_TURBIA',6.5,1.01,'','','','ESCASA','ESCASA','','','_','escasas','ESCASA','','_',11,'0'),(45,119,'2016-10-24','AMARILLO','','LIGERAMENTE_TURBIA',6,1.025,'leucocitos, 70/uL','3','eventuales','ESCASA','ESCASA','','','_','escasas','ESCASA','','_',11,'0'),(46,120,'2016-10-24','AMARILLO','','TURBIA',6,1.02,'leucocitos, trazas','2','','ESCASA','ESCASA','','','_','escasas','ESCASA','','_',11,'0'),(47,121,'2016-10-24','AMARILLO','','LIGERAMENTE_TURBIA',6,1.025,'','','','ESCASA','ESCASA','oxalato de calcio, escasos','','_','escasas','ESCASA','','_',11,'0'),(48,122,'2016-10-28','AMARILLO','','TURBIA',6.5,1.005,'leucocitos, trazas','eventuales','','REGULAR_CANTIDAD','ESCASA','','','_','','REGULAR_CANTIDAD','','_',11,'0'),(49,125,'2016-10-28','AMARILLO','','TURBIA',7.5,1.01,'leucocitos, trazas ','3','1','ABUNDANTE','ABUNDANTE','fosfatos amorfos, abundantes','','_','','ESCASA','','_',11,'0'),(50,94,'2016-10-29','AMARILLO','','TURBIA',5,1.03,'','eventuales','1','ABUNDANTE','_','','','_','','REGULAR_CANTIDAD','','_',11,'0'),(51,124,'2016-10-29','AMARILLO','','LIGERAMENTE_TURBIA',6.5,1.015,'leucocitos, trazas','','','ABUNDANTE','ESCASA','','','_','','ESCASA','','_',11,'0'),(52,127,'2016-10-29','AMARILLO','','LIGERAMENTE_TURBIA',6.5,1.015,'leucocitos, trazas','1','eventuales','REGULAR_CANTIDAD','ESCASA','','','_','','ABUNDANTE','','_',11,'0'),(53,61,'2016-10-29','CAFE','','TURBIA',6,1.025,'proteínas, 15 mg/dL; \r\nleucocitos, 125/uL; \r\nhemoglobina, abundante','campos llenos','10','ABUNDANTE','REGULAR_CANTIDAD','','','_','','ABUNDANTE','epitelio de transición ','ESCASA',11,'0'),(54,132,'2016-11-03','AMARILLO','','LIGERAMENTE_TURBIA',6.5,1.01,'leucocitos, trazas','','','ESCASA','_','','','_','','ESCASA','','_',11,'0'),(55,134,'2016-11-03','AMARILLO','','LIGERAMENTE_TURBIA',6,1.02,'eritrocitos, 50/uL;\r\nleucocitos, trazas','4','52','REGULAR_CANTIDAD','ABUNDANTE','','','_','','ESCASA','','_',11,'0'),(56,137,'2016-11-04','AMARILLO','','LIGERAMENTE_TURBIA',6,1.025,'','eventuales','0','ESCASA','_','Uratos Amorfos  regular cantidad','','_','','ESCASA','','_',5,'0'),(57,140,'2016-11-04','AMARILLO','','TURBIA',6,1.03,'cetonas, 15 mg/dL;\r\nproteínas, 100 mg/dL;\r\nhemoglobina, abundante','4','28','ABUNDANTE','ABUNDANTE','','hialino-granulosos','ESCASA','regular cantidad','REGULAR_CANTIDAD','','_',11,'0'),(58,135,'2016-11-04','AMARILLO','','LIGERAMENTE_TURBIA',6,1.03,'','1','','ESCASA','ABUNDANTE','','','_','escasas','ESCASA','','_',11,'0'),(59,142,'2016-11-04','AMARILLO','','LIMPIDO',6.5,1.005,'leucocitos, trazas; \r\neritrocitos, 5-10/uL','0','0','ESCASA','ESCASA','','','_','','_','','_',11,'0'),(60,115,'2016-10-24','AMARILLO','','TURBIA',6,1.03,'nitritos positivo','2','0','REGULAR_CANTIDAD','REGULAR_CANTIDAD','oxalato de calcio, abundantes','','_','abundantes','ABUNDANTE','','_',11,'0');
/*!40000 ALTER TABLE `EXAMEN_ORINA` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-05 10:51:26
