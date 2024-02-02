-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema itog_project
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema itog_project
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `itog_project` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `itog_project` ;

-- -----------------------------------------------------
-- Table `itog_project`.`manufacturer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `itog_project`.`manufacturer` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(90) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_fvhf6l0xkf8hnay7lvwimnwu1` (`name` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `itog_project`.`board_role_playing_games`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `itog_project`.`board_role_playing_games` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `description` LONGTEXT NOT NULL,
  `name` VARCHAR(90) NOT NULL,
  `year_of_release` VARCHAR(255) NOT NULL,
  `manufacturer_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_b8ky4ngvtofe2x9tqn8j80717` (`name` ASC) VISIBLE,
  INDEX `FKrplam0xbvas49rb5lmpp5m9e5` (`manufacturer_id` ASC) VISIBLE,
  CONSTRAINT `FKrplam0xbvas49rb5lmpp5m9e5`
    FOREIGN KEY (`manufacturer_id`)
    REFERENCES `itog_project`.`manufacturer` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `itog_project`.`citadel_colour_paints`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `itog_project`.`citadel_colour_paints` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `description` LONGTEXT NOT NULL,
  `name` VARCHAR(90) NOT NULL,
  `volume` DOUBLE NOT NULL,
  `weight` DOUBLE NOT NULL,
  `year_of_release` VARCHAR(255) NOT NULL,
  `manufacturer_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_jokilsru7ik0pb5gj5rhc63ey` (`name` ASC) VISIBLE,
  INDEX `FKly9ha96561ts55g5wbp448ibq` (`manufacturer_id` ASC) VISIBLE,
  CONSTRAINT `FKly9ha96561ts55g5wbp448ibq`
    FOREIGN KEY (`manufacturer_id`)
    REFERENCES `itog_project`.`manufacturer` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `itog_project`.`collectible_card_games`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `itog_project`.`collectible_card_games` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `description` LONGTEXT NOT NULL,
  `name` VARCHAR(90) NOT NULL,
  `year_of_release` VARCHAR(255) NOT NULL,
  `manufacturer_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_mj73irbwla7a6if6pfjt7kh7j` (`name` ASC) VISIBLE,
  INDEX `FK58sktkyea353l44wksegxvshq` (`manufacturer_id` ASC) VISIBLE,
  CONSTRAINT `FK58sktkyea353l44wksegxvshq`
    FOREIGN KEY (`manufacturer_id`)
    REFERENCES `itog_project`.`manufacturer` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `itog_project`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `itog_project`.`role` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(90) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_8sewwnpamngi6b1dwaa88askk` (`name` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `itog_project`.`emoloyee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `itog_project`.`emoloyee` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(50) NOT NULL,
  `middle_name` VARCHAR(50) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `second_name` VARCHAR(50) NOT NULL,
  `username` VARCHAR(40) NOT NULL,
  `manufacturer_id` BIGINT NULL DEFAULT NULL,
  `role_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_6bixsuu9llsy0j9h29wpdqa6k` (`username` ASC) VISIBLE,
  INDEX `FKpcoo9a15gnem905gn8afek1dx` (`manufacturer_id` ASC) VISIBLE,
  INDEX `FK5hvn7v63gsukd7js42mslv6md` (`role_id` ASC) VISIBLE,
  CONSTRAINT `FK5hvn7v63gsukd7js42mslv6md`
    FOREIGN KEY (`role_id`)
    REFERENCES `itog_project`.`role` (`id`),
  CONSTRAINT `FKpcoo9a15gnem905gn8afek1dx`
    FOREIGN KEY (`manufacturer_id`)
    REFERENCES `itog_project`.`manufacturer` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `itog_project`.`table_games`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `itog_project`.`table_games` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `description` LONGTEXT NOT NULL,
  `name` VARCHAR(90) NOT NULL,
  `year_of_release` VARCHAR(255) NOT NULL,
  `manufacturer_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_qk0syddqueh41f6bld1g351xv` (`name` ASC) VISIBLE,
  INDEX `FKno604mglwxq7ddwdeo5hnhu3m` (`manufacturer_id` ASC) VISIBLE,
  CONSTRAINT `FKno604mglwxq7ddwdeo5hnhu3m`
    FOREIGN KEY (`manufacturer_id`)
    REFERENCES `itog_project`.`manufacturer` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `itog_project`.`wargames`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `itog_project`.`wargames` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `description` LONGTEXT NOT NULL,
  `name` VARCHAR(90) NOT NULL,
  `year_of_release` VARCHAR(255) NOT NULL,
  `manufacturer_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_69umpte9dn3qwytdaebm5gp6o` (`name` ASC) VISIBLE,
  INDEX `FKkjtfre8ykyb92i0cnmuqgdabb` (`manufacturer_id` ASC) VISIBLE,
  CONSTRAINT `FKkjtfre8ykyb92i0cnmuqgdabb`
    FOREIGN KEY (`manufacturer_id`)
    REFERENCES `itog_project`.`manufacturer` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
