CREATE TABLE `user` (
`id` int NOT NULL AUTO_INCREMENT COMMENT '用户ID，主键自增',
`username` varchar(50) NOT NULL COMMENT '用户名',
`password` varchar(100) NOT NULL COMMENT '用户密码',
`phone` varchar(11) DEFAULT NULL COMMENT '手机号',
`create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`role` varchar(20) DEFAULT 'user' COMMENT '用户角色：user/Admin',
`avatar` longtext DEFAULT NULL COMMENT '用户头像（Base64或URL）',
PRIMARY KEY (`id`),
UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

CREATE TABLE `item` (
`id` int NOT NULL AUTO_INCREMENT COMMENT '物品ID，主键自增',
`user_id` int NOT NULL COMMENT '发布用户ID，关联user表',
`name` varchar(100) NOT NULL COMMENT '物品名称',
`description` text COMMENT '物品描述',
`status` tinyint DEFAULT '0' COMMENT '物品状态：1=已认领 0=未认领',
`create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
`type` int DEFAULT NULL COMMENT '物品类型（=0丢失，=1找到）',
`place` varchar(200) DEFAULT NULL COMMENT '丢失/发现地点',
`contact` varchar(50) DEFAULT NULL COMMENT '联系方式',
`check_status` tinyint DEFAULT '0' COMMENT '审核状态：0=待审核 1=已通过 2=已拒绝',
`found_status` int DEFAULT '0' COMMENT '找回状态：0=未找回 1=已找回',
PRIMARY KEY (`id`),
KEY `item_ibfk_1` (`user_id`),
CONSTRAINT `item_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='物品表（失物招领）';


CREATE TABLE `claim` (
`id` int NOT NULL AUTO_INCREMENT COMMENT '认领记录ID，主键自增',
`item_id` int NOT NULL COMMENT '关联的物品ID',
`user_id` int NOT NULL COMMENT '认领用户ID',
`message` varchar(255) DEFAULT NULL COMMENT '认领留言/说明',
`status` int DEFAULT '1' COMMENT '认领状态：0=待认领 1=已认领',
`create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '认领申请时间',
PRIMARY KEY (`id`),
KEY `item_id` (`item_id`),
KEY `user_id` (`user_id`),
CONSTRAINT `claim_ibfk_1` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`) ON DELETE CASCADE,
CONSTRAINT `claim_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='认领申请表';