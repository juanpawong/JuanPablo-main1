-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 16-10-2020 a las 15:24:09
-- Versión del servidor: 10.3.16-MariaDB
-- Versión de PHP: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `id15139888_bd_autos`
--

DELIMITER $$
--
-- Funciones
--
CREATE   FUNCTION `CrearVerificarUsuarios` (`user_dni_` VARCHAR(8), `user_nom_` VARCHAR(40), 
`user_ape_` VARCHAR(40), `user_name_` VARCHAR(40), `user_pass_` VARCHAR(40)) 
RETURNS TEXT CHARSET latin1 BEGIN

	DECLARE RESPUESTA VARCHAR(250);
	DECLARE cant INTEGER;
	
	SET cant = 0;
	SET RESPUESTA = '';
	 
	SET cant = (SELECT count(*)  FROM   tb_usuarios 
		      			WHERE   user_dni = user_dni_  );
				    	
	SET cant = IFNULL(cant , 0);  
				    	
	IF(cant<=0) THEN	
    	 
	-- NUEVO USUARIO
	  	 INSERT INTO tb_usuarios  (user_dni, user_nom, user_ape, user_name, user_pass )
		 VALUES (user_dni_, user_nom_, user_ape_, user_name_, user_pass_ );
		 SET RESPUESTA = (SELECT CONCAT("OK", "|", id_usuario, "|",user_dni,"|NEW")    
						FROM   tb_usuarios 
		      			WHERE   user_dni = user_dni_  );
		
		  
	ELSE
    	  
	-- USUARIO EXISTENTE
		 SET RESPUESTA = (SELECT CONCAT("OK", "|", id_usuario, "|",user_dni,"|EXIST")    
						FROM   tb_usuarios 
		      			WHERE   user_dni = user_dni_  );
         
	END IF;
  	RETURN RESPUESTA;
END  ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_user_tarjetas`
--

CREATE TABLE `tb_user_tarjetas` (
  `idtb_user_tarjetas` int(11) NOT NULL,
  `provehedor` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fecha_caducidad` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cvv` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_usuarios`
--

CREATE TABLE `tb_usuarios` (
  `id_usuario` int(11) NOT NULL,
  `fecha_def` timestamp NULL DEFAULT current_timestamp(),
  `user_dni` varchar(8) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_ape` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_pass` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_status` char(1) COLLATE utf8_unicode_ci DEFAULT '1',
  `user_tipo` char(1) COLLATE utf8_unicode_ci DEFAULT '1' COMMENT '1 - usuario normal\n0- administrador\n',
  `user_nom` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_name` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `tb_usuarios`
--

INSERT INTO `tb_usuarios` (`id_usuario`, `fecha_def`, `user_dni`, `user_ape`, `user_pass`, `user_status`, `user_tipo`, `user_nom`, `user_name`) VALUES
(1, '2020-10-16 09:11:16', '88888888', 'APE_PRUEBA', '123456', '1', '1', 'NOMBRE_PRUEBA', 'USER_NAME'),
(2, '2020-10-16 09:56:56', 'a', 'a', 'a', '1', '1', 'a', 'a');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_usuarios_has_tb_user_tarjetas`
--

CREATE TABLE `tb_usuarios_has_tb_user_tarjetas` (
  `id_usuario` int(11) NOT NULL,
  `idtb_user_tarjetas` int(11) NOT NULL,
  `fecha_def` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_usuarios_has_vehiculos`
--

CREATE TABLE `tb_usuarios_has_vehiculos` (
  `id_usu_veh` int(11) NOT NULL,
  `tb_usuarios_id_usuario` int(11) NOT NULL,
  `vehiculos_idvehiculos` int(11) NOT NULL,
  `tb_usuarios_has_vehiculoscol` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `estado` char(1) COLLATE utf8_unicode_ci DEFAULT '1' COMMENT '1 - pendiente de aprobacion\n2 - aprobado\n3 - denegado\n',
  `fecha_def` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vehiculos`
--

CREATE TABLE `vehiculos` (
  `idvehiculos` int(11) NOT NULL,
  `marca` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `modelo` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` char(1) COLLATE utf8_unicode_ci DEFAULT '1',
  `descripcion` text COLLATE utf8_unicode_ci DEFAULT NULL,
  `vehiculoscol` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `tb_user_tarjetas`
--
ALTER TABLE `tb_user_tarjetas`
  ADD PRIMARY KEY (`idtb_user_tarjetas`);

--
-- Indices de la tabla `tb_usuarios`
--
ALTER TABLE `tb_usuarios`
  ADD PRIMARY KEY (`id_usuario`);

--
-- Indices de la tabla `tb_usuarios_has_vehiculos`
--
ALTER TABLE `tb_usuarios_has_vehiculos`
  ADD PRIMARY KEY (`id_usu_veh`);

--
-- Indices de la tabla `vehiculos`
--
ALTER TABLE `vehiculos`
  ADD PRIMARY KEY (`idvehiculos`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `tb_user_tarjetas`
--
ALTER TABLE `tb_user_tarjetas`
  MODIFY `idtb_user_tarjetas` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tb_usuarios`
--
ALTER TABLE `tb_usuarios`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `tb_usuarios_has_vehiculos`
--
ALTER TABLE `tb_usuarios_has_vehiculos`
  MODIFY `id_usu_veh` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `vehiculos`
--
ALTER TABLE `vehiculos`
  MODIFY `idvehiculos` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;