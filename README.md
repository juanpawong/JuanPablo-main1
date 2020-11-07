# E-Commerce para Alquiler de Automoviles

App para realizar alquiler de vehiculos

---

### Descripcion 

El proyecto consiste en un app para realizar el alquiler de vehiculos.

### Requerimientos :

```
## **[Usuario]**
* Crear usuarios
* Ver lista de vehiculos disponibles para alquiler
* **Alquilar vehiculos**

## **[Administrador]**
* CRUD - lista de vehiculos
* Visualizar vehiculos alquilados

```

---
 
### Servicios 

Proveedor 	: 	[000WEBHOST](https://000webhost.com/) 

Website 	: 	[Proyectoint01](https://proyectoint01.000webhostapp.com) 

Dashboard 	:	https://es.000webhost.com/members/website/proyectoint01/dashboard

Apis 		: 	https://proyectoint01.000webhostapp.com/x.sSc.R90$o0/

sk 			:	4LQU1L3RV3H1CUL0S (**requisito para la validacion de las apis**)

```
Prueba de conexion : 
https://proyectoint01.000webhostapp.com/x.sSc.R90$o0/apis/inc/test.php
Method 	: GET
Params 	: null
Returns : estado / Total de usuarios registrados 
```

```
Login : login.php
https://proyectoint01.000webhostapp.com/x.sSc.R90$o0/apis/login.php
Method 	: GET
Params 	: doc=DNI , ps=PASSWORD, sk=SEGURE KEY
Returns : OK|ID|DNI|EXIST|NOMBRE_PRUEBA APE_PRUEBA // estado|codigo/error|nombre/error|si existe|Nombre Completo
```

```
Registro : register_v2.php
https://proyectoint01.000webhostapp.com/x.sSc.R90$o0/apis/registe_v2r.php?
Method 	: GET
Params 	: user_dni=DNI&user_nom=NOMBRES&user_ape=APELLIDOS&user_name=DNI&user_pass=PASSWORD
Returns : OK|4|a|EXIST|NOMBRE COMPLETO // estado|codigo/error|nombre/error|si es nuevo o ya existe|Nombre Completo
 //OK|4|DNI|EXIST| NOMBRE APELLIDO
 //OK|4|DNI|NEW| NOMBRE APELLIDO
```
   
```
Registro : vehiculos.php
https://proyectoint01.000webhostapp.com/x.sSc.R90$o0/apis/vehiculos.php?
Method 	: GET
Params 	: sk
Returns : Json // estado|codigo/error|nombre/error|si es nuevo o ya existe|Nombre Completo
JSON	:
			export interface Vehiculos {
				resultado: string;
				descrip:   string;
				data:      Datum[];
			}

			export interface Datum {
				idvehiculos:  string;
				marca:        string;
				modelo:       string;
				img:          string;
				descripcion:  string;
				prec_alquile: string;
				status:       string;
			}
```

```
Recuperar contrase?a : recuperar_ps.php
https://proyectoint01.000webhostapp.com/x.sSc.R90$o0/apis/recuperar_ps.php?
Method 	: GET
Params 	: doc=DNI&sk=SK 
Returns : OK|DESCRIPCION // estado| descripcion
  
```

### Pruebas de servicios 
```

* **Retorno de vehiculos : **


https://proyectoint01.000webhostapp.com/x.sSc.R90$o0/apis/vehiculos.php?sk=4LQU1L3RV3H1CUL0S


* **Recuperar contrase?a : **
 
https://proyectoint01.000webhostapp.com/x.sSc.R90$o0/apis/recuperar_ps.php?sk=4LQU1L3RV3H1CUL0S&doc=22222222

```

## Entorno

Android Studio 4.1
PHP 7.2

## Construido con ?
_Menciona las herramientas que utilizaste para crear tu proyecto_

* [Dropwizard](http://www.dropwizard.io/1.0.2/docs/) - El framework web usado
* [Maven](https://maven.apache.org/) - Manejador de dependencias
* [ROME](https://rometools.github.io/rome/) - Usado para generar RSS
 
## Versionado 

Usamos [SemVer](http://semver.org/) para el versionado. Para todas las versiones disponibles, mira los [tags en este repositorio](https://github.com/tu/proyecto/tags).

## Autor

_Menciona a todos aquellos que ayudaron a levantar el proyecto desde sus inicios_

* **Andrs Villanueva** - *Trabajo Inicial* - [villanuevand](https://github.com/villanuevand)
* **Fulanito Detal** - *Documentacin* - [fulanitodetal](#fulanito-de-tal)

Tambin puedes mirar la lista de todos los [contribuyentes](https://github.com/your/project/contributors) quenes han participado en este proyecto. 

 

## Informacion adicional

* Fuente de informaci¨®n de los vehiculos : [Motorgiga](https://motorgiga.com/marcas-de-coches)
 


---
 con  por [Villanuevand](https://github.com/Villanuevand) 
