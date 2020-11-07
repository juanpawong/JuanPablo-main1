 DROP TABLE IF EXISTS proyectoint01_autos_db.vehiculos;

CREATE TABLE proyectoint01_autos_db.vehiculos
	(
	idvehiculos  INT NOT NULL auto_increment,
	marca        VARCHAR (45),
	modelo       VARCHAR (45),
	img          VARCHAR (250),
	descripcion  TEXT, 
	prec_alquile NUMERIC(18,2),
	status       CHAR (1) DEFAULT 1,
	
	
	PRIMARY KEY (idvehiculos)
	);


INSERT INTO proyectoint01_autos_db.vehiculos (marca, modelo, img, descripcion, prec_alquile, status)
VALUES ('Ferrari', 'Ferrari 812', 'ferrari_812.jpg', 'El Ferrari 812 Superfast es el Ferrari de serie m�s potente de la historia y es el tope de la gama Ferrari. Monta un motor V12 a 65� en posici�n delantero-central que desarrolla 800 CV a 8.500 rpm y un para m�ximo de 718 Nm, cifras incre�bles para un motor atmosf�rico (sin turbos) de 6,5 litros de cilindrada.', 100, '1');

INSERT INTO proyectoint01_autos_db.vehiculos (marca, modelo, img, descripcion, prec_alquile, status)
VALUES ('lexus', 'Lexus LS', 'lexus_ls_2013_600h_4396_1.jpg', 'El renovado LS muestra cierto parecido con el aclamado coup� LC que mencion�bamos anteriormente, aunque quiz� no tanto como a muchos les gustar�a. ', 150, '1');

INSERT INTO proyectoint01_autos_db.vehiculos (marca, modelo, img, descripcion, prec_alquile, status)
VALUES ('Ford ', 'Ford Kuga PHEV 2020', 'exteriores-(17).jpg', 'Ford Kuga PHEV es un veh�culo eficiente gracias a su sistema h�brido enchufable, que nos permitir� circular muchos d�as de manera exclusivamente el�ctrica gracias a su generosa autonom�a �cero emisiones�', 120, '1');

INSERT INTO proyectoint01_autos_db.vehiculos (marca, modelo, img, descripcion, prec_alquile, status)
VALUES ('BMW', 'BMW i-3', 'bmw_i.jpg', 'El BMW i3 es un modelo electrico que ofrece el equivalente a 170 CV de potencia maxima mediante un motor electrico ubicado en la zona posterior del vehiculo.', 180, '1');

