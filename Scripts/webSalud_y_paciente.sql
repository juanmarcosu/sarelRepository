
CREATE DATABASE `websalud` ;

CREATE TABLE `websalud`.`paciente` (
  
`idpaciente` int(20) NOT NULL AUTO_INCREMENT,
  
`nombre` varchar(100) NOT NULL,
  
`apellido` varchar(100) DEFAULT NULL,
  
`fecha_nac` datetime NOT NULL,
  
`carne` int(11) DEFAULT NULL,
  
`direccion` varchar(300) NOT NULL,
  
`telefono` varchar(50) NOT NULL,
  
`movil` varchar(50) NOT NULL,
  
`email` varchar(80) NOT NULL,
  
`emer_nombre` varchar(200) DEFAULT NULL,
  
`idemer_parentesco` int(11) DEFAULT NULL,
  
`emer_telefono` varchar(50) DEFAULT NULL,
  
`emer_movil` varchar(50) DEFAULT NULL,
  
`tipo_sangreidtipo_sangre` int(11) DEFAULT NULL,
  
`estado_civilidestado_civil` int(11) DEFAULT NULL,
  
`usuario` varchar(20) DEFAULT NULL,
  
`sexo` int(11) NOT NULL DEFAULT '1',
  
`titulo_secundaria` varchar(150) DEFAULT NULL,
  
`crecio_en` varchar(100) DEFAULT NULL,
  
`estado` int(11) NOT NULL DEFAULT '1',
  
`iddepartamento` int(11) DEFAULT NULL,
  
`idnacionalidad` int(11) DEFAULT NULL,
  
`examen_linea` int(11) NOT NULL DEFAULT '0',
  
`idunidad_academica` int(11) DEFAULT NULL,
  
`fecha_exam` varchar(45) DEFAULT NULL,
  
PRIMARY KEY (`idpaciente`),
  
UNIQUE KEY `carne` (`carne`),
  
KEY `Index12` (`carne`),
  KEY `Index13` (`usuario`),
  
KEY `FKpaciente283056` (`idunidad_academica`)
) 
ENGINE=InnoDB AUTO_INCREMENT=88585 DEFAULT CHARSET=utf8;
