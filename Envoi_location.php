<?php
/**
 * Created by PhpStorm.
 * User: user
 * Date: 01/12/2016
 * Time: 20:55
 */
// Create connection
require_once "bdConnection.php";
$sql = "INSERT INTO `location` (`idLocation`, `lat`, `lng`, `name`, `type`)
VALUES (NULL, '".filter_input(INPUT_GET, 'latitude', FILTER_SANITIZE_ENCODED)."',
'".filter_input(INPUT_GET, 'longitude', FILTER_SANITIZE_ENCODED)."',
'".filter_input(INPUT_GET, 'name', FILTER_SANITIZE_ENCODED)."',
'".filter_input(INPUT_GET, 'type', FILTER_SANITIZE_ENCODED)."')";
echo $sql;
$res = $mysqli->query($sql);
?>