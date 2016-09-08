CREATE DATABASE `laboratorio` ;

/*All User's are stored in APP_USER table*/
create table laboratorio.APP_USER (
   id BIGINT NOT NULL AUTO_INCREMENT,
   sso_id VARCHAR(30) NOT NULL,
   password VARCHAR(100) NOT NULL,
   first_name VARCHAR(30) NOT NULL,
   last_name  VARCHAR(30) NOT NULL,
   email VARCHAR(30) NOT NULL,
   state VARCHAR(30) NOT NULL,  
   PRIMARY KEY (id),
   UNIQUE (sso_id)
);
  
/* USER_PROFILE table contains all possible roles */
create table laboratorio.USER_PROFILE(
   id BIGINT NOT NULL AUTO_INCREMENT,
   type VARCHAR(30) NOT NULL,
   PRIMARY KEY (id),
   UNIQUE (type)
);
  
/* JOIN TABLE for MANY-TO-MANY relationship*/ 
CREATE TABLE laboratorio.APP_USER_USER_PROFILE (
    user_id BIGINT NOT NULL,
    user_profile_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, user_profile_id),
    CONSTRAINT FK_APP_USER FOREIGN KEY (user_id) REFERENCES APP_USER (id),
    CONSTRAINT FK_USER_PROFILE FOREIGN KEY (user_profile_id) REFERENCES USER_PROFILE (id)
);

/* Populate CONSULTOR_PROFILE Table */
INSERT INTO laboratorio.USER_PROFILE(type)
VALUES ('CONSULTOR');
 
INSERT INTO laboratorio.USER_PROFILE(type)
VALUES ('LABORATORISTA');
 
INSERT INTO laboratorio.USER_PROFILE(type)
VALUES ('ADMINISTRADOR');
 
INSERT INTO laboratorio.APP_USER(sso_id, password, first_name, last_name, email, state)
VALUES ('juanmarcos','123', 'juanmarcos','AdminYLaboratorista','juanmarcos@xyz.com', 'Active');
 
INSERT INTO laboratorio.APP_USER_USER_PROFILE (USER_id, USER_profile_id)
  SELECT USER.id, profile.id FROM laboratorio.APP_USER USER, laboratorio.USER_PROFILE profile  
  where USER.sso_id='juanmarcos' and profile.type='ADMINISTRADOR';

--
-- Table structure for table `EXPEDIENTE_LABORATORIO`
--

DROP TABLE IF EXISTS laboratorio.`EXPEDIENTE_LABORATORIO`;

