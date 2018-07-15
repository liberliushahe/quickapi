-- ----------------------------
-- Table structure for interface
-- ----------------------------
DROP TABLE IF EXISTS `interface`;
CREATE TABLE `interface` (
  `id` int(11) NOT NULL,
  `url` varchar(255) DEFAULT NULL,
  `requestport` varchar(10) DEFAULT NULL,
  `method` varchar(10) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of interface
-- ----------------------------
INSERT INTO `interface` VALUES ('1', 'http://135.149.33.87:7001/smsProxySender', '7001', 'POST', 'http', '网厅发送短信接口');
INSERT INTO `interface` VALUES ('2', 'http://135.149.33.87:7001/SearchProxySender', '7001', 'POST', 'http', '网厅短信日志查询');
INSERT INTO `interface` VALUES ('3', '135.149.16.170', '8080', 'SOCKET', 'SOCKET', '计费清单查询');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(8) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admintest', 'admin');
