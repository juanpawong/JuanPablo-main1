<?php include('./inc/funciones.php');

	$id_usuario=$_GET["id_usuario"];

	if($resultset=getSQLResultSet("SELECT * FROM `tb_usuario` WHERE id_usuario='$id_usuario'")){
		while($row=$resultset->fetch_array(MYSQLI_NUM)){
			echo json_encode($row);
		}
	}


?>