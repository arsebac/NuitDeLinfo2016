CREATE TABLE `Nuit`.`articles`
(
  `idArticle`  SMALLINT      NOT NULL AUTO_INCREMENT,
  `visibility` TINYINT       NOT NULL,
  `title`      VARCHAR(500)  NOT NULL,
  `message`    VARCHAR(2000) NOT NULL,
  `idAuthor`   SMALLINT      NOT NULL
)
  ENGINE = MyISAM;
