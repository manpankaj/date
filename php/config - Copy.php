<?php
$serverName ="DESKTOP-HP2018";
$usr="sa";
$pwd="pkv123";
$db="count";
$connectionInfo = array("UID" => $usr, "PWD" => $pwd, "Database" => $db, "CharacterSet" => "UTF-8");
$conn = sqlsrv_connect($serverName, $connectionInfo);
if( $conn === false ) {
    die( print_r( sqlsrv_errors(), true));
}
?>
  