/*
 Navicat Premium Data Transfer

 Source Server         : badtzh.xyz
 Source Server Type    : MySQL
 Source Server Version : 80036
 Source Host           : badtzh.xyz:33306
 Source Schema         : turing

 Target Server Type    : MySQL
 Target Server Version : 80036
 File Encoding         : 65001

 Date: 11/04/2024 19:50:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_ad2tag
-- ----------------------------
DROP TABLE IF EXISTS `tb_ad2tag`;
CREATE TABLE `tb_ad2tag`  (
  `ad_id` int NOT NULL,
  `tag_id` int NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_ad2tag
-- ----------------------------
INSERT INTO `tb_ad2tag` VALUES (12, 1);
INSERT INTO `tb_ad2tag` VALUES (19, 6);
INSERT INTO `tb_ad2tag` VALUES (20, 6);

-- ----------------------------
-- Table structure for tb_ad_day_amount
-- ----------------------------
DROP TABLE IF EXISTS `tb_ad_day_amount`;
CREATE TABLE `tb_ad_day_amount`  (
  `amount` int NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_ad_day_amount
-- ----------------------------
INSERT INTO `tb_ad_day_amount` VALUES (50);

-- ----------------------------
-- Table structure for tb_admin_login_record
-- ----------------------------
DROP TABLE IF EXISTS `tb_admin_login_record`;
CREATE TABLE `tb_admin_login_record`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `admin_id` bigint NOT NULL,
  `ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `status` tinyint(1) NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 444 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_admin_login_record
-- ----------------------------
INSERT INTO `tb_admin_login_record` VALUES (1, 525042392395781, '0:0:0:0:0:0:0:1', '未知', 0, '2024-03-13 17:12:22');
INSERT INTO `tb_admin_login_record` VALUES (2, 525042392395781, '0:0:0:0:0:0:0:1', '未知', 0, '2024-03-13 17:13:21');
INSERT INTO `tb_admin_login_record` VALUES (3, 525042392395781, '0:0:0:0:0:0:0:1', '未知', 0, '2024-03-13 17:13:49');
INSERT INTO `tb_admin_login_record` VALUES (4, 525042392395781, '0:0:0:0:0:0:0:1', '未知', 0, '2024-03-13 17:13:53');
INSERT INTO `tb_admin_login_record` VALUES (5, 525045519282181, '0:0:0:0:0:0:0:1', '未知', 0, '2024-03-13 17:16:07');
INSERT INTO `tb_admin_login_record` VALUES (6, 525045519282181, '0:0:0:0:0:0:0:1', '未知', 1, '2024-03-13 17:18:29');
INSERT INTO `tb_admin_login_record` VALUES (7, 525045519282181, '0:0:0:0:0:0:0:1', '未知', 1, '2024-03-13 17:18:51');
INSERT INTO `tb_admin_login_record` VALUES (8, 525045519282181, '0:0:0:0:0:0:0:1', '未知', 1, '2024-03-14 13:19:24');
INSERT INTO `tb_admin_login_record` VALUES (9, 525354017488901, '0:0:0:0:0:0:0:1', '未知', 1, '2024-03-14 14:12:37');
INSERT INTO `tb_admin_login_record` VALUES (10, 525045519282181, '0:0:0:0:0:0:0:1', '未知', 1, '2024-03-14 14:35:25');
INSERT INTO `tb_admin_login_record` VALUES (11, 525354017488901, '0:0:0:0:0:0:0:1', '未知', 1, '2024-03-14 15:13:34');
INSERT INTO `tb_admin_login_record` VALUES (12, 525045519282181, '0:0:0:0:0:0:0:1', '未知', 1, '2024-03-14 15:14:19');
INSERT INTO `tb_admin_login_record` VALUES (13, 525045519282181, '117.136.33.253', '未知', 1, '2024-03-16 15:19:58');
INSERT INTO `tb_admin_login_record` VALUES (14, 525045519282181, '117.136.33.253', '未知', 1, '2024-03-16 15:22:11');
INSERT INTO `tb_admin_login_record` VALUES (15, 525045519282181, '117.136.33.253', '未知', 1, '2024-03-16 15:31:57');
INSERT INTO `tb_admin_login_record` VALUES (16, 525354017488901, '117.136.33.253', '未知', 1, '2024-03-16 15:36:42');
INSERT INTO `tb_admin_login_record` VALUES (17, 525354017488901, '117.136.33.253', '未知', 1, '2024-03-16 15:53:28');
INSERT INTO `tb_admin_login_record` VALUES (18, 525354017488901, '45.9.2.15', '未知', 1, '2024-03-16 15:55:52');
INSERT INTO `tb_admin_login_record` VALUES (19, 525354017488901, '117.136.33.253', '未知', 1, '2024-03-16 16:24:37');
INSERT INTO `tb_admin_login_record` VALUES (20, 525045519282181, '117.136.33.253', '未知', 1, '2024-03-16 16:30:53');
INSERT INTO `tb_admin_login_record` VALUES (21, 525045519282181, '45.9.2.15', '未知', 1, '2024-03-16 17:18:29');
INSERT INTO `tb_admin_login_record` VALUES (22, 525045519282181, '117.136.33.166', '未知', 1, '2024-03-16 20:19:26');
INSERT INTO `tb_admin_login_record` VALUES (23, 525045519282181, '117.136.33.166', '未知', 1, '2024-03-16 20:43:06');
INSERT INTO `tb_admin_login_record` VALUES (24, 525045519282181, '117.136.33.166', '未知', 1, '2024-03-16 20:43:35');
INSERT INTO `tb_admin_login_record` VALUES (25, 525045519282181, '223.104.66.160', '未知', 1, '2024-03-16 21:23:48');
INSERT INTO `tb_admin_login_record` VALUES (26, 525045519282181, '223.104.66.160', '未知', 1, '2024-03-16 21:23:58');
INSERT INTO `tb_admin_login_record` VALUES (27, 525354017488901, '223.104.66.160', '未知', 1, '2024-03-16 21:24:12');
INSERT INTO `tb_admin_login_record` VALUES (28, 525045519282181, '223.104.66.160', '未知', 1, '2024-03-16 21:50:06');
INSERT INTO `tb_admin_login_record` VALUES (29, 525354017488901, '223.104.66.160', '未知', 1, '2024-03-16 22:03:03');
INSERT INTO `tb_admin_login_record` VALUES (30, 525354017488901, '223.104.66.160', '未知', 1, '2024-03-16 22:07:22');
INSERT INTO `tb_admin_login_record` VALUES (31, 525045519282181, '223.104.66.160', '未知', 1, '2024-03-16 22:09:37');
INSERT INTO `tb_admin_login_record` VALUES (32, 525354017488901, '120.235.248.232', '未知', 1, '2024-03-16 22:24:01');
INSERT INTO `tb_admin_login_record` VALUES (33, 525045519282181, '120.235.248.232', '未知', 1, '2024-03-16 22:25:41');
INSERT INTO `tb_admin_login_record` VALUES (34, 525354017488901, '120.235.248.232', '未知', 1, '2024-03-16 22:27:50');
INSERT INTO `tb_admin_login_record` VALUES (35, 525354017488901, '223.104.66.160', '未知', 1, '2024-03-16 22:29:10');
INSERT INTO `tb_admin_login_record` VALUES (36, 525045519282181, '0:0:0:0:0:0:0:1', '未知', 1, '2024-03-16 23:05:50');
INSERT INTO `tb_admin_login_record` VALUES (37, 525045519282181, '45.9.2.15', '未知', 1, '2024-03-17 09:10:31');
INSERT INTO `tb_admin_login_record` VALUES (38, 525354017488901, '117.136.33.162', '未知', 1, '2024-03-17 09:23:53');
INSERT INTO `tb_admin_login_record` VALUES (39, 525045519282181, '117.136.79.177', '未知', 1, '2024-03-17 12:14:05');
INSERT INTO `tb_admin_login_record` VALUES (40, 525354017488901, '117.136.79.177', '未知', 1, '2024-03-17 12:16:40');
INSERT INTO `tb_admin_login_record` VALUES (41, 525354017488901, '45.9.2.15', '未知', 1, '2024-03-17 12:37:51');
INSERT INTO `tb_admin_login_record` VALUES (42, 525354017488901, '45.9.2.15', '未知', 1, '2024-03-17 12:39:37');
INSERT INTO `tb_admin_login_record` VALUES (43, 525354017488901, '117.136.79.177', '未知', 1, '2024-03-17 12:41:53');
INSERT INTO `tb_admin_login_record` VALUES (44, 525354017488901, '183.46.72.206', '未知', 1, '2024-03-18 15:29:52');
INSERT INTO `tb_admin_login_record` VALUES (45, 525045519282181, '183.46.72.206', '未知', 1, '2024-03-18 15:31:34');
INSERT INTO `tb_admin_login_record` VALUES (46, 525045519282181, '183.46.72.206', '未知', 1, '2024-03-18 15:39:06');
INSERT INTO `tb_admin_login_record` VALUES (47, 525045519282181, '183.46.72.206', '未知', 1, '2024-03-18 15:43:56');
INSERT INTO `tb_admin_login_record` VALUES (48, 525045519282181, '183.46.72.206', '未知', 1, '2024-03-18 15:47:01');
INSERT INTO `tb_admin_login_record` VALUES (49, 525045519282181, '183.46.72.206', '未知', 1, '2024-03-18 15:55:28');
INSERT INTO `tb_admin_login_record` VALUES (50, 525045519282181, '183.46.72.206', '未知', 1, '2024-03-18 15:56:46');
INSERT INTO `tb_admin_login_record` VALUES (51, 525045519282181, '183.46.72.206', '未知', 1, '2024-03-18 15:57:17');
INSERT INTO `tb_admin_login_record` VALUES (52, 525045519282181, '183.46.72.206', '未知', 1, '2024-03-18 15:58:26');
INSERT INTO `tb_admin_login_record` VALUES (53, 525045519282181, '183.46.72.206', '未知', 1, '2024-03-18 16:03:20');
INSERT INTO `tb_admin_login_record` VALUES (54, 525045519282181, '183.46.72.206', '未知', 1, '2024-03-18 16:08:06');
INSERT INTO `tb_admin_login_record` VALUES (55, 525354017488901, '183.46.72.206', '未知', 1, '2024-03-18 16:09:31');
INSERT INTO `tb_admin_login_record` VALUES (56, 525354017488901, '183.46.72.206', '未知', 1, '2024-03-18 16:37:30');
INSERT INTO `tb_admin_login_record` VALUES (57, 525045519282181, '183.46.72.206', '未知', 1, '2024-03-18 16:39:50');
INSERT INTO `tb_admin_login_record` VALUES (58, 525045519282181, '183.46.72.206', '未知', 1, '2024-03-18 16:41:29');
INSERT INTO `tb_admin_login_record` VALUES (59, 525045519282181, '183.46.72.206', '未知', 1, '2024-03-18 16:42:27');
INSERT INTO `tb_admin_login_record` VALUES (60, 525045519282181, '172.234.85.145', '未知', 1, '2024-03-18 16:49:46');
INSERT INTO `tb_admin_login_record` VALUES (61, 525045519282181, '120.82.32.131', '未知', 1, '2024-03-18 17:00:51');
INSERT INTO `tb_admin_login_record` VALUES (62, 525045519282181, '120.82.32.131', '未知', 1, '2024-03-18 17:00:57');
INSERT INTO `tb_admin_login_record` VALUES (63, 525045519282181, '120.82.32.131', '未知', 1, '2024-03-18 17:01:16');
INSERT INTO `tb_admin_login_record` VALUES (64, 525045519282181, '120.82.32.131', '未知', 1, '2024-03-18 17:15:31');
INSERT INTO `tb_admin_login_record` VALUES (65, 525045519282181, '0:0:0:0:0:0:0:1', '未知', 1, '2024-03-18 17:19:13');
INSERT INTO `tb_admin_login_record` VALUES (66, 525045519282181, '0:0:0:0:0:0:0:1', '未知', 1, '2024-03-18 17:24:18');
INSERT INTO `tb_admin_login_record` VALUES (67, 525045519282181, '0:0:0:0:0:0:0:1', '未知', 1, '2024-03-18 17:25:46');
INSERT INTO `tb_admin_login_record` VALUES (68, 525045519282181, '0:0:0:0:0:0:0:1', '未知', 1, '2024-03-18 17:27:02');
INSERT INTO `tb_admin_login_record` VALUES (69, 525045519282181, '0:0:0:0:0:0:0:1', '未知', 1, '2024-03-18 17:30:35');
INSERT INTO `tb_admin_login_record` VALUES (70, 525045519282181, '172.234.85.145', '未知', 1, '2024-03-18 17:31:53');
INSERT INTO `tb_admin_login_record` VALUES (71, 525045519282181, '0:0:0:0:0:0:0:1', '未知', 1, '2024-03-18 17:31:54');
INSERT INTO `tb_admin_login_record` VALUES (72, 525045519282181, '0:0:0:0:0:0:0:1', '未知', 1, '2024-03-18 17:31:54');
INSERT INTO `tb_admin_login_record` VALUES (73, 525045519282181, '0:0:0:0:0:0:0:1', '未知', 1, '2024-03-18 17:31:54');
INSERT INTO `tb_admin_login_record` VALUES (74, 525045519282181, '0:0:0:0:0:0:0:1', '未知', 1, '2024-03-18 17:31:55');
INSERT INTO `tb_admin_login_record` VALUES (75, 525045519282181, '172.234.85.145', '未知', 1, '2024-03-18 17:34:28');
INSERT INTO `tb_admin_login_record` VALUES (76, 525045519282181, '172.234.85.145', '未知', 1, '2024-03-18 17:34:40');
INSERT INTO `tb_admin_login_record` VALUES (77, 525045519282181, '0:0:0:0:0:0:0:1', '未知', 1, '2024-03-18 17:36:12');
INSERT INTO `tb_admin_login_record` VALUES (78, 525045519282181, '0:0:0:0:0:0:0:1', '未知', 1, '2024-03-18 17:36:18');
INSERT INTO `tb_admin_login_record` VALUES (79, 525045519282181, '172.234.85.145', '未知', 1, '2024-03-18 17:37:55');
INSERT INTO `tb_admin_login_record` VALUES (80, 525045519282181, '0:0:0:0:0:0:0:1', '未知', 1, '2024-03-18 17:38:25');
INSERT INTO `tb_admin_login_record` VALUES (81, 525045519282181, '172.234.85.145', '未知', 1, '2024-03-18 17:42:15');
INSERT INTO `tb_admin_login_record` VALUES (82, 525045519282181, '0:0:0:0:0:0:0:1', '未知', 1, '2024-03-18 17:42:26');
INSERT INTO `tb_admin_login_record` VALUES (83, 525045519282181, '120.235.248.151', '未知', 1, '2024-03-18 17:45:12');
INSERT INTO `tb_admin_login_record` VALUES (84, 525045519282181, '120.235.248.151', '未知', 1, '2024-03-18 17:46:20');
INSERT INTO `tb_admin_login_record` VALUES (85, 525045519282181, '120.235.248.151', '未知', 1, '2024-03-18 17:48:14');
INSERT INTO `tb_admin_login_record` VALUES (86, 525045519282181, '172.234.85.145', '未知', 1, '2024-03-18 18:03:29');
INSERT INTO `tb_admin_login_record` VALUES (87, 525045519282181, '172.234.85.145', '未知', 1, '2024-03-18 18:04:10');
INSERT INTO `tb_admin_login_record` VALUES (88, 525045519282181, '172.234.85.145', '未知', 1, '2024-03-18 18:05:00');
INSERT INTO `tb_admin_login_record` VALUES (89, 525045519282181, '172.234.85.145', '未知', 1, '2024-03-18 18:05:19');
INSERT INTO `tb_admin_login_record` VALUES (90, 525045519282181, '172.234.85.145', '未知', 1, '2024-03-18 18:05:34');
INSERT INTO `tb_admin_login_record` VALUES (91, 525045519282181, '0:0:0:0:0:0:0:1', '未知', 1, '2024-03-18 18:35:06');
INSERT INTO `tb_admin_login_record` VALUES (92, 525045519282181, '0:0:0:0:0:0:0:1', '未知', 1, '2024-03-18 18:46:13');
INSERT INTO `tb_admin_login_record` VALUES (93, 525045519282181, '0:0:0:0:0:0:0:1', '未知', 1, '2024-03-18 18:47:19');
INSERT INTO `tb_admin_login_record` VALUES (94, 525045519282181, '0:0:0:0:0:0:0:1', '未知', 1, '2024-03-18 18:49:20');
INSERT INTO `tb_admin_login_record` VALUES (95, 525045519282181, '0:0:0:0:0:0:0:1', '未知', 1, '2024-03-18 18:51:20');
INSERT INTO `tb_admin_login_record` VALUES (96, 525045519282181, '120.235.248.151', '未知', 1, '2024-03-18 19:14:55');
INSERT INTO `tb_admin_login_record` VALUES (97, 525045519282181, '120.235.248.151', '未知', 1, '2024-03-18 20:05:58');
INSERT INTO `tb_admin_login_record` VALUES (98, 525045519282181, '120.235.248.151', '未知', 1, '2024-03-18 20:06:36');
INSERT INTO `tb_admin_login_record` VALUES (99, 525045519282181, '172.234.85.145', '未知', 1, '2024-03-18 20:08:36');
INSERT INTO `tb_admin_login_record` VALUES (100, 525045519282181, '172.234.85.145', '未知', 1, '2024-03-18 20:09:38');
INSERT INTO `tb_admin_login_record` VALUES (101, 525045519282181, '172.234.85.145', '未知', 1, '2024-03-18 20:09:52');
INSERT INTO `tb_admin_login_record` VALUES (102, 525045519282181, '172.234.85.145', '未知', 1, '2024-03-18 20:12:45');
INSERT INTO `tb_admin_login_record` VALUES (103, 525354017488901, '172.234.85.145', '未知', 1, '2024-03-18 20:14:07');
INSERT INTO `tb_admin_login_record` VALUES (104, 525354017488901, '172.234.85.145', '未知', 1, '2024-03-18 20:14:50');
INSERT INTO `tb_admin_login_record` VALUES (105, 525354017488901, '172.234.85.145', '未知', 1, '2024-03-18 20:14:58');
INSERT INTO `tb_admin_login_record` VALUES (106, 525354017488901, '172.234.85.145', '未知', 1, '2024-03-18 20:15:42');
INSERT INTO `tb_admin_login_record` VALUES (107, 525354017488901, '172.234.85.145', '未知', 1, '2024-03-18 21:05:25');
INSERT INTO `tb_admin_login_record` VALUES (108, 525045519282181, '172.234.85.145', '未知', 1, '2024-03-18 21:13:17');
INSERT INTO `tb_admin_login_record` VALUES (109, 525045519282181, '172.234.85.145', '未知', 1, '2024-03-18 21:35:04');
INSERT INTO `tb_admin_login_record` VALUES (110, 525045519282181, '172.234.85.145', '未知', 1, '2024-03-18 21:44:27');
INSERT INTO `tb_admin_login_record` VALUES (111, 525045519282181, '172.234.85.145', '未知', 1, '2024-03-18 21:46:17');
INSERT INTO `tb_admin_login_record` VALUES (112, 525045519282181, '120.235.248.151', '未知', 1, '2024-03-18 22:01:37');
INSERT INTO `tb_admin_login_record` VALUES (113, 525045519282181, '120.235.248.151', '未知', 1, '2024-03-18 22:02:10');
INSERT INTO `tb_admin_login_record` VALUES (114, 525045519282181, '120.235.248.151', '未知', 1, '2024-03-18 22:02:29');
INSERT INTO `tb_admin_login_record` VALUES (115, 525045519282181, '120.235.248.151', '未知', 1, '2024-03-18 22:02:52');
INSERT INTO `tb_admin_login_record` VALUES (116, 525045519282181, '120.235.248.151', '未知', 1, '2024-03-18 22:03:29');
INSERT INTO `tb_admin_login_record` VALUES (117, 525045519282181, '120.235.248.151', '未知', 1, '2024-03-18 22:04:31');
INSERT INTO `tb_admin_login_record` VALUES (118, 525045519282181, '120.235.248.151', '未知', 1, '2024-03-18 22:06:35');
INSERT INTO `tb_admin_login_record` VALUES (119, 525045519282181, '120.235.248.151', '未知', 1, '2024-03-18 22:07:40');
INSERT INTO `tb_admin_login_record` VALUES (120, 525045519282181, '120.235.248.151', '未知', 1, '2024-03-18 22:09:00');
INSERT INTO `tb_admin_login_record` VALUES (121, 525045519282181, '120.235.248.151', '未知', 1, '2024-03-18 22:09:28');
INSERT INTO `tb_admin_login_record` VALUES (122, 525045519282181, '0:0:0:0:0:0:0:1', '未知', 1, '2024-03-18 22:34:20');
INSERT INTO `tb_admin_login_record` VALUES (123, 525045519282181, '0:0:0:0:0:0:0:1', '未知', 1, '2024-03-18 22:37:03');
INSERT INTO `tb_admin_login_record` VALUES (124, 525354017488901, '0:0:0:0:0:0:0:1', '未知', 1, '2024-03-19 16:05:44');
INSERT INTO `tb_admin_login_record` VALUES (125, 525045519282181, '0:0:0:0:0:0:0:1', '未知', 1, '2024-03-19 16:06:29');
INSERT INTO `tb_admin_login_record` VALUES (126, 525354017488901, '117.136.79.193', '未知', 1, '2024-03-19 17:49:01');
INSERT INTO `tb_admin_login_record` VALUES (127, 525354017488901, '117.136.79.193', '未知', 1, '2024-03-19 17:49:54');
INSERT INTO `tb_admin_login_record` VALUES (128, 525354017488901, '117.136.79.193', '未知', 1, '2024-03-19 18:00:32');
INSERT INTO `tb_admin_login_record` VALUES (129, 525354017488901, '117.136.79.193', '未知', 1, '2024-03-19 18:09:18');
INSERT INTO `tb_admin_login_record` VALUES (130, 525045519282181, '0:0:0:0:0:0:0:1', '未知', 1, '2024-03-19 18:24:25');
INSERT INTO `tb_admin_login_record` VALUES (131, 525354017488901, '117.136.41.96', '广东省深圳市', 1, '2024-03-20 16:09:51');
INSERT INTO `tb_admin_login_record` VALUES (132, 525354017488901, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-20 16:10:16');
INSERT INTO `tb_admin_login_record` VALUES (133, 525354017488901, '117.136.41.96', '广东省深圳市', 1, '2024-03-20 16:21:37');
INSERT INTO `tb_admin_login_record` VALUES (134, 525354017488901, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-20 16:25:51');
INSERT INTO `tb_admin_login_record` VALUES (135, 525354017488901, '117.136.41.96', '广东省深圳市', 1, '2024-03-20 16:26:38');
INSERT INTO `tb_admin_login_record` VALUES (136, 525045519282181, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-20 16:29:18');
INSERT INTO `tb_admin_login_record` VALUES (137, 527511432478725, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-20 16:31:46');
INSERT INTO `tb_admin_login_record` VALUES (138, 525354017488901, '117.136.41.96', '广东省深圳市', 1, '2024-03-20 16:32:15');
INSERT INTO `tb_admin_login_record` VALUES (139, 525354017488901, '117.136.41.96', '广东省深圳市', 1, '2024-03-20 16:32:21');
INSERT INTO `tb_admin_login_record` VALUES (140, 525045519282181, '117.136.41.96', '广东省深圳市', 1, '2024-03-20 16:35:42');
INSERT INTO `tb_admin_login_record` VALUES (141, 525045519282181, '117.136.41.96', '广东省深圳市', 1, '2024-03-20 16:35:58');
INSERT INTO `tb_admin_login_record` VALUES (142, 525045519282181, '117.136.41.96', '广东省深圳市', 1, '2024-03-20 16:36:05');
INSERT INTO `tb_admin_login_record` VALUES (143, 525045519282181, '117.136.41.96', '广东省深圳市', 1, '2024-03-20 16:36:29');
INSERT INTO `tb_admin_login_record` VALUES (144, 525354017488901, '117.136.41.96', '广东省深圳市', 1, '2024-03-20 16:57:05');
INSERT INTO `tb_admin_login_record` VALUES (145, 525045519282181, '117.136.41.96', '广东省深圳市', 1, '2024-03-20 17:49:51');
INSERT INTO `tb_admin_login_record` VALUES (146, 527511432478725, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-20 20:05:49');
INSERT INTO `tb_admin_login_record` VALUES (147, 527511432478725, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-20 20:39:17');
INSERT INTO `tb_admin_login_record` VALUES (148, 527511432478725, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-20 20:49:57');
INSERT INTO `tb_admin_login_record` VALUES (149, 527511432478725, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-20 20:53:23');
INSERT INTO `tb_admin_login_record` VALUES (150, 527511432478725, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-20 20:56:33');
INSERT INTO `tb_admin_login_record` VALUES (151, 525354017488901, '117.136.79.197', '广东省茂名市', 1, '2024-03-20 21:29:02');
INSERT INTO `tb_admin_login_record` VALUES (152, 525354017488901, '45.9.2.15', '伦敦伦敦', 1, '2024-03-20 21:52:33');
INSERT INTO `tb_admin_login_record` VALUES (153, 525045519282181, '117.136.79.197', '广东省茂名市', 1, '2024-03-20 22:01:29');
INSERT INTO `tb_admin_login_record` VALUES (154, 525354017488901, '117.136.79.197', '广东省茂名市', 1, '2024-03-20 23:55:53');
INSERT INTO `tb_admin_login_record` VALUES (155, 525045519282181, '117.136.79.197', '广东省茂名市', 1, '2024-03-20 23:56:02');
INSERT INTO `tb_admin_login_record` VALUES (156, 525045519282181, '117.136.79.197', '广东省茂名市', 1, '2024-03-21 09:43:33');
INSERT INTO `tb_admin_login_record` VALUES (157, 525045519282181, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-21 09:46:47');
INSERT INTO `tb_admin_login_record` VALUES (158, 525045519282181, '117.136.79.197', '广东省茂名市', 1, '2024-03-21 09:46:54');
INSERT INTO `tb_admin_login_record` VALUES (159, 525045519282181, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-21 09:47:32');
INSERT INTO `tb_admin_login_record` VALUES (160, 525045519282181, '117.136.79.197', '广东省茂名市', 1, '2024-03-21 09:49:57');
INSERT INTO `tb_admin_login_record` VALUES (161, 527511432478729, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-21 09:51:39');
INSERT INTO `tb_admin_login_record` VALUES (162, 527511432478729, '117.136.79.197', '广东省茂名市', 1, '2024-03-21 09:56:48');
INSERT INTO `tb_admin_login_record` VALUES (163, 525354017488901, '117.136.79.197', '广东省茂名市', 1, '2024-03-21 10:54:33');
INSERT INTO `tb_admin_login_record` VALUES (164, 525045519282181, '120.235.248.21', '广东省0', 1, '2024-03-21 12:22:48');
INSERT INTO `tb_admin_login_record` VALUES (165, 527511432478729, '117.136.79.197', '广东省茂名市', 1, '2024-03-21 12:29:37');
INSERT INTO `tb_admin_login_record` VALUES (166, 527511432478729, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-21 12:41:54');
INSERT INTO `tb_admin_login_record` VALUES (167, 527511432478729, '117.136.79.197', '广东省茂名市', 1, '2024-03-21 12:52:16');
INSERT INTO `tb_admin_login_record` VALUES (168, 525045519282181, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-21 12:56:53');
INSERT INTO `tb_admin_login_record` VALUES (169, 525045519282181, '120.235.248.21', '广东省0', 1, '2024-03-21 13:03:00');
INSERT INTO `tb_admin_login_record` VALUES (170, 525045519282181, '120.235.248.21', '广东省0', 1, '2024-03-21 13:03:25');
INSERT INTO `tb_admin_login_record` VALUES (171, 525045519282181, '117.136.79.197', '广东省茂名市', 1, '2024-03-21 13:07:01');
INSERT INTO `tb_admin_login_record` VALUES (172, 527511432478729, '117.136.79.197', '广东省茂名市', 1, '2024-03-21 13:22:58');
INSERT INTO `tb_admin_login_record` VALUES (173, 527511432478729, '117.136.79.197', '广东省茂名市', 1, '2024-03-21 19:19:28');
INSERT INTO `tb_admin_login_record` VALUES (174, 525045519282181, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-21 20:43:19');
INSERT INTO `tb_admin_login_record` VALUES (175, 525354017488901, '117.136.79.197', '广东省茂名市', 1, '2024-03-21 21:55:39');
INSERT INTO `tb_admin_login_record` VALUES (176, 525045519282181, '117.136.79.197', '广东省茂名市', 1, '2024-03-21 22:03:35');
INSERT INTO `tb_admin_login_record` VALUES (177, 527511432478729, '117.136.79.197', '广东省茂名市', 1, '2024-03-21 22:04:05');
INSERT INTO `tb_admin_login_record` VALUES (178, 527511432478729, '117.136.79.197', '广东省茂名市', 1, '2024-03-21 22:40:37');
INSERT INTO `tb_admin_login_record` VALUES (179, 527511432478729, '117.136.79.197', '广东省茂名市', 1, '2024-03-21 22:40:57');
INSERT INTO `tb_admin_login_record` VALUES (180, 527511432478729, '45.9.2.15', '伦敦伦敦', 1, '2024-03-22 23:00:27');
INSERT INTO `tb_admin_login_record` VALUES (181, 525045519282181, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-23 12:19:47');
INSERT INTO `tb_admin_login_record` VALUES (182, 525045519282181, '172.234.85.145', '马萨诸塞0', 1, '2024-03-23 13:37:21');
INSERT INTO `tb_admin_login_record` VALUES (183, 528534178275333, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-23 17:35:00');
INSERT INTO `tb_admin_login_record` VALUES (184, 528534178275333, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-23 17:36:55');
INSERT INTO `tb_admin_login_record` VALUES (185, 528534178275333, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-23 17:38:14');
INSERT INTO `tb_admin_login_record` VALUES (186, 528534178275331, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-23 21:24:46');
INSERT INTO `tb_admin_login_record` VALUES (187, 528534178275331, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-23 22:05:27');
INSERT INTO `tb_admin_login_record` VALUES (188, 528534178275331, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-23 22:28:42');
INSERT INTO `tb_admin_login_record` VALUES (189, 528534178275331, '120.235.248.221', '广东省0', 1, '2024-03-24 00:57:06');
INSERT INTO `tb_admin_login_record` VALUES (190, 528534178275331, '120.235.248.7', '广东省0', 1, '2024-03-24 15:31:32');
INSERT INTO `tb_admin_login_record` VALUES (191, 528534178275333, '117.136.79.197', '广东省茂名市', 1, '2024-03-24 20:48:48');
INSERT INTO `tb_admin_login_record` VALUES (192, 528534178275331, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-25 15:53:44');
INSERT INTO `tb_admin_login_record` VALUES (193, 528534178275399, '183.46.222.71', '广东省汕头市', 0, '2024-03-25 16:03:05');
INSERT INTO `tb_admin_login_record` VALUES (194, 528534178275399, '183.46.222.71', '广东省汕头市', 0, '2024-03-25 16:03:25');
INSERT INTO `tb_admin_login_record` VALUES (195, 528534178275399, '183.46.222.71', '广东省汕头市', 1, '2024-03-25 16:03:44');
INSERT INTO `tb_admin_login_record` VALUES (196, 528534178275333, '117.136.79.197', '广东省茂名市', 1, '2024-03-25 16:53:37');
INSERT INTO `tb_admin_login_record` VALUES (197, 528534178275331, '117.136.79.197', '广东省茂名市', 1, '2024-03-25 16:56:16');
INSERT INTO `tb_admin_login_record` VALUES (198, 528534178275333, '117.136.79.197', '广东省茂名市', 1, '2024-03-25 16:59:52');
INSERT INTO `tb_admin_login_record` VALUES (199, 528534178275333, '117.136.79.197', '广东省茂名市', 1, '2024-03-25 17:08:33');
INSERT INTO `tb_admin_login_record` VALUES (200, 528534178275399, '117.136.79.203', '广东省茂名市', 1, '2024-03-25 21:54:48');
INSERT INTO `tb_admin_login_record` VALUES (201, 528534178275333, '117.136.79.203', '广东省茂名市', 1, '2024-03-25 22:06:06');
INSERT INTO `tb_admin_login_record` VALUES (202, 528534178275399, '223.104.64.92', '广东省湛江市', 1, '2024-03-26 19:18:56');
INSERT INTO `tb_admin_login_record` VALUES (203, 528534178275399, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-26 21:05:45');
INSERT INTO `tb_admin_login_record` VALUES (204, 528534178275399, '223.104.66.216', '广东省东莞市', 1, '2024-03-26 21:16:31');
INSERT INTO `tb_admin_login_record` VALUES (205, 528534178275331, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-27 20:37:20');
INSERT INTO `tb_admin_login_record` VALUES (206, 528534178275399, '223.104.64.89', '广东省湛江市', 1, '2024-03-27 22:33:35');
INSERT INTO `tb_admin_login_record` VALUES (207, 528534178275331, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-28 19:06:32');
INSERT INTO `tb_admin_login_record` VALUES (208, 528534178275331, '183.46.222.71', '广东省汕头市', 1, '2024-03-29 15:43:19');
INSERT INTO `tb_admin_login_record` VALUES (209, 528534178275399, '0:0:0:0:0:0:0:1', '本地访问', 0, '2024-03-29 22:40:19');
INSERT INTO `tb_admin_login_record` VALUES (210, 528534178275399, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-29 22:40:27');
INSERT INTO `tb_admin_login_record` VALUES (211, 528534178275399, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-29 22:40:36');
INSERT INTO `tb_admin_login_record` VALUES (212, 528534178275399, '0:0:0:0:0:0:0:1', '本地访问', 0, '2024-03-29 22:40:45');
INSERT INTO `tb_admin_login_record` VALUES (213, 528534178275399, '0:0:0:0:0:0:0:1', '本地访问', 0, '2024-03-29 22:41:32');
INSERT INTO `tb_admin_login_record` VALUES (214, 528534178275399, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-29 22:41:38');
INSERT INTO `tb_admin_login_record` VALUES (215, 528534178275399, '0:0:0:0:0:0:0:1', '本地访问', 0, '2024-03-29 22:41:49');
INSERT INTO `tb_admin_login_record` VALUES (216, 528534178275399, '0:0:0:0:0:0:0:1', '本地访问', 0, '2024-03-29 22:44:04');
INSERT INTO `tb_admin_login_record` VALUES (217, 528534178275399, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-29 22:44:08');
INSERT INTO `tb_admin_login_record` VALUES (218, 528534178275399, '0:0:0:0:0:0:0:1', '本地访问', 0, '2024-03-29 22:44:28');
INSERT INTO `tb_admin_login_record` VALUES (219, 528534178275399, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-29 22:44:32');
INSERT INTO `tb_admin_login_record` VALUES (220, 528534178275399, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-29 22:45:08');
INSERT INTO `tb_admin_login_record` VALUES (221, 528534178275399, '0:0:0:0:0:0:0:1', '本地访问', 0, '2024-03-29 22:56:37');
INSERT INTO `tb_admin_login_record` VALUES (222, 528534178275399, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-29 22:56:48');
INSERT INTO `tb_admin_login_record` VALUES (223, 528534178275399, '0:0:0:0:0:0:0:1', '本地访问', 0, '2024-03-29 22:56:57');
INSERT INTO `tb_admin_login_record` VALUES (224, 528534178275333, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-29 22:57:02');
INSERT INTO `tb_admin_login_record` VALUES (225, 528534178275333, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-29 22:57:07');
INSERT INTO `tb_admin_login_record` VALUES (226, 528534178275333, '0:0:0:0:0:0:0:1', '本地访问', 0, '2024-03-29 22:58:26');
INSERT INTO `tb_admin_login_record` VALUES (227, 528534178275333, '0:0:0:0:0:0:0:1', '本地访问', 0, '2024-03-29 22:58:28');
INSERT INTO `tb_admin_login_record` VALUES (228, 528534178275399, '223.104.64.74', '广东省湛江市', 1, '2024-03-29 23:36:09');
INSERT INTO `tb_admin_login_record` VALUES (229, 528534178275399, '223.104.64.74', '广东省湛江市', 1, '2024-03-29 23:36:12');
INSERT INTO `tb_admin_login_record` VALUES (230, 528534178275399, '223.104.64.74', '广东省湛江市', 1, '2024-03-29 23:36:26');
INSERT INTO `tb_admin_login_record` VALUES (231, 528534178275399, '223.104.64.74', '广东省湛江市', 1, '2024-03-29 23:38:42');
INSERT INTO `tb_admin_login_record` VALUES (232, 528534178275399, '223.104.64.74', '广东省湛江市', 1, '2024-03-29 23:40:44');
INSERT INTO `tb_admin_login_record` VALUES (233, 528534178275399, '223.104.64.74', '广东省湛江市', 1, '2024-03-29 23:40:45');
INSERT INTO `tb_admin_login_record` VALUES (234, 528534178275399, '223.104.64.74', '广东省湛江市', 1, '2024-03-29 23:41:37');
INSERT INTO `tb_admin_login_record` VALUES (235, 528534178275399, '223.104.64.74', '广东省湛江市', 0, '2024-03-29 23:42:57');
INSERT INTO `tb_admin_login_record` VALUES (236, 528534178275399, '45.131.70.140', '00', 1, '2024-03-30 08:41:50');
INSERT INTO `tb_admin_login_record` VALUES (237, 528534178275399, '45.131.70.140', '00', 1, '2024-03-30 08:45:01');
INSERT INTO `tb_admin_login_record` VALUES (238, 528534178275331, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-30 17:34:16');
INSERT INTO `tb_admin_login_record` VALUES (239, 528534178275331, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-31 21:10:13');
INSERT INTO `tb_admin_login_record` VALUES (240, 528534178275331, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-04-01 15:45:25');
INSERT INTO `tb_admin_login_record` VALUES (241, 528534178275333, '45.9.2.15', '伦敦伦敦', 1, '2024-04-01 16:53:21');
INSERT INTO `tb_admin_login_record` VALUES (242, 528534178275399, '223.104.66.209', '广东省东莞市', 1, '2024-04-01 21:05:51');
INSERT INTO `tb_admin_login_record` VALUES (243, 528534178275399, '223.104.66.209', '广东省东莞市', 1, '2024-04-01 22:23:15');
INSERT INTO `tb_admin_login_record` VALUES (244, 528534178275333, '120.235.248.18', '广东省0', 1, '2024-04-02 20:51:25');
INSERT INTO `tb_admin_login_record` VALUES (245, 528534178275399, '223.104.65.48', '广东省湛江市', 0, '2024-04-02 21:18:31');
INSERT INTO `tb_admin_login_record` VALUES (246, 528534178275399, '223.104.65.48', '广东省湛江市', 0, '2024-04-02 21:18:31');
INSERT INTO `tb_admin_login_record` VALUES (247, 528534178275333, '223.104.65.48', '广东省湛江市', 1, '2024-04-02 21:18:39');
INSERT INTO `tb_admin_login_record` VALUES (248, 528534178275333, '0:0:0:0:0:0:0:1', '本地访问', 0, '2024-04-02 21:19:27');
INSERT INTO `tb_admin_login_record` VALUES (249, 528534178275333, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-04-02 21:19:32');
INSERT INTO `tb_admin_login_record` VALUES (250, 528534178275333, '223.104.65.48', '广东省湛江市', 1, '2024-04-02 21:19:57');
INSERT INTO `tb_admin_login_record` VALUES (251, 528534178275333, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-04-02 21:27:27');
INSERT INTO `tb_admin_login_record` VALUES (252, 528534178275355, '223.104.65.48', '广东省湛江市', 0, '2024-04-02 21:43:23');
INSERT INTO `tb_admin_login_record` VALUES (253, 528534178275355, '223.104.65.48', '广东省湛江市', 0, '2024-04-02 21:43:28');
INSERT INTO `tb_admin_login_record` VALUES (254, 528534178275333, '223.104.65.48', '广东省湛江市', 1, '2024-04-02 21:43:34');
INSERT INTO `tb_admin_login_record` VALUES (255, 528534178275333, '120.235.248.63', '广东省0', 0, '2024-04-03 17:25:23');
INSERT INTO `tb_admin_login_record` VALUES (256, 528534178275333, '120.235.248.63', '广东省0', 1, '2024-04-03 17:25:27');
INSERT INTO `tb_admin_login_record` VALUES (257, 528534178275355, '223.104.66.225', '广东省东莞市', 0, '2024-04-03 17:30:15');
INSERT INTO `tb_admin_login_record` VALUES (258, 528534178275333, '223.104.66.225', '广东省东莞市', 1, '2024-04-03 17:30:19');
INSERT INTO `tb_admin_login_record` VALUES (259, 528534178275399, '223.104.66.225', '广东省东莞市', 1, '2024-04-03 17:37:03');
INSERT INTO `tb_admin_login_record` VALUES (260, 528534178275399, '223.104.66.225', '广东省东莞市', 1, '2024-04-03 20:10:04');
INSERT INTO `tb_admin_login_record` VALUES (261, 528534178275333, '223.104.66.225', '广东省东莞市', 1, '2024-04-03 21:13:08');
INSERT INTO `tb_admin_login_record` VALUES (262, 528534178275331, '172.233.84.252', '德克萨斯达拉斯', 0, '2024-04-03 21:25:28');
INSERT INTO `tb_admin_login_record` VALUES (263, 528534178275333, '172.233.84.252', '德克萨斯达拉斯', 1, '2024-04-03 21:25:41');
INSERT INTO `tb_admin_login_record` VALUES (264, 528534178275399, '223.104.66.225', '广东省东莞市', 1, '2024-04-03 21:39:30');
INSERT INTO `tb_admin_login_record` VALUES (265, 528534178275333, '172.233.84.252', '德克萨斯达拉斯', 1, '2024-04-03 21:41:00');
INSERT INTO `tb_admin_login_record` VALUES (266, 528534178275333, '172.233.84.252', '德克萨斯达拉斯', 1, '2024-04-03 21:44:53');
INSERT INTO `tb_admin_login_record` VALUES (267, 528534178275333, '223.104.66.225', '广东省东莞市', 1, '2024-04-03 21:48:51');
INSERT INTO `tb_admin_login_record` VALUES (268, 528534178275333, '223.104.66.225', '广东省东莞市', 1, '2024-04-03 21:50:17');
INSERT INTO `tb_admin_login_record` VALUES (269, 528534178275333, '223.104.66.225', '广东省东莞市', 1, '2024-04-03 21:50:39');
INSERT INTO `tb_admin_login_record` VALUES (270, 528534178275333, '172.233.84.252', '德克萨斯达拉斯', 1, '2024-04-03 21:55:34');
INSERT INTO `tb_admin_login_record` VALUES (271, 528534178275333, '172.233.84.252', '德克萨斯达拉斯', 1, '2024-04-03 21:58:52');
INSERT INTO `tb_admin_login_record` VALUES (272, 532549819674629, '223.104.66.225', '广东省东莞市', 1, '2024-04-03 22:43:42');
INSERT INTO `tb_admin_login_record` VALUES (273, 532549819674629, '223.104.66.225', '广东省东莞市', 1, '2024-04-03 22:43:42');
INSERT INTO `tb_admin_login_record` VALUES (274, 528534178275399, '223.104.66.225', '广东省东莞市', 1, '2024-04-03 22:44:25');
INSERT INTO `tb_admin_login_record` VALUES (275, 528534178275399, '120.235.248.190', '广东省0', 1, '2024-04-04 00:00:49');
INSERT INTO `tb_admin_login_record` VALUES (276, 528534178275399, '223.104.66.225', '广东省东莞市', 1, '2024-04-04 08:10:47');
INSERT INTO `tb_admin_login_record` VALUES (277, 528534178275399, '223.104.66.225', '广东省东莞市', 1, '2024-04-04 08:11:56');
INSERT INTO `tb_admin_login_record` VALUES (278, 528534178275399, '223.104.66.225', '广东省东莞市', 1, '2024-04-04 09:18:39');
INSERT INTO `tb_admin_login_record` VALUES (279, 528534178275399, '120.235.248.190', '广东省0', 1, '2024-04-04 10:17:22');
INSERT INTO `tb_admin_login_record` VALUES (280, 528534178275399, '223.104.66.225', '广东省东莞市', 1, '2024-04-04 10:33:18');
INSERT INTO `tb_admin_login_record` VALUES (281, 528534178275399, '120.235.248.190', '广东省0', 1, '2024-04-04 10:59:45');
INSERT INTO `tb_admin_login_record` VALUES (282, 528534178275399, '223.104.66.225', '广东省东莞市', 1, '2024-04-04 11:00:00');
INSERT INTO `tb_admin_login_record` VALUES (283, 528534178275399, '223.104.66.225', '广东省东莞市', 1, '2024-04-04 11:01:46');
INSERT INTO `tb_admin_login_record` VALUES (284, 528534178275399, '223.104.66.225', '广东省东莞市', 1, '2024-04-04 11:02:16');
INSERT INTO `tb_admin_login_record` VALUES (285, 528534178275399, '223.104.66.225', '广东省东莞市', 1, '2024-04-04 11:03:09');
INSERT INTO `tb_admin_login_record` VALUES (286, 528534178275399, '120.235.248.190', '广东省0', 1, '2024-04-04 14:51:08');
INSERT INTO `tb_admin_login_record` VALUES (287, 528534178275399, '223.104.66.225', '广东省东莞市', 1, '2024-04-04 15:14:28');
INSERT INTO `tb_admin_login_record` VALUES (288, 528534178275333, '223.104.66.225', '广东省东莞市', 1, '2024-04-04 17:07:03');
INSERT INTO `tb_admin_login_record` VALUES (289, 528534178275399, '223.104.66.225', '广东省东莞市', 1, '2024-04-04 17:41:33');
INSERT INTO `tb_admin_login_record` VALUES (290, 528534178275399, '120.235.248.190', '广东省0', 1, '2024-04-04 19:18:28');
INSERT INTO `tb_admin_login_record` VALUES (291, 528534178275331, '120.235.248.219', '广东省0', 0, '2024-04-04 19:38:47');
INSERT INTO `tb_admin_login_record` VALUES (292, 528534178275331, '120.235.248.219', '广东省0', 0, '2024-04-04 19:38:53');
INSERT INTO `tb_admin_login_record` VALUES (293, 528534178275331, '120.235.248.219', '广东省0', 1, '2024-04-04 19:39:26');
INSERT INTO `tb_admin_login_record` VALUES (294, 528534178275333, '120.235.248.219', '广东省0', 1, '2024-04-04 19:40:12');
INSERT INTO `tb_admin_login_record` VALUES (295, 528534178275399, '223.104.66.225', '广东省东莞市', 1, '2024-04-04 21:05:40');
INSERT INTO `tb_admin_login_record` VALUES (296, 528534178275333, '223.104.66.225', '广东省东莞市', 1, '2024-04-05 10:19:02');
INSERT INTO `tb_admin_login_record` VALUES (297, 528534178275333, '223.104.66.225', '广东省东莞市', 1, '2024-04-05 10:19:16');
INSERT INTO `tb_admin_login_record` VALUES (298, 528534178275399, '223.104.66.225', '广东省东莞市', 1, '2024-04-05 10:22:17');
INSERT INTO `tb_admin_login_record` VALUES (299, 528534178275333, '223.104.66.225', '广东省东莞市', 1, '2024-04-05 10:27:21');
INSERT INTO `tb_admin_login_record` VALUES (300, 528534178275333, '223.104.66.225', '广东省东莞市', 1, '2024-04-05 10:27:21');
INSERT INTO `tb_admin_login_record` VALUES (301, 528534178275333, '223.104.66.225', '广东省东莞市', 1, '2024-04-05 10:27:21');
INSERT INTO `tb_admin_login_record` VALUES (302, 528534178275333, '223.104.66.225', '广东省东莞市', 1, '2024-04-05 10:27:21');
INSERT INTO `tb_admin_login_record` VALUES (303, 528534178275333, '223.104.66.225', '广东省东莞市', 1, '2024-04-05 10:27:21');
INSERT INTO `tb_admin_login_record` VALUES (304, 528534178275333, '223.104.66.225', '广东省东莞市', 1, '2024-04-05 10:28:12');
INSERT INTO `tb_admin_login_record` VALUES (305, 528534178275333, '223.104.66.225', '广东省东莞市', 0, '2024-04-05 10:31:41');
INSERT INTO `tb_admin_login_record` VALUES (306, 528534178275333, '223.104.66.225', '广东省东莞市', 0, '2024-04-05 10:31:41');
INSERT INTO `tb_admin_login_record` VALUES (307, 528534178275333, '223.104.66.225', '广东省东莞市', 0, '2024-04-05 10:31:49');
INSERT INTO `tb_admin_login_record` VALUES (308, 528534178275333, '223.104.66.225', '广东省东莞市', 0, '2024-04-05 10:31:51');
INSERT INTO `tb_admin_login_record` VALUES (309, 528534178275333, '223.104.66.225', '广东省东莞市', 0, '2024-04-05 10:31:57');
INSERT INTO `tb_admin_login_record` VALUES (310, 528534178275333, '223.104.66.225', '广东省东莞市', 0, '2024-04-05 10:31:57');
INSERT INTO `tb_admin_login_record` VALUES (311, 528534178275333, '223.104.66.225', '广东省东莞市', 1, '2024-04-05 10:32:11');
INSERT INTO `tb_admin_login_record` VALUES (312, 528534178275399, '120.235.248.190', '广东省0', 1, '2024-04-05 10:33:41');
INSERT INTO `tb_admin_login_record` VALUES (313, 528534178275333, '223.104.66.225', '广东省东莞市', 1, '2024-04-05 10:34:16');
INSERT INTO `tb_admin_login_record` VALUES (314, 528534178275333, '223.104.66.225', '广东省东莞市', 1, '2024-04-05 10:42:52');
INSERT INTO `tb_admin_login_record` VALUES (315, 528534178275399, '120.235.248.190', '广东省0', 1, '2024-04-05 10:45:19');
INSERT INTO `tb_admin_login_record` VALUES (316, 528534178275333, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-04-05 10:46:41');
INSERT INTO `tb_admin_login_record` VALUES (317, 528534178275333, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-04-05 10:50:01');
INSERT INTO `tb_admin_login_record` VALUES (318, 528534178275355, '223.104.66.225', '广东省东莞市', 0, '2024-04-05 10:51:49');
INSERT INTO `tb_admin_login_record` VALUES (319, 528534178275355, '223.104.66.225', '广东省东莞市', 0, '2024-04-05 10:51:51');
INSERT INTO `tb_admin_login_record` VALUES (320, 528534178275333, '223.104.66.225', '广东省东莞市', 1, '2024-04-05 10:51:55');
INSERT INTO `tb_admin_login_record` VALUES (321, 528534178275399, '223.104.66.225', '广东省东莞市', 1, '2024-04-05 10:54:39');
INSERT INTO `tb_admin_login_record` VALUES (322, 528534178275333, '223.104.66.225', '广东省东莞市', 1, '2024-04-05 11:28:07');
INSERT INTO `tb_admin_login_record` VALUES (323, 528534178275333, '223.104.66.225', '广东省东莞市', 1, '2024-04-05 11:28:07');
INSERT INTO `tb_admin_login_record` VALUES (324, 528534178275333, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-04-05 13:05:16');
INSERT INTO `tb_admin_login_record` VALUES (325, 528534178275333, '223.104.66.225', '广东省东莞市', 1, '2024-04-05 13:06:47');
INSERT INTO `tb_admin_login_record` VALUES (326, 528534178275333, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-04-05 13:07:56');
INSERT INTO `tb_admin_login_record` VALUES (327, 528534178275333, '223.104.66.225', '广东省东莞市', 1, '2024-04-05 13:18:55');
INSERT INTO `tb_admin_login_record` VALUES (328, 528534178275333, '223.104.66.225', '广东省东莞市', 1, '2024-04-05 13:19:38');
INSERT INTO `tb_admin_login_record` VALUES (329, 528534178275333, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-04-05 13:43:36');
INSERT INTO `tb_admin_login_record` VALUES (330, 528534178275333, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-04-05 13:45:45');
INSERT INTO `tb_admin_login_record` VALUES (331, 528534178275333, '223.104.66.225', '广东省东莞市', 1, '2024-04-05 15:24:19');
INSERT INTO `tb_admin_login_record` VALUES (332, 528534178275333, '223.104.66.225', '广东省东莞市', 0, '2024-04-06 09:49:14');
INSERT INTO `tb_admin_login_record` VALUES (333, 528534178275399, '223.104.66.225', '广东省东莞市', 1, '2024-04-06 09:49:23');
INSERT INTO `tb_admin_login_record` VALUES (334, 528534178275331, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-04-06 17:19:26');
INSERT INTO `tb_admin_login_record` VALUES (335, 528534178275399, '120.235.248.191', '广东省0', 1, '2024-04-07 12:57:09');
INSERT INTO `tb_admin_login_record` VALUES (336, 528534178275333, '223.104.66.225', '广东省东莞市', 0, '2024-04-07 16:38:40');
INSERT INTO `tb_admin_login_record` VALUES (337, 528534178275333, '223.104.66.225', '广东省东莞市', 0, '2024-04-07 16:38:41');
INSERT INTO `tb_admin_login_record` VALUES (338, 528534178275399, '223.104.66.225', '广东省东莞市', 1, '2024-04-07 16:38:47');
INSERT INTO `tb_admin_login_record` VALUES (339, 528534178275333, '223.104.66.225', '广东省东莞市', 1, '2024-04-07 16:48:22');
INSERT INTO `tb_admin_login_record` VALUES (340, 528534178275399, '117.136.79.194', '广东省茂名市', 1, '2024-04-07 20:56:12');
INSERT INTO `tb_admin_login_record` VALUES (341, 528534178275399, '209.141.36.119', '内华达拉斯维加斯', 1, '2024-04-07 22:47:31');
INSERT INTO `tb_admin_login_record` VALUES (342, 528534178275399, '223.104.65.56', '广东省湛江市', 1, '2024-04-07 22:56:08');
INSERT INTO `tb_admin_login_record` VALUES (343, 528534178275399, '223.104.65.56', '广东省湛江市', 1, '2024-04-07 22:56:09');
INSERT INTO `tb_admin_login_record` VALUES (344, 528534178275399, '223.104.65.56', '广东省湛江市', 1, '2024-04-07 22:56:09');
INSERT INTO `tb_admin_login_record` VALUES (345, 528534178275399, '223.104.65.56', '广东省湛江市', 1, '2024-04-07 22:56:09');
INSERT INTO `tb_admin_login_record` VALUES (346, 528534178275399, '223.104.65.56', '广东省湛江市', 1, '2024-04-07 22:56:10');
INSERT INTO `tb_admin_login_record` VALUES (347, 528534178275333, '223.104.65.56', '广东省湛江市', 1, '2024-04-08 17:32:50');
INSERT INTO `tb_admin_login_record` VALUES (348, 528534178275399, '223.104.65.56', '广东省湛江市', 1, '2024-04-08 17:38:27');
INSERT INTO `tb_admin_login_record` VALUES (349, 528534178275333, '223.104.65.56', '广东省湛江市', 1, '2024-04-08 17:52:07');
INSERT INTO `tb_admin_login_record` VALUES (350, 528534178275399, '223.104.65.56', '广东省湛江市', 1, '2024-04-08 17:57:18');
INSERT INTO `tb_admin_login_record` VALUES (351, 528534178275333, '223.104.65.56', '广东省湛江市', 1, '2024-04-08 18:05:15');
INSERT INTO `tb_admin_login_record` VALUES (352, 528534178275399, '223.104.65.56', '广东省湛江市', 1, '2024-04-08 20:39:07');
INSERT INTO `tb_admin_login_record` VALUES (353, 528534178275333, '223.104.65.56', '广东省湛江市', 1, '2024-04-08 20:45:08');
INSERT INTO `tb_admin_login_record` VALUES (354, 528534178275399, '45.9.2.15', '伦敦伦敦', 1, '2024-04-08 22:08:18');
INSERT INTO `tb_admin_login_record` VALUES (355, 528534178275333, '223.104.65.56', '广东省湛江市', 1, '2024-04-08 22:23:15');
INSERT INTO `tb_admin_login_record` VALUES (356, 528534178275333, '223.104.65.56', '广东省湛江市', 1, '2024-04-08 22:23:15');
INSERT INTO `tb_admin_login_record` VALUES (357, 528534178275333, '223.104.65.56', '广东省湛江市', 1, '2024-04-09 10:20:38');
INSERT INTO `tb_admin_login_record` VALUES (358, 528534178275333, '223.104.65.56', '广东省湛江市', 1, '2024-04-09 10:20:38');
INSERT INTO `tb_admin_login_record` VALUES (359, 528534178275333, '223.104.65.56', '广东省湛江市', 1, '2024-04-09 10:20:38');
INSERT INTO `tb_admin_login_record` VALUES (360, 528534178275399, '223.104.65.56', '广东省湛江市', 1, '2024-04-09 10:21:06');
INSERT INTO `tb_admin_login_record` VALUES (361, 528534178275333, '223.104.65.56', '广东省湛江市', 1, '2024-04-09 10:34:13');
INSERT INTO `tb_admin_login_record` VALUES (362, 528534178275331, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-04-09 12:04:39');
INSERT INTO `tb_admin_login_record` VALUES (363, 528534178275333, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-04-09 12:14:47');
INSERT INTO `tb_admin_login_record` VALUES (364, 528534178275333, '223.104.65.56', '广东省湛江市', 1, '2024-04-09 13:07:49');
INSERT INTO `tb_admin_login_record` VALUES (365, 528534178275333, '223.104.65.56', '广东省湛江市', 1, '2024-04-09 13:08:27');
INSERT INTO `tb_admin_login_record` VALUES (366, 528534178275331, '142.171.204.195', '安大略0', 1, '2024-04-09 16:23:00');
INSERT INTO `tb_admin_login_record` VALUES (367, 528534178275333, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-04-09 17:38:38');
INSERT INTO `tb_admin_login_record` VALUES (368, 528534178275331, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-04-09 17:38:50');
INSERT INTO `tb_admin_login_record` VALUES (369, 528534178275331, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-04-09 17:44:21');
INSERT INTO `tb_admin_login_record` VALUES (370, 528534178275333, '223.104.65.56', '广东省湛江市', 1, '2024-04-09 18:51:39');
INSERT INTO `tb_admin_login_record` VALUES (371, 528534178275333, '223.104.65.56', '广东省湛江市', 1, '2024-04-09 18:51:39');
INSERT INTO `tb_admin_login_record` VALUES (372, 528534178275333, '223.104.65.56', '广东省湛江市', 1, '2024-04-09 18:51:39');
INSERT INTO `tb_admin_login_record` VALUES (373, 528534178275333, '223.104.65.56', '广东省湛江市', 1, '2024-04-09 18:51:39');
INSERT INTO `tb_admin_login_record` VALUES (374, 528534178275333, '223.104.65.56', '广东省湛江市', 1, '2024-04-09 18:51:40');
INSERT INTO `tb_admin_login_record` VALUES (375, 528534178275333, '142.171.204.195', '安大略0', 1, '2024-04-09 20:37:53');
INSERT INTO `tb_admin_login_record` VALUES (376, 528534178275399, '142.171.204.195', '安大略0', 0, '2024-04-09 20:42:45');
INSERT INTO `tb_admin_login_record` VALUES (377, 528534178275399, '142.171.204.195', '安大略0', 1, '2024-04-09 20:42:51');
INSERT INTO `tb_admin_login_record` VALUES (378, 528534178275399, '223.104.65.56', '广东省湛江市', 1, '2024-04-09 20:48:59');
INSERT INTO `tb_admin_login_record` VALUES (379, 532549819674629, '142.171.204.195', '安大略0', 1, '2024-04-09 20:51:13');
INSERT INTO `tb_admin_login_record` VALUES (380, 528534178275399, '142.171.204.195', '安大略0', 1, '2024-04-09 20:57:35');
INSERT INTO `tb_admin_login_record` VALUES (381, 528534178275399, '223.104.65.56', '广东省湛江市', 1, '2024-04-09 20:59:05');
INSERT INTO `tb_admin_login_record` VALUES (382, 528534178275331, '142.171.204.195', '安大略0', 1, '2024-04-09 21:33:09');
INSERT INTO `tb_admin_login_record` VALUES (383, 528534178275331, '120.235.248.254', '广东省0', 1, '2024-04-09 23:41:44');
INSERT INTO `tb_admin_login_record` VALUES (384, 528534178275333, '223.104.65.56', '广东省湛江市', 1, '2024-04-10 00:03:53');
INSERT INTO `tb_admin_login_record` VALUES (385, 528534178275331, '120.235.248.254', '广东省0', 1, '2024-04-10 00:11:07');
INSERT INTO `tb_admin_login_record` VALUES (386, 528534178275331, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-04-10 00:36:50');
INSERT INTO `tb_admin_login_record` VALUES (387, 528534178275399, '117.136.41.93', '广东省茂名市', 1, '2024-04-10 12:58:20');
INSERT INTO `tb_admin_login_record` VALUES (388, 528534178275399, '117.136.41.93', '广东省茂名市', 1, '2024-04-10 13:40:25');
INSERT INTO `tb_admin_login_record` VALUES (389, 528534178275399, '117.136.41.93', '广东省茂名市', 1, '2024-04-10 13:40:26');
INSERT INTO `tb_admin_login_record` VALUES (390, 528534178275333, '223.104.66.222', '广东省东莞市', 1, '2024-04-10 15:27:37');
INSERT INTO `tb_admin_login_record` VALUES (391, 528534178275333, '223.104.66.222', '广东省东莞市', 1, '2024-04-10 18:30:17');
INSERT INTO `tb_admin_login_record` VALUES (392, 528534178275333, '223.104.66.222', '广东省东莞市', 1, '2024-04-10 18:30:17');
INSERT INTO `tb_admin_login_record` VALUES (393, 528534178275399, '223.104.66.222', '广东省东莞市', 1, '2024-04-10 20:38:21');
INSERT INTO `tb_admin_login_record` VALUES (394, 528534178275399, '223.104.66.222', '广东省东莞市', 1, '2024-04-10 20:38:21');
INSERT INTO `tb_admin_login_record` VALUES (395, 528534178275331, '142.171.204.195', '安大略0', 1, '2024-04-10 22:37:25');
INSERT INTO `tb_admin_login_record` VALUES (396, 528534178275399, '223.104.66.222', '广东省东莞市', 1, '2024-04-11 10:45:27');
INSERT INTO `tb_admin_login_record` VALUES (397, 528534178275399, '223.104.66.222', '广东省东莞市', 1, '2024-04-11 10:47:28');
INSERT INTO `tb_admin_login_record` VALUES (398, 528534178275331, '120.235.248.16', '广东省0', 1, '2024-04-11 13:18:29');
INSERT INTO `tb_admin_login_record` VALUES (399, 528534178275399, '142.171.204.195', '安大略0', 0, '2024-04-11 13:20:59');
INSERT INTO `tb_admin_login_record` VALUES (400, 528534178275399, '142.171.204.195', '安大略0', 1, '2024-04-11 13:21:02');
INSERT INTO `tb_admin_login_record` VALUES (401, 532549819674629, '142.171.204.195', '安大略0', 1, '2024-04-11 13:24:03');
INSERT INTO `tb_admin_login_record` VALUES (402, 528534178275331, '120.235.248.16', '广东省0', 1, '2024-04-11 13:35:35');
INSERT INTO `tb_admin_login_record` VALUES (403, 532549819674629, '142.171.204.195', '安大略0', 1, '2024-04-11 13:39:19');
INSERT INTO `tb_admin_login_record` VALUES (404, 528534178275331, '120.235.248.16', '广东省0', 1, '2024-04-11 13:45:15');
INSERT INTO `tb_admin_login_record` VALUES (405, 528534178275331, '142.171.204.195', '安大略0', 1, '2024-04-11 13:55:10');
INSERT INTO `tb_admin_login_record` VALUES (406, 528534178275331, '142.171.204.195', '安大略0', 1, '2024-04-11 13:59:25');
INSERT INTO `tb_admin_login_record` VALUES (407, 528534178275331, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 14:26:00');
INSERT INTO `tb_admin_login_record` VALUES (408, 528534178275399, '223.104.66.222', '广东省东莞市', 1, '2024-04-11 14:33:51');
INSERT INTO `tb_admin_login_record` VALUES (409, 528534178275399, '223.104.66.222', '广东省东莞市', 1, '2024-04-11 14:35:19');
INSERT INTO `tb_admin_login_record` VALUES (410, 528534178275399, '223.104.66.222', '广东省东莞市', 1, '2024-04-11 14:38:22');
INSERT INTO `tb_admin_login_record` VALUES (411, 528534178275399, '45.9.2.15', '伦敦伦敦', 1, '2024-04-11 14:40:22');
INSERT INTO `tb_admin_login_record` VALUES (412, 528534178275399, '223.104.66.222', '广东省东莞市', 1, '2024-04-11 14:48:18');
INSERT INTO `tb_admin_login_record` VALUES (413, 528534178275331, '142.171.204.195', '安大略0', 1, '2024-04-11 15:11:39');
INSERT INTO `tb_admin_login_record` VALUES (414, 528534178275399, '223.104.66.222', '广东省东莞市', 1, '2024-04-11 15:28:17');
INSERT INTO `tb_admin_login_record` VALUES (415, 528534178275399, '223.104.66.222', '广东省东莞市', 1, '2024-04-11 15:29:12');
INSERT INTO `tb_admin_login_record` VALUES (416, 528534178275399, '223.104.66.222', '广东省东莞市', 1, '2024-04-11 15:30:13');
INSERT INTO `tb_admin_login_record` VALUES (417, 528534178275331, '142.171.204.195', '安大略0', 1, '2024-04-11 15:30:19');
INSERT INTO `tb_admin_login_record` VALUES (418, 528534178275333, '142.171.204.195', '安大略0', 1, '2024-04-11 15:36:19');
INSERT INTO `tb_admin_login_record` VALUES (419, 528534178275399, '223.104.66.222', '广东省东莞市', 1, '2024-04-11 15:49:59');
INSERT INTO `tb_admin_login_record` VALUES (420, 528534178275399, '223.104.66.222', '广东省东莞市', 1, '2024-04-11 15:49:59');
INSERT INTO `tb_admin_login_record` VALUES (421, 528534178275399, '223.104.66.222', '广东省东莞市', 1, '2024-04-11 15:50:55');
INSERT INTO `tb_admin_login_record` VALUES (422, 528534178275331, '142.171.204.195', '安大略0', 1, '2024-04-11 15:57:59');
INSERT INTO `tb_admin_login_record` VALUES (423, 528534178275399, '223.104.66.222', '广东省东莞市', 1, '2024-04-11 16:33:18');
INSERT INTO `tb_admin_login_record` VALUES (424, 528534178275399, '223.104.66.222', '广东省东莞市', 1, '2024-04-11 16:34:37');
INSERT INTO `tb_admin_login_record` VALUES (425, 528534178275399, '223.104.66.222', '广东省东莞市', 1, '2024-04-11 16:55:33');
INSERT INTO `tb_admin_login_record` VALUES (426, 528534178275399, '223.104.66.222', '广东省东莞市', 1, '2024-04-11 17:17:25');
INSERT INTO `tb_admin_login_record` VALUES (427, 528534178275399, '223.104.66.222', '广东省东莞市', 1, '2024-04-11 17:17:25');
INSERT INTO `tb_admin_login_record` VALUES (428, 528534178275399, '223.104.66.222', '广东省东莞市', 1, '2024-04-11 17:17:58');
INSERT INTO `tb_admin_login_record` VALUES (429, 528534178275399, '223.104.66.222', '广东省东莞市', 1, '2024-04-11 17:58:26');
INSERT INTO `tb_admin_login_record` VALUES (430, 528534178275399, '223.104.66.222', '广东省东莞市', 1, '2024-04-11 17:58:26');
INSERT INTO `tb_admin_login_record` VALUES (431, 528534178275399, '223.104.66.222', '广东省东莞市', 1, '2024-04-11 17:58:26');
INSERT INTO `tb_admin_login_record` VALUES (432, 528534178275331, '120.235.248.237', '广东省0', 1, '2024-04-11 18:50:46');
INSERT INTO `tb_admin_login_record` VALUES (433, 528534178275399, '223.104.66.222', '广东省东莞市', 1, '2024-04-11 18:58:02');
INSERT INTO `tb_admin_login_record` VALUES (434, 528534178275399, '223.104.66.222', '广东省东莞市', 1, '2024-04-11 18:58:31');
INSERT INTO `tb_admin_login_record` VALUES (435, 528534178275333, '223.104.66.222', '广东省东莞市', 1, '2024-04-11 19:02:16');
INSERT INTO `tb_admin_login_record` VALUES (436, 528534178275399, '223.104.66.222', '广东省东莞市', 1, '2024-04-11 19:04:21');
INSERT INTO `tb_admin_login_record` VALUES (437, 528534178275399, '223.104.66.222', '广东省东莞市', 1, '2024-04-11 19:05:10');
INSERT INTO `tb_admin_login_record` VALUES (438, 528534178275333, '120.235.248.237', '广东省0', 1, '2024-04-11 19:08:25');
INSERT INTO `tb_admin_login_record` VALUES (439, 528534178275331, '120.82.32.155', '广东省湛江市', 0, '2024-04-11 19:12:40');
INSERT INTO `tb_admin_login_record` VALUES (440, 528534178275331, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 19:12:42');
INSERT INTO `tb_admin_login_record` VALUES (441, 528534178275333, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 19:13:15');
INSERT INTO `tb_admin_login_record` VALUES (442, 528534178275399, '223.104.66.222', '广东省东莞市', 1, '2024-04-11 19:14:39');
INSERT INTO `tb_admin_login_record` VALUES (443, 528534178275333, '223.104.66.222', '广东省东莞市', 1, '2024-04-11 19:19:27');

-- ----------------------------
-- Table structure for tb_admin_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_admin_user`;
CREATE TABLE `tb_admin_user`  (
  `id` bigint NOT NULL,
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'Admin',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `school` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `delete_flag` tinyint(1) NOT NULL DEFAULT 1,
  `role` tinyint(1) NOT NULL DEFAULT 0 COMMENT '默认false为普通学校管理员',
  `create_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_admin_user
-- ----------------------------
INSERT INTO `tb_admin_user` VALUES (528534178275331, 'admin', 'admin', '13999999999', '45c7aec583fdda457c41584d073fd255f962cf09541233852d8b235ab34fe921', '10566', 0, 0, '2024-03-23 21:24:13', '2024-03-23 21:38:28');
INSERT INTO `tb_admin_user` VALUES (528534178275333, 'super', 'super', '13888888888', '45c7aec583fdda457c41584d073fd255f962cf09541233852d8b235ab34fe921', '', 0, 1, '2024-03-23 13:51:13', '2024-03-23 13:53:16');
INSERT INTO `tb_admin_user` VALUES (528534178275355, 'super1', 'super1', '13711111111', '45c7aec583fdda457c41584d073fd255f962cf09541233852d8b235ab34fe921', '', 1, 1, '2024-04-02 21:24:10', '2024-04-02 21:24:28');
INSERT INTO `tb_admin_user` VALUES (528534178275399, 'school', 'school', '12777777777', '45c7aec583fdda457c41584d073fd255f962cf09541233852d8b235ab34fe921', '10566', 0, 0, '2024-03-25 16:02:54', '2024-03-25 16:03:38');
INSERT INTO `tb_admin_user` VALUES (532549819674629, 'GDOU', 'GDOU', '18686719805', '45c7aec583fdda457c41584d073fd255f962cf09541233852d8b235ab34fe921', '10566', 0, 0, '2024-04-03 22:10:47', '2024-04-03 22:10:47');
INSERT INTO `tb_admin_user` VALUES (532551993552901, 'GDOU1', 'GDOU1', '18686719809', '45c7aec583fdda457c41584d073fd255f962cf09541233852d8b235ab34fe921', '10566', 0, 0, '2024-04-03 22:19:38', '2024-04-03 22:19:38');

-- ----------------------------
-- Table structure for tb_advertisement
-- ----------------------------
DROP TABLE IF EXISTS `tb_advertisement`;
CREATE TABLE `tb_advertisement`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `topic` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `cover_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `content_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `audit_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `publish_days` int NOT NULL COMMENT '发布天数',
  `publish_user_id` bigint NOT NULL,
  `audit_id` bigint NULL DEFAULT NULL,
  `for_region` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `for_school` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `for_college` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `for_gender` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `submit_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `publish_time` timestamp NULL DEFAULT NULL COMMENT '发布时间',
  `expire_time` timestamp NULL DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_advertisement
-- ----------------------------
INSERT INTO `tb_advertisement` VALUES (11, '考研', '考研考研，只考一年', '助你早日上岸', 'https://turingminio.raqtpie.xyz:49000/bud-student/3c98cbf4-e279-4ebe-9892-4de28f138b16.jpeg', 'https://turingminio.raqtpie.xyz:49000/bud-student/23b13776-9084-431b-bf12-4cc053926245.jpeg', '待发布', 10, 525048316940293, 528534178275333, NULL, NULL, NULL, NULL, '2024-04-05 15:22:20', '2024-04-11 15:43:11', NULL, NULL);
INSERT INTO `tb_advertisement` VALUES (13, '美食', '有味道餐厅开业了', '有味道餐厅开业特惠，全场八折', 'https://turingminio.raqtpie.xyz:49000/bud-student/531c26e9-2dab-4e5c-89e8-fa7a6cf1393c.jpeg', 'https://turingminio.raqtpie.xyz:49000/bud-student/370a61e4-e2a4-4338-9a31-39dff94ccdf1.jpeg', '发布中', 10, 525048316940293, 528534178275333, '', '', '', '', '2024-04-09 20:34:19', '2024-04-09 20:59:24', '2024-04-09 20:41:37', '2024-04-19 20:41:37');
INSERT INTO `tb_advertisement` VALUES (14, '美食', '三九食堂回馈老用户有礼', '三九食堂是一家有着多年历史的食堂...', 'https://turingminio.raqtpie.xyz:49000/bud-student/c3c3254c-5bc0-437a-9601-37cc97964595.jpeg', 'https://turingminio.raqtpie.xyz:49000/bud-student/6db24460-434c-4c9e-b809-e82a34f2480f.jpeg', '发布中', 9, 525048316940293, 528534178275333, '', '', '', '', '2024-04-09 20:37:04', '2024-04-09 20:59:24', '2024-04-09 20:41:40', '2024-04-18 20:41:40');
INSERT INTO `tb_advertisement` VALUES (15, '活动', '校园足球赛即将开始', '为了丰富校园生活，提高同学们的体育锻炼...', 'https://turingminio.raqtpie.xyz:49000/bud-student/20592d13-71fe-45d9-8dfc-606d96c56253.jpeg', 'https://turingminio.raqtpie.xyz:49000/bud-student/4fcfc221-07fa-4763-a160-a54794b72c32.jpeg', '发布中', 5, 528534178275399, 528534178275333, '', '', '', '', '2024-04-09 20:47:37', '2024-04-09 20:59:24', '2024-04-09 20:57:50', '2024-04-14 20:57:50');
INSERT INTO `tb_advertisement` VALUES (16, '学术', '计算机学院举办学术讲座', '本次讲座将邀请国内知名专家...', 'https://turingminio.raqtpie.xyz:49000/bud-student/3316e871-fe26-4903-a9cd-27a69ac228a5.jpeg', 'https://turingminio.raqtpie.xyz:49000/bud-student/8f5eaa0e-220c-465d-b433-6ff48ec98bb9.jpeg', '发布中', 7, 528534178275399, 528534178275333, '', '', '', '', '2024-04-09 20:48:56', '2024-04-09 20:59:24', '2024-04-09 20:57:54', '2024-04-16 20:57:54');
INSERT INTO `tb_advertisement` VALUES (17, '活动', '校园迎新晚会节目征集', '为欢迎新生入学，学校将举办迎新晚会。现诚邀各位同学积极参与，可报名表演歌舞、小品、音乐等节目，展示你的才艺，与新生共度欢乐时光。报名截止日期为XX月XX日，有意者请尽快报名。', 'https://turingminio.raqtpie.xyz:49000/bud-student/5cf59f5c-956d-4890-b7a2-4acd404b7673.jpeg', 'https://turingminio.raqtpie.xyz:49000/bud-student/d33a24ee-70a7-43c9-a30c-8095e4f975ef.jpeg', '发布中', 7, 532549819674629, 528534178275333, '', '', '', '', '2024-04-09 20:51:35', '2024-04-09 20:59:24', '2024-04-09 20:57:11', '2024-04-16 20:57:11');
INSERT INTO `tb_advertisement` VALUES (18, '健康', '校园健身俱乐部开放日', '为倡导健康生活方式，校园健身俱乐部将举办开放日活动。届时将有专业教练现场指导健身技巧，还将有体质测试、健康讲座等丰富活动，欢迎广大同学踊跃参与。', 'https://turingminio.raqtpie.xyz:49000/bud-student/c59d2085-3d0b-4848-ad7e-be09782e4e6d.jpeg', 'https://turingminio.raqtpie.xyz:49000/bud-student/2a2a7f5a-8f1c-4e83-a0cc-45701e70aef6.jpeg', '发布中', 6, 532549819674629, 528534178275333, '', '', '', '', '2024-04-09 21:03:21', '2024-04-09 21:10:25', '2024-04-09 21:10:25', '2024-04-15 21:10:25');
INSERT INTO `tb_advertisement` VALUES (19, '美食', '不容错过的美食', '不容错过的大众美食火锅，你一定要来试试！', 'https://turingminio.raqtpie.xyz:49000/bud-student/bd4e3555-f814-40ea-b81b-915e04a76d17.jpeg', 'https://turingminio.raqtpie.xyz:49000/bud-student/f900a5e6-5de7-456a-bae3-66deb7ee0886.jpeg', '发布中', 10, 525048316940293, 528534178275333, NULL, NULL, NULL, NULL, '2024-04-10 19:11:10', '2024-04-10 19:32:11', '2024-04-10 19:32:12', '2024-04-20 19:32:12');
INSERT INTO `tb_advertisement` VALUES (20, '美食', '不容错过的美食', '不容错过的大众美食火锅，你一定要来试试！', 'https://turingminio.raqtpie.xyz:49000/bud-student/38281ae3-914a-4d32-aa77-3213d8539e91.jpeg', 'https://turingminio.raqtpie.xyz:49000/bud-student/8842465c-36b4-4adc-b6c4-4cdca560a3bd.jpeg', '审核未通过', 10, 525048316940293, 528534178275333, NULL, NULL, NULL, NULL, '2024-04-10 19:11:10', '2024-04-10 19:11:10', NULL, NULL);

-- ----------------------------
-- Table structure for tb_college
-- ----------------------------
DROP TABLE IF EXISTS `tb_college`;
CREATE TABLE `tb_college`  (
  `school_code` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `collage_code` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `introduction` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `website_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_college
-- ----------------------------
INSERT INTO `tb_college` VALUES ('10566', '1001', '化学与材料学院', '化学与材料学院是...', 'http://huaxueyucailiaoxueyuan.com');
INSERT INTO `tb_college` VALUES ('10566', '1002', '生物与环境科学学院', '生物与环境科学学院是...', 'http://shengwuyuhuanjingkexuexueyuan.com');
INSERT INTO `tb_college` VALUES ('10566', '1003', '经济与管理学院', '经济与管理学院是...', 'http://jingjiyuguanlixueyuan.com');
INSERT INTO `tb_college` VALUES ('10566', '1004', '艺术与传媒学院', '艺术与传媒学院是...', 'http://yishuyuchuanmeixueyuan.com');
INSERT INTO `tb_college` VALUES ('10566', '1005', '数学与计算机学院', '数学与计算机学院是...', 'http://shuxueyujisuanjixueyuan.com');
INSERT INTO `tb_college` VALUES ('10566', '1006', '体育与休闲学院', '体育学院是......', 'http://tiyuxueyuan.com');

-- ----------------------------
-- Table structure for tb_college2major
-- ----------------------------
DROP TABLE IF EXISTS `tb_college2major`;
CREATE TABLE `tb_college2major`  (
  `school_code` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `college_code` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `major_code` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`school_code`, `college_code`, `major_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_college2major
-- ----------------------------
INSERT INTO `tb_college2major` VALUES ('10566', '1003', '1009');
INSERT INTO `tb_college2major` VALUES ('10566', '1003', '1010');
INSERT INTO `tb_college2major` VALUES ('10566', '1005', '1001');
INSERT INTO `tb_college2major` VALUES ('10566', '1005', '1002');
INSERT INTO `tb_college2major` VALUES ('10566', '1005', '1003');
INSERT INTO `tb_college2major` VALUES ('10566', '1006', '1006');
INSERT INTO `tb_college2major` VALUES ('10566', '1006', '1007');
INSERT INTO `tb_college2major` VALUES ('10566', '1006', '1008');

-- ----------------------------
-- Table structure for tb_gift
-- ----------------------------
DROP TABLE IF EXISTS `tb_gift`;
CREATE TABLE `tb_gift`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `max_exchange` int NOT NULL DEFAULT 0 COMMENT '最大的兑换次数，0表示无限制',
  `photo_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `point` int NOT NULL,
  `school_code` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_id` bigint NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_gift
-- ----------------------------
INSERT INTO `tb_gift` VALUES (29, '鼠标垫', '精美鼠标垫', 98, 'https://turingminio.raqtpie.xyz:49000/bud-student/985641f9-19cc-41a5-9479-329f27ea7d5e.jpeg', 200, '10566', 528534178275399, '2024-04-10 13:27:20');
INSERT INTO `tb_gift` VALUES (30, '书签', '精美书签', 295, 'https://turingminio.raqtpie.xyz:49000/bud-student/1c8abc7a-4617-4a7b-9211-67550cdee1eb.jpeg', 50, '10566', 528534178275399, '2024-04-10 13:27:58');
INSERT INTO `tb_gift` VALUES (31, '键盘', '精美键盘', 50, 'https://turingminio.raqtpie.xyz:49000/bud-student/5ced63f9-3c67-4b1b-b231-1fe433dd5486.png', 500, '10566', 528534178275399, '2024-04-10 13:28:27');
INSERT INTO `tb_gift` VALUES (33, '帆布袋', '精美帆布袋', 30, 'https://turingminio.raqtpie.xyz:49000/bud-student/d9a99d71-af0b-4e15-9db2-7d70fb7ea489.png', 150, '10566', 528534178275399, '2024-04-11 19:10:21');

-- ----------------------------
-- Table structure for tb_major
-- ----------------------------
DROP TABLE IF EXISTS `tb_major`;
CREATE TABLE `tb_major`  (
  `major_code` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `degree_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `duration` int NOT NULL,
  PRIMARY KEY (`major_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_major
-- ----------------------------
INSERT INTO `tb_major` VALUES ('1001', '计算机科学与技术', '工科', 4);
INSERT INTO `tb_major` VALUES ('1002', '软件工程', '工科', 4);
INSERT INTO `tb_major` VALUES ('1003', '物联网', '工科', 4);
INSERT INTO `tb_major` VALUES ('1004', '大数据', '工科', 4);
INSERT INTO `tb_major` VALUES ('1005', '信息管理', '工科', 4);
INSERT INTO `tb_major` VALUES ('1006', '汉语言文学', '文科', 4);
INSERT INTO `tb_major` VALUES ('1007', '外国语文学', '文科', 4);
INSERT INTO `tb_major` VALUES ('1008', '新闻学', '文科', 4);
INSERT INTO `tb_major` VALUES ('1009', '土地管理', '管理类', 4);
INSERT INTO `tb_major` VALUES ('1010', '财政管理', '管理类', 4);

-- ----------------------------
-- Table structure for tb_merchant_login_record
-- ----------------------------
DROP TABLE IF EXISTS `tb_merchant_login_record`;
CREATE TABLE `tb_merchant_login_record`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `merchant_id` bigint NOT NULL,
  `ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `status` tinyint(1) NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 80 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_merchant_login_record
-- ----------------------------
INSERT INTO `tb_merchant_login_record` VALUES (1, 525048316940293, '0:0:0:0:0:0:0:1', '未知', 1, '2024-03-13 17:27:37');
INSERT INTO `tb_merchant_login_record` VALUES (2, 525048316940293, '0:0:0:0:0:0:0:1', '未知', 1, '2024-03-13 17:39:40');
INSERT INTO `tb_merchant_login_record` VALUES (3, 525048316940293, '0:0:0:0:0:0:0:1', '未知', 0, '2024-03-13 22:00:35');
INSERT INTO `tb_merchant_login_record` VALUES (4, 525048316940293, '0:0:0:0:0:0:0:1', '未知', 1, '2024-03-13 22:00:39');
INSERT INTO `tb_merchant_login_record` VALUES (5, 525048316940293, '0:0:0:0:0:0:0:1', '未知', 1, '2024-03-18 22:24:34');
INSERT INTO `tb_merchant_login_record` VALUES (6, 525048316940293, '0:0:0:0:0:0:0:1', '未知', 1, '2024-03-19 16:03:04');
INSERT INTO `tb_merchant_login_record` VALUES (7, 525048316940293, '0:0:0:0:0:0:0:1', '未知', 1, '2024-03-19 18:23:13');
INSERT INTO `tb_merchant_login_record` VALUES (8, 525048316940293, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-21 20:42:56');
INSERT INTO `tb_merchant_login_record` VALUES (9, 525112796979205, '223.104.64.89', '广东省湛江市', 1, '2024-03-27 20:36:56');
INSERT INTO `tb_merchant_login_record` VALUES (10, 525112796979205, '223.104.64.89', '广东省湛江市', 1, '2024-03-27 20:52:03');
INSERT INTO `tb_merchant_login_record` VALUES (11, 525112796979205, '223.104.64.89', '广东省湛江市', 1, '2024-03-27 20:52:03');
INSERT INTO `tb_merchant_login_record` VALUES (12, 525112796979205, '223.104.64.89', '广东省湛江市', 1, '2024-03-27 22:23:05');
INSERT INTO `tb_merchant_login_record` VALUES (13, 525112796979205, '223.104.64.89', '广东省湛江市', 1, '2024-03-27 22:23:05');
INSERT INTO `tb_merchant_login_record` VALUES (14, 525048316940293, '223.104.64.89', '广东省湛江市', 1, '2024-03-27 22:27:38');
INSERT INTO `tb_merchant_login_record` VALUES (15, 525048316940293, '223.104.66.225', '广东省东莞市', 1, '2024-04-04 19:51:32');
INSERT INTO `tb_merchant_login_record` VALUES (16, 525048316940293, '223.104.66.225', '广东省东莞市', 1, '2024-04-04 19:51:32');
INSERT INTO `tb_merchant_login_record` VALUES (17, 525048316940293, '223.104.66.225', '广东省东莞市', 1, '2024-04-05 11:43:39');
INSERT INTO `tb_merchant_login_record` VALUES (18, 525048316940293, '223.104.66.225', '广东省东莞市', 1, '2024-04-05 11:43:39');
INSERT INTO `tb_merchant_login_record` VALUES (19, 525048316940293, '223.104.66.225', '广东省东莞市', 1, '2024-04-05 11:43:39');
INSERT INTO `tb_merchant_login_record` VALUES (20, 525048316940293, '223.104.66.225', '广东省东莞市', 1, '2024-04-05 11:43:39');
INSERT INTO `tb_merchant_login_record` VALUES (21, 525048316940293, '223.104.66.225', '广东省东莞市', 1, '2024-04-05 16:33:03');
INSERT INTO `tb_merchant_login_record` VALUES (22, 525048316940293, '223.104.66.225', '广东省东莞市', 1, '2024-04-05 16:33:04');
INSERT INTO `tb_merchant_login_record` VALUES (23, 525048316940293, '223.104.65.56', '广东省湛江市', 1, '2024-04-08 22:57:27');
INSERT INTO `tb_merchant_login_record` VALUES (24, 525048316940293, '223.104.65.56', '广东省湛江市', 1, '2024-04-08 22:58:46');
INSERT INTO `tb_merchant_login_record` VALUES (25, 525048316940293, '223.104.65.56', '广东省湛江市', 1, '2024-04-08 22:58:46');
INSERT INTO `tb_merchant_login_record` VALUES (26, 525048316940293, '223.104.65.56', '广东省湛江市', 1, '2024-04-08 22:59:31');
INSERT INTO `tb_merchant_login_record` VALUES (27, 525048316940293, '223.104.65.56', '广东省湛江市', 1, '2024-04-08 23:01:00');
INSERT INTO `tb_merchant_login_record` VALUES (28, 525048316940293, '223.104.65.56', '广东省湛江市', 1, '2024-04-08 23:01:00');
INSERT INTO `tb_merchant_login_record` VALUES (29, 525048316940293, '223.104.65.56', '广东省湛江市', 1, '2024-04-08 23:04:38');
INSERT INTO `tb_merchant_login_record` VALUES (30, 525048316940293, '223.104.65.56', '广东省湛江市', 1, '2024-04-08 23:04:38');
INSERT INTO `tb_merchant_login_record` VALUES (31, 525048316940293, '223.104.65.56', '广东省湛江市', 1, '2024-04-08 23:06:05');
INSERT INTO `tb_merchant_login_record` VALUES (32, 525048316940293, '223.104.65.56', '广东省湛江市', 1, '2024-04-08 23:06:06');
INSERT INTO `tb_merchant_login_record` VALUES (33, 525048316940293, '142.171.204.195', '安大略0', 1, '2024-04-09 20:30:09');
INSERT INTO `tb_merchant_login_record` VALUES (34, 525048316940293, '223.104.66.222', '广东省东莞市', 1, '2024-04-10 17:48:50');
INSERT INTO `tb_merchant_login_record` VALUES (35, 525048316940293, '223.104.66.222', '广东省东莞市', 1, '2024-04-10 18:06:59');
INSERT INTO `tb_merchant_login_record` VALUES (36, 525048316940293, '223.104.66.222', '广东省东莞市', 1, '2024-04-10 18:08:22');
INSERT INTO `tb_merchant_login_record` VALUES (37, 525048316940293, '223.104.66.222', '广东省东莞市', 1, '2024-04-10 18:10:14');
INSERT INTO `tb_merchant_login_record` VALUES (38, 525048316940293, '223.104.66.222', '广东省东莞市', 1, '2024-04-10 18:15:11');
INSERT INTO `tb_merchant_login_record` VALUES (39, 525048316940293, '223.104.66.222', '广东省东莞市', 1, '2024-04-11 13:16:36');
INSERT INTO `tb_merchant_login_record` VALUES (40, 525048316940293, '223.104.66.222', '广东省东莞市', 1, '2024-04-11 13:16:36');
INSERT INTO `tb_merchant_login_record` VALUES (41, 525048316940293, '223.104.66.222', '广东省东莞市', 1, '2024-04-11 13:16:37');
INSERT INTO `tb_merchant_login_record` VALUES (42, 525048316940293, '142.171.204.195', '安大略0', 1, '2024-04-11 13:32:06');
INSERT INTO `tb_merchant_login_record` VALUES (43, 525048316940293, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 14:27:22');
INSERT INTO `tb_merchant_login_record` VALUES (44, 525048316940293, '223.104.66.222', '广东省东莞市', 1, '2024-04-11 15:03:27');
INSERT INTO `tb_merchant_login_record` VALUES (45, 525048316940293, '223.104.66.222', '广东省东莞市', 1, '2024-04-11 15:03:27');
INSERT INTO `tb_merchant_login_record` VALUES (46, 525048316940293, '223.104.66.222', '广东省东莞市', 1, '2024-04-11 15:03:27');
INSERT INTO `tb_merchant_login_record` VALUES (47, 525048316940293, '223.104.66.222', '广东省东莞市', 1, '2024-04-11 15:17:16');
INSERT INTO `tb_merchant_login_record` VALUES (48, 525048316940293, '223.104.66.222', '广东省东莞市', 1, '2024-04-11 15:17:16');
INSERT INTO `tb_merchant_login_record` VALUES (49, 525048316940293, '223.104.66.222', '广东省东莞市', 1, '2024-04-11 15:23:26');
INSERT INTO `tb_merchant_login_record` VALUES (50, 525048316940293, '223.104.66.222', '广东省东莞市', 1, '2024-04-11 15:23:26');
INSERT INTO `tb_merchant_login_record` VALUES (51, 525048316940293, '223.104.66.222', '广东省东莞市', 1, '2024-04-11 15:24:34');
INSERT INTO `tb_merchant_login_record` VALUES (52, 525048316940293, '223.104.66.222', '广东省东莞市', 1, '2024-04-11 15:27:46');
INSERT INTO `tb_merchant_login_record` VALUES (53, 525048316940293, '223.104.66.222', '广东省东莞市', 1, '2024-04-11 15:31:04');
INSERT INTO `tb_merchant_login_record` VALUES (54, 525048316940293, '223.104.66.222', '广东省东莞市', 1, '2024-04-11 15:31:04');
INSERT INTO `tb_merchant_login_record` VALUES (55, 525048316940293, '223.104.66.222', '广东省东莞市', 1, '2024-04-11 15:33:32');
INSERT INTO `tb_merchant_login_record` VALUES (56, 525048316940293, '223.104.66.222', '广东省东莞市', 1, '2024-04-11 15:44:46');
INSERT INTO `tb_merchant_login_record` VALUES (57, 525048316940293, '223.104.66.222', '广东省东莞市', 1, '2024-04-11 15:44:46');
INSERT INTO `tb_merchant_login_record` VALUES (58, 525048316940293, '223.104.66.222', '广东省东莞市', 1, '2024-04-11 15:54:49');
INSERT INTO `tb_merchant_login_record` VALUES (59, 525048316940293, '45.9.2.15', '伦敦伦敦', 1, '2024-04-11 15:54:49');
INSERT INTO `tb_merchant_login_record` VALUES (60, 525048316940293, '223.104.66.222', '广东省东莞市', 1, '2024-04-11 15:55:49');
INSERT INTO `tb_merchant_login_record` VALUES (61, 525048316940293, '223.104.66.222', '广东省东莞市', 1, '2024-04-11 16:14:09');
INSERT INTO `tb_merchant_login_record` VALUES (62, 525048316940293, '223.104.66.222', '广东省东莞市', 1, '2024-04-11 16:16:48');
INSERT INTO `tb_merchant_login_record` VALUES (63, 525048316940293, '223.104.66.222', '广东省东莞市', 1, '2024-04-11 16:20:46');
INSERT INTO `tb_merchant_login_record` VALUES (64, 525048316940293, '223.104.66.222', '广东省东莞市', 1, '2024-04-11 16:28:04');
INSERT INTO `tb_merchant_login_record` VALUES (65, 525048316940293, '223.104.66.222', '广东省东莞市', 1, '2024-04-11 16:28:04');
INSERT INTO `tb_merchant_login_record` VALUES (66, 525048316940293, '223.104.66.222', '广东省东莞市', 0, '2024-04-11 16:30:12');
INSERT INTO `tb_merchant_login_record` VALUES (67, 525048316940293, '223.104.66.222', '广东省东莞市', 0, '2024-04-11 16:30:16');
INSERT INTO `tb_merchant_login_record` VALUES (68, 525048316940293, '223.104.66.222', '广东省东莞市', 1, '2024-04-11 16:37:48');
INSERT INTO `tb_merchant_login_record` VALUES (69, 525048316940293, '223.104.66.222', '广东省东莞市', 0, '2024-04-11 16:41:35');
INSERT INTO `tb_merchant_login_record` VALUES (70, 525048316940293, '223.104.66.222', '广东省东莞市', 0, '2024-04-11 16:41:37');
INSERT INTO `tb_merchant_login_record` VALUES (71, 525048316940293, '223.104.66.222', '广东省东莞市', 1, '2024-04-11 16:41:48');
INSERT INTO `tb_merchant_login_record` VALUES (72, 525048316940293, '223.104.66.222', '广东省东莞市', 1, '2024-04-11 16:48:31');
INSERT INTO `tb_merchant_login_record` VALUES (73, 525048316940293, '142.171.204.195', '安大略0', 1, '2024-04-11 18:44:46');
INSERT INTO `tb_merchant_login_record` VALUES (74, 525048316940293, '120.235.248.237', '广东省0', 1, '2024-04-11 19:04:54');
INSERT INTO `tb_merchant_login_record` VALUES (75, 525048316940293, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 19:13:39');
INSERT INTO `tb_merchant_login_record` VALUES (76, 525048316940293, '142.171.204.195', '安大略0', 1, '2024-04-11 19:19:35');
INSERT INTO `tb_merchant_login_record` VALUES (77, 525048316940293, '223.104.66.222', '广东省东莞市', 1, '2024-04-11 19:24:03');
INSERT INTO `tb_merchant_login_record` VALUES (78, 525048316940293, '223.104.66.222', '广东省东莞市', 1, '2024-04-11 19:24:27');
INSERT INTO `tb_merchant_login_record` VALUES (79, 525048316940293, '223.104.66.222', '广东省东莞市', 1, '2024-04-11 19:25:32');

-- ----------------------------
-- Table structure for tb_merchant_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_merchant_user`;
CREATE TABLE `tb_merchant_user`  (
  `id` bigint NOT NULL,
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `ban_expire_status` tinyint(1) NOT NULL DEFAULT 0,
  `create_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_merchant_user
-- ----------------------------
INSERT INTO `tb_merchant_user` VALUES (525048316940293, '13912341234', '45c7aec583fdda457c41584d073fd255f962cf09541233852d8b235ab34fe921', 0, '2024-03-13 17:27:06', '2024-03-13 17:27:06');
INSERT INTO `tb_merchant_user` VALUES (525112796979205, '13900000000', '45c7aec583fdda457c41584d073fd255f962cf09541233852d8b235ab34fe921', 0, '2024-03-13 21:49:28', '2024-03-13 21:49:28');
INSERT INTO `tb_merchant_user` VALUES (535000181653509, '13511112222', '45c7aec583fdda457c41584d073fd255f962cf09541233852d8b235ab34fe921', 0, '2024-04-10 20:21:21', '2024-04-10 20:21:21');

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order`  (
  `order_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `user_id` bigint NOT NULL,
  `ad_id` int NOT NULL,
  `amount` double NOT NULL,
  `pay_status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '默认false为未支付',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `complete_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_order
-- ----------------------------
INSERT INTO `tb_order` VALUES ('5bbf6c2e0da3478aa6a6991714c1becb', 525048316940293, 14, 50, 1, '2024-04-09 20:38:38', NULL);
INSERT INTO `tb_order` VALUES ('7abeaddc90834586937349f12aedee97', 525048316940293, 13, 50, 1, '2024-04-09 20:38:27', NULL);
INSERT INTO `tb_order` VALUES ('917886508a964e468c7b27ffaf46300f', 525048316940293, 19, 1100, 1, '2024-04-10 19:12:32', NULL);
INSERT INTO `tb_order` VALUES ('955d7405ff8d48e0ba57ce226340cfb6', 525048316940293, 11, 500, 0, '2024-04-11 19:02:44', NULL);
INSERT INTO `tb_order` VALUES ('d3941155cdae49fe92eafcf77b6c1f10', 525048316940293, 12, 150, 1, '2024-04-05 15:24:38', NULL);

-- ----------------------------
-- Table structure for tb_school
-- ----------------------------
DROP TABLE IF EXISTS `tb_school`;
CREATE TABLE `tb_school`  (
  `school_code` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `postal_code` char(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `call_number` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `introduction` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `website_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`school_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_school
-- ----------------------------
INSERT INTO `tb_school` VALUES ('1001', 'A大学', 'A市', '552440', '556688', 'A大学是一所以理工科为主的大学', 'https://Auniversity.com');
INSERT INTO `tb_school` VALUES ('1002', 'B大学', 'B市', '54590', '13912341211', 'B大学是一所艺术类大学', 'https://Buniversity.com');
INSERT INTO `tb_school` VALUES ('1003', 'C大学', 'C市', '54530', '13912341211', 'C大学是一所体育类大学', 'https://Cuniversty.com');
INSERT INTO `tb_school` VALUES ('10566', '广东海洋大学', '湛江市', 'xxx', 'xxxx', 'xxx', 'xxx');

-- ----------------------------
-- Table structure for tb_stu2persona
-- ----------------------------
DROP TABLE IF EXISTS `tb_stu2persona`;
CREATE TABLE `tb_stu2persona`  (
  `student_id` bigint NOT NULL,
  `persona_id` int NOT NULL,
  PRIMARY KEY (`student_id`, `persona_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_stu2persona
-- ----------------------------
INSERT INTO `tb_stu2persona` VALUES (529272163794949, 3);
INSERT INTO `tb_stu2persona` VALUES (529272163794949, 5);
INSERT INTO `tb_stu2persona` VALUES (535034288562181, 3);
INSERT INTO `tb_stu2persona` VALUES (535034288562181, 5);

-- ----------------------------
-- Table structure for tb_stu_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_stu_info`;
CREATE TABLE `tb_stu_info`  (
  `id` bigint NOT NULL,
  `id_card` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `gender` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `notice_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '录取通知书编号',
  `exam_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '高考考生号',
  `school` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `college` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `major` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `user_class` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `signature` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `postal_code` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `home_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `avatar_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `real_photo_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id_card_unique`(`id_card`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_stu_info
-- ----------------------------
INSERT INTO `tb_stu_info` VALUES (529272163794949, '73be243ca1adacc853ab7f138be9095019c6b915f758898dcd8d6ce49d7a6b7b', '张明', '男', 'GDOU20223863', '011103107', '10566', '1001', '2', '计科1242', 'test', '510544', '广东省广州市', 'https://turingminio.raqtpie.xyz:49000/bud-student/529272163794949.jpeg', 'https://turingminio.raqtpie.xyz:49000/bud-student/face_529272163794949.jpeg', '2024-03-25 15:54:01', '2024-04-11 17:35:43');
INSERT INTO `tb_stu_info` VALUES (534199168622597, 'cc5901c46af4d981ab71574f93521ab784460669df0887a67a90c131e43cfd05', '李四', '男', 'GDOU20221111', '1234567890', '10566', '1001', '2', '计科1242', NULL, '510000', '广东省广州市', 'https://turingminio.raqtpie.xyz:49000/bud-student/avatar_man.png', 'https://turingminio.raqtpie.xyz:49000/bud-student/face_534199168622597.jpeg', '2024-04-08 14:02:01', '2024-04-10 15:31:50');
INSERT INTO `tb_stu_info` VALUES (534641419563013, 'bd1999606c03e2627c45889c6129e86303c9676618d115f33975d4f3aeb5e07d', '张三', '女', '20220409001', '20220409001001', '10566', '1001', '2', '计算机科学与技术1班', NULL, '518000', '广东省深圳市南山区科技园路1号', 'https://turingminio.raqtpie.xyz:49000/bud-student/avatar_woman.png', NULL, '2024-04-09 20:01:32', '2024-04-10 15:20:20');
INSERT INTO `tb_stu_info` VALUES (534649537556485, 'b0fe77658d1f7b0605418b993d779786f17acd6bb79ea1e905dc3196a9b8fed1', '李媛', '女', '10011002', '202411321129', '10566', '1001', '2', '2', NULL, '510012', '湛江', 'https://turingminio.raqtpie.xyz:49000/bud-student/avatar_woman.png', NULL, '2024-04-09 20:34:34', '2024-04-10 15:31:50');
INSERT INTO `tb_stu_info` VALUES (534655658229765, '9fbbc112716835698b8477004960748fa6e99d3ff6e15e1c48c359607eb15351', '小明', '男', 'GDOU20264112', '011103181', '10566', '1001', '2', '软件1243', NULL, '510552', '广东省汕头市', 'https://turingminio.raqtpie.xyz:49000/bud-student/avatar_man.png', NULL, '2024-04-09 20:59:28', '2024-04-10 15:31:50');
INSERT INTO `tb_stu_info` VALUES (534664236462085, '5ead7ffb0c0e1b28a5b555662bf6d7eb935516567c127d29f212802314ecb107', '赵六', '女', 'GDOU20264810', '011101802', '10566', '1001', '2', '土木1242', NULL, '510542', '广东省广州市', 'https://turingminio.raqtpie.xyz:49000/bud-student/avatar_woman.png', 'https://turingminio.raqtpie.xyz:49000/bud-student/face_534664236462085.png', '2024-04-09 21:34:22', '2024-04-10 15:31:50');
INSERT INTO `tb_stu_info` VALUES (535034288562181, 'c83966210f0c3d9343c9bf101487d3c9221ff9549519f21ae987b881d28add84', '张小明', '男', 'GDOU20223863', 'xxx', '10566', '1005', '1002', '软件1242', '我的签名', '510000', '广东省', 'https://turingminio.raqtpie.xyz:49000/bud-student/535034288562181.jpeg', 'https://turingminio.raqtpie.xyz:49000/bud-student/czc.jpg', '2024-04-10 22:40:07', '2024-04-11 17:49:13');
INSERT INTO `tb_stu_info` VALUES (535332485214213, '707cb910d0ea60dc9d19ca286b934fbae7e3109acabfe04dd98514d6270331d4', '孟凯文', '男', 'GDOU12341234', 'xxxx', '10566', '1001', '1001', '计科1234', '我的签名', '510000', '广东省', 'https://turingminio.raqtpie.xyz:49000/bud-student/avatar_man.png', 'https://turingminio.raqtpie.xyz:49000/bud-student/czc.jpg', '2024-04-11 18:53:29', '2024-04-11 19:44:39');

-- ----------------------------
-- Table structure for tb_stu_login_record
-- ----------------------------
DROP TABLE IF EXISTS `tb_stu_login_record`;
CREATE TABLE `tb_stu_login_record`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `student_id` bigint NOT NULL,
  `ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `status` tinyint(1) NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 589 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_stu_login_record
-- ----------------------------
INSERT INTO `tb_stu_login_record` VALUES (1, 524999133093893, '0:0:0:0:0:0:0:1', '未知', 1, '2024-03-13 16:12:55');
INSERT INTO `tb_stu_login_record` VALUES (2, 524999133093893, '0:0:0:0:0:0:0:1', '未知', 1, '2024-03-13 16:14:41');
INSERT INTO `tb_stu_login_record` VALUES (3, 524999133093893, '0:0:0:0:0:0:0:1', '未知', 1, '2024-03-13 16:15:15');
INSERT INTO `tb_stu_login_record` VALUES (4, 524999133093893, '0:0:0:0:0:0:0:1', '未知', 1, '2024-03-13 16:15:21');
INSERT INTO `tb_stu_login_record` VALUES (5, 524999133093893, '0:0:0:0:0:0:0:1', '未知', 1, '2024-03-13 16:15:26');
INSERT INTO `tb_stu_login_record` VALUES (6, 525039925985285, '0:0:0:0:0:0:0:1', '未知', 1, '2024-03-13 16:55:19');
INSERT INTO `tb_stu_login_record` VALUES (7, 525039925985285, '0:0:0:0:0:0:0:1', '未知', 0, '2024-03-14 20:44:08');
INSERT INTO `tb_stu_login_record` VALUES (8, 525039925985285, '0:0:0:0:0:0:0:1', '未知', 1, '2024-03-14 20:44:13');
INSERT INTO `tb_stu_login_record` VALUES (9, 525039925985285, '120.235.248.129', '未知', 1, '2024-03-18 19:19:08');
INSERT INTO `tb_stu_login_record` VALUES (10, 525039925985285, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-20 13:17:10');
INSERT INTO `tb_stu_login_record` VALUES (11, 525039925985285, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-20 16:09:00');
INSERT INTO `tb_stu_login_record` VALUES (12, 525039925985285, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-20 17:28:58');
INSERT INTO `tb_stu_login_record` VALUES (13, 525039925985285, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-20 17:29:33');
INSERT INTO `tb_stu_login_record` VALUES (14, 525039925985285, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-21 19:06:19');
INSERT INTO `tb_stu_login_record` VALUES (15, 525039925985285, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-23 12:44:35');
INSERT INTO `tb_stu_login_record` VALUES (16, 525039925985285, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-23 14:05:34');
INSERT INTO `tb_stu_login_record` VALUES (17, 525039925985285, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-23 17:39:45');
INSERT INTO `tb_stu_login_record` VALUES (18, 525039925985285, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-23 21:39:45');
INSERT INTO `tb_stu_login_record` VALUES (19, 525039925985285, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-23 22:30:05');
INSERT INTO `tb_stu_login_record` VALUES (20, 529272163794949, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-25 15:56:02');
INSERT INTO `tb_stu_login_record` VALUES (21, 529272163794949, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-25 15:56:28');
INSERT INTO `tb_stu_login_record` VALUES (22, 529272163794949, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-26 13:37:04');
INSERT INTO `tb_stu_login_record` VALUES (23, 529272163794949, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-26 13:38:11');
INSERT INTO `tb_stu_login_record` VALUES (24, 529272163794949, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-26 13:39:49');
INSERT INTO `tb_stu_login_record` VALUES (25, 529272163794949, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-26 13:40:11');
INSERT INTO `tb_stu_login_record` VALUES (26, 529272163794949, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-26 13:40:17');
INSERT INTO `tb_stu_login_record` VALUES (27, 529272163794949, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-26 13:40:23');
INSERT INTO `tb_stu_login_record` VALUES (28, 529272163794949, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-26 13:45:05');
INSERT INTO `tb_stu_login_record` VALUES (29, 529272163794949, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-26 13:47:40');
INSERT INTO `tb_stu_login_record` VALUES (30, 529272163794949, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-26 13:49:45');
INSERT INTO `tb_stu_login_record` VALUES (31, 529272163794949, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-26 13:51:59');
INSERT INTO `tb_stu_login_record` VALUES (32, 529272163794949, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-26 14:02:20');
INSERT INTO `tb_stu_login_record` VALUES (33, 529272163794949, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-26 14:02:45');
INSERT INTO `tb_stu_login_record` VALUES (34, 529272163794949, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-26 16:11:13');
INSERT INTO `tb_stu_login_record` VALUES (35, 529272163794949, '0:0:0:0:0:0:0:1', '本地访问', 0, '2024-03-27 20:32:45');
INSERT INTO `tb_stu_login_record` VALUES (36, 529272163794949, '0:0:0:0:0:0:0:1', '本地访问', 0, '2024-03-27 20:33:50');
INSERT INTO `tb_stu_login_record` VALUES (37, 529272163794949, '0:0:0:0:0:0:0:1', '本地访问', 0, '2024-03-27 20:35:10');
INSERT INTO `tb_stu_login_record` VALUES (38, 529272163794949, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-27 20:36:22');
INSERT INTO `tb_stu_login_record` VALUES (39, 529272163794949, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-28 14:16:44');
INSERT INTO `tb_stu_login_record` VALUES (40, 529272163794949, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-03-29 18:36:40');
INSERT INTO `tb_stu_login_record` VALUES (41, 529272163794949, '120.235.248.187', '广东省0', 1, '2024-03-29 19:20:13');
INSERT INTO `tb_stu_login_record` VALUES (42, 529272163794949, '183.46.222.71', '广东省汕头市', 1, '2024-03-29 19:21:13');
INSERT INTO `tb_stu_login_record` VALUES (43, 529272163794949, '120.235.248.199', '广东省0', 1, '2024-03-31 20:27:49');
INSERT INTO `tb_stu_login_record` VALUES (44, 529272163794949, '120.235.248.199', '广东省0', 1, '2024-03-31 20:59:01');
INSERT INTO `tb_stu_login_record` VALUES (45, 529272163794949, '120.235.248.199', '广东省0', 1, '2024-03-31 23:35:29');
INSERT INTO `tb_stu_login_record` VALUES (46, 529272163794949, '120.235.248.238', '广东省0', 1, '2024-04-02 00:57:40');
INSERT INTO `tb_stu_login_record` VALUES (47, 529272163794949, '120.235.248.63', '广东省0', 1, '2024-04-03 16:01:27');
INSERT INTO `tb_stu_login_record` VALUES (48, 529272163794949, '120.235.248.63', '广东省0', 1, '2024-04-03 17:27:24');
INSERT INTO `tb_stu_login_record` VALUES (49, 529272163794949, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-04-03 18:57:30');
INSERT INTO `tb_stu_login_record` VALUES (50, 529272163794949, '120.235.248.63', '广东省0', 1, '2024-04-03 19:59:39');
INSERT INTO `tb_stu_login_record` VALUES (51, 529272163794949, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-04-03 20:12:27');
INSERT INTO `tb_stu_login_record` VALUES (52, 529272163794949, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-04-03 20:19:24');
INSERT INTO `tb_stu_login_record` VALUES (53, 529272163794949, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-04-03 20:23:16');
INSERT INTO `tb_stu_login_record` VALUES (54, 529272163794949, '120.235.248.63', '广东省0', 1, '2024-04-03 20:26:07');
INSERT INTO `tb_stu_login_record` VALUES (55, 529272163794949, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-04-03 20:27:16');
INSERT INTO `tb_stu_login_record` VALUES (56, 529272163794949, '172.233.84.252', '德克萨斯达拉斯', 1, '2024-04-03 21:22:07');
INSERT INTO `tb_stu_login_record` VALUES (57, 529272163794949, '172.233.84.252', '德克萨斯达拉斯', 1, '2024-04-03 21:23:29');
INSERT INTO `tb_stu_login_record` VALUES (58, 529272163794949, '172.233.84.252', '德克萨斯达拉斯', 1, '2024-04-03 21:26:43');
INSERT INTO `tb_stu_login_record` VALUES (59, 529272163794949, '172.233.84.252', '德克萨斯达拉斯', 1, '2024-04-03 21:26:49');
INSERT INTO `tb_stu_login_record` VALUES (60, 529272163794949, '172.233.84.252', '德克萨斯达拉斯', 1, '2024-04-03 21:27:46');
INSERT INTO `tb_stu_login_record` VALUES (61, 529272163794949, '120.235.248.190', '广东省0', 1, '2024-04-03 23:55:15');
INSERT INTO `tb_stu_login_record` VALUES (62, 529272163794949, '120.235.248.190', '广东省0', 1, '2024-04-04 10:15:25');
INSERT INTO `tb_stu_login_record` VALUES (63, 529272163794949, '120.235.248.105', '广东省0', 1, '2024-04-04 14:41:33');
INSERT INTO `tb_stu_login_record` VALUES (64, 529272163794949, '120.235.248.105', '广东省0', 1, '2024-04-04 21:14:01');
INSERT INTO `tb_stu_login_record` VALUES (65, 529272163794949, '172.233.84.252', '德克萨斯达拉斯', 1, '2024-04-05 00:20:25');
INSERT INTO `tb_stu_login_record` VALUES (66, 529272163794949, '120.235.248.105', '广东省0', 1, '2024-04-05 16:31:29');
INSERT INTO `tb_stu_login_record` VALUES (67, 529272163794949, '120.235.248.105', '广东省0', 1, '2024-04-05 21:43:45');
INSERT INTO `tb_stu_login_record` VALUES (68, 529272163794949, '120.235.248.105', '广东省0', 1, '2024-04-06 11:17:02');
INSERT INTO `tb_stu_login_record` VALUES (69, 529272163794949, '120.235.248.105', '广东省0', 1, '2024-04-06 11:36:23');
INSERT INTO `tb_stu_login_record` VALUES (70, 529272163794949, '120.235.248.135', '广东省0', 1, '2024-04-07 15:58:21');
INSERT INTO `tb_stu_login_record` VALUES (71, 529272163794949, '120.235.248.135', '广东省0', 1, '2024-04-07 16:08:26');
INSERT INTO `tb_stu_login_record` VALUES (72, 529272163794949, '120.235.248.135', '广东省0', 1, '2024-04-07 16:09:07');
INSERT INTO `tb_stu_login_record` VALUES (73, 529272163794949, '120.235.248.135', '广东省0', 1, '2024-04-07 16:12:22');
INSERT INTO `tb_stu_login_record` VALUES (74, 529272163794949, '183.46.203.6', '广东省汕头市', 1, '2024-04-07 16:39:01');
INSERT INTO `tb_stu_login_record` VALUES (75, 529272163794949, '183.46.203.6', '广东省汕头市', 1, '2024-04-07 16:56:05');
INSERT INTO `tb_stu_login_record` VALUES (76, 529272163794949, '183.46.203.6', '广东省汕头市', 1, '2024-04-07 17:01:37');
INSERT INTO `tb_stu_login_record` VALUES (77, 529272163794949, '183.46.203.6', '广东省汕头市', 1, '2024-04-07 17:02:52');
INSERT INTO `tb_stu_login_record` VALUES (78, 529272163794949, '183.46.203.6', '广东省汕头市', 1, '2024-04-07 17:04:46');
INSERT INTO `tb_stu_login_record` VALUES (79, 529272163794949, '183.46.203.6', '广东省汕头市', 1, '2024-04-07 17:12:01');
INSERT INTO `tb_stu_login_record` VALUES (80, 529272163794949, '183.46.203.6', '广东省汕头市', 1, '2024-04-07 17:30:25');
INSERT INTO `tb_stu_login_record` VALUES (81, 529272163794949, '183.46.203.6', '广东省汕头市', 1, '2024-04-07 17:31:59');
INSERT INTO `tb_stu_login_record` VALUES (82, 529272163794949, '183.46.203.6', '广东省汕头市', 1, '2024-04-07 17:44:00');
INSERT INTO `tb_stu_login_record` VALUES (83, 529272163794949, '183.46.203.6', '广东省汕头市', 1, '2024-04-07 17:49:20');
INSERT INTO `tb_stu_login_record` VALUES (84, 529272163794949, '183.46.203.6', '广东省汕头市', 1, '2024-04-07 17:51:38');
INSERT INTO `tb_stu_login_record` VALUES (85, 529272163794949, '183.46.203.6', '广东省汕头市', 1, '2024-04-07 17:56:06');
INSERT INTO `tb_stu_login_record` VALUES (86, 529272163794949, '183.46.203.6', '广东省汕头市', 0, '2024-04-07 18:00:17');
INSERT INTO `tb_stu_login_record` VALUES (87, 529272163794949, '183.46.203.6', '广东省汕头市', 1, '2024-04-07 18:00:33');
INSERT INTO `tb_stu_login_record` VALUES (88, 529272163794949, '183.46.203.6', '广东省汕头市', 1, '2024-04-07 18:04:31');
INSERT INTO `tb_stu_login_record` VALUES (89, 529272163794949, '183.46.203.6', '广东省汕头市', 1, '2024-04-07 19:15:34');
INSERT INTO `tb_stu_login_record` VALUES (90, 529272163794949, '183.46.203.6', '广东省汕头市', 1, '2024-04-07 19:18:17');
INSERT INTO `tb_stu_login_record` VALUES (91, 529272163794949, '183.46.203.6', '广东省汕头市', 1, '2024-04-07 19:32:34');
INSERT INTO `tb_stu_login_record` VALUES (92, 529272163794949, '183.46.203.6', '广东省汕头市', 1, '2024-04-07 19:35:19');
INSERT INTO `tb_stu_login_record` VALUES (93, 529272163794949, '183.46.203.6', '广东省汕头市', 1, '2024-04-07 19:44:56');
INSERT INTO `tb_stu_login_record` VALUES (94, 529272163794949, '183.46.203.6', '广东省汕头市', 1, '2024-04-07 19:51:49');
INSERT INTO `tb_stu_login_record` VALUES (95, 529272163794949, '183.46.203.6', '广东省汕头市', 1, '2024-04-07 19:56:30');
INSERT INTO `tb_stu_login_record` VALUES (96, 529272163794949, '183.46.203.6', '广东省汕头市', 1, '2024-04-07 20:00:24');
INSERT INTO `tb_stu_login_record` VALUES (97, 529272163794949, '120.235.248.135', '广东省0', 1, '2024-04-07 20:24:38');
INSERT INTO `tb_stu_login_record` VALUES (98, 529272163794949, '120.235.248.135', '广东省0', 1, '2024-04-07 20:45:02');
INSERT INTO `tb_stu_login_record` VALUES (99, 529272163794949, '120.235.248.135', '广东省0', 1, '2024-04-07 21:46:20');
INSERT INTO `tb_stu_login_record` VALUES (100, 529272163794949, '120.235.248.135', '广东省0', 1, '2024-04-07 21:47:41');
INSERT INTO `tb_stu_login_record` VALUES (101, 529272163794949, '120.235.248.135', '广东省0', 1, '2024-04-07 21:51:25');
INSERT INTO `tb_stu_login_record` VALUES (102, 529272163794949, '120.235.248.135', '广东省0', 1, '2024-04-07 21:54:12');
INSERT INTO `tb_stu_login_record` VALUES (103, 529272163794949, '120.235.248.135', '广东省0', 1, '2024-04-07 23:52:54');
INSERT INTO `tb_stu_login_record` VALUES (104, 529272163794949, '183.46.203.6', '广东省汕头市', 1, '2024-04-08 10:22:39');
INSERT INTO `tb_stu_login_record` VALUES (105, 529272163794949, '183.46.203.6', '广东省汕头市', 1, '2024-04-08 10:49:59');
INSERT INTO `tb_stu_login_record` VALUES (106, 529272163794949, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-04-08 10:58:38');
INSERT INTO `tb_stu_login_record` VALUES (107, 529272163794949, '183.46.203.6', '广东省汕头市', 1, '2024-04-08 11:07:39');
INSERT INTO `tb_stu_login_record` VALUES (108, 529272163794949, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-04-08 11:10:27');
INSERT INTO `tb_stu_login_record` VALUES (109, 529272163794949, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-04-08 11:10:50');
INSERT INTO `tb_stu_login_record` VALUES (110, 529272163794949, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-04-08 11:19:48');
INSERT INTO `tb_stu_login_record` VALUES (111, 529272163794949, '183.46.203.6', '广东省汕头市', 1, '2024-04-08 11:46:59');
INSERT INTO `tb_stu_login_record` VALUES (112, 529272163794949, '183.46.203.6', '广东省汕头市', 1, '2024-04-08 14:29:35');
INSERT INTO `tb_stu_login_record` VALUES (113, 529272163794949, '183.46.203.6', '广东省汕头市', 1, '2024-04-08 14:31:28');
INSERT INTO `tb_stu_login_record` VALUES (114, 529272163794949, '183.46.203.6', '广东省汕头市', 1, '2024-04-08 14:34:21');
INSERT INTO `tb_stu_login_record` VALUES (115, 529272163794949, '183.46.203.6', '广东省汕头市', 1, '2024-04-08 14:35:39');
INSERT INTO `tb_stu_login_record` VALUES (116, 529272163794949, '183.46.203.6', '广东省汕头市', 1, '2024-04-08 14:37:43');
INSERT INTO `tb_stu_login_record` VALUES (117, 529272163794949, '183.46.203.6', '广东省汕头市', 1, '2024-04-08 14:46:51');
INSERT INTO `tb_stu_login_record` VALUES (118, 529272163794949, '183.46.203.6', '广东省汕头市', 1, '2024-04-08 14:50:26');
INSERT INTO `tb_stu_login_record` VALUES (119, 529272163794949, '183.46.203.6', '广东省汕头市', 1, '2024-04-08 14:59:10');
INSERT INTO `tb_stu_login_record` VALUES (120, 529272163794949, '183.46.203.6', '广东省汕头市', 1, '2024-04-08 15:04:46');
INSERT INTO `tb_stu_login_record` VALUES (121, 529272163794949, '183.46.203.6', '广东省汕头市', 1, '2024-04-08 16:00:13');
INSERT INTO `tb_stu_login_record` VALUES (122, 529272163794949, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-04-08 16:03:45');
INSERT INTO `tb_stu_login_record` VALUES (123, 529272163794949, '120.235.248.97', '广东省0', 1, '2024-04-08 16:50:44');
INSERT INTO `tb_stu_login_record` VALUES (124, 529272163794949, '120.235.248.97', '广东省0', 1, '2024-04-08 16:52:34');
INSERT INTO `tb_stu_login_record` VALUES (125, 529272163794949, '120.235.248.97', '广东省0', 1, '2024-04-08 16:55:12');
INSERT INTO `tb_stu_login_record` VALUES (126, 529272163794949, '120.235.248.97', '广东省0', 1, '2024-04-08 16:58:11');
INSERT INTO `tb_stu_login_record` VALUES (127, 529272163794949, '120.235.248.97', '广东省0', 1, '2024-04-08 17:27:08');
INSERT INTO `tb_stu_login_record` VALUES (128, 529272163794949, '120.235.248.97', '广东省0', 1, '2024-04-08 17:40:07');
INSERT INTO `tb_stu_login_record` VALUES (129, 529272163794949, '120.235.248.97', '广东省0', 1, '2024-04-08 19:35:18');
INSERT INTO `tb_stu_login_record` VALUES (130, 529272163794949, '120.235.248.97', '广东省0', 1, '2024-04-08 19:40:34');
INSERT INTO `tb_stu_login_record` VALUES (131, 529272163794949, '120.235.248.97', '广东省0', 1, '2024-04-08 20:15:01');
INSERT INTO `tb_stu_login_record` VALUES (132, 529272163794949, '120.235.248.97', '广东省0', 1, '2024-04-08 20:32:42');
INSERT INTO `tb_stu_login_record` VALUES (133, 529272163794949, '120.235.248.97', '广东省0', 1, '2024-04-08 20:40:13');
INSERT INTO `tb_stu_login_record` VALUES (134, 529272163794949, '120.235.248.240', '广东省0', 1, '2024-04-09 00:36:19');
INSERT INTO `tb_stu_login_record` VALUES (135, 529272163794949, '120.235.248.240', '广东省0', 1, '2024-04-09 00:41:44');
INSERT INTO `tb_stu_login_record` VALUES (136, 529272163794949, '120.235.248.240', '广东省0', 1, '2024-04-09 00:50:39');
INSERT INTO `tb_stu_login_record` VALUES (137, 529272163794949, '120.235.248.240', '广东省0', 1, '2024-04-09 00:54:21');
INSERT INTO `tb_stu_login_record` VALUES (138, 529272163794949, '120.235.248.240', '广东省0', 1, '2024-04-09 00:58:57');
INSERT INTO `tb_stu_login_record` VALUES (139, 529272163794949, '120.235.248.240', '广东省0', 1, '2024-04-09 01:03:36');
INSERT INTO `tb_stu_login_record` VALUES (140, 529272163794949, '120.235.248.240', '广东省0', 1, '2024-04-09 01:09:43');
INSERT INTO `tb_stu_login_record` VALUES (141, 529272163794949, '120.235.248.240', '广东省0', 1, '2024-04-09 01:19:15');
INSERT INTO `tb_stu_login_record` VALUES (142, 529272163794949, '120.235.248.240', '广东省0', 1, '2024-04-09 02:15:39');
INSERT INTO `tb_stu_login_record` VALUES (143, 529272163794949, '120.235.248.240', '广东省0', 1, '2024-04-09 02:19:04');
INSERT INTO `tb_stu_login_record` VALUES (144, 529272163794949, '120.235.248.240', '广东省0', 1, '2024-04-09 03:01:33');
INSERT INTO `tb_stu_login_record` VALUES (145, 529272163794949, '120.235.248.240', '广东省0', 1, '2024-04-09 03:17:18');
INSERT INTO `tb_stu_login_record` VALUES (146, 529272163794949, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-04-09 08:24:54');
INSERT INTO `tb_stu_login_record` VALUES (147, 529272163794949, '120.235.248.200', '广东省0', 1, '2024-04-09 10:49:36');
INSERT INTO `tb_stu_login_record` VALUES (148, 529272163794949, '120.235.248.200', '广东省0', 1, '2024-04-09 11:12:02');
INSERT INTO `tb_stu_login_record` VALUES (149, 529272163794949, '120.235.248.200', '广东省0', 1, '2024-04-09 11:32:05');
INSERT INTO `tb_stu_login_record` VALUES (150, 529272163794949, '120.235.248.200', '广东省0', 1, '2024-04-09 12:46:56');
INSERT INTO `tb_stu_login_record` VALUES (151, 529272163794949, '120.235.248.200', '广东省0', 1, '2024-04-09 12:58:00');
INSERT INTO `tb_stu_login_record` VALUES (152, 529272163794949, '0:0:0:0:0:0:0:1', '本地访问', 1, '2024-04-09 12:58:14');
INSERT INTO `tb_stu_login_record` VALUES (153, 529272163794949, '127.0.0.1', '本地访问', 1, '2024-04-09 13:14:26');
INSERT INTO `tb_stu_login_record` VALUES (154, 529272163794949, '127.0.0.1', '本地访问', 1, '2024-04-09 13:14:30');
INSERT INTO `tb_stu_login_record` VALUES (155, 529272163794949, '127.0.0.1', '本地访问', 1, '2024-04-09 13:16:39');
INSERT INTO `tb_stu_login_record` VALUES (156, 529272163794949, '120.235.248.200', '广东省0', 1, '2024-04-09 13:41:12');
INSERT INTO `tb_stu_login_record` VALUES (157, 529272163794949, '142.171.204.195', '安大略0', 1, '2024-04-09 14:05:41');
INSERT INTO `tb_stu_login_record` VALUES (158, 529272163794949, '120.235.248.200', '广东省0', 1, '2024-04-09 19:41:27');
INSERT INTO `tb_stu_login_record` VALUES (159, 529272163794949, '120.235.248.200', '广东省0', 1, '2024-04-09 19:42:30');
INSERT INTO `tb_stu_login_record` VALUES (160, 529272163794949, '120.235.248.200', '广东省0', 1, '2024-04-09 19:58:50');
INSERT INTO `tb_stu_login_record` VALUES (161, 529272163794949, '120.235.248.200', '广东省0', 1, '2024-04-09 19:59:41');
INSERT INTO `tb_stu_login_record` VALUES (162, 529272163794949, '120.235.248.200', '广东省0', 1, '2024-04-09 20:17:11');
INSERT INTO `tb_stu_login_record` VALUES (163, 529272163794949, '120.235.248.200', '广东省0', 1, '2024-04-09 20:20:24');
INSERT INTO `tb_stu_login_record` VALUES (164, 529272163794949, '120.235.248.200', '广东省0', 1, '2024-04-09 20:24:43');
INSERT INTO `tb_stu_login_record` VALUES (165, 529272163794949, '120.235.248.200', '广东省0', 1, '2024-04-09 20:26:41');
INSERT INTO `tb_stu_login_record` VALUES (166, 529272163794949, '120.235.248.200', '广东省0', 1, '2024-04-09 20:51:39');
INSERT INTO `tb_stu_login_record` VALUES (167, 529272163794949, '120.235.248.200', '广东省0', 1, '2024-04-09 20:52:05');
INSERT INTO `tb_stu_login_record` VALUES (168, 529272163794949, '142.171.204.195', '安大略0', 1, '2024-04-09 20:58:02');
INSERT INTO `tb_stu_login_record` VALUES (169, 529272163794949, '120.235.248.200', '广东省0', 1, '2024-04-09 21:13:38');
INSERT INTO `tb_stu_login_record` VALUES (170, 529272163794949, '120.235.248.200', '广东省0', 1, '2024-04-09 21:13:53');
INSERT INTO `tb_stu_login_record` VALUES (171, 529272163794949, '120.235.248.200', '广东省0', 1, '2024-04-09 21:35:04');
INSERT INTO `tb_stu_login_record` VALUES (172, 529272163794949, '120.235.248.200', '广东省0', 1, '2024-04-09 22:00:45');
INSERT INTO `tb_stu_login_record` VALUES (173, 529272163794949, '120.235.248.200', '广东省0', 1, '2024-04-09 22:58:06');
INSERT INTO `tb_stu_login_record` VALUES (174, 529272163794949, '120.235.248.200', '广东省0', 1, '2024-04-09 23:01:13');
INSERT INTO `tb_stu_login_record` VALUES (175, 529272163794949, '120.235.248.200', '广东省0', 1, '2024-04-09 23:01:45');
INSERT INTO `tb_stu_login_record` VALUES (176, 529272163794949, '120.235.248.200', '广东省0', 1, '2024-04-09 23:02:02');
INSERT INTO `tb_stu_login_record` VALUES (177, 529272163794949, '120.235.248.200', '广东省0', 1, '2024-04-09 23:04:36');
INSERT INTO `tb_stu_login_record` VALUES (178, 529272163794949, '120.235.248.200', '广东省0', 1, '2024-04-09 23:04:49');
INSERT INTO `tb_stu_login_record` VALUES (179, 529272163794949, '120.235.248.200', '广东省0', 1, '2024-04-09 23:05:18');
INSERT INTO `tb_stu_login_record` VALUES (180, 529272163794949, '120.235.248.200', '广东省0', 1, '2024-04-09 23:14:29');
INSERT INTO `tb_stu_login_record` VALUES (181, 529272163794949, '120.235.248.200', '广东省0', 1, '2024-04-09 23:21:58');
INSERT INTO `tb_stu_login_record` VALUES (182, 529272163794949, '120.235.248.200', '广东省0', 1, '2024-04-09 23:26:32');
INSERT INTO `tb_stu_login_record` VALUES (183, 529272163794949, '120.235.248.200', '广东省0', 1, '2024-04-09 23:35:50');
INSERT INTO `tb_stu_login_record` VALUES (184, 529272163794949, '120.235.248.200', '广东省0', 1, '2024-04-09 23:38:21');
INSERT INTO `tb_stu_login_record` VALUES (185, 529272163794949, '120.235.248.200', '广东省0', 1, '2024-04-09 23:42:23');
INSERT INTO `tb_stu_login_record` VALUES (186, 529272163794949, '120.235.248.200', '广东省0', 1, '2024-04-09 23:42:37');
INSERT INTO `tb_stu_login_record` VALUES (187, 529272163794949, '120.235.248.200', '广东省0', 1, '2024-04-09 23:43:00');
INSERT INTO `tb_stu_login_record` VALUES (188, 529272163794949, '120.235.248.200', '广东省0', 1, '2024-04-09 23:43:22');
INSERT INTO `tb_stu_login_record` VALUES (189, 529272163794949, '120.235.248.200', '广东省0', 1, '2024-04-09 23:43:55');
INSERT INTO `tb_stu_login_record` VALUES (190, 529272163794949, '120.235.248.200', '广东省0', 1, '2024-04-09 23:47:49');
INSERT INTO `tb_stu_login_record` VALUES (191, 529272163794949, '120.235.248.200', '广东省0', 1, '2024-04-10 00:07:41');
INSERT INTO `tb_stu_login_record` VALUES (192, 529272163794949, '120.235.248.200', '广东省0', 1, '2024-04-10 00:12:02');
INSERT INTO `tb_stu_login_record` VALUES (193, 529272163794949, '120.235.248.200', '广东省0', 1, '2024-04-10 00:14:04');
INSERT INTO `tb_stu_login_record` VALUES (194, 529272163794949, '120.235.248.200', '广东省0', 1, '2024-04-10 00:22:37');
INSERT INTO `tb_stu_login_record` VALUES (195, 529272163794949, '120.235.248.200', '广东省0', 1, '2024-04-10 00:31:25');
INSERT INTO `tb_stu_login_record` VALUES (196, 529272163794949, '120.235.248.200', '广东省0', 1, '2024-04-10 00:35:10');
INSERT INTO `tb_stu_login_record` VALUES (197, 529272163794949, '120.235.248.200', '广东省0', 1, '2024-04-10 00:40:06');
INSERT INTO `tb_stu_login_record` VALUES (198, 529272163794949, '120.235.248.200', '广东省0', 1, '2024-04-10 01:19:20');
INSERT INTO `tb_stu_login_record` VALUES (199, 529272163794949, '120.235.248.200', '广东省0', 1, '2024-04-10 01:33:12');
INSERT INTO `tb_stu_login_record` VALUES (200, 529272163794949, '120.235.248.200', '广东省0', 1, '2024-04-10 01:39:33');
INSERT INTO `tb_stu_login_record` VALUES (201, 529272163794949, '120.235.248.200', '广东省0', 1, '2024-04-10 01:43:56');
INSERT INTO `tb_stu_login_record` VALUES (202, 529272163794949, '120.235.248.200', '广东省0', 1, '2024-04-10 02:07:50');
INSERT INTO `tb_stu_login_record` VALUES (203, 529272163794949, '120.235.248.200', '广东省0', 1, '2024-04-10 02:08:35');
INSERT INTO `tb_stu_login_record` VALUES (204, 529272163794949, '120.235.248.200', '广东省0', 1, '2024-04-10 02:16:50');
INSERT INTO `tb_stu_login_record` VALUES (205, 529272163794949, '120.235.248.200', '广东省0', 1, '2024-04-10 02:19:12');
INSERT INTO `tb_stu_login_record` VALUES (206, 529272163794949, '120.235.248.200', '广东省0', 1, '2024-04-10 02:19:12');
INSERT INTO `tb_stu_login_record` VALUES (207, 529272163794949, '120.235.248.200', '广东省0', 1, '2024-04-10 02:25:21');
INSERT INTO `tb_stu_login_record` VALUES (208, 529272163794949, '120.235.248.200', '广东省0', 1, '2024-04-10 02:25:29');
INSERT INTO `tb_stu_login_record` VALUES (209, 529272163794949, '120.235.248.200', '广东省0', 1, '2024-04-10 02:26:50');
INSERT INTO `tb_stu_login_record` VALUES (210, 529272163794949, '120.235.248.200', '广东省0', 1, '2024-04-10 02:28:33');
INSERT INTO `tb_stu_login_record` VALUES (211, 529272163794949, '120.235.248.200', '广东省0', 1, '2024-04-10 02:29:07');
INSERT INTO `tb_stu_login_record` VALUES (212, 529272163794949, '120.235.248.200', '广东省0', 1, '2024-04-10 03:03:59');
INSERT INTO `tb_stu_login_record` VALUES (213, 529272163794949, '183.46.34.116', '广东省汕头市', 1, '2024-04-10 08:23:53');
INSERT INTO `tb_stu_login_record` VALUES (214, 529272163794949, '183.46.34.116', '广东省汕头市', 1, '2024-04-10 08:28:09');
INSERT INTO `tb_stu_login_record` VALUES (215, 529272163794949, '183.46.34.116', '广东省汕头市', 1, '2024-04-10 08:29:48');
INSERT INTO `tb_stu_login_record` VALUES (216, 529272163794949, '183.46.34.116', '广东省汕头市', 1, '2024-04-10 08:31:01');
INSERT INTO `tb_stu_login_record` VALUES (217, 529272163794949, '183.46.34.116', '广东省汕头市', 1, '2024-04-10 08:33:17');
INSERT INTO `tb_stu_login_record` VALUES (218, 529272163794949, '183.46.34.116', '广东省汕头市', 1, '2024-04-10 08:39:58');
INSERT INTO `tb_stu_login_record` VALUES (219, 529272163794949, '183.46.34.116', '广东省汕头市', 1, '2024-04-10 08:48:38');
INSERT INTO `tb_stu_login_record` VALUES (220, 529272163794949, '183.46.34.116', '广东省汕头市', 1, '2024-04-10 09:04:28');
INSERT INTO `tb_stu_login_record` VALUES (221, 529272163794949, '183.46.34.116', '广东省汕头市', 1, '2024-04-10 09:05:24');
INSERT INTO `tb_stu_login_record` VALUES (222, 529272163794949, '183.46.34.116', '广东省汕头市', 1, '2024-04-10 09:10:30');
INSERT INTO `tb_stu_login_record` VALUES (223, 529272163794949, '183.46.34.116', '广东省汕头市', 1, '2024-04-10 09:11:46');
INSERT INTO `tb_stu_login_record` VALUES (224, 529272163794949, '183.46.34.116', '广东省汕头市', 1, '2024-04-10 09:14:50');
INSERT INTO `tb_stu_login_record` VALUES (225, 529272163794949, '183.46.34.116', '广东省汕头市', 1, '2024-04-10 09:16:01');
INSERT INTO `tb_stu_login_record` VALUES (226, 529272163794949, '183.46.34.116', '广东省汕头市', 1, '2024-04-10 09:17:13');
INSERT INTO `tb_stu_login_record` VALUES (227, 529272163794949, '183.46.34.116', '广东省汕头市', 1, '2024-04-10 09:25:19');
INSERT INTO `tb_stu_login_record` VALUES (228, 529272163794949, '183.46.34.116', '广东省汕头市', 1, '2024-04-10 09:42:33');
INSERT INTO `tb_stu_login_record` VALUES (229, 529272163794949, '183.46.34.116', '广东省汕头市', 1, '2024-04-10 09:43:22');
INSERT INTO `tb_stu_login_record` VALUES (230, 529272163794949, '183.46.34.116', '广东省汕头市', 1, '2024-04-10 09:46:14');
INSERT INTO `tb_stu_login_record` VALUES (231, 529272163794949, '183.46.34.116', '广东省汕头市', 1, '2024-04-10 09:48:42');
INSERT INTO `tb_stu_login_record` VALUES (232, 529272163794949, '183.46.34.116', '广东省汕头市', 1, '2024-04-10 09:55:26');
INSERT INTO `tb_stu_login_record` VALUES (233, 529272163794949, '183.46.34.116', '广东省汕头市', 1, '2024-04-10 09:57:27');
INSERT INTO `tb_stu_login_record` VALUES (234, 529272163794949, '183.46.34.116', '广东省汕头市', 1, '2024-04-10 10:01:26');
INSERT INTO `tb_stu_login_record` VALUES (235, 529272163794949, '183.46.34.116', '广东省汕头市', 1, '2024-04-10 10:22:13');
INSERT INTO `tb_stu_login_record` VALUES (236, 529272163794949, '183.46.34.116', '广东省汕头市', 1, '2024-04-10 10:59:56');
INSERT INTO `tb_stu_login_record` VALUES (237, 529272163794949, '183.46.34.116', '广东省汕头市', 1, '2024-04-10 11:17:04');
INSERT INTO `tb_stu_login_record` VALUES (238, 529272163794949, '183.46.34.116', '广东省汕头市', 1, '2024-04-10 11:24:09');
INSERT INTO `tb_stu_login_record` VALUES (239, 529272163794949, '183.46.34.116', '广东省汕头市', 1, '2024-04-10 11:34:23');
INSERT INTO `tb_stu_login_record` VALUES (240, 529272163794949, '120.235.248.254', '广东省0', 1, '2024-04-10 12:12:42');
INSERT INTO `tb_stu_login_record` VALUES (241, 529272163794949, '183.46.34.116', '广东省汕头市', 1, '2024-04-10 12:38:47');
INSERT INTO `tb_stu_login_record` VALUES (242, 529272163794949, '183.46.34.116', '广东省汕头市', 1, '2024-04-10 12:51:25');
INSERT INTO `tb_stu_login_record` VALUES (243, 529272163794949, '183.46.34.116', '广东省汕头市', 1, '2024-04-10 12:52:57');
INSERT INTO `tb_stu_login_record` VALUES (244, 529272163794949, '183.46.34.116', '广东省汕头市', 1, '2024-04-10 12:55:18');
INSERT INTO `tb_stu_login_record` VALUES (245, 529272163794949, '183.46.34.116', '广东省汕头市', 1, '2024-04-10 12:57:41');
INSERT INTO `tb_stu_login_record` VALUES (246, 529272163794949, '183.46.34.116', '广东省汕头市', 1, '2024-04-10 13:01:10');
INSERT INTO `tb_stu_login_record` VALUES (247, 529272163794949, '183.46.34.116', '广东省汕头市', 1, '2024-04-10 13:04:39');
INSERT INTO `tb_stu_login_record` VALUES (248, 529272163794949, '183.46.34.116', '广东省汕头市', 1, '2024-04-10 13:05:42');
INSERT INTO `tb_stu_login_record` VALUES (249, 529272163794949, '183.46.34.116', '广东省汕头市', 1, '2024-04-10 13:14:13');
INSERT INTO `tb_stu_login_record` VALUES (250, 529272163794949, '183.46.34.116', '广东省汕头市', 1, '2024-04-10 13:38:09');
INSERT INTO `tb_stu_login_record` VALUES (251, 529272163794949, '183.46.34.116', '广东省汕头市', 1, '2024-04-10 13:44:20');
INSERT INTO `tb_stu_login_record` VALUES (252, 529272163794949, '183.46.34.116', '广东省汕头市', 1, '2024-04-10 13:48:39');
INSERT INTO `tb_stu_login_record` VALUES (253, 529272163794949, '183.46.34.116', '广东省汕头市', 1, '2024-04-10 14:02:18');
INSERT INTO `tb_stu_login_record` VALUES (254, 529272163794949, '183.46.34.116', '广东省汕头市', 1, '2024-04-10 14:16:52');
INSERT INTO `tb_stu_login_record` VALUES (255, 529272163794949, '183.46.34.116', '广东省汕头市', 0, '2024-04-10 14:45:16');
INSERT INTO `tb_stu_login_record` VALUES (256, 529272163794949, '183.46.34.116', '广东省汕头市', 0, '2024-04-10 14:46:26');
INSERT INTO `tb_stu_login_record` VALUES (257, 529272163794949, '183.46.34.116', '广东省汕头市', 1, '2024-04-10 14:46:40');
INSERT INTO `tb_stu_login_record` VALUES (258, 529272163794949, '183.46.34.116', '广东省汕头市', 1, '2024-04-10 14:56:25');
INSERT INTO `tb_stu_login_record` VALUES (259, 529272163794949, '183.46.34.116', '广东省汕头市', 1, '2024-04-10 15:01:52');
INSERT INTO `tb_stu_login_record` VALUES (260, 529272163794949, '183.46.34.116', '广东省汕头市', 1, '2024-04-10 15:05:52');
INSERT INTO `tb_stu_login_record` VALUES (261, 529272163794949, '192.168.137.64', '0内网IP', 1, '2024-04-10 15:26:17');
INSERT INTO `tb_stu_login_record` VALUES (262, 529272163794949, '192.168.137.64', '0内网IP', 1, '2024-04-10 15:26:25');
INSERT INTO `tb_stu_login_record` VALUES (263, 529272163794949, '120.235.248.254', '广东省0', 1, '2024-04-10 15:27:37');
INSERT INTO `tb_stu_login_record` VALUES (264, 529272163794949, '120.235.248.254', '广东省0', 1, '2024-04-10 15:28:27');
INSERT INTO `tb_stu_login_record` VALUES (265, 529272163794949, '120.235.248.254', '广东省0', 1, '2024-04-10 15:28:39');
INSERT INTO `tb_stu_login_record` VALUES (266, 529272163794949, '192.168.137.64', '0内网IP', 1, '2024-04-10 15:29:37');
INSERT INTO `tb_stu_login_record` VALUES (267, 529272163794949, '192.168.137.64', '0内网IP', 1, '2024-04-10 15:30:59');
INSERT INTO `tb_stu_login_record` VALUES (268, 529272163794949, '192.168.137.64', '0内网IP', 1, '2024-04-10 15:31:59');
INSERT INTO `tb_stu_login_record` VALUES (269, 529272163794949, '120.235.248.254', '广东省0', 1, '2024-04-10 15:33:41');
INSERT INTO `tb_stu_login_record` VALUES (270, 529272163794949, '120.235.248.254', '广东省0', 1, '2024-04-10 15:35:00');
INSERT INTO `tb_stu_login_record` VALUES (271, 529272163794949, '120.235.248.254', '广东省0', 1, '2024-04-10 15:35:09');
INSERT INTO `tb_stu_login_record` VALUES (272, 529272163794949, '120.235.248.254', '广东省0', 1, '2024-04-10 15:44:15');
INSERT INTO `tb_stu_login_record` VALUES (273, 529272163794949, '120.235.248.254', '广东省0', 1, '2024-04-10 15:44:22');
INSERT INTO `tb_stu_login_record` VALUES (274, 529272163794949, '120.235.248.254', '广东省0', 1, '2024-04-10 15:55:30');
INSERT INTO `tb_stu_login_record` VALUES (275, 529272163794949, '120.235.248.254', '广东省0', 1, '2024-04-10 15:59:27');
INSERT INTO `tb_stu_login_record` VALUES (276, 529272163794949, '120.235.248.254', '广东省0', 1, '2024-04-10 16:00:54');
INSERT INTO `tb_stu_login_record` VALUES (277, 529272163794949, '120.235.248.254', '广东省0', 0, '2024-04-10 16:05:13');
INSERT INTO `tb_stu_login_record` VALUES (278, 529272163794949, '120.235.248.254', '广东省0', 1, '2024-04-10 16:05:18');
INSERT INTO `tb_stu_login_record` VALUES (279, 529272163794949, '120.235.248.254', '广东省0', 1, '2024-04-10 16:06:34');
INSERT INTO `tb_stu_login_record` VALUES (280, 529272163794949, '120.235.248.254', '广东省0', 1, '2024-04-10 16:06:50');
INSERT INTO `tb_stu_login_record` VALUES (281, 529272163794949, '120.235.248.254', '广东省0', 1, '2024-04-10 16:28:39');
INSERT INTO `tb_stu_login_record` VALUES (282, 529272163794949, '120.235.248.254', '广东省0', 1, '2024-04-10 16:33:01');
INSERT INTO `tb_stu_login_record` VALUES (283, 529272163794949, '120.235.248.254', '广东省0', 1, '2024-04-10 16:38:46');
INSERT INTO `tb_stu_login_record` VALUES (284, 529272163794949, '120.235.248.254', '广东省0', 1, '2024-04-10 16:41:37');
INSERT INTO `tb_stu_login_record` VALUES (285, 529272163794949, '120.235.248.254', '广东省0', 1, '2024-04-10 20:17:36');
INSERT INTO `tb_stu_login_record` VALUES (286, 529272163794949, '142.171.204.195', '安大略0', 1, '2024-04-10 20:43:36');
INSERT INTO `tb_stu_login_record` VALUES (287, 529272163794949, '119.143.202.192', '广东省广州市', 1, '2024-04-10 21:22:14');
INSERT INTO `tb_stu_login_record` VALUES (288, 529272163794949, '120.235.248.254', '广东省0', 1, '2024-04-10 21:40:22');
INSERT INTO `tb_stu_login_record` VALUES (289, 529272163794949, '120.235.248.254', '广东省0', 1, '2024-04-10 21:41:54');
INSERT INTO `tb_stu_login_record` VALUES (290, 529272163794949, '120.235.248.254', '广东省0', 1, '2024-04-10 21:42:05');
INSERT INTO `tb_stu_login_record` VALUES (291, 529272163794949, '120.235.248.254', '广东省0', 1, '2024-04-10 21:57:17');
INSERT INTO `tb_stu_login_record` VALUES (292, 529272163794949, '120.235.248.254', '广东省0', 1, '2024-04-10 21:58:56');
INSERT INTO `tb_stu_login_record` VALUES (293, 529272163794949, '120.235.248.254', '广东省0', 1, '2024-04-10 22:00:44');
INSERT INTO `tb_stu_login_record` VALUES (294, 529272163794949, '120.235.248.254', '广东省0', 1, '2024-04-10 22:03:03');
INSERT INTO `tb_stu_login_record` VALUES (295, 529272163794949, '120.235.248.254', '广东省0', 1, '2024-04-10 22:14:11');
INSERT INTO `tb_stu_login_record` VALUES (296, 529272163794949, '120.235.248.254', '广东省0', 1, '2024-04-10 22:14:18');
INSERT INTO `tb_stu_login_record` VALUES (297, 529272163794949, '120.235.248.254', '广东省0', 1, '2024-04-10 22:17:47');
INSERT INTO `tb_stu_login_record` VALUES (298, 529272163794949, '120.235.248.254', '广东省0', 1, '2024-04-10 22:26:06');
INSERT INTO `tb_stu_login_record` VALUES (299, 529272163794949, '120.235.248.254', '广东省0', 1, '2024-04-10 22:26:19');
INSERT INTO `tb_stu_login_record` VALUES (300, 529272163794949, '120.235.248.254', '广东省0', 1, '2024-04-10 22:35:02');
INSERT INTO `tb_stu_login_record` VALUES (301, 529272163794949, '120.235.248.254', '广东省0', 1, '2024-04-10 22:40:54');
INSERT INTO `tb_stu_login_record` VALUES (302, 529272163794949, '120.235.248.254', '广东省0', 1, '2024-04-10 22:44:37');
INSERT INTO `tb_stu_login_record` VALUES (303, 529272163794949, '120.235.248.254', '广东省0', 1, '2024-04-10 22:45:32');
INSERT INTO `tb_stu_login_record` VALUES (304, 529272163794949, '120.235.248.254', '广东省0', 1, '2024-04-10 22:47:17');
INSERT INTO `tb_stu_login_record` VALUES (305, 535034288562181, '120.235.248.254', '广东省0', 0, '2024-04-10 22:50:28');
INSERT INTO `tb_stu_login_record` VALUES (306, 529272163794949, '120.235.248.254', '广东省0', 1, '2024-04-10 22:52:03');
INSERT INTO `tb_stu_login_record` VALUES (307, 529272163794949, '120.235.248.254', '广东省0', 1, '2024-04-10 22:52:03');
INSERT INTO `tb_stu_login_record` VALUES (308, 535034288562181, '120.235.248.254', '广东省0', 0, '2024-04-10 22:52:46');
INSERT INTO `tb_stu_login_record` VALUES (309, 535034288562181, '120.235.248.254', '广东省0', 1, '2024-04-10 22:53:41');
INSERT INTO `tb_stu_login_record` VALUES (310, 529272163794949, '120.235.248.254', '广东省0', 1, '2024-04-10 22:57:43');
INSERT INTO `tb_stu_login_record` VALUES (311, 529272163794949, '120.235.248.254', '广东省0', 1, '2024-04-10 23:00:40');
INSERT INTO `tb_stu_login_record` VALUES (312, 529272163794949, '120.235.248.254', '广东省0', 1, '2024-04-10 23:06:07');
INSERT INTO `tb_stu_login_record` VALUES (313, 535034288562181, '120.235.248.254', '广东省0', 1, '2024-04-10 23:09:01');
INSERT INTO `tb_stu_login_record` VALUES (314, 535034288562181, '120.235.248.254', '广东省0', 1, '2024-04-10 23:10:15');
INSERT INTO `tb_stu_login_record` VALUES (315, 535034288562181, '120.235.248.254', '广东省0', 1, '2024-04-10 23:13:10');
INSERT INTO `tb_stu_login_record` VALUES (316, 535034288562181, '120.235.248.254', '广东省0', 0, '2024-04-10 23:18:24');
INSERT INTO `tb_stu_login_record` VALUES (317, 535034288562181, '120.235.248.254', '广东省0', 1, '2024-04-10 23:18:32');
INSERT INTO `tb_stu_login_record` VALUES (318, 535034288562181, '120.235.248.147', '广东省0', 0, '2024-04-11 01:37:35');
INSERT INTO `tb_stu_login_record` VALUES (319, 535034288562181, '120.235.248.147', '广东省0', 0, '2024-04-11 01:37:43');
INSERT INTO `tb_stu_login_record` VALUES (320, 535034288562181, '120.235.248.147', '广东省0', 1, '2024-04-11 01:38:21');
INSERT INTO `tb_stu_login_record` VALUES (321, 535034288562181, '120.235.248.147', '广东省0', 1, '2024-04-11 01:44:07');
INSERT INTO `tb_stu_login_record` VALUES (322, 535034288562181, '120.235.248.147', '广东省0', 0, '2024-04-11 02:11:08');
INSERT INTO `tb_stu_login_record` VALUES (323, 535034288562181, '120.235.248.147', '广东省0', 1, '2024-04-11 02:11:15');
INSERT INTO `tb_stu_login_record` VALUES (324, 535034288562181, '120.235.248.147', '广东省0', 1, '2024-04-11 02:11:18');
INSERT INTO `tb_stu_login_record` VALUES (325, 535034288562181, '120.235.248.147', '广东省0', 1, '2024-04-11 02:11:19');
INSERT INTO `tb_stu_login_record` VALUES (326, 535034288562181, '120.235.248.147', '广东省0', 1, '2024-04-11 02:13:23');
INSERT INTO `tb_stu_login_record` VALUES (327, 535034288562181, '120.235.248.147', '广东省0', 1, '2024-04-11 02:13:23');
INSERT INTO `tb_stu_login_record` VALUES (328, 535034288562181, '120.235.248.147', '广东省0', 1, '2024-04-11 02:18:39');
INSERT INTO `tb_stu_login_record` VALUES (329, 535034288562181, '120.235.248.147', '广东省0', 1, '2024-04-11 02:18:39');
INSERT INTO `tb_stu_login_record` VALUES (330, 535034288562181, '120.235.248.147', '广东省0', 1, '2024-04-11 02:27:00');
INSERT INTO `tb_stu_login_record` VALUES (331, 535034288562181, '120.235.248.147', '广东省0', 1, '2024-04-11 02:27:01');
INSERT INTO `tb_stu_login_record` VALUES (332, 535034288562181, '120.235.248.147', '广东省0', 1, '2024-04-11 02:27:15');
INSERT INTO `tb_stu_login_record` VALUES (333, 535034288562181, '120.235.248.147', '广东省0', 1, '2024-04-11 02:27:15');
INSERT INTO `tb_stu_login_record` VALUES (334, 535034288562181, '120.235.248.147', '广东省0', 1, '2024-04-11 02:35:39');
INSERT INTO `tb_stu_login_record` VALUES (335, 535034288562181, '120.235.248.147', '广东省0', 1, '2024-04-11 02:35:39');
INSERT INTO `tb_stu_login_record` VALUES (336, 535034288562181, '120.235.248.147', '广东省0', 1, '2024-04-11 02:39:22');
INSERT INTO `tb_stu_login_record` VALUES (337, 535034288562181, '120.235.248.147', '广东省0', 1, '2024-04-11 02:39:22');
INSERT INTO `tb_stu_login_record` VALUES (338, 535034288562181, '120.235.248.147', '广东省0', 1, '2024-04-11 02:39:57');
INSERT INTO `tb_stu_login_record` VALUES (339, 535034288562181, '120.235.248.147', '广东省0', 1, '2024-04-11 02:39:57');
INSERT INTO `tb_stu_login_record` VALUES (340, 535034288562181, '120.235.248.147', '广东省0', 1, '2024-04-11 02:41:21');
INSERT INTO `tb_stu_login_record` VALUES (341, 535034288562181, '120.235.248.147', '广东省0', 1, '2024-04-11 02:41:21');
INSERT INTO `tb_stu_login_record` VALUES (342, 535034288562181, '120.235.248.147', '广东省0', 1, '2024-04-11 02:50:27');
INSERT INTO `tb_stu_login_record` VALUES (343, 535034288562181, '120.235.248.147', '广东省0', 1, '2024-04-11 02:50:28');
INSERT INTO `tb_stu_login_record` VALUES (344, 535034288562181, '120.235.248.147', '广东省0', 1, '2024-04-11 02:53:37');
INSERT INTO `tb_stu_login_record` VALUES (345, 535034288562181, '120.235.248.147', '广东省0', 1, '2024-04-11 02:53:37');
INSERT INTO `tb_stu_login_record` VALUES (346, 535034288562181, '119.143.202.192', '广东省广州市', 1, '2024-04-11 10:25:47');
INSERT INTO `tb_stu_login_record` VALUES (347, 535034288562181, '119.143.202.192', '广东省广州市', 1, '2024-04-11 10:25:48');
INSERT INTO `tb_stu_login_record` VALUES (348, 535034288562181, '119.143.202.192', '广东省广州市', 1, '2024-04-11 10:31:34');
INSERT INTO `tb_stu_login_record` VALUES (349, 535034288562181, '119.143.202.192', '广东省广州市', 1, '2024-04-11 10:31:34');
INSERT INTO `tb_stu_login_record` VALUES (350, 535034288562181, '119.143.202.192', '广东省广州市', 1, '2024-04-11 10:34:05');
INSERT INTO `tb_stu_login_record` VALUES (351, 535034288562181, '119.143.202.192', '广东省广州市', 1, '2024-04-11 10:34:05');
INSERT INTO `tb_stu_login_record` VALUES (352, 535034288562181, '119.143.202.192', '广东省广州市', 1, '2024-04-11 10:37:30');
INSERT INTO `tb_stu_login_record` VALUES (353, 535034288562181, '119.143.202.192', '广东省广州市', 1, '2024-04-11 10:37:30');
INSERT INTO `tb_stu_login_record` VALUES (354, 535034288562181, '119.143.202.192', '广东省广州市', 1, '2024-04-11 10:41:25');
INSERT INTO `tb_stu_login_record` VALUES (355, 535034288562181, '119.143.202.192', '广东省广州市', 1, '2024-04-11 10:41:25');
INSERT INTO `tb_stu_login_record` VALUES (356, 535034288562181, '119.143.202.192', '广东省广州市', 1, '2024-04-11 10:41:31');
INSERT INTO `tb_stu_login_record` VALUES (357, 535034288562181, '119.143.202.192', '广东省广州市', 1, '2024-04-11 10:41:31');
INSERT INTO `tb_stu_login_record` VALUES (358, 535034288562181, '119.143.202.192', '广东省广州市', 1, '2024-04-11 10:42:13');
INSERT INTO `tb_stu_login_record` VALUES (359, 535034288562181, '119.143.202.192', '广东省广州市', 1, '2024-04-11 10:42:13');
INSERT INTO `tb_stu_login_record` VALUES (360, 529272163794949, '119.143.202.192', '广东省广州市', 1, '2024-04-11 10:46:22');
INSERT INTO `tb_stu_login_record` VALUES (361, 529272163794949, '119.143.202.192', '广东省广州市', 1, '2024-04-11 10:47:35');
INSERT INTO `tb_stu_login_record` VALUES (362, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 10:55:21');
INSERT INTO `tb_stu_login_record` VALUES (363, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 10:55:21');
INSERT INTO `tb_stu_login_record` VALUES (364, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 10:55:29');
INSERT INTO `tb_stu_login_record` VALUES (365, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 10:55:29');
INSERT INTO `tb_stu_login_record` VALUES (366, 529272163794949, '120.235.248.16', '广东省0', 1, '2024-04-11 10:56:50');
INSERT INTO `tb_stu_login_record` VALUES (367, 529272163794949, '120.235.248.16', '广东省0', 1, '2024-04-11 10:58:15');
INSERT INTO `tb_stu_login_record` VALUES (368, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 10:59:14');
INSERT INTO `tb_stu_login_record` VALUES (369, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 10:59:34');
INSERT INTO `tb_stu_login_record` VALUES (370, 529272163794949, '120.235.248.16', '广东省0', 1, '2024-04-11 10:59:34');
INSERT INTO `tb_stu_login_record` VALUES (371, 529272163794949, '120.235.248.16', '广东省0', 1, '2024-04-11 11:01:48');
INSERT INTO `tb_stu_login_record` VALUES (372, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 11:03:42');
INSERT INTO `tb_stu_login_record` VALUES (373, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 11:03:42');
INSERT INTO `tb_stu_login_record` VALUES (374, 529272163794949, '120.235.248.16', '广东省0', 1, '2024-04-11 11:03:55');
INSERT INTO `tb_stu_login_record` VALUES (375, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 11:04:35');
INSERT INTO `tb_stu_login_record` VALUES (376, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 11:04:35');
INSERT INTO `tb_stu_login_record` VALUES (377, 529272163794949, '120.235.248.16', '广东省0', 1, '2024-04-11 11:04:51');
INSERT INTO `tb_stu_login_record` VALUES (378, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 11:15:56');
INSERT INTO `tb_stu_login_record` VALUES (379, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 11:15:56');
INSERT INTO `tb_stu_login_record` VALUES (380, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 11:16:42');
INSERT INTO `tb_stu_login_record` VALUES (381, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 11:16:42');
INSERT INTO `tb_stu_login_record` VALUES (382, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 11:16:53');
INSERT INTO `tb_stu_login_record` VALUES (383, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 11:16:53');
INSERT INTO `tb_stu_login_record` VALUES (384, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 11:17:15');
INSERT INTO `tb_stu_login_record` VALUES (385, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 11:17:15');
INSERT INTO `tb_stu_login_record` VALUES (386, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 11:19:34');
INSERT INTO `tb_stu_login_record` VALUES (387, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 11:19:36');
INSERT INTO `tb_stu_login_record` VALUES (388, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 11:19:36');
INSERT INTO `tb_stu_login_record` VALUES (389, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 11:21:30');
INSERT INTO `tb_stu_login_record` VALUES (390, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 11:21:30');
INSERT INTO `tb_stu_login_record` VALUES (391, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 11:21:42');
INSERT INTO `tb_stu_login_record` VALUES (392, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 11:21:42');
INSERT INTO `tb_stu_login_record` VALUES (393, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 11:24:15');
INSERT INTO `tb_stu_login_record` VALUES (394, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 11:24:15');
INSERT INTO `tb_stu_login_record` VALUES (395, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 11:26:44');
INSERT INTO `tb_stu_login_record` VALUES (396, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 11:26:44');
INSERT INTO `tb_stu_login_record` VALUES (397, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 11:27:04');
INSERT INTO `tb_stu_login_record` VALUES (398, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 11:27:04');
INSERT INTO `tb_stu_login_record` VALUES (399, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 11:27:17');
INSERT INTO `tb_stu_login_record` VALUES (400, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 11:27:17');
INSERT INTO `tb_stu_login_record` VALUES (401, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 11:27:42');
INSERT INTO `tb_stu_login_record` VALUES (402, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 11:27:42');
INSERT INTO `tb_stu_login_record` VALUES (403, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 11:29:43');
INSERT INTO `tb_stu_login_record` VALUES (404, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 11:29:43');
INSERT INTO `tb_stu_login_record` VALUES (405, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 11:30:54');
INSERT INTO `tb_stu_login_record` VALUES (406, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 11:30:54');
INSERT INTO `tb_stu_login_record` VALUES (407, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 11:32:18');
INSERT INTO `tb_stu_login_record` VALUES (408, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 11:33:29');
INSERT INTO `tb_stu_login_record` VALUES (409, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 11:34:23');
INSERT INTO `tb_stu_login_record` VALUES (410, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 11:35:47');
INSERT INTO `tb_stu_login_record` VALUES (411, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 11:38:23');
INSERT INTO `tb_stu_login_record` VALUES (412, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 11:40:09');
INSERT INTO `tb_stu_login_record` VALUES (413, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 11:40:47');
INSERT INTO `tb_stu_login_record` VALUES (414, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 11:42:06');
INSERT INTO `tb_stu_login_record` VALUES (415, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 11:43:48');
INSERT INTO `tb_stu_login_record` VALUES (416, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 12:30:45');
INSERT INTO `tb_stu_login_record` VALUES (417, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 12:34:02');
INSERT INTO `tb_stu_login_record` VALUES (418, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 12:39:54');
INSERT INTO `tb_stu_login_record` VALUES (419, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 12:46:02');
INSERT INTO `tb_stu_login_record` VALUES (420, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 12:48:10');
INSERT INTO `tb_stu_login_record` VALUES (421, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 12:48:23');
INSERT INTO `tb_stu_login_record` VALUES (422, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 12:48:30');
INSERT INTO `tb_stu_login_record` VALUES (423, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 12:48:39');
INSERT INTO `tb_stu_login_record` VALUES (424, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 12:48:57');
INSERT INTO `tb_stu_login_record` VALUES (425, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 12:50:16');
INSERT INTO `tb_stu_login_record` VALUES (426, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 12:57:28');
INSERT INTO `tb_stu_login_record` VALUES (427, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 12:58:21');
INSERT INTO `tb_stu_login_record` VALUES (428, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 13:00:23');
INSERT INTO `tb_stu_login_record` VALUES (429, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 13:00:56');
INSERT INTO `tb_stu_login_record` VALUES (430, 535034288562181, '192.168.137.64', '0内网IP', 1, '2024-04-11 13:08:26');
INSERT INTO `tb_stu_login_record` VALUES (431, 535034288562181, '192.168.137.64', '0内网IP', 1, '2024-04-11 13:11:41');
INSERT INTO `tb_stu_login_record` VALUES (432, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 13:27:45');
INSERT INTO `tb_stu_login_record` VALUES (433, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 13:30:43');
INSERT INTO `tb_stu_login_record` VALUES (434, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 13:30:58');
INSERT INTO `tb_stu_login_record` VALUES (435, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 13:36:17');
INSERT INTO `tb_stu_login_record` VALUES (436, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 13:40:36');
INSERT INTO `tb_stu_login_record` VALUES (437, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 13:42:06');
INSERT INTO `tb_stu_login_record` VALUES (438, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 13:44:55');
INSERT INTO `tb_stu_login_record` VALUES (439, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 13:47:07');
INSERT INTO `tb_stu_login_record` VALUES (440, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 13:48:04');
INSERT INTO `tb_stu_login_record` VALUES (441, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 13:54:45');
INSERT INTO `tb_stu_login_record` VALUES (442, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 14:01:03');
INSERT INTO `tb_stu_login_record` VALUES (443, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 14:01:20');
INSERT INTO `tb_stu_login_record` VALUES (444, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 14:06:33');
INSERT INTO `tb_stu_login_record` VALUES (445, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 14:06:40');
INSERT INTO `tb_stu_login_record` VALUES (446, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 14:07:18');
INSERT INTO `tb_stu_login_record` VALUES (447, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 14:13:03');
INSERT INTO `tb_stu_login_record` VALUES (448, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 14:13:34');
INSERT INTO `tb_stu_login_record` VALUES (449, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 14:15:55');
INSERT INTO `tb_stu_login_record` VALUES (450, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 14:16:41');
INSERT INTO `tb_stu_login_record` VALUES (451, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 14:36:22');
INSERT INTO `tb_stu_login_record` VALUES (452, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 14:37:32');
INSERT INTO `tb_stu_login_record` VALUES (453, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 14:38:13');
INSERT INTO `tb_stu_login_record` VALUES (454, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 14:42:33');
INSERT INTO `tb_stu_login_record` VALUES (455, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 14:43:01');
INSERT INTO `tb_stu_login_record` VALUES (456, 535034288562181, '119.143.202.192', '广东省广州市', 1, '2024-04-11 14:44:14');
INSERT INTO `tb_stu_login_record` VALUES (457, 535034288562181, '120.235.248.16', '广东省0', 1, '2024-04-11 14:47:25');
INSERT INTO `tb_stu_login_record` VALUES (458, 535034288562181, '119.143.202.192', '广东省广州市', 1, '2024-04-11 14:51:02');
INSERT INTO `tb_stu_login_record` VALUES (459, 535034288562181, '119.143.202.192', '广东省广州市', 1, '2024-04-11 14:51:16');
INSERT INTO `tb_stu_login_record` VALUES (460, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 14:56:17');
INSERT INTO `tb_stu_login_record` VALUES (461, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 14:59:20');
INSERT INTO `tb_stu_login_record` VALUES (462, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 15:00:39');
INSERT INTO `tb_stu_login_record` VALUES (463, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 15:02:26');
INSERT INTO `tb_stu_login_record` VALUES (464, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 15:05:53');
INSERT INTO `tb_stu_login_record` VALUES (465, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 15:08:57');
INSERT INTO `tb_stu_login_record` VALUES (466, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 15:09:31');
INSERT INTO `tb_stu_login_record` VALUES (467, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 15:11:34');
INSERT INTO `tb_stu_login_record` VALUES (468, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 15:12:44');
INSERT INTO `tb_stu_login_record` VALUES (469, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 15:13:16');
INSERT INTO `tb_stu_login_record` VALUES (470, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 15:13:34');
INSERT INTO `tb_stu_login_record` VALUES (471, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 15:19:43');
INSERT INTO `tb_stu_login_record` VALUES (472, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 15:23:12');
INSERT INTO `tb_stu_login_record` VALUES (473, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 15:25:46');
INSERT INTO `tb_stu_login_record` VALUES (474, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 15:27:36');
INSERT INTO `tb_stu_login_record` VALUES (475, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 15:28:24');
INSERT INTO `tb_stu_login_record` VALUES (476, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 15:32:10');
INSERT INTO `tb_stu_login_record` VALUES (477, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 15:37:23');
INSERT INTO `tb_stu_login_record` VALUES (478, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 15:39:11');
INSERT INTO `tb_stu_login_record` VALUES (479, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 15:39:42');
INSERT INTO `tb_stu_login_record` VALUES (480, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 15:40:04');
INSERT INTO `tb_stu_login_record` VALUES (481, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 15:43:14');
INSERT INTO `tb_stu_login_record` VALUES (482, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 15:44:19');
INSERT INTO `tb_stu_login_record` VALUES (483, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 15:46:58');
INSERT INTO `tb_stu_login_record` VALUES (484, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 15:47:35');
INSERT INTO `tb_stu_login_record` VALUES (485, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 15:49:29');
INSERT INTO `tb_stu_login_record` VALUES (486, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 15:50:46');
INSERT INTO `tb_stu_login_record` VALUES (487, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 15:53:59');
INSERT INTO `tb_stu_login_record` VALUES (488, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 15:58:34');
INSERT INTO `tb_stu_login_record` VALUES (489, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 16:05:50');
INSERT INTO `tb_stu_login_record` VALUES (490, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 16:11:21');
INSERT INTO `tb_stu_login_record` VALUES (491, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 16:12:08');
INSERT INTO `tb_stu_login_record` VALUES (492, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 16:12:09');
INSERT INTO `tb_stu_login_record` VALUES (493, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 16:12:19');
INSERT INTO `tb_stu_login_record` VALUES (494, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 16:12:19');
INSERT INTO `tb_stu_login_record` VALUES (495, 535034288562181, '119.143.202.192', '广东省广州市', 1, '2024-04-11 16:16:26');
INSERT INTO `tb_stu_login_record` VALUES (496, 535034288562181, '119.143.202.192', '广东省广州市', 1, '2024-04-11 16:16:26');
INSERT INTO `tb_stu_login_record` VALUES (497, 535034288562181, '119.143.202.192', '广东省广州市', 1, '2024-04-11 16:18:28');
INSERT INTO `tb_stu_login_record` VALUES (498, 535034288562181, '119.143.202.192', '广东省广州市', 1, '2024-04-11 16:18:28');
INSERT INTO `tb_stu_login_record` VALUES (499, 535034288562181, '119.143.202.192', '广东省广州市', 1, '2024-04-11 16:18:39');
INSERT INTO `tb_stu_login_record` VALUES (500, 535034288562181, '119.143.202.192', '广东省广州市', 1, '2024-04-11 16:18:39');
INSERT INTO `tb_stu_login_record` VALUES (501, 535034288562181, '119.143.202.192', '广东省广州市', 1, '2024-04-11 16:18:55');
INSERT INTO `tb_stu_login_record` VALUES (502, 535034288562181, '119.143.202.192', '广东省广州市', 1, '2024-04-11 16:18:55');
INSERT INTO `tb_stu_login_record` VALUES (503, 535034288562181, '119.143.202.192', '广东省广州市', 1, '2024-04-11 16:19:01');
INSERT INTO `tb_stu_login_record` VALUES (504, 535034288562181, '119.143.202.192', '广东省广州市', 1, '2024-04-11 16:19:01');
INSERT INTO `tb_stu_login_record` VALUES (505, 535034288562181, '119.143.202.192', '广东省广州市', 1, '2024-04-11 17:45:32');
INSERT INTO `tb_stu_login_record` VALUES (506, 535034288562181, '119.143.202.192', '广东省广州市', 1, '2024-04-11 17:45:32');
INSERT INTO `tb_stu_login_record` VALUES (507, 535034288562181, '119.143.202.192', '广东省广州市', 1, '2024-04-11 17:51:55');
INSERT INTO `tb_stu_login_record` VALUES (508, 535034288562181, '119.143.202.192', '广东省广州市', 1, '2024-04-11 17:51:55');
INSERT INTO `tb_stu_login_record` VALUES (509, 535034288562181, '119.143.202.192', '广东省广州市', 1, '2024-04-11 17:53:40');
INSERT INTO `tb_stu_login_record` VALUES (510, 535034288562181, '119.143.202.192', '广东省广州市', 1, '2024-04-11 17:53:40');
INSERT INTO `tb_stu_login_record` VALUES (511, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 18:34:14');
INSERT INTO `tb_stu_login_record` VALUES (512, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 18:34:14');
INSERT INTO `tb_stu_login_record` VALUES (513, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 18:35:30');
INSERT INTO `tb_stu_login_record` VALUES (514, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 18:35:30');
INSERT INTO `tb_stu_login_record` VALUES (515, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 18:35:40');
INSERT INTO `tb_stu_login_record` VALUES (516, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 18:35:40');
INSERT INTO `tb_stu_login_record` VALUES (517, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 18:36:20');
INSERT INTO `tb_stu_login_record` VALUES (518, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 18:36:20');
INSERT INTO `tb_stu_login_record` VALUES (519, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 18:37:40');
INSERT INTO `tb_stu_login_record` VALUES (520, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 18:37:40');
INSERT INTO `tb_stu_login_record` VALUES (521, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 18:44:27');
INSERT INTO `tb_stu_login_record` VALUES (522, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 18:44:27');
INSERT INTO `tb_stu_login_record` VALUES (523, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 18:46:25');
INSERT INTO `tb_stu_login_record` VALUES (524, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 18:46:25');
INSERT INTO `tb_stu_login_record` VALUES (525, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 18:47:12');
INSERT INTO `tb_stu_login_record` VALUES (526, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 18:47:12');
INSERT INTO `tb_stu_login_record` VALUES (527, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 18:48:39');
INSERT INTO `tb_stu_login_record` VALUES (528, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 18:48:39');
INSERT INTO `tb_stu_login_record` VALUES (529, 535034288562181, '119.143.202.192', '广东省广州市', 1, '2024-04-11 18:49:39');
INSERT INTO `tb_stu_login_record` VALUES (530, 535034288562181, '119.143.202.192', '广东省广州市', 1, '2024-04-11 18:49:39');
INSERT INTO `tb_stu_login_record` VALUES (531, 535034288562181, '119.143.202.192', '广东省广州市', 1, '2024-04-11 18:49:45');
INSERT INTO `tb_stu_login_record` VALUES (532, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 18:53:03');
INSERT INTO `tb_stu_login_record` VALUES (533, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 18:53:03');
INSERT INTO `tb_stu_login_record` VALUES (534, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 18:53:36');
INSERT INTO `tb_stu_login_record` VALUES (535, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 18:53:36');
INSERT INTO `tb_stu_login_record` VALUES (536, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 18:54:44');
INSERT INTO `tb_stu_login_record` VALUES (537, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 18:54:44');
INSERT INTO `tb_stu_login_record` VALUES (538, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 18:54:50');
INSERT INTO `tb_stu_login_record` VALUES (539, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 18:54:50');
INSERT INTO `tb_stu_login_record` VALUES (540, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 18:55:36');
INSERT INTO `tb_stu_login_record` VALUES (541, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 18:55:36');
INSERT INTO `tb_stu_login_record` VALUES (542, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 18:55:40');
INSERT INTO `tb_stu_login_record` VALUES (543, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 18:55:40');
INSERT INTO `tb_stu_login_record` VALUES (544, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 18:57:23');
INSERT INTO `tb_stu_login_record` VALUES (545, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 19:10:33');
INSERT INTO `tb_stu_login_record` VALUES (546, 535034288562181, '119.143.202.192', '广东省广州市', 1, '2024-04-11 19:11:15');
INSERT INTO `tb_stu_login_record` VALUES (547, 535034288562181, '119.143.202.192', '广东省广州市', 1, '2024-04-11 19:11:16');
INSERT INTO `tb_stu_login_record` VALUES (548, 535034288562181, '119.143.202.192', '广东省广州市', 1, '2024-04-11 19:11:23');
INSERT INTO `tb_stu_login_record` VALUES (549, 535034288562181, '119.143.202.192', '广东省广州市', 1, '2024-04-11 19:11:32');
INSERT INTO `tb_stu_login_record` VALUES (550, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 19:12:14');
INSERT INTO `tb_stu_login_record` VALUES (551, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 19:12:22');
INSERT INTO `tb_stu_login_record` VALUES (552, 535034288562181, '119.143.202.192', '广东省广州市', 1, '2024-04-11 19:12:54');
INSERT INTO `tb_stu_login_record` VALUES (553, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 19:13:56');
INSERT INTO `tb_stu_login_record` VALUES (554, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 19:14:58');
INSERT INTO `tb_stu_login_record` VALUES (555, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 19:14:59');
INSERT INTO `tb_stu_login_record` VALUES (556, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 19:15:04');
INSERT INTO `tb_stu_login_record` VALUES (557, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 19:15:05');
INSERT INTO `tb_stu_login_record` VALUES (558, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 19:17:15');
INSERT INTO `tb_stu_login_record` VALUES (559, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 19:17:15');
INSERT INTO `tb_stu_login_record` VALUES (560, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 19:18:06');
INSERT INTO `tb_stu_login_record` VALUES (561, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 19:18:06');
INSERT INTO `tb_stu_login_record` VALUES (562, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 19:19:07');
INSERT INTO `tb_stu_login_record` VALUES (563, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 19:19:07');
INSERT INTO `tb_stu_login_record` VALUES (564, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 19:20:36');
INSERT INTO `tb_stu_login_record` VALUES (565, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 19:20:36');
INSERT INTO `tb_stu_login_record` VALUES (566, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 19:20:49');
INSERT INTO `tb_stu_login_record` VALUES (567, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 19:20:49');
INSERT INTO `tb_stu_login_record` VALUES (568, 535034288562181, '119.143.202.192', '广东省广州市', 1, '2024-04-11 19:23:18');
INSERT INTO `tb_stu_login_record` VALUES (569, 535034288562181, '119.143.202.192', '广东省广州市', 1, '2024-04-11 19:23:18');
INSERT INTO `tb_stu_login_record` VALUES (570, 535034288562181, '119.143.202.192', '广东省广州市', 1, '2024-04-11 19:24:10');
INSERT INTO `tb_stu_login_record` VALUES (571, 535034288562181, '119.143.202.192', '广东省广州市', 1, '2024-04-11 19:24:10');
INSERT INTO `tb_stu_login_record` VALUES (572, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 19:24:24');
INSERT INTO `tb_stu_login_record` VALUES (573, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 19:24:24');
INSERT INTO `tb_stu_login_record` VALUES (574, 535034288562181, '119.143.202.192', '广东省广州市', 1, '2024-04-11 19:27:57');
INSERT INTO `tb_stu_login_record` VALUES (575, 535034288562181, '119.143.202.192', '广东省广州市', 1, '2024-04-11 19:27:57');
INSERT INTO `tb_stu_login_record` VALUES (576, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 19:29:19');
INSERT INTO `tb_stu_login_record` VALUES (577, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 19:29:19');
INSERT INTO `tb_stu_login_record` VALUES (578, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 19:29:24');
INSERT INTO `tb_stu_login_record` VALUES (579, 535034288562181, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 19:29:24');
INSERT INTO `tb_stu_login_record` VALUES (580, 535332485214213, '119.143.202.192', '广东省广州市', 0, '2024-04-11 19:37:04');
INSERT INTO `tb_stu_login_record` VALUES (581, 535332485214213, '119.143.202.192', '广东省广州市', 1, '2024-04-11 19:41:34');
INSERT INTO `tb_stu_login_record` VALUES (582, 535332485214213, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 19:46:56');
INSERT INTO `tb_stu_login_record` VALUES (583, 535332485214213, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 19:46:56');
INSERT INTO `tb_stu_login_record` VALUES (584, 535332485214213, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 19:47:44');
INSERT INTO `tb_stu_login_record` VALUES (585, 535332485214213, '120.82.32.155', '广东省湛江市', 1, '2024-04-11 19:47:44');
INSERT INTO `tb_stu_login_record` VALUES (586, 535332485214213, '119.143.202.192', '广东省广州市', 1, '2024-04-11 19:48:26');
INSERT INTO `tb_stu_login_record` VALUES (587, 535332485214213, '119.143.202.192', '广东省广州市', 1, '2024-04-11 19:48:26');
INSERT INTO `tb_stu_login_record` VALUES (588, 535332485214213, '119.143.202.192', '广东省广州市', 1, '2024-04-11 19:49:17');

-- ----------------------------
-- Table structure for tb_stu_persona
-- ----------------------------
DROP TABLE IF EXISTS `tb_stu_persona`;
CREATE TABLE `tb_stu_persona`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `gender` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `require_point` int NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_stu_persona
-- ----------------------------
INSERT INTO `tb_stu_persona` VALUES (1, '默认-男', '男', 'https://turingminio.raqtpie.xyz:49000/bud-student/man_default.png', 0, '2024-03-23 00:00:00', '2024-03-23 00:00:00');
INSERT INTO `tb_stu_persona` VALUES (2, '默认-女', '女', 'https://turingminio.raqtpie.xyz:49000/bud-student/women_default.png', 0, '2024-03-23 00:00:00', '2024-03-23 00:00:00');
INSERT INTO `tb_stu_persona` VALUES (3, '男生形象1', '男', 'https://turingminio.raqtpie.xyz:49000/bud-student/man1.png', 100, '2024-03-23 00:00:00', '2024-03-23 00:00:00');
INSERT INTO `tb_stu_persona` VALUES (4, '女生形象1', '女', 'https://turingminio.raqtpie.xyz:49000/bud-student/women1.png', 100, '2024-03-23 00:00:00', '2024-03-23 00:00:00');
INSERT INTO `tb_stu_persona` VALUES (5, '男生形象2', '男', 'https://turingminio.raqtpie.xyz:49000/bud-student/man2.png', 200, '2024-03-23 00:00:00', '2024-03-23 00:00:00');
INSERT INTO `tb_stu_persona` VALUES (6, '女生形象2', '女', 'https://turingminio.raqtpie.xyz:49000/bud-student/women2.png', 200, '2024-03-23 00:00:00', '2024-03-23 00:00:00');
INSERT INTO `tb_stu_persona` VALUES (7, '男生形象3', '男', 'https://turingminio.raqtpie.xyz:49000/bud-student/man3.png', 300, '2024-03-23 00:00:00', '2024-03-23 00:00:00');
INSERT INTO `tb_stu_persona` VALUES (8, '女生形象3', '女', 'https://turingminio.raqtpie.xyz:49000/bud-student/women3.png', 300, '2024-03-23 00:00:00', '2024-03-23 00:00:00');
INSERT INTO `tb_stu_persona` VALUES (9, '男生形象4', '男', 'https://turingminio.raqtpie.xyz:49000/bud-student/man4.png', 400, '2024-03-23 00:00:00', '2024-03-23 00:00:00');
INSERT INTO `tb_stu_persona` VALUES (10, '女生形象4', '女', 'https://turingminio.raqtpie.xyz:49000/bud-student/women4.png', 400, '2024-03-23 00:00:00', '2024-03-23 00:00:00');
INSERT INTO `tb_stu_persona` VALUES (11, '男生形象5', '男', 'https://turingminio.raqtpie.xyz:49000/bud-student/man5.png', 500, '2024-03-23 00:00:00', '2024-03-23 00:00:00');
INSERT INTO `tb_stu_persona` VALUES (12, '女生形象5', '女', 'https://turingminio.raqtpie.xyz:49000/bud-student/women5.png', 500, '2024-03-23 00:00:00', '2024-03-23 00:00:00');

-- ----------------------------
-- Table structure for tb_stu_persona_now
-- ----------------------------
DROP TABLE IF EXISTS `tb_stu_persona_now`;
CREATE TABLE `tb_stu_persona_now`  (
  `student_id` bigint NOT NULL,
  `persona_id` int NOT NULL,
  PRIMARY KEY (`student_id`, `persona_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_stu_persona_now
-- ----------------------------
INSERT INTO `tb_stu_persona_now` VALUES (529272163794949, 5);
INSERT INTO `tb_stu_persona_now` VALUES (534199168622597, 1);
INSERT INTO `tb_stu_persona_now` VALUES (534641419563013, 2);
INSERT INTO `tb_stu_persona_now` VALUES (534649537556485, 2);
INSERT INTO `tb_stu_persona_now` VALUES (534655658229765, 1);
INSERT INTO `tb_stu_persona_now` VALUES (534664236462085, 2);
INSERT INTO `tb_stu_persona_now` VALUES (535034288562181, 1);
INSERT INTO `tb_stu_persona_now` VALUES (535332485214213, 1);

-- ----------------------------
-- Table structure for tb_stu_point
-- ----------------------------
DROP TABLE IF EXISTS `tb_stu_point`;
CREATE TABLE `tb_stu_point`  (
  `id` bigint NOT NULL,
  `id_card` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `school_code` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `task_completed_count` int NULL DEFAULT 0,
  `last_completed_task_time` timestamp NULL DEFAULT NULL,
  `points_total` int NULL DEFAULT 0 COMMENT '总获取积分',
  `points_now` int NULL DEFAULT 0 COMMENT '当前剩余积分',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id_card_unique`(`id_card`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_stu_point
-- ----------------------------
INSERT INTO `tb_stu_point` VALUES (529272163794949, '73be243ca1adacc853ab7f138be9095019c6b915f758898dcd8d6ce49d7a6b7b', '10566', 0, NULL, 355, 53);
INSERT INTO `tb_stu_point` VALUES (534199168622597, 'cc5901c46af4d981ab71574f93521ab784460669df0887a67a90c131e43cfd05', '10566', 0, NULL, 0, 0);
INSERT INTO `tb_stu_point` VALUES (534641419563013, 'bd1999606c03e2627c45889c6129e86303c9676618d115f33975d4f3aeb5e07d', '10566', 0, NULL, 0, 0);
INSERT INTO `tb_stu_point` VALUES (534649537556485, 'b0fe77658d1f7b0605418b993d779786f17acd6bb79ea1e905dc3196a9b8fed1', '10566', 0, NULL, 0, 0);
INSERT INTO `tb_stu_point` VALUES (534655658229765, '9fbbc112716835698b8477004960748fa6e99d3ff6e15e1c48c359607eb15351', '10566', 0, NULL, 0, 0);
INSERT INTO `tb_stu_point` VALUES (534664236462085, '5ead7ffb0c0e1b28a5b555662bf6d7eb935516567c127d29f212802314ecb107', '10566', 0, NULL, 0, 0);
INSERT INTO `tb_stu_point` VALUES (535034288562181, 'c83966210f0c3d9343c9bf101487d3c9221ff9549519f21ae987b881d28add84', '10566', 0, NULL, 10080, 9130);
INSERT INTO `tb_stu_point` VALUES (535332485214213, '707cb910d0ea60dc9d19ca286b934fbae7e3109acabfe04dd98514d6270331d4', '10566', 0, NULL, 15, 15);

-- ----------------------------
-- Table structure for tb_stu_point_record
-- ----------------------------
DROP TABLE IF EXISTS `tb_stu_point_record`;
CREATE TABLE `tb_stu_point_record`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `student_id` bigint NOT NULL,
  `reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `points` int NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_tag
-- ----------------------------
DROP TABLE IF EXISTS `tb_tag`;
CREATE TABLE `tb_tag`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `topic` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `level` int NULL DEFAULT NULL,
  `search_count` int NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_tag
-- ----------------------------
INSERT INTO `tb_tag` VALUES (1, '考研', 5, 2);
INSERT INTO `tb_tag` VALUES (2, '热点', 3, 4562);
INSERT INTO `tb_tag` VALUES (5, '考驾照', 2, 2342);
INSERT INTO `tb_tag` VALUES (6, '美食', 1, 1);
INSERT INTO `tb_tag` VALUES (7, '娱乐', 3, 9763);
INSERT INTO `tb_tag` VALUES (8, '潮流', 1, 0);
INSERT INTO `tb_tag` VALUES (10, '时尚', 1, 0);

-- ----------------------------
-- Table structure for tb_tag_level
-- ----------------------------
DROP TABLE IF EXISTS `tb_tag_level`;
CREATE TABLE `tb_tag_level`  (
  `level` int NOT NULL,
  `price` int NOT NULL,
  PRIMARY KEY (`level`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_tag_level
-- ----------------------------
INSERT INTO `tb_tag_level` VALUES (0, 0);
INSERT INTO `tb_tag_level` VALUES (1, 100);
INSERT INTO `tb_tag_level` VALUES (2, 200);
INSERT INTO `tb_tag_level` VALUES (3, 300);
INSERT INTO `tb_tag_level` VALUES (4, 400);
INSERT INTO `tb_tag_level` VALUES (5, 500);
INSERT INTO `tb_tag_level` VALUES (6, 600);
INSERT INTO `tb_tag_level` VALUES (7, 700);
INSERT INTO `tb_tag_level` VALUES (8, 800);
INSERT INTO `tb_tag_level` VALUES (9, 900);
INSERT INTO `tb_tag_level` VALUES (10, 1000);

-- ----------------------------
-- Table structure for tb_task
-- ----------------------------
DROP TABLE IF EXISTS `tb_task`;
CREATE TABLE `tb_task`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `creator_id` bigint NOT NULL,
  `school_code` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `task_sub_id` int NULL DEFAULT NULL,
  `type` int NOT NULL,
  `topic` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `description` json NOT NULL,
  `obj_id` int NULL DEFAULT NULL,
  `answer` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `location` point NULL,
  `radius` int NULL DEFAULT NULL,
  `award` int NOT NULL,
  `parent_task_id` int NULL DEFAULT NULL,
  `pre_task_id` int NULL DEFAULT NULL,
  `start_time` date NOT NULL,
  `end_time` date NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_task
-- ----------------------------
INSERT INTO `tb_task` VALUES (7, 528534178275399, '10566', 7, 0, '这是一个实地打卡任务的主题', '{\"content\": \"实地打卡任务\"}', 0, '', ST_GeomFromText('POINT(40.7128 -74.006)'), 100, 100, NULL, NULL, '2024-04-06', '2024-04-07', '2024-04-06 16:08:24', '2024-04-08 15:09:06');
INSERT INTO `tb_task` VALUES (8, 528534178275399, '10566', 8, 0, '这是一个实地打卡任务的主题', '{\"content\": \"实地打卡任务\"}', 0, '', ST_GeomFromText('POINT(40.7128 -74.006)'), 100, 100, NULL, NULL, '2024-04-06', '2024-04-07', '2024-04-06 16:14:14', '2024-04-08 15:09:06');
INSERT INTO `tb_task` VALUES (16, 528534178275399, '10566', 15, 2, '选择题', '{\"A\": \"将未清洗的碗筷堆放在水池旁边\", \"B\": \"将垃圾放在地板上而不是垃圾桶里\", \"C\": \"定期清理自己的床铺和个人物品\", \"D\": \"把使用过的衣物随意扔在床上\", \"content\": \"在宿舍中，以下哪种行为是正确的？\"}', NULL, 'C', NULL, NULL, 10, NULL, NULL, '2024-04-06', '2024-04-07', '2024-04-06 21:59:47', '2024-04-10 12:16:32');
INSERT INTO `tb_task` VALUES (21, 528534178275399, '10566', 17, 1, '拍照打卡', '{\"content\": \"请你收拾好你的身份证\"}', 4, NULL, NULL, NULL, 20, 7, NULL, '2024-04-05', '2024-04-06', '2024-04-06 22:43:37', '2024-04-06 22:43:37');
INSERT INTO `tb_task` VALUES (22, 528534178275399, '10566', 18, 1, '拍照打卡', '{\"content\": \"请收拾好你的充电器\"}', 1, NULL, NULL, NULL, 10, NULL, NULL, '2024-04-06', '2024-04-07', '2024-04-07 09:32:58', '2024-04-10 12:16:32');
INSERT INTO `tb_task` VALUES (23, 528534178275399, '10566', 19, 0, '实地打卡', '{\"content\": \"在二教打卡\"}', NULL, NULL, ST_GeomFromText('POINT(21.150894 110.298596)'), 100, 100, NULL, NULL, '2024-04-10', '2024-04-11', '2024-04-11 10:48:24', '2024-04-11 10:48:24');
INSERT INTO `tb_task` VALUES (25, 528534178275399, '10566', 20, 1, '拍照打卡', '{\"content\": \"请收拾好你的鼠标\"}', 5, NULL, NULL, NULL, 20, 7, NULL, '2024-04-10', '2024-04-11', '2024-04-11 17:46:53', '2024-04-11 17:46:53');
INSERT INTO `tb_task` VALUES (26, 528534178275399, '10566', 21, 2, '选择题', '{\"A\": \"北京\", \"B\": \"上海\", \"C\": \"广东\", \"D\": \"海南\", \"content\": \"中国的首都是？\"}', NULL, 'A', NULL, NULL, 20, 7, NULL, '2024-04-10', '2024-04-11', '2024-04-11 17:51:55', '2024-04-11 17:51:55');

-- ----------------------------
-- Table structure for tb_task_complete
-- ----------------------------
DROP TABLE IF EXISTS `tb_task_complete`;
CREATE TABLE `tb_task_complete`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `school_code` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `task_online_id` int NULL DEFAULT NULL,
  `task_id` int NULL DEFAULT NULL,
  `stu_id` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `img_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `location` point NULL,
  `complete_time` timestamp NULL DEFAULT NULL,
  `status` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 60 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_task_complete
-- ----------------------------
INSERT INTO `tb_task_complete` VALUES (57, '10566', 56, NULL, '535034288562181', 'https://turingminio.raqtpie.xyz:49000/bud-student/submitOnlineTask-1416237d-1488-4e63-952f-c84aa335621d.jpeg', NULL, '2024-04-11 08:00:42', 1);
INSERT INTO `tb_task_complete` VALUES (58, '10566', 56, NULL, '535332485214213', 'https://turingminio.raqtpie.xyz:49000/bud-student/submitOnlineTask-f316a24d-5c18-4415-90da-4db5000fc5c7.jpeg', NULL, '2024-04-11 11:42:46', 1);
INSERT INTO `tb_task_complete` VALUES (59, '10566', NULL, 26, '535332485214213', NULL, NULL, '2024-04-11 11:44:55', 1);

-- ----------------------------
-- Table structure for tb_task_object
-- ----------------------------
DROP TABLE IF EXISTS `tb_task_object`;
CREATE TABLE `tb_task_object`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `object_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `object_name_zh` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_task_object
-- ----------------------------
INSERT INTO `tb_task_object` VALUES (1, 'charger', '充电器');
INSERT INTO `tb_task_object` VALUES (2, 'cup', '杯');
INSERT INTO `tb_task_object` VALUES (3, 'suitcase', '手提箱');
INSERT INTO `tb_task_object` VALUES (4, 'ID', '身份证');
INSERT INTO `tb_task_object` VALUES (5, 'mouse', '鼠标');

-- ----------------------------
-- Table structure for tb_task_online
-- ----------------------------
DROP TABLE IF EXISTS `tb_task_online`;
CREATE TABLE `tb_task_online`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `school_code` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `creator_id` bigint NOT NULL,
  `topic` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `obj_id` int NULL DEFAULT NULL,
  `point_award` int NOT NULL,
  `date` date NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 59 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_task_online
-- ----------------------------
INSERT INTO `tb_task_online` VALUES (56, '10566', 528534178275399, '杯子', '请准备物品：杯子', 2, 10, '2024-04-03', '2024-03-31 22:13:22', '2024-04-03 20:40:03');
INSERT INTO `tb_task_online` VALUES (57, '10566', 532549819674629, '毛巾', '请准备物品：毛巾', 3, 5, '2024-04-09', '2024-04-03 22:46:22', '2024-04-09 20:03:48');
INSERT INTO `tb_task_online` VALUES (58, '10566', 528534178275399, '手提箱', '请准备物品：手提箱', 3, 20, '2024-04-10', '2024-04-11 17:38:47', '2024-04-11 17:38:47');

-- ----------------------------
-- Table structure for tb_user_gift
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_gift`;
CREATE TABLE `tb_user_gift`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `gift_id` int NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '领取状态(默认未领取)',
  `exchange_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '兑换时间',
  `get_time` timestamp NULL DEFAULT NULL COMMENT '领取时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user_gift
-- ----------------------------
INSERT INTO `tb_user_gift` VALUES (4, 535034288562181, 30, 1, '2024-04-11 14:08:43', NULL);
INSERT INTO `tb_user_gift` VALUES (5, 535034288562181, 29, 1, '2024-04-11 14:13:42', NULL);
INSERT INTO `tb_user_gift` VALUES (6, 535034288562181, 30, 1, '2024-04-11 14:15:30', NULL);
INSERT INTO `tb_user_gift` VALUES (7, 535034288562181, 30, 1, '2024-04-11 14:16:34', NULL);
INSERT INTO `tb_user_gift` VALUES (8, 535034288562181, 30, 0, '2024-04-11 14:17:37', NULL);
INSERT INTO `tb_user_gift` VALUES (9, 535034288562181, 29, 0, '2024-04-11 14:17:53', NULL);
INSERT INTO `tb_user_gift` VALUES (10, 535034288562181, 30, 0, '2024-04-11 14:44:58', NULL);

SET FOREIGN_KEY_CHECKS = 1;
