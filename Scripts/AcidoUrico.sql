--
-- Table structure for table `ACIDO_URICO`
--

DROP TABLE IF EXISTS laboratorio.`ACIDO_URICO`;

CREATE TABLE laboratorio.`ACIDO_URICO` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `id_expediente` int(20) NOT NULL,
  `fecha_laboratorio` DATE NOT NULL,
  `nivel_acido_urico` DOUBLE NOT NULL,
  `id_quimico_biologo` bigint(20) NOT NULL,
  `estado`  VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_ACIDO_URICO_QUIMICO` FOREIGN KEY (`id_quimico_biologo`) REFERENCES laboratorio.`APP_USER` (`id`),
  CONSTRAINT `FK_ACIDO_URICO_EXPEDIENTE` FOREIGN KEY (`id_expediente`) REFERENCES laboratorio.`EXPEDIENTE_LABORATORIO` (`id`),
  KEY `Index112` (`id_expediente`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
