--
-- Table structure for table `EXPEDIENTE_LABORATORIO`
--

DROP TABLE IF EXISTS `EXPEDIENTE_LABORATORIO`;

CREATE TABLE `EXPEDIENTE_LABORATORIO` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `id_paciente` int(20),
  `nombres` varchar(100) NOT NULL,
  `apellidos` varchar(100) DEFAULT NULL,
  `fecha_nacimiento` datetime NOT NULL,
  `no_registro` int(11) DEFAULT NULL,
  `direccion` varchar(300) NOT NULL,
  `telefono` varchar(50) NOT NULL,
  `movil` varchar(50) NOT NULL,
  `email` varchar(80) NOT NULL,
  `sexo` varchar(50) NOT NULL,
  `estado` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
