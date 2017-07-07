/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : demo

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2017-07-07 21:24:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `data`
-- ----------------------------
DROP TABLE IF EXISTS `data`;
CREATE TABLE `data` (
  `id` int(12) NOT NULL,
  `date` varchar(14) NOT NULL,
  `user_behavior` int(1) NOT NULL,
  `show_name` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of data
-- ----------------------------
INSERT INTO `data` VALUES ('3', '20170702112632', '1', 'cctv1');
INSERT INTO `data` VALUES ('4', '20170702112630', '0', 'null');
INSERT INTO `data` VALUES ('5', '20170702112632', '1', 'hunanweishi');
INSERT INTO `data` VALUES ('6', '20170702112732', '1', 'jiangsuweishi');
INSERT INTO `data` VALUES ('7', '20170802112742', '1', '江苏卫视');
