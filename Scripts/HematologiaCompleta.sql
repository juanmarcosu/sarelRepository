--
-- Table structure for table `HEMATOLOGIA_COMPLETA`
--

DROP TABLE IF EXISTS laboratorio.`HEMATOLOGIA_COMPLETA`;

CREATE TABLE laboratorio.`HEMATOLOGIA_COMPLETA` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `id_expediente` int(20) NOT NULL,
  `fecha_laboratorio` DATE NOT NULL,
  `rbc` DOUBLE NOT NULL,
  `hct` DOUBLE NOT NULL,
  `mcv` DOUBLE NOT NULL,
  `hgb` DOUBLE NOT NULL,
  `mch` DOUBLE NOT NULL,
  `mchc` DOUBLE NOT NULL,
  `plt` DOUBLE NOT NULL,
  `wbc` DOUBLE NOT NULL,
  `lym` DOUBLE NOT NULL,
  `gran` DOUBLE NOT NULL,
  `mid` DOUBLE NOT NULL,
  `vse` int(20),
  `id_quimico_biologo` bigint(20) NOT NULL,
  `estado`  VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_HEMATOLOGIA_COMPLETA_QUIMICO` FOREIGN KEY (`id_quimico_biologo`) REFERENCES laboratorio.`APP_USER` (`id`),
  CONSTRAINT `FK_HEMATOLOGIA_COMPLETA_EXPEDIENTE` FOREIGN KEY (`id_expediente`) REFERENCES laboratorio.`EXPEDIENTE_LABORATORIO` (`id`),
  KEY `Index112` (`id_expediente`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
