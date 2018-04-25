/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : infomanage

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2018-04-25 07:57:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_category`
-- ----------------------------
DROP TABLE IF EXISTS `t_category`;
CREATE TABLE `t_category` (
  `category_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(64) NOT NULL COMMENT '种类名称',
  `category_desc` varchar(128) DEFAULT NULL COMMENT '种类描述',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modify` datetime DEFAULT NULL COMMENT '修改时间',
  `status` int(2) NOT NULL COMMENT '状态',
  PRIMARY KEY (`category_id`),
  UNIQUE KEY `un_name` (`category_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_category
-- ----------------------------
INSERT INTO `t_category` VALUES ('1', '鹿晗', '123456', '2018-04-21 14:28:05', '2018-04-23 22:00:23', '0');
INSERT INTO `t_category` VALUES ('2', '音乐', '订单', '2018-04-21 15:29:49', '2018-04-21 15:57:49', '1');

-- ----------------------------
-- Table structure for `t_source`
-- ----------------------------
DROP TABLE IF EXISTS `t_source`;
CREATE TABLE `t_source` (
  `source_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `source_name` varchar(128) NOT NULL COMMENT '资源名称',
  `source_desc` varchar(256) DEFAULT NULL COMMENT '资源描述',
  `source_url` varchar(256) NOT NULL COMMENT '资源地址',
  `source_type` int(2) NOT NULL COMMENT '资源类型（1-图片，2-音频，3-视频）',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modify` datetime DEFAULT NULL COMMENT '修改时间',
  `status` int(2) NOT NULL COMMENT '状态',
  PRIMARY KEY (`source_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_source
-- ----------------------------

-- ----------------------------
-- Table structure for `t_source_category`
-- ----------------------------
DROP TABLE IF EXISTS `t_source_category`;
CREATE TABLE `t_source_category` (
  `source_category_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `source_id` bigint(11) NOT NULL COMMENT '资源id',
  `category_id` bigint(11) NOT NULL COMMENT '种类id',
  `gmt_create` datetime NOT NULL COMMENT '修改时间',
  `gmt_modify` datetime DEFAULT NULL COMMENT '创建时间',
  `status` int(2) NOT NULL COMMENT '状态',
  PRIMARY KEY (`source_category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_source_category
-- ----------------------------
