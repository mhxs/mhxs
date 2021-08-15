/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 80026
Source Host           : localhost:3306
Source Database       : test_02

Target Server Type    : MYSQL
Target Server Version : 80026
File Encoding         : 65001

Date: 2021-08-15 22:47:10
*/

SET FOREIGN_KEY_CHECKS=0;


-- ----------------------------
-- Table structure for t_order_0
-- ----------------------------
DROP TABLE IF EXISTS `t_order_0`;
CREATE TABLE `t_order_0` (
  `order_id` bigint NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for t_order_1
-- ----------------------------
DROP TABLE IF EXISTS `t_order_1`;
CREATE TABLE `t_order_1` (
  `order_id` bigint NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
