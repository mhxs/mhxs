/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 80026
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 80026
File Encoding         : 65001

Date: 2021-08-01 21:00:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL COMMENT '购买人',
  `user_address_id` bigint NOT NULL COMMENT '送货地址',
  `receive_money` decimal(20,4) NOT NULL COMMENT '实收金额',
  `create_time` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` bigint NOT NULL COMMENT '创建人',
  `update_time` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最近修改时间',
  `update_by` bigint NOT NULL COMMENT '最近修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2307 DEFAULT CHARSET=utf8mb3 COMMENT='订单表';

-- ----------------------------
-- Table structure for tb_order_item
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_item`;
CREATE TABLE `tb_order_item` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `order_id` bigint NOT NULL COMMENT '订单id',
  `product_id` bigint NOT NULL,
  `num` int NOT NULL DEFAULT '0' COMMENT '商品数量',
  `receive_money` decimal(20,4) NOT NULL COMMENT '实收金额',
  `product_money` decimal(20,0) NOT NULL COMMENT '商品单价',
  `create_time` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` bigint NOT NULL COMMENT '创建人',
  `update_time` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最近修改时间',
  `update_by` bigint NOT NULL COMMENT '最近修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2307 DEFAULT CHARSET=utf8mb3 COMMENT='订单项表';

-- ----------------------------
-- Table structure for tb_product
-- ----------------------------
DROP TABLE IF EXISTS `tb_product`;
CREATE TABLE `tb_product` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `product_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名称',
  `product_price` decimal(20,2) NOT NULL COMMENT '商品价格',
  `product_desc` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品描述',
  `create_time` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` bigint NOT NULL COMMENT '创建人',
  `update_time` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最近修改时间',
  `update_by` bigint NOT NULL COMMENT '最近修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2307 DEFAULT CHARSET=utf8mb3 COMMENT='商品表';

-- ----------------------------
-- Table structure for tb_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_user`;
CREATE TABLE `tb_sys_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户账号',
  `pwd` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录密码',
  `realname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '真实姓名',
  `mobile` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号码',
  `create_time` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` bigint NOT NULL COMMENT '创建人',
  `update_time` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最近修改时间',
  `update_by` bigint NOT NULL COMMENT '最近修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2307 DEFAULT CHARSET=utf8mb3 COMMENT='用户表';

-- ----------------------------
-- Table structure for tb_sys_user_address
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_user_address`;
CREATE TABLE `tb_sys_user_address` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL COMMENT '用户id',
  `receive_username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收货人姓名',
  `receive_address` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收货人地址',
  `receive_mobile` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收货人手机号码',
  `create_time` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` bigint NOT NULL COMMENT '创建人',
  `update_time` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最近修改时间',
  `update_by` bigint NOT NULL COMMENT '最近修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2307 DEFAULT CHARSET=utf8mb3 COMMENT='用户收货地址表';

-- ----------------------------
-- Table structure for tb_user_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_order`;
CREATE TABLE `tb_user_order` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `realname` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2307 DEFAULT CHARSET=utf8mb3;
