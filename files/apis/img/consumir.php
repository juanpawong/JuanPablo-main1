     <?php 
    // echo   (file_get_contents("https://proyectoint01.000webhostapp.com/img/tests.php?img=Ferrari_812.fw.png")) ;
//echo "<br>";
    // echo base64_encode (file_get_contents("https://proyectoint01.000webhostapp.com/img/tests.php?img=Ferrari_812.fw.png")) ;
     echo base64_encode (file_get_contents("https://proyectoint01.000webhostapp.com/img/tests.php?img=".$_GET["img"])) ;
    
    ?> 