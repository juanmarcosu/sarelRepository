CREATE DATABASE  IF NOT EXISTS `laboratorio` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `laboratorio`;
-- MySQL dump 10.13  Distrib 5.5.22, for Linux (i686)
--
-- Host: 127.0.0.1    Database: laboratorio
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PERFIL_LIPIDICO`
--

LOCK TABLES `PERFIL_LIPIDICO` WRITE;
/*!40000 ALTER TABLE `PERFIL_LIPIDICO` DISABLE KEYS */;
/*!40000 ALTER TABLE `PERFIL_LIPIDICO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `HEMATOLOGIA_COMPLETA`
--

DROP TABLE IF EXISTS `HEMATOLOGIA_COMPLETA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `HEMATOLOGIA_COMPLETA` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `id_expediente` int(20) NOT NULL,
  `fecha_laboratorio` date NOT NULL,
  `rbc` double NOT NULL,
  `hct` double NOT NULL,
  `mcv` double NOT NULL,
  `hgb` double NOT NULL,
  `mch` double NOT NULL,
  `mchc` double NOT NULL,
  `plt` double NOT NULL,
  `wbc` double NOT NULL,
  `lym` double NOT NULL,
  `gran` double NOT NULL,
  `mid` double NOT NULL,
  `vse` int(20) DEFAULT NULL,
  `id_quimico_biologo` bigint(20) NOT NULL,
  `estado` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_HEMATOLOGIA_COMPLETA_QUIMICO` (`id_quimico_biologo`),
  KEY `Index112` (`id_expediente`),
  CONSTRAINT `FK_HEMATOLOGIA_COMPLETA_EXPEDIENTE` FOREIGN KEY (`id_expediente`) REFERENCES `EXPEDIENTE_LABORATORIO` (`id`),
  CONSTRAINT `FK_HEMATOLOGIA_COMPLETA_QUIMICO` FOREIGN KEY (`id_quimico_biologo`) REFERENCES `APP_USER` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `HEMATOLOGIA_COMPLETA`
--

