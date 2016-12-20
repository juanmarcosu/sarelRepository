--
-- Table structure for table `HELICOBACTER_PYLORI`
--

DROP TABLE IF EXISTS laboratorio.`HELICOBACTER_PYLORI`;

CREATE TABLE laboratorio.`HELICOBACTER_PYLORI` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `id_expediente` int(20) NOT NULL,
  `fecha_laboratorio` DATE NOT NULL,
  `resultado`  VARCHAR(50) NOT NULL,
  `id_quimico_biologo` bigint(20) NOT NULL,
  `estado`  VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_HELICOBACTER_PYLORI_QUIMICO` FOREIGN KEY (`id_quimico_biologo`) REFERENCES laboratorio.`APP_USER` (`id`),
  CONSTRAINT `FK_HELICOBACTER_PYLORI_EXPEDIENTE` FOREIGN KEY (`id_expediente`) REFERENCES laboratorio.`EXPEDIENTE_LABORATORIO` (`id`),
  KEY `Index122` (`id_expediente`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
