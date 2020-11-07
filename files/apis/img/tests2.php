<?php
/**
 * tests.php - basic usage tests for mThumb.
 *
 * Images are from Wikimedia Commons Public Domain category.
 *
 * @created   11/12/14 10:26 PM
 * @author    Mindshare Studios, Inc.
 * @copyright Copyright (c) 2014
 * @link      http://www.mindsharelabs.com/documentation/
 *
 */
?>
 <!--
<p>
    
    <img src="./vehiculos/Ferrari_812.fw.png" alt="test jpg" /><br> 
</p>

<p>
	 <br /> <img src="mthumb.php?src=./vehiculos/Ferrari_812.fw.png&w=350&h=250" alt="test png" />
	 <br /> <img src="mthumb.php?src=/vehiculos/Ferrari_812.fw.png&w=350&h=250" alt="test png" />

<img src="mthumb.php?src=/vehiculos/<?php echo $_GET["img"]; ?>&w=350&h=250" alt="test png" />
-->   <?php
    
//    echo require_once("mthumb.php?src=/vehiculos/Ferrari_812.fw.png&w=350&h=250");
//  echo file_get_contents( "mthumb.php?src=/vehiculos/". $_GET["img"]);


  echo file_get_contents( "mthumb.php?src=https://media-exp1.licdn.com/dms/image/C560BAQHMnA03XDdf3w/company-logo_200_200/0?e=2159024400&v=beta&t=C7KMOtnrJwGrMXmgIk2u1B8a7VRfgxMwXng9cdP9kZk". $_GET["img"]);
  echo "<br>";



  echo file_get_contents(  "https://media-exp1.licdn.com/dms/image/C560BAQHMnA03XDdf3w/company-logo_200_200/0?e=2159024400&v=beta&t=C7KMOtnrJwGrMXmgIk2u1B8a7VRfgxMwXng9cdP9kZk");
    ?>
</p><!--
<p>
	// test gif<br /> <img src="/mthumb.php?src=/tests/medium.gif&w=150&h=160" alt="test gif" />
</p>
<p>
	// test external<br /> <img src="/mthumb.php?src=https://farm8.staticflickr.com/7559/15590345589_8e79f8744d_k.jpg&w=500&h=300" alt="test external jpg" /><br />
	// test external<br />
	<img src="/mthumb.php?src=https://i.imgur.com/g5kZOtT.png&w=500&h=300" alt="test external jpg" />
</p>
<p>
	// test large file size<br /> <img src="/mthumb.php?src=/tests/large.jpg&w=100&h=100" alt="test jpg" />
</p>
<p>
	// test tilde<br /> <img src="/mthumb.php?src=/tests/small.jpg&w=100&h=100" alt="test jpg" />
</p>
-->