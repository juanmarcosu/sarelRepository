--
-- Table structure for table `ANTIGENO_PROSTATICO`
--

DROP TABLE IF EXISTS `ANTIGENO_PROSTATICO`;

CREATE TABLE `ANTIGENO_PROSTATICO` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `id_expediente` int(20) NOT NULL,
  `fecha_laboratorio` DATE NOT NULL,
  `resultado` DOUBLE NOT NULL,
  `id_quimico_biologo` bigint(20) NOT NULL,
  `estado`  VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_ANTIGENO_PROSTATICO_QUIMICO` FOREIGN KEY (`id_quimico_biologo`) REFERENCES `APP_USER` (`id`),
  CONSTRAINT `FK_ANTIGENO_PROSTATICO_EXPEDIENTE` FOREIGN KEY (`id_expediente`) REFERENCES `EXPEDIENTE_LABORATORIO` (`id`),
  KEY `Index20182` (`id_expediente`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
