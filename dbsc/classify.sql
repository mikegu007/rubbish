/*
Navicat MySQL Data Transfer

Source Server         : 49.234.39.19
Source Server Version : 50645
Source Host           : 49.234.39.19:3306
Source Database       : classify

Target Server Type    : MYSQL
Target Server Version : 50645
File Encoding         : 65001

Date: 2019-09-28 21:28:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tm_order
-- ----------------------------
DROP TABLE IF EXISTS `tm_order`;
CREATE TABLE `tm_order` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `tm_order` varchar(255) DEFAULT NULL,
  `address_id` bigint(20) DEFAULT NULL,
  `address_name` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `user_uuid` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `work_id` bigint(20) DEFAULT NULL,
  `work_uuid` varchar(255) DEFAULT NULL,
  `work_name` varchar(255) DEFAULT NULL,
  `order_status` tinyint(4) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `pay_time` datetime DEFAULT NULL,
  `pay_order` varchar(255) DEFAULT NULL,
  `pay_price` decimal(10,0) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `finish_time` datetime DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `is_del` bit(1) DEFAULT NULL,
  `longitude` varchar(255) DEFAULT NULL,
  `latitude` varchar(255) DEFAULT NULL,
  `out_trade_no` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of tm_order
-- ----------------------------

-- ----------------------------
-- Table structure for tm_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `tm_order_detail`;
CREATE TABLE `tm_order_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(255) DEFAULT NULL,
  `classify_type` tinyint(4) DEFAULT NULL,
  `classify_count` int(11) DEFAULT NULL,
  `classify_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of tm_order_detail
-- ----------------------------

-- ----------------------------
-- Table structure for tm_red_package
-- ----------------------------
DROP TABLE IF EXISTS `tm_red_package`;
CREATE TABLE `tm_red_package` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `package_name` varchar(255) DEFAULT NULL,
  `user_uuid` varchar(255) DEFAULT NULL,
  `expire_time` datetime DEFAULT NULL,
  `package_price` decimal(10,0) DEFAULT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `is_del` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of tm_red_package
-- ----------------------------

-- ----------------------------
-- Table structure for tm_user
-- ----------------------------
DROP TABLE IF EXISTS `tm_user`;
CREATE TABLE `tm_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uuid` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_sex` int(11) DEFAULT NULL,
  `user_image` varchar(255) DEFAULT NULL,
  `user_mobile` varchar(255) DEFAULT NULL,
  `is_del` bit(1) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `default_address_id` bigint(20) DEFAULT NULL,
  `open_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of tm_user
-- ----------------------------

-- ----------------------------
-- Table structure for tm_user_address
-- ----------------------------
DROP TABLE IF EXISTS `tm_user_address`;
CREATE TABLE `tm_user_address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_uuid` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `address_detail` varchar(255) DEFAULT NULL,
  `sign` tinyint(4) DEFAULT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `is_del` bit(1) DEFAULT NULL,
  `longitude` varchar(255) DEFAULT NULL,
  `latitude` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of tm_user_address
-- ----------------------------

-- ----------------------------
-- Table structure for tt_energy_generate
-- ----------------------------
DROP TABLE IF EXISTS `tt_energy_generate`;
CREATE TABLE `tt_energy_generate` (
  `id` bigint(20) NOT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `energy` bigint(20) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `is_del` int(11) DEFAULT NULL,
  `get_time` datetime DEFAULT NULL,
  `expire_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of tt_energy_generate
-- ----------------------------

-- ----------------------------
-- Table structure for tt_user_feedback
-- ----------------------------
DROP TABLE IF EXISTS `tt_user_feedback`;
CREATE TABLE `tt_user_feedback` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uuid` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `is_del` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `content` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of tt_user_feedback
-- ----------------------------
