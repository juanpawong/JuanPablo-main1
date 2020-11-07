<?php
	include ('funciones.php');
	header('Content-Type: text/html;charset=utf-8');

	function probarConexion() {
		$mysqli = new mysqli($GLOBALS["server"],$GLOBALS["uid"],$GLOBALS["Password"],$GLOBALS["dataBase"]);
		if ($mysqli->connect_errno){
			printf("Connect failed: %s\n", $mysqli->connect_error);
			exit();
		} else {
			echo "conectado";
		}

	}
	 
	 	probarConexion();
	echo "<br>Total de usuarios registrados : ";
       echo getSQLResult( "select count(*) as cantidad from tb_usuarios","cantidad");
?>