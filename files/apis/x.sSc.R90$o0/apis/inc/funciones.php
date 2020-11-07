<?php
	include ('cn.php');
	header('Content-Type: text/html;charset=utf-8');
 
	function ejecutarSQLCommand($commando){
      
		$mysqli = new mysqli($GLOBALS["server"],$GLOBALS["uid"],$GLOBALS["Password"],$GLOBALS["dataBase"]);

		/*validar conexión*/
		if ($mysqli->connect_errno){
			printf("Connect failed: %s\n", $mysqli->connect_error);
			exit();
		}

		if($mysqli->multi_query($commando)){
			if($resultset = $mysqli->store_result()){
				while ($row=$resultset->fetch_array(MYSQLI_BOTH)) {
					# code...
				}
				$resultset->free();
			}

		}

	$mysqli->close();
	}

	function getSQLResultSet($commando){

		$mysqli = new mysqli($GLOBALS["server"],$GLOBALS["uid"],$GLOBALS["Password"],$GLOBALS["dataBase"]);
		/*valida conexiíon*/
		if ($mysqli->connect_errno){
			printf("Connect failed: %s\n", $mysqli->connect_error);
			exit();
		}
		if($mysqli->multi_query($commando)){
			return $mysqli->store_result();
		}
	$mysqli->close();
	}
    function getSQLResult($sql,$column){
        $resultado="";
        
		$conn = new mysqli($GLOBALS["server"],$GLOBALS["uid"],$GLOBALS["Password"],$GLOBALS["dataBase"]);

		if ($conn->connect_error) {
			die("Connection failed: " . $conn->connect_error);
			 return   "ERR|fallo de conexion|".$mysqli->connect_errno ."|" . $mysqli->connect_error . " | | | | |";

					exit;
		}
		 
		$conn->set_charset("utf8");
		$result2 = $conn->query($sql);
		 
		if ($result2->num_rows > 0) {
			$rowcount2=mysqli_num_rows($result2);
			  while($row2 = $result2->fetch_assoc()) {
			  
			  $resultado= $row2[$column];
			  }
		}else {
			return   "ERR|no hay resultados| | | | |";

            exit;
		}

 
 

        $conn->close();
        return  $resultado;
}
//	probarConexion();
?>