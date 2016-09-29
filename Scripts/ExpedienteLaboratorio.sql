--
-- Table structure for table `EXPEDIENTE_LABORATORIO`
--

DROP TABLE IF EXISTS laboratorio.`EXPEDIENTE_LABORATORIO`;

CREATE TABLE laboratorio.`EXPEDIENTE_LABORATORIO` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `id_paciente` int(20),
  `nombres` varchar(100) NOT NULL,
  `apellidos` varchar(100) NOT NULL,
  `fecha_nacimiento` datetime,
  `no_registro` bigint(20) NOT NULL,
  `direccion` varchar(300),
  `telefono` varchar(50),
  `movil` varchar(50),
  `email` varchar(80),
  `sexo` varchar(50),
  `estado` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
