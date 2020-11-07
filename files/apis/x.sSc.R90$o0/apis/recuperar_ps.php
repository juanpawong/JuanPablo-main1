<?php include ('./inc/funciones.php');

if (empty($_GET["doc"])   ||   empty($_GET["sk"])       )
{
    echo "ERR|datos incompletos| | | | | | | | | | |";

} else {
    $doc = $_GET["doc"];
	 $sk = $_GET["sk"];



    IF ($sk!="4LQU1L3RV3H1CUL0S") {
        echo "ERR|codigo de validacion erroneo| | | | | | | | | | |";
    }ELSE{
        $sqlQuery="  SELECT ResetPassword('".$doc."' ) AS resp ;";

        $resp= getSQLResult( $sqlQuery,"resp");

        if (empty($resp)){
            echo "ERR|retorno de datos| | | | | | | | | | |";
        }else {
            echo $resp;
        }
    }

}

?>