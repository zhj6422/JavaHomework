CREATE DATABASE `e_commerce_db`;

USE `e_commerce_db`;

CREATE TABLE IF NOT EXISTS `user` (
    `id` int NOT NULL AUTO_INCREMENT,
    `name` varchar(16) NOT NULL,
    `password` varchar(16) NOT NULL,
    `phone` varchar(16) NOT NULL,
    `money` int NOT NULL,
    `create_time` int NOT NULL,
    `update_time` int NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `commodity` (
    `id` int NOT NULL AUTO_INCREMENT,
    `name` varchar(16) NOT NULL,
    `price` int NOT NULL,
    `stock` int NOT NULL,
    `create_time` int NOT NULL,
    `update_time` int NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS `order` (
    `id` int NOT NULL AUTO_INCREMENT,
    `user_id` int NOT NULL,
    `commodities` varchar(21812) NOT NULL,
    `order_price` int NOT NULL,
    `status` int NOT NULL,
    `create_time` int NOT NULL,
    `update_time` int NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `user`(name, password, phone, money, create_time, update_time)  VALUES ("zhj6422", "zhj6422", "12010302930", 9999, unix_timestamp(now()) , unix_timestamp(now()));

INSERT INTO `commodity` (name, price, stock, create_time, update_time) VALUES ("书包", 100, 900, unix_timestamp(now()) , unix_timestamp(now()));

INSERT INTO `order`(`user_id`, commodities, order_price, status, create_time, update_time) VALUES (1, "书包", 100, 1, unix_timestamp(now()) , unix_timestamp(now()));

INSERT INTO `order`(`user_id`, commodities, order_price, status, create_time, update_time) VALUES (1, "书包", 200, 2, unix_timestamp(now()) , unix_timestamp(now()));

DELETE FROM `order` WHERE id = 1;

UPDATE `order` SET order_price = 200 WHERE id = 2;

SELECT * FROM `user`;

SELECT * FROM `commodity`;

SELECT * FROM `order`;