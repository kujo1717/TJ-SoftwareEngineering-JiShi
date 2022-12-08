/*
 Navicat Premium Data Transfer

 Source Server         : localMySQL
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : jishi

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 08/12/2022 10:24:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity`  (
  `activity_id` bigint(0) NOT NULL,
  `title_name` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `detail_text` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `summary` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `start_time` datetime(0) NULL DEFAULT NULL,
  `end_time` datetime(0) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `hit_num` int(0) NULL DEFAULT NULL,
  `limit_capacity` tinyint(1) NULL DEFAULT NULL,
  `capacity` int(0) NULL DEFAULT NULL,
  `participant_num` int(0) NULL DEFAULT NULL,
  `repeat_interval` int(0) NULL DEFAULT NULL,
  `creator_id` bigint(0) NULL DEFAULT NULL,
  `state` tinyint(0) NULL DEFAULT 0,
  `mark` double NULL DEFAULT NULL,
  `applicant_num` int(0) NULL DEFAULT NULL,
  `address_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `address_formatted` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `longitude` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `latitude` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `images` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `is_remind` tinyint(0) NOT NULL,
  PRIMARY KEY (`activity_id`) USING BTREE,
  INDEX `creator_id`(`creator_id`) USING BTREE,
  CONSTRAINT `creator_id` FOREIGN KEY (`creator_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activity
-- ----------------------------

-- ----------------------------
-- Table structure for activity_apply
-- ----------------------------
DROP TABLE IF EXISTS `activity_apply`;
CREATE TABLE `activity_apply`  (
  `activity_id` bigint(0) NOT NULL,
  `user_id` bigint(0) NOT NULL,
  `apply_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`activity_id`, `user_id`) USING BTREE,
  INDEX `activity_apply_user_id`(`user_id`) USING BTREE,
  CONSTRAINT `activity_apply_activity_id` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`activity_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `activity_apply_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activity_apply
-- ----------------------------

-- ----------------------------
-- Table structure for activity_mark
-- ----------------------------
DROP TABLE IF EXISTS `activity_mark`;
CREATE TABLE `activity_mark`  (
  `activity_id` bigint(0) NOT NULL,
  `user_id` bigint(0) NOT NULL,
  `mark` int(0) NULL DEFAULT NULL,
  `mark_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`activity_id`, `user_id`) USING BTREE,
  INDEX `activity_mark_user_id`(`user_id`) USING BTREE,
  CONSTRAINT `activity_mark_activity_id` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`activity_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `activity_mark_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activity_mark
-- ----------------------------

-- ----------------------------
-- Table structure for activity_participate
-- ----------------------------
DROP TABLE IF EXISTS `activity_participate`;
CREATE TABLE `activity_participate`  (
  `activity_id` bigint(0) NOT NULL,
  `user_id` bigint(0) NOT NULL,
  PRIMARY KEY (`activity_id`, `user_id`) USING BTREE,
  INDEX `activity_participate_user_id`(`user_id`) USING BTREE,
  CONSTRAINT `activity_participate_activity_id` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`activity_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `activity_participate_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activity_participate
-- ----------------------------

-- ----------------------------
-- Table structure for activity_tag
-- ----------------------------
DROP TABLE IF EXISTS `activity_tag`;
CREATE TABLE `activity_tag`  (
  `activity_id` bigint(0) NOT NULL,
  `tag_id` bigint(0) NOT NULL,
  PRIMARY KEY (`activity_id`, `tag_id`) USING BTREE,
  INDEX `activity_tag_tag_id`(`tag_id`) USING BTREE,
  CONSTRAINT `activity_tag_activity_id` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`activity_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `activity_tag_tag_id` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`tag_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activity_tag
-- ----------------------------

-- ----------------------------
-- Table structure for classification
-- ----------------------------
DROP TABLE IF EXISTS `classification`;
CREATE TABLE `classification`  (
  `classification_title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `user_id` bigint(0) NOT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`classification_title`, `user_id`) USING BTREE,
  INDEX `classification_title`(`classification_title`) USING BTREE,
  INDEX `classification_ibfk_1`(`user_id`) USING BTREE,
  CONSTRAINT `classification_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of classification
-- ----------------------------

-- ----------------------------
-- Table structure for item_notice
-- ----------------------------
DROP TABLE IF EXISTS `item_notice`;
CREATE TABLE `item_notice`  (
  `item_notice_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '个人事项通知',
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '所属用户ID',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `content` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `item_id` bigint(0) NULL DEFAULT NULL COMMENT '事项ID',
  `status` tinyint(0) NULL DEFAULT NULL COMMENT '1为已读，0为未读',
  PRIMARY KEY (`item_notice_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of item_notice
-- ----------------------------

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `message_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '消息ID',
  `activity_id` bigint(0) NULL DEFAULT NULL COMMENT '活动ID',
  `sender_id` bigint(0) NULL DEFAULT NULL,
  `status` tinyint(0) NULL DEFAULT NULL COMMENT '消息状态:0为going,1为succeed,2为failed',
  `type` tinyint(0) NULL DEFAULT NULL COMMENT '消息类型:0为text，1为image，2为file',
  `send_time` bigint(0) NULL DEFAULT NULL COMMENT '消息发送时间，13位毫秒',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '消息内容，如果为image则为URL地址',
  `file_id` bigint(0) NULL DEFAULT NULL COMMENT '消息对应的文件ID',
  PRIMARY KEY (`message_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 54 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message
-- ----------------------------

-- ----------------------------
-- Table structure for message_board
-- ----------------------------
DROP TABLE IF EXISTS `message_board`;
CREATE TABLE `message_board`  (
  `message_board_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '留言板信息ID',
  `activity_id` bigint(0) NULL DEFAULT NULL COMMENT '留言所属活动ID',
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '留言用户ID',
  `content` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '留言内容',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '留言时间',
  PRIMARY KEY (`message_board_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message_board
-- ----------------------------

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `notice_id` bigint(0) NOT NULL AUTO_INCREMENT,
  `activity_id` bigint(0) NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `content` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `type` tinyint(0) NULL DEFAULT NULL COMMENT '1为系统通知，2为活动通知\r\n',
  PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notice
-- ----------------------------

-- ----------------------------
-- Table structure for poll
-- ----------------------------
DROP TABLE IF EXISTS `poll`;
CREATE TABLE `poll`  (
  `poll_id` bigint(0) NOT NULL,
  `activity_id` bigint(0) NOT NULL,
  `topic_text` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `deadline` datetime(0) NULL DEFAULT NULL,
  `multiple_choice` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY (`poll_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of poll
-- ----------------------------

-- ----------------------------
-- Table structure for relativetask
-- ----------------------------
DROP TABLE IF EXISTS `relativetask`;
CREATE TABLE `relativetask`  (
  `task_id` bigint(0) NOT NULL,
  `relative_task_id` bigint(0) NOT NULL,
  PRIMARY KEY (`task_id`, `relative_task_id`) USING BTREE,
  INDEX `relative_task_id`(`relative_task_id`) USING BTREE,
  CONSTRAINT `relativetask_ibfk_1` FOREIGN KEY (`task_id`) REFERENCES `task` (`task_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `relativetask_ibfk_2` FOREIGN KEY (`relative_task_id`) REFERENCES `task` (`task_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of relativetask
-- ----------------------------

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag`  (
  `tag_id` bigint(0) NOT NULL,
  `tag_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `num` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`tag_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tag
-- ----------------------------
INSERT INTO `tag` VALUES (1, '学习', 0);
INSERT INTO `tag` VALUES (2, '工作', 0);
INSERT INTO `tag` VALUES (3, '户外', 0);
INSERT INTO `tag` VALUES (4, '室内', 0);
INSERT INTO `tag` VALUES (5, '运动', 0);
INSERT INTO `tag` VALUES (6, '娱乐', 0);
INSERT INTO `tag` VALUES (7, '游戏', 0);
INSERT INTO `tag` VALUES (8, '开黑', 0);
INSERT INTO `tag` VALUES (9, '技术', 0);
INSERT INTO `tag` VALUES (10, '艺术', 0);
INSERT INTO `tag` VALUES (11, '专业', 0);

-- ----------------------------
-- Table structure for task
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task`  (
  `task_id` bigint(0) NOT NULL,
  `task_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `task_detail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `task_state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `classification_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `priority` tinyint(0) NULL DEFAULT NULL,
  `start_time` datetime(0) NULL DEFAULT NULL,
  `end_time` datetime(0) NULL DEFAULT NULL,
  `is_parent` tinyint(0) NULL DEFAULT NULL,
  `is_in_dustbin` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user_id` bigint(0) NULL DEFAULT NULL,
  `real_finish_time` datetime(0) NULL DEFAULT NULL,
  `tag` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `is_remind` tinyint(0) NOT NULL,
  PRIMARY KEY (`task_id`) USING BTREE,
  INDEX `task_ibfk_1`(`classification_title`) USING BTREE,
  INDEX `task_ibfk_2`(`user_id`) USING BTREE,
  CONSTRAINT `task_ibfk_1` FOREIGN KEY (`classification_title`) REFERENCES `classification` (`classification_title`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `task_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of task
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主码',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '名字',
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码,MD5加密',
  `email` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '邮箱',
  `age` int(0) NULL DEFAULT NULL,
  `introduce` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `avatar` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'http://42.192.45.95:8081/api/user/avatar.jpg' COMMENT '头像',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 111 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------

-- ----------------------------
-- Table structure for user_forget_request
-- ----------------------------
DROP TABLE IF EXISTS `user_forget_request`;
CREATE TABLE `user_forget_request`  (
  `id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '邮箱',
  `code` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '验证码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_forget_request
-- ----------------------------

-- ----------------------------
-- Table structure for user_notice
-- ----------------------------
DROP TABLE IF EXISTS `user_notice`;
CREATE TABLE `user_notice`  (
  `user_id` bigint(0) NOT NULL,
  `notice_id` bigint(0) NOT NULL,
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '通知是否已读：1为已读，0为未读',
  PRIMARY KEY (`user_id`, `notice_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_notice
-- ----------------------------

-- ----------------------------
-- Table structure for vote
-- ----------------------------
DROP TABLE IF EXISTS `vote`;
CREATE TABLE `vote`  (
  `user_id` bigint(0) NOT NULL,
  `option_id` bigint(0) NOT NULL,
  PRIMARY KEY (`user_id`, `option_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vote
-- ----------------------------

-- ----------------------------
-- Table structure for vote_option
-- ----------------------------
DROP TABLE IF EXISTS `vote_option`;
CREATE TABLE `vote_option`  (
  `option_id` bigint(0) NOT NULL,
  `option_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `poll_id` bigint(0) NULL DEFAULT NULL,
  `vote_num` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`option_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vote_option
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
