
 

SELECT loginUsuarios('33333333','333')

CREATE   FUNCTION `loginUsuarios`(
	`user_dni_` VARCHAR(8), 
	`user_pass_` VARCHAR(40)) 
	RETURNS text CHARSET latin1
BEGIN

	DECLARE RESPUESTA VARCHAR(250);
	DECLARE cant INTEGER;
	
	SET cant = 0;
	SET RESPUESTA = '';
	 
	SET cant = (SELECT count(*)  FROM   tb_usuarios 
		      			WHERE   user_dni = user_dni_  AND user_pass=user_pass_ );
				    	
	SET cant = IFNULL(cant , 0);  
				    	
	IF(cant<=0) THEN	
    	 
	-- NUEVO USUARIO
	  	  SET RESPUESTA = (SELECT CONCAT("ERR", "|USUARIO O PASSWOR EQUIVOCADOS| | | | | | | | | ") );
		
		  
	ELSE
    	  
	-- USUARIO EXISTENTE
		 SET RESPUESTA = (SELECT CONCAT("OK", "|", id_usuario, "|",user_dni,"|EXIST", "|", user_nom, " ",user_ape)    
						FROM   tb_usuarios 
		      			WHERE   user_dni = user_dni_  AND user_pass=user_pass_ );
         
	END IF;
  	RETURN RESPUESTA;
END;

