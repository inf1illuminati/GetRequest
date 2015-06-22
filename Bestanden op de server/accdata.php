<?php
    //connectie met database
	$dBConnect = mysql_connect('127.0.0.1', 'robotarm', '$_Rbrm2015') or die(mysql_error());
    
	//selecteer database
    mysql_select_db("robotarm") or die(mysql_error());

	//data integers
	//sql query 
    $query = "SELECT * FROM robotarm_testJD.waarden";

	//voer query uit
    $resultaatMessage = @mysql_query($query, $dBConnect) OR DIE ("Selecteren lukt niet");

	//stop alle gegevens in een tabel
    echo "<table width='50%' border='1'>";
    echo "<tr><th>ID</th><th>X</th><th>Y</th><th>Z</th></tr>";

    //data uit database halen en weergeven
    while (($row = mysql_fetch_assoc($resultaatMessage)) !== FALSE)
    {
        $ID = $row['ID'];
        $x = $row['waardeX'];
        $y = $row['waardeY'];
        $z = $row['waardeZ'];

        echo    "<tr><td> $ID </td>";
        echo    "<td> $x </td>";
        echo    "<td>$y</td>";
        echo    "<td>$z</td></tr>";
    } 
    echo '</table>';
	
	//data strings
	//sql query 
	$query2 = "SELECT * FROM robotarm_testJD.stringtest";

	//query uitvoeren
    $resultaatMessage2 = @mysql_query($query2, $dBConnect) OR DIE ("Selecteren lukt niet");

	//stop gegevens in tabel
    echo "<table width='50%' border='1'>";
    echo "<tr><th>ID</th><th>X</th><th>Y</th><th>Z</th></tr>";

    //data uit database halen en weergeven
    while (($row2 = mysql_fetch_assoc($resultaatMessage2)) !== FALSE)
    {
        $strID = $row2['strID'];
        $strx = $row2['strX'];
        $stry = $row2['strY'];
        $strz = $row2['strZ'];

        echo    "<tr><td>$strID </td>";
        echo    "<td>$strx </td>";
        echo    "<td>$stry</td>";
        echo    "<td>$strz</td></tr>";
    } 
    echo '</table>';

	//laat alle resultaten los en sluit database
    mysql_free_result($resultaatMessage);
    mysql_close($dBConnect);
    
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Data uit accelerometer</title>
    </head>
    <body>
        <?php
            //Hier php
        ?>   
        <!--Hier HTML-->
    </body>
</html>