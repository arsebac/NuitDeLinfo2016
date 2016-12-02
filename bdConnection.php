<?php
/**
 * Created by PhpStorm.
 * User: user
 * Date: 02/12/2016
 * Time: 00:09
 */
$local = true;
if($local){

    $servername = "localhost";
    $username = "root";
    $password = "";
    $dbname = "nuit";

}else{

    $servername = "mysql.hostinger.fr";
    $username = "u414950692_nuit";
    $password = "hackermen1";
    $dbname = "u414950692_nuit";

}
$mysqli = new mysqli($servername, $username, $password, $dbname);
if ($mysqli->connect_errno) {
    echo "Unable to open database";
    echo "Errno: " . $mysqli->connect_errno . "\n";
}