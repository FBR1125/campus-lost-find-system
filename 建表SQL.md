CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `role` varchar(20) DEFAULT 'user' COMMENT '角色：user/admin',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像地址',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';



CREATE TABLE `item` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL COMMENT '发布人ID',
  `name` varchar(100) NOT NULL COMMENT '物品名称',
  `place` varchar(255) DEFAULT NULL COMMENT '丢失/捡到地点',
  `description` text COMMENT '物品描述',
  `contact` varchar(50) DEFAULT NULL COMMENT '联系方式',
  `type` int NOT NULL COMMENT '0=丢失 1=捡到',
  `status` int DEFAULT '0' COMMENT '0=待认领 1=已认领',
  `found_status` int DEFAULT '0' COMMENT '0=待找回 1=已找回',
  `check_status` int DEFAULT '0' COMMENT '0=待审核 1=审核通过 2=不通过',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='失物招领物品表';


CREATE TABLE `claim` (
  `id` int NOT NULL AUTO_INCREMENT,
  `item_id` int NOT NULL COMMENT '物品ID',
  `user_id` int NOT NULL COMMENT '认领人ID',
  `message` varchar(255) DEFAULT NULL COMMENT '认领说明',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `item_id` (`item_id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='物品认领记录表';
