--
-- Table structure for table `HEMOGLOBINA_GLUCOSA`
--

DROP TABLE IF EXISTS laboratorio.`HEMOGLOBINA_GLUCOSA`;

CREATE TABLE laboratorio.`HEMOGLOBINA_GLUCOSA` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `id_expediente` int(20) NOT NULL,
  `fecha_laboratorio` DATE NOT NULL,
  `hemoglobina_glicosilada` DOUBLE,
  `nivel_promedio_glucosa` DOUBLE,
  `id_quimico_biologo` bigint(20) NOT NULL,
  `estado`  VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_HEMOGLOBINA_GLUCOSA_QUIMICO` FOREIGN KEY (`id_quimico_biologo`) REFERENCES laboratorio.`APP_USER` (`id`),
  CONSTRAINT `FK_HEMOGLOBINA_GLUCOSA_EXPEDIENTE` FOREIGN KEY (`id_expediente`) REFERENCES laboratorio.`EXPEDIENTE_LABORATORIO` (`id`),
  KEY `Index123` (`id_expediente`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
