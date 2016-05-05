--
-- Table structure for table `PERFIL_LIPIDO`
--

DROP TABLE IF EXISTS `PERFIL_LIPIDICO`;

CREATE TABLE `PERFIL_LIPIDICO` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `id_expediente` int(20) NOT NULL,
  `fecha_laboratorio` DATE NOT NULL,
  `colesterol_total` DOUBLE NOT NULL,
  `colesterol_alta_densidad` DOUBLE NOT NULL,
  `colesterol_baja_densidad` DOUBLE NOT NULL,
  `colesterol_muy_baja_densidad` DOUBLE NOT NULL,
  `trigliceridos` DOUBLE NOT NULL,
  `indice_riesgo` DOUBLE NOT NULL,
  `resistencia_insulina` DOUBLE NOT NULL,
  `id_quimico_biologo` int(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `Index110` (`id_expediente`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
