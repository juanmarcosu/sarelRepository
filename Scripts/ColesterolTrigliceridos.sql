--
-- Table structure for table `COLESTEROL_TRIGLICERIDOS`
--

DROP TABLE IF EXISTS laboratorio.`COLESTEROL_TRIGLICERIDOS`;

CREATE TABLE laboratorio.`COLESTEROL_TRIGLICERIDOS` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `id_expediente` int(20) NOT NULL,
  `fecha_laboratorio` DATE NOT NULL,
  `colesterol` DOUBLE,
  `trigliceridos` DOUBLE,
  `id_quimico_biologo` bigint(20) NOT NULL,
  `estado`  VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_COLESTEROL_TRIGLICERIDOS_QUIMICO` FOREIGN KEY (`id_quimico_biologo`) REFERENCES laboratorio.`APP_USER` (`id`),
  CONSTRAINT `FK_COLESTEROL_TRIGLICERIDOS_EXPEDIENTE` FOREIGN KEY (`id_expediente`) REFERENCES laboratorio.`EXPEDIENTE_LABORATORIO` (`id`),
  KEY `Index116` (`id_expediente`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
