
CREATE TABLE `location` (
  `idLocation` smallint(6) NOT NULL,
  `lat` decimal(10,0) NOT NULL,
  `lng` decimal(10,0) NOT NULL,
  `name`    VARCHAR(2000) NOT NULL,
  `type`    VARCHAR(2000) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

ALTER TABLE `location`
  ADD PRIMARY KEY (`idLocation`);
ALTER TABLE `location`
  MODIFY `idLocation` smallint(6) NOT NULL AUTO_INCREMENT;
INSERT INTO `location` (`idLocation`, `lat`, `lng`, `name`, `desc`) VALUES (NULL, '120', '1240', 'Triangle des bermudes', "Un endroit inexistant avec plein d\'imigrés");