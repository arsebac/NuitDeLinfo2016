<?php
require_once "bdConnection.php";
$sql = "DELETE FROM `location` WHERE `location`.`idLocation` = ".filter_input(INPUT_GET, 'idLocation', FILTER_SANITIZE_ENCODED);
echo $sql;
$res = $mysqli->query($sql);
?>