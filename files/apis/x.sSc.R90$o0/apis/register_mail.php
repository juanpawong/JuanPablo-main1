<?php include ('./inc/funciones.php');

if (empty($_GET["user_dni"])   || empty($_GET["user_nom"])   || empty($_GET["user_ape"])    || empty($_GET["user_pass"])   || empty($_GET["user_mail"])     )
{
    echo "err|datos incompletos| | | | | | | | | | |";

} else {
    $user_dni = $_GET["user_dni"];
	$user_nom = $_GET["user_nom"];
	$user_ape = $_GET["user_ape"];
    $user_mail = $_GET["user_mail"];

	$user_pass = $_GET["user_pass"];
/*
	ejecutarSQLCommand("INSERT INTO  `tb_usuario` (user_dni,user_nom,user_ape,user_name,user_pass) VALUES ('$user_dni','$user_nom','$user_ape','$user_name','$user_pass')
		ON DUPLICATE KEY UPDATE `user_dni`='$user_dni',`user_nom`='$user_nom',`user_ape`='$user_ape',
		`user_name`='$user_name',`user_pass`='$user_pass';");
*/
//SELECT  CrearVerificarUsuarios ('88888888','NOMBRE_PRUEBA','APE_PRUEBA','USER_NAME','123456')  ;
   $sqlQuery="SELECT  CrearVerificarUsuarios_v2 ('".$user_dni."','".$user_nom."','".$user_ape."','".$user_mail."','".$user_pass."') as resp   ;";
  // $sqlQuery="SELECT user_id  tb_usuarios where user_dni='".$user_dni."'   ;";
    $resp= getSQLResult( $sqlQuery,"resp");
  //  $resp= getSQLResult( $sqlQuery,"rest");

    if (empty($resp)){
        echo "ERR|retorno de datos| | | | | | | | | | |";
    }else {
        echo $resp;
    }
}

if($resultset=getSQLResultSet("SELECT * FROM `tb_usuario` WHERE id_usuario=1'")){
    while($row=$resultset->fetch_array(MYSQLI_NUM)){
        echo json_encode($row);
    }
}
?>