/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50509
Source Host           : localhost:3306
Source Database       : infomanage

Target Server Type    : MYSQL
Target Server Version : 50509
File Encoding         : 65001

Date: 2018-04-28 11:21:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_category`
-- ----------------------------
DROP TABLE IF EXISTS `t_category`;
CREATE TABLE `t_category` (
  `category_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(11) NOT NULL,
  `category_name` varchar(64) NOT NULL COMMENT '种类名称',
  `category_desc` varchar(128) DEFAULT NULL COMMENT '种类描述',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modify` datetime DEFAULT NULL COMMENT '修改时间',
  `status` int(2) NOT NULL COMMENT '状态',
  PRIMARY KEY (`category_id`),
  UNIQUE KEY `un_name` (`category_name`,`user_id`,`status`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_category
-- ----------------------------
INSERT INTO `t_category` VALUES ('1', '0', '鹿晗', '123456', '2018-04-21 14:28:05', '2018-04-23 22:00:23', '0');
INSERT INTO `t_category` VALUES ('2', '0', '音乐', '订单', '2018-04-21 15:29:49', '2018-04-21 15:57:49', '1');
INSERT INTO `t_category` VALUES ('3', '1', '摇滚', '1', '2018-04-25 11:57:47', '2018-04-25 12:09:22', '0');
INSERT INTO `t_category` VALUES ('5', '1', '摇滚', 'bar', '2018-04-25 12:11:12', '2018-04-25 16:06:54', '1');
INSERT INTO `t_category` VALUES ('6', '1', '轻音乐', '睡眠', '2018-04-25 16:06:45', null, '1');
INSERT INTO `t_category` VALUES ('7', '1', '情歌对唱', '男女朋友', '2018-04-25 16:07:14', null, '1');
INSERT INTO `t_category` VALUES ('8', '2', 'bobo', '唱跳组合', '2018-04-26 17:59:43', null, '1');
INSERT INTO `t_category` VALUES ('9', '2', '谢霆锋', '香港英皇艺人', '2018-04-26 18:00:01', null, '1');
INSERT INTO `t_category` VALUES ('10', '2', '张飞', '三国战将', '2018-04-26 18:00:16', null, '1');
INSERT INTO `t_category` VALUES ('11', '2', '周星驰', '香港喜剧电影艺人', '2018-04-26 18:00:36', null, '1');
INSERT INTO `t_category` VALUES ('12', '1', '战争电影', '二战', '2018-04-27 11:40:45', null, '1');
INSERT INTO `t_category` VALUES ('13', '1', 'java', '开发语言', '2018-04-27 11:41:02', null, '1');
INSERT INTO `t_category` VALUES ('14', '6', '黄晓明的', '歌声发士d大夫', '2018-04-27 17:34:21', '2018-04-28 11:18:08', '0');
INSERT INTO `t_category` VALUES ('15', '6', '陈楚生', '歌手', '2018-04-27 17:34:45', null, '1');
INSERT INTO `t_category` VALUES ('16', '6', '孙俪', '演员', '2018-04-27 17:34:56', null, '1');
INSERT INTO `t_category` VALUES ('17', '6', '的的', '', '2018-04-27 17:35:21', '2018-04-27 17:39:42', '0');
INSERT INTO `t_category` VALUES ('18', '6', '的', '1', '2018-04-27 17:39:21', '2018-04-27 17:53:20', '0');
INSERT INTO `t_category` VALUES ('19', '6', '的的2', '12', '2018-04-27 17:39:33', '2018-04-27 17:39:36', '0');
INSERT INTO `t_category` VALUES ('20', '5', '2121', '2121', '2018-04-27 18:00:53', '2018-04-28 11:14:14', '0');
INSERT INTO `t_category` VALUES ('21', '5', 'java发多少', '开发语言', '2018-04-28 11:12:33', '2018-04-28 11:14:08', '1');
INSERT INTO `t_category` VALUES ('22', '5', 'php', '开发语言', '2018-04-28 11:14:36', null, '1');

-- ----------------------------
-- Table structure for `t_comment`
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment` (
  `comment_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(11) NOT NULL,
  `source_id` bigint(11) NOT NULL,
  `content` varchar(256) NOT NULL COMMENT '评论内容',
  `gmt_create` datetime NOT NULL,
  `gmt_modify` datetime DEFAULT NULL,
  `status` int(2) NOT NULL,
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_comment
-- ----------------------------
INSERT INTO `t_comment` VALUES ('1', '1', '7', '士大夫', '2018-04-27 16:19:32', '2018-04-27 16:55:51', '0');
INSERT INTO `t_comment` VALUES ('2', '1', '7', '还是很不错的嘛', '2018-04-27 16:31:55', null, '1');
INSERT INTO `t_comment` VALUES ('3', '1', '7', '手动阀手动阀', '2018-04-27 16:35:32', null, '1');
INSERT INTO `t_comment` VALUES ('4', '1', '7', '单元格格式-对其-靠左，或者直接在快捷菜单栏里面，有左对齐的快捷方式的', '2018-04-27 16:36:33', null, '1');
INSERT INTO `t_comment` VALUES ('5', '5', '7', '哦哟不催哦', '2018-04-27 17:11:18', null, '1');
INSERT INTO `t_comment` VALUES ('6', '6', '7', '听了和好看呢', '2018-04-27 17:31:33', null, '1');
INSERT INTO `t_comment` VALUES ('7', '6', '13', '还不错哦，大家看看点评一下下', '2018-04-27 17:41:49', null, '1');
INSERT INTO `t_comment` VALUES ('8', '5', '14', 'dsf ', '2018-04-27 18:05:57', '2018-04-28 11:18:22', '0');
INSERT INTO `t_comment` VALUES ('9', '5', '14', '哈哈哈好地方惊', '2018-04-28 11:10:13', null, '1');
INSERT INTO `t_comment` VALUES ('10', '5', '13', '发送到发送到', '2018-04-28 11:10:29', null, '1');
INSERT INTO `t_comment` VALUES ('11', '5', '15', 'fsd发斯蒂芬房东电风扇', '2018-04-28 11:15:49', '2018-04-28 11:15:55', '0');
INSERT INTO `t_comment` VALUES ('12', '5', '15', '不不不没法过水电费', '2018-04-28 11:16:05', null, '1');

-- ----------------------------
-- Table structure for `t_source`
-- ----------------------------
DROP TABLE IF EXISTS `t_source`;
CREATE TABLE `t_source` (
  `source_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(11) NOT NULL,
  `source_name` varchar(128) NOT NULL COMMENT '资源名称',
  `source_desc` varchar(256) DEFAULT NULL COMMENT '资源描述',
  `source_url` varchar(256) NOT NULL COMMENT '资源地址',
  `source_type` int(2) NOT NULL COMMENT '资源类型（1-图片，2-音频，3-视频，4-文件，5-其他）',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modify` datetime DEFAULT NULL COMMENT '修改时间',
  `status` int(2) NOT NULL COMMENT '状态',
  PRIMARY KEY (`source_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_source
-- ----------------------------
INSERT INTO `t_source` VALUES ('1', '1', '33', '21', '', '1', '2018-04-26 16:27:44', null, '1');
INSERT INTO `t_source` VALUES ('2', '1', '12', '12', 'f49f76dea6c929831247d6e391b713c6.jpg', '1', '2018-04-26 16:31:11', null, '1');
INSERT INTO `t_source` VALUES ('3', '1', '21', '3', '4b93651badb8f544c90e73b6c70adaf3.jpg', '1', '2018-04-26 16:34:49', '2018-04-26 17:35:10', '0');
INSERT INTO `t_source` VALUES ('4', '1', '大海', '到时', 'upload/4b93651badb8f544c90e73b6c70adaf3.jpg', '1', '2018-04-26 16:43:53', null, '1');
INSERT INTO `t_source` VALUES ('5', '1', '平原', '啦啦啦', 'upload/20140809094936532.jpg', '1', '2018-04-26 16:46:10', null, '1');
INSERT INTO `t_source` VALUES ('6', '1', '王者', '1', 'upload/6b8e18dcc60184ed69d35cc8f929a139.jpg', '2', '2018-04-26 16:47:13', '2018-04-28 11:19:22', '0');
INSERT INTO `t_source` VALUES ('7', '1', '天空', '1221', 'upload/fd5859af64d0a486101b41dd4b12a3db.png', '1', '2018-04-26 16:48:27', null, '1');
INSERT INTO `t_source` VALUES ('8', '1', 'fds', 'fsd', 'upload/0b8241e44001d2ace65d5850a2fe7a65.jpg', '1', '2018-04-26 17:05:53', '2018-04-26 17:35:16', '0');
INSERT INTO `t_source` VALUES ('9', '1', '胡夏', '歌手', 'upload/4b93651badb8f544c90e73b6c70adaf3.jpg', '1', '2018-04-27 10:20:22', null, '1');
INSERT INTO `t_source` VALUES ('10', '1', '谢霆锋', '歌手', 'upload/ 郭富城 - 就是孙悟空.mp3', '2', '2018-04-27 10:21:23', null, '1');
INSERT INTO `t_source` VALUES ('11', '1', '信息', ' 大声道', 'f49f76dea6c929831247d6e391b713c6.jpg', '1', '2018-04-27 10:29:50', null, '1');
INSERT INTO `t_source` VALUES ('12', '1', 'java开发', '资料', '阿里巴巴Java开发手册(终极版).pdf', '4', '2018-04-27 11:41:55', null, '1');
INSERT INTO `t_source` VALUES ('13', '6', '艾普特', '发射点发射点', 'c3a5599ecaae712dd536cb916646a6e9.jpg', '1', '2018-04-27 17:41:27', null, '1');
INSERT INTO `t_source` VALUES ('14', '5', 'fdsfsd', 'fsd', '0b8241e44001d2ace65d5850a2fe7a65.jpg', '1', '2018-04-27 18:03:28', null, '1');
INSERT INTO `t_source` VALUES ('15', '5', 'java开发', '221', 'fd5859af64d0a486101b41dd4b12a3db.png', '1', '2018-04-28 11:15:33', null, '1');

-- ----------------------------
-- Table structure for `t_source_category`
-- ----------------------------
DROP TABLE IF EXISTS `t_source_category`;
CREATE TABLE `t_source_category` (
  `source_category_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `source_id` bigint(11) NOT NULL COMMENT '资源id',
  `category_id` bigint(11) NOT NULL COMMENT '种类id',
  `user_id` bigint(11) NOT NULL,
  `gmt_create` datetime NOT NULL COMMENT '修改时间',
  `gmt_modify` datetime DEFAULT NULL COMMENT '创建时间',
  `status` int(2) NOT NULL COMMENT '状态',
  PRIMARY KEY (`source_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_source_category
-- ----------------------------
INSERT INTO `t_source_category` VALUES ('1', '3', '5', '1', '2018-04-26 16:34:49', null, '1');
INSERT INTO `t_source_category` VALUES ('2', '4', '5', '1', '2018-04-26 16:43:53', null, '1');
INSERT INTO `t_source_category` VALUES ('3', '5', '5', '1', '2018-04-26 16:46:10', null, '1');
INSERT INTO `t_source_category` VALUES ('4', '6', '7', '1', '2018-04-26 16:47:13', null, '1');
INSERT INTO `t_source_category` VALUES ('5', '7', '6', '1', '2018-04-26 16:48:27', null, '1');
INSERT INTO `t_source_category` VALUES ('6', '8', '6', '1', '2018-04-26 17:05:53', null, '1');
INSERT INTO `t_source_category` VALUES ('7', '9', '5', '1', '2018-04-27 10:20:22', null, '1');
INSERT INTO `t_source_category` VALUES ('8', '10', '5', '1', '2018-04-27 10:21:23', null, '1');
INSERT INTO `t_source_category` VALUES ('9', '11', '5', '1', '2018-04-27 10:29:50', null, '1');
INSERT INTO `t_source_category` VALUES ('10', '12', '13', '1', '2018-04-27 11:41:55', null, '1');
INSERT INTO `t_source_category` VALUES ('11', '13', '14', '6', '2018-04-27 17:41:27', null, '1');
INSERT INTO `t_source_category` VALUES ('12', '14', '20', '5', '2018-04-27 18:03:28', null, '1');
INSERT INTO `t_source_category` VALUES ('13', '15', '21', '5', '2018-04-28 11:15:33', null, '1');

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `user_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(128) NOT NULL COMMENT '用户姓名',
  `user_passwd` varchar(128) NOT NULL COMMENT '用户密码',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modify` datetime DEFAULT NULL COMMENT '修改时间',
  `status` int(2) NOT NULL COMMENT '状态',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', '12', '1', '2018-04-25 11:45:44', '2018-04-27 17:47:30', '0');
INSERT INTO `t_user` VALUES ('4', 'admin', '13', '2018-04-27 14:46:06', null, '1');
INSERT INTO `t_user` VALUES ('5', 'zyt', '1', '2018-04-27 17:06:31', null, '1');
INSERT INTO `t_user` VALUES ('6', 'nicholas', '1', '2018-04-27 17:31:01', null, '1');
INSERT INTO `t_user` VALUES ('7', 'test', 'test', '2018-04-28 11:20:17', null, '1');
