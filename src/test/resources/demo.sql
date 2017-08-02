/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : demo

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2017-08-02 17:16:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `data`
-- ----------------------------
DROP TABLE IF EXISTS `data`;
CREATE TABLE `data` (
  `user_id` int(20) NOT NULL,
  `date` char(14) NOT NULL,
  `user_behavior` int(1) NOT NULL,
  `show_name` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of data
-- ----------------------------

-- ----------------------------
-- Table structure for `onlinenumber`
-- ----------------------------
DROP TABLE IF EXISTS `onlinenumber`;
CREATE TABLE `onlinenumber` (
  `date` char(14) NOT NULL,
  `user_number` int(20) NOT NULL,
  PRIMARY KEY (`date`),
  UNIQUE KEY `dateIndex` (`date`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of onlinenumber
-- ----------------------------

-- ----------------------------
-- Table structure for `tvshowsonlineviewer`
-- ----------------------------
DROP TABLE IF EXISTS `tvshowsonlineviewer`;
CREATE TABLE `tvshowsonlineviewer` (
  `date` char(14) NOT NULL,
  `show_name` int(10) NOT NULL,
  `count` int(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of tvshowsonlineviewer
-- ----------------------------

-- ----------------------------
-- Procedure structure for `test`
-- ----------------------------
DROP PROCEDURE IF EXISTS `test`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `test`()
BEGIN
	#Routine body goes here...
	SELECT date, user_behavior, show_name, COUNT(id) as id FROM `data` GROUP BY show_name;
END
;;
DELIMITER ;
