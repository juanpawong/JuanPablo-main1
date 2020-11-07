 

CREATE  FUNCTION `ValidarPago`(
	`cvv_`                VARCHAR (45),
	`tarjeta_`            VARCHAR (50),
	`tarjeta_fecha_`      VARCHAR (50),
	`titular_`            VARCHAR (50),
	`email_`              VARCHAR (50),
	`monto_`              VARCHAR (50),
	`fecha_inicio_`       VARCHAR (50),
	`fecha_fin_`          VARCHAR (50),
	`dias_alquiler_`      VARCHAR (50),
	`vehiculoId_`		  INTEGER ,
	`user_dni_` 		  VARCHAR(8) ,
	`fecha_caducidad_`    VARCHAR (50)
	 ) RETURNS text CHARSET latin1
BEGIN

	DECLARE RESPUESTA VARCHAR(250);
	DECLARE cant INTEGER;
	DECLARE id_usuario_ INTEGER ;
	DECLARE idtb_user_tarjetas_ INTEGER ;
	DECLARE id_usu_veh_ INTEGER ;
	
	
	SET cant = 0;
	SET RESPUESTA = '';
	 
	SET cant = (SELECT count(*)  FROM   tb_usuarios 
		      			WHERE   user_dni = user_dni_  );
				    	
	SET cant = IFNULL(cant , 0);  
				    	
	IF(cant<=0) THEN	
    	 
	-- NO EXISTE
	  	  
		SET RESPUESTA = (SELECT CONCAT("ERR", "|USUARIO NO EXISTE| | | | | | | | | ") );
		
		
		  
	ELSE
		
		SET id_usuario_ = (SELECT id_usuario  FROM   tb_usuarios 
		      			WHERE   user_dni = user_dni_  );
    	  
		
		-- VERIFICAR SI EXISTE LA TARJETA		 
        SET cant = (SELECT count(*)  FROM   tb_user_tarjetas 
		      			WHERE   tarjeta = tarjeta_  );
	
		IF(cant<=0) THEN
			-- AGREGAMOS LA TARJETA
		 	INSERT INTO tb_user_tarjetas (  fecha_caducidad, cvv, 
		 		tarjeta, tarjeta_fecha, titular, email)
			VALUES ( fecha_caducidad_, cvv_, 
		 		tarjeta_, tarjeta_fecha_, titular_, email_);
		END IF;
		
		
		SET idtb_user_tarjetas_ = (SELECT idtb_user_tarjetas  FROM   tb_user_tarjetas 
		      			WHERE   tarjeta = tarjeta_  );
		      			
		      			
		-- registrando la relacion
		-- VERIFICAR SI EXISTE LA relacion		 
        SET cant = (SELECT count(*)  FROM   tb_usuarios_has_tb_user_tarjetas 
		      			WHERE   id_usuario=id_usuario_
		      			 AND idtb_user_tarjetas = idtb_user_tarjetas_ );
		
		IF(cant<=0) THEN
			-- AGREGAMOS LA relacion usuario tarjeta
		 	INSERT INTO tb_usuarios_has_tb_user_tarjetas (
		 		id_usuario, idtb_user_tarjetas)
				VALUES (id_usuario_,idtb_user_tarjetas_ ) ;
		END IF;
		
		
		-- AGREGAMOS LA relacion usuario vehiculo
		INSERT INTO tb_usuarios_has_vehiculos (
		 		tb_usuarios_id_usuario, vehiculos_idvehiculos,id_tarjetas, monto, fecha_inicio, fecha_fin, dias_alquiler)
				VALUES (id_usuario_,vehiculoId_,idtb_user_tarjetas_, monto_, fecha_inicio_, fecha_fin_, dias_alquiler_ ) ;
		
		-- actualizando estado de vehiculo
		UPDATE vehiculos SET status='0' WHERE idvehiculos=vehiculoId_ ;
		
		SET RESPUESTA = (SELECT CONCAT("OK", "|PROCESO COMPLETADO| | | | | | | | | ") );  
	END IF;
  	RETURN RESPUESTA;
END;


 