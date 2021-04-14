CREATE DATABASE IF NOT EXISTS dagacube;

ALTER DATABASE dagacube
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;

GRANT ALL PRIVILEGES ON dagacube.* TO pc@localhost IDENTIFIED BY 'pc';

USE dagacube;

CREATE TABLE IF NOT EXISTS players (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(30),
  password VARCHAR(30),
  last_modified_date DATE ,
  create_date DATE,
  balance_amount INT(4) ,
 INDEX(username)
  ) engine=InnoDB;

CREATE TABLE IF NOT EXISTS transactions (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  transaction_type VARCHAR(30),
  player_id INT(4) UNSIGNED NOT NULL,
  transaction_id varchar(45),
  last_modified_date DATE ,
  create_date DATE,
  INDEX(transaction_type),
  FOREIGN KEY (player_id) REFERENCES players(id)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS promotions (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  last_modified_date DATE ,
  create_date DATE,
  is_active INT(4) ,
  available_wagers INT(4)
) engine=InnoDB


INSERT INTO `dagacube`.`players`
(
`username`,
`password`,
`last_modified_date`,
`create_date`,
`balance_amount`)
VALUES
(
"boom",
"boom",
NOW(),
NOW(),6);


INSERT INTO `dagacube`.`promotions`
(
`last_modified_date`,
`create_date`,
`is_active`,
`available_wagers`)
VALUES
(
now(),
now(),
true,
6);

INSERT INTO `dagacube`.`transactions`
(
`transaction_type`,
`player_id`,
`last_modified_date`,
`create_date`)
VALUES(
"WINNING",
1,
now(),
now());


