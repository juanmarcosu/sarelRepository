--
-- Table structure for table `GLUCOSA_PRE_Y_POST`
--

DROP TABLE IF EXISTS `GLUCOSA_PRE_Y_POST`;

CREATE TABLE `GLUCOSA_PRE_Y_POST` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `id_expediente` int(20) NOT NULL,
  `fecha_laboratorio` DATE NOT NULL,
  `pre_prandial` DOUBLE,
  `post_prandial` DOUBLE,
  `id_quimico_biologo` bigint(20) NOT NULL,
  `estado`  VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_GLUCOSA_PRE_Y_POST_QUIMICO` FOREIGN KEY (`id_quimico_biologo`) REFERENCES `APP_USER` (`id`),
  CONSTRAINT `FK_GLUCOSA_PRE_Y_POST_EXPEDIENTE` FOREIGN KEY (`id_expediente`) REFERENCES `EXPEDIENTE_LABORATORIO` (`id`),
  KEY `Index117` (`id_expediente`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
