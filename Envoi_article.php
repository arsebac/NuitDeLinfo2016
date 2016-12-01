<?php
/**
 * Created by PhpStorm.
 * User: user
 * Date: 01/12/2016
 * Time: 20:55
 */

$mysqli = new mysqli('127.0.0.1', 'root', '', 'Nuit');
if ($mysqli->connect_errno) {
    echo "Unable to open database";
    echo "Errno: " . $mysqli->connect_errno . "\n";
}
$sql ="INSERT INTO `articles` (`idArticle`, `visibility`, `title`, `message`, `idAuthor`,`date`,`location`
) VALUES (NULL, '1', '".filter_input(INPUT_POST,'titre',FILTER_SANITIZE_ENCODED)."', '".filter_input(INPUT_POST,'message',FILTER_SANITIZE_ENCODED)."', '1',NULL,'Afrique'
);";
echo $sql;
    $res = $mysqli->query($sql);
?>