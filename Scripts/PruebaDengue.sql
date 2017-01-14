--
-- Table structure for table `PRUEBA_DENGE`
--

DROP TABLE IF EXISTS laboratorio.`PRUEBA_DENGE`;

CREATE TABLE laboratorio.`PRUEBA_DENGE` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `id_expediente` int(20) NOT NULL,
  `fecha_laboratorio` DATE NOT NULL,
  `resultado`  VARCHAR(50) NOT NULL,
  `tipo_dengue`  VARCHAR(50),
  `id_quimico_biologo` bigint(20) NOT NULL,
  `estado`  VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_PRUEBA_DENGE_QUIMICO` FOREIGN KEY (`id_quimico_biologo`) REFERENCES laboratorio.`APP_USER` (`id`),
  CONSTRAINT `FK_PRUEBA_DENGE_EXPEDIENTE` FOREIGN KEY (`id_expediente`) REFERENCES laboratorio.`EXPEDIENTE_LABORATORIO` (`id`),
  KEY `Index120` (`id_expediente`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
