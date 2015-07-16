DROP TABLE IF EXISTS `cspu_info`;
CREATE TABLE `cspu_info`(
	`cspuid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
	`cid` BIGINT(20) NOT NULL COMMENT '后台类目ID',
	`spuid` BIGINT(20) NOT NULL DEFAULT -1 COMMENT '对应的spu（去掉销售属性后的部分）',
	`name` VARCHAR(255) NOT NULL COMMENT 'cspu名称:比如 诺基亚n97 16G 黑色',
	`alias` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT 'Cspu别名，可以多个，分号分开',
	`brand` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '品牌：比如诺基亚',
	`product_model` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '产品型号：对于产品的描述，可以是型号或文本描述，例如茅台飞天酒',
	`product_upc` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '产品条形码',
	`sale_attribute` VARCHAR(2048) NOT NULL DEFAULT '' COMMENT '销售属性pv对（归一化的id对），比如：容量:100ml;颜色:黑色',
	`attribute` VARCHAR(2048) NOT NULL DEFAULT '' COMMENT '一般属性pv对（归一化的id），比如：风格：雪纺；',
	`sale_attribute_orig` VARCHAR(2048) NOT NULL DEFAULT '' COMMENT '原始网站归一化前销售属性pv对',
	`attribute_orig` VARCHAR(2048) NOT NULL DEFAULT '' COMMENT '始网站归一化前一般属性pv对',
	`extend_attribute` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '扩展属性字段，比如以后我们想扩展竞品、搭配商品',
	`pack_list` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '包装清单，例如：鼠标X1,用户文档X1,AA电池X2',
	`price` DECIMAL(10,2) NOT NULL DEFAULT 0 COMMENT '官方价格',
	`url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '对应产品在官网的详情介绍页',
	`big_field` LONGBLOB NOT NULL COMMENT '产品详情图文信息',
	`deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0：正常;1：已删除，会移出表',
	`active` TINYINT(4) NOT NULL DEFAULT '1' COMMENT '是否有效,1有效，0无效',
	`level` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '等级，用于对相应挂靠sku，采取不同更新策略',
	`cspu_from` TINYINT(4) NOT NULL COMMENT '来源  0 MACHINE，1 MANUAL',
	`addtime` DATETIME NOT NULL COMMENT '添加时间',
	`updatetime` DATETIME NOT NULL COMMENT '更新时间',
	PRIMARY KEY (`cspuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '细粒度产品单元信息表';

DROP TABLE IF EXISTS `cspu_level`;
CREATE TABLE `cspu_level`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
	`cspuid` BIGINT(20) NOT NULL COMMENT 'cspuid',
	`level` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '等级，用于对相应挂靠sku，采取不同更新策略',
	`addtime` DATETIME NOT NULL COMMENT '添加时间',
	`updatetime` DATETIME NOT NULL COMMENT '更新时间',
	PRIMARY KEY (`id`),
	KEY `cspuid` (`cspuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '细粒度产品单元更新频率表';

DROP TABLE IF EXISTS `cspu_comment`;
CREATE TABLE `cspu_comment`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
	`cspuid` BIGINT(20) NOT NULL COMMENT 'cspuid',
	`reviewcount` INT(11) NOT NULL DEFAULT 0 COMMENT '评论数',
	`goodcount` INT(11) NOT NULL DEFAULT 0 COMMENT '好评数',
	`medcount` INT(11) NOT NULL DEFAULT 0 COMMENT '中评数',
	`badcount` INT(11) NOT NULL DEFAULT 0 COMMENT '差评数',
	`tags` VARCHAR(255)NOT NULL DEFAULT '' COMMENT 'json串，评论提取tag',
	`score` DOUBLE NOT NULL DEFAULT 0 COMMENT '平均分',
	`addtime` DATETIME NOT NULL COMMENT '添加时间',
	`updatetime` DATETIME NOT NULL COMMENT '更新时间',
	PRIMARY KEY (`id`),
	KEY `cspuid` (`cspuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '细粒度产品单元评价表';

DROP TABLE IF EXISTS `spu_info`;
CREATE TABLE `spu_info`(
	`spuid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
	`cid` BIGINT(20) NOT NULL COMMENT '后台类目ID',
	`name` VARCHAR(255) NOT NULL COMMENT 'spu名称:比如 诺基亚n97 手机',
	`brand` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '品牌：比如诺基亚',
	`product_model` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '产品型号：对于产品的描述，可以是型号或文本描述，例如茅台飞天酒',
	`deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0：正常;1：已删除，会移出表',
	`active` TINYINT(4) NOT NULL DEFAULT '1' COMMENT '是否有效,1有效，0无效',
	`addtime` DATETIME NOT NULL COMMENT '添加时间',
	`updatetime` DATETIME NOT NULL COMMENT '更新时间',
	PRIMARY KEY (`spuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '产品单元信息表';

DROP TABLE IF EXISTS `spu_comment`;
CREATE TABLE `spu_comment`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
	`spuid` BIGINT(20) NOT NULL COMMENT 'Spuid',
	`reviewcount` INT(11) NOT NULL DEFAULT 0 COMMENT '评论数',
	`goodcount` INT(11) NOT NULL DEFAULT 0 COMMENT '好评数',
	`medcount` INT(11) NOT NULL DEFAULT 0 COMMENT '中评数',
	`badcount` INT(11) NOT NULL DEFAULT 0 COMMENT '差评数',
	`tags` VARCHAR(255) NOT NULL DEFAULT '' COMMENT 'json串，评论提取tag',
	`score` DOUBLE NOT NULL DEFAULT 0 COMMENT '平均分',
	`addtime` DATETIME NOT NULL COMMENT '添加时间',
	`updatetime` DATETIME NOT NULL COMMENT '更新时间',
	PRIMARY KEY (`id`),
	KEY `spuid` (`spuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '细粒度产品单元评价表';


DROP TABLE IF EXISTS `bcs_cspu_image`;
CREATE TABLE `bcs_cspu_image`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
	`cspuid` BIGINT(20) NOT NULL COMMENT 'Spuid',
	`image_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片第三方地址',
	`image_bcs_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片本地地址',
	`status` TINYINT(4) NOT NULL DEFAULT '3' COMMENT '图片下载状态，0：done：已下载到本地:1：downing：正在下载:2：error：下载出错:3：undo：未开始。DEFAULT undo',
	`addtime` DATETIME NOT NULL COMMENT '添加时间',
	`updatetime` DATETIME NOT NULL COMMENT '更新时间',
	`errormessage` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '错误信息',
	`width` INT(11) NOT NULL DEFAULT '0' COMMENT '图片宽度',
	`height` INT(11) NOT NULL DEFAULT '0' COMMENT '图片高度',
	`content_md5` CHAR(32) NOT NULL  DEFAULT '' COMMENT '内容MD5',
	`sequence` INT(11) NOT NULL DEFAULT '1' COMMENT '图片序号',
	`gips_image` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '缩略图json串，一堆url和尺寸',
	`type` TINYINT(4) NOT NULL COMMENT '图片类型：主图，俯视图、侧视45度图等',
	PRIMARY KEY (`id`),
	KEY `cspuid` (`cspuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '图片信息表';