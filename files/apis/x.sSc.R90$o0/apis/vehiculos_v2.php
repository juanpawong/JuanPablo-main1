<?php use function MongoDB\BSON\toJSON;

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
        $sql="select * from vehiculos where status='1' order by marca,modelo asc";
        $conn->set_charset("utf8");
        $result2 = $conn->query($sql);

        $resultado ="";

        if ($result2->num_rows > 0) {
            $rowcount2=mysqli_num_rows($result2);
            while($row = $result2->fetch_assoc()) {
/*
                $idvehiculos        =       $row['idvehiculos'];
                $marca              =       $row['marca'];
                $logo              =        $row['marca'].".png";
                $modelo             =       $row['modelo'];
                $img                =       $row['img'];
                $descripcion        =       $row['descripcion'];
                $prec_alquile       =       $row['prec_alquile'];
                $status             =       $row['status'];

                $vehiculos[] = array('idvehiculos'=> $idvehiculos, 'marca'=> $marca, 'logo'=> $logo, 'modelo'=> $modelo,
                    'img'=> $img, 'descripcion'=> $descripcion, 'prec_alquile'=> $prec_alquile, 'status'=> $status);*/
              //  echo json_encode($row);

             //  echo      $row['idvehiculos']."|" .$row['marca']."|" .  $row['marca'].".png"."|" . $row['modelo']."|" .
              //     $row['img']."|" .   $row['descripcion']."|" .   $row['prec_alquile']."|" .  $row['status']."<br>" ;

                $aux ='{"idvehiculos":"'. $row['idvehiculos']. '","marca":"'.$row['marca']. '","modelo":"'.$row['modelo'].
                    '","img":"';

              //  $image=file_get_contents("https://proyectoint01.000webhostapp.com/img/tests.php?img=". $row['img']);
              $image=base64url_encode(file_get_contents("https://proyectoint01.000webhostapp.com/img/tests.php?img=". $row['img']));



                $aux =$aux. $image;


           //     $aux =$aux.
          //          base64_encode (  base64_encode (file_get_contents("https://proyectoint01.000webhostapp.com/img/tests.php?img=". $row['img']))) ;
           //     base64_encode_image (file_get_contents("https://proyectoint01.000webhostapp.com/img/tests.php?img=". $row['img']),'png') ;

//
                $aux =$aux.'","descripcion":"'. $row['descripcion'].
                    '","prec_alquile":"'. $row['prec_alquile']. '","status":"'. $row['status']. '"}';

               if( $resultado !="") {
                    //ok   $resultado= $resultado.",".  json_encode($row, JSON_UNESCAPED_UNICODE );
                    //idvehiculos, marca, modelo, img, descripcion, prec_alquile, status
                    //{"idvehiculos":"4","marca":"BMW","modelo":"BMW i-3","img":"bmw_i_1.fw.png",
                    //"descripcion":"El BMW i3 es un modelo electric....","prec_alquile":"180.00","status":"1"},

                    $resultado= $resultado.",".$aux;
                  // $resultado= $resultado.",".  json_encode($row, JSON_UNESCAPED_UNICODE );
               }else{
                 //  $resultado=   json_encode($row, JSON_UNESCAPED_UNICODE );
                   $resultado=  $aux;
               }
            }


            //Creamos el JSON
          //  $json_string = json_encode($vehiculos);

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

function base64url_encode($data) {
  return rtrim(strtr(base64_encode($data), '+/', '-_'), '=');
}

function base64url_decode($data) {
  return base64_decode(str_pad(strtr($data, '-_', '+/'), strlen($data) % 4, '=', STR_PAD_RIGHT));
}
?>
?>