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

 Date: 29/11/2022 20:53:50
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
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `region` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `hit_num` int(0) NULL DEFAULT NULL,
  `limit_capacity` tinyint(1) NULL DEFAULT NULL,
  `capacity` int(0) NULL DEFAULT NULL,
  `participant_num` int(0) NULL DEFAULT NULL,
  `repeat_interval` int(0) NULL DEFAULT NULL,
  `creator_id` bigint(0) NULL DEFAULT NULL,
  `state` tinyint(0) NULL DEFAULT 0,
  `mark` int(0) NULL DEFAULT NULL,
  `applicant_num` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`activity_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activity
-- ----------------------------
INSERT INTO `activity` VALUES (1597567450432405506, '6 create', '6 create', '6 create', '2022-11-17 00:00:00', NULL, '2022-11-29 20:25:36', '6 create', NULL, 0, 1, 2, 1, NULL, 6, 2, NULL, 0);

-- ----------------------------
-- Table structure for activity_apply
-- ----------------------------
DROP TABLE IF EXISTS `activity_apply`;
CREATE TABLE `activity_apply`  (
  `activity_id` bigint(0) NOT NULL,
  `user_id` bigint(0) NOT NULL,
  `apply_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`activity_id`, `user_id`) USING BTREE
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
  `mark_id` bigint(0) NOT NULL,
  `activity_id` bigint(0) NOT NULL,
  `user_id` bigint(0) NULL DEFAULT NULL,
  `mark` int(0) NULL DEFAULT NULL,
  `mark_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`mark_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activity_mark
-- ----------------------------
INSERT INTO `activity_mark` VALUES (1597568588397412354, 1597567450432405506, 6, 4, '2022-11-29 20:30:07');
INSERT INTO `activity_mark` VALUES (1597568605615030273, 1597567450432405506, 1146, 2, '2022-11-29 20:30:11');

-- ----------------------------
-- Table structure for activity_participate
-- ----------------------------
DROP TABLE IF EXISTS `activity_participate`;
CREATE TABLE `activity_participate`  (
  `activity_id` bigint(0) NOT NULL,
  `user_id` bigint(0) NOT NULL,
  PRIMARY KEY (`activity_id`, `user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activity_participate
-- ----------------------------
INSERT INTO `activity_participate` VALUES (1597566700876726274, 1145);
INSERT INTO `activity_participate` VALUES (1597567450432405506, 6);
INSERT INTO `activity_participate` VALUES (1597567450432405506, 1146);

-- ----------------------------
-- Table structure for activity_tag
-- ----------------------------
DROP TABLE IF EXISTS `activity_tag`;
CREATE TABLE `activity_tag`  (
  `activity_id` bigint(0) NOT NULL,
  `tag_id` bigint(0) NOT NULL,
  PRIMARY KEY (`activity_id`, `tag_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activity_tag
-- ----------------------------

-- ----------------------------
-- Table structure for activity_work
-- ----------------------------
DROP TABLE IF EXISTS `activity_work`;
CREATE TABLE `activity_work`  (
  `activity_id` bigint(0) NOT NULL,
  `work_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`activity_id`, `work_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activity_work
-- ----------------------------

-- ----------------------------
-- Table structure for poll
-- ----------------------------
DROP TABLE IF EXISTS `poll`;
CREATE TABLE `poll`  (
  `activity_id` bigint(0) NOT NULL,
  `poll_id` bigint(0) NOT NULL,
  `topic_text` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `deadline` datetime(0) NULL DEFAULT NULL,
  `multiple_choice` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY (`poll_id`, `activity_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of poll
-- ----------------------------

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag`  (
  `tag_id` bigint(0) NOT NULL,
  `tag_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`tag_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tag
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主码',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '名字',
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码,MD5加密',
  `email` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '邮箱',
  `age` int(0) NULL DEFAULT NULL,
  `introduce` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `avatar` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'http://localhost:8081/api/static/th.jpg' COMMENT '头像',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (6, 'yy', 'yy657826', '657826973@qq.com', NULL, NULL, 'http://localhost:8081/api/static/th.jpg');
INSERT INTO `user` VALUES (111, '111N', '111', '111', NULL, NULL, 'http://localhost:8081/api/static/th.jpg');
INSERT INTO `user` VALUES (222, '222N', '222', '222', NULL, NULL, 'http://localhost:8081/api/static/th.jpg');
INSERT INTO `user` VALUES (1145, '1145N', '1145', '1145', NULL, NULL, 'http://localhost:8081/api/static/th.jpg');
INSERT INTO `user` VALUES (1146, 'yy2', 'yy657826', 'm13958790203@163.com', NULL, NULL, 'http://localhost:8081/api/static/th.jpg');

-- ----------------------------
-- Table structure for user_mark
-- ----------------------------
DROP TABLE IF EXISTS `user_mark`;
CREATE TABLE `user_mark`  (
  `activity_id` bigint(0) NOT NULL,
  `marker_id` bigint(0) NOT NULL,
  `user_id` bigint(0) NOT NULL,
  `mark` int(0) NOT NULL,
  `mark_time` datetime(0) NULL DEFAULT NULL,
  `mark_id` bigint(0) NOT NULL,
  PRIMARY KEY (`mark_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_mark
-- ----------------------------

-- ----------------------------
-- Table structure for vote_option
-- ----------------------------
DROP TABLE IF EXISTS `vote_option`;
CREATE TABLE `vote_option`  (
  `option_id` bigint(0) NOT NULL,
  `option_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `poll_id` bigint(0) NULL DEFAULT NULL,
  `num` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`option_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vote_option
-- ----------------------------

-- ----------------------------
-- Table structure for work
-- ----------------------------
DROP TABLE IF EXISTS `work`;
CREATE TABLE `work`  (
  `work_id` bigint(0) NOT NULL,
  `user_id` bigint(0) NULL DEFAULT NULL,
  `work_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`work_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of work
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
