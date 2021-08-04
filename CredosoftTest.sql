
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema CredosoftServer
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `CredosoftServer` ;
USE `CredosoftServer` ;

-- -----------------------------------------------------
-- Table `CredosoftServer`.`CarModel`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CredosoftServer`.`CarModel` (
  `ModelID` int NOT NULL,
  `ModelName` VARCHAR(255) NOT NULL,
  `ModelYear` VARCHAR(4) NOT NULL,
  `FeatureID` int NULL,
  PRIMARY KEY (`ModelID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CredosoftServer`.`CarPart`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CredosoftServer`.`CarPart`(
	`ID` int NOT NULL,
	`PartName` varchar(255) NOT NULL,
	`PartType` varchar(255) NOT NULL,
	`ModelID` int NULL,
	`FeatureID` int NULL,
	PRIMARY KEY (ID),
    CONSTRAINT FK_CarModel FOREIGN KEY (ModelID)
    REFERENCES CarModel(ModelID),
    CONSTRAINT FK_FeatureID FOREIGN KEY (FeatureID)
    REFERENCES Features(FeatureID)
);

-- -----------------------------------------------------
-- Table `CredosoftServer`.`CarPart`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CredosoftServer`.`Features`(
	`FeatureID` int,
	`FeatureName` varchar(255),
    `FeatureDescription` varchar(255),
	PRIMARY KEY (FeatureID)
);

-- -----------------------------------------------------
-- inventory summary view count of carparts type with their assigned carmodels
-- -----------------------------------------------------
CREATE VIEW `Summary` AS SELECT `PartType` ,COUNT(*) as Count, group_concat(DISTINCT ModelID) as `Linked Model IDs` 
FROM `CredosoftServer`.`CarPart` 
GROUP BY PartType 
ORDER BY Count DESC;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
