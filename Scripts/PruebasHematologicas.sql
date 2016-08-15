--
-- Table structure for table `PRUEBAS_HEMATOLOGICAS`
--

DROP TABLE IF EXISTS `PRUEBAS_HEMATOLOGICAS`;

CREATE TABLE `PRUEBAS_HEMATOLOGICAS` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `id_expediente` int(20) NOT NULL,
  `fecha_laboratorio` DATE NOT NULL,
  `velocidad_sedimentacion` int(20),
  `hematocrito` int(20),
  `id_quimico_biologo` bigint(20) NOT NULL,
  `estado`  VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_PRUEBAS_HEMATOLOGICAS_QUIMICO` FOREIGN KEY (`id_quimico_biologo`) REFERENCES `APP_USER` (`id`),
  CONSTRAINT `FK_PRUEBAS_HEMATOLOGICAS_EXPEDIENTE` FOREIGN KEY (`id_expediente`) REFERENCES `EXPEDIENTE_LABORATORIO` (`id`),
  KEY `Index117` (`id_expediente`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