CREATE TABLE laboratorio.`EXPEDIENTE_LABORATORIO` (
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

--
-- Table structure for table `ACIDO_URICO`
--

DROP TABLE IF EXISTS laboratorio.`ACIDO_URICO`;

CREATE TABLE laboratorio.`ACIDO_URICO` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `id_expediente` int(20) NOT NULL,
  `fecha_laboratorio` DATE NOT NULL,
  `nivel_acido_urico` DOUBLE NOT NULL,
  `id_quimico_biologo` bigint(20) NOT NULL,
  `estado`  VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_ACIDO_URICO_QUIMICO` FOREIGN KEY (`id_quimico_biologo`) REFERENCES `APP_USER` (`id`),
  CONSTRAINT `FK_ACIDO_URICO_EXPEDIENTE` FOREIGN KEY (`id_expediente`) REFERENCES `EXPEDIENTE_LABORATORIO` (`id`),
  KEY `Index112` (`id_expediente`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

--
-- Table structure for table `COLESTEROL_TRIGLICERIDOS`
--

DROP TABLE IF EXISTS laboratorio.`COLESTEROL_TRIGLICERIDOS`;

CREATE TABLE laboratorio.`COLESTEROL_TRIGLICERIDOS` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `id_expediente` int(20) NOT NULL,
  `fecha_laboratorio` DATE NOT NULL,
  `colesterol` DOUBLE,
  `trigliceridos` DOUBLE,
  `id_quimico_biologo` bigint(20) NOT NULL,
  `estado`  VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_COLESTEROL_TRIGLICERIDOS_QUIMICO` FOREIGN KEY (`id_quimico_biologo`) REFERENCES `APP_USER` (`id`),
  CONSTRAINT `FK_COLESTEROL_TRIGLICERIDOS_EXPEDIENTE` FOREIGN KEY (`id_expediente`) REFERENCES `EXPEDIENTE_LABORATORIO` (`id`),
  KEY `Index116` (`id_expediente`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

--
-- Table structure for table `EXAMEN_HECES`
--

DROP TABLE IF EXISTS laboratorio.`EXAMEN_HECES`;

CREATE TABLE laboratorio.`EXAMEN_HECES` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `id_expediente` int(20) NOT NULL,
  `fecha_laboratorio` DATE NOT NULL,
  `aspecto`  VARCHAR(50) NOT NULL,
  `otro_aspecto`  VARCHAR(50),
  `moco`  VARCHAR(50),
  `sangre`  VARCHAR(50),
  `restos_alimenticios`  VARCHAR(50),
  `parasitos`  VARCHAR(500),
  `almidones`  VARCHAR(50),
  `grasas`  VARCHAR(50),
  `fibras_musculares`  VARCHAR(50),
  `jabones`  VARCHAR(50),
  `texto_otros`  VARCHAR(50),
  `otros`  VARCHAR(50),
  `id_quimico_biologo` bigint(20) NOT NULL,
  `estado`  VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_EXAMEN_HECES_QUIMICO` FOREIGN KEY (`id_quimico_biologo`) REFERENCES `APP_USER` (`id`),
  CONSTRAINT `FK_EXAMEN_HECES_EXPEDIENTE` FOREIGN KEY (`id_expediente`) REFERENCES `EXPEDIENTE_LABORATORIO` (`id`),
  KEY `Index118` (`id_expediente`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

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
  `ph` DOUBLE NOT NULL,
  `densidad` DOUBLE NOT NULL,
  `bioquimico`  VARCHAR(250),
  `leucositos`  VARCHAR(50) NOT NULL,
  `eritrositos`  VARCHAR(50) NOT NULL,
  `celulas_epiteliales`  VARCHAR(50) NOT NULL,
  `moco`  VARCHAR(50) NOT NULL,
  `texto_cristales`  VARCHAR(50) NOT NULL,
  `cristales`  VARCHAR(50) NOT NULL,
  `texto_cilindros`  VARCHAR(50) NOT NULL,
  `cilindros`  VARCHAR(50) NOT NULL,
  `texto_bacterias`  VARCHAR(50) NOT NULL,
  `bacterias`  VARCHAR(50) NOT NULL,
  `texto_otros`  VARCHAR(50) NOT NULL,
  `otros`  VARCHAR(50) NOT NULL,
  `id_quimico_biologo` bigint(20) NOT NULL,
  `estado`  VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_EXAMEN_ORINA_QUIMICO` FOREIGN KEY (`id_quimico_biologo`) REFERENCES `APP_USER` (`id`),
  CONSTRAINT `FK_EXAMEN_ORINA_EXPEDIENTE` FOREIGN KEY (`id_expediente`) REFERENCES `EXPEDIENTE_LABORATORIO` (`id`),
  KEY `Index117` (`id_expediente`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

--
-- Table structure for table `GLUCOSA_PRE_Y_POST`
--

DROP TABLE IF EXISTS laboratorio.`GLUCOSA_PRE_Y_POST`;

CREATE TABLE laboratorio.`GLUCOSA_PRE_Y_POST` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `id_expediente` int(20) NOT NULL,
  `fecha_laboratorio` DATE NOT NULL,
  `pre_prandial` DOUBLE NOT NULL,
  `post_prandial` DOUBLE NOT NULL,
  `id_quimico_biologo` bigint(20) NOT NULL,
  `estado`  VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_GLUCOSA_PRE_Y_POST_QUIMICO` FOREIGN KEY (`id_quimico_biologo`) REFERENCES `APP_USER` (`id`),
  CONSTRAINT `FK_GLUCOSA_PRE_Y_POST_EXPEDIENTE` FOREIGN KEY (`id_expediente`) REFERENCES `EXPEDIENTE_LABORATORIO` (`id`),
  KEY `Index117` (`id_expediente`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

--
-- Table structure for table `HEMATOLOGIA_COMPLETA`
--

DROP TABLE IF EXISTS laboratorio.`HEMATOLOGIA_COMPLETA`;

CREATE TABLE laboratorio.`HEMATOLOGIA_COMPLETA` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `id_expediente` int(20) NOT NULL,
  `fecha_laboratorio` DATE NOT NULL,
  `rbc` DOUBLE NOT NULL,
  `hct` DOUBLE NOT NULL,
  `mcv` DOUBLE NOT NULL,
  `hgb` DOUBLE NOT NULL,
  `mch` DOUBLE NOT NULL,
  `mchc` DOUBLE NOT NULL,
  `plt` DOUBLE NOT NULL,
  `wbc` DOUBLE NOT NULL,
  `lym` DOUBLE NOT NULL,
  `gran` DOUBLE NOT NULL,
  `mid` DOUBLE NOT NULL,
  `vse` int(20),
  `id_quimico_biologo` bigint(20) NOT NULL,
  `estado`  VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_HEMATOLOGIA_COMPLETA_QUIMICO` FOREIGN KEY (`id_quimico_biologo`) REFERENCES `APP_USER` (`id`),
  CONSTRAINT `FK_HEMATOLOGIA_COMPLETA_EXPEDIENTE` FOREIGN KEY (`id_expediente`) REFERENCES `EXPEDIENTE_LABORATORIO` (`id`),
  KEY `Index112` (`id_expediente`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

--
-- Table structure for table `PERFIL_LIPIDO`
--

DROP TABLE IF EXISTS laboratorio.`PERFIL_LIPIDICO`;

CREATE TABLE laboratorio.`PERFIL_LIPIDICO` (
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
  `id_quimico_biologo` bigint(20) NOT NULL,
  `estado`  VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_PERFIL_LIPIDICO_QUIMICO` FOREIGN KEY (`id_quimico_biologo`) REFERENCES `APP_USER` (`id`),
  CONSTRAINT `FK_PERFIL_LIPIDICO_EXPEDIENTE` FOREIGN KEY (`id_expediente`) REFERENCES `EXPEDIENTE_LABORATORIO` (`id`),
  KEY `Index110` (`id_expediente`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

--
-- Table structure for table `PRUEBA_EMBARAZO`
--

DROP TABLE IF EXISTS laboratorio.`PRUEBA_EMBARAZO`;

CREATE TABLE laboratorio.`PRUEBA_EMBARAZO` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `id_expediente` int(20) NOT NULL,
  `fecha_laboratorio` DATE NOT NULL,
  `resultado`  VARCHAR(50) NOT NULL,
  `id_quimico_biologo` bigint(20) NOT NULL,
  `estado`  VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_PRUEBA_EMBARAZO_QUIMICO` FOREIGN KEY (`id_quimico_biologo`) REFERENCES `APP_USER` (`id`),
  CONSTRAINT `FK_PRUEBA_EMBARAZO_EXPEDIENTE` FOREIGN KEY (`id_expediente`) REFERENCES `EXPEDIENTE_LABORATORIO` (`id`),
  KEY `Index111` (`id_expediente`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

--
-- Table structure for table `PRUEBA_SEROLOGICA`
--

DROP TABLE IF EXISTS laboratorio.`PRUEBA_SEROLOGICA`;

CREATE TABLE laboratorio.`PRUEBA_SEROLOGICA` (
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

--
-- Table structure for table `PRUEBAS_HEMATOLOGICAS`
--

DROP TABLE IF EXISTS laboratorio.`PRUEBAS_HEMATOLOGICAS`;

CREATE TABLE laboratorio.`PRUEBAS_HEMATOLOGICAS` (
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

--
-- Table structure for table `PRUEBA_VDRL`
--

DROP TABLE IF EXISTS laboratorio.`PRUEBA_VDRL`;

CREATE TABLE laboratorio.`PRUEBA_VDRL` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `id_expediente` int(20) NOT NULL,
  `fecha_laboratorio` DATE NOT NULL,
  `resultado`  VARCHAR(30) NOT NULL,
  `rea_VDRL` int(20),
  `nivel_VDRL` int(20),
  `id_quimico_biologo` bigint(20) NOT NULL,
  `estado`  VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_PRUEBA_VDRL_QUIMICO` FOREIGN KEY (`id_quimico_biologo`) REFERENCES `APP_USER` (`id`),
  CONSTRAINT `FK_PRUEBA_VDRL_EXPEDIENTE` FOREIGN KEY (`id_expediente`) REFERENCES `EXPEDIENTE_LABORATORIO` (`id`),
  KEY `Index113` (`id_expediente`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

--
-- Table structure for table `PRUEBA_VIH`
--

DROP TABLE IF EXISTS laboratorio.`PRUEBA_VIH`;

CREATE TABLE laboratorio.`PRUEBA_VIH` (
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

