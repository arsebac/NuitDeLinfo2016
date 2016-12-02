<?php
/**
 * Created by PhpStorm.
 * User: user
 * Date: 02/12/2016
 * Time: 00:08
 */
//require_once "bdConnection.php";

$servername = "localhost";
$username = "root";
$password = "";
$dbname = "nuit";

$mysqli = new mysqli($servername, $username, $password, $dbname);
if ($mysqli->connect_errno) {
    echo "Unable to open database";
    echo "Errno: " . $mysqli->connect_errno . "\n";
}
    $arrayResult = array();
    $res = $mysqli->query("SELECT `lat`,`lng`,`name`,`type` FROM `location`");


for ($row_no = 0; $row_no < $res->num_rows; $row_no++) {
    $res->data_seek($row_no);
    $row = $res->fetch_assoc();
    array_push($arrayResult, array("lat"=>$row["lat"],"lng"=>$row["lng"],"name"=>$row["name"],"type"=>$row["type"]));
}
$finalResult = array("pts" => $arrayResult);
echo json_encode($finalResult);
