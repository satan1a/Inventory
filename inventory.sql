/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50711
Source Host           : localhost:3306
Source Database       : inventory

Target Server Type    : MYSQL
Target Server Version : 50711
File Encoding         : 65001

Date: 2017-12-11 20:59:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` varchar(255) NOT NULL,
  `inventory` int(11) DEFAULT NULL,
  `maxnum` int(11) DEFAULT NULL,
  `minnum` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `supplierId` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('0001', '4000', '8888', '423', 'MacBook Air', '001', '2');
INSERT INTO `goods` VALUES ('0002', '750', '4000', '230', 'MacBook Pro', '001', '2');
INSERT INTO `goods` VALUES ('0003', '4500', '5000', '900', 'surface pro 4', '002', '2');

-- ----------------------------
-- Table structure for retrieval
-- ----------------------------
DROP TABLE IF EXISTS `retrieval`;
CREATE TABLE `retrieval` (
  `id` varchar(11) NOT NULL,
  `consignee` varchar(255) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `goodsId` varchar(255) DEFAULT NULL,
  `number` int(11) NOT NULL,
  `operator` varchar(255) DEFAULT NULL,
  `outPrice` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of retrieval
-- ----------------------------
INSERT INTO `retrieval` VALUES ('0001', '刘强东', '2017-12-11', '0002', '1250', 'ship', '6000');

-- ----------------------------
-- Table structure for storage
-- ----------------------------
DROP TABLE IF EXISTS `storage`;
CREATE TABLE `storage` (
  `id` varchar(255) NOT NULL,
  `data` date DEFAULT NULL,
  `goodsId` varchar(255) DEFAULT NULL,
  `number` int(11) NOT NULL,
  `offerPrice` double NOT NULL,
  `operator` varchar(255) DEFAULT NULL,
  `supplierId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of storage
-- ----------------------------
INSERT INTO `storage` VALUES ('0001', '2017-12-10', '0001', '4000', '4500', 'buyer', '001');
INSERT INTO `storage` VALUES ('0002', '2017-12-11', '0002', '2000', '12000', 'buyer', '001');
INSERT INTO `storage` VALUES ('0003', '2017-12-11', '0003', '4500', '9000', 'buyer', '002');

-- ----------------------------
-- Table structure for supplier
-- ----------------------------
DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier` (
  `id` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `contactMan` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of supplier
-- ----------------------------
INSERT INTO `supplier` VALUES ('001', 'USA', 'jobs', 'jobs@apple.com', 'apple', '1234234');
INSERT INTO `supplier` VALUES ('002', 'USA', 'Bill', 'Bill@outlook.com', 'MicroSoft', '346346');
INSERT INTO `supplier` VALUES ('003', '深圳市龙岗区坂雪岗', '吴海军', 'hjwu@gmail.com', '神州', '400-106-9999');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '管理员', 'admin');
INSERT INTO `user` VALUES ('2', 'jiyun', '管理员', 'jiyun');
INSERT INTO `user` VALUES ('3', 'buyer', '进货员', 'buyer');
INSERT INTO `user` VALUES ('4', 'ship', '出货员', 'ship');
INSERT INTO `user` VALUES ('5', 'invest', '调查员', 'invest');
