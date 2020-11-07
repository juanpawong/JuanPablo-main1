-- procedimientos almacenados y funciones

SELECT  CrearVerificarUsuarios ('88888888','NOMBRE_PRUEBA','APE_PRUEBA','USER_NAME','123456')  ;


CREATE   FUNCTION  `CrearVerificarUsuarios`(
                  `user_dni_` varchar(8)  ,
				  `user_nom_` varchar(40)  ,
				  `user_ape_` varchar(40)  ,
				  `user_name_` varchar(40)  ,
				  `user_pass_` varchar(40)    					 
) RETURNS text CHARSET latin1
BEGIN

	DECLARE RESPUESTA VARCHAR(250);
	DECLARE cant INTEGER;
	
	SET cant = 0;
	SET RESPUESTA='';
	 
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
END;


CREATE   FUNCTION  `SET_URBANIA_ADD`(
                    CODIGO_WEB_          DECIMAL (18,4),
                    NOMBRES_             VARCHAR (250),
                    APELLIDOS_           VARCHAR (250),
                    DNI_                 VARCHAR (250),
                    MAIL_                VARCHAR (250),
                    Distrito1_           VARCHAR (250),
                    Distrito2_           VARCHAR (250),
                    Distrito3_           VARCHAR (250),
                    procedencia_         VARCHAR (250),
                    Precio_del_Inmueble_ VARCHAR (250),
                    INS_CATEGORIA_       VARCHAR (250),
                    OBS_                 VARCHAR (250)
  					 
) RETURNS text CHARSET latin1
BEGIN

	DECLARE DATOS_PERSONALES VARCHAR(250);
	DECLARE cant INTEGER;
	
	SET cant = 0;
	SET DATOS_PERSONALES='';
	 
	SET cant = (SELECT count(*)  FROM   URBANIA 
		      			WHERE   CODIGO_WEB = CODIGO_WEB_  );
				    	
	SET cant = IFNULL(cant , 0);  
				    	
	IF(cant<=0) THEN	  
		    	-- NO EXISTE
	  	 INSERT INTO URBANIA (CODIGO_WEB, NOMBRES, APELLIDOS, DNI, MAIL, 
	  	 Distrito1, Distrito2, Distrito3, procedencia, Precio_del_Inmueble, 
	  	 INS_CATEGORIA,  OBS)
		 VALUES (CODIGO_WEB_, NOMBRES_, APELLIDOS_, 
		 DNI_, MAIL_, Distrito1_, Distrito2_, Distrito3_, procedencia_, 
		 Precio_del_Inmueble_, INS_CATEGORIA_, 
		 OBS_);
	END IF;
  	RETURN cant;
END;



CREATE PROCEDURE SEARCH_PARTICIPANTE 
(@PARAMETRO VARCHAR(50))
AS 
BEGIN 
 
 
 	DECLARE @BUSQUEDA_NUMERICA NUMERIC(18)  
 	SET @BUSQUEDA_NUMERICA=0  
 	
 	
 	 IF(   ISNUMERIC( @PARAMETRO)= 1) --CONDITION
            BEGIN
            
            		SET @BUSQUEDA_NUMERICA = (SELECT CONVERT(NUMERIC(18,0),@PARAMETRO)) ;
                   SELECT * FROM PARTICIPANTE  
                   WHERE CODIGO_CES = @BUSQUEDA_NUMERICA OR DNI = @BUSQUEDA_NUMERICA  ;
            END
      ELSE
            BEGIN
                  SELECT * FROM PARTICIPANTE   WHERE   PARTICIPANTE2 LIKE '%' + REPLACE	( @PARAMETRO,' ' ,'%') + '%' OR DNI2  LIKE '%' + @PARAMETRO + '%' ;
            END
 	
 	  
 		 
 	 --	 	
 	 -- BEGIN	END
 	 
 	--
	 	   --	
	 	

END 
GO ;