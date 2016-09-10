--
-- Table structure for table `EXAMEN_ORINA`
--

DROP TABLE IF EXISTS laboratorio.`EXAMEN_ORINA`;

CREATE TABLE laboratorio.`EXAMEN_ORINA` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `id_expediente` int(20) NOT NULL,
  `fecha_laboratorio` DATE NOT NULL,
  `color`  VARCHAR(50) NOT NULL,
  `otro_color`  VARCHAR(50),
  `aspecto`  VARCHAR(50) NOT NULL,
  `ph` DOUBLE,
  `densidad` DOUBLE,
  `bioquimico`  VARCHAR(250),
  `leucositos`  VARCHAR(50),
  `eritrositos`  VARCHAR(50),
  `celulas_epiteliales`VARCHAR(50),
  `moco`  VARCHAR(50),
  `cristales`  VARCHAR(250),
  `texto_cilindros`  VARCHAR(50),
  `cilindros`  VARCHAR(50),
  `texto_bacterias`  VARCHAR(50),
  `bacterias`  VARCHAR(50) ,
  `texto_otros`  VARCHAR(50),
  `otros`  VARCHAR(50),
  `id_quimico_biologo` bigint(20) NOT NULL,
  `estado`  VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_EXAMEN_ORINA_QUIMICO` FOREIGN KEY (`id_quimico_biologo`) REFERENCES `APP_USER` (`id`),
  CONSTRAINT `FK_EXAMEN_ORINA_EXPEDIENTE` FOREIGN KEY (`id_expediente`) REFERENCES `EXPEDIENTE_LABORATORIO` (`id`),
  KEY `Index117` (`id_expediente`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
