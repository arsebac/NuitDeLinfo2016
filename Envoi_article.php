<?php
/**
 * Created by PhpStorm.
 * User: user
 * Date: 01/12/2016
 * Time: 20:55
 */

    $res = $mysqli->query("INSERT INTO `articles` (`idArticle`, `visibility`, `title`, `message`, `idAuthor`,`date`,`location`
) VALUES (NULL, '1', ".filter_input(INPUT_POST,'titre',FILTER_SANITIZE_ENCODED).", ".filter_input(INPUT_POST,'message',FILTER_SANITIZE_ENCODED).", '1',NULL,'Afrique'
);");
?>