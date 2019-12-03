DROP TABLE shop.user;
DROP TABLE shop.BANK;
DROP TABLE shop.SNS;
DROP TABLE shop.sns_type;
DROP TABLE shop.bank_conn;
DROP TABLE shop.adress_conn;
DROP TABLE shop.address;


CREATE TABLE IF NOT EXISTS `shop`.`user` (
  `id` INT NOT NULL,
  `username` VARCHAR(16) NOT NULL,
  `email` VARCHAR(255) NULL,
  `password` VARCHAR(32) NOT NULL,
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `delete_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `nickname` VARCHAR(25) NULL,
  `sns_type` INT NULL,user
  PRIMARY KEY (`id`));

CREATE TABLE IF NOT EXISTS `shop`.`sns` (
  `sns_type` INT NOT NULL,
  `sns_name` VARCHAR(45) NULL,
  `token` VARCHAR(255) NULL,
  PRIMARY KEY (`sns_type`),
  CONSTRAINT `user_sns_conn`
    FOREIGN KEY (`sns_type`)
    REFERENCES `shop`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS `shop`.`sns_type` (
  `sns_code` INT NOT NULL,
  `sns_type` INT NOT NULL,
  PRIMARY KEY (`sns_code`));

CREATE TABLE IF NOT EXISTS `shop`.`bank` (
  `bank_id` INT NOT NULL,
  `balance` INT ZEROFILL UNSIGNED NOT NULL DEFAULT 0,
  PRIMARY KEY (`bank_id`));

CREATE TABLE IF NOT EXISTS `shop`.`bank_conn` (
  `user_id` INT NULL,
  `bank_id` INT NULL,
  CONSTRAINT `bank_conn_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `shop`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `bank_conn_bank`
    FOREIGN KEY (`bank_id`)
    REFERENCES `shop`.`bank` (`bank_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS `shop`.`address` (
  `address_id` INT NOT NULL,
  `zip_code` INT NULL,
  `country` VARCHAR(20) NULL,
  `address` VARCHAR(255) NULL,
  `address_detail` VARCHAR(45) NULL,
  PRIMARY KEY (`address_id`));

CREATE TABLE IF NOT EXISTS `shop`.`adress_conn` (
  `user_id` INT NOT NULL,
  `address_id` INT NULL,
  CONSTRAINT `user_address_conn`
    FOREIGN KEY (`user_id`)
    REFERENCES `shop`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `address_adress_conn`
    FOREIGN KEY (`address_id`)
    REFERENCES `shop`.`address` (`address_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
