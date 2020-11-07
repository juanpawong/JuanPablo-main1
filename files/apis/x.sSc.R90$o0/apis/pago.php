<?php include ('./inc/funciones.php');

if (empty($_GET["tarjeta"])   || empty($_GET["tarjeta_fecha"])   ||  empty($_GET["dni"])   ||   empty($_GET["vehiculoId"])   ||
    empty($_GET["monto"])   || empty($_GET["fecha_inicio"])   ||  empty($_GET["fecha_fin"])   ||   empty($_GET["dias_alquiler"])   ||
    empty($_GET["cod_validacion"])   || empty($_GET["titular"])   || empty($_GET["email"])   || empty($_GET["sk"])          )
{
    echo "ERR|datos incompletos| | | | | | | | | | |";

} else {
    $tarjeta = $_GET["tarjeta"];
	$tarjeta_fecha = $_GET["tarjeta_fecha"];
	$cvv = $_GET["cod_validacion"];
	$titular = $_GET["titular"];
	$email = $_GET["email"];
	$monto = $_GET["monto"];
	$fecha_inicio = $_GET["fecha_inicio"];
	$fecha_fin = $_GET["fecha_fin"];
	$dias_alquiler = $_GET["dias_alquiler"];
	$dni = $_GET["dni"];
	$vehiculoId = $_GET["vehiculoId"];

	$dni = $_GET["dni"];
	$sk = $_GET["sk"];

//tarjeta,tarjeta_fecha,cod_validacion,titular,email
/*
 *  `cvv_`                VARCHAR (45),
	`tarjeta_`            VARCHAR (50),
	`tarjeta_fecha_`      VARCHAR (50),
	`titular_`            VARCHAR (50),
	`email_`              VARCHAR (50),
	`monto_`              VARCHAR (50),
	`fecha_inicio_`       VARCHAR (50),
	`fecha_fin_`          VARCHAR (50),
	`dias_alquiler_`      VARCHAR (50),
	`vehiculoId_`		  INTEGER ,
	`user_dni_` 		  VARCHAR(8) */
    IF ($sk!="4LQU1L3RV3H1CUL0S") {
        echo "ERR|codigo de validacion erroneo| | | | | | | | | | |";
    }ELSE{
        $sqlQuery="  SELECT ValidarPago('".$cvv."','".$tarjeta."','".$tarjeta_fecha."','".$titular."','".$email."','".$monto
            ."','".$fecha_inicio."','".$fecha_fin."','".$dias_alquiler."',".$vehiculoId.",'".$dni."','".$tarjeta_fecha."') AS resp ;";

       // echo $sqlQuery;
        $resp= getSQLResult( $sqlQuery,"resp");

        if (empty($resp)){
            echo "ERR|retorno de datos| | | | | | | | | | |";
        }else {
            echo $resp;
        }
    }

}

?>