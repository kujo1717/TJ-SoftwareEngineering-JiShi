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

 Date: 07/12/2022 20:45:34
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
INSERT INTO `activity` VALUES (1600317207164858370, '123123', NULL, '123123', '2022-12-07 01:02:00', NULL, '2022-12-07 10:32:09', 5, 1, 2, 1, NULL, 1, 0, NULL, 0, '123123', '上海市浦东新区花木街道高科西路2599号', '121.545646', '31.191832', ':/activity/illus/1600317207164858370/2021-10-08 (2).png:/activity/illus/1600317207164858370/2021-10-08 (6).png', 0);
INSERT INTO `activity` VALUES (1600350112419180546, '222', NULL, '222', '2022-12-07 13:02:00', NULL, '2022-12-07 00:42:54', 2, 1, 2, 1, NULL, 1, 0, NULL, 0, '222', NULL, NULL, NULL, ':/activity/illus/1600350112419180546/2021-10-05.png', 2);

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
-- Table structure for activity_invite
-- ----------------------------
DROP TABLE IF EXISTS `activity_invite`;
CREATE TABLE `activity_invite`  (
  `activity_id` bigint(0) NOT NULL,
  `invitee_id` bigint(0) NOT NULL,
  `state` tinyint(1) NULL DEFAULT NULL,
  `invite_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`activity_id`, `invitee_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activity_invite
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
INSERT INTO `activity_participate` VALUES (1600317207164858370, 1);
INSERT INTO `activity_participate` VALUES (1600350112419180546, 1);

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
  `user_id` int(0) NOT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`classification_title`, `user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of classification
-- ----------------------------
INSERT INTO `classification` VALUES ('c1', 1, '2022-11-23 19:15:36');
INSERT INTO `classification` VALUES ('c2', 1, '2022-11-23 20:05:56');
INSERT INTO `classification` VALUES ('c3', 1, '2022-11-23 20:09:57');
INSERT INTO `classification` VALUES ('默认分组', 1, '2022-11-24 22:06:15');
INSERT INTO `classification` VALUES ('默认分组', 2, '2022-12-07 09:45:42');

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of item_notice
-- ----------------------------
INSERT INTO `item_notice` VALUES (1, 1, '事项开始提醒111', '您的事项要开始了', '2022-11-28 18:06:20', 1, 1);
INSERT INTO `item_notice` VALUES (2, 1, '父事项事项开始提醒', '该事项还有一小时就要开始了，请注意及时参加哦', '2022-12-07 12:40:35', 1597049239446257666, 0);
INSERT INTO `item_notice` VALUES (3, 1, '父事项事项开始提醒', '该事项还有十分钟就要开始了，请注意及时参加哦', '2022-12-07 12:49:00', 1597049239446257666, 0);
INSERT INTO `item_notice` VALUES (4, 1, '时间测试事项开始提醒', '该事项还有十分钟就要开始了，请注意及时参加哦', '2022-12-07 12:49:05', 1600351476016771074, 0);
INSERT INTO `item_notice` VALUES (5, 1, 'test事项开始提醒', '该事项还有十分钟就要开始了，请注意及时参加哦', '2022-12-07 12:49:40', 1600351795803090946, 0);

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
) ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES (1, 1599231060451835906, 1, 1, 0, 1566047865417, 'test', NULL);
INSERT INTO `message` VALUES (40, 1599384406513020929, 1, 1, 0, 1670159424087, '123', NULL);
INSERT INTO `message` VALUES (41, 1599384406513020929, 2, 1, 0, 1670159438437, '222', NULL);
INSERT INTO `message` VALUES (42, 1599384406513020929, 1, 1, 0, 1670159455175, '111\n\n', NULL);
INSERT INTO `message` VALUES (43, 1599996338710360066, 1, 1, 0, 1670334409307, '123', NULL);
INSERT INTO `message` VALUES (44, 1599996338710360066, 2, 1, 0, 1670334454611, 'yy2', NULL);
INSERT INTO `message` VALUES (45, 1599996338710360066, 2, 1, 0, 1670334479563, 'yy22\n\n', NULL);
INSERT INTO `message` VALUES (46, 1599996338710360066, 2, 1, 0, 1670334659363, '222', NULL);
INSERT INTO `message` VALUES (47, 1599996338710360066, 1, 1, 0, 1670334666779, '111\n\n', NULL);
INSERT INTO `message` VALUES (48, 1599996338710360066, 1, 1, 0, 1670334683707, '1', NULL);
INSERT INTO `message` VALUES (49, 1600130693369806850, 1, 1, 0, 1670335937229, '123', NULL);
INSERT INTO `message` VALUES (50, 1600130693369806850, 2, 1, 0, 1670335948611, '333', NULL);
INSERT INTO `message` VALUES (51, 1600130693369806850, 1, 1, 0, 1670338691923, '1', NULL);
INSERT INTO `message` VALUES (52, 1600130693369806850, 2, 1, 0, 1670338726707, '222', NULL);
INSERT INTO `message` VALUES (53, 1600130693369806850, 1, 1, 0, 1670338736491, '111', NULL);
INSERT INTO `message` VALUES (54, 1600303714969612290, 2, 1, 0, 1670378171041, 'yy2', NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message_board
-- ----------------------------
INSERT INTO `message_board` VALUES (1, 1599384406513020929, 1, '大家别忘记来参加活动', '2022-12-06 11:10:42');
INSERT INTO `message_board` VALUES (2, 1599384406513020929, 1, '时间为12月8日', '2022-12-06 11:12:27');
INSERT INTO `message_board` VALUES (3, 1599384406513020929, 1, '1', '2022-11-17 00:00:00');
INSERT INTO `message_board` VALUES (4, 1599384406513020929, 1, 'test', '2022-12-06 20:05:41');
INSERT INTO `message_board` VALUES (5, 1599384406513020929, 1, '111', '2022-12-06 21:07:35');
INSERT INTO `message_board` VALUES (6, 1599384406513020929, 1, 'qweqw\n', '2022-12-06 21:08:12');
INSERT INTO `message_board` VALUES (7, 1599384406513020929, 1, '111', '2022-12-06 21:09:28');
INSERT INTO `message_board` VALUES (8, 1599384406513020929, 1, 'qqqq', '2022-12-06 21:10:44');
INSERT INTO `message_board` VALUES (9, 1599384406513020929, 1, 'qqqq', '2022-12-06 21:10:54');
INSERT INTO `message_board` VALUES (10, 1599384406513020929, 1, 'wewew', '2022-12-06 21:11:02');
INSERT INTO `message_board` VALUES (11, 1599384406513020929, 1, 'qweqweq', '2022-12-06 21:11:23');
INSERT INTO `message_board` VALUES (12, 1599384406513020929, 1, 'asdadasd', '2022-12-06 21:11:44');
INSERT INTO `message_board` VALUES (13, 1599384406513020929, 1, 'wqeq', '2022-12-06 21:13:02');
INSERT INTO `message_board` VALUES (14, 1599384406513020929, 1, 'test\n', '2022-12-06 21:13:44');
INSERT INTO `message_board` VALUES (15, 1600130693369806850, 1, 'google check', '2022-12-06 22:12:57');
INSERT INTO `message_board` VALUES (16, 1600130693369806850, 2, '111', '2022-12-06 22:59:38');

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES (1, NULL, '您的账号被勒索了', 'All your data was backed up from your server. You need to email us at rambler+2vdlm@onionmail.org to recover your data. If you dont contact us we will reach the General Data Protection Regulation, GDPR,\r\nand notify them that you store user data in an open form that is not safe. Under the rules of the law, you face a heavy fine or arrest and your database dump will be deleted from our server forever!', '2022-11-28 11:09:22', 1);
INSERT INTO `notice` VALUES (2, 1, '活动开始提醒', '您的活动要开始了，请安排好您的时间', '2022-11-28 16:38:48', 2);
INSERT INTO `notice` VALUES (3, 1600317207164858370, '123123活动开始提醒', '该活动还有一小时就要开始了，请注意及时参加哦', '2022-12-07 12:39:10', 2);
INSERT INTO `notice` VALUES (4, 1600350112419180546, '222活动开始提醒', '该活动还有一小时就要开始了，请注意及时参加哦', '2022-12-07 12:47:35', 2);
INSERT INTO `notice` VALUES (5, 1600317207164858370, '123123活动开始提醒', '该活动还有十分钟就要开始了，请注意及时参加哦', '2022-12-07 12:51:00', 2);
INSERT INTO `notice` VALUES (6, 1600350112419180546, '222活动开始提醒', '该活动还有十分钟就要开始了，请注意及时参加哦', '2022-12-07 12:51:00', 2);

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
INSERT INTO `poll` VALUES (1599385924670713857, 1599384406513020929, '\"user_id\"', '2022-12-04 20:51:34', 0);
INSERT INTO `poll` VALUES (1599387527733702657, 1599384406513020929, '\"http://localhost:9528/#/activity/activityPage?id=1599384406513020929\"', '2022-12-04 20:57:56', 0);
INSERT INTO `poll` VALUES (1599962268185919489, 1599956939733733377, '\"GetImgUrl\"', '2022-12-06 11:01:45', 0);
INSERT INTO `poll` VALUES (1599962979116253186, 1599962780151054337, '\"POLL1\"', '2022-12-06 11:04:35', 0);
INSERT INTO `poll` VALUES (1600304111083876353, 1600303714969612290, '\"activity_id\"', '2022-12-07 09:40:07', 0);
INSERT INTO `poll` VALUES (1600307925945970690, 1600303714969612290, '\"poll\"', '2022-12-07 09:55:16', 0);

-- ----------------------------
-- Table structure for relativetask
-- ----------------------------
DROP TABLE IF EXISTS `relativetask`;
CREATE TABLE `relativetask`  (
  `task_id` bigint(0) NOT NULL,
  `relative_task_id` bigint(0) NOT NULL,
  PRIMARY KEY (`task_id`, `relative_task_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of relativetask
-- ----------------------------
INSERT INTO `relativetask` VALUES (1597049239446257666, 1597115538444771330);
INSERT INTO `relativetask` VALUES (1597049239446257666, 1597116493529649153);

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
  PRIMARY KEY (`task_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of task
-- ----------------------------
INSERT INTO `task` VALUES (1597049239446257666, '父事项', '', '0', '', 1, '2022-12-07 13:00:00', '2022-11-17 00:00:00', 1, '0', 1, NULL, NULL, '2022-11-29 10:44:33', 2);
INSERT INTO `task` VALUES (1597793663394557953, '6', '', '0', '', 0, '2022-11-10 00:00:00', '2022-11-11 00:00:00', 1, '0', 1, NULL, NULL, '1970-01-20 15:49:39', 0);
INSERT INTO `task` VALUES (1597794384445829121, '7', '', '0', '', 0, '2022-11-11 00:00:00', '2022-11-12 00:00:00', 1, '2022-11-30T03:27:36.325Z', 1, NULL, NULL, '1970-01-20 15:49:39', 0);
INSERT INTO `task` VALUES (1597794824507891713, '5', '', '0', '', 0, '2022-11-09 00:00:00', '2022-11-10 00:00:00', 1, '0', 1, NULL, NULL, '1970-01-20 15:49:39', 0);
INSERT INTO `task` VALUES (1597795136580898817, '9', '', '0', '', 0, '2022-11-17 00:00:00', '2022-11-18 00:00:00', 1, '0', 1, NULL, NULL, '1970-01-20 15:49:39', 0);
INSERT INTO `task` VALUES (1600351476016771074, '时间测试', '时间测试', '0', '默认分组', 0, '2022-12-07 13:00:00', '2022-12-07 00:00:00', 1, '0', 1, NULL, '无', '2022-12-07 12:48:20', 2);
INSERT INTO `task` VALUES (1600351795803090946, 'test', '', '0', '默认分组', 0, '2022-12-07 13:00:00', '2022-12-08 00:00:00', 1, '0', 1, NULL, '无', '2022-12-07 12:49:36', 2);

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
  `avatar` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'http://localhost:8081/api/static/th.jpg' COMMENT '头像',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1147 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'yy1', 'yy657826', '657826973@qq.com', 0, 'undefined', 'http://localhost:8081/api/static/th.jpg');
INSERT INTO `user` VALUES (2, 'yy2', 'yy657826', 'm13958790203@163.com', NULL, NULL, 'http://localhost:8081/api/static/th.jpg');
INSERT INTO `user` VALUES (111, '111', '111', '111', 111, '111', 'http://localhost:8081/api/static/th.jpg');

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
INSERT INTO `user_notice` VALUES (1, 1, 1);
INSERT INTO `user_notice` VALUES (1, 2, 1);
INSERT INTO `user_notice` VALUES (1, 3, 0);
INSERT INTO `user_notice` VALUES (1, 4, 0);
INSERT INTO `user_notice` VALUES (1, 5, 0);
INSERT INTO `user_notice` VALUES (1, 6, 0);

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
INSERT INTO `vote` VALUES (1, 1599962268206891009);
INSERT INTO `vote` VALUES (1, 1600304111150985219);
INSERT INTO `vote` VALUES (1, 1600307925945970692);
INSERT INTO `vote` VALUES (2, 1600304111150985218);
INSERT INTO `vote` VALUES (2, 1600307925945970692);

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
INSERT INTO `vote_option` VALUES (1599385924670713858, 'user_id', 1599385924670713857, 3);
INSERT INTO `vote_option` VALUES (1599385924670713859, 'user_id', 1599385924670713857, 4);
INSERT INTO `vote_option` VALUES (1599387527800811522, 'http://localhost:9528/#/activity/activityPage?id=1599384406513020929', 1599387527733702657, 0);
INSERT INTO `vote_option` VALUES (1599387527800811523, 'http://localhost:9528/#/activity/activityPage?id=1599384406513020929', 1599387527733702657, 0);
INSERT INTO `vote_option` VALUES (1599962268206891009, 'GetImgUrl', 1599962268185919489, 1);
INSERT INTO `vote_option` VALUES (1599962268206891010, 'GetImgUrl', 1599962268185919489, 0);
INSERT INTO `vote_option` VALUES (1599962979116253187, 'OPTION1', 1599962979116253186, 0);
INSERT INTO `vote_option` VALUES (1599962979116253188, 'OPTION2', 1599962979116253186, 0);
INSERT INTO `vote_option` VALUES (1600304111150985218, 'activity_id', 1600304111083876353, 2);
INSERT INTO `vote_option` VALUES (1600304111150985219, 'activity_22222', 1600304111083876353, 1);
INSERT INTO `vote_option` VALUES (1600307925945970691, '1', 1600307925945970690, 0);
INSERT INTO `vote_option` VALUES (1600307925945970692, '2', 1600307925945970690, 2);

SET FOREIGN_KEY_CHECKS = 1;
