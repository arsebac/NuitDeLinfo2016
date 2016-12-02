<?php
/**
 * Created by PhpStorm.
 * User: user
 * Date: 01/12/2016
 * Time: 20:55
 */
// Create connection
require_once "bdConnection.php";
$sql ="DELETE FROM `articles` WHERE `articles`.`idArticle` = ".filter_input(INPUT_GET, 'idArticle', FILTER_SANITIZE_ENCODED);
echo $sql;
$res = $mysqli->query($sql);
?>