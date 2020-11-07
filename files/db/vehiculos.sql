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
VALUES ('Ferrari', 'Ferrari 812', 'ferrari_812.jpg', 'El Ferrari 812 Superfast es el Ferrari de serie más potente de la historia y es el tope de la gama Ferrari. Monta un motor V12 a 65º en posición delantero-central que desarrolla 800 CV a 8.500 rpm y un para máximo de 718 Nm, cifras increíbles para un motor atmosférico (sin turbos) de 6,5 litros de cilindrada.', 100, '1');

INSERT INTO proyectoint01_autos_db.vehiculos (marca, modelo, img, descripcion, prec_alquile, status)
VALUES ('lexus', 'Lexus LS', 'lexus_ls_2013_600h_4396_1.jpg', 'El renovado LS muestra cierto parecido con el aclamado coupé LC que mencionábamos anteriormente, aunque quizá no tanto como a muchos les gustaría. ', 150, '1');

INSERT INTO proyectoint01_autos_db.vehiculos (marca, modelo, img, descripcion, prec_alquile, status)
VALUES ('Ford ', 'Ford Kuga PHEV 2020', 'exteriores-(17).jpg', 'Ford Kuga PHEV es un vehículo eficiente gracias a su sistema híbrido enchufable, que nos permitirá circular muchos días de manera exclusivamente eléctrica gracias a su generosa autonomía ‘cero emisiones’', 120, '1');

INSERT INTO proyectoint01_autos_db.vehiculos (marca, modelo, img, descripcion, prec_alquile, status)
VALUES ('BMW', 'BMW i-3', 'bmw_i.jpg', 'El BMW i3 es un modelo electrico que ofrece el equivalente a 170 CV de potencia maxima mediante un motor electrico ubicado en la zona posterior del vehiculo.', 180, '1');

