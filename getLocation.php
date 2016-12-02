<?php
/**
 * Created by PhpStorm.
 * User: user
 * Date: 02/12/2016
 * Time: 00:08
 */

require_once "bdConnection.php";
    $arrayResult = array();
    $res = $mysqli->query("SELECT `lat`,`lng`,`name`,`type` FROM `location`");

echo '{"pts":[';
for ($row_no = 0; $row_no < $res->num_rows; $row_no++) {
    $res->data_seek($row_no);
    $row = $res->fetch_assoc();
    echo json_encode(array("lat"=>$row["lat"],"lng"=>$row["lng"],"name"=>$row["name"],"type"=>$row["type"]));
    if($row_no - 1  != $res->num_rows){
        echo ",";
    }
}
echo "]}";
