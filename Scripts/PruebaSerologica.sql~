--
-- Table structure for table `PRUEBA_SEROLOGICA`
--

DROP TABLE IF EXISTS `PRUEBA_SEROLOGICA`;

CREATE TABLE `PRUEBA_SEROLOGICA` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `id_expediente` int(20) NOT NULL,
  `fecha_laboratorio` DATE NOT NULL,
  `resultadoFactorReumatoide`  VARCHAR(30) NOT NULL,
  `resultadoProteinaCReactiva`  VARCHAR(30) NOT NULL,
  `resultadoAntiEstreptolisinaO`  VARCHAR(30) NOT NULL,
  `factor_reumatoide` DOUBLE,
  `proteina_c_reactiva` DOUBLE,
  `anti_estreptolisina_o` DOUBLE,
  `id_quimico_biologo` bigint(20) NOT NULL,
  `estado`  VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_PRUEBA_SEROLOGICA_QUIMICO` FOREIGN KEY (`id_quimico_biologo`) REFERENCES `APP_USER` (`id`),
  CONSTRAINT `FK_PRUEBA_SEROLOGICA_EXPEDIENTE` FOREIGN KEY (`id_expediente`) REFERENCES `EXPEDIENTE_LABORATORIO` (`id`),
  KEY `Index118` (`id_expediente`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
