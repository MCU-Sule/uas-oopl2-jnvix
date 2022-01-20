-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema oopl20211
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `oopl20211` ;

-- -----------------------------------------------------
-- Schema oopl20211
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `oopl20211` DEFAULT CHARACTER SET utf8 ;
USE `oopl20211` ;

-- -----------------------------------------------------
-- Table `oopl20211`.`fe_member`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oopl20211`.`fe_member` (
  `citizenId` VARCHAR(14) NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `address` VARCHAR(400) NOT NULL,
  `phone` VARCHAR(13) NOT NULL,
  `email` VARCHAR(30) NULL,
  `username` VARCHAR(50) NOT NULL,
  `birthdate` DATE NOT NULL,
  PRIMARY KEY (`citizenId`))
ENGINE = InnoDB
COMMENT = '								';


-- -----------------------------------------------------
-- Table `oopl20211`.`fe_transaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oopl20211`.`fe_transaction` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `trans_date` DATE NOT NULL,
  `nominal` BIGINT NOT NULL,
  `member_citizenId` VARCHAR(14) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_transaction_member1_idx` (`member_citizenId` ASC) VISIBLE,
  CONSTRAINT `fk_transaction_member1`
    FOREIGN KEY (`member_citizenId`)
    REFERENCES `oopl20211`.`fe_member` (`citizenId`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oopl20211`.`fe_point`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oopl20211`.`fe_point` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `value` INT NOT NULL,
  `member_citizenId` VARCHAR(14) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_point_member1_idx` (`member_citizenId` ASC) VISIBLE,
  CONSTRAINT `fk_point_member1`
    FOREIGN KEY (`member_citizenId`)
    REFERENCES `oopl20211`.`fe_member` (`citizenId`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `oopl20211`.`fe_member`
-- -----------------------------------------------------
START TRANSACTION;
USE `oopl20211`;
INSERT INTO `oopl20211`.`fe_member` (`citizenId`, `name`, `address`, `phone`, `email`, `username`, `birthdate`) VALUES ('12345678900001', 'John Doe', 'Dummy Address 1', '081212121212', 'john.doe@email.com', 'john.doe', '1975-12-11');
INSERT INTO `oopl20211`.`fe_member` (`citizenId`, `name`, `address`, `phone`, `email`, `username`, `birthdate`) VALUES ('12345678900002', 'Susan Bones', 'Dummy Address 2', '081212121213', 'susanbones@email.com', 'sbones', '1978-05-03');
INSERT INTO `oopl20211`.`fe_member` (`citizenId`, `name`, `address`, `phone`, `email`, `username`, `birthdate`) VALUES ('12345678900003', 'Richard Max', 'Dummy Address 3', '081212121214', 'maxedr@email.com', 'rmax', '1977-08-30');
INSERT INTO `oopl20211`.`fe_member` (`citizenId`, `name`, `address`, `phone`, `email`, `username`, `birthdate`) VALUES ('12345678900004', 'Rendy Miles', 'Dummy Address 4', '081212121215', 'rendymiles@email.com', 'rendy.miles', '1985-01-08');

COMMIT;


-- -----------------------------------------------------
-- Data for table `oopl20211`.`fe_transaction`
-- -----------------------------------------------------
START TRANSACTION;
USE `oopl20211`;
INSERT INTO `oopl20211`.`fe_transaction` (`id`, `trans_date`, `nominal`, `member_citizenId`) VALUES (DEFAULT, '2022-01-05', 535900, '12345678900001');
INSERT INTO `oopl20211`.`fe_transaction` (`id`, `trans_date`, `nominal`, `member_citizenId`) VALUES (DEFAULT, '2022-01-05', 475800, '12345678900002');
INSERT INTO `oopl20211`.`fe_transaction` (`id`, `trans_date`, `nominal`, `member_citizenId`) VALUES (DEFAULT, '2022-01-11', 135000, '12345678900001');

COMMIT;


-- -----------------------------------------------------
-- Data for table `oopl20211`.`fe_point`
-- -----------------------------------------------------
START TRANSACTION;
USE `oopl20211`;
INSERT INTO `oopl20211`.`fe_point` (`id`, `value`, `member_citizenId`) VALUES (DEFAULT, 5, '12345678900001');
INSERT INTO `oopl20211`.`fe_point` (`id`, `value`, `member_citizenId`) VALUES (DEFAULT, 4, '12345678900002');
INSERT INTO `oopl20211`.`fe_point` (`id`, `value`, `member_citizenId`) VALUES (DEFAULT, 1, '12345678900001');

COMMIT;

