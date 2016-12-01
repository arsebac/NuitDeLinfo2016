CREATE TABLE `articles` (
  `idArticle`  SMALLINT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `visibility` TINYINT       NOT NULL,
  `title`      VARCHAR(500)  NOT NULL,
  `message`    VARCHAR(2000) NOT NULL,
  `idAuthor`   SMALLINT      NOT NULL,
  `date` datetime default CURRENT_TIMESTAMP,
  `location`    VARCHAR(2000) NOT NULL
)
  ENGINE = MyISAM;

-- Ajouter un article
INSERT INTO `articles` (`idArticle`, `visibility`, `title`, `message`, `idAuthor`,`date`,`location`
) VALUES (NULL, '1', 'Essai', 'Premier article', '1',NULL,'Afrique'
);