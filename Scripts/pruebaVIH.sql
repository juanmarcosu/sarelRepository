--
-- Table structure for table `PRUEBA_VIH`
--

DROP TABLE IF EXISTS `PRUEBA_VIH`;

CREATE TABLE `PRUEBA_VIH` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `codigo`  VARCHAR(50) NOT NULL,
  `fecha_laboratorio` DATE NOT NULL,
  `orientador`  VARCHAR(200) NOT NULL,
  `resultado`  VARCHAR(50) NOT NULL,
  `id_quimico_biologo` bigint(20) NOT NULL,
  `estado`  VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_PRUEBA_VIH_QUIMICO` FOREIGN KEY (`id_quimico_biologo`) REFERENCES `APP_USER` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
