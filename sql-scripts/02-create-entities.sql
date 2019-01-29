CREATE DATABASE  IF NOT EXISTS `hb_student_tracker1`;
USE `hb_student_tracker1`;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `customer`;

CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
 `first_name` varchar(45) DEFAULT NULL,
 `last_name` varchar(45) DEFAULT NULL,
 `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `online_order`;

CREATE TABLE `online_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) DEFAULT NULL,
  `delivery_address` varchar(128) DEFAULT NULL,
  `order_date` datetime DEFAULT NULL,
  `payment_type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_CUSTOMER` FOREIGN KEY (`customer_id`)
  REFERENCES `customer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `cart`;

CREATE TABLE `cart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) DEFAULT NULL,
  `total_sum` decimal(9, 2) DEFAULT NULL,
  `cart_date` datetime DEFAULT NULL, 
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_CUSTOMER1` FOREIGN KEY (`customer_id`)
  REFERENCES `customer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION

) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `product_category`;

CREATE TABLE `product_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
 `category_name` varchar(128) DEFAULT NULL,
 `parent_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_category_id` int(11) DEFAULT NULL,
  `name` varchar(128) DEFAULT NULL,
  `description` varchar(256) DEFAULT NULL,
  `price` DECIMAL(11,2) DEFAULT NULL,
  `warehouse_stock_id` int(11) DEFAULT NULL,
  
  PRIMARY KEY (`id`),
  
  CONSTRAINT `FK_DETAIL1` FOREIGN KEY (`product_category_id`) REFERENCES `product_category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,

  CONSTRAINT `FK_DETAIL2` FOREIGN KEY (`warehouse_stock_id`) REFERENCES `warehouse_stock` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION

  ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
  

DROP TABLE IF EXISTS `warehouse_stock`;

CREATE TABLE `warehouse_stock` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_quantity` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `product_cart`;

CREATE TABLE `product_cart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL,
  `cart_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  
  CONSTRAINT `FK_PRODUCT_3`
  FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  CONSTRAINT `FK_CART1`
  FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `product_order`;

CREATE TABLE `product_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  
  CONSTRAINT `FK_PRODUCT_4`
  FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  CONSTRAINT `FK_ORDER`
  FOREIGN KEY (`order_id`) REFERENCES `online_order` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


SET FOREIGN_KEY_CHECKS = 1;