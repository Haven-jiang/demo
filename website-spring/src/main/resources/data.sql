drop database if exists `menu`;
CREATE TABLE `menu` (
                          `id` int NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
                          `menu_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '新菜单' COMMENT '菜单名',
                          `path` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '菜单指向路径',
                          `menu_level` int NOT NULL DEFAULT '1' COMMENT '菜单等级',
                          `picture_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'logo id',
                          `parent_id` int DEFAULT '0' COMMENT '子菜单名',
                          `child_id` varchar(255) NOT NULL DEFAULT '0',
                          PRIMARY KEY (`id`) USING BTREE
  ) ENGINE=InnoDB AUTO_INCREMENT=182961 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

drop database if exists `user_auth`;
CREATE TABLE `user_auth` (
                             `id` int NOT NULL AUTO_INCREMENT,
                             `user_info_id` int NOT NULL COMMENT '用户信息id',
                             `user_role_id` int NOT NULL COMMENT '用户信息id',
                             `user_seller_id` int NOT NULL DEFAULT '0' COMMENT '用户信息id',
                             `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
                             `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
                             `login_type` tinyint(1) NOT NULL DEFAULT '1' COMMENT '登录类型',
                             `ip_address` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户登录ip',
                             `ip_source` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'ip来源',
                             `create_time` datetime NOT NULL COMMENT '创建时间',
                             `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                             `last_login_time` datetime DEFAULT NULL COMMENT '上次登录时间',
                             PRIMARY KEY (`id`) USING BTREE,
                             UNIQUE KEY `username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1361712599 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

drop database if exists `user_info`;
CREATE TABLE `user_info` (
                             `id` int NOT NULL AUTO_INCREMENT,
                             `email` varchar(40) DEFAULT NULL,
                             `avatar_id` int DEFAULT NULL,
                             `intro` varchar(200) DEFAULT NULL,
                             `is_ban` int DEFAULT NULL,
                             `nickname` varchar(15) DEFAULT NULL,
                             `cart` varchar(500) DEFAULT NULL,
                             `purchased_list` varchar(1500) DEFAULT NULL,
                             `real_name_auth_id` int DEFAULT '0',
                             `ship_address_source` varchar(800) DEFAULT NULL,
                             `default_ship_address` int DEFAULT NULL,
                             `user_seller_id` int DEFAULT '0',
                             `create_time` datetime NOT NULL COMMENT '创建时间',
                             `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                             PRIMARY KEY (`id`),
                             UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=1211730978 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

drop database if exists `user_real_name_auth`;

CREATE TABLE `user_real_name_auth` (
                                       `id` int NOT NULL AUTO_INCREMENT,
                                       `real_name` int DEFAULT NULL,
                                       `id_card_num` int DEFAULT NULL,
                                       PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

drop database if exists `user_role`;


CREATE TABLE `user_role` (
                             `id` int NOT NULL AUTO_INCREMENT,
                             `user_id` int NOT NULL,
                             `perms` varchar(25) DEFAULT NULL,
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1363346863 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

drop database if exists `user_seller`;


CREATE TABLE `user_seller` (
                               `id` int NOT NULL AUTO_INCREMENT,
                               `goods_list` varchar(2000) DEFAULT NULL,
                               `selling_list` varchar(2000) DEFAULT NULL,
                               `satisfaction_level` int NOT NULL DEFAULT '1',
                               `default_ship_address` varchar(200) DEFAULT NULL,
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci


# 182935,新菜单_0,"",0,,0,"[182936,182938,182939,182940]"
# 182936,新菜单_0_1,"",1,,182935,[182937]
# 182937,新菜单_0_1_1,/pages/new,2,,182936,[]
# 182938,新菜单_0_2,"",1,,182935,[182941]
# 182939,新菜单_0_3,"",1,,182935,[182942]
# 182940,新菜单_0_4,"",1,,182935,[182943]
# 182941,新菜单_0_2_1,/pages/new,2,,182938,[]
# 182942,新菜单_0_3_1,/pages/new,2,,182939,[]
# 182943,新菜单_0_4_1,/pages/new,2,,182940,[]
# 182944,新菜单_1,"",0,,0,"[182949,182950]"
# 182945,新菜单_2,"",0,,0,"[182954,182955]"
# 182946,新菜单_3,"",0,,0,"[182956,182957]"
# 182947,新菜单_4,"",0,,0,[182958]
# 182948,新菜单_5,"",0,,0,[182959]
# 182949,新菜单_1_1,"",2,,182944,[182951]
# 182950,新菜单_1_2,"",2,,182944,"[182952,182953]"
# 182951,新菜单_1_1_1,/pages/new,3,,182949,[]
# 182952,新菜单_1_2_1,/pages/new,3,,182950,[]
# 182953,新菜单_1_2_2,/pages/new,3,,182950,[]
# 182954,新菜单_2_1,/pages/new,1,,182945,[]
# 182955,新菜单_2_2,/pages/new,1,,182945,[]
# 182956,新菜单_3_1,/pages/new,1,,182946,[]
# 182957,新菜单_3_2,/pages/new,1,,182946,[]
# 182958,新菜单_4_1,/pages/new,1,,182947,[]
# 182959,新菜单_5_1,"",1,,182948,[182960]
# 182960,新菜单_5_1_1,/pages/new,1,,182959,[]
