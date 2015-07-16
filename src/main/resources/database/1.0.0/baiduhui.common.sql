DROP TABLE IF EXISTS `merchant_info`;
CREATE TABLE `merchant_info`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
	`ucid` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '商家在百度内部的统一ID，和merchantid的区别是，merchantid是产品内部自己定义的',
	`name` VARCHAR(100) NOT NULL COMMENT '商家名称',
	`addtime` DATETIME NOT NULL COMMENT '添加时间',
	`updatetime` DATETIME NOT NULL COMMENT '更新时间',
	PRIMARY KEY (`id`),
	KEY `ucid` (`ucid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品信息表';

DROP TABLE IF EXISTS `region_map`;
CREATE TABLE `region_map`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
	`cityid` INT(11) NOT NULL COMMENT '城市id',
	`cityname` VARCHAR(100) NOT NULL COMMENT '城市名称',
	`regionid` INT(11) NOT NULL COMMENT '地址id，可以为仓库id，目前采用国标id，全国为0',
	`regionname` VARCHAR(100) NOT NULL COMMENT '地域名称',
	`addtime` DATETIME NOT NULL  DEFAULT '1970-01-01 00:00:00' COMMENT '添加时间',
	`updatetime` DATETIME NOT NULL  DEFAULT '1970-01-01 00:00:00' COMMENT '更新时间',
	PRIMARY KEY (`id`),
	KEY `cityid` (`cityid`),
	KEY `regionid` (`regionid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '地域信息表';

DROP TABLE IF EXISTS `merchant_router`;
CREATE TABLE `merchant_router`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
	`merchant_id` BIGINT(20) NOT NULL COMMENT '商家ID',
	`used_shards` VARCHAR(255) NOT NULL COMMENT 'DB分片信息',
	`merchant_amount_type` TINYINT(4) NOT NULL DEFAULT 0 COMMENT '标识商家是否是大小商家: 0小商家，1中商家，2大商家',
	`merchant_router_stat` TINYINT(4) NOT NULL DEFAULT 0 NULL COMMENT '当前路由的状态：0正常，1扩容中',
	`addtime` DATETIME NULL DEFAULT NULL COMMENT '添加时间',
	`updatetime` DATETIME NULL DEFAULT NULL COMMENT '更新时间',
	PRIMARY KEY (`id`),
	KEY `merchant_id` (`merchant_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商家-DB分片映射表';