LOCK TABLES `HEMATOLOGIA_COMPLETA` WRITE;
/*!40000 ALTER TABLE `HEMATOLOGIA_COMPLETA` DISABLE KEYS */;
INSERT INTO `HEMATOLOGIA_COMPLETA` VALUES (1,1,'2016-09-14',4.5,36.7,81.5,12.4,27.5,33.8,190,5,44,47,9,27,5,'0'),(2,8,'2016-09-27',4.25,38.5,90.6,13.1,30.9,34.1,301,6.3,35.2,57.8,7,9,3,'0');
/*!40000 ALTER TABLE `HEMATOLOGIA_COMPLETA` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACIDO_URICO`
--

DROP TABLE IF EXISTS `ACIDO_URICO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACIDO_URICO` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `id_expediente` int(20) NOT NULL,
  `fecha_laboratorio` date NOT NULL,
  `nivel_acido_urico` double NOT NULL,
  `id_quimico_biologo` bigint(20) NOT NULL,
  `estado` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ACIDO_URICO_QUIMICO` (`id_quimico_biologo`),
  KEY `Index112` (`id_expediente`),
  CONSTRAINT `FK_ACIDO_URICO_EXPEDIENTE` FOREIGN KEY (`id_expediente`) REFERENCES `EXPEDIENTE_LABORATORIO` (`id`),
  CONSTRAINT `FK_ACIDO_URICO_QUIMICO` FOREIGN KEY (`id_quimico_biologo`) REFERENCES `APP_USER` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACIDO_URICO`
--

LOCK TABLES `ACIDO_URICO` WRITE;
/*!40000 ALTER TABLE `ACIDO_URICO` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACIDO_URICO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USER_PROFILE`
--

DROP TABLE IF EXISTS `USER_PROFILE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USER_PROFILE` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `type` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USER_PROFILE`
--

LOCK TABLES `USER_PROFILE` WRITE;
/*!40000 ALTER TABLE `USER_PROFILE` DISABLE KEYS */;
INSERT INTO `USER_PROFILE` VALUES (3,'ADMINISTRADOR'),(1,'CONSULTOR'),(2,'LABORATORISTA');
/*!40000 ALTER TABLE `USER_PROFILE` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `GLUCOSA_PRE_Y_POST`
--

LOCK TABLES `GLUCOSA_PRE_Y_POST` WRITE;
/*!40000 ALTER TABLE `GLUCOSA_PRE_Y_POST` DISABLE KEYS */;
/*!40000 ALTER TABLE `GLUCOSA_PRE_Y_POST` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EXAMEN_ORINA`
--

LOCK TABLES `EXAMEN_ORINA` WRITE;
/*!40000 ALTER TABLE `EXAMEN_ORINA` DISABLE KEYS */;
INSERT INTO `EXAMEN_ORINA` VALUES (1,8,'2016-09-27','AMARILLO','','LIGERAMENTE_TURBIA',6,1.01,'','eventuales','eventuales','REGULAR_CANTIDAD','ESCASA','Uratos amorfos Regular cantidad','','_','','ESCASA','','_',5,'0');
/*!40000 ALTER TABLE `EXAMEN_ORINA` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PRUEBA_VDRL`
--

LOCK TABLES `PRUEBA_VDRL` WRITE;
/*!40000 ALTER TABLE `PRUEBA_VDRL` DISABLE KEYS */;
INSERT INTO `PRUEBA_VDRL` VALUES (1,8,'0016-09-27','0',NULL,NULL,5,'0');
/*!40000 ALTER TABLE `PRUEBA_VDRL` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PRUEBAS_HEMATOLOGICAS`
--

DROP TABLE IF EXISTS `PRUEBAS_HEMATOLOGICAS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PRUEBAS_HEMATOLOGICAS` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `id_expediente` int(20) NOT NULL,
  `fecha_laboratorio` date NOT NULL,
  `velocidad_sedimentacion` int(20) DEFAULT NULL,
  `hematocrito` int(20) DEFAULT NULL,
  `id_quimico_biologo` bigint(20) NOT NULL,
  `estado` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_PRUEBAS_HEMATOLOGICAS_QUIMICO` (`id_quimico_biologo`),
  KEY `Index120` (`id_expediente`),
  CONSTRAINT `FK_PRUEBAS_HEMATOLOGICAS_EXPEDIENTE` FOREIGN KEY (`id_expediente`) REFERENCES `EXPEDIENTE_LABORATORIO` (`id`),
  CONSTRAINT `FK_PRUEBAS_HEMATOLOGICAS_QUIMICO` FOREIGN KEY (`id_quimico_biologo`) REFERENCES `APP_USER` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PRUEBAS_HEMATOLOGICAS`
--

LOCK TABLES `PRUEBAS_HEMATOLOGICAS` WRITE;
/*!40000 ALTER TABLE `PRUEBAS_HEMATOLOGICAS` DISABLE KEYS */;
/*!40000 ALTER TABLE `PRUEBAS_HEMATOLOGICAS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `COLESTEROL_TRIGLICERIDOS`
--

DROP TABLE IF EXISTS `COLESTEROL_TRIGLICERIDOS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `COLESTEROL_TRIGLICERIDOS` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `id_expediente` int(20) NOT NULL,
  `fecha_laboratorio` date NOT NULL,
  `colesterol` double DEFAULT NULL,
  `trigliceridos` double DEFAULT NULL,
  `id_quimico_biologo` bigint(20) NOT NULL,
  `estado` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_COLESTEROL_TRIGLICERIDOS_QUIMICO` (`id_quimico_biologo`),
  KEY `Index116` (`id_expediente`),
  CONSTRAINT `FK_COLESTEROL_TRIGLICERIDOS_EXPEDIENTE` FOREIGN KEY (`id_expediente`) REFERENCES `EXPEDIENTE_LABORATORIO` (`id`),
  CONSTRAINT `FK_COLESTEROL_TRIGLICERIDOS_QUIMICO` FOREIGN KEY (`id_quimico_biologo`) REFERENCES `APP_USER` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `COLESTEROL_TRIGLICERIDOS`
--

LOCK TABLES `COLESTEROL_TRIGLICERIDOS` WRITE;
/*!40000 ALTER TABLE `COLESTEROL_TRIGLICERIDOS` DISABLE KEYS */;
/*!40000 ALTER TABLE `COLESTEROL_TRIGLICERIDOS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PRUEBA_VIH`
--

DROP TABLE IF EXISTS `PRUEBA_VIH`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PRUEBA_VIH` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `codigo` varchar(50) NOT NULL,
  `fecha_laboratorio` date NOT NULL,
  `orientador` varchar(200) NOT NULL,
  `resultado` varchar(50) NOT NULL,
  `id_quimico_biologo` bigint(20) NOT NULL,
  `estado` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_PRUEBA_VIH_QUIMICO` (`id_quimico_biologo`),
  CONSTRAINT `FK_PRUEBA_VIH_QUIMICO` FOREIGN KEY (`id_quimico_biologo`) REFERENCES `APP_USER` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PRUEBA_VIH`
--

LOCK TABLES `PRUEBA_VIH` WRITE;
/*!40000 ALTER TABLE `PRUEBA_VIH` DISABLE KEYS */;
/*!40000 ALTER TABLE `PRUEBA_VIH` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EXPEDIENTE_LABORATORIO`
--

DROP TABLE IF EXISTS `EXPEDIENTE_LABORATORIO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EXPEDIENTE_LABORATORIO` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `id_paciente` int(20) DEFAULT NULL,
  `nombres` varchar(100) NOT NULL,
  `apellidos` varchar(100) DEFAULT NULL,
  `fecha_nacimiento` datetime NOT NULL,
  `no_registro` int(11) DEFAULT NULL,
  `direccion` varchar(300) NOT NULL,
  `telefono` varchar(50) NOT NULL,
  `movil` varchar(50) NOT NULL,
  `email` varchar(80) NOT NULL,
  `sexo` varchar(50) NOT NULL,
  `estado` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EXPEDIENTE_LABORATORIO`
--

LOCK TABLES `EXPEDIENTE_LABORATORIO` WRITE;
/*!40000 ALTER TABLE `EXPEDIENTE_LABORATORIO` DISABLE KEYS */;
INSERT INTO `EXPEDIENTE_LABORATORIO` VALUES (1,78264,'Susan Johana','Batz Valdez','1996-02-20 00:00:00',201514157,'Avenida Centroamerica A 15-05 Zona 1, Ciudad de Guatemala','22304795','41351595','valdez27ags@outlook.com','0','0'),(2,67612,'Jorge Luis','Cano Gonzalez','1993-01-09 00:00:00',201112058,'7 Avenida 9-32 Zona 1, Mixco','24385052','54830152','jorge_lcg_078@hotmail.com    ','1','0'),(3,79696,'Jesús Alberto','Guerrero Velasquez','1992-04-21 00:00:00',201504517,'Casa 10 Manzana F sector 8  Zona 18  Residenciales los Olivos, Ciudad de Guatemala','24732490','30117074','guerrerojesus388@gmail.com','1','0'),(4,59185,'LUIS RODRIGO','MORALES MARROQUIN','1994-07-11 00:00:00',201445881,'15 calle b 6-24 zona 3','','34776889','luisro1@hotmail.es','1','0'),(5,89572,'Susan Andrea','Garrido Iriarte','1990-12-27 00:00:00',201607623,'29 Calle A 1-12 Zona 8, Ciudad de Guatemala','24400806','55831687','chikizandreag27@gmail.com','0','0'),(6,227,'JUAN CARLOS','MORALES PALACIOS','1992-02-09 00:00:00',201222599,'SEC 2 LOTE 62 COL SN JULIAN Z 6, Chinautla','','42502664','halo_7jc@hotmail.com','1','0'),(7,74255,'Maria Guadalupe','Tiú Dominguez','1996-12-08 00:00:00',201520332,'Zona 3 Paraje los Cayax, Quetzaltenango','41777040','51149493','luperocket8@gmail.com','0','0'),(8,89561,'Reina Elizabeth','Maldonado Cruz','1995-06-29 00:00:00',201611560,'6 Avenida A 5-68 Zona 2 Condominio Campos de San José, Villa Nueva','6646 385','54815352','29elicruz@gmail.com','0','0'),(9,68977,'Floridalma ','Estrada Catalán','1982-06-19 00:00:00',201511570,'3 Calle 1-59 Zona 1 Barrio La Esperanza, Jalapa','57851899','57851899','humanidade2015@hotmail.com','0','0');
/*!40000 ALTER TABLE `EXPEDIENTE_LABORATORIO` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EXAMEN_HECES`
--

LOCK TABLES `EXAMEN_HECES` WRITE;
/*!40000 ALTER TABLE `EXAMEN_HECES` DISABLE KEYS */;
INSERT INTO `EXAMEN_HECES` VALUES (1,1,'2016-09-14','PASTOSA','','_','_','REGULAR_CANTIDAD','No se encontraron','REGULAR_CANTIDAD','_','_','ESCASA','','_',5,'0'),(2,8,'2016-09-27','PASTOSA','','_','_','ESCASA','No se encontro','ESCASA','_','REGULAR_CANTIDAD','ESCASA','','_',4,'0');
/*!40000 ALTER TABLE `EXAMEN_HECES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PRUEBA_SEROLOGICA`
--

DROP TABLE IF EXISTS `PRUEBA_SEROLOGICA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PRUEBA_SEROLOGICA` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `id_expediente` int(20) NOT NULL,
  `fecha_laboratorio` date NOT NULL,
  `resultadoFactorReumatoide` varchar(30) NOT NULL,
  `resultadoProteinaCReactiva` varchar(30) NOT NULL,
  `resultadoAntiEstreptolisinaO` varchar(30) NOT NULL,
  `factor_reumatoide` double DEFAULT NULL,
  `proteina_c_reactiva` double DEFAULT NULL,
  `anti_estreptolisina_o` double DEFAULT NULL,
  `id_quimico_biologo` bigint(20) NOT NULL,
  `estado` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_PRUEBA_SEROLOGICA_QUIMICO` (`id_quimico_biologo`),
  KEY `Index118` (`id_expediente`),
  CONSTRAINT `FK_PRUEBA_SEROLOGICA_EXPEDIENTE` FOREIGN KEY (`id_expediente`) REFERENCES `EXPEDIENTE_LABORATORIO` (`id`),
  CONSTRAINT `FK_PRUEBA_SEROLOGICA_QUIMICO` FOREIGN KEY (`id_quimico_biologo`) REFERENCES `APP_USER` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PRUEBA_SEROLOGICA`
--

LOCK TABLES `PRUEBA_SEROLOGICA` WRITE;
/*!40000 ALTER TABLE `PRUEBA_SEROLOGICA` DISABLE KEYS */;
/*!40000 ALTER TABLE `PRUEBA_SEROLOGICA` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PRUEBA_EMBARAZO`
--

DROP TABLE IF EXISTS `PRUEBA_EMBARAZO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PRUEBA_EMBARAZO` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `id_expediente` int(20) NOT NULL,
  `fecha_laboratorio` date NOT NULL,
  `resultado` varchar(50) NOT NULL,
  `id_quimico_biologo` bigint(20) NOT NULL,
  `estado` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_PRUEBA_EMBARAZO_QUIMICO` (`id_quimico_biologo`),
  KEY `Index111` (`id_expediente`),
  CONSTRAINT `FK_PRUEBA_EMBARAZO_EXPEDIENTE` FOREIGN KEY (`id_expediente`) REFERENCES `EXPEDIENTE_LABORATORIO` (`id`),
  CONSTRAINT `FK_PRUEBA_EMBARAZO_QUIMICO` FOREIGN KEY (`id_quimico_biologo`) REFERENCES `APP_USER` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PRUEBA_EMBARAZO`
--

LOCK TABLES `PRUEBA_EMBARAZO` WRITE;
/*!40000 ALTER TABLE `PRUEBA_EMBARAZO` DISABLE KEYS */;
/*!40000 ALTER TABLE `PRUEBA_EMBARAZO` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `APP_USER`
--

LOCK TABLES `APP_USER` WRITE;
/*!40000 ALTER TABLE `APP_USER` DISABLE KEYS */;
INSERT INTO `APP_USER` VALUES (1,'juanmarcos','$2a$10$70rj5fADrPb.VHuNzezwPehvghosh3cUHLWGr1shcpnZHvPEppL0G','juanmarcos','AdminYLaboratorista','juanmarcos@xyz.com','juan.chacon.png','Active'),(3,'martha.campos','$2a$10$Ano6pw0kMLkBp0pZkZ9DPOX7/NiESUu1EoV/cgdOelmC/2j8OHame','Martha Eugenia','Campos Urizar','mecamposu@gmail.com','martha.campos.png','Active'),(4,'astrid.robles','$2a$10$SW4usdwIhUTMESglQGHo9OozK9yUdgocGTW/LLjmb/Unn7wKxerJa',' Astrid Nicté','Robles Giron','anrg30@gmail.com','astrid.robles.png','Active'),(5,'sandra.armas','$2a$10$yTbKR2tOk2t4EfLRvuc28OB7Xu3og0z7jbbjZKhh6goHE.RvvbNT.','María Sandra','Armas Bonilla','armasmarisandra27@gmail.com','sandra.armas.png','Active'),(6,'rita.figueroa','$2a$10$LZKS6q6BGJ37PHH/xMS//uLJzpyrzg0TcBhl98e/zs7QSo.PI5cfi','Rita Ma. de los Angeles','Figueroa Figueroa','figueroa.ritamaria@gmail.com','','Active'),(7,'danilo.morales','$2a$10$Bzu6yqdvJ0xAyKlK9EcCiOtH9TaDpZLPzCCNzpOmg3DvjIO2EePvS','Danilo Oliverio','Morales Andrade','dmorales@usac.edu.gt','','Active'),(8,'ligia.arevalo','$2a$10$2wt42onuCu95fyzI3cdyc.1I6.R/5WjFdSnsWVEB3ooa0luFbkSOW','Ligia Ester','Arevalo Veras','aligiaester@yahoo.com','','Active'),(9,'ingrid.hernandez','$2a$10$SXT5fcX8CaQoc4tIWbbnJeKsa4vA2Lk3sw/4ngjhl7frHwB50jDqW','Ingrid Solei ','Hernández Caracun','tanuc87@hotmail.com','4.jpg','Active'),(11,'ingrid.hernandezc','$2a$10$1Ki5pYYXSoaljGE58dpViOlz0IQS5oqhhcdqR.k2XlOoRjZnE7wvW','Ingrid Solei ','Hernández Caracun','ihernandezqb@hotmail.com','4.jpg','Active');
/*!40000 ALTER TABLE `APP_USER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `APP_USER_USER_PROFILE`
--

DROP TABLE IF EXISTS `APP_USER_USER_PROFILE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `APP_USER_USER_PROFILE` (
  `user_id` bigint(20) NOT NULL,
  `user_profile_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`user_profile_id`),
  KEY `FK_USER_PROFILE` (`user_profile_id`),
  CONSTRAINT `FK_APP_USER` FOREIGN KEY (`user_id`) REFERENCES `APP_USER` (`id`),
  CONSTRAINT `FK_USER_PROFILE` FOREIGN KEY (`user_profile_id`) REFERENCES `USER_PROFILE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `APP_USER_USER_PROFILE`
--

LOCK TABLES `APP_USER_USER_PROFILE` WRITE;
/*!40000 ALTER TABLE `APP_USER_USER_PROFILE` DISABLE KEYS */;
INSERT INTO `APP_USER_USER_PROFILE` VALUES (6,1),(7,1),(8,1),(3,2),(4,2),(5,2),(11,2),(1,3),(3,3);
/*!40000 ALTER TABLE `APP_USER_USER_PROFILE` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-09-28 18:35:39
