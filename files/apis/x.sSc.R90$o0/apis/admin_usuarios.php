<?php use function MongoDB\BSON\toJSON;
header('Content-Type: application/json');
include ('./inc/funciones.php');

if ( empty($_GET["sk"])       )
{
    echo "ERR|datos incompletos| | | | | | | | | | |";

} else {
    $sk="";
     if (!empty($_GET["sk"])) {
         $sk = $_GET["sk"];
     }

    IF ($sk!="4LQU1L3RV3H1CUL0S") {
        echo "ERR|codigo de validacion erroneo| | | | | | | | | | |";
    }ELSE{
        $conn = new mysqli($GLOBALS["server"],$GLOBALS["uid"],$GLOBALS["Password"],$GLOBALS["dataBase"]);
        $vehiculos = array(); //creamos un array
        if ($conn->connect_error) {
            die("Connection failed: " . $conn->connect_error);
            $ErrorCompleto= $mysqli->connect_errno ."|" . $mysqli->connect_error  ;
            $json_string = json_encode('{"resultado":"ERR","mensaje":"error al conectar | '.$ErrorCompleto.'"}'  );
            echo $json_string;
            exit;
        }
        $sql="select * from tb_usuarios  order by user_ape, user_nom asc";
        $conn->set_charset("utf8");
        $result2 = $conn->query($sql);

        $resultado ="";

        if ($result2->num_rows > 0) {
            $rowcount2=mysqli_num_rows($result2);
            while($row = $result2->fetch_assoc()) {

               if( $resultado !="") {
                   $resultado= $resultado.",".  json_encode($row, JSON_UNESCAPED_UNICODE );
               }else{
                   $resultado=   json_encode($row, JSON_UNESCAPED_UNICODE );
               }
            }

            echo  '{"resultado":"OK","mensaje":"success","data":['. $resultado ."] }";
        }else {

            $json_string = json_encode('{"resultado":"ERR","mensaje":"no hay data que devolver"}');
            echo $json_string;

        }
/*
 {"resultado":"OK","descrip":"success",
"data":[{"idvehiculos":"4","marca":"BMW","modelo":"BMW i-3","img":"bmw_i.jpg","descripcion":"El BMW i3 es un modelo ","prec_alquile":"180.00","status":"1"},
{"idvehiculos":"1","marca":"Ferrari","modelo":"Ferrari 812","img":"ferrari_812.jpg","descripcion":"El Ferrari 812 Superfast","prec_alquile":"100.00","status":"1"},
{"idvehiculos":"3","marca":"Ford ","modelo":"Ford Kuga PHEV 2020","img":"exteriores-(17).jpg","descripcion":"Ford Kuga PHEV es un vehiculo eficiente ","prec_alquile":"120.00","status":"1"}
]}
 * */



        $conn->close();
    }

}

?>