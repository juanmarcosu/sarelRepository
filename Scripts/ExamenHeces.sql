--
-- Table structure for table `EXAMEN_HECES`
--

DROP TABLE IF EXISTS `EXAMEN_HECES`;

CREATE TABLE `EXAMEN_HECES` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `id_expediente` int(20) NOT NULL,
  `fecha_laboratorio` DATE NOT NULL,
  `aspecto`  VARCHAR(50) NOT NULL,
  `otro_aspecto`  VARCHAR(50),
  `moco`  VARCHAR(50),
  `sangre`  VARCHAR(50),
  `restos_alimenticios`  VARCHAR(50),
  `parasitos`  VARCHAR(500),
  `almidones`  VARCHAR(50),
  `grasas`  VARCHAR(50),
  `fibras_musculares`  VARCHAR(50),
  `jabones`  VARCHAR(50),
  `texto_otros`  VARCHAR(50),
  `otros`  VARCHAR(50),
  `id_quimico_biologo` bigint(20) NOT NULL,
  `estado`  VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_EXAMEN_HECES_QUIMICO` FOREIGN KEY (`id_quimico_biologo`) REFERENCES `APP_USER` (`id`),
  CONSTRAINT `FK_EXAMEN_HECES_EXPEDIENTE` FOREIGN KEY (`id_expediente`) REFERENCES `EXPEDIENTE_LABORATORIO` (`id`),
  KEY `Index118` (`id_expediente`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
