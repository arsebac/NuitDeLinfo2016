<?php
/**
 * Created by PhpStorm.
 * User: user
 * Date: 01/12/2016
 * Time: 20:46
 */
 require_once "bdConnection.php";

getArticles($mysqli);
/**
 *
 * @param $mysqli mysqli connection
 */
function getArticles($mysqli){
    $arrayResult = array();
    $res = $mysqli->query("SELECT * FROM `articles` WHERE `idArticle` - 10 < ( SELECT MAX(`idArticle`)  GROUP BY `idArticle`)");

    for ($row_no = 0; $row_no < $res->num_rows; $row_no++) {
        $res->data_seek($row_no);
        $row = $res->fetch_assoc();
        array_push($arrayResult,$row);
}
    $finalResult = array("articles" => $arrayResult);
    echo json_encode($finalResult);
}