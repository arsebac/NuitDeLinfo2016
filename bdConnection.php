<?php
$local = true;
if($local){

    $servername = "localhost";
    $username = "root";
    $password = "hackermen";
    $dbname = "Nuit";

}
$mysqli = new mysqli($servername, $username, $password, $dbname);
if ($mysqli->connect_errno) {
    echo "Unable to open database";
    echo "Errno: " . $mysqli->connect_errno . "\n";
}