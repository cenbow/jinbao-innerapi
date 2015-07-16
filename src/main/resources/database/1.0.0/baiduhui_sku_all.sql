DROP TABLE IF EXISTS `sku_attribute_0`;
CREATE TABLE `sku_attribute_0`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT 'Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
`property_id` VARCHAR(2048) NOT NULL DEFAULT '' COMMENT '属性id:属性值id；(存策略归一化后的id)',
`property_values` VARCHAR(2048) NOT NULl DEFAULT '' COMMENT '属性1：属性值；属性2:属性值 (存商家原始属性对)',
`property_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '对property_id+property_values做hash，用于重复判断',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品基础属性表';

DROP TABLE IF EXISTS `sku_cdt_0`;
CREATE TABLE `sku_cdt_0`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`leafcategoryid` BIGINT(20) NOT NULL COMMENT '叶子类目ID',
`classificationtype` TINYINT(4) NOT NULL COMMENT '类目来源 0：手工修改，1：机器学习，其他状态',
`confidence` FLOAT(5,2) NOT NULL DEFAULT '0' COMMENT '取值0-100，可信度(目前没在用)',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品分类信息表';

DROP TABLE IF EXISTS `sku_description_0`;
CREATE TABLE `sku_description_0`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `sku_desc_ori` LONGBLOB NOT NULL COMMENT '商品描述的压缩信息',
    `pd_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '商品描述的压缩信息',
    `sku_desc` LONGBLOB NOT NULL COMMENT '提取出来的商品描述压缩信息',
    `pd_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0 Undo, 1 doing, 2 done',
    `area` INT(11) NOT NULL DEFAULT '0' COMMENT '总的图片区域',
    `word_count` INT(11) NOT NULL DEFAULT '0' COMMENT '总字数',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品描述详情表';

DROP TABLE IF EXISTS `sku_info_0`;
CREATE TABLE `sku_info_0`(
    `sku_innerid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '商品shard内部id，自增id',
    `sku_hashkey` BIGINT(20) NOT NULL COMMENT 'Hash（skuid）的结果',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id,Merchantid(数字转化为8位的明文)拼接outerid。',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `unique_key` CHAR(32) NOT NULL DEFAULT '' COMMENT 'outer_id 的md5值  用来建索引',
    `feedid` BIGINT(20) NOT NULL COMMENT '属于哪个feed，feed平台导入商品时用',
    `ucid` BIGINT(2) NOT NULL DEFAULT '0' COMMENT '商家在百度内部的统一ID，和merchantid的区别是，merchantid是产品内部自己定义的',
    `title` VARCHAR(1024) NOT NULL COMMENT '标题（name字段去除，和title一样的东西）',
    `subtitle` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '副标题',
    `url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(web)',
    `mobileurl` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(移动端)',
    `outerid` VARCHAR(128) NOT NULL COMMENT '商户系统中的商品id',
    `category_ori` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '原网站面包屑（和微购schema不同，这边没有拆开分多个字段）',
    `brand_ori` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '原始网站品牌（归一化后品牌从attribute表找）',
    `starttime` DATETIME NOT NULL COMMENT '开始时间',
    `endtime` DATETIME NOT NULL COMMENT '结束时间',
    `merchant_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '商品状态：0 ONLINE(上架),1 OFFLINE（下架）',
    `manual_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '运营维护状态（0 Normal，1 Pause，2 uncheck，3 check）',
    `signature` VARCHAR(256) NOT NULL COMMENT '商品签名（8张相关表签名字段：除date,输入的绝大多数信息，用于重复判断）',
    `isselfopen` TINYINT(4) NOT NULL COMMENT '是否自营（1：自营，0：非自营）',
    `dataversion` BIGINT(20) NOT NULL COMMENT '数据版本（防止老的覆盖旧的）',
    `inactivetime` DATETIME NOT NULL COMMENT '失效时间（先保留，后续再看逻辑）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    `deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0：正常;1：已删除，会移出表',
    `seller_name` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '商家名称',
    `upc` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '条形码',
    PRIMARY KEY (`sku_innerid`),
    KEY `sku_hashkey` (`sku_hashkey`),
    KEY `skuid` (`skuid`),
    KEY `unique_key` (`unique_key`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品信息表';

DROP TABLE IF EXISTS `sku_pps_0`;
CREATE TABLE `sku_pps_0`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',    
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，用于表关联',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `regionid` INT(11) NOT NULL COMMENT '地址id，可以为仓库id，目前采用国标id，全国为0',
    `terminal` TINYINT(4) NOT NULL COMMENT '0:MOBILE,1:WEB(移动端还是web端等)',
    `promotion_info` VARCHAR(1024) NOT NULl DEFAULT '' COMMENT '促销信息',
    `promotion_price_machine` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：使用促销模型算出来的价格',
    `promotion_price_manual` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：人工修改的促销价格',
    `originalprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '原始价格',
    `discountprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '折扣价',
    `stock` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '库存值状态（1:有货，0：无货）',
    `post_pay` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否支持货到付款（默认不支持）', 
    `m_update_time` DATETIME NOT NULL COMMENT '商家的库存价格更新时间，用于比较时间进行更新',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku价格&库存表';

DROP TABLE IF EXISTS `sku_level_0`;
CREATE TABLE `sku_level_0`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `level` INT(11) NOT NULL DEFAULT '0' COMMENT '确定价格库存更新频率（0更新频率最低）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku更新频率表';

DROP TABLE IF EXISTS `sku_cspu_0`;
CREATE TABLE `sku_cspu_0`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `cspuid` BIGINT(20) NOT NULL COMMENT 'Cspu ID',
    `type` TINYINT(4)  NOT NULL COMMENT 'cspu来源:1:MANUAL_CLASSIFICATION：手工修改，2:MACHINE_CLASSIFICATION：机器学习',
    `confidence` FLOAT(5,2) NOT NULL DEFAULT '0'COMMENT '取值0-100，可信度',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`),
    KEY `cspuid` (`cspuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku到cspu映射';

DROP TABLE IF EXISTS `sku_comment_0`;
CREATE TABLE `sku_comment_0`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `reviewcount` INT(11) NOT NULL COMMENT '评论数',
    `goodcount` INT(11) NOT NULL COMMENT '好评数',
    `medcount` INT(11) NOT NULL COMMENT '中评数',
    `badcount` INT(11) NOT NULL COMMENT '差评数',
    `tags` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT 'json串，评论提取tag',
    `score` DOUBLE NOT NULL COMMENT '平均分',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku评论表';


DROP TABLE IF EXISTS `bcs_sku_image_0`;
CREATE TABLE `bcs_sku_image_0`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
	`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
	`image_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片第三方地址',
	`image_bcs_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片本地地址',
	`status` TINYINT(4) NOT NULL DEFAULT '3' COMMENT '图片下载状态：0未开始，1下载出错，2正在下载，3下载到本地',
	`addtime` DATETIME NOT NULL COMMENT '添加时间',
	`updatetime` DATETIME NOT NULL COMMENT '更新时间',
	`errormessage` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '错误信息',
	`width` INT(11) NOT NULL DEFAULT '0' COMMENT '图片宽度',
	`height` INT(11) NOT NULL DEFAULT '0' COMMENT '图片高度',
	`content_md5` CHAR(32) NOT NULL  DEFAULT '' COMMENT '内容MD5',
	`sequence` INT(11) NOT NULL DEFAULT '1' COMMENT '图片序号',
	`gips_image` VARCHAR(1024) NOT NULL COMMENT '缩略图json串，一堆url和尺寸',
	`type` TINYINT(4) NOT NULL COMMENT '图片类型：主图，俯视图、侧视45度图等',
	PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '图片信息表';
DROP TABLE IF EXISTS `sku_attribute_1`;
CREATE TABLE `sku_attribute_1`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT 'Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
`property_id` VARCHAR(2048) NOT NULL DEFAULT '' COMMENT '属性id:属性值id；(存策略归一化后的id)',
`property_values` VARCHAR(2048) NOT NULl DEFAULT '' COMMENT '属性1：属性值；属性2:属性值 (存商家原始属性对)',
`property_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '对property_id+property_values做hash，用于重复判断',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品基础属性表';

DROP TABLE IF EXISTS `sku_cdt_1`;
CREATE TABLE `sku_cdt_1`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`leafcategoryid` BIGINT(20) NOT NULL COMMENT '叶子类目ID',
`classificationtype` TINYINT(4) NOT NULL COMMENT '类目来源 0：手工修改，1：机器学习，其他状态',
`confidence` FLOAT(5,2) NOT NULL DEFAULT '0' COMMENT '取值0-100，可信度(目前没在用)',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品分类信息表';

DROP TABLE IF EXISTS `sku_description_1`;
CREATE TABLE `sku_description_1`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `sku_desc_ori` LONGBLOB NOT NULL COMMENT '商品描述的压缩信息',
    `pd_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '商品描述的压缩信息',
    `sku_desc` LONGBLOB NOT NULL COMMENT '提取出来的商品描述压缩信息',
    `pd_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0 Undo, 1 doing, 2 done',
    `area` INT(11) NOT NULL DEFAULT '0' COMMENT '总的图片区域',
    `word_count` INT(11) NOT NULL DEFAULT '0' COMMENT '总字数',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品描述详情表';

DROP TABLE IF EXISTS `sku_info_1`;
CREATE TABLE `sku_info_1`(
    `sku_innerid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '商品shard内部id，自增id',
    `sku_hashkey` BIGINT(20) NOT NULL COMMENT 'Hash（skuid）的结果',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id,Merchantid(数字转化为8位的明文)拼接outerid。',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `unique_key` CHAR(32) NOT NULL DEFAULT '' COMMENT 'outer_id 的md5值  用来建索引',
    `feedid` BIGINT(20) NOT NULL COMMENT '属于哪个feed，feed平台导入商品时用',
    `ucid` BIGINT(2) NOT NULL DEFAULT '0' COMMENT '商家在百度内部的统一ID，和merchantid的区别是，merchantid是产品内部自己定义的',
    `title` VARCHAR(1024) NOT NULL COMMENT '标题（name字段去除，和title一样的东西）',
    `subtitle` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '副标题',
    `url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(web)',
    `mobileurl` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(移动端)',
    `outerid` VARCHAR(128) NOT NULL COMMENT '商户系统中的商品id',
    `category_ori` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '原网站面包屑（和微购schema不同，这边没有拆开分多个字段）',
    `brand_ori` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '原始网站品牌（归一化后品牌从attribute表找）',
    `starttime` DATETIME NOT NULL COMMENT '开始时间',
    `endtime` DATETIME NOT NULL COMMENT '结束时间',
    `merchant_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '商品状态：0 ONLINE(上架),1 OFFLINE（下架）',
    `manual_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '运营维护状态（0 Normal，1 Pause，2 uncheck，3 check）',
    `signature` VARCHAR(256) NOT NULL COMMENT '商品签名（8张相关表签名字段：除date,输入的绝大多数信息，用于重复判断）',
    `isselfopen` TINYINT(4) NOT NULL COMMENT '是否自营（1：自营，0：非自营）',
    `dataversion` BIGINT(20) NOT NULL COMMENT '数据版本（防止老的覆盖旧的）',
    `inactivetime` DATETIME NOT NULL COMMENT '失效时间（先保留，后续再看逻辑）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    `deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0：正常;1：已删除，会移出表',
    `seller_name` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '商家名称',
    `upc` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '条形码',
    PRIMARY KEY (`sku_innerid`),
    KEY `sku_hashkey` (`sku_hashkey`),
    KEY `skuid` (`skuid`),
    KEY `unique_key` (`unique_key`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品信息表';

DROP TABLE IF EXISTS `sku_pps_1`;
CREATE TABLE `sku_pps_1`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',    
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，用于表关联',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `regionid` INT(11) NOT NULL COMMENT '地址id，可以为仓库id，目前采用国标id，全国为0',
    `terminal` TINYINT(4) NOT NULL COMMENT '0:MOBILE,1:WEB(移动端还是web端等)',
    `promotion_info` VARCHAR(1024) NOT NULl DEFAULT '' COMMENT '促销信息',
    `promotion_price_machine` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：使用促销模型算出来的价格',
    `promotion_price_manual` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：人工修改的促销价格',
    `originalprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '原始价格',
    `discountprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '折扣价',
    `stock` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '库存值状态（1:有货，0：无货）',
    `post_pay` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否支持货到付款（默认不支持）', 
    `m_update_time` DATETIME NOT NULL COMMENT '商家的库存价格更新时间，用于比较时间进行更新',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku价格&库存表';

DROP TABLE IF EXISTS `sku_level_1`;
CREATE TABLE `sku_level_1`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `level` INT(11) NOT NULL DEFAULT '0' COMMENT '确定价格库存更新频率（0更新频率最低）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku更新频率表';

DROP TABLE IF EXISTS `sku_cspu_1`;
CREATE TABLE `sku_cspu_1`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `cspuid` BIGINT(20) NOT NULL COMMENT 'Cspu ID',
    `type` TINYINT(4)  NOT NULL COMMENT 'cspu来源:1:MANUAL_CLASSIFICATION：手工修改，2:MACHINE_CLASSIFICATION：机器学习',
    `confidence` FLOAT(5,2) NOT NULL DEFAULT '0'COMMENT '取值0-100，可信度',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`),
    KEY `cspuid` (`cspuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku到cspu映射';

DROP TABLE IF EXISTS `sku_comment_1`;
CREATE TABLE `sku_comment_1`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `reviewcount` INT(11) NOT NULL COMMENT '评论数',
    `goodcount` INT(11) NOT NULL COMMENT '好评数',
    `medcount` INT(11) NOT NULL COMMENT '中评数',
    `badcount` INT(11) NOT NULL COMMENT '差评数',
    `tags` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT 'json串，评论提取tag',
    `score` DOUBLE NOT NULL COMMENT '平均分',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku评论表';


DROP TABLE IF EXISTS `bcs_sku_image_1`;
CREATE TABLE `bcs_sku_image_1`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
	`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
	`image_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片第三方地址',
	`image_bcs_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片本地地址',
	`status` TINYINT(4) NOT NULL DEFAULT '3' COMMENT '图片下载状态：0未开始，1下载出错，2正在下载，3下载到本地',
	`addtime` DATETIME NOT NULL COMMENT '添加时间',
	`updatetime` DATETIME NOT NULL COMMENT '更新时间',
	`errormessage` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '错误信息',
	`width` INT(11) NOT NULL DEFAULT '0' COMMENT '图片宽度',
	`height` INT(11) NOT NULL DEFAULT '0' COMMENT '图片高度',
	`content_md5` CHAR(32) NOT NULL  DEFAULT '' COMMENT '内容MD5',
	`sequence` INT(11) NOT NULL DEFAULT '1' COMMENT '图片序号',
	`gips_image` VARCHAR(1024) NOT NULL COMMENT '缩略图json串，一堆url和尺寸',
	`type` TINYINT(4) NOT NULL COMMENT '图片类型：主图，俯视图、侧视45度图等',
	PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '图片信息表';
DROP TABLE IF EXISTS `sku_attribute_2`;
CREATE TABLE `sku_attribute_2`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT 'Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
`property_id` VARCHAR(2048) NOT NULL DEFAULT '' COMMENT '属性id:属性值id；(存策略归一化后的id)',
`property_values` VARCHAR(2048) NOT NULl DEFAULT '' COMMENT '属性1：属性值；属性2:属性值 (存商家原始属性对)',
`property_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '对property_id+property_values做hash，用于重复判断',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品基础属性表';

DROP TABLE IF EXISTS `sku_cdt_2`;
CREATE TABLE `sku_cdt_2`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`leafcategoryid` BIGINT(20) NOT NULL COMMENT '叶子类目ID',
`classificationtype` TINYINT(4) NOT NULL COMMENT '类目来源 0：手工修改，1：机器学习，其他状态',
`confidence` FLOAT(5,2) NOT NULL DEFAULT '0' COMMENT '取值0-100，可信度(目前没在用)',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品分类信息表';

DROP TABLE IF EXISTS `sku_description_2`;
CREATE TABLE `sku_description_2`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `sku_desc_ori` LONGBLOB NOT NULL COMMENT '商品描述的压缩信息',
    `pd_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '商品描述的压缩信息',
    `sku_desc` LONGBLOB NOT NULL COMMENT '提取出来的商品描述压缩信息',
    `pd_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0 Undo, 1 doing, 2 done',
    `area` INT(11) NOT NULL DEFAULT '0' COMMENT '总的图片区域',
    `word_count` INT(11) NOT NULL DEFAULT '0' COMMENT '总字数',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品描述详情表';

DROP TABLE IF EXISTS `sku_info_2`;
CREATE TABLE `sku_info_2`(
    `sku_innerid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '商品shard内部id，自增id',
    `sku_hashkey` BIGINT(20) NOT NULL COMMENT 'Hash（skuid）的结果',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id,Merchantid(数字转化为8位的明文)拼接outerid。',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `unique_key` CHAR(32) NOT NULL DEFAULT '' COMMENT 'outer_id 的md5值  用来建索引',
    `feedid` BIGINT(20) NOT NULL COMMENT '属于哪个feed，feed平台导入商品时用',
    `ucid` BIGINT(2) NOT NULL DEFAULT '0' COMMENT '商家在百度内部的统一ID，和merchantid的区别是，merchantid是产品内部自己定义的',
    `title` VARCHAR(1024) NOT NULL COMMENT '标题（name字段去除，和title一样的东西）',
    `subtitle` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '副标题',
    `url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(web)',
    `mobileurl` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(移动端)',
    `outerid` VARCHAR(128) NOT NULL COMMENT '商户系统中的商品id',
    `category_ori` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '原网站面包屑（和微购schema不同，这边没有拆开分多个字段）',
    `brand_ori` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '原始网站品牌（归一化后品牌从attribute表找）',
    `starttime` DATETIME NOT NULL COMMENT '开始时间',
    `endtime` DATETIME NOT NULL COMMENT '结束时间',
    `merchant_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '商品状态：0 ONLINE(上架),1 OFFLINE（下架）',
    `manual_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '运营维护状态（0 Normal，1 Pause，2 uncheck，3 check）',
    `signature` VARCHAR(256) NOT NULL COMMENT '商品签名（8张相关表签名字段：除date,输入的绝大多数信息，用于重复判断）',
    `isselfopen` TINYINT(4) NOT NULL COMMENT '是否自营（1：自营，0：非自营）',
    `dataversion` BIGINT(20) NOT NULL COMMENT '数据版本（防止老的覆盖旧的）',
    `inactivetime` DATETIME NOT NULL COMMENT '失效时间（先保留，后续再看逻辑）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    `deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0：正常;1：已删除，会移出表',
    `seller_name` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '商家名称',
    `upc` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '条形码',
    PRIMARY KEY (`sku_innerid`),
    KEY `sku_hashkey` (`sku_hashkey`),
    KEY `skuid` (`skuid`),
    KEY `unique_key` (`unique_key`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品信息表';

DROP TABLE IF EXISTS `sku_pps_2`;
CREATE TABLE `sku_pps_2`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',    
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，用于表关联',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `regionid` INT(11) NOT NULL COMMENT '地址id，可以为仓库id，目前采用国标id，全国为0',
    `terminal` TINYINT(4) NOT NULL COMMENT '0:MOBILE,1:WEB(移动端还是web端等)',
    `promotion_info` VARCHAR(1024) NOT NULl DEFAULT '' COMMENT '促销信息',
    `promotion_price_machine` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：使用促销模型算出来的价格',
    `promotion_price_manual` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：人工修改的促销价格',
    `originalprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '原始价格',
    `discountprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '折扣价',
    `stock` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '库存值状态（1:有货，0：无货）',
    `post_pay` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否支持货到付款（默认不支持）', 
    `m_update_time` DATETIME NOT NULL COMMENT '商家的库存价格更新时间，用于比较时间进行更新',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku价格&库存表';

DROP TABLE IF EXISTS `sku_level_2`;
CREATE TABLE `sku_level_2`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `level` INT(11) NOT NULL DEFAULT '0' COMMENT '确定价格库存更新频率（0更新频率最低）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku更新频率表';

DROP TABLE IF EXISTS `sku_cspu_2`;
CREATE TABLE `sku_cspu_2`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `cspuid` BIGINT(20) NOT NULL COMMENT 'Cspu ID',
    `type` TINYINT(4)  NOT NULL COMMENT 'cspu来源:1:MANUAL_CLASSIFICATION：手工修改，2:MACHINE_CLASSIFICATION：机器学习',
    `confidence` FLOAT(5,2) NOT NULL DEFAULT '0'COMMENT '取值0-100，可信度',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`),
    KEY `cspuid` (`cspuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku到cspu映射';

DROP TABLE IF EXISTS `sku_comment_2`;
CREATE TABLE `sku_comment_2`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `reviewcount` INT(11) NOT NULL COMMENT '评论数',
    `goodcount` INT(11) NOT NULL COMMENT '好评数',
    `medcount` INT(11) NOT NULL COMMENT '中评数',
    `badcount` INT(11) NOT NULL COMMENT '差评数',
    `tags` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT 'json串，评论提取tag',
    `score` DOUBLE NOT NULL COMMENT '平均分',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku评论表';


DROP TABLE IF EXISTS `bcs_sku_image_2`;
CREATE TABLE `bcs_sku_image_2`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
	`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
	`image_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片第三方地址',
	`image_bcs_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片本地地址',
	`status` TINYINT(4) NOT NULL DEFAULT '3' COMMENT '图片下载状态：0未开始，1下载出错，2正在下载，3下载到本地',
	`addtime` DATETIME NOT NULL COMMENT '添加时间',
	`updatetime` DATETIME NOT NULL COMMENT '更新时间',
	`errormessage` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '错误信息',
	`width` INT(11) NOT NULL DEFAULT '0' COMMENT '图片宽度',
	`height` INT(11) NOT NULL DEFAULT '0' COMMENT '图片高度',
	`content_md5` CHAR(32) NOT NULL  DEFAULT '' COMMENT '内容MD5',
	`sequence` INT(11) NOT NULL DEFAULT '1' COMMENT '图片序号',
	`gips_image` VARCHAR(1024) NOT NULL COMMENT '缩略图json串，一堆url和尺寸',
	`type` TINYINT(4) NOT NULL COMMENT '图片类型：主图，俯视图、侧视45度图等',
	PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '图片信息表';
DROP TABLE IF EXISTS `sku_attribute_3`;
CREATE TABLE `sku_attribute_3`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT 'Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
`property_id` VARCHAR(2048) NOT NULL DEFAULT '' COMMENT '属性id:属性值id；(存策略归一化后的id)',
`property_values` VARCHAR(2048) NOT NULl DEFAULT '' COMMENT '属性1：属性值；属性2:属性值 (存商家原始属性对)',
`property_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '对property_id+property_values做hash，用于重复判断',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品基础属性表';

DROP TABLE IF EXISTS `sku_cdt_3`;
CREATE TABLE `sku_cdt_3`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`leafcategoryid` BIGINT(20) NOT NULL COMMENT '叶子类目ID',
`classificationtype` TINYINT(4) NOT NULL COMMENT '类目来源 0：手工修改，1：机器学习，其他状态',
`confidence` FLOAT(5,2) NOT NULL DEFAULT '0' COMMENT '取值0-100，可信度(目前没在用)',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品分类信息表';

DROP TABLE IF EXISTS `sku_description_3`;
CREATE TABLE `sku_description_3`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `sku_desc_ori` LONGBLOB NOT NULL COMMENT '商品描述的压缩信息',
    `pd_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '商品描述的压缩信息',
    `sku_desc` LONGBLOB NOT NULL COMMENT '提取出来的商品描述压缩信息',
    `pd_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0 Undo, 1 doing, 2 done',
    `area` INT(11) NOT NULL DEFAULT '0' COMMENT '总的图片区域',
    `word_count` INT(11) NOT NULL DEFAULT '0' COMMENT '总字数',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品描述详情表';

DROP TABLE IF EXISTS `sku_info_3`;
CREATE TABLE `sku_info_3`(
    `sku_innerid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '商品shard内部id，自增id',
    `sku_hashkey` BIGINT(20) NOT NULL COMMENT 'Hash（skuid）的结果',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id,Merchantid(数字转化为8位的明文)拼接outerid。',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `unique_key` CHAR(32) NOT NULL DEFAULT '' COMMENT 'outer_id 的md5值  用来建索引',
    `feedid` BIGINT(20) NOT NULL COMMENT '属于哪个feed，feed平台导入商品时用',
    `ucid` BIGINT(2) NOT NULL DEFAULT '0' COMMENT '商家在百度内部的统一ID，和merchantid的区别是，merchantid是产品内部自己定义的',
    `title` VARCHAR(1024) NOT NULL COMMENT '标题（name字段去除，和title一样的东西）',
    `subtitle` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '副标题',
    `url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(web)',
    `mobileurl` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(移动端)',
    `outerid` VARCHAR(128) NOT NULL COMMENT '商户系统中的商品id',
    `category_ori` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '原网站面包屑（和微购schema不同，这边没有拆开分多个字段）',
    `brand_ori` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '原始网站品牌（归一化后品牌从attribute表找）',
    `starttime` DATETIME NOT NULL COMMENT '开始时间',
    `endtime` DATETIME NOT NULL COMMENT '结束时间',
    `merchant_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '商品状态：0 ONLINE(上架),1 OFFLINE（下架）',
    `manual_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '运营维护状态（0 Normal，1 Pause，2 uncheck，3 check）',
    `signature` VARCHAR(256) NOT NULL COMMENT '商品签名（8张相关表签名字段：除date,输入的绝大多数信息，用于重复判断）',
    `isselfopen` TINYINT(4) NOT NULL COMMENT '是否自营（1：自营，0：非自营）',
    `dataversion` BIGINT(20) NOT NULL COMMENT '数据版本（防止老的覆盖旧的）',
    `inactivetime` DATETIME NOT NULL COMMENT '失效时间（先保留，后续再看逻辑）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    `deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0：正常;1：已删除，会移出表',
    `seller_name` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '商家名称',
    `upc` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '条形码',
    PRIMARY KEY (`sku_innerid`),
    KEY `sku_hashkey` (`sku_hashkey`),
    KEY `skuid` (`skuid`),
    KEY `unique_key` (`unique_key`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品信息表';

DROP TABLE IF EXISTS `sku_pps_3`;
CREATE TABLE `sku_pps_3`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',    
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，用于表关联',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `regionid` INT(11) NOT NULL COMMENT '地址id，可以为仓库id，目前采用国标id，全国为0',
    `terminal` TINYINT(4) NOT NULL COMMENT '0:MOBILE,1:WEB(移动端还是web端等)',
    `promotion_info` VARCHAR(1024) NOT NULl DEFAULT '' COMMENT '促销信息',
    `promotion_price_machine` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：使用促销模型算出来的价格',
    `promotion_price_manual` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：人工修改的促销价格',
    `originalprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '原始价格',
    `discountprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '折扣价',
    `stock` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '库存值状态（1:有货，0：无货）',
    `post_pay` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否支持货到付款（默认不支持）', 
    `m_update_time` DATETIME NOT NULL COMMENT '商家的库存价格更新时间，用于比较时间进行更新',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku价格&库存表';

DROP TABLE IF EXISTS `sku_level_3`;
CREATE TABLE `sku_level_3`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `level` INT(11) NOT NULL DEFAULT '0' COMMENT '确定价格库存更新频率（0更新频率最低）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku更新频率表';

DROP TABLE IF EXISTS `sku_cspu_3`;
CREATE TABLE `sku_cspu_3`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `cspuid` BIGINT(20) NOT NULL COMMENT 'Cspu ID',
    `type` TINYINT(4)  NOT NULL COMMENT 'cspu来源:1:MANUAL_CLASSIFICATION：手工修改，2:MACHINE_CLASSIFICATION：机器学习',
    `confidence` FLOAT(5,2) NOT NULL DEFAULT '0'COMMENT '取值0-100，可信度',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`),
    KEY `cspuid` (`cspuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku到cspu映射';

DROP TABLE IF EXISTS `sku_comment_3`;
CREATE TABLE `sku_comment_3`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `reviewcount` INT(11) NOT NULL COMMENT '评论数',
    `goodcount` INT(11) NOT NULL COMMENT '好评数',
    `medcount` INT(11) NOT NULL COMMENT '中评数',
    `badcount` INT(11) NOT NULL COMMENT '差评数',
    `tags` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT 'json串，评论提取tag',
    `score` DOUBLE NOT NULL COMMENT '平均分',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku评论表';


DROP TABLE IF EXISTS `bcs_sku_image_3`;
CREATE TABLE `bcs_sku_image_3`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
	`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
	`image_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片第三方地址',
	`image_bcs_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片本地地址',
	`status` TINYINT(4) NOT NULL DEFAULT '3' COMMENT '图片下载状态：0未开始，1下载出错，2正在下载，3下载到本地',
	`addtime` DATETIME NOT NULL COMMENT '添加时间',
	`updatetime` DATETIME NOT NULL COMMENT '更新时间',
	`errormessage` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '错误信息',
	`width` INT(11) NOT NULL DEFAULT '0' COMMENT '图片宽度',
	`height` INT(11) NOT NULL DEFAULT '0' COMMENT '图片高度',
	`content_md5` CHAR(32) NOT NULL  DEFAULT '' COMMENT '内容MD5',
	`sequence` INT(11) NOT NULL DEFAULT '1' COMMENT '图片序号',
	`gips_image` VARCHAR(1024) NOT NULL COMMENT '缩略图json串，一堆url和尺寸',
	`type` TINYINT(4) NOT NULL COMMENT '图片类型：主图，俯视图、侧视45度图等',
	PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '图片信息表';
DROP TABLE IF EXISTS `sku_attribute_4`;
CREATE TABLE `sku_attribute_4`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT 'Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
`property_id` VARCHAR(2048) NOT NULL DEFAULT '' COMMENT '属性id:属性值id；(存策略归一化后的id)',
`property_values` VARCHAR(2048) NOT NULl DEFAULT '' COMMENT '属性1：属性值；属性2:属性值 (存商家原始属性对)',
`property_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '对property_id+property_values做hash，用于重复判断',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品基础属性表';

DROP TABLE IF EXISTS `sku_cdt_4`;
CREATE TABLE `sku_cdt_4`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`leafcategoryid` BIGINT(20) NOT NULL COMMENT '叶子类目ID',
`classificationtype` TINYINT(4) NOT NULL COMMENT '类目来源 0：手工修改，1：机器学习，其他状态',
`confidence` FLOAT(5,2) NOT NULL DEFAULT '0' COMMENT '取值0-100，可信度(目前没在用)',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品分类信息表';

DROP TABLE IF EXISTS `sku_description_4`;
CREATE TABLE `sku_description_4`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `sku_desc_ori` LONGBLOB NOT NULL COMMENT '商品描述的压缩信息',
    `pd_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '商品描述的压缩信息',
    `sku_desc` LONGBLOB NOT NULL COMMENT '提取出来的商品描述压缩信息',
    `pd_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0 Undo, 1 doing, 2 done',
    `area` INT(11) NOT NULL DEFAULT '0' COMMENT '总的图片区域',
    `word_count` INT(11) NOT NULL DEFAULT '0' COMMENT '总字数',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品描述详情表';

DROP TABLE IF EXISTS `sku_info_4`;
CREATE TABLE `sku_info_4`(
    `sku_innerid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '商品shard内部id，自增id',
    `sku_hashkey` BIGINT(20) NOT NULL COMMENT 'Hash（skuid）的结果',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id,Merchantid(数字转化为8位的明文)拼接outerid。',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `unique_key` CHAR(32) NOT NULL DEFAULT '' COMMENT 'outer_id 的md5值  用来建索引',
    `feedid` BIGINT(20) NOT NULL COMMENT '属于哪个feed，feed平台导入商品时用',
    `ucid` BIGINT(2) NOT NULL DEFAULT '0' COMMENT '商家在百度内部的统一ID，和merchantid的区别是，merchantid是产品内部自己定义的',
    `title` VARCHAR(1024) NOT NULL COMMENT '标题（name字段去除，和title一样的东西）',
    `subtitle` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '副标题',
    `url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(web)',
    `mobileurl` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(移动端)',
    `outerid` VARCHAR(128) NOT NULL COMMENT '商户系统中的商品id',
    `category_ori` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '原网站面包屑（和微购schema不同，这边没有拆开分多个字段）',
    `brand_ori` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '原始网站品牌（归一化后品牌从attribute表找）',
    `starttime` DATETIME NOT NULL COMMENT '开始时间',
    `endtime` DATETIME NOT NULL COMMENT '结束时间',
    `merchant_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '商品状态：0 ONLINE(上架),1 OFFLINE（下架）',
    `manual_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '运营维护状态（0 Normal，1 Pause，2 uncheck，3 check）',
    `signature` VARCHAR(256) NOT NULL COMMENT '商品签名（8张相关表签名字段：除date,输入的绝大多数信息，用于重复判断）',
    `isselfopen` TINYINT(4) NOT NULL COMMENT '是否自营（1：自营，0：非自营）',
    `dataversion` BIGINT(20) NOT NULL COMMENT '数据版本（防止老的覆盖旧的）',
    `inactivetime` DATETIME NOT NULL COMMENT '失效时间（先保留，后续再看逻辑）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    `deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0：正常;1：已删除，会移出表',
    `seller_name` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '商家名称',
    `upc` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '条形码',
    PRIMARY KEY (`sku_innerid`),
    KEY `sku_hashkey` (`sku_hashkey`),
    KEY `skuid` (`skuid`),
    KEY `unique_key` (`unique_key`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品信息表';

DROP TABLE IF EXISTS `sku_pps_4`;
CREATE TABLE `sku_pps_4`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',    
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，用于表关联',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `regionid` INT(11) NOT NULL COMMENT '地址id，可以为仓库id，目前采用国标id，全国为0',
    `terminal` TINYINT(4) NOT NULL COMMENT '0:MOBILE,1:WEB(移动端还是web端等)',
    `promotion_info` VARCHAR(1024) NOT NULl DEFAULT '' COMMENT '促销信息',
    `promotion_price_machine` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：使用促销模型算出来的价格',
    `promotion_price_manual` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：人工修改的促销价格',
    `originalprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '原始价格',
    `discountprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '折扣价',
    `stock` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '库存值状态（1:有货，0：无货）',
    `post_pay` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否支持货到付款（默认不支持）', 
    `m_update_time` DATETIME NOT NULL COMMENT '商家的库存价格更新时间，用于比较时间进行更新',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku价格&库存表';

DROP TABLE IF EXISTS `sku_level_4`;
CREATE TABLE `sku_level_4`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `level` INT(11) NOT NULL DEFAULT '0' COMMENT '确定价格库存更新频率（0更新频率最低）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku更新频率表';

DROP TABLE IF EXISTS `sku_cspu_4`;
CREATE TABLE `sku_cspu_4`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `cspuid` BIGINT(20) NOT NULL COMMENT 'Cspu ID',
    `type` TINYINT(4)  NOT NULL COMMENT 'cspu来源:1:MANUAL_CLASSIFICATION：手工修改，2:MACHINE_CLASSIFICATION：机器学习',
    `confidence` FLOAT(5,2) NOT NULL DEFAULT '0'COMMENT '取值0-100，可信度',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`),
    KEY `cspuid` (`cspuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku到cspu映射';

DROP TABLE IF EXISTS `sku_comment_4`;
CREATE TABLE `sku_comment_4`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `reviewcount` INT(11) NOT NULL COMMENT '评论数',
    `goodcount` INT(11) NOT NULL COMMENT '好评数',
    `medcount` INT(11) NOT NULL COMMENT '中评数',
    `badcount` INT(11) NOT NULL COMMENT '差评数',
    `tags` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT 'json串，评论提取tag',
    `score` DOUBLE NOT NULL COMMENT '平均分',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku评论表';


DROP TABLE IF EXISTS `bcs_sku_image_4`;
CREATE TABLE `bcs_sku_image_4`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
	`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
	`image_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片第三方地址',
	`image_bcs_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片本地地址',
	`status` TINYINT(4) NOT NULL DEFAULT '3' COMMENT '图片下载状态：0未开始，1下载出错，2正在下载，3下载到本地',
	`addtime` DATETIME NOT NULL COMMENT '添加时间',
	`updatetime` DATETIME NOT NULL COMMENT '更新时间',
	`errormessage` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '错误信息',
	`width` INT(11) NOT NULL DEFAULT '0' COMMENT '图片宽度',
	`height` INT(11) NOT NULL DEFAULT '0' COMMENT '图片高度',
	`content_md5` CHAR(32) NOT NULL  DEFAULT '' COMMENT '内容MD5',
	`sequence` INT(11) NOT NULL DEFAULT '1' COMMENT '图片序号',
	`gips_image` VARCHAR(1024) NOT NULL COMMENT '缩略图json串，一堆url和尺寸',
	`type` TINYINT(4) NOT NULL COMMENT '图片类型：主图，俯视图、侧视45度图等',
	PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '图片信息表';
DROP TABLE IF EXISTS `sku_attribute_5`;
CREATE TABLE `sku_attribute_5`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT 'Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
`property_id` VARCHAR(2048) NOT NULL DEFAULT '' COMMENT '属性id:属性值id；(存策略归一化后的id)',
`property_values` VARCHAR(2048) NOT NULl DEFAULT '' COMMENT '属性1：属性值；属性2:属性值 (存商家原始属性对)',
`property_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '对property_id+property_values做hash，用于重复判断',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品基础属性表';

DROP TABLE IF EXISTS `sku_cdt_5`;
CREATE TABLE `sku_cdt_5`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`leafcategoryid` BIGINT(20) NOT NULL COMMENT '叶子类目ID',
`classificationtype` TINYINT(4) NOT NULL COMMENT '类目来源 0：手工修改，1：机器学习，其他状态',
`confidence` FLOAT(5,2) NOT NULL DEFAULT '0' COMMENT '取值0-100，可信度(目前没在用)',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品分类信息表';

DROP TABLE IF EXISTS `sku_description_5`;
CREATE TABLE `sku_description_5`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `sku_desc_ori` LONGBLOB NOT NULL COMMENT '商品描述的压缩信息',
    `pd_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '商品描述的压缩信息',
    `sku_desc` LONGBLOB NOT NULL COMMENT '提取出来的商品描述压缩信息',
    `pd_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0 Undo, 1 doing, 2 done',
    `area` INT(11) NOT NULL DEFAULT '0' COMMENT '总的图片区域',
    `word_count` INT(11) NOT NULL DEFAULT '0' COMMENT '总字数',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品描述详情表';

DROP TABLE IF EXISTS `sku_info_5`;
CREATE TABLE `sku_info_5`(
    `sku_innerid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '商品shard内部id，自增id',
    `sku_hashkey` BIGINT(20) NOT NULL COMMENT 'Hash（skuid）的结果',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id,Merchantid(数字转化为8位的明文)拼接outerid。',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `unique_key` CHAR(32) NOT NULL DEFAULT '' COMMENT 'outer_id 的md5值  用来建索引',
    `feedid` BIGINT(20) NOT NULL COMMENT '属于哪个feed，feed平台导入商品时用',
    `ucid` BIGINT(2) NOT NULL DEFAULT '0' COMMENT '商家在百度内部的统一ID，和merchantid的区别是，merchantid是产品内部自己定义的',
    `title` VARCHAR(1024) NOT NULL COMMENT '标题（name字段去除，和title一样的东西）',
    `subtitle` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '副标题',
    `url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(web)',
    `mobileurl` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(移动端)',
    `outerid` VARCHAR(128) NOT NULL COMMENT '商户系统中的商品id',
    `category_ori` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '原网站面包屑（和微购schema不同，这边没有拆开分多个字段）',
    `brand_ori` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '原始网站品牌（归一化后品牌从attribute表找）',
    `starttime` DATETIME NOT NULL COMMENT '开始时间',
    `endtime` DATETIME NOT NULL COMMENT '结束时间',
    `merchant_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '商品状态：0 ONLINE(上架),1 OFFLINE（下架）',
    `manual_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '运营维护状态（0 Normal，1 Pause，2 uncheck，3 check）',
    `signature` VARCHAR(256) NOT NULL COMMENT '商品签名（8张相关表签名字段：除date,输入的绝大多数信息，用于重复判断）',
    `isselfopen` TINYINT(4) NOT NULL COMMENT '是否自营（1：自营，0：非自营）',
    `dataversion` BIGINT(20) NOT NULL COMMENT '数据版本（防止老的覆盖旧的）',
    `inactivetime` DATETIME NOT NULL COMMENT '失效时间（先保留，后续再看逻辑）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    `deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0：正常;1：已删除，会移出表',
    `seller_name` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '商家名称',
    `upc` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '条形码',
    PRIMARY KEY (`sku_innerid`),
    KEY `sku_hashkey` (`sku_hashkey`),
    KEY `skuid` (`skuid`),
    KEY `unique_key` (`unique_key`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品信息表';

DROP TABLE IF EXISTS `sku_pps_5`;
CREATE TABLE `sku_pps_5`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',    
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，用于表关联',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `regionid` INT(11) NOT NULL COMMENT '地址id，可以为仓库id，目前采用国标id，全国为0',
    `terminal` TINYINT(4) NOT NULL COMMENT '0:MOBILE,1:WEB(移动端还是web端等)',
    `promotion_info` VARCHAR(1024) NOT NULl DEFAULT '' COMMENT '促销信息',
    `promotion_price_machine` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：使用促销模型算出来的价格',
    `promotion_price_manual` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：人工修改的促销价格',
    `originalprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '原始价格',
    `discountprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '折扣价',
    `stock` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '库存值状态（1:有货，0：无货）',
    `post_pay` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否支持货到付款（默认不支持）', 
    `m_update_time` DATETIME NOT NULL COMMENT '商家的库存价格更新时间，用于比较时间进行更新',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku价格&库存表';

DROP TABLE IF EXISTS `sku_level_5`;
CREATE TABLE `sku_level_5`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `level` INT(11) NOT NULL DEFAULT '0' COMMENT '确定价格库存更新频率（0更新频率最低）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku更新频率表';

DROP TABLE IF EXISTS `sku_cspu_5`;
CREATE TABLE `sku_cspu_5`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `cspuid` BIGINT(20) NOT NULL COMMENT 'Cspu ID',
    `type` TINYINT(4)  NOT NULL COMMENT 'cspu来源:1:MANUAL_CLASSIFICATION：手工修改，2:MACHINE_CLASSIFICATION：机器学习',
    `confidence` FLOAT(5,2) NOT NULL DEFAULT '0'COMMENT '取值0-100，可信度',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`),
    KEY `cspuid` (`cspuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku到cspu映射';

DROP TABLE IF EXISTS `sku_comment_5`;
CREATE TABLE `sku_comment_5`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `reviewcount` INT(11) NOT NULL COMMENT '评论数',
    `goodcount` INT(11) NOT NULL COMMENT '好评数',
    `medcount` INT(11) NOT NULL COMMENT '中评数',
    `badcount` INT(11) NOT NULL COMMENT '差评数',
    `tags` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT 'json串，评论提取tag',
    `score` DOUBLE NOT NULL COMMENT '平均分',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku评论表';


DROP TABLE IF EXISTS `bcs_sku_image_5`;
CREATE TABLE `bcs_sku_image_5`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
	`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
	`image_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片第三方地址',
	`image_bcs_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片本地地址',
	`status` TINYINT(4) NOT NULL DEFAULT '3' COMMENT '图片下载状态：0未开始，1下载出错，2正在下载，3下载到本地',
	`addtime` DATETIME NOT NULL COMMENT '添加时间',
	`updatetime` DATETIME NOT NULL COMMENT '更新时间',
	`errormessage` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '错误信息',
	`width` INT(11) NOT NULL DEFAULT '0' COMMENT '图片宽度',
	`height` INT(11) NOT NULL DEFAULT '0' COMMENT '图片高度',
	`content_md5` CHAR(32) NOT NULL  DEFAULT '' COMMENT '内容MD5',
	`sequence` INT(11) NOT NULL DEFAULT '1' COMMENT '图片序号',
	`gips_image` VARCHAR(1024) NOT NULL COMMENT '缩略图json串，一堆url和尺寸',
	`type` TINYINT(4) NOT NULL COMMENT '图片类型：主图，俯视图、侧视45度图等',
	PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '图片信息表';
DROP TABLE IF EXISTS `sku_attribute_6`;
CREATE TABLE `sku_attribute_6`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT 'Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
`property_id` VARCHAR(2048) NOT NULL DEFAULT '' COMMENT '属性id:属性值id；(存策略归一化后的id)',
`property_values` VARCHAR(2048) NOT NULl DEFAULT '' COMMENT '属性1：属性值；属性2:属性值 (存商家原始属性对)',
`property_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '对property_id+property_values做hash，用于重复判断',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品基础属性表';

DROP TABLE IF EXISTS `sku_cdt_6`;
CREATE TABLE `sku_cdt_6`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`leafcategoryid` BIGINT(20) NOT NULL COMMENT '叶子类目ID',
`classificationtype` TINYINT(4) NOT NULL COMMENT '类目来源 0：手工修改，1：机器学习，其他状态',
`confidence` FLOAT(5,2) NOT NULL DEFAULT '0' COMMENT '取值0-100，可信度(目前没在用)',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品分类信息表';

DROP TABLE IF EXISTS `sku_description_6`;
CREATE TABLE `sku_description_6`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `sku_desc_ori` LONGBLOB NOT NULL COMMENT '商品描述的压缩信息',
    `pd_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '商品描述的压缩信息',
    `sku_desc` LONGBLOB NOT NULL COMMENT '提取出来的商品描述压缩信息',
    `pd_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0 Undo, 1 doing, 2 done',
    `area` INT(11) NOT NULL DEFAULT '0' COMMENT '总的图片区域',
    `word_count` INT(11) NOT NULL DEFAULT '0' COMMENT '总字数',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品描述详情表';

DROP TABLE IF EXISTS `sku_info_6`;
CREATE TABLE `sku_info_6`(
    `sku_innerid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '商品shard内部id，自增id',
    `sku_hashkey` BIGINT(20) NOT NULL COMMENT 'Hash（skuid）的结果',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id,Merchantid(数字转化为8位的明文)拼接outerid。',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `unique_key` CHAR(32) NOT NULL DEFAULT '' COMMENT 'outer_id 的md5值  用来建索引',
    `feedid` BIGINT(20) NOT NULL COMMENT '属于哪个feed，feed平台导入商品时用',
    `ucid` BIGINT(2) NOT NULL DEFAULT '0' COMMENT '商家在百度内部的统一ID，和merchantid的区别是，merchantid是产品内部自己定义的',
    `title` VARCHAR(1024) NOT NULL COMMENT '标题（name字段去除，和title一样的东西）',
    `subtitle` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '副标题',
    `url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(web)',
    `mobileurl` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(移动端)',
    `outerid` VARCHAR(128) NOT NULL COMMENT '商户系统中的商品id',
    `category_ori` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '原网站面包屑（和微购schema不同，这边没有拆开分多个字段）',
    `brand_ori` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '原始网站品牌（归一化后品牌从attribute表找）',
    `starttime` DATETIME NOT NULL COMMENT '开始时间',
    `endtime` DATETIME NOT NULL COMMENT '结束时间',
    `merchant_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '商品状态：0 ONLINE(上架),1 OFFLINE（下架）',
    `manual_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '运营维护状态（0 Normal，1 Pause，2 uncheck，3 check）',
    `signature` VARCHAR(256) NOT NULL COMMENT '商品签名（8张相关表签名字段：除date,输入的绝大多数信息，用于重复判断）',
    `isselfopen` TINYINT(4) NOT NULL COMMENT '是否自营（1：自营，0：非自营）',
    `dataversion` BIGINT(20) NOT NULL COMMENT '数据版本（防止老的覆盖旧的）',
    `inactivetime` DATETIME NOT NULL COMMENT '失效时间（先保留，后续再看逻辑）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    `deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0：正常;1：已删除，会移出表',
    `seller_name` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '商家名称',
    `upc` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '条形码',
    PRIMARY KEY (`sku_innerid`),
    KEY `sku_hashkey` (`sku_hashkey`),
    KEY `skuid` (`skuid`),
    KEY `unique_key` (`unique_key`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品信息表';

DROP TABLE IF EXISTS `sku_pps_6`;
CREATE TABLE `sku_pps_6`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',    
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，用于表关联',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `regionid` INT(11) NOT NULL COMMENT '地址id，可以为仓库id，目前采用国标id，全国为0',
    `terminal` TINYINT(4) NOT NULL COMMENT '0:MOBILE,1:WEB(移动端还是web端等)',
    `promotion_info` VARCHAR(1024) NOT NULl DEFAULT '' COMMENT '促销信息',
    `promotion_price_machine` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：使用促销模型算出来的价格',
    `promotion_price_manual` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：人工修改的促销价格',
    `originalprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '原始价格',
    `discountprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '折扣价',
    `stock` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '库存值状态（1:有货，0：无货）',
    `post_pay` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否支持货到付款（默认不支持）', 
    `m_update_time` DATETIME NOT NULL COMMENT '商家的库存价格更新时间，用于比较时间进行更新',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku价格&库存表';

DROP TABLE IF EXISTS `sku_level_6`;
CREATE TABLE `sku_level_6`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `level` INT(11) NOT NULL DEFAULT '0' COMMENT '确定价格库存更新频率（0更新频率最低）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku更新频率表';

DROP TABLE IF EXISTS `sku_cspu_6`;
CREATE TABLE `sku_cspu_6`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `cspuid` BIGINT(20) NOT NULL COMMENT 'Cspu ID',
    `type` TINYINT(4)  NOT NULL COMMENT 'cspu来源:1:MANUAL_CLASSIFICATION：手工修改，2:MACHINE_CLASSIFICATION：机器学习',
    `confidence` FLOAT(5,2) NOT NULL DEFAULT '0'COMMENT '取值0-100，可信度',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`),
    KEY `cspuid` (`cspuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku到cspu映射';

DROP TABLE IF EXISTS `sku_comment_6`;
CREATE TABLE `sku_comment_6`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `reviewcount` INT(11) NOT NULL COMMENT '评论数',
    `goodcount` INT(11) NOT NULL COMMENT '好评数',
    `medcount` INT(11) NOT NULL COMMENT '中评数',
    `badcount` INT(11) NOT NULL COMMENT '差评数',
    `tags` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT 'json串，评论提取tag',
    `score` DOUBLE NOT NULL COMMENT '平均分',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku评论表';


DROP TABLE IF EXISTS `bcs_sku_image_6`;
CREATE TABLE `bcs_sku_image_6`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
	`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
	`image_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片第三方地址',
	`image_bcs_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片本地地址',
	`status` TINYINT(4) NOT NULL DEFAULT '3' COMMENT '图片下载状态：0未开始，1下载出错，2正在下载，3下载到本地',
	`addtime` DATETIME NOT NULL COMMENT '添加时间',
	`updatetime` DATETIME NOT NULL COMMENT '更新时间',
	`errormessage` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '错误信息',
	`width` INT(11) NOT NULL DEFAULT '0' COMMENT '图片宽度',
	`height` INT(11) NOT NULL DEFAULT '0' COMMENT '图片高度',
	`content_md5` CHAR(32) NOT NULL  DEFAULT '' COMMENT '内容MD5',
	`sequence` INT(11) NOT NULL DEFAULT '1' COMMENT '图片序号',
	`gips_image` VARCHAR(1024) NOT NULL COMMENT '缩略图json串，一堆url和尺寸',
	`type` TINYINT(4) NOT NULL COMMENT '图片类型：主图，俯视图、侧视45度图等',
	PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '图片信息表';
DROP TABLE IF EXISTS `sku_attribute_7`;
CREATE TABLE `sku_attribute_7`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT 'Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
`property_id` VARCHAR(2048) NOT NULL DEFAULT '' COMMENT '属性id:属性值id；(存策略归一化后的id)',
`property_values` VARCHAR(2048) NOT NULl DEFAULT '' COMMENT '属性1：属性值；属性2:属性值 (存商家原始属性对)',
`property_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '对property_id+property_values做hash，用于重复判断',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品基础属性表';

DROP TABLE IF EXISTS `sku_cdt_7`;
CREATE TABLE `sku_cdt_7`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`leafcategoryid` BIGINT(20) NOT NULL COMMENT '叶子类目ID',
`classificationtype` TINYINT(4) NOT NULL COMMENT '类目来源 0：手工修改，1：机器学习，其他状态',
`confidence` FLOAT(5,2) NOT NULL DEFAULT '0' COMMENT '取值0-100，可信度(目前没在用)',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品分类信息表';

DROP TABLE IF EXISTS `sku_description_7`;
CREATE TABLE `sku_description_7`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `sku_desc_ori` LONGBLOB NOT NULL COMMENT '商品描述的压缩信息',
    `pd_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '商品描述的压缩信息',
    `sku_desc` LONGBLOB NOT NULL COMMENT '提取出来的商品描述压缩信息',
    `pd_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0 Undo, 1 doing, 2 done',
    `area` INT(11) NOT NULL DEFAULT '0' COMMENT '总的图片区域',
    `word_count` INT(11) NOT NULL DEFAULT '0' COMMENT '总字数',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品描述详情表';

DROP TABLE IF EXISTS `sku_info_7`;
CREATE TABLE `sku_info_7`(
    `sku_innerid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '商品shard内部id，自增id',
    `sku_hashkey` BIGINT(20) NOT NULL COMMENT 'Hash（skuid）的结果',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id,Merchantid(数字转化为8位的明文)拼接outerid。',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `unique_key` CHAR(32) NOT NULL DEFAULT '' COMMENT 'outer_id 的md5值  用来建索引',
    `feedid` BIGINT(20) NOT NULL COMMENT '属于哪个feed，feed平台导入商品时用',
    `ucid` BIGINT(2) NOT NULL DEFAULT '0' COMMENT '商家在百度内部的统一ID，和merchantid的区别是，merchantid是产品内部自己定义的',
    `title` VARCHAR(1024) NOT NULL COMMENT '标题（name字段去除，和title一样的东西）',
    `subtitle` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '副标题',
    `url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(web)',
    `mobileurl` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(移动端)',
    `outerid` VARCHAR(128) NOT NULL COMMENT '商户系统中的商品id',
    `category_ori` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '原网站面包屑（和微购schema不同，这边没有拆开分多个字段）',
    `brand_ori` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '原始网站品牌（归一化后品牌从attribute表找）',
    `starttime` DATETIME NOT NULL COMMENT '开始时间',
    `endtime` DATETIME NOT NULL COMMENT '结束时间',
    `merchant_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '商品状态：0 ONLINE(上架),1 OFFLINE（下架）',
    `manual_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '运营维护状态（0 Normal，1 Pause，2 uncheck，3 check）',
    `signature` VARCHAR(256) NOT NULL COMMENT '商品签名（8张相关表签名字段：除date,输入的绝大多数信息，用于重复判断）',
    `isselfopen` TINYINT(4) NOT NULL COMMENT '是否自营（1：自营，0：非自营）',
    `dataversion` BIGINT(20) NOT NULL COMMENT '数据版本（防止老的覆盖旧的）',
    `inactivetime` DATETIME NOT NULL COMMENT '失效时间（先保留，后续再看逻辑）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    `deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0：正常;1：已删除，会移出表',
    `seller_name` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '商家名称',
    `upc` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '条形码',
    PRIMARY KEY (`sku_innerid`),
    KEY `sku_hashkey` (`sku_hashkey`),
    KEY `skuid` (`skuid`),
    KEY `unique_key` (`unique_key`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品信息表';

DROP TABLE IF EXISTS `sku_pps_7`;
CREATE TABLE `sku_pps_7`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',    
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，用于表关联',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `regionid` INT(11) NOT NULL COMMENT '地址id，可以为仓库id，目前采用国标id，全国为0',
    `terminal` TINYINT(4) NOT NULL COMMENT '0:MOBILE,1:WEB(移动端还是web端等)',
    `promotion_info` VARCHAR(1024) NOT NULl DEFAULT '' COMMENT '促销信息',
    `promotion_price_machine` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：使用促销模型算出来的价格',
    `promotion_price_manual` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：人工修改的促销价格',
    `originalprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '原始价格',
    `discountprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '折扣价',
    `stock` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '库存值状态（1:有货，0：无货）',
    `post_pay` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否支持货到付款（默认不支持）', 
    `m_update_time` DATETIME NOT NULL COMMENT '商家的库存价格更新时间，用于比较时间进行更新',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku价格&库存表';

DROP TABLE IF EXISTS `sku_level_7`;
CREATE TABLE `sku_level_7`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `level` INT(11) NOT NULL DEFAULT '0' COMMENT '确定价格库存更新频率（0更新频率最低）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku更新频率表';

DROP TABLE IF EXISTS `sku_cspu_7`;
CREATE TABLE `sku_cspu_7`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `cspuid` BIGINT(20) NOT NULL COMMENT 'Cspu ID',
    `type` TINYINT(4)  NOT NULL COMMENT 'cspu来源:1:MANUAL_CLASSIFICATION：手工修改，2:MACHINE_CLASSIFICATION：机器学习',
    `confidence` FLOAT(5,2) NOT NULL DEFAULT '0'COMMENT '取值0-100，可信度',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`),
    KEY `cspuid` (`cspuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku到cspu映射';

DROP TABLE IF EXISTS `sku_comment_7`;
CREATE TABLE `sku_comment_7`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `reviewcount` INT(11) NOT NULL COMMENT '评论数',
    `goodcount` INT(11) NOT NULL COMMENT '好评数',
    `medcount` INT(11) NOT NULL COMMENT '中评数',
    `badcount` INT(11) NOT NULL COMMENT '差评数',
    `tags` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT 'json串，评论提取tag',
    `score` DOUBLE NOT NULL COMMENT '平均分',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku评论表';


DROP TABLE IF EXISTS `bcs_sku_image_7`;
CREATE TABLE `bcs_sku_image_7`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
	`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
	`image_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片第三方地址',
	`image_bcs_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片本地地址',
	`status` TINYINT(4) NOT NULL DEFAULT '3' COMMENT '图片下载状态：0未开始，1下载出错，2正在下载，3下载到本地',
	`addtime` DATETIME NOT NULL COMMENT '添加时间',
	`updatetime` DATETIME NOT NULL COMMENT '更新时间',
	`errormessage` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '错误信息',
	`width` INT(11) NOT NULL DEFAULT '0' COMMENT '图片宽度',
	`height` INT(11) NOT NULL DEFAULT '0' COMMENT '图片高度',
	`content_md5` CHAR(32) NOT NULL  DEFAULT '' COMMENT '内容MD5',
	`sequence` INT(11) NOT NULL DEFAULT '1' COMMENT '图片序号',
	`gips_image` VARCHAR(1024) NOT NULL COMMENT '缩略图json串，一堆url和尺寸',
	`type` TINYINT(4) NOT NULL COMMENT '图片类型：主图，俯视图、侧视45度图等',
	PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '图片信息表';
DROP TABLE IF EXISTS `sku_attribute_8`;
CREATE TABLE `sku_attribute_8`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT 'Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
`property_id` VARCHAR(2048) NOT NULL DEFAULT '' COMMENT '属性id:属性值id；(存策略归一化后的id)',
`property_values` VARCHAR(2048) NOT NULl DEFAULT '' COMMENT '属性1：属性值；属性2:属性值 (存商家原始属性对)',
`property_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '对property_id+property_values做hash，用于重复判断',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品基础属性表';

DROP TABLE IF EXISTS `sku_cdt_8`;
CREATE TABLE `sku_cdt_8`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`leafcategoryid` BIGINT(20) NOT NULL COMMENT '叶子类目ID',
`classificationtype` TINYINT(4) NOT NULL COMMENT '类目来源 0：手工修改，1：机器学习，其他状态',
`confidence` FLOAT(5,2) NOT NULL DEFAULT '0' COMMENT '取值0-100，可信度(目前没在用)',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品分类信息表';

DROP TABLE IF EXISTS `sku_description_8`;
CREATE TABLE `sku_description_8`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `sku_desc_ori` LONGBLOB NOT NULL COMMENT '商品描述的压缩信息',
    `pd_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '商品描述的压缩信息',
    `sku_desc` LONGBLOB NOT NULL COMMENT '提取出来的商品描述压缩信息',
    `pd_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0 Undo, 1 doing, 2 done',
    `area` INT(11) NOT NULL DEFAULT '0' COMMENT '总的图片区域',
    `word_count` INT(11) NOT NULL DEFAULT '0' COMMENT '总字数',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品描述详情表';

DROP TABLE IF EXISTS `sku_info_8`;
CREATE TABLE `sku_info_8`(
    `sku_innerid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '商品shard内部id，自增id',
    `sku_hashkey` BIGINT(20) NOT NULL COMMENT 'Hash（skuid）的结果',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id,Merchantid(数字转化为8位的明文)拼接outerid。',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `unique_key` CHAR(32) NOT NULL DEFAULT '' COMMENT 'outer_id 的md5值  用来建索引',
    `feedid` BIGINT(20) NOT NULL COMMENT '属于哪个feed，feed平台导入商品时用',
    `ucid` BIGINT(2) NOT NULL DEFAULT '0' COMMENT '商家在百度内部的统一ID，和merchantid的区别是，merchantid是产品内部自己定义的',
    `title` VARCHAR(1024) NOT NULL COMMENT '标题（name字段去除，和title一样的东西）',
    `subtitle` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '副标题',
    `url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(web)',
    `mobileurl` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(移动端)',
    `outerid` VARCHAR(128) NOT NULL COMMENT '商户系统中的商品id',
    `category_ori` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '原网站面包屑（和微购schema不同，这边没有拆开分多个字段）',
    `brand_ori` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '原始网站品牌（归一化后品牌从attribute表找）',
    `starttime` DATETIME NOT NULL COMMENT '开始时间',
    `endtime` DATETIME NOT NULL COMMENT '结束时间',
    `merchant_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '商品状态：0 ONLINE(上架),1 OFFLINE（下架）',
    `manual_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '运营维护状态（0 Normal，1 Pause，2 uncheck，3 check）',
    `signature` VARCHAR(256) NOT NULL COMMENT '商品签名（8张相关表签名字段：除date,输入的绝大多数信息，用于重复判断）',
    `isselfopen` TINYINT(4) NOT NULL COMMENT '是否自营（1：自营，0：非自营）',
    `dataversion` BIGINT(20) NOT NULL COMMENT '数据版本（防止老的覆盖旧的）',
    `inactivetime` DATETIME NOT NULL COMMENT '失效时间（先保留，后续再看逻辑）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    `deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0：正常;1：已删除，会移出表',
    `seller_name` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '商家名称',
    `upc` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '条形码',
    PRIMARY KEY (`sku_innerid`),
    KEY `sku_hashkey` (`sku_hashkey`),
    KEY `skuid` (`skuid`),
    KEY `unique_key` (`unique_key`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品信息表';

DROP TABLE IF EXISTS `sku_pps_8`;
CREATE TABLE `sku_pps_8`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',    
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，用于表关联',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `regionid` INT(11) NOT NULL COMMENT '地址id，可以为仓库id，目前采用国标id，全国为0',
    `terminal` TINYINT(4) NOT NULL COMMENT '0:MOBILE,1:WEB(移动端还是web端等)',
    `promotion_info` VARCHAR(1024) NOT NULl DEFAULT '' COMMENT '促销信息',
    `promotion_price_machine` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：使用促销模型算出来的价格',
    `promotion_price_manual` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：人工修改的促销价格',
    `originalprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '原始价格',
    `discountprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '折扣价',
    `stock` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '库存值状态（1:有货，0：无货）',
    `post_pay` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否支持货到付款（默认不支持）', 
    `m_update_time` DATETIME NOT NULL COMMENT '商家的库存价格更新时间，用于比较时间进行更新',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku价格&库存表';

DROP TABLE IF EXISTS `sku_level_8`;
CREATE TABLE `sku_level_8`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `level` INT(11) NOT NULL DEFAULT '0' COMMENT '确定价格库存更新频率（0更新频率最低）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku更新频率表';

DROP TABLE IF EXISTS `sku_cspu_8`;
CREATE TABLE `sku_cspu_8`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `cspuid` BIGINT(20) NOT NULL COMMENT 'Cspu ID',
    `type` TINYINT(4)  NOT NULL COMMENT 'cspu来源:1:MANUAL_CLASSIFICATION：手工修改，2:MACHINE_CLASSIFICATION：机器学习',
    `confidence` FLOAT(5,2) NOT NULL DEFAULT '0'COMMENT '取值0-100，可信度',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`),
    KEY `cspuid` (`cspuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku到cspu映射';

DROP TABLE IF EXISTS `sku_comment_8`;
CREATE TABLE `sku_comment_8`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `reviewcount` INT(11) NOT NULL COMMENT '评论数',
    `goodcount` INT(11) NOT NULL COMMENT '好评数',
    `medcount` INT(11) NOT NULL COMMENT '中评数',
    `badcount` INT(11) NOT NULL COMMENT '差评数',
    `tags` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT 'json串，评论提取tag',
    `score` DOUBLE NOT NULL COMMENT '平均分',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku评论表';


DROP TABLE IF EXISTS `bcs_sku_image_8`;
CREATE TABLE `bcs_sku_image_8`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
	`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
	`image_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片第三方地址',
	`image_bcs_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片本地地址',
	`status` TINYINT(4) NOT NULL DEFAULT '3' COMMENT '图片下载状态：0未开始，1下载出错，2正在下载，3下载到本地',
	`addtime` DATETIME NOT NULL COMMENT '添加时间',
	`updatetime` DATETIME NOT NULL COMMENT '更新时间',
	`errormessage` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '错误信息',
	`width` INT(11) NOT NULL DEFAULT '0' COMMENT '图片宽度',
	`height` INT(11) NOT NULL DEFAULT '0' COMMENT '图片高度',
	`content_md5` CHAR(32) NOT NULL  DEFAULT '' COMMENT '内容MD5',
	`sequence` INT(11) NOT NULL DEFAULT '1' COMMENT '图片序号',
	`gips_image` VARCHAR(1024) NOT NULL COMMENT '缩略图json串，一堆url和尺寸',
	`type` TINYINT(4) NOT NULL COMMENT '图片类型：主图，俯视图、侧视45度图等',
	PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '图片信息表';
DROP TABLE IF EXISTS `sku_attribute_9`;
CREATE TABLE `sku_attribute_9`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT 'Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
`property_id` VARCHAR(2048) NOT NULL DEFAULT '' COMMENT '属性id:属性值id；(存策略归一化后的id)',
`property_values` VARCHAR(2048) NOT NULl DEFAULT '' COMMENT '属性1：属性值；属性2:属性值 (存商家原始属性对)',
`property_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '对property_id+property_values做hash，用于重复判断',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品基础属性表';

DROP TABLE IF EXISTS `sku_cdt_9`;
CREATE TABLE `sku_cdt_9`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`leafcategoryid` BIGINT(20) NOT NULL COMMENT '叶子类目ID',
`classificationtype` TINYINT(4) NOT NULL COMMENT '类目来源 0：手工修改，1：机器学习，其他状态',
`confidence` FLOAT(5,2) NOT NULL DEFAULT '0' COMMENT '取值0-100，可信度(目前没在用)',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品分类信息表';

DROP TABLE IF EXISTS `sku_description_9`;
CREATE TABLE `sku_description_9`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `sku_desc_ori` LONGBLOB NOT NULL COMMENT '商品描述的压缩信息',
    `pd_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '商品描述的压缩信息',
    `sku_desc` LONGBLOB NOT NULL COMMENT '提取出来的商品描述压缩信息',
    `pd_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0 Undo, 1 doing, 2 done',
    `area` INT(11) NOT NULL DEFAULT '0' COMMENT '总的图片区域',
    `word_count` INT(11) NOT NULL DEFAULT '0' COMMENT '总字数',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品描述详情表';

DROP TABLE IF EXISTS `sku_info_9`;
CREATE TABLE `sku_info_9`(
    `sku_innerid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '商品shard内部id，自增id',
    `sku_hashkey` BIGINT(20) NOT NULL COMMENT 'Hash（skuid）的结果',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id,Merchantid(数字转化为8位的明文)拼接outerid。',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `unique_key` CHAR(32) NOT NULL DEFAULT '' COMMENT 'outer_id 的md5值  用来建索引',
    `feedid` BIGINT(20) NOT NULL COMMENT '属于哪个feed，feed平台导入商品时用',
    `ucid` BIGINT(2) NOT NULL DEFAULT '0' COMMENT '商家在百度内部的统一ID，和merchantid的区别是，merchantid是产品内部自己定义的',
    `title` VARCHAR(1024) NOT NULL COMMENT '标题（name字段去除，和title一样的东西）',
    `subtitle` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '副标题',
    `url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(web)',
    `mobileurl` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(移动端)',
    `outerid` VARCHAR(128) NOT NULL COMMENT '商户系统中的商品id',
    `category_ori` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '原网站面包屑（和微购schema不同，这边没有拆开分多个字段）',
    `brand_ori` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '原始网站品牌（归一化后品牌从attribute表找）',
    `starttime` DATETIME NOT NULL COMMENT '开始时间',
    `endtime` DATETIME NOT NULL COMMENT '结束时间',
    `merchant_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '商品状态：0 ONLINE(上架),1 OFFLINE（下架）',
    `manual_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '运营维护状态（0 Normal，1 Pause，2 uncheck，3 check）',
    `signature` VARCHAR(256) NOT NULL COMMENT '商品签名（8张相关表签名字段：除date,输入的绝大多数信息，用于重复判断）',
    `isselfopen` TINYINT(4) NOT NULL COMMENT '是否自营（1：自营，0：非自营）',
    `dataversion` BIGINT(20) NOT NULL COMMENT '数据版本（防止老的覆盖旧的）',
    `inactivetime` DATETIME NOT NULL COMMENT '失效时间（先保留，后续再看逻辑）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    `deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0：正常;1：已删除，会移出表',
    `seller_name` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '商家名称',
    `upc` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '条形码',
    PRIMARY KEY (`sku_innerid`),
    KEY `sku_hashkey` (`sku_hashkey`),
    KEY `skuid` (`skuid`),
    KEY `unique_key` (`unique_key`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品信息表';

DROP TABLE IF EXISTS `sku_pps_9`;
CREATE TABLE `sku_pps_9`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',    
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，用于表关联',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `regionid` INT(11) NOT NULL COMMENT '地址id，可以为仓库id，目前采用国标id，全国为0',
    `terminal` TINYINT(4) NOT NULL COMMENT '0:MOBILE,1:WEB(移动端还是web端等)',
    `promotion_info` VARCHAR(1024) NOT NULl DEFAULT '' COMMENT '促销信息',
    `promotion_price_machine` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：使用促销模型算出来的价格',
    `promotion_price_manual` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：人工修改的促销价格',
    `originalprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '原始价格',
    `discountprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '折扣价',
    `stock` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '库存值状态（1:有货，0：无货）',
    `post_pay` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否支持货到付款（默认不支持）', 
    `m_update_time` DATETIME NOT NULL COMMENT '商家的库存价格更新时间，用于比较时间进行更新',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku价格&库存表';

DROP TABLE IF EXISTS `sku_level_9`;
CREATE TABLE `sku_level_9`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `level` INT(11) NOT NULL DEFAULT '0' COMMENT '确定价格库存更新频率（0更新频率最低）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku更新频率表';

DROP TABLE IF EXISTS `sku_cspu_9`;
CREATE TABLE `sku_cspu_9`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `cspuid` BIGINT(20) NOT NULL COMMENT 'Cspu ID',
    `type` TINYINT(4)  NOT NULL COMMENT 'cspu来源:1:MANUAL_CLASSIFICATION：手工修改，2:MACHINE_CLASSIFICATION：机器学习',
    `confidence` FLOAT(5,2) NOT NULL DEFAULT '0'COMMENT '取值0-100，可信度',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`),
    KEY `cspuid` (`cspuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku到cspu映射';

DROP TABLE IF EXISTS `sku_comment_9`;
CREATE TABLE `sku_comment_9`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `reviewcount` INT(11) NOT NULL COMMENT '评论数',
    `goodcount` INT(11) NOT NULL COMMENT '好评数',
    `medcount` INT(11) NOT NULL COMMENT '中评数',
    `badcount` INT(11) NOT NULL COMMENT '差评数',
    `tags` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT 'json串，评论提取tag',
    `score` DOUBLE NOT NULL COMMENT '平均分',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku评论表';


DROP TABLE IF EXISTS `bcs_sku_image_9`;
CREATE TABLE `bcs_sku_image_9`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
	`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
	`image_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片第三方地址',
	`image_bcs_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片本地地址',
	`status` TINYINT(4) NOT NULL DEFAULT '3' COMMENT '图片下载状态：0未开始，1下载出错，2正在下载，3下载到本地',
	`addtime` DATETIME NOT NULL COMMENT '添加时间',
	`updatetime` DATETIME NOT NULL COMMENT '更新时间',
	`errormessage` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '错误信息',
	`width` INT(11) NOT NULL DEFAULT '0' COMMENT '图片宽度',
	`height` INT(11) NOT NULL DEFAULT '0' COMMENT '图片高度',
	`content_md5` CHAR(32) NOT NULL  DEFAULT '' COMMENT '内容MD5',
	`sequence` INT(11) NOT NULL DEFAULT '1' COMMENT '图片序号',
	`gips_image` VARCHAR(1024) NOT NULL COMMENT '缩略图json串，一堆url和尺寸',
	`type` TINYINT(4) NOT NULL COMMENT '图片类型：主图，俯视图、侧视45度图等',
	PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '图片信息表';
DROP TABLE IF EXISTS `sku_attribute_10`;
CREATE TABLE `sku_attribute_10`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT 'Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
`property_id` VARCHAR(2048) NOT NULL DEFAULT '' COMMENT '属性id:属性值id；(存策略归一化后的id)',
`property_values` VARCHAR(2048) NOT NULl DEFAULT '' COMMENT '属性1：属性值；属性2:属性值 (存商家原始属性对)',
`property_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '对property_id+property_values做hash，用于重复判断',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品基础属性表';

DROP TABLE IF EXISTS `sku_cdt_10`;
CREATE TABLE `sku_cdt_10`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`leafcategoryid` BIGINT(20) NOT NULL COMMENT '叶子类目ID',
`classificationtype` TINYINT(4) NOT NULL COMMENT '类目来源 0：手工修改，1：机器学习，其他状态',
`confidence` FLOAT(5,2) NOT NULL DEFAULT '0' COMMENT '取值0-100，可信度(目前没在用)',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品分类信息表';

DROP TABLE IF EXISTS `sku_description_10`;
CREATE TABLE `sku_description_10`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `sku_desc_ori` LONGBLOB NOT NULL COMMENT '商品描述的压缩信息',
    `pd_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '商品描述的压缩信息',
    `sku_desc` LONGBLOB NOT NULL COMMENT '提取出来的商品描述压缩信息',
    `pd_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0 Undo, 1 doing, 2 done',
    `area` INT(11) NOT NULL DEFAULT '0' COMMENT '总的图片区域',
    `word_count` INT(11) NOT NULL DEFAULT '0' COMMENT '总字数',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品描述详情表';

DROP TABLE IF EXISTS `sku_info_10`;
CREATE TABLE `sku_info_10`(
    `sku_innerid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '商品shard内部id，自增id',
    `sku_hashkey` BIGINT(20) NOT NULL COMMENT 'Hash（skuid）的结果',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id,Merchantid(数字转化为8位的明文)拼接outerid。',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `unique_key` CHAR(32) NOT NULL DEFAULT '' COMMENT 'outer_id 的md5值  用来建索引',
    `feedid` BIGINT(20) NOT NULL COMMENT '属于哪个feed，feed平台导入商品时用',
    `ucid` BIGINT(2) NOT NULL DEFAULT '0' COMMENT '商家在百度内部的统一ID，和merchantid的区别是，merchantid是产品内部自己定义的',
    `title` VARCHAR(1024) NOT NULL COMMENT '标题（name字段去除，和title一样的东西）',
    `subtitle` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '副标题',
    `url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(web)',
    `mobileurl` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(移动端)',
    `outerid` VARCHAR(128) NOT NULL COMMENT '商户系统中的商品id',
    `category_ori` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '原网站面包屑（和微购schema不同，这边没有拆开分多个字段）',
    `brand_ori` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '原始网站品牌（归一化后品牌从attribute表找）',
    `starttime` DATETIME NOT NULL COMMENT '开始时间',
    `endtime` DATETIME NOT NULL COMMENT '结束时间',
    `merchant_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '商品状态：0 ONLINE(上架),1 OFFLINE（下架）',
    `manual_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '运营维护状态（0 Normal，1 Pause，2 uncheck，3 check）',
    `signature` VARCHAR(256) NOT NULL COMMENT '商品签名（8张相关表签名字段：除date,输入的绝大多数信息，用于重复判断）',
    `isselfopen` TINYINT(4) NOT NULL COMMENT '是否自营（1：自营，0：非自营）',
    `dataversion` BIGINT(20) NOT NULL COMMENT '数据版本（防止老的覆盖旧的）',
    `inactivetime` DATETIME NOT NULL COMMENT '失效时间（先保留，后续再看逻辑）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    `deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0：正常;1：已删除，会移出表',
    `seller_name` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '商家名称',
    `upc` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '条形码',
    PRIMARY KEY (`sku_innerid`),
    KEY `sku_hashkey` (`sku_hashkey`),
    KEY `skuid` (`skuid`),
    KEY `unique_key` (`unique_key`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品信息表';

DROP TABLE IF EXISTS `sku_pps_10`;
CREATE TABLE `sku_pps_10`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',    
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，用于表关联',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `regionid` INT(11) NOT NULL COMMENT '地址id，可以为仓库id，目前采用国标id，全国为0',
    `terminal` TINYINT(4) NOT NULL COMMENT '0:MOBILE,1:WEB(移动端还是web端等)',
    `promotion_info` VARCHAR(1024) NOT NULl DEFAULT '' COMMENT '促销信息',
    `promotion_price_machine` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：使用促销模型算出来的价格',
    `promotion_price_manual` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：人工修改的促销价格',
    `originalprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '原始价格',
    `discountprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '折扣价',
    `stock` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '库存值状态（1:有货，0：无货）',
    `post_pay` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否支持货到付款（默认不支持）', 
    `m_update_time` DATETIME NOT NULL COMMENT '商家的库存价格更新时间，用于比较时间进行更新',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku价格&库存表';

DROP TABLE IF EXISTS `sku_level_10`;
CREATE TABLE `sku_level_10`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `level` INT(11) NOT NULL DEFAULT '0' COMMENT '确定价格库存更新频率（0更新频率最低）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku更新频率表';

DROP TABLE IF EXISTS `sku_cspu_10`;
CREATE TABLE `sku_cspu_10`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `cspuid` BIGINT(20) NOT NULL COMMENT 'Cspu ID',
    `type` TINYINT(4)  NOT NULL COMMENT 'cspu来源:1:MANUAL_CLASSIFICATION：手工修改，2:MACHINE_CLASSIFICATION：机器学习',
    `confidence` FLOAT(5,2) NOT NULL DEFAULT '0'COMMENT '取值0-100，可信度',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`),
    KEY `cspuid` (`cspuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku到cspu映射';

DROP TABLE IF EXISTS `sku_comment_10`;
CREATE TABLE `sku_comment_10`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `reviewcount` INT(11) NOT NULL COMMENT '评论数',
    `goodcount` INT(11) NOT NULL COMMENT '好评数',
    `medcount` INT(11) NOT NULL COMMENT '中评数',
    `badcount` INT(11) NOT NULL COMMENT '差评数',
    `tags` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT 'json串，评论提取tag',
    `score` DOUBLE NOT NULL COMMENT '平均分',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku评论表';


DROP TABLE IF EXISTS `bcs_sku_image_10`;
CREATE TABLE `bcs_sku_image_10`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
	`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
	`image_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片第三方地址',
	`image_bcs_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片本地地址',
	`status` TINYINT(4) NOT NULL DEFAULT '3' COMMENT '图片下载状态：0未开始，1下载出错，2正在下载，3下载到本地',
	`addtime` DATETIME NOT NULL COMMENT '添加时间',
	`updatetime` DATETIME NOT NULL COMMENT '更新时间',
	`errormessage` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '错误信息',
	`width` INT(11) NOT NULL DEFAULT '0' COMMENT '图片宽度',
	`height` INT(11) NOT NULL DEFAULT '0' COMMENT '图片高度',
	`content_md5` CHAR(32) NOT NULL  DEFAULT '' COMMENT '内容MD5',
	`sequence` INT(11) NOT NULL DEFAULT '1' COMMENT '图片序号',
	`gips_image` VARCHAR(1024) NOT NULL COMMENT '缩略图json串，一堆url和尺寸',
	`type` TINYINT(4) NOT NULL COMMENT '图片类型：主图，俯视图、侧视45度图等',
	PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '图片信息表';
DROP TABLE IF EXISTS `sku_attribute_11`;
CREATE TABLE `sku_attribute_11`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT 'Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
`property_id` VARCHAR(2048) NOT NULL DEFAULT '' COMMENT '属性id:属性值id；(存策略归一化后的id)',
`property_values` VARCHAR(2048) NOT NULl DEFAULT '' COMMENT '属性1：属性值；属性2:属性值 (存商家原始属性对)',
`property_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '对property_id+property_values做hash，用于重复判断',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品基础属性表';

DROP TABLE IF EXISTS `sku_cdt_11`;
CREATE TABLE `sku_cdt_11`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`leafcategoryid` BIGINT(20) NOT NULL COMMENT '叶子类目ID',
`classificationtype` TINYINT(4) NOT NULL COMMENT '类目来源 0：手工修改，1：机器学习，其他状态',
`confidence` FLOAT(5,2) NOT NULL DEFAULT '0' COMMENT '取值0-100，可信度(目前没在用)',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品分类信息表';

DROP TABLE IF EXISTS `sku_description_11`;
CREATE TABLE `sku_description_11`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `sku_desc_ori` LONGBLOB NOT NULL COMMENT '商品描述的压缩信息',
    `pd_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '商品描述的压缩信息',
    `sku_desc` LONGBLOB NOT NULL COMMENT '提取出来的商品描述压缩信息',
    `pd_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0 Undo, 1 doing, 2 done',
    `area` INT(11) NOT NULL DEFAULT '0' COMMENT '总的图片区域',
    `word_count` INT(11) NOT NULL DEFAULT '0' COMMENT '总字数',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品描述详情表';

DROP TABLE IF EXISTS `sku_info_11`;
CREATE TABLE `sku_info_11`(
    `sku_innerid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '商品shard内部id，自增id',
    `sku_hashkey` BIGINT(20) NOT NULL COMMENT 'Hash（skuid）的结果',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id,Merchantid(数字转化为8位的明文)拼接outerid。',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `unique_key` CHAR(32) NOT NULL DEFAULT '' COMMENT 'outer_id 的md5值  用来建索引',
    `feedid` BIGINT(20) NOT NULL COMMENT '属于哪个feed，feed平台导入商品时用',
    `ucid` BIGINT(2) NOT NULL DEFAULT '0' COMMENT '商家在百度内部的统一ID，和merchantid的区别是，merchantid是产品内部自己定义的',
    `title` VARCHAR(1024) NOT NULL COMMENT '标题（name字段去除，和title一样的东西）',
    `subtitle` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '副标题',
    `url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(web)',
    `mobileurl` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(移动端)',
    `outerid` VARCHAR(128) NOT NULL COMMENT '商户系统中的商品id',
    `category_ori` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '原网站面包屑（和微购schema不同，这边没有拆开分多个字段）',
    `brand_ori` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '原始网站品牌（归一化后品牌从attribute表找）',
    `starttime` DATETIME NOT NULL COMMENT '开始时间',
    `endtime` DATETIME NOT NULL COMMENT '结束时间',
    `merchant_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '商品状态：0 ONLINE(上架),1 OFFLINE（下架）',
    `manual_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '运营维护状态（0 Normal，1 Pause，2 uncheck，3 check）',
    `signature` VARCHAR(256) NOT NULL COMMENT '商品签名（8张相关表签名字段：除date,输入的绝大多数信息，用于重复判断）',
    `isselfopen` TINYINT(4) NOT NULL COMMENT '是否自营（1：自营，0：非自营）',
    `dataversion` BIGINT(20) NOT NULL COMMENT '数据版本（防止老的覆盖旧的）',
    `inactivetime` DATETIME NOT NULL COMMENT '失效时间（先保留，后续再看逻辑）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    `deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0：正常;1：已删除，会移出表',
    `seller_name` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '商家名称',
    `upc` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '条形码',
    PRIMARY KEY (`sku_innerid`),
    KEY `sku_hashkey` (`sku_hashkey`),
    KEY `skuid` (`skuid`),
    KEY `unique_key` (`unique_key`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品信息表';

DROP TABLE IF EXISTS `sku_pps_11`;
CREATE TABLE `sku_pps_11`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',    
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，用于表关联',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `regionid` INT(11) NOT NULL COMMENT '地址id，可以为仓库id，目前采用国标id，全国为0',
    `terminal` TINYINT(4) NOT NULL COMMENT '0:MOBILE,1:WEB(移动端还是web端等)',
    `promotion_info` VARCHAR(1024) NOT NULl DEFAULT '' COMMENT '促销信息',
    `promotion_price_machine` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：使用促销模型算出来的价格',
    `promotion_price_manual` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：人工修改的促销价格',
    `originalprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '原始价格',
    `discountprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '折扣价',
    `stock` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '库存值状态（1:有货，0：无货）',
    `post_pay` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否支持货到付款（默认不支持）', 
    `m_update_time` DATETIME NOT NULL COMMENT '商家的库存价格更新时间，用于比较时间进行更新',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku价格&库存表';

DROP TABLE IF EXISTS `sku_level_11`;
CREATE TABLE `sku_level_11`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `level` INT(11) NOT NULL DEFAULT '0' COMMENT '确定价格库存更新频率（0更新频率最低）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku更新频率表';

DROP TABLE IF EXISTS `sku_cspu_11`;
CREATE TABLE `sku_cspu_11`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `cspuid` BIGINT(20) NOT NULL COMMENT 'Cspu ID',
    `type` TINYINT(4)  NOT NULL COMMENT 'cspu来源:1:MANUAL_CLASSIFICATION：手工修改，2:MACHINE_CLASSIFICATION：机器学习',
    `confidence` FLOAT(5,2) NOT NULL DEFAULT '0'COMMENT '取值0-100，可信度',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`),
    KEY `cspuid` (`cspuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku到cspu映射';

DROP TABLE IF EXISTS `sku_comment_11`;
CREATE TABLE `sku_comment_11`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `reviewcount` INT(11) NOT NULL COMMENT '评论数',
    `goodcount` INT(11) NOT NULL COMMENT '好评数',
    `medcount` INT(11) NOT NULL COMMENT '中评数',
    `badcount` INT(11) NOT NULL COMMENT '差评数',
    `tags` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT 'json串，评论提取tag',
    `score` DOUBLE NOT NULL COMMENT '平均分',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku评论表';


DROP TABLE IF EXISTS `bcs_sku_image_11`;
CREATE TABLE `bcs_sku_image_11`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
	`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
	`image_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片第三方地址',
	`image_bcs_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片本地地址',
	`status` TINYINT(4) NOT NULL DEFAULT '3' COMMENT '图片下载状态：0未开始，1下载出错，2正在下载，3下载到本地',
	`addtime` DATETIME NOT NULL COMMENT '添加时间',
	`updatetime` DATETIME NOT NULL COMMENT '更新时间',
	`errormessage` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '错误信息',
	`width` INT(11) NOT NULL DEFAULT '0' COMMENT '图片宽度',
	`height` INT(11) NOT NULL DEFAULT '0' COMMENT '图片高度',
	`content_md5` CHAR(32) NOT NULL  DEFAULT '' COMMENT '内容MD5',
	`sequence` INT(11) NOT NULL DEFAULT '1' COMMENT '图片序号',
	`gips_image` VARCHAR(1024) NOT NULL COMMENT '缩略图json串，一堆url和尺寸',
	`type` TINYINT(4) NOT NULL COMMENT '图片类型：主图，俯视图、侧视45度图等',
	PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '图片信息表';
DROP TABLE IF EXISTS `sku_attribute_12`;
CREATE TABLE `sku_attribute_12`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT 'Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
`property_id` VARCHAR(2048) NOT NULL DEFAULT '' COMMENT '属性id:属性值id；(存策略归一化后的id)',
`property_values` VARCHAR(2048) NOT NULl DEFAULT '' COMMENT '属性1：属性值；属性2:属性值 (存商家原始属性对)',
`property_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '对property_id+property_values做hash，用于重复判断',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品基础属性表';

DROP TABLE IF EXISTS `sku_cdt_12`;
CREATE TABLE `sku_cdt_12`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`leafcategoryid` BIGINT(20) NOT NULL COMMENT '叶子类目ID',
`classificationtype` TINYINT(4) NOT NULL COMMENT '类目来源 0：手工修改，1：机器学习，其他状态',
`confidence` FLOAT(5,2) NOT NULL DEFAULT '0' COMMENT '取值0-100，可信度(目前没在用)',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品分类信息表';

DROP TABLE IF EXISTS `sku_description_12`;
CREATE TABLE `sku_description_12`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `sku_desc_ori` LONGBLOB NOT NULL COMMENT '商品描述的压缩信息',
    `pd_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '商品描述的压缩信息',
    `sku_desc` LONGBLOB NOT NULL COMMENT '提取出来的商品描述压缩信息',
    `pd_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0 Undo, 1 doing, 2 done',
    `area` INT(11) NOT NULL DEFAULT '0' COMMENT '总的图片区域',
    `word_count` INT(11) NOT NULL DEFAULT '0' COMMENT '总字数',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品描述详情表';

DROP TABLE IF EXISTS `sku_info_12`;
CREATE TABLE `sku_info_12`(
    `sku_innerid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '商品shard内部id，自增id',
    `sku_hashkey` BIGINT(20) NOT NULL COMMENT 'Hash（skuid）的结果',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id,Merchantid(数字转化为8位的明文)拼接outerid。',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `unique_key` CHAR(32) NOT NULL DEFAULT '' COMMENT 'outer_id 的md5值  用来建索引',
    `feedid` BIGINT(20) NOT NULL COMMENT '属于哪个feed，feed平台导入商品时用',
    `ucid` BIGINT(2) NOT NULL DEFAULT '0' COMMENT '商家在百度内部的统一ID，和merchantid的区别是，merchantid是产品内部自己定义的',
    `title` VARCHAR(1024) NOT NULL COMMENT '标题（name字段去除，和title一样的东西）',
    `subtitle` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '副标题',
    `url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(web)',
    `mobileurl` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(移动端)',
    `outerid` VARCHAR(128) NOT NULL COMMENT '商户系统中的商品id',
    `category_ori` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '原网站面包屑（和微购schema不同，这边没有拆开分多个字段）',
    `brand_ori` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '原始网站品牌（归一化后品牌从attribute表找）',
    `starttime` DATETIME NOT NULL COMMENT '开始时间',
    `endtime` DATETIME NOT NULL COMMENT '结束时间',
    `merchant_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '商品状态：0 ONLINE(上架),1 OFFLINE（下架）',
    `manual_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '运营维护状态（0 Normal，1 Pause，2 uncheck，3 check）',
    `signature` VARCHAR(256) NOT NULL COMMENT '商品签名（8张相关表签名字段：除date,输入的绝大多数信息，用于重复判断）',
    `isselfopen` TINYINT(4) NOT NULL COMMENT '是否自营（1：自营，0：非自营）',
    `dataversion` BIGINT(20) NOT NULL COMMENT '数据版本（防止老的覆盖旧的）',
    `inactivetime` DATETIME NOT NULL COMMENT '失效时间（先保留，后续再看逻辑）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    `deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0：正常;1：已删除，会移出表',
    `seller_name` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '商家名称',
    `upc` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '条形码',
    PRIMARY KEY (`sku_innerid`),
    KEY `sku_hashkey` (`sku_hashkey`),
    KEY `skuid` (`skuid`),
    KEY `unique_key` (`unique_key`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品信息表';

DROP TABLE IF EXISTS `sku_pps_12`;
CREATE TABLE `sku_pps_12`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',    
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，用于表关联',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `regionid` INT(11) NOT NULL COMMENT '地址id，可以为仓库id，目前采用国标id，全国为0',
    `terminal` TINYINT(4) NOT NULL COMMENT '0:MOBILE,1:WEB(移动端还是web端等)',
    `promotion_info` VARCHAR(1024) NOT NULl DEFAULT '' COMMENT '促销信息',
    `promotion_price_machine` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：使用促销模型算出来的价格',
    `promotion_price_manual` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：人工修改的促销价格',
    `originalprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '原始价格',
    `discountprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '折扣价',
    `stock` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '库存值状态（1:有货，0：无货）',
    `post_pay` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否支持货到付款（默认不支持）', 
    `m_update_time` DATETIME NOT NULL COMMENT '商家的库存价格更新时间，用于比较时间进行更新',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku价格&库存表';

DROP TABLE IF EXISTS `sku_level_12`;
CREATE TABLE `sku_level_12`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `level` INT(11) NOT NULL DEFAULT '0' COMMENT '确定价格库存更新频率（0更新频率最低）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku更新频率表';

DROP TABLE IF EXISTS `sku_cspu_12`;
CREATE TABLE `sku_cspu_12`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `cspuid` BIGINT(20) NOT NULL COMMENT 'Cspu ID',
    `type` TINYINT(4)  NOT NULL COMMENT 'cspu来源:1:MANUAL_CLASSIFICATION：手工修改，2:MACHINE_CLASSIFICATION：机器学习',
    `confidence` FLOAT(5,2) NOT NULL DEFAULT '0'COMMENT '取值0-100，可信度',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`),
    KEY `cspuid` (`cspuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku到cspu映射';

DROP TABLE IF EXISTS `sku_comment_12`;
CREATE TABLE `sku_comment_12`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `reviewcount` INT(11) NOT NULL COMMENT '评论数',
    `goodcount` INT(11) NOT NULL COMMENT '好评数',
    `medcount` INT(11) NOT NULL COMMENT '中评数',
    `badcount` INT(11) NOT NULL COMMENT '差评数',
    `tags` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT 'json串，评论提取tag',
    `score` DOUBLE NOT NULL COMMENT '平均分',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku评论表';


DROP TABLE IF EXISTS `bcs_sku_image_12`;
CREATE TABLE `bcs_sku_image_12`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
	`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
	`image_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片第三方地址',
	`image_bcs_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片本地地址',
	`status` TINYINT(4) NOT NULL DEFAULT '3' COMMENT '图片下载状态：0未开始，1下载出错，2正在下载，3下载到本地',
	`addtime` DATETIME NOT NULL COMMENT '添加时间',
	`updatetime` DATETIME NOT NULL COMMENT '更新时间',
	`errormessage` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '错误信息',
	`width` INT(11) NOT NULL DEFAULT '0' COMMENT '图片宽度',
	`height` INT(11) NOT NULL DEFAULT '0' COMMENT '图片高度',
	`content_md5` CHAR(32) NOT NULL  DEFAULT '' COMMENT '内容MD5',
	`sequence` INT(11) NOT NULL DEFAULT '1' COMMENT '图片序号',
	`gips_image` VARCHAR(1024) NOT NULL COMMENT '缩略图json串，一堆url和尺寸',
	`type` TINYINT(4) NOT NULL COMMENT '图片类型：主图，俯视图、侧视45度图等',
	PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '图片信息表';
DROP TABLE IF EXISTS `sku_attribute_13`;
CREATE TABLE `sku_attribute_13`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT 'Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
`property_id` VARCHAR(2048) NOT NULL DEFAULT '' COMMENT '属性id:属性值id；(存策略归一化后的id)',
`property_values` VARCHAR(2048) NOT NULl DEFAULT '' COMMENT '属性1：属性值；属性2:属性值 (存商家原始属性对)',
`property_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '对property_id+property_values做hash，用于重复判断',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品基础属性表';

DROP TABLE IF EXISTS `sku_cdt_13`;
CREATE TABLE `sku_cdt_13`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`leafcategoryid` BIGINT(20) NOT NULL COMMENT '叶子类目ID',
`classificationtype` TINYINT(4) NOT NULL COMMENT '类目来源 0：手工修改，1：机器学习，其他状态',
`confidence` FLOAT(5,2) NOT NULL DEFAULT '0' COMMENT '取值0-100，可信度(目前没在用)',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品分类信息表';

DROP TABLE IF EXISTS `sku_description_13`;
CREATE TABLE `sku_description_13`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `sku_desc_ori` LONGBLOB NOT NULL COMMENT '商品描述的压缩信息',
    `pd_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '商品描述的压缩信息',
    `sku_desc` LONGBLOB NOT NULL COMMENT '提取出来的商品描述压缩信息',
    `pd_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0 Undo, 1 doing, 2 done',
    `area` INT(11) NOT NULL DEFAULT '0' COMMENT '总的图片区域',
    `word_count` INT(11) NOT NULL DEFAULT '0' COMMENT '总字数',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品描述详情表';

DROP TABLE IF EXISTS `sku_info_13`;
CREATE TABLE `sku_info_13`(
    `sku_innerid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '商品shard内部id，自增id',
    `sku_hashkey` BIGINT(20) NOT NULL COMMENT 'Hash（skuid）的结果',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id,Merchantid(数字转化为8位的明文)拼接outerid。',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `unique_key` CHAR(32) NOT NULL DEFAULT '' COMMENT 'outer_id 的md5值  用来建索引',
    `feedid` BIGINT(20) NOT NULL COMMENT '属于哪个feed，feed平台导入商品时用',
    `ucid` BIGINT(2) NOT NULL DEFAULT '0' COMMENT '商家在百度内部的统一ID，和merchantid的区别是，merchantid是产品内部自己定义的',
    `title` VARCHAR(1024) NOT NULL COMMENT '标题（name字段去除，和title一样的东西）',
    `subtitle` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '副标题',
    `url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(web)',
    `mobileurl` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(移动端)',
    `outerid` VARCHAR(128) NOT NULL COMMENT '商户系统中的商品id',
    `category_ori` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '原网站面包屑（和微购schema不同，这边没有拆开分多个字段）',
    `brand_ori` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '原始网站品牌（归一化后品牌从attribute表找）',
    `starttime` DATETIME NOT NULL COMMENT '开始时间',
    `endtime` DATETIME NOT NULL COMMENT '结束时间',
    `merchant_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '商品状态：0 ONLINE(上架),1 OFFLINE（下架）',
    `manual_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '运营维护状态（0 Normal，1 Pause，2 uncheck，3 check）',
    `signature` VARCHAR(256) NOT NULL COMMENT '商品签名（8张相关表签名字段：除date,输入的绝大多数信息，用于重复判断）',
    `isselfopen` TINYINT(4) NOT NULL COMMENT '是否自营（1：自营，0：非自营）',
    `dataversion` BIGINT(20) NOT NULL COMMENT '数据版本（防止老的覆盖旧的）',
    `inactivetime` DATETIME NOT NULL COMMENT '失效时间（先保留，后续再看逻辑）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    `deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0：正常;1：已删除，会移出表',
    `seller_name` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '商家名称',
    `upc` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '条形码',
    PRIMARY KEY (`sku_innerid`),
    KEY `sku_hashkey` (`sku_hashkey`),
    KEY `skuid` (`skuid`),
    KEY `unique_key` (`unique_key`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品信息表';

DROP TABLE IF EXISTS `sku_pps_13`;
CREATE TABLE `sku_pps_13`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',    
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，用于表关联',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `regionid` INT(11) NOT NULL COMMENT '地址id，可以为仓库id，目前采用国标id，全国为0',
    `terminal` TINYINT(4) NOT NULL COMMENT '0:MOBILE,1:WEB(移动端还是web端等)',
    `promotion_info` VARCHAR(1024) NOT NULl DEFAULT '' COMMENT '促销信息',
    `promotion_price_machine` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：使用促销模型算出来的价格',
    `promotion_price_manual` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：人工修改的促销价格',
    `originalprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '原始价格',
    `discountprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '折扣价',
    `stock` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '库存值状态（1:有货，0：无货）',
    `post_pay` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否支持货到付款（默认不支持）', 
    `m_update_time` DATETIME NOT NULL COMMENT '商家的库存价格更新时间，用于比较时间进行更新',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku价格&库存表';

DROP TABLE IF EXISTS `sku_level_13`;
CREATE TABLE `sku_level_13`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `level` INT(11) NOT NULL DEFAULT '0' COMMENT '确定价格库存更新频率（0更新频率最低）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku更新频率表';

DROP TABLE IF EXISTS `sku_cspu_13`;
CREATE TABLE `sku_cspu_13`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `cspuid` BIGINT(20) NOT NULL COMMENT 'Cspu ID',
    `type` TINYINT(4)  NOT NULL COMMENT 'cspu来源:1:MANUAL_CLASSIFICATION：手工修改，2:MACHINE_CLASSIFICATION：机器学习',
    `confidence` FLOAT(5,2) NOT NULL DEFAULT '0'COMMENT '取值0-100，可信度',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`),
    KEY `cspuid` (`cspuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku到cspu映射';

DROP TABLE IF EXISTS `sku_comment_13`;
CREATE TABLE `sku_comment_13`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `reviewcount` INT(11) NOT NULL COMMENT '评论数',
    `goodcount` INT(11) NOT NULL COMMENT '好评数',
    `medcount` INT(11) NOT NULL COMMENT '中评数',
    `badcount` INT(11) NOT NULL COMMENT '差评数',
    `tags` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT 'json串，评论提取tag',
    `score` DOUBLE NOT NULL COMMENT '平均分',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku评论表';


DROP TABLE IF EXISTS `bcs_sku_image_13`;
CREATE TABLE `bcs_sku_image_13`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
	`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
	`image_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片第三方地址',
	`image_bcs_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片本地地址',
	`status` TINYINT(4) NOT NULL DEFAULT '3' COMMENT '图片下载状态：0未开始，1下载出错，2正在下载，3下载到本地',
	`addtime` DATETIME NOT NULL COMMENT '添加时间',
	`updatetime` DATETIME NOT NULL COMMENT '更新时间',
	`errormessage` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '错误信息',
	`width` INT(11) NOT NULL DEFAULT '0' COMMENT '图片宽度',
	`height` INT(11) NOT NULL DEFAULT '0' COMMENT '图片高度',
	`content_md5` CHAR(32) NOT NULL  DEFAULT '' COMMENT '内容MD5',
	`sequence` INT(11) NOT NULL DEFAULT '1' COMMENT '图片序号',
	`gips_image` VARCHAR(1024) NOT NULL COMMENT '缩略图json串，一堆url和尺寸',
	`type` TINYINT(4) NOT NULL COMMENT '图片类型：主图，俯视图、侧视45度图等',
	PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '图片信息表';
DROP TABLE IF EXISTS `sku_attribute_14`;
CREATE TABLE `sku_attribute_14`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT 'Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
`property_id` VARCHAR(2048) NOT NULL DEFAULT '' COMMENT '属性id:属性值id；(存策略归一化后的id)',
`property_values` VARCHAR(2048) NOT NULl DEFAULT '' COMMENT '属性1：属性值；属性2:属性值 (存商家原始属性对)',
`property_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '对property_id+property_values做hash，用于重复判断',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品基础属性表';

DROP TABLE IF EXISTS `sku_cdt_14`;
CREATE TABLE `sku_cdt_14`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`leafcategoryid` BIGINT(20) NOT NULL COMMENT '叶子类目ID',
`classificationtype` TINYINT(4) NOT NULL COMMENT '类目来源 0：手工修改，1：机器学习，其他状态',
`confidence` FLOAT(5,2) NOT NULL DEFAULT '0' COMMENT '取值0-100，可信度(目前没在用)',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品分类信息表';

DROP TABLE IF EXISTS `sku_description_14`;
CREATE TABLE `sku_description_14`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `sku_desc_ori` LONGBLOB NOT NULL COMMENT '商品描述的压缩信息',
    `pd_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '商品描述的压缩信息',
    `sku_desc` LONGBLOB NOT NULL COMMENT '提取出来的商品描述压缩信息',
    `pd_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0 Undo, 1 doing, 2 done',
    `area` INT(11) NOT NULL DEFAULT '0' COMMENT '总的图片区域',
    `word_count` INT(11) NOT NULL DEFAULT '0' COMMENT '总字数',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品描述详情表';

DROP TABLE IF EXISTS `sku_info_14`;
CREATE TABLE `sku_info_14`(
    `sku_innerid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '商品shard内部id，自增id',
    `sku_hashkey` BIGINT(20) NOT NULL COMMENT 'Hash（skuid）的结果',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id,Merchantid(数字转化为8位的明文)拼接outerid。',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `unique_key` CHAR(32) NOT NULL DEFAULT '' COMMENT 'outer_id 的md5值  用来建索引',
    `feedid` BIGINT(20) NOT NULL COMMENT '属于哪个feed，feed平台导入商品时用',
    `ucid` BIGINT(2) NOT NULL DEFAULT '0' COMMENT '商家在百度内部的统一ID，和merchantid的区别是，merchantid是产品内部自己定义的',
    `title` VARCHAR(1024) NOT NULL COMMENT '标题（name字段去除，和title一样的东西）',
    `subtitle` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '副标题',
    `url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(web)',
    `mobileurl` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(移动端)',
    `outerid` VARCHAR(128) NOT NULL COMMENT '商户系统中的商品id',
    `category_ori` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '原网站面包屑（和微购schema不同，这边没有拆开分多个字段）',
    `brand_ori` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '原始网站品牌（归一化后品牌从attribute表找）',
    `starttime` DATETIME NOT NULL COMMENT '开始时间',
    `endtime` DATETIME NOT NULL COMMENT '结束时间',
    `merchant_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '商品状态：0 ONLINE(上架),1 OFFLINE（下架）',
    `manual_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '运营维护状态（0 Normal，1 Pause，2 uncheck，3 check）',
    `signature` VARCHAR(256) NOT NULL COMMENT '商品签名（8张相关表签名字段：除date,输入的绝大多数信息，用于重复判断）',
    `isselfopen` TINYINT(4) NOT NULL COMMENT '是否自营（1：自营，0：非自营）',
    `dataversion` BIGINT(20) NOT NULL COMMENT '数据版本（防止老的覆盖旧的）',
    `inactivetime` DATETIME NOT NULL COMMENT '失效时间（先保留，后续再看逻辑）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    `deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0：正常;1：已删除，会移出表',
    `seller_name` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '商家名称',
    `upc` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '条形码',
    PRIMARY KEY (`sku_innerid`),
    KEY `sku_hashkey` (`sku_hashkey`),
    KEY `skuid` (`skuid`),
    KEY `unique_key` (`unique_key`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品信息表';

DROP TABLE IF EXISTS `sku_pps_14`;
CREATE TABLE `sku_pps_14`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',    
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，用于表关联',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `regionid` INT(11) NOT NULL COMMENT '地址id，可以为仓库id，目前采用国标id，全国为0',
    `terminal` TINYINT(4) NOT NULL COMMENT '0:MOBILE,1:WEB(移动端还是web端等)',
    `promotion_info` VARCHAR(1024) NOT NULl DEFAULT '' COMMENT '促销信息',
    `promotion_price_machine` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：使用促销模型算出来的价格',
    `promotion_price_manual` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：人工修改的促销价格',
    `originalprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '原始价格',
    `discountprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '折扣价',
    `stock` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '库存值状态（1:有货，0：无货）',
    `post_pay` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否支持货到付款（默认不支持）', 
    `m_update_time` DATETIME NOT NULL COMMENT '商家的库存价格更新时间，用于比较时间进行更新',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku价格&库存表';

DROP TABLE IF EXISTS `sku_level_14`;
CREATE TABLE `sku_level_14`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `level` INT(11) NOT NULL DEFAULT '0' COMMENT '确定价格库存更新频率（0更新频率最低）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku更新频率表';

DROP TABLE IF EXISTS `sku_cspu_14`;
CREATE TABLE `sku_cspu_14`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `cspuid` BIGINT(20) NOT NULL COMMENT 'Cspu ID',
    `type` TINYINT(4)  NOT NULL COMMENT 'cspu来源:1:MANUAL_CLASSIFICATION：手工修改，2:MACHINE_CLASSIFICATION：机器学习',
    `confidence` FLOAT(5,2) NOT NULL DEFAULT '0'COMMENT '取值0-100，可信度',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`),
    KEY `cspuid` (`cspuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku到cspu映射';

DROP TABLE IF EXISTS `sku_comment_14`;
CREATE TABLE `sku_comment_14`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `reviewcount` INT(11) NOT NULL COMMENT '评论数',
    `goodcount` INT(11) NOT NULL COMMENT '好评数',
    `medcount` INT(11) NOT NULL COMMENT '中评数',
    `badcount` INT(11) NOT NULL COMMENT '差评数',
    `tags` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT 'json串，评论提取tag',
    `score` DOUBLE NOT NULL COMMENT '平均分',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku评论表';


DROP TABLE IF EXISTS `bcs_sku_image_14`;
CREATE TABLE `bcs_sku_image_14`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
	`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
	`image_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片第三方地址',
	`image_bcs_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片本地地址',
	`status` TINYINT(4) NOT NULL DEFAULT '3' COMMENT '图片下载状态：0未开始，1下载出错，2正在下载，3下载到本地',
	`addtime` DATETIME NOT NULL COMMENT '添加时间',
	`updatetime` DATETIME NOT NULL COMMENT '更新时间',
	`errormessage` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '错误信息',
	`width` INT(11) NOT NULL DEFAULT '0' COMMENT '图片宽度',
	`height` INT(11) NOT NULL DEFAULT '0' COMMENT '图片高度',
	`content_md5` CHAR(32) NOT NULL  DEFAULT '' COMMENT '内容MD5',
	`sequence` INT(11) NOT NULL DEFAULT '1' COMMENT '图片序号',
	`gips_image` VARCHAR(1024) NOT NULL COMMENT '缩略图json串，一堆url和尺寸',
	`type` TINYINT(4) NOT NULL COMMENT '图片类型：主图，俯视图、侧视45度图等',
	PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '图片信息表';
DROP TABLE IF EXISTS `sku_attribute_15`;
CREATE TABLE `sku_attribute_15`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT 'Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
`property_id` VARCHAR(2048) NOT NULL DEFAULT '' COMMENT '属性id:属性值id；(存策略归一化后的id)',
`property_values` VARCHAR(2048) NOT NULl DEFAULT '' COMMENT '属性1：属性值；属性2:属性值 (存商家原始属性对)',
`property_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '对property_id+property_values做hash，用于重复判断',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品基础属性表';

DROP TABLE IF EXISTS `sku_cdt_15`;
CREATE TABLE `sku_cdt_15`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`leafcategoryid` BIGINT(20) NOT NULL COMMENT '叶子类目ID',
`classificationtype` TINYINT(4) NOT NULL COMMENT '类目来源 0：手工修改，1：机器学习，其他状态',
`confidence` FLOAT(5,2) NOT NULL DEFAULT '0' COMMENT '取值0-100，可信度(目前没在用)',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品分类信息表';

DROP TABLE IF EXISTS `sku_description_15`;
CREATE TABLE `sku_description_15`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `sku_desc_ori` LONGBLOB NOT NULL COMMENT '商品描述的压缩信息',
    `pd_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '商品描述的压缩信息',
    `sku_desc` LONGBLOB NOT NULL COMMENT '提取出来的商品描述压缩信息',
    `pd_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0 Undo, 1 doing, 2 done',
    `area` INT(11) NOT NULL DEFAULT '0' COMMENT '总的图片区域',
    `word_count` INT(11) NOT NULL DEFAULT '0' COMMENT '总字数',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品描述详情表';

DROP TABLE IF EXISTS `sku_info_15`;
CREATE TABLE `sku_info_15`(
    `sku_innerid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '商品shard内部id，自增id',
    `sku_hashkey` BIGINT(20) NOT NULL COMMENT 'Hash（skuid）的结果',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id,Merchantid(数字转化为8位的明文)拼接outerid。',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `unique_key` CHAR(32) NOT NULL DEFAULT '' COMMENT 'outer_id 的md5值  用来建索引',
    `feedid` BIGINT(20) NOT NULL COMMENT '属于哪个feed，feed平台导入商品时用',
    `ucid` BIGINT(2) NOT NULL DEFAULT '0' COMMENT '商家在百度内部的统一ID，和merchantid的区别是，merchantid是产品内部自己定义的',
    `title` VARCHAR(1024) NOT NULL COMMENT '标题（name字段去除，和title一样的东西）',
    `subtitle` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '副标题',
    `url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(web)',
    `mobileurl` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(移动端)',
    `outerid` VARCHAR(128) NOT NULL COMMENT '商户系统中的商品id',
    `category_ori` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '原网站面包屑（和微购schema不同，这边没有拆开分多个字段）',
    `brand_ori` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '原始网站品牌（归一化后品牌从attribute表找）',
    `starttime` DATETIME NOT NULL COMMENT '开始时间',
    `endtime` DATETIME NOT NULL COMMENT '结束时间',
    `merchant_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '商品状态：0 ONLINE(上架),1 OFFLINE（下架）',
    `manual_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '运营维护状态（0 Normal，1 Pause，2 uncheck，3 check）',
    `signature` VARCHAR(256) NOT NULL COMMENT '商品签名（8张相关表签名字段：除date,输入的绝大多数信息，用于重复判断）',
    `isselfopen` TINYINT(4) NOT NULL COMMENT '是否自营（1：自营，0：非自营）',
    `dataversion` BIGINT(20) NOT NULL COMMENT '数据版本（防止老的覆盖旧的）',
    `inactivetime` DATETIME NOT NULL COMMENT '失效时间（先保留，后续再看逻辑）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    `deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0：正常;1：已删除，会移出表',
    `seller_name` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '商家名称',
    `upc` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '条形码',
    PRIMARY KEY (`sku_innerid`),
    KEY `sku_hashkey` (`sku_hashkey`),
    KEY `skuid` (`skuid`),
    KEY `unique_key` (`unique_key`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品信息表';

DROP TABLE IF EXISTS `sku_pps_15`;
CREATE TABLE `sku_pps_15`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',    
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，用于表关联',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `regionid` INT(11) NOT NULL COMMENT '地址id，可以为仓库id，目前采用国标id，全国为0',
    `terminal` TINYINT(4) NOT NULL COMMENT '0:MOBILE,1:WEB(移动端还是web端等)',
    `promotion_info` VARCHAR(1024) NOT NULl DEFAULT '' COMMENT '促销信息',
    `promotion_price_machine` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：使用促销模型算出来的价格',
    `promotion_price_manual` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：人工修改的促销价格',
    `originalprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '原始价格',
    `discountprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '折扣价',
    `stock` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '库存值状态（1:有货，0：无货）',
    `post_pay` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否支持货到付款（默认不支持）', 
    `m_update_time` DATETIME NOT NULL COMMENT '商家的库存价格更新时间，用于比较时间进行更新',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku价格&库存表';

DROP TABLE IF EXISTS `sku_level_15`;
CREATE TABLE `sku_level_15`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `level` INT(11) NOT NULL DEFAULT '0' COMMENT '确定价格库存更新频率（0更新频率最低）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku更新频率表';

DROP TABLE IF EXISTS `sku_cspu_15`;
CREATE TABLE `sku_cspu_15`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `cspuid` BIGINT(20) NOT NULL COMMENT 'Cspu ID',
    `type` TINYINT(4)  NOT NULL COMMENT 'cspu来源:1:MANUAL_CLASSIFICATION：手工修改，2:MACHINE_CLASSIFICATION：机器学习',
    `confidence` FLOAT(5,2) NOT NULL DEFAULT '0'COMMENT '取值0-100，可信度',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`),
    KEY `cspuid` (`cspuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku到cspu映射';

DROP TABLE IF EXISTS `sku_comment_15`;
CREATE TABLE `sku_comment_15`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `reviewcount` INT(11) NOT NULL COMMENT '评论数',
    `goodcount` INT(11) NOT NULL COMMENT '好评数',
    `medcount` INT(11) NOT NULL COMMENT '中评数',
    `badcount` INT(11) NOT NULL COMMENT '差评数',
    `tags` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT 'json串，评论提取tag',
    `score` DOUBLE NOT NULL COMMENT '平均分',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku评论表';


DROP TABLE IF EXISTS `bcs_sku_image_15`;
CREATE TABLE `bcs_sku_image_15`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
	`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
	`image_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片第三方地址',
	`image_bcs_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片本地地址',
	`status` TINYINT(4) NOT NULL DEFAULT '3' COMMENT '图片下载状态：0未开始，1下载出错，2正在下载，3下载到本地',
	`addtime` DATETIME NOT NULL COMMENT '添加时间',
	`updatetime` DATETIME NOT NULL COMMENT '更新时间',
	`errormessage` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '错误信息',
	`width` INT(11) NOT NULL DEFAULT '0' COMMENT '图片宽度',
	`height` INT(11) NOT NULL DEFAULT '0' COMMENT '图片高度',
	`content_md5` CHAR(32) NOT NULL  DEFAULT '' COMMENT '内容MD5',
	`sequence` INT(11) NOT NULL DEFAULT '1' COMMENT '图片序号',
	`gips_image` VARCHAR(1024) NOT NULL COMMENT '缩略图json串，一堆url和尺寸',
	`type` TINYINT(4) NOT NULL COMMENT '图片类型：主图，俯视图、侧视45度图等',
	PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '图片信息表';
DROP TABLE IF EXISTS `sku_attribute_16`;
CREATE TABLE `sku_attribute_16`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT 'Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
`property_id` VARCHAR(2048) NOT NULL DEFAULT '' COMMENT '属性id:属性值id；(存策略归一化后的id)',
`property_values` VARCHAR(2048) NOT NULl DEFAULT '' COMMENT '属性1：属性值；属性2:属性值 (存商家原始属性对)',
`property_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '对property_id+property_values做hash，用于重复判断',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品基础属性表';

DROP TABLE IF EXISTS `sku_cdt_16`;
CREATE TABLE `sku_cdt_16`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`leafcategoryid` BIGINT(20) NOT NULL COMMENT '叶子类目ID',
`classificationtype` TINYINT(4) NOT NULL COMMENT '类目来源 0：手工修改，1：机器学习，其他状态',
`confidence` FLOAT(5,2) NOT NULL DEFAULT '0' COMMENT '取值0-100，可信度(目前没在用)',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品分类信息表';

DROP TABLE IF EXISTS `sku_description_16`;
CREATE TABLE `sku_description_16`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `sku_desc_ori` LONGBLOB NOT NULL COMMENT '商品描述的压缩信息',
    `pd_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '商品描述的压缩信息',
    `sku_desc` LONGBLOB NOT NULL COMMENT '提取出来的商品描述压缩信息',
    `pd_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0 Undo, 1 doing, 2 done',
    `area` INT(11) NOT NULL DEFAULT '0' COMMENT '总的图片区域',
    `word_count` INT(11) NOT NULL DEFAULT '0' COMMENT '总字数',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品描述详情表';

DROP TABLE IF EXISTS `sku_info_16`;
CREATE TABLE `sku_info_16`(
    `sku_innerid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '商品shard内部id，自增id',
    `sku_hashkey` BIGINT(20) NOT NULL COMMENT 'Hash（skuid）的结果',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id,Merchantid(数字转化为8位的明文)拼接outerid。',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `unique_key` CHAR(32) NOT NULL DEFAULT '' COMMENT 'outer_id 的md5值  用来建索引',
    `feedid` BIGINT(20) NOT NULL COMMENT '属于哪个feed，feed平台导入商品时用',
    `ucid` BIGINT(2) NOT NULL DEFAULT '0' COMMENT '商家在百度内部的统一ID，和merchantid的区别是，merchantid是产品内部自己定义的',
    `title` VARCHAR(1024) NOT NULL COMMENT '标题（name字段去除，和title一样的东西）',
    `subtitle` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '副标题',
    `url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(web)',
    `mobileurl` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(移动端)',
    `outerid` VARCHAR(128) NOT NULL COMMENT '商户系统中的商品id',
    `category_ori` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '原网站面包屑（和微购schema不同，这边没有拆开分多个字段）',
    `brand_ori` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '原始网站品牌（归一化后品牌从attribute表找）',
    `starttime` DATETIME NOT NULL COMMENT '开始时间',
    `endtime` DATETIME NOT NULL COMMENT '结束时间',
    `merchant_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '商品状态：0 ONLINE(上架),1 OFFLINE（下架）',
    `manual_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '运营维护状态（0 Normal，1 Pause，2 uncheck，3 check）',
    `signature` VARCHAR(256) NOT NULL COMMENT '商品签名（8张相关表签名字段：除date,输入的绝大多数信息，用于重复判断）',
    `isselfopen` TINYINT(4) NOT NULL COMMENT '是否自营（1：自营，0：非自营）',
    `dataversion` BIGINT(20) NOT NULL COMMENT '数据版本（防止老的覆盖旧的）',
    `inactivetime` DATETIME NOT NULL COMMENT '失效时间（先保留，后续再看逻辑）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    `deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0：正常;1：已删除，会移出表',
    `seller_name` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '商家名称',
    `upc` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '条形码',
    PRIMARY KEY (`sku_innerid`),
    KEY `sku_hashkey` (`sku_hashkey`),
    KEY `skuid` (`skuid`),
    KEY `unique_key` (`unique_key`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品信息表';

DROP TABLE IF EXISTS `sku_pps_16`;
CREATE TABLE `sku_pps_16`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',    
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，用于表关联',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `regionid` INT(11) NOT NULL COMMENT '地址id，可以为仓库id，目前采用国标id，全国为0',
    `terminal` TINYINT(4) NOT NULL COMMENT '0:MOBILE,1:WEB(移动端还是web端等)',
    `promotion_info` VARCHAR(1024) NOT NULl DEFAULT '' COMMENT '促销信息',
    `promotion_price_machine` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：使用促销模型算出来的价格',
    `promotion_price_manual` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：人工修改的促销价格',
    `originalprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '原始价格',
    `discountprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '折扣价',
    `stock` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '库存值状态（1:有货，0：无货）',
    `post_pay` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否支持货到付款（默认不支持）', 
    `m_update_time` DATETIME NOT NULL COMMENT '商家的库存价格更新时间，用于比较时间进行更新',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku价格&库存表';

DROP TABLE IF EXISTS `sku_level_16`;
CREATE TABLE `sku_level_16`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `level` INT(11) NOT NULL DEFAULT '0' COMMENT '确定价格库存更新频率（0更新频率最低）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku更新频率表';

DROP TABLE IF EXISTS `sku_cspu_16`;
CREATE TABLE `sku_cspu_16`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `cspuid` BIGINT(20) NOT NULL COMMENT 'Cspu ID',
    `type` TINYINT(4)  NOT NULL COMMENT 'cspu来源:1:MANUAL_CLASSIFICATION：手工修改，2:MACHINE_CLASSIFICATION：机器学习',
    `confidence` FLOAT(5,2) NOT NULL DEFAULT '0'COMMENT '取值0-100，可信度',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`),
    KEY `cspuid` (`cspuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku到cspu映射';

DROP TABLE IF EXISTS `sku_comment_16`;
CREATE TABLE `sku_comment_16`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `reviewcount` INT(11) NOT NULL COMMENT '评论数',
    `goodcount` INT(11) NOT NULL COMMENT '好评数',
    `medcount` INT(11) NOT NULL COMMENT '中评数',
    `badcount` INT(11) NOT NULL COMMENT '差评数',
    `tags` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT 'json串，评论提取tag',
    `score` DOUBLE NOT NULL COMMENT '平均分',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku评论表';


DROP TABLE IF EXISTS `bcs_sku_image_16`;
CREATE TABLE `bcs_sku_image_16`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
	`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
	`image_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片第三方地址',
	`image_bcs_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片本地地址',
	`status` TINYINT(4) NOT NULL DEFAULT '3' COMMENT '图片下载状态：0未开始，1下载出错，2正在下载，3下载到本地',
	`addtime` DATETIME NOT NULL COMMENT '添加时间',
	`updatetime` DATETIME NOT NULL COMMENT '更新时间',
	`errormessage` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '错误信息',
	`width` INT(11) NOT NULL DEFAULT '0' COMMENT '图片宽度',
	`height` INT(11) NOT NULL DEFAULT '0' COMMENT '图片高度',
	`content_md5` CHAR(32) NOT NULL  DEFAULT '' COMMENT '内容MD5',
	`sequence` INT(11) NOT NULL DEFAULT '1' COMMENT '图片序号',
	`gips_image` VARCHAR(1024) NOT NULL COMMENT '缩略图json串，一堆url和尺寸',
	`type` TINYINT(4) NOT NULL COMMENT '图片类型：主图，俯视图、侧视45度图等',
	PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '图片信息表';
DROP TABLE IF EXISTS `sku_attribute_17`;
CREATE TABLE `sku_attribute_17`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT 'Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
`property_id` VARCHAR(2048) NOT NULL DEFAULT '' COMMENT '属性id:属性值id；(存策略归一化后的id)',
`property_values` VARCHAR(2048) NOT NULl DEFAULT '' COMMENT '属性1：属性值；属性2:属性值 (存商家原始属性对)',
`property_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '对property_id+property_values做hash，用于重复判断',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品基础属性表';

DROP TABLE IF EXISTS `sku_cdt_17`;
CREATE TABLE `sku_cdt_17`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`leafcategoryid` BIGINT(20) NOT NULL COMMENT '叶子类目ID',
`classificationtype` TINYINT(4) NOT NULL COMMENT '类目来源 0：手工修改，1：机器学习，其他状态',
`confidence` FLOAT(5,2) NOT NULL DEFAULT '0' COMMENT '取值0-100，可信度(目前没在用)',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品分类信息表';

DROP TABLE IF EXISTS `sku_description_17`;
CREATE TABLE `sku_description_17`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `sku_desc_ori` LONGBLOB NOT NULL COMMENT '商品描述的压缩信息',
    `pd_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '商品描述的压缩信息',
    `sku_desc` LONGBLOB NOT NULL COMMENT '提取出来的商品描述压缩信息',
    `pd_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0 Undo, 1 doing, 2 done',
    `area` INT(11) NOT NULL DEFAULT '0' COMMENT '总的图片区域',
    `word_count` INT(11) NOT NULL DEFAULT '0' COMMENT '总字数',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品描述详情表';

DROP TABLE IF EXISTS `sku_info_17`;
CREATE TABLE `sku_info_17`(
    `sku_innerid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '商品shard内部id，自增id',
    `sku_hashkey` BIGINT(20) NOT NULL COMMENT 'Hash（skuid）的结果',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id,Merchantid(数字转化为8位的明文)拼接outerid。',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `unique_key` CHAR(32) NOT NULL DEFAULT '' COMMENT 'outer_id 的md5值  用来建索引',
    `feedid` BIGINT(20) NOT NULL COMMENT '属于哪个feed，feed平台导入商品时用',
    `ucid` BIGINT(2) NOT NULL DEFAULT '0' COMMENT '商家在百度内部的统一ID，和merchantid的区别是，merchantid是产品内部自己定义的',
    `title` VARCHAR(1024) NOT NULL COMMENT '标题（name字段去除，和title一样的东西）',
    `subtitle` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '副标题',
    `url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(web)',
    `mobileurl` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(移动端)',
    `outerid` VARCHAR(128) NOT NULL COMMENT '商户系统中的商品id',
    `category_ori` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '原网站面包屑（和微购schema不同，这边没有拆开分多个字段）',
    `brand_ori` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '原始网站品牌（归一化后品牌从attribute表找）',
    `starttime` DATETIME NOT NULL COMMENT '开始时间',
    `endtime` DATETIME NOT NULL COMMENT '结束时间',
    `merchant_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '商品状态：0 ONLINE(上架),1 OFFLINE（下架）',
    `manual_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '运营维护状态（0 Normal，1 Pause，2 uncheck，3 check）',
    `signature` VARCHAR(256) NOT NULL COMMENT '商品签名（8张相关表签名字段：除date,输入的绝大多数信息，用于重复判断）',
    `isselfopen` TINYINT(4) NOT NULL COMMENT '是否自营（1：自营，0：非自营）',
    `dataversion` BIGINT(20) NOT NULL COMMENT '数据版本（防止老的覆盖旧的）',
    `inactivetime` DATETIME NOT NULL COMMENT '失效时间（先保留，后续再看逻辑）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    `deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0：正常;1：已删除，会移出表',
    `seller_name` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '商家名称',
    `upc` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '条形码',
    PRIMARY KEY (`sku_innerid`),
    KEY `sku_hashkey` (`sku_hashkey`),
    KEY `skuid` (`skuid`),
    KEY `unique_key` (`unique_key`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品信息表';

DROP TABLE IF EXISTS `sku_pps_17`;
CREATE TABLE `sku_pps_17`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',    
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，用于表关联',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `regionid` INT(11) NOT NULL COMMENT '地址id，可以为仓库id，目前采用国标id，全国为0',
    `terminal` TINYINT(4) NOT NULL COMMENT '0:MOBILE,1:WEB(移动端还是web端等)',
    `promotion_info` VARCHAR(1024) NOT NULl DEFAULT '' COMMENT '促销信息',
    `promotion_price_machine` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：使用促销模型算出来的价格',
    `promotion_price_manual` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：人工修改的促销价格',
    `originalprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '原始价格',
    `discountprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '折扣价',
    `stock` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '库存值状态（1:有货，0：无货）',
    `post_pay` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否支持货到付款（默认不支持）', 
    `m_update_time` DATETIME NOT NULL COMMENT '商家的库存价格更新时间，用于比较时间进行更新',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku价格&库存表';

DROP TABLE IF EXISTS `sku_level_17`;
CREATE TABLE `sku_level_17`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `level` INT(11) NOT NULL DEFAULT '0' COMMENT '确定价格库存更新频率（0更新频率最低）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku更新频率表';

DROP TABLE IF EXISTS `sku_cspu_17`;
CREATE TABLE `sku_cspu_17`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `cspuid` BIGINT(20) NOT NULL COMMENT 'Cspu ID',
    `type` TINYINT(4)  NOT NULL COMMENT 'cspu来源:1:MANUAL_CLASSIFICATION：手工修改，2:MACHINE_CLASSIFICATION：机器学习',
    `confidence` FLOAT(5,2) NOT NULL DEFAULT '0'COMMENT '取值0-100，可信度',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`),
    KEY `cspuid` (`cspuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku到cspu映射';

DROP TABLE IF EXISTS `sku_comment_17`;
CREATE TABLE `sku_comment_17`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `reviewcount` INT(11) NOT NULL COMMENT '评论数',
    `goodcount` INT(11) NOT NULL COMMENT '好评数',
    `medcount` INT(11) NOT NULL COMMENT '中评数',
    `badcount` INT(11) NOT NULL COMMENT '差评数',
    `tags` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT 'json串，评论提取tag',
    `score` DOUBLE NOT NULL COMMENT '平均分',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku评论表';


DROP TABLE IF EXISTS `bcs_sku_image_17`;
CREATE TABLE `bcs_sku_image_17`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
	`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
	`image_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片第三方地址',
	`image_bcs_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片本地地址',
	`status` TINYINT(4) NOT NULL DEFAULT '3' COMMENT '图片下载状态：0未开始，1下载出错，2正在下载，3下载到本地',
	`addtime` DATETIME NOT NULL COMMENT '添加时间',
	`updatetime` DATETIME NOT NULL COMMENT '更新时间',
	`errormessage` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '错误信息',
	`width` INT(11) NOT NULL DEFAULT '0' COMMENT '图片宽度',
	`height` INT(11) NOT NULL DEFAULT '0' COMMENT '图片高度',
	`content_md5` CHAR(32) NOT NULL  DEFAULT '' COMMENT '内容MD5',
	`sequence` INT(11) NOT NULL DEFAULT '1' COMMENT '图片序号',
	`gips_image` VARCHAR(1024) NOT NULL COMMENT '缩略图json串，一堆url和尺寸',
	`type` TINYINT(4) NOT NULL COMMENT '图片类型：主图，俯视图、侧视45度图等',
	PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '图片信息表';
DROP TABLE IF EXISTS `sku_attribute_18`;
CREATE TABLE `sku_attribute_18`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT 'Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
`property_id` VARCHAR(2048) NOT NULL DEFAULT '' COMMENT '属性id:属性值id；(存策略归一化后的id)',
`property_values` VARCHAR(2048) NOT NULl DEFAULT '' COMMENT '属性1：属性值；属性2:属性值 (存商家原始属性对)',
`property_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '对property_id+property_values做hash，用于重复判断',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品基础属性表';

DROP TABLE IF EXISTS `sku_cdt_18`;
CREATE TABLE `sku_cdt_18`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`leafcategoryid` BIGINT(20) NOT NULL COMMENT '叶子类目ID',
`classificationtype` TINYINT(4) NOT NULL COMMENT '类目来源 0：手工修改，1：机器学习，其他状态',
`confidence` FLOAT(5,2) NOT NULL DEFAULT '0' COMMENT '取值0-100，可信度(目前没在用)',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品分类信息表';

DROP TABLE IF EXISTS `sku_description_18`;
CREATE TABLE `sku_description_18`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `sku_desc_ori` LONGBLOB NOT NULL COMMENT '商品描述的压缩信息',
    `pd_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '商品描述的压缩信息',
    `sku_desc` LONGBLOB NOT NULL COMMENT '提取出来的商品描述压缩信息',
    `pd_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0 Undo, 1 doing, 2 done',
    `area` INT(11) NOT NULL DEFAULT '0' COMMENT '总的图片区域',
    `word_count` INT(11) NOT NULL DEFAULT '0' COMMENT '总字数',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品描述详情表';

DROP TABLE IF EXISTS `sku_info_18`;
CREATE TABLE `sku_info_18`(
    `sku_innerid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '商品shard内部id，自增id',
    `sku_hashkey` BIGINT(20) NOT NULL COMMENT 'Hash（skuid）的结果',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id,Merchantid(数字转化为8位的明文)拼接outerid。',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `unique_key` CHAR(32) NOT NULL DEFAULT '' COMMENT 'outer_id 的md5值  用来建索引',
    `feedid` BIGINT(20) NOT NULL COMMENT '属于哪个feed，feed平台导入商品时用',
    `ucid` BIGINT(2) NOT NULL DEFAULT '0' COMMENT '商家在百度内部的统一ID，和merchantid的区别是，merchantid是产品内部自己定义的',
    `title` VARCHAR(1024) NOT NULL COMMENT '标题（name字段去除，和title一样的东西）',
    `subtitle` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '副标题',
    `url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(web)',
    `mobileurl` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(移动端)',
    `outerid` VARCHAR(128) NOT NULL COMMENT '商户系统中的商品id',
    `category_ori` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '原网站面包屑（和微购schema不同，这边没有拆开分多个字段）',
    `brand_ori` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '原始网站品牌（归一化后品牌从attribute表找）',
    `starttime` DATETIME NOT NULL COMMENT '开始时间',
    `endtime` DATETIME NOT NULL COMMENT '结束时间',
    `merchant_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '商品状态：0 ONLINE(上架),1 OFFLINE（下架）',
    `manual_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '运营维护状态（0 Normal，1 Pause，2 uncheck，3 check）',
    `signature` VARCHAR(256) NOT NULL COMMENT '商品签名（8张相关表签名字段：除date,输入的绝大多数信息，用于重复判断）',
    `isselfopen` TINYINT(4) NOT NULL COMMENT '是否自营（1：自营，0：非自营）',
    `dataversion` BIGINT(20) NOT NULL COMMENT '数据版本（防止老的覆盖旧的）',
    `inactivetime` DATETIME NOT NULL COMMENT '失效时间（先保留，后续再看逻辑）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    `deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0：正常;1：已删除，会移出表',
    `seller_name` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '商家名称',
    `upc` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '条形码',
    PRIMARY KEY (`sku_innerid`),
    KEY `sku_hashkey` (`sku_hashkey`),
    KEY `skuid` (`skuid`),
    KEY `unique_key` (`unique_key`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品信息表';

DROP TABLE IF EXISTS `sku_pps_18`;
CREATE TABLE `sku_pps_18`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',    
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，用于表关联',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `regionid` INT(11) NOT NULL COMMENT '地址id，可以为仓库id，目前采用国标id，全国为0',
    `terminal` TINYINT(4) NOT NULL COMMENT '0:MOBILE,1:WEB(移动端还是web端等)',
    `promotion_info` VARCHAR(1024) NOT NULl DEFAULT '' COMMENT '促销信息',
    `promotion_price_machine` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：使用促销模型算出来的价格',
    `promotion_price_manual` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：人工修改的促销价格',
    `originalprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '原始价格',
    `discountprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '折扣价',
    `stock` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '库存值状态（1:有货，0：无货）',
    `post_pay` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否支持货到付款（默认不支持）', 
    `m_update_time` DATETIME NOT NULL COMMENT '商家的库存价格更新时间，用于比较时间进行更新',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku价格&库存表';

DROP TABLE IF EXISTS `sku_level_18`;
CREATE TABLE `sku_level_18`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `level` INT(11) NOT NULL DEFAULT '0' COMMENT '确定价格库存更新频率（0更新频率最低）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku更新频率表';

DROP TABLE IF EXISTS `sku_cspu_18`;
CREATE TABLE `sku_cspu_18`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `cspuid` BIGINT(20) NOT NULL COMMENT 'Cspu ID',
    `type` TINYINT(4)  NOT NULL COMMENT 'cspu来源:1:MANUAL_CLASSIFICATION：手工修改，2:MACHINE_CLASSIFICATION：机器学习',
    `confidence` FLOAT(5,2) NOT NULL DEFAULT '0'COMMENT '取值0-100，可信度',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`),
    KEY `cspuid` (`cspuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku到cspu映射';

DROP TABLE IF EXISTS `sku_comment_18`;
CREATE TABLE `sku_comment_18`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `reviewcount` INT(11) NOT NULL COMMENT '评论数',
    `goodcount` INT(11) NOT NULL COMMENT '好评数',
    `medcount` INT(11) NOT NULL COMMENT '中评数',
    `badcount` INT(11) NOT NULL COMMENT '差评数',
    `tags` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT 'json串，评论提取tag',
    `score` DOUBLE NOT NULL COMMENT '平均分',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku评论表';


DROP TABLE IF EXISTS `bcs_sku_image_18`;
CREATE TABLE `bcs_sku_image_18`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
	`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
	`image_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片第三方地址',
	`image_bcs_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片本地地址',
	`status` TINYINT(4) NOT NULL DEFAULT '3' COMMENT '图片下载状态：0未开始，1下载出错，2正在下载，3下载到本地',
	`addtime` DATETIME NOT NULL COMMENT '添加时间',
	`updatetime` DATETIME NOT NULL COMMENT '更新时间',
	`errormessage` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '错误信息',
	`width` INT(11) NOT NULL DEFAULT '0' COMMENT '图片宽度',
	`height` INT(11) NOT NULL DEFAULT '0' COMMENT '图片高度',
	`content_md5` CHAR(32) NOT NULL  DEFAULT '' COMMENT '内容MD5',
	`sequence` INT(11) NOT NULL DEFAULT '1' COMMENT '图片序号',
	`gips_image` VARCHAR(1024) NOT NULL COMMENT '缩略图json串，一堆url和尺寸',
	`type` TINYINT(4) NOT NULL COMMENT '图片类型：主图，俯视图、侧视45度图等',
	PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '图片信息表';
DROP TABLE IF EXISTS `sku_attribute_19`;
CREATE TABLE `sku_attribute_19`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT 'Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
`property_id` VARCHAR(2048) NOT NULL DEFAULT '' COMMENT '属性id:属性值id；(存策略归一化后的id)',
`property_values` VARCHAR(2048) NOT NULl DEFAULT '' COMMENT '属性1：属性值；属性2:属性值 (存商家原始属性对)',
`property_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '对property_id+property_values做hash，用于重复判断',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品基础属性表';

DROP TABLE IF EXISTS `sku_cdt_19`;
CREATE TABLE `sku_cdt_19`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`leafcategoryid` BIGINT(20) NOT NULL COMMENT '叶子类目ID',
`classificationtype` TINYINT(4) NOT NULL COMMENT '类目来源 0：手工修改，1：机器学习，其他状态',
`confidence` FLOAT(5,2) NOT NULL DEFAULT '0' COMMENT '取值0-100，可信度(目前没在用)',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品分类信息表';

DROP TABLE IF EXISTS `sku_description_19`;
CREATE TABLE `sku_description_19`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `sku_desc_ori` LONGBLOB NOT NULL COMMENT '商品描述的压缩信息',
    `pd_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '商品描述的压缩信息',
    `sku_desc` LONGBLOB NOT NULL COMMENT '提取出来的商品描述压缩信息',
    `pd_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0 Undo, 1 doing, 2 done',
    `area` INT(11) NOT NULL DEFAULT '0' COMMENT '总的图片区域',
    `word_count` INT(11) NOT NULL DEFAULT '0' COMMENT '总字数',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品描述详情表';

DROP TABLE IF EXISTS `sku_info_19`;
CREATE TABLE `sku_info_19`(
    `sku_innerid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '商品shard内部id，自增id',
    `sku_hashkey` BIGINT(20) NOT NULL COMMENT 'Hash（skuid）的结果',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id,Merchantid(数字转化为8位的明文)拼接outerid。',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `unique_key` CHAR(32) NOT NULL DEFAULT '' COMMENT 'outer_id 的md5值  用来建索引',
    `feedid` BIGINT(20) NOT NULL COMMENT '属于哪个feed，feed平台导入商品时用',
    `ucid` BIGINT(2) NOT NULL DEFAULT '0' COMMENT '商家在百度内部的统一ID，和merchantid的区别是，merchantid是产品内部自己定义的',
    `title` VARCHAR(1024) NOT NULL COMMENT '标题（name字段去除，和title一样的东西）',
    `subtitle` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '副标题',
    `url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(web)',
    `mobileurl` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(移动端)',
    `outerid` VARCHAR(128) NOT NULL COMMENT '商户系统中的商品id',
    `category_ori` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '原网站面包屑（和微购schema不同，这边没有拆开分多个字段）',
    `brand_ori` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '原始网站品牌（归一化后品牌从attribute表找）',
    `starttime` DATETIME NOT NULL COMMENT '开始时间',
    `endtime` DATETIME NOT NULL COMMENT '结束时间',
    `merchant_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '商品状态：0 ONLINE(上架),1 OFFLINE（下架）',
    `manual_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '运营维护状态（0 Normal，1 Pause，2 uncheck，3 check）',
    `signature` VARCHAR(256) NOT NULL COMMENT '商品签名（8张相关表签名字段：除date,输入的绝大多数信息，用于重复判断）',
    `isselfopen` TINYINT(4) NOT NULL COMMENT '是否自营（1：自营，0：非自营）',
    `dataversion` BIGINT(20) NOT NULL COMMENT '数据版本（防止老的覆盖旧的）',
    `inactivetime` DATETIME NOT NULL COMMENT '失效时间（先保留，后续再看逻辑）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    `deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0：正常;1：已删除，会移出表',
    `seller_name` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '商家名称',
    `upc` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '条形码',
    PRIMARY KEY (`sku_innerid`),
    KEY `sku_hashkey` (`sku_hashkey`),
    KEY `skuid` (`skuid`),
    KEY `unique_key` (`unique_key`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品信息表';

DROP TABLE IF EXISTS `sku_pps_19`;
CREATE TABLE `sku_pps_19`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',    
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，用于表关联',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `regionid` INT(11) NOT NULL COMMENT '地址id，可以为仓库id，目前采用国标id，全国为0',
    `terminal` TINYINT(4) NOT NULL COMMENT '0:MOBILE,1:WEB(移动端还是web端等)',
    `promotion_info` VARCHAR(1024) NOT NULl DEFAULT '' COMMENT '促销信息',
    `promotion_price_machine` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：使用促销模型算出来的价格',
    `promotion_price_manual` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：人工修改的促销价格',
    `originalprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '原始价格',
    `discountprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '折扣价',
    `stock` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '库存值状态（1:有货，0：无货）',
    `post_pay` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否支持货到付款（默认不支持）', 
    `m_update_time` DATETIME NOT NULL COMMENT '商家的库存价格更新时间，用于比较时间进行更新',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku价格&库存表';

DROP TABLE IF EXISTS `sku_level_19`;
CREATE TABLE `sku_level_19`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `level` INT(11) NOT NULL DEFAULT '0' COMMENT '确定价格库存更新频率（0更新频率最低）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku更新频率表';

DROP TABLE IF EXISTS `sku_cspu_19`;
CREATE TABLE `sku_cspu_19`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `cspuid` BIGINT(20) NOT NULL COMMENT 'Cspu ID',
    `type` TINYINT(4)  NOT NULL COMMENT 'cspu来源:1:MANUAL_CLASSIFICATION：手工修改，2:MACHINE_CLASSIFICATION：机器学习',
    `confidence` FLOAT(5,2) NOT NULL DEFAULT '0'COMMENT '取值0-100，可信度',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`),
    KEY `cspuid` (`cspuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku到cspu映射';

DROP TABLE IF EXISTS `sku_comment_19`;
CREATE TABLE `sku_comment_19`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `reviewcount` INT(11) NOT NULL COMMENT '评论数',
    `goodcount` INT(11) NOT NULL COMMENT '好评数',
    `medcount` INT(11) NOT NULL COMMENT '中评数',
    `badcount` INT(11) NOT NULL COMMENT '差评数',
    `tags` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT 'json串，评论提取tag',
    `score` DOUBLE NOT NULL COMMENT '平均分',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku评论表';


DROP TABLE IF EXISTS `bcs_sku_image_19`;
CREATE TABLE `bcs_sku_image_19`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
	`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
	`image_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片第三方地址',
	`image_bcs_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片本地地址',
	`status` TINYINT(4) NOT NULL DEFAULT '3' COMMENT '图片下载状态：0未开始，1下载出错，2正在下载，3下载到本地',
	`addtime` DATETIME NOT NULL COMMENT '添加时间',
	`updatetime` DATETIME NOT NULL COMMENT '更新时间',
	`errormessage` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '错误信息',
	`width` INT(11) NOT NULL DEFAULT '0' COMMENT '图片宽度',
	`height` INT(11) NOT NULL DEFAULT '0' COMMENT '图片高度',
	`content_md5` CHAR(32) NOT NULL  DEFAULT '' COMMENT '内容MD5',
	`sequence` INT(11) NOT NULL DEFAULT '1' COMMENT '图片序号',
	`gips_image` VARCHAR(1024) NOT NULL COMMENT '缩略图json串，一堆url和尺寸',
	`type` TINYINT(4) NOT NULL COMMENT '图片类型：主图，俯视图、侧视45度图等',
	PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '图片信息表';
DROP TABLE IF EXISTS `sku_attribute_20`;
CREATE TABLE `sku_attribute_20`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT 'Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
`property_id` VARCHAR(2048) NOT NULL DEFAULT '' COMMENT '属性id:属性值id；(存策略归一化后的id)',
`property_values` VARCHAR(2048) NOT NULl DEFAULT '' COMMENT '属性1：属性值；属性2:属性值 (存商家原始属性对)',
`property_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '对property_id+property_values做hash，用于重复判断',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品基础属性表';

DROP TABLE IF EXISTS `sku_cdt_20`;
CREATE TABLE `sku_cdt_20`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`leafcategoryid` BIGINT(20) NOT NULL COMMENT '叶子类目ID',
`classificationtype` TINYINT(4) NOT NULL COMMENT '类目来源 0：手工修改，1：机器学习，其他状态',
`confidence` FLOAT(5,2) NOT NULL DEFAULT '0' COMMENT '取值0-100，可信度(目前没在用)',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品分类信息表';

DROP TABLE IF EXISTS `sku_description_20`;
CREATE TABLE `sku_description_20`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `sku_desc_ori` LONGBLOB NOT NULL COMMENT '商品描述的压缩信息',
    `pd_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '商品描述的压缩信息',
    `sku_desc` LONGBLOB NOT NULL COMMENT '提取出来的商品描述压缩信息',
    `pd_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0 Undo, 1 doing, 2 done',
    `area` INT(11) NOT NULL DEFAULT '0' COMMENT '总的图片区域',
    `word_count` INT(11) NOT NULL DEFAULT '0' COMMENT '总字数',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品描述详情表';

DROP TABLE IF EXISTS `sku_info_20`;
CREATE TABLE `sku_info_20`(
    `sku_innerid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '商品shard内部id，自增id',
    `sku_hashkey` BIGINT(20) NOT NULL COMMENT 'Hash（skuid）的结果',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id,Merchantid(数字转化为8位的明文)拼接outerid。',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `unique_key` CHAR(32) NOT NULL DEFAULT '' COMMENT 'outer_id 的md5值  用来建索引',
    `feedid` BIGINT(20) NOT NULL COMMENT '属于哪个feed，feed平台导入商品时用',
    `ucid` BIGINT(2) NOT NULL DEFAULT '0' COMMENT '商家在百度内部的统一ID，和merchantid的区别是，merchantid是产品内部自己定义的',
    `title` VARCHAR(1024) NOT NULL COMMENT '标题（name字段去除，和title一样的东西）',
    `subtitle` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '副标题',
    `url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(web)',
    `mobileurl` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(移动端)',
    `outerid` VARCHAR(128) NOT NULL COMMENT '商户系统中的商品id',
    `category_ori` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '原网站面包屑（和微购schema不同，这边没有拆开分多个字段）',
    `brand_ori` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '原始网站品牌（归一化后品牌从attribute表找）',
    `starttime` DATETIME NOT NULL COMMENT '开始时间',
    `endtime` DATETIME NOT NULL COMMENT '结束时间',
    `merchant_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '商品状态：0 ONLINE(上架),1 OFFLINE（下架）',
    `manual_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '运营维护状态（0 Normal，1 Pause，2 uncheck，3 check）',
    `signature` VARCHAR(256) NOT NULL COMMENT '商品签名（8张相关表签名字段：除date,输入的绝大多数信息，用于重复判断）',
    `isselfopen` TINYINT(4) NOT NULL COMMENT '是否自营（1：自营，0：非自营）',
    `dataversion` BIGINT(20) NOT NULL COMMENT '数据版本（防止老的覆盖旧的）',
    `inactivetime` DATETIME NOT NULL COMMENT '失效时间（先保留，后续再看逻辑）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    `deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0：正常;1：已删除，会移出表',
    `seller_name` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '商家名称',
    `upc` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '条形码',
    PRIMARY KEY (`sku_innerid`),
    KEY `sku_hashkey` (`sku_hashkey`),
    KEY `skuid` (`skuid`),
    KEY `unique_key` (`unique_key`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品信息表';

DROP TABLE IF EXISTS `sku_pps_20`;
CREATE TABLE `sku_pps_20`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',    
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，用于表关联',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `regionid` INT(11) NOT NULL COMMENT '地址id，可以为仓库id，目前采用国标id，全国为0',
    `terminal` TINYINT(4) NOT NULL COMMENT '0:MOBILE,1:WEB(移动端还是web端等)',
    `promotion_info` VARCHAR(1024) NOT NULl DEFAULT '' COMMENT '促销信息',
    `promotion_price_machine` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：使用促销模型算出来的价格',
    `promotion_price_manual` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：人工修改的促销价格',
    `originalprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '原始价格',
    `discountprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '折扣价',
    `stock` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '库存值状态（1:有货，0：无货）',
    `post_pay` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否支持货到付款（默认不支持）', 
    `m_update_time` DATETIME NOT NULL COMMENT '商家的库存价格更新时间，用于比较时间进行更新',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku价格&库存表';

DROP TABLE IF EXISTS `sku_level_20`;
CREATE TABLE `sku_level_20`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `level` INT(11) NOT NULL DEFAULT '0' COMMENT '确定价格库存更新频率（0更新频率最低）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku更新频率表';

DROP TABLE IF EXISTS `sku_cspu_20`;
CREATE TABLE `sku_cspu_20`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `cspuid` BIGINT(20) NOT NULL COMMENT 'Cspu ID',
    `type` TINYINT(4)  NOT NULL COMMENT 'cspu来源:1:MANUAL_CLASSIFICATION：手工修改，2:MACHINE_CLASSIFICATION：机器学习',
    `confidence` FLOAT(5,2) NOT NULL DEFAULT '0'COMMENT '取值0-100，可信度',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`),
    KEY `cspuid` (`cspuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku到cspu映射';

DROP TABLE IF EXISTS `sku_comment_20`;
CREATE TABLE `sku_comment_20`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `reviewcount` INT(11) NOT NULL COMMENT '评论数',
    `goodcount` INT(11) NOT NULL COMMENT '好评数',
    `medcount` INT(11) NOT NULL COMMENT '中评数',
    `badcount` INT(11) NOT NULL COMMENT '差评数',
    `tags` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT 'json串，评论提取tag',
    `score` DOUBLE NOT NULL COMMENT '平均分',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku评论表';


DROP TABLE IF EXISTS `bcs_sku_image_20`;
CREATE TABLE `bcs_sku_image_20`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
	`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
	`image_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片第三方地址',
	`image_bcs_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片本地地址',
	`status` TINYINT(4) NOT NULL DEFAULT '3' COMMENT '图片下载状态：0未开始，1下载出错，2正在下载，3下载到本地',
	`addtime` DATETIME NOT NULL COMMENT '添加时间',
	`updatetime` DATETIME NOT NULL COMMENT '更新时间',
	`errormessage` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '错误信息',
	`width` INT(11) NOT NULL DEFAULT '0' COMMENT '图片宽度',
	`height` INT(11) NOT NULL DEFAULT '0' COMMENT '图片高度',
	`content_md5` CHAR(32) NOT NULL  DEFAULT '' COMMENT '内容MD5',
	`sequence` INT(11) NOT NULL DEFAULT '1' COMMENT '图片序号',
	`gips_image` VARCHAR(1024) NOT NULL COMMENT '缩略图json串，一堆url和尺寸',
	`type` TINYINT(4) NOT NULL COMMENT '图片类型：主图，俯视图、侧视45度图等',
	PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '图片信息表';
DROP TABLE IF EXISTS `sku_attribute_21`;
CREATE TABLE `sku_attribute_21`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT 'Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
`property_id` VARCHAR(2048) NOT NULL DEFAULT '' COMMENT '属性id:属性值id；(存策略归一化后的id)',
`property_values` VARCHAR(2048) NOT NULl DEFAULT '' COMMENT '属性1：属性值；属性2:属性值 (存商家原始属性对)',
`property_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '对property_id+property_values做hash，用于重复判断',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品基础属性表';

DROP TABLE IF EXISTS `sku_cdt_21`;
CREATE TABLE `sku_cdt_21`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`leafcategoryid` BIGINT(20) NOT NULL COMMENT '叶子类目ID',
`classificationtype` TINYINT(4) NOT NULL COMMENT '类目来源 0：手工修改，1：机器学习，其他状态',
`confidence` FLOAT(5,2) NOT NULL DEFAULT '0' COMMENT '取值0-100，可信度(目前没在用)',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品分类信息表';

DROP TABLE IF EXISTS `sku_description_21`;
CREATE TABLE `sku_description_21`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `sku_desc_ori` LONGBLOB NOT NULL COMMENT '商品描述的压缩信息',
    `pd_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '商品描述的压缩信息',
    `sku_desc` LONGBLOB NOT NULL COMMENT '提取出来的商品描述压缩信息',
    `pd_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0 Undo, 1 doing, 2 done',
    `area` INT(11) NOT NULL DEFAULT '0' COMMENT '总的图片区域',
    `word_count` INT(11) NOT NULL DEFAULT '0' COMMENT '总字数',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品描述详情表';

DROP TABLE IF EXISTS `sku_info_21`;
CREATE TABLE `sku_info_21`(
    `sku_innerid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '商品shard内部id，自增id',
    `sku_hashkey` BIGINT(20) NOT NULL COMMENT 'Hash（skuid）的结果',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id,Merchantid(数字转化为8位的明文)拼接outerid。',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `unique_key` CHAR(32) NOT NULL DEFAULT '' COMMENT 'outer_id 的md5值  用来建索引',
    `feedid` BIGINT(20) NOT NULL COMMENT '属于哪个feed，feed平台导入商品时用',
    `ucid` BIGINT(2) NOT NULL DEFAULT '0' COMMENT '商家在百度内部的统一ID，和merchantid的区别是，merchantid是产品内部自己定义的',
    `title` VARCHAR(1024) NOT NULL COMMENT '标题（name字段去除，和title一样的东西）',
    `subtitle` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '副标题',
    `url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(web)',
    `mobileurl` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(移动端)',
    `outerid` VARCHAR(128) NOT NULL COMMENT '商户系统中的商品id',
    `category_ori` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '原网站面包屑（和微购schema不同，这边没有拆开分多个字段）',
    `brand_ori` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '原始网站品牌（归一化后品牌从attribute表找）',
    `starttime` DATETIME NOT NULL COMMENT '开始时间',
    `endtime` DATETIME NOT NULL COMMENT '结束时间',
    `merchant_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '商品状态：0 ONLINE(上架),1 OFFLINE（下架）',
    `manual_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '运营维护状态（0 Normal，1 Pause，2 uncheck，3 check）',
    `signature` VARCHAR(256) NOT NULL COMMENT '商品签名（8张相关表签名字段：除date,输入的绝大多数信息，用于重复判断）',
    `isselfopen` TINYINT(4) NOT NULL COMMENT '是否自营（1：自营，0：非自营）',
    `dataversion` BIGINT(20) NOT NULL COMMENT '数据版本（防止老的覆盖旧的）',
    `inactivetime` DATETIME NOT NULL COMMENT '失效时间（先保留，后续再看逻辑）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    `deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0：正常;1：已删除，会移出表',
    `seller_name` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '商家名称',
    `upc` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '条形码',
    PRIMARY KEY (`sku_innerid`),
    KEY `sku_hashkey` (`sku_hashkey`),
    KEY `skuid` (`skuid`),
    KEY `unique_key` (`unique_key`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品信息表';

DROP TABLE IF EXISTS `sku_pps_21`;
CREATE TABLE `sku_pps_21`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',    
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，用于表关联',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `regionid` INT(11) NOT NULL COMMENT '地址id，可以为仓库id，目前采用国标id，全国为0',
    `terminal` TINYINT(4) NOT NULL COMMENT '0:MOBILE,1:WEB(移动端还是web端等)',
    `promotion_info` VARCHAR(1024) NOT NULl DEFAULT '' COMMENT '促销信息',
    `promotion_price_machine` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：使用促销模型算出来的价格',
    `promotion_price_manual` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：人工修改的促销价格',
    `originalprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '原始价格',
    `discountprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '折扣价',
    `stock` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '库存值状态（1:有货，0：无货）',
    `post_pay` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否支持货到付款（默认不支持）', 
    `m_update_time` DATETIME NOT NULL COMMENT '商家的库存价格更新时间，用于比较时间进行更新',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku价格&库存表';

DROP TABLE IF EXISTS `sku_level_21`;
CREATE TABLE `sku_level_21`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `level` INT(11) NOT NULL DEFAULT '0' COMMENT '确定价格库存更新频率（0更新频率最低）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku更新频率表';

DROP TABLE IF EXISTS `sku_cspu_21`;
CREATE TABLE `sku_cspu_21`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `cspuid` BIGINT(20) NOT NULL COMMENT 'Cspu ID',
    `type` TINYINT(4)  NOT NULL COMMENT 'cspu来源:1:MANUAL_CLASSIFICATION：手工修改，2:MACHINE_CLASSIFICATION：机器学习',
    `confidence` FLOAT(5,2) NOT NULL DEFAULT '0'COMMENT '取值0-100，可信度',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`),
    KEY `cspuid` (`cspuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku到cspu映射';

DROP TABLE IF EXISTS `sku_comment_21`;
CREATE TABLE `sku_comment_21`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `reviewcount` INT(11) NOT NULL COMMENT '评论数',
    `goodcount` INT(11) NOT NULL COMMENT '好评数',
    `medcount` INT(11) NOT NULL COMMENT '中评数',
    `badcount` INT(11) NOT NULL COMMENT '差评数',
    `tags` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT 'json串，评论提取tag',
    `score` DOUBLE NOT NULL COMMENT '平均分',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku评论表';


DROP TABLE IF EXISTS `bcs_sku_image_21`;
CREATE TABLE `bcs_sku_image_21`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
	`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
	`image_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片第三方地址',
	`image_bcs_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片本地地址',
	`status` TINYINT(4) NOT NULL DEFAULT '3' COMMENT '图片下载状态：0未开始，1下载出错，2正在下载，3下载到本地',
	`addtime` DATETIME NOT NULL COMMENT '添加时间',
	`updatetime` DATETIME NOT NULL COMMENT '更新时间',
	`errormessage` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '错误信息',
	`width` INT(11) NOT NULL DEFAULT '0' COMMENT '图片宽度',
	`height` INT(11) NOT NULL DEFAULT '0' COMMENT '图片高度',
	`content_md5` CHAR(32) NOT NULL  DEFAULT '' COMMENT '内容MD5',
	`sequence` INT(11) NOT NULL DEFAULT '1' COMMENT '图片序号',
	`gips_image` VARCHAR(1024) NOT NULL COMMENT '缩略图json串，一堆url和尺寸',
	`type` TINYINT(4) NOT NULL COMMENT '图片类型：主图，俯视图、侧视45度图等',
	PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '图片信息表';
DROP TABLE IF EXISTS `sku_attribute_22`;
CREATE TABLE `sku_attribute_22`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT 'Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
`property_id` VARCHAR(2048) NOT NULL DEFAULT '' COMMENT '属性id:属性值id；(存策略归一化后的id)',
`property_values` VARCHAR(2048) NOT NULl DEFAULT '' COMMENT '属性1：属性值；属性2:属性值 (存商家原始属性对)',
`property_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '对property_id+property_values做hash，用于重复判断',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品基础属性表';

DROP TABLE IF EXISTS `sku_cdt_22`;
CREATE TABLE `sku_cdt_22`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`leafcategoryid` BIGINT(20) NOT NULL COMMENT '叶子类目ID',
`classificationtype` TINYINT(4) NOT NULL COMMENT '类目来源 0：手工修改，1：机器学习，其他状态',
`confidence` FLOAT(5,2) NOT NULL DEFAULT '0' COMMENT '取值0-100，可信度(目前没在用)',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品分类信息表';

DROP TABLE IF EXISTS `sku_description_22`;
CREATE TABLE `sku_description_22`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `sku_desc_ori` LONGBLOB NOT NULL COMMENT '商品描述的压缩信息',
    `pd_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '商品描述的压缩信息',
    `sku_desc` LONGBLOB NOT NULL COMMENT '提取出来的商品描述压缩信息',
    `pd_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0 Undo, 1 doing, 2 done',
    `area` INT(11) NOT NULL DEFAULT '0' COMMENT '总的图片区域',
    `word_count` INT(11) NOT NULL DEFAULT '0' COMMENT '总字数',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品描述详情表';

DROP TABLE IF EXISTS `sku_info_22`;
CREATE TABLE `sku_info_22`(
    `sku_innerid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '商品shard内部id，自增id',
    `sku_hashkey` BIGINT(20) NOT NULL COMMENT 'Hash（skuid）的结果',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id,Merchantid(数字转化为8位的明文)拼接outerid。',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `unique_key` CHAR(32) NOT NULL DEFAULT '' COMMENT 'outer_id 的md5值  用来建索引',
    `feedid` BIGINT(20) NOT NULL COMMENT '属于哪个feed，feed平台导入商品时用',
    `ucid` BIGINT(2) NOT NULL DEFAULT '0' COMMENT '商家在百度内部的统一ID，和merchantid的区别是，merchantid是产品内部自己定义的',
    `title` VARCHAR(1024) NOT NULL COMMENT '标题（name字段去除，和title一样的东西）',
    `subtitle` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '副标题',
    `url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(web)',
    `mobileurl` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(移动端)',
    `outerid` VARCHAR(128) NOT NULL COMMENT '商户系统中的商品id',
    `category_ori` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '原网站面包屑（和微购schema不同，这边没有拆开分多个字段）',
    `brand_ori` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '原始网站品牌（归一化后品牌从attribute表找）',
    `starttime` DATETIME NOT NULL COMMENT '开始时间',
    `endtime` DATETIME NOT NULL COMMENT '结束时间',
    `merchant_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '商品状态：0 ONLINE(上架),1 OFFLINE（下架）',
    `manual_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '运营维护状态（0 Normal，1 Pause，2 uncheck，3 check）',
    `signature` VARCHAR(256) NOT NULL COMMENT '商品签名（8张相关表签名字段：除date,输入的绝大多数信息，用于重复判断）',
    `isselfopen` TINYINT(4) NOT NULL COMMENT '是否自营（1：自营，0：非自营）',
    `dataversion` BIGINT(20) NOT NULL COMMENT '数据版本（防止老的覆盖旧的）',
    `inactivetime` DATETIME NOT NULL COMMENT '失效时间（先保留，后续再看逻辑）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    `deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0：正常;1：已删除，会移出表',
    `seller_name` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '商家名称',
    `upc` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '条形码',
    PRIMARY KEY (`sku_innerid`),
    KEY `sku_hashkey` (`sku_hashkey`),
    KEY `skuid` (`skuid`),
    KEY `unique_key` (`unique_key`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品信息表';

DROP TABLE IF EXISTS `sku_pps_22`;
CREATE TABLE `sku_pps_22`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',    
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，用于表关联',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `regionid` INT(11) NOT NULL COMMENT '地址id，可以为仓库id，目前采用国标id，全国为0',
    `terminal` TINYINT(4) NOT NULL COMMENT '0:MOBILE,1:WEB(移动端还是web端等)',
    `promotion_info` VARCHAR(1024) NOT NULl DEFAULT '' COMMENT '促销信息',
    `promotion_price_machine` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：使用促销模型算出来的价格',
    `promotion_price_manual` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：人工修改的促销价格',
    `originalprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '原始价格',
    `discountprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '折扣价',
    `stock` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '库存值状态（1:有货，0：无货）',
    `post_pay` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否支持货到付款（默认不支持）', 
    `m_update_time` DATETIME NOT NULL COMMENT '商家的库存价格更新时间，用于比较时间进行更新',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku价格&库存表';

DROP TABLE IF EXISTS `sku_level_22`;
CREATE TABLE `sku_level_22`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `level` INT(11) NOT NULL DEFAULT '0' COMMENT '确定价格库存更新频率（0更新频率最低）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku更新频率表';

DROP TABLE IF EXISTS `sku_cspu_22`;
CREATE TABLE `sku_cspu_22`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `cspuid` BIGINT(20) NOT NULL COMMENT 'Cspu ID',
    `type` TINYINT(4)  NOT NULL COMMENT 'cspu来源:1:MANUAL_CLASSIFICATION：手工修改，2:MACHINE_CLASSIFICATION：机器学习',
    `confidence` FLOAT(5,2) NOT NULL DEFAULT '0'COMMENT '取值0-100，可信度',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`),
    KEY `cspuid` (`cspuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku到cspu映射';

DROP TABLE IF EXISTS `sku_comment_22`;
CREATE TABLE `sku_comment_22`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `reviewcount` INT(11) NOT NULL COMMENT '评论数',
    `goodcount` INT(11) NOT NULL COMMENT '好评数',
    `medcount` INT(11) NOT NULL COMMENT '中评数',
    `badcount` INT(11) NOT NULL COMMENT '差评数',
    `tags` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT 'json串，评论提取tag',
    `score` DOUBLE NOT NULL COMMENT '平均分',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku评论表';


DROP TABLE IF EXISTS `bcs_sku_image_22`;
CREATE TABLE `bcs_sku_image_22`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
	`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
	`image_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片第三方地址',
	`image_bcs_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片本地地址',
	`status` TINYINT(4) NOT NULL DEFAULT '3' COMMENT '图片下载状态：0未开始，1下载出错，2正在下载，3下载到本地',
	`addtime` DATETIME NOT NULL COMMENT '添加时间',
	`updatetime` DATETIME NOT NULL COMMENT '更新时间',
	`errormessage` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '错误信息',
	`width` INT(11) NOT NULL DEFAULT '0' COMMENT '图片宽度',
	`height` INT(11) NOT NULL DEFAULT '0' COMMENT '图片高度',
	`content_md5` CHAR(32) NOT NULL  DEFAULT '' COMMENT '内容MD5',
	`sequence` INT(11) NOT NULL DEFAULT '1' COMMENT '图片序号',
	`gips_image` VARCHAR(1024) NOT NULL COMMENT '缩略图json串，一堆url和尺寸',
	`type` TINYINT(4) NOT NULL COMMENT '图片类型：主图，俯视图、侧视45度图等',
	PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '图片信息表';
DROP TABLE IF EXISTS `sku_attribute_23`;
CREATE TABLE `sku_attribute_23`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT 'Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
`property_id` VARCHAR(2048) NOT NULL DEFAULT '' COMMENT '属性id:属性值id；(存策略归一化后的id)',
`property_values` VARCHAR(2048) NOT NULl DEFAULT '' COMMENT '属性1：属性值；属性2:属性值 (存商家原始属性对)',
`property_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '对property_id+property_values做hash，用于重复判断',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品基础属性表';

DROP TABLE IF EXISTS `sku_cdt_23`;
CREATE TABLE `sku_cdt_23`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`leafcategoryid` BIGINT(20) NOT NULL COMMENT '叶子类目ID',
`classificationtype` TINYINT(4) NOT NULL COMMENT '类目来源 0：手工修改，1：机器学习，其他状态',
`confidence` FLOAT(5,2) NOT NULL DEFAULT '0' COMMENT '取值0-100，可信度(目前没在用)',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品分类信息表';

DROP TABLE IF EXISTS `sku_description_23`;
CREATE TABLE `sku_description_23`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `sku_desc_ori` LONGBLOB NOT NULL COMMENT '商品描述的压缩信息',
    `pd_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '商品描述的压缩信息',
    `sku_desc` LONGBLOB NOT NULL COMMENT '提取出来的商品描述压缩信息',
    `pd_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0 Undo, 1 doing, 2 done',
    `area` INT(11) NOT NULL DEFAULT '0' COMMENT '总的图片区域',
    `word_count` INT(11) NOT NULL DEFAULT '0' COMMENT '总字数',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品描述详情表';

DROP TABLE IF EXISTS `sku_info_23`;
CREATE TABLE `sku_info_23`(
    `sku_innerid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '商品shard内部id，自增id',
    `sku_hashkey` BIGINT(20) NOT NULL COMMENT 'Hash（skuid）的结果',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id,Merchantid(数字转化为8位的明文)拼接outerid。',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `unique_key` CHAR(32) NOT NULL DEFAULT '' COMMENT 'outer_id 的md5值  用来建索引',
    `feedid` BIGINT(20) NOT NULL COMMENT '属于哪个feed，feed平台导入商品时用',
    `ucid` BIGINT(2) NOT NULL DEFAULT '0' COMMENT '商家在百度内部的统一ID，和merchantid的区别是，merchantid是产品内部自己定义的',
    `title` VARCHAR(1024) NOT NULL COMMENT '标题（name字段去除，和title一样的东西）',
    `subtitle` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '副标题',
    `url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(web)',
    `mobileurl` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(移动端)',
    `outerid` VARCHAR(128) NOT NULL COMMENT '商户系统中的商品id',
    `category_ori` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '原网站面包屑（和微购schema不同，这边没有拆开分多个字段）',
    `brand_ori` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '原始网站品牌（归一化后品牌从attribute表找）',
    `starttime` DATETIME NOT NULL COMMENT '开始时间',
    `endtime` DATETIME NOT NULL COMMENT '结束时间',
    `merchant_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '商品状态：0 ONLINE(上架),1 OFFLINE（下架）',
    `manual_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '运营维护状态（0 Normal，1 Pause，2 uncheck，3 check）',
    `signature` VARCHAR(256) NOT NULL COMMENT '商品签名（8张相关表签名字段：除date,输入的绝大多数信息，用于重复判断）',
    `isselfopen` TINYINT(4) NOT NULL COMMENT '是否自营（1：自营，0：非自营）',
    `dataversion` BIGINT(20) NOT NULL COMMENT '数据版本（防止老的覆盖旧的）',
    `inactivetime` DATETIME NOT NULL COMMENT '失效时间（先保留，后续再看逻辑）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    `deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0：正常;1：已删除，会移出表',
    `seller_name` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '商家名称',
    `upc` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '条形码',
    PRIMARY KEY (`sku_innerid`),
    KEY `sku_hashkey` (`sku_hashkey`),
    KEY `skuid` (`skuid`),
    KEY `unique_key` (`unique_key`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品信息表';

DROP TABLE IF EXISTS `sku_pps_23`;
CREATE TABLE `sku_pps_23`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',    
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，用于表关联',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `regionid` INT(11) NOT NULL COMMENT '地址id，可以为仓库id，目前采用国标id，全国为0',
    `terminal` TINYINT(4) NOT NULL COMMENT '0:MOBILE,1:WEB(移动端还是web端等)',
    `promotion_info` VARCHAR(1024) NOT NULl DEFAULT '' COMMENT '促销信息',
    `promotion_price_machine` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：使用促销模型算出来的价格',
    `promotion_price_manual` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：人工修改的促销价格',
    `originalprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '原始价格',
    `discountprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '折扣价',
    `stock` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '库存值状态（1:有货，0：无货）',
    `post_pay` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否支持货到付款（默认不支持）', 
    `m_update_time` DATETIME NOT NULL COMMENT '商家的库存价格更新时间，用于比较时间进行更新',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku价格&库存表';

DROP TABLE IF EXISTS `sku_level_23`;
CREATE TABLE `sku_level_23`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `level` INT(11) NOT NULL DEFAULT '0' COMMENT '确定价格库存更新频率（0更新频率最低）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku更新频率表';

DROP TABLE IF EXISTS `sku_cspu_23`;
CREATE TABLE `sku_cspu_23`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `cspuid` BIGINT(20) NOT NULL COMMENT 'Cspu ID',
    `type` TINYINT(4)  NOT NULL COMMENT 'cspu来源:1:MANUAL_CLASSIFICATION：手工修改，2:MACHINE_CLASSIFICATION：机器学习',
    `confidence` FLOAT(5,2) NOT NULL DEFAULT '0'COMMENT '取值0-100，可信度',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`),
    KEY `cspuid` (`cspuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku到cspu映射';

DROP TABLE IF EXISTS `sku_comment_23`;
CREATE TABLE `sku_comment_23`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `reviewcount` INT(11) NOT NULL COMMENT '评论数',
    `goodcount` INT(11) NOT NULL COMMENT '好评数',
    `medcount` INT(11) NOT NULL COMMENT '中评数',
    `badcount` INT(11) NOT NULL COMMENT '差评数',
    `tags` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT 'json串，评论提取tag',
    `score` DOUBLE NOT NULL COMMENT '平均分',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku评论表';


DROP TABLE IF EXISTS `bcs_sku_image_23`;
CREATE TABLE `bcs_sku_image_23`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
	`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
	`image_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片第三方地址',
	`image_bcs_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片本地地址',
	`status` TINYINT(4) NOT NULL DEFAULT '3' COMMENT '图片下载状态：0未开始，1下载出错，2正在下载，3下载到本地',
	`addtime` DATETIME NOT NULL COMMENT '添加时间',
	`updatetime` DATETIME NOT NULL COMMENT '更新时间',
	`errormessage` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '错误信息',
	`width` INT(11) NOT NULL DEFAULT '0' COMMENT '图片宽度',
	`height` INT(11) NOT NULL DEFAULT '0' COMMENT '图片高度',
	`content_md5` CHAR(32) NOT NULL  DEFAULT '' COMMENT '内容MD5',
	`sequence` INT(11) NOT NULL DEFAULT '1' COMMENT '图片序号',
	`gips_image` VARCHAR(1024) NOT NULL COMMENT '缩略图json串，一堆url和尺寸',
	`type` TINYINT(4) NOT NULL COMMENT '图片类型：主图，俯视图、侧视45度图等',
	PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '图片信息表';
DROP TABLE IF EXISTS `sku_attribute_24`;
CREATE TABLE `sku_attribute_24`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT 'Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
`property_id` VARCHAR(2048) NOT NULL DEFAULT '' COMMENT '属性id:属性值id；(存策略归一化后的id)',
`property_values` VARCHAR(2048) NOT NULl DEFAULT '' COMMENT '属性1：属性值；属性2:属性值 (存商家原始属性对)',
`property_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '对property_id+property_values做hash，用于重复判断',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品基础属性表';

DROP TABLE IF EXISTS `sku_cdt_24`;
CREATE TABLE `sku_cdt_24`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`leafcategoryid` BIGINT(20) NOT NULL COMMENT '叶子类目ID',
`classificationtype` TINYINT(4) NOT NULL COMMENT '类目来源 0：手工修改，1：机器学习，其他状态',
`confidence` FLOAT(5,2) NOT NULL DEFAULT '0' COMMENT '取值0-100，可信度(目前没在用)',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品分类信息表';

DROP TABLE IF EXISTS `sku_description_24`;
CREATE TABLE `sku_description_24`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `sku_desc_ori` LONGBLOB NOT NULL COMMENT '商品描述的压缩信息',
    `pd_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '商品描述的压缩信息',
    `sku_desc` LONGBLOB NOT NULL COMMENT '提取出来的商品描述压缩信息',
    `pd_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0 Undo, 1 doing, 2 done',
    `area` INT(11) NOT NULL DEFAULT '0' COMMENT '总的图片区域',
    `word_count` INT(11) NOT NULL DEFAULT '0' COMMENT '总字数',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品描述详情表';

DROP TABLE IF EXISTS `sku_info_24`;
CREATE TABLE `sku_info_24`(
    `sku_innerid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '商品shard内部id，自增id',
    `sku_hashkey` BIGINT(20) NOT NULL COMMENT 'Hash（skuid）的结果',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id,Merchantid(数字转化为8位的明文)拼接outerid。',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `unique_key` CHAR(32) NOT NULL DEFAULT '' COMMENT 'outer_id 的md5值  用来建索引',
    `feedid` BIGINT(20) NOT NULL COMMENT '属于哪个feed，feed平台导入商品时用',
    `ucid` BIGINT(2) NOT NULL DEFAULT '0' COMMENT '商家在百度内部的统一ID，和merchantid的区别是，merchantid是产品内部自己定义的',
    `title` VARCHAR(1024) NOT NULL COMMENT '标题（name字段去除，和title一样的东西）',
    `subtitle` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '副标题',
    `url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(web)',
    `mobileurl` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(移动端)',
    `outerid` VARCHAR(128) NOT NULL COMMENT '商户系统中的商品id',
    `category_ori` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '原网站面包屑（和微购schema不同，这边没有拆开分多个字段）',
    `brand_ori` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '原始网站品牌（归一化后品牌从attribute表找）',
    `starttime` DATETIME NOT NULL COMMENT '开始时间',
    `endtime` DATETIME NOT NULL COMMENT '结束时间',
    `merchant_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '商品状态：0 ONLINE(上架),1 OFFLINE（下架）',
    `manual_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '运营维护状态（0 Normal，1 Pause，2 uncheck，3 check）',
    `signature` VARCHAR(256) NOT NULL COMMENT '商品签名（8张相关表签名字段：除date,输入的绝大多数信息，用于重复判断）',
    `isselfopen` TINYINT(4) NOT NULL COMMENT '是否自营（1：自营，0：非自营）',
    `dataversion` BIGINT(20) NOT NULL COMMENT '数据版本（防止老的覆盖旧的）',
    `inactivetime` DATETIME NOT NULL COMMENT '失效时间（先保留，后续再看逻辑）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    `deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0：正常;1：已删除，会移出表',
    `seller_name` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '商家名称',
    `upc` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '条形码',
    PRIMARY KEY (`sku_innerid`),
    KEY `sku_hashkey` (`sku_hashkey`),
    KEY `skuid` (`skuid`),
    KEY `unique_key` (`unique_key`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品信息表';

DROP TABLE IF EXISTS `sku_pps_24`;
CREATE TABLE `sku_pps_24`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',    
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，用于表关联',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `regionid` INT(11) NOT NULL COMMENT '地址id，可以为仓库id，目前采用国标id，全国为0',
    `terminal` TINYINT(4) NOT NULL COMMENT '0:MOBILE,1:WEB(移动端还是web端等)',
    `promotion_info` VARCHAR(1024) NOT NULl DEFAULT '' COMMENT '促销信息',
    `promotion_price_machine` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：使用促销模型算出来的价格',
    `promotion_price_manual` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：人工修改的促销价格',
    `originalprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '原始价格',
    `discountprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '折扣价',
    `stock` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '库存值状态（1:有货，0：无货）',
    `post_pay` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否支持货到付款（默认不支持）', 
    `m_update_time` DATETIME NOT NULL COMMENT '商家的库存价格更新时间，用于比较时间进行更新',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku价格&库存表';

DROP TABLE IF EXISTS `sku_level_24`;
CREATE TABLE `sku_level_24`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `level` INT(11) NOT NULL DEFAULT '0' COMMENT '确定价格库存更新频率（0更新频率最低）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku更新频率表';

DROP TABLE IF EXISTS `sku_cspu_24`;
CREATE TABLE `sku_cspu_24`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `cspuid` BIGINT(20) NOT NULL COMMENT 'Cspu ID',
    `type` TINYINT(4)  NOT NULL COMMENT 'cspu来源:1:MANUAL_CLASSIFICATION：手工修改，2:MACHINE_CLASSIFICATION：机器学习',
    `confidence` FLOAT(5,2) NOT NULL DEFAULT '0'COMMENT '取值0-100，可信度',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`),
    KEY `cspuid` (`cspuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku到cspu映射';

DROP TABLE IF EXISTS `sku_comment_24`;
CREATE TABLE `sku_comment_24`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `reviewcount` INT(11) NOT NULL COMMENT '评论数',
    `goodcount` INT(11) NOT NULL COMMENT '好评数',
    `medcount` INT(11) NOT NULL COMMENT '中评数',
    `badcount` INT(11) NOT NULL COMMENT '差评数',
    `tags` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT 'json串，评论提取tag',
    `score` DOUBLE NOT NULL COMMENT '平均分',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku评论表';


DROP TABLE IF EXISTS `bcs_sku_image_24`;
CREATE TABLE `bcs_sku_image_24`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
	`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
	`image_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片第三方地址',
	`image_bcs_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片本地地址',
	`status` TINYINT(4) NOT NULL DEFAULT '3' COMMENT '图片下载状态：0未开始，1下载出错，2正在下载，3下载到本地',
	`addtime` DATETIME NOT NULL COMMENT '添加时间',
	`updatetime` DATETIME NOT NULL COMMENT '更新时间',
	`errormessage` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '错误信息',
	`width` INT(11) NOT NULL DEFAULT '0' COMMENT '图片宽度',
	`height` INT(11) NOT NULL DEFAULT '0' COMMENT '图片高度',
	`content_md5` CHAR(32) NOT NULL  DEFAULT '' COMMENT '内容MD5',
	`sequence` INT(11) NOT NULL DEFAULT '1' COMMENT '图片序号',
	`gips_image` VARCHAR(1024) NOT NULL COMMENT '缩略图json串，一堆url和尺寸',
	`type` TINYINT(4) NOT NULL COMMENT '图片类型：主图，俯视图、侧视45度图等',
	PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '图片信息表';
DROP TABLE IF EXISTS `sku_attribute_25`;
CREATE TABLE `sku_attribute_25`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT 'Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
`property_id` VARCHAR(2048) NOT NULL DEFAULT '' COMMENT '属性id:属性值id；(存策略归一化后的id)',
`property_values` VARCHAR(2048) NOT NULl DEFAULT '' COMMENT '属性1：属性值；属性2:属性值 (存商家原始属性对)',
`property_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '对property_id+property_values做hash，用于重复判断',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品基础属性表';

DROP TABLE IF EXISTS `sku_cdt_25`;
CREATE TABLE `sku_cdt_25`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`leafcategoryid` BIGINT(20) NOT NULL COMMENT '叶子类目ID',
`classificationtype` TINYINT(4) NOT NULL COMMENT '类目来源 0：手工修改，1：机器学习，其他状态',
`confidence` FLOAT(5,2) NOT NULL DEFAULT '0' COMMENT '取值0-100，可信度(目前没在用)',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品分类信息表';

DROP TABLE IF EXISTS `sku_description_25`;
CREATE TABLE `sku_description_25`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `sku_desc_ori` LONGBLOB NOT NULL COMMENT '商品描述的压缩信息',
    `pd_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '商品描述的压缩信息',
    `sku_desc` LONGBLOB NOT NULL COMMENT '提取出来的商品描述压缩信息',
    `pd_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0 Undo, 1 doing, 2 done',
    `area` INT(11) NOT NULL DEFAULT '0' COMMENT '总的图片区域',
    `word_count` INT(11) NOT NULL DEFAULT '0' COMMENT '总字数',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品描述详情表';

DROP TABLE IF EXISTS `sku_info_25`;
CREATE TABLE `sku_info_25`(
    `sku_innerid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '商品shard内部id，自增id',
    `sku_hashkey` BIGINT(20) NOT NULL COMMENT 'Hash（skuid）的结果',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id,Merchantid(数字转化为8位的明文)拼接outerid。',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `unique_key` CHAR(32) NOT NULL DEFAULT '' COMMENT 'outer_id 的md5值  用来建索引',
    `feedid` BIGINT(20) NOT NULL COMMENT '属于哪个feed，feed平台导入商品时用',
    `ucid` BIGINT(2) NOT NULL DEFAULT '0' COMMENT '商家在百度内部的统一ID，和merchantid的区别是，merchantid是产品内部自己定义的',
    `title` VARCHAR(1024) NOT NULL COMMENT '标题（name字段去除，和title一样的东西）',
    `subtitle` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '副标题',
    `url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(web)',
    `mobileurl` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(移动端)',
    `outerid` VARCHAR(128) NOT NULL COMMENT '商户系统中的商品id',
    `category_ori` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '原网站面包屑（和微购schema不同，这边没有拆开分多个字段）',
    `brand_ori` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '原始网站品牌（归一化后品牌从attribute表找）',
    `starttime` DATETIME NOT NULL COMMENT '开始时间',
    `endtime` DATETIME NOT NULL COMMENT '结束时间',
    `merchant_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '商品状态：0 ONLINE(上架),1 OFFLINE（下架）',
    `manual_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '运营维护状态（0 Normal，1 Pause，2 uncheck，3 check）',
    `signature` VARCHAR(256) NOT NULL COMMENT '商品签名（8张相关表签名字段：除date,输入的绝大多数信息，用于重复判断）',
    `isselfopen` TINYINT(4) NOT NULL COMMENT '是否自营（1：自营，0：非自营）',
    `dataversion` BIGINT(20) NOT NULL COMMENT '数据版本（防止老的覆盖旧的）',
    `inactivetime` DATETIME NOT NULL COMMENT '失效时间（先保留，后续再看逻辑）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    `deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0：正常;1：已删除，会移出表',
    `seller_name` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '商家名称',
    `upc` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '条形码',
    PRIMARY KEY (`sku_innerid`),
    KEY `sku_hashkey` (`sku_hashkey`),
    KEY `skuid` (`skuid`),
    KEY `unique_key` (`unique_key`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品信息表';

DROP TABLE IF EXISTS `sku_pps_25`;
CREATE TABLE `sku_pps_25`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',    
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，用于表关联',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `regionid` INT(11) NOT NULL COMMENT '地址id，可以为仓库id，目前采用国标id，全国为0',
    `terminal` TINYINT(4) NOT NULL COMMENT '0:MOBILE,1:WEB(移动端还是web端等)',
    `promotion_info` VARCHAR(1024) NOT NULl DEFAULT '' COMMENT '促销信息',
    `promotion_price_machine` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：使用促销模型算出来的价格',
    `promotion_price_manual` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：人工修改的促销价格',
    `originalprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '原始价格',
    `discountprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '折扣价',
    `stock` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '库存值状态（1:有货，0：无货）',
    `post_pay` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否支持货到付款（默认不支持）', 
    `m_update_time` DATETIME NOT NULL COMMENT '商家的库存价格更新时间，用于比较时间进行更新',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku价格&库存表';

DROP TABLE IF EXISTS `sku_level_25`;
CREATE TABLE `sku_level_25`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `level` INT(11) NOT NULL DEFAULT '0' COMMENT '确定价格库存更新频率（0更新频率最低）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku更新频率表';

DROP TABLE IF EXISTS `sku_cspu_25`;
CREATE TABLE `sku_cspu_25`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `cspuid` BIGINT(20) NOT NULL COMMENT 'Cspu ID',
    `type` TINYINT(4)  NOT NULL COMMENT 'cspu来源:1:MANUAL_CLASSIFICATION：手工修改，2:MACHINE_CLASSIFICATION：机器学习',
    `confidence` FLOAT(5,2) NOT NULL DEFAULT '0'COMMENT '取值0-100，可信度',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`),
    KEY `cspuid` (`cspuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku到cspu映射';

DROP TABLE IF EXISTS `sku_comment_25`;
CREATE TABLE `sku_comment_25`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `reviewcount` INT(11) NOT NULL COMMENT '评论数',
    `goodcount` INT(11) NOT NULL COMMENT '好评数',
    `medcount` INT(11) NOT NULL COMMENT '中评数',
    `badcount` INT(11) NOT NULL COMMENT '差评数',
    `tags` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT 'json串，评论提取tag',
    `score` DOUBLE NOT NULL COMMENT '平均分',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku评论表';


DROP TABLE IF EXISTS `bcs_sku_image_25`;
CREATE TABLE `bcs_sku_image_25`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
	`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
	`image_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片第三方地址',
	`image_bcs_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片本地地址',
	`status` TINYINT(4) NOT NULL DEFAULT '3' COMMENT '图片下载状态：0未开始，1下载出错，2正在下载，3下载到本地',
	`addtime` DATETIME NOT NULL COMMENT '添加时间',
	`updatetime` DATETIME NOT NULL COMMENT '更新时间',
	`errormessage` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '错误信息',
	`width` INT(11) NOT NULL DEFAULT '0' COMMENT '图片宽度',
	`height` INT(11) NOT NULL DEFAULT '0' COMMENT '图片高度',
	`content_md5` CHAR(32) NOT NULL  DEFAULT '' COMMENT '内容MD5',
	`sequence` INT(11) NOT NULL DEFAULT '1' COMMENT '图片序号',
	`gips_image` VARCHAR(1024) NOT NULL COMMENT '缩略图json串，一堆url和尺寸',
	`type` TINYINT(4) NOT NULL COMMENT '图片类型：主图，俯视图、侧视45度图等',
	PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '图片信息表';
DROP TABLE IF EXISTS `sku_attribute_26`;
CREATE TABLE `sku_attribute_26`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT 'Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
`property_id` VARCHAR(2048) NOT NULL DEFAULT '' COMMENT '属性id:属性值id；(存策略归一化后的id)',
`property_values` VARCHAR(2048) NOT NULl DEFAULT '' COMMENT '属性1：属性值；属性2:属性值 (存商家原始属性对)',
`property_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '对property_id+property_values做hash，用于重复判断',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品基础属性表';

DROP TABLE IF EXISTS `sku_cdt_26`;
CREATE TABLE `sku_cdt_26`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`leafcategoryid` BIGINT(20) NOT NULL COMMENT '叶子类目ID',
`classificationtype` TINYINT(4) NOT NULL COMMENT '类目来源 0：手工修改，1：机器学习，其他状态',
`confidence` FLOAT(5,2) NOT NULL DEFAULT '0' COMMENT '取值0-100，可信度(目前没在用)',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品分类信息表';

DROP TABLE IF EXISTS `sku_description_26`;
CREATE TABLE `sku_description_26`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `sku_desc_ori` LONGBLOB NOT NULL COMMENT '商品描述的压缩信息',
    `pd_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '商品描述的压缩信息',
    `sku_desc` LONGBLOB NOT NULL COMMENT '提取出来的商品描述压缩信息',
    `pd_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0 Undo, 1 doing, 2 done',
    `area` INT(11) NOT NULL DEFAULT '0' COMMENT '总的图片区域',
    `word_count` INT(11) NOT NULL DEFAULT '0' COMMENT '总字数',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品描述详情表';

DROP TABLE IF EXISTS `sku_info_26`;
CREATE TABLE `sku_info_26`(
    `sku_innerid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '商品shard内部id，自增id',
    `sku_hashkey` BIGINT(20) NOT NULL COMMENT 'Hash（skuid）的结果',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id,Merchantid(数字转化为8位的明文)拼接outerid。',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `unique_key` CHAR(32) NOT NULL DEFAULT '' COMMENT 'outer_id 的md5值  用来建索引',
    `feedid` BIGINT(20) NOT NULL COMMENT '属于哪个feed，feed平台导入商品时用',
    `ucid` BIGINT(2) NOT NULL DEFAULT '0' COMMENT '商家在百度内部的统一ID，和merchantid的区别是，merchantid是产品内部自己定义的',
    `title` VARCHAR(1024) NOT NULL COMMENT '标题（name字段去除，和title一样的东西）',
    `subtitle` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '副标题',
    `url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(web)',
    `mobileurl` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(移动端)',
    `outerid` VARCHAR(128) NOT NULL COMMENT '商户系统中的商品id',
    `category_ori` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '原网站面包屑（和微购schema不同，这边没有拆开分多个字段）',
    `brand_ori` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '原始网站品牌（归一化后品牌从attribute表找）',
    `starttime` DATETIME NOT NULL COMMENT '开始时间',
    `endtime` DATETIME NOT NULL COMMENT '结束时间',
    `merchant_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '商品状态：0 ONLINE(上架),1 OFFLINE（下架）',
    `manual_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '运营维护状态（0 Normal，1 Pause，2 uncheck，3 check）',
    `signature` VARCHAR(256) NOT NULL COMMENT '商品签名（8张相关表签名字段：除date,输入的绝大多数信息，用于重复判断）',
    `isselfopen` TINYINT(4) NOT NULL COMMENT '是否自营（1：自营，0：非自营）',
    `dataversion` BIGINT(20) NOT NULL COMMENT '数据版本（防止老的覆盖旧的）',
    `inactivetime` DATETIME NOT NULL COMMENT '失效时间（先保留，后续再看逻辑）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    `deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0：正常;1：已删除，会移出表',
    `seller_name` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '商家名称',
    `upc` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '条形码',
    PRIMARY KEY (`sku_innerid`),
    KEY `sku_hashkey` (`sku_hashkey`),
    KEY `skuid` (`skuid`),
    KEY `unique_key` (`unique_key`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品信息表';

DROP TABLE IF EXISTS `sku_pps_26`;
CREATE TABLE `sku_pps_26`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',    
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，用于表关联',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `regionid` INT(11) NOT NULL COMMENT '地址id，可以为仓库id，目前采用国标id，全国为0',
    `terminal` TINYINT(4) NOT NULL COMMENT '0:MOBILE,1:WEB(移动端还是web端等)',
    `promotion_info` VARCHAR(1024) NOT NULl DEFAULT '' COMMENT '促销信息',
    `promotion_price_machine` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：使用促销模型算出来的价格',
    `promotion_price_manual` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：人工修改的促销价格',
    `originalprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '原始价格',
    `discountprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '折扣价',
    `stock` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '库存值状态（1:有货，0：无货）',
    `post_pay` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否支持货到付款（默认不支持）', 
    `m_update_time` DATETIME NOT NULL COMMENT '商家的库存价格更新时间，用于比较时间进行更新',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku价格&库存表';

DROP TABLE IF EXISTS `sku_level_26`;
CREATE TABLE `sku_level_26`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `level` INT(11) NOT NULL DEFAULT '0' COMMENT '确定价格库存更新频率（0更新频率最低）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku更新频率表';

DROP TABLE IF EXISTS `sku_cspu_26`;
CREATE TABLE `sku_cspu_26`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `cspuid` BIGINT(20) NOT NULL COMMENT 'Cspu ID',
    `type` TINYINT(4)  NOT NULL COMMENT 'cspu来源:1:MANUAL_CLASSIFICATION：手工修改，2:MACHINE_CLASSIFICATION：机器学习',
    `confidence` FLOAT(5,2) NOT NULL DEFAULT '0'COMMENT '取值0-100，可信度',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`),
    KEY `cspuid` (`cspuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku到cspu映射';

DROP TABLE IF EXISTS `sku_comment_26`;
CREATE TABLE `sku_comment_26`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `reviewcount` INT(11) NOT NULL COMMENT '评论数',
    `goodcount` INT(11) NOT NULL COMMENT '好评数',
    `medcount` INT(11) NOT NULL COMMENT '中评数',
    `badcount` INT(11) NOT NULL COMMENT '差评数',
    `tags` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT 'json串，评论提取tag',
    `score` DOUBLE NOT NULL COMMENT '平均分',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku评论表';


DROP TABLE IF EXISTS `bcs_sku_image_26`;
CREATE TABLE `bcs_sku_image_26`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
	`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
	`image_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片第三方地址',
	`image_bcs_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片本地地址',
	`status` TINYINT(4) NOT NULL DEFAULT '3' COMMENT '图片下载状态：0未开始，1下载出错，2正在下载，3下载到本地',
	`addtime` DATETIME NOT NULL COMMENT '添加时间',
	`updatetime` DATETIME NOT NULL COMMENT '更新时间',
	`errormessage` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '错误信息',
	`width` INT(11) NOT NULL DEFAULT '0' COMMENT '图片宽度',
	`height` INT(11) NOT NULL DEFAULT '0' COMMENT '图片高度',
	`content_md5` CHAR(32) NOT NULL  DEFAULT '' COMMENT '内容MD5',
	`sequence` INT(11) NOT NULL DEFAULT '1' COMMENT '图片序号',
	`gips_image` VARCHAR(1024) NOT NULL COMMENT '缩略图json串，一堆url和尺寸',
	`type` TINYINT(4) NOT NULL COMMENT '图片类型：主图，俯视图、侧视45度图等',
	PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '图片信息表';
DROP TABLE IF EXISTS `sku_attribute_27`;
CREATE TABLE `sku_attribute_27`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT 'Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
`property_id` VARCHAR(2048) NOT NULL DEFAULT '' COMMENT '属性id:属性值id；(存策略归一化后的id)',
`property_values` VARCHAR(2048) NOT NULl DEFAULT '' COMMENT '属性1：属性值；属性2:属性值 (存商家原始属性对)',
`property_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '对property_id+property_values做hash，用于重复判断',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品基础属性表';

DROP TABLE IF EXISTS `sku_cdt_27`;
CREATE TABLE `sku_cdt_27`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`leafcategoryid` BIGINT(20) NOT NULL COMMENT '叶子类目ID',
`classificationtype` TINYINT(4) NOT NULL COMMENT '类目来源 0：手工修改，1：机器学习，其他状态',
`confidence` FLOAT(5,2) NOT NULL DEFAULT '0' COMMENT '取值0-100，可信度(目前没在用)',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品分类信息表';

DROP TABLE IF EXISTS `sku_description_27`;
CREATE TABLE `sku_description_27`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `sku_desc_ori` LONGBLOB NOT NULL COMMENT '商品描述的压缩信息',
    `pd_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '商品描述的压缩信息',
    `sku_desc` LONGBLOB NOT NULL COMMENT '提取出来的商品描述压缩信息',
    `pd_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0 Undo, 1 doing, 2 done',
    `area` INT(11) NOT NULL DEFAULT '0' COMMENT '总的图片区域',
    `word_count` INT(11) NOT NULL DEFAULT '0' COMMENT '总字数',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品描述详情表';

DROP TABLE IF EXISTS `sku_info_27`;
CREATE TABLE `sku_info_27`(
    `sku_innerid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '商品shard内部id，自增id',
    `sku_hashkey` BIGINT(20) NOT NULL COMMENT 'Hash（skuid）的结果',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id,Merchantid(数字转化为8位的明文)拼接outerid。',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `unique_key` CHAR(32) NOT NULL DEFAULT '' COMMENT 'outer_id 的md5值  用来建索引',
    `feedid` BIGINT(20) NOT NULL COMMENT '属于哪个feed，feed平台导入商品时用',
    `ucid` BIGINT(2) NOT NULL DEFAULT '0' COMMENT '商家在百度内部的统一ID，和merchantid的区别是，merchantid是产品内部自己定义的',
    `title` VARCHAR(1024) NOT NULL COMMENT '标题（name字段去除，和title一样的东西）',
    `subtitle` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '副标题',
    `url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(web)',
    `mobileurl` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(移动端)',
    `outerid` VARCHAR(128) NOT NULL COMMENT '商户系统中的商品id',
    `category_ori` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '原网站面包屑（和微购schema不同，这边没有拆开分多个字段）',
    `brand_ori` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '原始网站品牌（归一化后品牌从attribute表找）',
    `starttime` DATETIME NOT NULL COMMENT '开始时间',
    `endtime` DATETIME NOT NULL COMMENT '结束时间',
    `merchant_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '商品状态：0 ONLINE(上架),1 OFFLINE（下架）',
    `manual_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '运营维护状态（0 Normal，1 Pause，2 uncheck，3 check）',
    `signature` VARCHAR(256) NOT NULL COMMENT '商品签名（8张相关表签名字段：除date,输入的绝大多数信息，用于重复判断）',
    `isselfopen` TINYINT(4) NOT NULL COMMENT '是否自营（1：自营，0：非自营）',
    `dataversion` BIGINT(20) NOT NULL COMMENT '数据版本（防止老的覆盖旧的）',
    `inactivetime` DATETIME NOT NULL COMMENT '失效时间（先保留，后续再看逻辑）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    `deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0：正常;1：已删除，会移出表',
    `seller_name` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '商家名称',
    `upc` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '条形码',
    PRIMARY KEY (`sku_innerid`),
    KEY `sku_hashkey` (`sku_hashkey`),
    KEY `skuid` (`skuid`),
    KEY `unique_key` (`unique_key`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品信息表';

DROP TABLE IF EXISTS `sku_pps_27`;
CREATE TABLE `sku_pps_27`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',    
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，用于表关联',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `regionid` INT(11) NOT NULL COMMENT '地址id，可以为仓库id，目前采用国标id，全国为0',
    `terminal` TINYINT(4) NOT NULL COMMENT '0:MOBILE,1:WEB(移动端还是web端等)',
    `promotion_info` VARCHAR(1024) NOT NULl DEFAULT '' COMMENT '促销信息',
    `promotion_price_machine` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：使用促销模型算出来的价格',
    `promotion_price_manual` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：人工修改的促销价格',
    `originalprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '原始价格',
    `discountprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '折扣价',
    `stock` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '库存值状态（1:有货，0：无货）',
    `post_pay` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否支持货到付款（默认不支持）', 
    `m_update_time` DATETIME NOT NULL COMMENT '商家的库存价格更新时间，用于比较时间进行更新',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku价格&库存表';

DROP TABLE IF EXISTS `sku_level_27`;
CREATE TABLE `sku_level_27`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `level` INT(11) NOT NULL DEFAULT '0' COMMENT '确定价格库存更新频率（0更新频率最低）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku更新频率表';

DROP TABLE IF EXISTS `sku_cspu_27`;
CREATE TABLE `sku_cspu_27`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `cspuid` BIGINT(20) NOT NULL COMMENT 'Cspu ID',
    `type` TINYINT(4)  NOT NULL COMMENT 'cspu来源:1:MANUAL_CLASSIFICATION：手工修改，2:MACHINE_CLASSIFICATION：机器学习',
    `confidence` FLOAT(5,2) NOT NULL DEFAULT '0'COMMENT '取值0-100，可信度',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`),
    KEY `cspuid` (`cspuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku到cspu映射';

DROP TABLE IF EXISTS `sku_comment_27`;
CREATE TABLE `sku_comment_27`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `reviewcount` INT(11) NOT NULL COMMENT '评论数',
    `goodcount` INT(11) NOT NULL COMMENT '好评数',
    `medcount` INT(11) NOT NULL COMMENT '中评数',
    `badcount` INT(11) NOT NULL COMMENT '差评数',
    `tags` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT 'json串，评论提取tag',
    `score` DOUBLE NOT NULL COMMENT '平均分',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku评论表';


DROP TABLE IF EXISTS `bcs_sku_image_27`;
CREATE TABLE `bcs_sku_image_27`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
	`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
	`image_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片第三方地址',
	`image_bcs_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片本地地址',
	`status` TINYINT(4) NOT NULL DEFAULT '3' COMMENT '图片下载状态：0未开始，1下载出错，2正在下载，3下载到本地',
	`addtime` DATETIME NOT NULL COMMENT '添加时间',
	`updatetime` DATETIME NOT NULL COMMENT '更新时间',
	`errormessage` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '错误信息',
	`width` INT(11) NOT NULL DEFAULT '0' COMMENT '图片宽度',
	`height` INT(11) NOT NULL DEFAULT '0' COMMENT '图片高度',
	`content_md5` CHAR(32) NOT NULL  DEFAULT '' COMMENT '内容MD5',
	`sequence` INT(11) NOT NULL DEFAULT '1' COMMENT '图片序号',
	`gips_image` VARCHAR(1024) NOT NULL COMMENT '缩略图json串，一堆url和尺寸',
	`type` TINYINT(4) NOT NULL COMMENT '图片类型：主图，俯视图、侧视45度图等',
	PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '图片信息表';
DROP TABLE IF EXISTS `sku_attribute_28`;
CREATE TABLE `sku_attribute_28`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT 'Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
`property_id` VARCHAR(2048) NOT NULL DEFAULT '' COMMENT '属性id:属性值id；(存策略归一化后的id)',
`property_values` VARCHAR(2048) NOT NULl DEFAULT '' COMMENT '属性1：属性值；属性2:属性值 (存商家原始属性对)',
`property_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '对property_id+property_values做hash，用于重复判断',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品基础属性表';

DROP TABLE IF EXISTS `sku_cdt_28`;
CREATE TABLE `sku_cdt_28`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`leafcategoryid` BIGINT(20) NOT NULL COMMENT '叶子类目ID',
`classificationtype` TINYINT(4) NOT NULL COMMENT '类目来源 0：手工修改，1：机器学习，其他状态',
`confidence` FLOAT(5,2) NOT NULL DEFAULT '0' COMMENT '取值0-100，可信度(目前没在用)',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品分类信息表';

DROP TABLE IF EXISTS `sku_description_28`;
CREATE TABLE `sku_description_28`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `sku_desc_ori` LONGBLOB NOT NULL COMMENT '商品描述的压缩信息',
    `pd_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '商品描述的压缩信息',
    `sku_desc` LONGBLOB NOT NULL COMMENT '提取出来的商品描述压缩信息',
    `pd_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0 Undo, 1 doing, 2 done',
    `area` INT(11) NOT NULL DEFAULT '0' COMMENT '总的图片区域',
    `word_count` INT(11) NOT NULL DEFAULT '0' COMMENT '总字数',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品描述详情表';

DROP TABLE IF EXISTS `sku_info_28`;
CREATE TABLE `sku_info_28`(
    `sku_innerid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '商品shard内部id，自增id',
    `sku_hashkey` BIGINT(20) NOT NULL COMMENT 'Hash（skuid）的结果',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id,Merchantid(数字转化为8位的明文)拼接outerid。',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `unique_key` CHAR(32) NOT NULL DEFAULT '' COMMENT 'outer_id 的md5值  用来建索引',
    `feedid` BIGINT(20) NOT NULL COMMENT '属于哪个feed，feed平台导入商品时用',
    `ucid` BIGINT(2) NOT NULL DEFAULT '0' COMMENT '商家在百度内部的统一ID，和merchantid的区别是，merchantid是产品内部自己定义的',
    `title` VARCHAR(1024) NOT NULL COMMENT '标题（name字段去除，和title一样的东西）',
    `subtitle` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '副标题',
    `url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(web)',
    `mobileurl` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(移动端)',
    `outerid` VARCHAR(128) NOT NULL COMMENT '商户系统中的商品id',
    `category_ori` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '原网站面包屑（和微购schema不同，这边没有拆开分多个字段）',
    `brand_ori` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '原始网站品牌（归一化后品牌从attribute表找）',
    `starttime` DATETIME NOT NULL COMMENT '开始时间',
    `endtime` DATETIME NOT NULL COMMENT '结束时间',
    `merchant_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '商品状态：0 ONLINE(上架),1 OFFLINE（下架）',
    `manual_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '运营维护状态（0 Normal，1 Pause，2 uncheck，3 check）',
    `signature` VARCHAR(256) NOT NULL COMMENT '商品签名（8张相关表签名字段：除date,输入的绝大多数信息，用于重复判断）',
    `isselfopen` TINYINT(4) NOT NULL COMMENT '是否自营（1：自营，0：非自营）',
    `dataversion` BIGINT(20) NOT NULL COMMENT '数据版本（防止老的覆盖旧的）',
    `inactivetime` DATETIME NOT NULL COMMENT '失效时间（先保留，后续再看逻辑）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    `deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0：正常;1：已删除，会移出表',
    `seller_name` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '商家名称',
    `upc` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '条形码',
    PRIMARY KEY (`sku_innerid`),
    KEY `sku_hashkey` (`sku_hashkey`),
    KEY `skuid` (`skuid`),
    KEY `unique_key` (`unique_key`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品信息表';

DROP TABLE IF EXISTS `sku_pps_28`;
CREATE TABLE `sku_pps_28`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',    
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，用于表关联',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `regionid` INT(11) NOT NULL COMMENT '地址id，可以为仓库id，目前采用国标id，全国为0',
    `terminal` TINYINT(4) NOT NULL COMMENT '0:MOBILE,1:WEB(移动端还是web端等)',
    `promotion_info` VARCHAR(1024) NOT NULl DEFAULT '' COMMENT '促销信息',
    `promotion_price_machine` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：使用促销模型算出来的价格',
    `promotion_price_manual` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：人工修改的促销价格',
    `originalprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '原始价格',
    `discountprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '折扣价',
    `stock` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '库存值状态（1:有货，0：无货）',
    `post_pay` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否支持货到付款（默认不支持）', 
    `m_update_time` DATETIME NOT NULL COMMENT '商家的库存价格更新时间，用于比较时间进行更新',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku价格&库存表';

DROP TABLE IF EXISTS `sku_level_28`;
CREATE TABLE `sku_level_28`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `level` INT(11) NOT NULL DEFAULT '0' COMMENT '确定价格库存更新频率（0更新频率最低）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku更新频率表';

DROP TABLE IF EXISTS `sku_cspu_28`;
CREATE TABLE `sku_cspu_28`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `cspuid` BIGINT(20) NOT NULL COMMENT 'Cspu ID',
    `type` TINYINT(4)  NOT NULL COMMENT 'cspu来源:1:MANUAL_CLASSIFICATION：手工修改，2:MACHINE_CLASSIFICATION：机器学习',
    `confidence` FLOAT(5,2) NOT NULL DEFAULT '0'COMMENT '取值0-100，可信度',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`),
    KEY `cspuid` (`cspuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku到cspu映射';

DROP TABLE IF EXISTS `sku_comment_28`;
CREATE TABLE `sku_comment_28`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `reviewcount` INT(11) NOT NULL COMMENT '评论数',
    `goodcount` INT(11) NOT NULL COMMENT '好评数',
    `medcount` INT(11) NOT NULL COMMENT '中评数',
    `badcount` INT(11) NOT NULL COMMENT '差评数',
    `tags` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT 'json串，评论提取tag',
    `score` DOUBLE NOT NULL COMMENT '平均分',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku评论表';


DROP TABLE IF EXISTS `bcs_sku_image_28`;
CREATE TABLE `bcs_sku_image_28`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
	`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
	`image_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片第三方地址',
	`image_bcs_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片本地地址',
	`status` TINYINT(4) NOT NULL DEFAULT '3' COMMENT '图片下载状态：0未开始，1下载出错，2正在下载，3下载到本地',
	`addtime` DATETIME NOT NULL COMMENT '添加时间',
	`updatetime` DATETIME NOT NULL COMMENT '更新时间',
	`errormessage` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '错误信息',
	`width` INT(11) NOT NULL DEFAULT '0' COMMENT '图片宽度',
	`height` INT(11) NOT NULL DEFAULT '0' COMMENT '图片高度',
	`content_md5` CHAR(32) NOT NULL  DEFAULT '' COMMENT '内容MD5',
	`sequence` INT(11) NOT NULL DEFAULT '1' COMMENT '图片序号',
	`gips_image` VARCHAR(1024) NOT NULL COMMENT '缩略图json串，一堆url和尺寸',
	`type` TINYINT(4) NOT NULL COMMENT '图片类型：主图，俯视图、侧视45度图等',
	PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '图片信息表';
DROP TABLE IF EXISTS `sku_attribute_29`;
CREATE TABLE `sku_attribute_29`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT 'Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
`property_id` VARCHAR(2048) NOT NULL DEFAULT '' COMMENT '属性id:属性值id；(存策略归一化后的id)',
`property_values` VARCHAR(2048) NOT NULl DEFAULT '' COMMENT '属性1：属性值；属性2:属性值 (存商家原始属性对)',
`property_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '对property_id+property_values做hash，用于重复判断',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品基础属性表';

DROP TABLE IF EXISTS `sku_cdt_29`;
CREATE TABLE `sku_cdt_29`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`leafcategoryid` BIGINT(20) NOT NULL COMMENT '叶子类目ID',
`classificationtype` TINYINT(4) NOT NULL COMMENT '类目来源 0：手工修改，1：机器学习，其他状态',
`confidence` FLOAT(5,2) NOT NULL DEFAULT '0' COMMENT '取值0-100，可信度(目前没在用)',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品分类信息表';

DROP TABLE IF EXISTS `sku_description_29`;
CREATE TABLE `sku_description_29`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `sku_desc_ori` LONGBLOB NOT NULL COMMENT '商品描述的压缩信息',
    `pd_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '商品描述的压缩信息',
    `sku_desc` LONGBLOB NOT NULL COMMENT '提取出来的商品描述压缩信息',
    `pd_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0 Undo, 1 doing, 2 done',
    `area` INT(11) NOT NULL DEFAULT '0' COMMENT '总的图片区域',
    `word_count` INT(11) NOT NULL DEFAULT '0' COMMENT '总字数',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品描述详情表';

DROP TABLE IF EXISTS `sku_info_29`;
CREATE TABLE `sku_info_29`(
    `sku_innerid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '商品shard内部id，自增id',
    `sku_hashkey` BIGINT(20) NOT NULL COMMENT 'Hash（skuid）的结果',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id,Merchantid(数字转化为8位的明文)拼接outerid。',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `unique_key` CHAR(32) NOT NULL DEFAULT '' COMMENT 'outer_id 的md5值  用来建索引',
    `feedid` BIGINT(20) NOT NULL COMMENT '属于哪个feed，feed平台导入商品时用',
    `ucid` BIGINT(2) NOT NULL DEFAULT '0' COMMENT '商家在百度内部的统一ID，和merchantid的区别是，merchantid是产品内部自己定义的',
    `title` VARCHAR(1024) NOT NULL COMMENT '标题（name字段去除，和title一样的东西）',
    `subtitle` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '副标题',
    `url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(web)',
    `mobileurl` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(移动端)',
    `outerid` VARCHAR(128) NOT NULL COMMENT '商户系统中的商品id',
    `category_ori` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '原网站面包屑（和微购schema不同，这边没有拆开分多个字段）',
    `brand_ori` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '原始网站品牌（归一化后品牌从attribute表找）',
    `starttime` DATETIME NOT NULL COMMENT '开始时间',
    `endtime` DATETIME NOT NULL COMMENT '结束时间',
    `merchant_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '商品状态：0 ONLINE(上架),1 OFFLINE（下架）',
    `manual_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '运营维护状态（0 Normal，1 Pause，2 uncheck，3 check）',
    `signature` VARCHAR(256) NOT NULL COMMENT '商品签名（8张相关表签名字段：除date,输入的绝大多数信息，用于重复判断）',
    `isselfopen` TINYINT(4) NOT NULL COMMENT '是否自营（1：自营，0：非自营）',
    `dataversion` BIGINT(20) NOT NULL COMMENT '数据版本（防止老的覆盖旧的）',
    `inactivetime` DATETIME NOT NULL COMMENT '失效时间（先保留，后续再看逻辑）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    `deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0：正常;1：已删除，会移出表',
    `seller_name` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '商家名称',
    `upc` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '条形码',
    PRIMARY KEY (`sku_innerid`),
    KEY `sku_hashkey` (`sku_hashkey`),
    KEY `skuid` (`skuid`),
    KEY `unique_key` (`unique_key`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品信息表';

DROP TABLE IF EXISTS `sku_pps_29`;
CREATE TABLE `sku_pps_29`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',    
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，用于表关联',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `regionid` INT(11) NOT NULL COMMENT '地址id，可以为仓库id，目前采用国标id，全国为0',
    `terminal` TINYINT(4) NOT NULL COMMENT '0:MOBILE,1:WEB(移动端还是web端等)',
    `promotion_info` VARCHAR(1024) NOT NULl DEFAULT '' COMMENT '促销信息',
    `promotion_price_machine` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：使用促销模型算出来的价格',
    `promotion_price_manual` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：人工修改的促销价格',
    `originalprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '原始价格',
    `discountprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '折扣价',
    `stock` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '库存值状态（1:有货，0：无货）',
    `post_pay` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否支持货到付款（默认不支持）', 
    `m_update_time` DATETIME NOT NULL COMMENT '商家的库存价格更新时间，用于比较时间进行更新',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku价格&库存表';

DROP TABLE IF EXISTS `sku_level_29`;
CREATE TABLE `sku_level_29`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `level` INT(11) NOT NULL DEFAULT '0' COMMENT '确定价格库存更新频率（0更新频率最低）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku更新频率表';

DROP TABLE IF EXISTS `sku_cspu_29`;
CREATE TABLE `sku_cspu_29`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `cspuid` BIGINT(20) NOT NULL COMMENT 'Cspu ID',
    `type` TINYINT(4)  NOT NULL COMMENT 'cspu来源:1:MANUAL_CLASSIFICATION：手工修改，2:MACHINE_CLASSIFICATION：机器学习',
    `confidence` FLOAT(5,2) NOT NULL DEFAULT '0'COMMENT '取值0-100，可信度',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`),
    KEY `cspuid` (`cspuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku到cspu映射';

DROP TABLE IF EXISTS `sku_comment_29`;
CREATE TABLE `sku_comment_29`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `reviewcount` INT(11) NOT NULL COMMENT '评论数',
    `goodcount` INT(11) NOT NULL COMMENT '好评数',
    `medcount` INT(11) NOT NULL COMMENT '中评数',
    `badcount` INT(11) NOT NULL COMMENT '差评数',
    `tags` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT 'json串，评论提取tag',
    `score` DOUBLE NOT NULL COMMENT '平均分',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku评论表';


DROP TABLE IF EXISTS `bcs_sku_image_29`;
CREATE TABLE `bcs_sku_image_29`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
	`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
	`image_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片第三方地址',
	`image_bcs_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片本地地址',
	`status` TINYINT(4) NOT NULL DEFAULT '3' COMMENT '图片下载状态：0未开始，1下载出错，2正在下载，3下载到本地',
	`addtime` DATETIME NOT NULL COMMENT '添加时间',
	`updatetime` DATETIME NOT NULL COMMENT '更新时间',
	`errormessage` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '错误信息',
	`width` INT(11) NOT NULL DEFAULT '0' COMMENT '图片宽度',
	`height` INT(11) NOT NULL DEFAULT '0' COMMENT '图片高度',
	`content_md5` CHAR(32) NOT NULL  DEFAULT '' COMMENT '内容MD5',
	`sequence` INT(11) NOT NULL DEFAULT '1' COMMENT '图片序号',
	`gips_image` VARCHAR(1024) NOT NULL COMMENT '缩略图json串，一堆url和尺寸',
	`type` TINYINT(4) NOT NULL COMMENT '图片类型：主图，俯视图、侧视45度图等',
	PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '图片信息表';
DROP TABLE IF EXISTS `sku_attribute_30`;
CREATE TABLE `sku_attribute_30`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT 'Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
`property_id` VARCHAR(2048) NOT NULL DEFAULT '' COMMENT '属性id:属性值id；(存策略归一化后的id)',
`property_values` VARCHAR(2048) NOT NULl DEFAULT '' COMMENT '属性1：属性值；属性2:属性值 (存商家原始属性对)',
`property_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '对property_id+property_values做hash，用于重复判断',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品基础属性表';

DROP TABLE IF EXISTS `sku_cdt_30`;
CREATE TABLE `sku_cdt_30`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`leafcategoryid` BIGINT(20) NOT NULL COMMENT '叶子类目ID',
`classificationtype` TINYINT(4) NOT NULL COMMENT '类目来源 0：手工修改，1：机器学习，其他状态',
`confidence` FLOAT(5,2) NOT NULL DEFAULT '0' COMMENT '取值0-100，可信度(目前没在用)',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品分类信息表';

DROP TABLE IF EXISTS `sku_description_30`;
CREATE TABLE `sku_description_30`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `sku_desc_ori` LONGBLOB NOT NULL COMMENT '商品描述的压缩信息',
    `pd_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '商品描述的压缩信息',
    `sku_desc` LONGBLOB NOT NULL COMMENT '提取出来的商品描述压缩信息',
    `pd_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0 Undo, 1 doing, 2 done',
    `area` INT(11) NOT NULL DEFAULT '0' COMMENT '总的图片区域',
    `word_count` INT(11) NOT NULL DEFAULT '0' COMMENT '总字数',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品描述详情表';

DROP TABLE IF EXISTS `sku_info_30`;
CREATE TABLE `sku_info_30`(
    `sku_innerid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '商品shard内部id，自增id',
    `sku_hashkey` BIGINT(20) NOT NULL COMMENT 'Hash（skuid）的结果',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id,Merchantid(数字转化为8位的明文)拼接outerid。',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `unique_key` CHAR(32) NOT NULL DEFAULT '' COMMENT 'outer_id 的md5值  用来建索引',
    `feedid` BIGINT(20) NOT NULL COMMENT '属于哪个feed，feed平台导入商品时用',
    `ucid` BIGINT(2) NOT NULL DEFAULT '0' COMMENT '商家在百度内部的统一ID，和merchantid的区别是，merchantid是产品内部自己定义的',
    `title` VARCHAR(1024) NOT NULL COMMENT '标题（name字段去除，和title一样的东西）',
    `subtitle` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '副标题',
    `url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(web)',
    `mobileurl` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(移动端)',
    `outerid` VARCHAR(128) NOT NULL COMMENT '商户系统中的商品id',
    `category_ori` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '原网站面包屑（和微购schema不同，这边没有拆开分多个字段）',
    `brand_ori` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '原始网站品牌（归一化后品牌从attribute表找）',
    `starttime` DATETIME NOT NULL COMMENT '开始时间',
    `endtime` DATETIME NOT NULL COMMENT '结束时间',
    `merchant_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '商品状态：0 ONLINE(上架),1 OFFLINE（下架）',
    `manual_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '运营维护状态（0 Normal，1 Pause，2 uncheck，3 check）',
    `signature` VARCHAR(256) NOT NULL COMMENT '商品签名（8张相关表签名字段：除date,输入的绝大多数信息，用于重复判断）',
    `isselfopen` TINYINT(4) NOT NULL COMMENT '是否自营（1：自营，0：非自营）',
    `dataversion` BIGINT(20) NOT NULL COMMENT '数据版本（防止老的覆盖旧的）',
    `inactivetime` DATETIME NOT NULL COMMENT '失效时间（先保留，后续再看逻辑）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    `deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0：正常;1：已删除，会移出表',
    `seller_name` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '商家名称',
    `upc` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '条形码',
    PRIMARY KEY (`sku_innerid`),
    KEY `sku_hashkey` (`sku_hashkey`),
    KEY `skuid` (`skuid`),
    KEY `unique_key` (`unique_key`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品信息表';

DROP TABLE IF EXISTS `sku_pps_30`;
CREATE TABLE `sku_pps_30`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',    
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，用于表关联',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `regionid` INT(11) NOT NULL COMMENT '地址id，可以为仓库id，目前采用国标id，全国为0',
    `terminal` TINYINT(4) NOT NULL COMMENT '0:MOBILE,1:WEB(移动端还是web端等)',
    `promotion_info` VARCHAR(1024) NOT NULl DEFAULT '' COMMENT '促销信息',
    `promotion_price_machine` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：使用促销模型算出来的价格',
    `promotion_price_manual` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：人工修改的促销价格',
    `originalprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '原始价格',
    `discountprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '折扣价',
    `stock` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '库存值状态（1:有货，0：无货）',
    `post_pay` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否支持货到付款（默认不支持）', 
    `m_update_time` DATETIME NOT NULL COMMENT '商家的库存价格更新时间，用于比较时间进行更新',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku价格&库存表';

DROP TABLE IF EXISTS `sku_level_30`;
CREATE TABLE `sku_level_30`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `level` INT(11) NOT NULL DEFAULT '0' COMMENT '确定价格库存更新频率（0更新频率最低）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku更新频率表';

DROP TABLE IF EXISTS `sku_cspu_30`;
CREATE TABLE `sku_cspu_30`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `cspuid` BIGINT(20) NOT NULL COMMENT 'Cspu ID',
    `type` TINYINT(4)  NOT NULL COMMENT 'cspu来源:1:MANUAL_CLASSIFICATION：手工修改，2:MACHINE_CLASSIFICATION：机器学习',
    `confidence` FLOAT(5,2) NOT NULL DEFAULT '0'COMMENT '取值0-100，可信度',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`),
    KEY `cspuid` (`cspuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku到cspu映射';

DROP TABLE IF EXISTS `sku_comment_30`;
CREATE TABLE `sku_comment_30`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `reviewcount` INT(11) NOT NULL COMMENT '评论数',
    `goodcount` INT(11) NOT NULL COMMENT '好评数',
    `medcount` INT(11) NOT NULL COMMENT '中评数',
    `badcount` INT(11) NOT NULL COMMENT '差评数',
    `tags` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT 'json串，评论提取tag',
    `score` DOUBLE NOT NULL COMMENT '平均分',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku评论表';


DROP TABLE IF EXISTS `bcs_sku_image_30`;
CREATE TABLE `bcs_sku_image_30`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
	`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
	`image_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片第三方地址',
	`image_bcs_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片本地地址',
	`status` TINYINT(4) NOT NULL DEFAULT '3' COMMENT '图片下载状态：0未开始，1下载出错，2正在下载，3下载到本地',
	`addtime` DATETIME NOT NULL COMMENT '添加时间',
	`updatetime` DATETIME NOT NULL COMMENT '更新时间',
	`errormessage` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '错误信息',
	`width` INT(11) NOT NULL DEFAULT '0' COMMENT '图片宽度',
	`height` INT(11) NOT NULL DEFAULT '0' COMMENT '图片高度',
	`content_md5` CHAR(32) NOT NULL  DEFAULT '' COMMENT '内容MD5',
	`sequence` INT(11) NOT NULL DEFAULT '1' COMMENT '图片序号',
	`gips_image` VARCHAR(1024) NOT NULL COMMENT '缩略图json串，一堆url和尺寸',
	`type` TINYINT(4) NOT NULL COMMENT '图片类型：主图，俯视图、侧视45度图等',
	PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '图片信息表';
DROP TABLE IF EXISTS `sku_attribute_31`;
CREATE TABLE `sku_attribute_31`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT 'Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
`property_id` VARCHAR(2048) NOT NULL DEFAULT '' COMMENT '属性id:属性值id；(存策略归一化后的id)',
`property_values` VARCHAR(2048) NOT NULl DEFAULT '' COMMENT '属性1：属性值；属性2:属性值 (存商家原始属性对)',
`property_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '对property_id+property_values做hash，用于重复判断',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品基础属性表';

DROP TABLE IF EXISTS `sku_cdt_31`;
CREATE TABLE `sku_cdt_31`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`leafcategoryid` BIGINT(20) NOT NULL COMMENT '叶子类目ID',
`classificationtype` TINYINT(4) NOT NULL COMMENT '类目来源 0：手工修改，1：机器学习，其他状态',
`confidence` FLOAT(5,2) NOT NULL DEFAULT '0' COMMENT '取值0-100，可信度(目前没在用)',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品分类信息表';

DROP TABLE IF EXISTS `sku_description_31`;
CREATE TABLE `sku_description_31`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `sku_desc_ori` LONGBLOB NOT NULL COMMENT '商品描述的压缩信息',
    `pd_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '商品描述的压缩信息',
    `sku_desc` LONGBLOB NOT NULL COMMENT '提取出来的商品描述压缩信息',
    `pd_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0 Undo, 1 doing, 2 done',
    `area` INT(11) NOT NULL DEFAULT '0' COMMENT '总的图片区域',
    `word_count` INT(11) NOT NULL DEFAULT '0' COMMENT '总字数',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品描述详情表';

DROP TABLE IF EXISTS `sku_info_31`;
CREATE TABLE `sku_info_31`(
    `sku_innerid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '商品shard内部id，自增id',
    `sku_hashkey` BIGINT(20) NOT NULL COMMENT 'Hash（skuid）的结果',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id,Merchantid(数字转化为8位的明文)拼接outerid。',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `unique_key` CHAR(32) NOT NULL DEFAULT '' COMMENT 'outer_id 的md5值  用来建索引',
    `feedid` BIGINT(20) NOT NULL COMMENT '属于哪个feed，feed平台导入商品时用',
    `ucid` BIGINT(2) NOT NULL DEFAULT '0' COMMENT '商家在百度内部的统一ID，和merchantid的区别是，merchantid是产品内部自己定义的',
    `title` VARCHAR(1024) NOT NULL COMMENT '标题（name字段去除，和title一样的东西）',
    `subtitle` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '副标题',
    `url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(web)',
    `mobileurl` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(移动端)',
    `outerid` VARCHAR(128) NOT NULL COMMENT '商户系统中的商品id',
    `category_ori` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '原网站面包屑（和微购schema不同，这边没有拆开分多个字段）',
    `brand_ori` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '原始网站品牌（归一化后品牌从attribute表找）',
    `starttime` DATETIME NOT NULL COMMENT '开始时间',
    `endtime` DATETIME NOT NULL COMMENT '结束时间',
    `merchant_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '商品状态：0 ONLINE(上架),1 OFFLINE（下架）',
    `manual_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '运营维护状态（0 Normal，1 Pause，2 uncheck，3 check）',
    `signature` VARCHAR(256) NOT NULL COMMENT '商品签名（8张相关表签名字段：除date,输入的绝大多数信息，用于重复判断）',
    `isselfopen` TINYINT(4) NOT NULL COMMENT '是否自营（1：自营，0：非自营）',
    `dataversion` BIGINT(20) NOT NULL COMMENT '数据版本（防止老的覆盖旧的）',
    `inactivetime` DATETIME NOT NULL COMMENT '失效时间（先保留，后续再看逻辑）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    `deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0：正常;1：已删除，会移出表',
    `seller_name` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '商家名称',
    `upc` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '条形码',
    PRIMARY KEY (`sku_innerid`),
    KEY `sku_hashkey` (`sku_hashkey`),
    KEY `skuid` (`skuid`),
    KEY `unique_key` (`unique_key`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品信息表';

DROP TABLE IF EXISTS `sku_pps_31`;
CREATE TABLE `sku_pps_31`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',    
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，用于表关联',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `regionid` INT(11) NOT NULL COMMENT '地址id，可以为仓库id，目前采用国标id，全国为0',
    `terminal` TINYINT(4) NOT NULL COMMENT '0:MOBILE,1:WEB(移动端还是web端等)',
    `promotion_info` VARCHAR(1024) NOT NULl DEFAULT '' COMMENT '促销信息',
    `promotion_price_machine` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：使用促销模型算出来的价格',
    `promotion_price_manual` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：人工修改的促销价格',
    `originalprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '原始价格',
    `discountprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '折扣价',
    `stock` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '库存值状态（1:有货，0：无货）',
    `post_pay` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否支持货到付款（默认不支持）', 
    `m_update_time` DATETIME NOT NULL COMMENT '商家的库存价格更新时间，用于比较时间进行更新',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku价格&库存表';

DROP TABLE IF EXISTS `sku_level_31`;
CREATE TABLE `sku_level_31`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `level` INT(11) NOT NULL DEFAULT '0' COMMENT '确定价格库存更新频率（0更新频率最低）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku更新频率表';

DROP TABLE IF EXISTS `sku_cspu_31`;
CREATE TABLE `sku_cspu_31`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `cspuid` BIGINT(20) NOT NULL COMMENT 'Cspu ID',
    `type` TINYINT(4)  NOT NULL COMMENT 'cspu来源:1:MANUAL_CLASSIFICATION：手工修改，2:MACHINE_CLASSIFICATION：机器学习',
    `confidence` FLOAT(5,2) NOT NULL DEFAULT '0'COMMENT '取值0-100，可信度',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`),
    KEY `cspuid` (`cspuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku到cspu映射';

DROP TABLE IF EXISTS `sku_comment_31`;
CREATE TABLE `sku_comment_31`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `reviewcount` INT(11) NOT NULL COMMENT '评论数',
    `goodcount` INT(11) NOT NULL COMMENT '好评数',
    `medcount` INT(11) NOT NULL COMMENT '中评数',
    `badcount` INT(11) NOT NULL COMMENT '差评数',
    `tags` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT 'json串，评论提取tag',
    `score` DOUBLE NOT NULL COMMENT '平均分',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku评论表';


DROP TABLE IF EXISTS `bcs_sku_image_31`;
CREATE TABLE `bcs_sku_image_31`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
	`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
	`image_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片第三方地址',
	`image_bcs_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片本地地址',
	`status` TINYINT(4) NOT NULL DEFAULT '3' COMMENT '图片下载状态：0未开始，1下载出错，2正在下载，3下载到本地',
	`addtime` DATETIME NOT NULL COMMENT '添加时间',
	`updatetime` DATETIME NOT NULL COMMENT '更新时间',
	`errormessage` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '错误信息',
	`width` INT(11) NOT NULL DEFAULT '0' COMMENT '图片宽度',
	`height` INT(11) NOT NULL DEFAULT '0' COMMENT '图片高度',
	`content_md5` CHAR(32) NOT NULL  DEFAULT '' COMMENT '内容MD5',
	`sequence` INT(11) NOT NULL DEFAULT '1' COMMENT '图片序号',
	`gips_image` VARCHAR(1024) NOT NULL COMMENT '缩略图json串，一堆url和尺寸',
	`type` TINYINT(4) NOT NULL COMMENT '图片类型：主图，俯视图、侧视45度图等',
	PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '图片信息表';
DROP TABLE IF EXISTS `sku_attribute_32`;
CREATE TABLE `sku_attribute_32`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT 'Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
`property_id` VARCHAR(2048) NOT NULL DEFAULT '' COMMENT '属性id:属性值id；(存策略归一化后的id)',
`property_values` VARCHAR(2048) NOT NULl DEFAULT '' COMMENT '属性1：属性值；属性2:属性值 (存商家原始属性对)',
`property_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '对property_id+property_values做hash，用于重复判断',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品基础属性表';

DROP TABLE IF EXISTS `sku_cdt_32`;
CREATE TABLE `sku_cdt_32`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`leafcategoryid` BIGINT(20) NOT NULL COMMENT '叶子类目ID',
`classificationtype` TINYINT(4) NOT NULL COMMENT '类目来源 0：手工修改，1：机器学习，其他状态',
`confidence` FLOAT(5,2) NOT NULL DEFAULT '0' COMMENT '取值0-100，可信度(目前没在用)',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品分类信息表';

DROP TABLE IF EXISTS `sku_description_32`;
CREATE TABLE `sku_description_32`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `sku_desc_ori` LONGBLOB NOT NULL COMMENT '商品描述的压缩信息',
    `pd_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '商品描述的压缩信息',
    `sku_desc` LONGBLOB NOT NULL COMMENT '提取出来的商品描述压缩信息',
    `pd_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0 Undo, 1 doing, 2 done',
    `area` INT(11) NOT NULL DEFAULT '0' COMMENT '总的图片区域',
    `word_count` INT(11) NOT NULL DEFAULT '0' COMMENT '总字数',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品描述详情表';

DROP TABLE IF EXISTS `sku_info_32`;
CREATE TABLE `sku_info_32`(
    `sku_innerid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '商品shard内部id，自增id',
    `sku_hashkey` BIGINT(20) NOT NULL COMMENT 'Hash（skuid）的结果',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id,Merchantid(数字转化为8位的明文)拼接outerid。',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `unique_key` CHAR(32) NOT NULL DEFAULT '' COMMENT 'outer_id 的md5值  用来建索引',
    `feedid` BIGINT(20) NOT NULL COMMENT '属于哪个feed，feed平台导入商品时用',
    `ucid` BIGINT(2) NOT NULL DEFAULT '0' COMMENT '商家在百度内部的统一ID，和merchantid的区别是，merchantid是产品内部自己定义的',
    `title` VARCHAR(1024) NOT NULL COMMENT '标题（name字段去除，和title一样的东西）',
    `subtitle` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '副标题',
    `url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(web)',
    `mobileurl` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(移动端)',
    `outerid` VARCHAR(128) NOT NULL COMMENT '商户系统中的商品id',
    `category_ori` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '原网站面包屑（和微购schema不同，这边没有拆开分多个字段）',
    `brand_ori` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '原始网站品牌（归一化后品牌从attribute表找）',
    `starttime` DATETIME NOT NULL COMMENT '开始时间',
    `endtime` DATETIME NOT NULL COMMENT '结束时间',
    `merchant_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '商品状态：0 ONLINE(上架),1 OFFLINE（下架）',
    `manual_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '运营维护状态（0 Normal，1 Pause，2 uncheck，3 check）',
    `signature` VARCHAR(256) NOT NULL COMMENT '商品签名（8张相关表签名字段：除date,输入的绝大多数信息，用于重复判断）',
    `isselfopen` TINYINT(4) NOT NULL COMMENT '是否自营（1：自营，0：非自营）',
    `dataversion` BIGINT(20) NOT NULL COMMENT '数据版本（防止老的覆盖旧的）',
    `inactivetime` DATETIME NOT NULL COMMENT '失效时间（先保留，后续再看逻辑）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    `deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0：正常;1：已删除，会移出表',
    `seller_name` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '商家名称',
    `upc` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '条形码',
    PRIMARY KEY (`sku_innerid`),
    KEY `sku_hashkey` (`sku_hashkey`),
    KEY `skuid` (`skuid`),
    KEY `unique_key` (`unique_key`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品信息表';

DROP TABLE IF EXISTS `sku_pps_32`;
CREATE TABLE `sku_pps_32`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',    
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，用于表关联',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `regionid` INT(11) NOT NULL COMMENT '地址id，可以为仓库id，目前采用国标id，全国为0',
    `terminal` TINYINT(4) NOT NULL COMMENT '0:MOBILE,1:WEB(移动端还是web端等)',
    `promotion_info` VARCHAR(1024) NOT NULl DEFAULT '' COMMENT '促销信息',
    `promotion_price_machine` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：使用促销模型算出来的价格',
    `promotion_price_manual` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：人工修改的促销价格',
    `originalprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '原始价格',
    `discountprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '折扣价',
    `stock` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '库存值状态（1:有货，0：无货）',
    `post_pay` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否支持货到付款（默认不支持）', 
    `m_update_time` DATETIME NOT NULL COMMENT '商家的库存价格更新时间，用于比较时间进行更新',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku价格&库存表';

DROP TABLE IF EXISTS `sku_level_32`;
CREATE TABLE `sku_level_32`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `level` INT(11) NOT NULL DEFAULT '0' COMMENT '确定价格库存更新频率（0更新频率最低）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku更新频率表';

DROP TABLE IF EXISTS `sku_cspu_32`;
CREATE TABLE `sku_cspu_32`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `cspuid` BIGINT(20) NOT NULL COMMENT 'Cspu ID',
    `type` TINYINT(4)  NOT NULL COMMENT 'cspu来源:1:MANUAL_CLASSIFICATION：手工修改，2:MACHINE_CLASSIFICATION：机器学习',
    `confidence` FLOAT(5,2) NOT NULL DEFAULT '0'COMMENT '取值0-100，可信度',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`),
    KEY `cspuid` (`cspuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku到cspu映射';

DROP TABLE IF EXISTS `sku_comment_32`;
CREATE TABLE `sku_comment_32`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `reviewcount` INT(11) NOT NULL COMMENT '评论数',
    `goodcount` INT(11) NOT NULL COMMENT '好评数',
    `medcount` INT(11) NOT NULL COMMENT '中评数',
    `badcount` INT(11) NOT NULL COMMENT '差评数',
    `tags` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT 'json串，评论提取tag',
    `score` DOUBLE NOT NULL COMMENT '平均分',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku评论表';


DROP TABLE IF EXISTS `bcs_sku_image_32`;
CREATE TABLE `bcs_sku_image_32`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
	`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
	`image_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片第三方地址',
	`image_bcs_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片本地地址',
	`status` TINYINT(4) NOT NULL DEFAULT '3' COMMENT '图片下载状态：0未开始，1下载出错，2正在下载，3下载到本地',
	`addtime` DATETIME NOT NULL COMMENT '添加时间',
	`updatetime` DATETIME NOT NULL COMMENT '更新时间',
	`errormessage` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '错误信息',
	`width` INT(11) NOT NULL DEFAULT '0' COMMENT '图片宽度',
	`height` INT(11) NOT NULL DEFAULT '0' COMMENT '图片高度',
	`content_md5` CHAR(32) NOT NULL  DEFAULT '' COMMENT '内容MD5',
	`sequence` INT(11) NOT NULL DEFAULT '1' COMMENT '图片序号',
	`gips_image` VARCHAR(1024) NOT NULL COMMENT '缩略图json串，一堆url和尺寸',
	`type` TINYINT(4) NOT NULL COMMENT '图片类型：主图，俯视图、侧视45度图等',
	PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '图片信息表';
DROP TABLE IF EXISTS `sku_attribute_33`;
CREATE TABLE `sku_attribute_33`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT 'Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
`property_id` VARCHAR(2048) NOT NULL DEFAULT '' COMMENT '属性id:属性值id；(存策略归一化后的id)',
`property_values` VARCHAR(2048) NOT NULl DEFAULT '' COMMENT '属性1：属性值；属性2:属性值 (存商家原始属性对)',
`property_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '对property_id+property_values做hash，用于重复判断',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品基础属性表';

DROP TABLE IF EXISTS `sku_cdt_33`;
CREATE TABLE `sku_cdt_33`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`leafcategoryid` BIGINT(20) NOT NULL COMMENT '叶子类目ID',
`classificationtype` TINYINT(4) NOT NULL COMMENT '类目来源 0：手工修改，1：机器学习，其他状态',
`confidence` FLOAT(5,2) NOT NULL DEFAULT '0' COMMENT '取值0-100，可信度(目前没在用)',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品分类信息表';

DROP TABLE IF EXISTS `sku_description_33`;
CREATE TABLE `sku_description_33`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `sku_desc_ori` LONGBLOB NOT NULL COMMENT '商品描述的压缩信息',
    `pd_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '商品描述的压缩信息',
    `sku_desc` LONGBLOB NOT NULL COMMENT '提取出来的商品描述压缩信息',
    `pd_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0 Undo, 1 doing, 2 done',
    `area` INT(11) NOT NULL DEFAULT '0' COMMENT '总的图片区域',
    `word_count` INT(11) NOT NULL DEFAULT '0' COMMENT '总字数',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品描述详情表';

DROP TABLE IF EXISTS `sku_info_33`;
CREATE TABLE `sku_info_33`(
    `sku_innerid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '商品shard内部id，自增id',
    `sku_hashkey` BIGINT(20) NOT NULL COMMENT 'Hash（skuid）的结果',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id,Merchantid(数字转化为8位的明文)拼接outerid。',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `unique_key` CHAR(32) NOT NULL DEFAULT '' COMMENT 'outer_id 的md5值  用来建索引',
    `feedid` BIGINT(20) NOT NULL COMMENT '属于哪个feed，feed平台导入商品时用',
    `ucid` BIGINT(2) NOT NULL DEFAULT '0' COMMENT '商家在百度内部的统一ID，和merchantid的区别是，merchantid是产品内部自己定义的',
    `title` VARCHAR(1024) NOT NULL COMMENT '标题（name字段去除，和title一样的东西）',
    `subtitle` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '副标题',
    `url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(web)',
    `mobileurl` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(移动端)',
    `outerid` VARCHAR(128) NOT NULL COMMENT '商户系统中的商品id',
    `category_ori` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '原网站面包屑（和微购schema不同，这边没有拆开分多个字段）',
    `brand_ori` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '原始网站品牌（归一化后品牌从attribute表找）',
    `starttime` DATETIME NOT NULL COMMENT '开始时间',
    `endtime` DATETIME NOT NULL COMMENT '结束时间',
    `merchant_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '商品状态：0 ONLINE(上架),1 OFFLINE（下架）',
    `manual_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '运营维护状态（0 Normal，1 Pause，2 uncheck，3 check）',
    `signature` VARCHAR(256) NOT NULL COMMENT '商品签名（8张相关表签名字段：除date,输入的绝大多数信息，用于重复判断）',
    `isselfopen` TINYINT(4) NOT NULL COMMENT '是否自营（1：自营，0：非自营）',
    `dataversion` BIGINT(20) NOT NULL COMMENT '数据版本（防止老的覆盖旧的）',
    `inactivetime` DATETIME NOT NULL COMMENT '失效时间（先保留，后续再看逻辑）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    `deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0：正常;1：已删除，会移出表',
    `seller_name` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '商家名称',
    `upc` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '条形码',
    PRIMARY KEY (`sku_innerid`),
    KEY `sku_hashkey` (`sku_hashkey`),
    KEY `skuid` (`skuid`),
    KEY `unique_key` (`unique_key`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品信息表';

DROP TABLE IF EXISTS `sku_pps_33`;
CREATE TABLE `sku_pps_33`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',    
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，用于表关联',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `regionid` INT(11) NOT NULL COMMENT '地址id，可以为仓库id，目前采用国标id，全国为0',
    `terminal` TINYINT(4) NOT NULL COMMENT '0:MOBILE,1:WEB(移动端还是web端等)',
    `promotion_info` VARCHAR(1024) NOT NULl DEFAULT '' COMMENT '促销信息',
    `promotion_price_machine` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：使用促销模型算出来的价格',
    `promotion_price_manual` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：人工修改的促销价格',
    `originalprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '原始价格',
    `discountprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '折扣价',
    `stock` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '库存值状态（1:有货，0：无货）',
    `post_pay` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否支持货到付款（默认不支持）', 
    `m_update_time` DATETIME NOT NULL COMMENT '商家的库存价格更新时间，用于比较时间进行更新',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku价格&库存表';

DROP TABLE IF EXISTS `sku_level_33`;
CREATE TABLE `sku_level_33`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `level` INT(11) NOT NULL DEFAULT '0' COMMENT '确定价格库存更新频率（0更新频率最低）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku更新频率表';

DROP TABLE IF EXISTS `sku_cspu_33`;
CREATE TABLE `sku_cspu_33`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `cspuid` BIGINT(20) NOT NULL COMMENT 'Cspu ID',
    `type` TINYINT(4)  NOT NULL COMMENT 'cspu来源:1:MANUAL_CLASSIFICATION：手工修改，2:MACHINE_CLASSIFICATION：机器学习',
    `confidence` FLOAT(5,2) NOT NULL DEFAULT '0'COMMENT '取值0-100，可信度',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`),
    KEY `cspuid` (`cspuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku到cspu映射';

DROP TABLE IF EXISTS `sku_comment_33`;
CREATE TABLE `sku_comment_33`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `reviewcount` INT(11) NOT NULL COMMENT '评论数',
    `goodcount` INT(11) NOT NULL COMMENT '好评数',
    `medcount` INT(11) NOT NULL COMMENT '中评数',
    `badcount` INT(11) NOT NULL COMMENT '差评数',
    `tags` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT 'json串，评论提取tag',
    `score` DOUBLE NOT NULL COMMENT '平均分',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku评论表';


DROP TABLE IF EXISTS `bcs_sku_image_33`;
CREATE TABLE `bcs_sku_image_33`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
	`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
	`image_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片第三方地址',
	`image_bcs_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片本地地址',
	`status` TINYINT(4) NOT NULL DEFAULT '3' COMMENT '图片下载状态：0未开始，1下载出错，2正在下载，3下载到本地',
	`addtime` DATETIME NOT NULL COMMENT '添加时间',
	`updatetime` DATETIME NOT NULL COMMENT '更新时间',
	`errormessage` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '错误信息',
	`width` INT(11) NOT NULL DEFAULT '0' COMMENT '图片宽度',
	`height` INT(11) NOT NULL DEFAULT '0' COMMENT '图片高度',
	`content_md5` CHAR(32) NOT NULL  DEFAULT '' COMMENT '内容MD5',
	`sequence` INT(11) NOT NULL DEFAULT '1' COMMENT '图片序号',
	`gips_image` VARCHAR(1024) NOT NULL COMMENT '缩略图json串，一堆url和尺寸',
	`type` TINYINT(4) NOT NULL COMMENT '图片类型：主图，俯视图、侧视45度图等',
	PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '图片信息表';
DROP TABLE IF EXISTS `sku_attribute_34`;
CREATE TABLE `sku_attribute_34`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT 'Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
`property_id` VARCHAR(2048) NOT NULL DEFAULT '' COMMENT '属性id:属性值id；(存策略归一化后的id)',
`property_values` VARCHAR(2048) NOT NULl DEFAULT '' COMMENT '属性1：属性值；属性2:属性值 (存商家原始属性对)',
`property_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '对property_id+property_values做hash，用于重复判断',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品基础属性表';

DROP TABLE IF EXISTS `sku_cdt_34`;
CREATE TABLE `sku_cdt_34`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`leafcategoryid` BIGINT(20) NOT NULL COMMENT '叶子类目ID',
`classificationtype` TINYINT(4) NOT NULL COMMENT '类目来源 0：手工修改，1：机器学习，其他状态',
`confidence` FLOAT(5,2) NOT NULL DEFAULT '0' COMMENT '取值0-100，可信度(目前没在用)',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品分类信息表';

DROP TABLE IF EXISTS `sku_description_34`;
CREATE TABLE `sku_description_34`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `sku_desc_ori` LONGBLOB NOT NULL COMMENT '商品描述的压缩信息',
    `pd_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '商品描述的压缩信息',
    `sku_desc` LONGBLOB NOT NULL COMMENT '提取出来的商品描述压缩信息',
    `pd_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0 Undo, 1 doing, 2 done',
    `area` INT(11) NOT NULL DEFAULT '0' COMMENT '总的图片区域',
    `word_count` INT(11) NOT NULL DEFAULT '0' COMMENT '总字数',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品描述详情表';

DROP TABLE IF EXISTS `sku_info_34`;
CREATE TABLE `sku_info_34`(
    `sku_innerid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '商品shard内部id，自增id',
    `sku_hashkey` BIGINT(20) NOT NULL COMMENT 'Hash（skuid）的结果',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id,Merchantid(数字转化为8位的明文)拼接outerid。',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `unique_key` CHAR(32) NOT NULL DEFAULT '' COMMENT 'outer_id 的md5值  用来建索引',
    `feedid` BIGINT(20) NOT NULL COMMENT '属于哪个feed，feed平台导入商品时用',
    `ucid` BIGINT(2) NOT NULL DEFAULT '0' COMMENT '商家在百度内部的统一ID，和merchantid的区别是，merchantid是产品内部自己定义的',
    `title` VARCHAR(1024) NOT NULL COMMENT '标题（name字段去除，和title一样的东西）',
    `subtitle` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '副标题',
    `url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(web)',
    `mobileurl` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(移动端)',
    `outerid` VARCHAR(128) NOT NULL COMMENT '商户系统中的商品id',
    `category_ori` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '原网站面包屑（和微购schema不同，这边没有拆开分多个字段）',
    `brand_ori` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '原始网站品牌（归一化后品牌从attribute表找）',
    `starttime` DATETIME NOT NULL COMMENT '开始时间',
    `endtime` DATETIME NOT NULL COMMENT '结束时间',
    `merchant_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '商品状态：0 ONLINE(上架),1 OFFLINE（下架）',
    `manual_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '运营维护状态（0 Normal，1 Pause，2 uncheck，3 check）',
    `signature` VARCHAR(256) NOT NULL COMMENT '商品签名（8张相关表签名字段：除date,输入的绝大多数信息，用于重复判断）',
    `isselfopen` TINYINT(4) NOT NULL COMMENT '是否自营（1：自营，0：非自营）',
    `dataversion` BIGINT(20) NOT NULL COMMENT '数据版本（防止老的覆盖旧的）',
    `inactivetime` DATETIME NOT NULL COMMENT '失效时间（先保留，后续再看逻辑）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    `deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0：正常;1：已删除，会移出表',
    `seller_name` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '商家名称',
    `upc` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '条形码',
    PRIMARY KEY (`sku_innerid`),
    KEY `sku_hashkey` (`sku_hashkey`),
    KEY `skuid` (`skuid`),
    KEY `unique_key` (`unique_key`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品信息表';

DROP TABLE IF EXISTS `sku_pps_34`;
CREATE TABLE `sku_pps_34`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',    
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，用于表关联',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `regionid` INT(11) NOT NULL COMMENT '地址id，可以为仓库id，目前采用国标id，全国为0',
    `terminal` TINYINT(4) NOT NULL COMMENT '0:MOBILE,1:WEB(移动端还是web端等)',
    `promotion_info` VARCHAR(1024) NOT NULl DEFAULT '' COMMENT '促销信息',
    `promotion_price_machine` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：使用促销模型算出来的价格',
    `promotion_price_manual` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：人工修改的促销价格',
    `originalprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '原始价格',
    `discountprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '折扣价',
    `stock` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '库存值状态（1:有货，0：无货）',
    `post_pay` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否支持货到付款（默认不支持）', 
    `m_update_time` DATETIME NOT NULL COMMENT '商家的库存价格更新时间，用于比较时间进行更新',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku价格&库存表';

DROP TABLE IF EXISTS `sku_level_34`;
CREATE TABLE `sku_level_34`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `level` INT(11) NOT NULL DEFAULT '0' COMMENT '确定价格库存更新频率（0更新频率最低）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku更新频率表';

DROP TABLE IF EXISTS `sku_cspu_34`;
CREATE TABLE `sku_cspu_34`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `cspuid` BIGINT(20) NOT NULL COMMENT 'Cspu ID',
    `type` TINYINT(4)  NOT NULL COMMENT 'cspu来源:1:MANUAL_CLASSIFICATION：手工修改，2:MACHINE_CLASSIFICATION：机器学习',
    `confidence` FLOAT(5,2) NOT NULL DEFAULT '0'COMMENT '取值0-100，可信度',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`),
    KEY `cspuid` (`cspuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku到cspu映射';

DROP TABLE IF EXISTS `sku_comment_34`;
CREATE TABLE `sku_comment_34`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `reviewcount` INT(11) NOT NULL COMMENT '评论数',
    `goodcount` INT(11) NOT NULL COMMENT '好评数',
    `medcount` INT(11) NOT NULL COMMENT '中评数',
    `badcount` INT(11) NOT NULL COMMENT '差评数',
    `tags` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT 'json串，评论提取tag',
    `score` DOUBLE NOT NULL COMMENT '平均分',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku评论表';


DROP TABLE IF EXISTS `bcs_sku_image_34`;
CREATE TABLE `bcs_sku_image_34`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
	`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
	`image_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片第三方地址',
	`image_bcs_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片本地地址',
	`status` TINYINT(4) NOT NULL DEFAULT '3' COMMENT '图片下载状态：0未开始，1下载出错，2正在下载，3下载到本地',
	`addtime` DATETIME NOT NULL COMMENT '添加时间',
	`updatetime` DATETIME NOT NULL COMMENT '更新时间',
	`errormessage` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '错误信息',
	`width` INT(11) NOT NULL DEFAULT '0' COMMENT '图片宽度',
	`height` INT(11) NOT NULL DEFAULT '0' COMMENT '图片高度',
	`content_md5` CHAR(32) NOT NULL  DEFAULT '' COMMENT '内容MD5',
	`sequence` INT(11) NOT NULL DEFAULT '1' COMMENT '图片序号',
	`gips_image` VARCHAR(1024) NOT NULL COMMENT '缩略图json串，一堆url和尺寸',
	`type` TINYINT(4) NOT NULL COMMENT '图片类型：主图，俯视图、侧视45度图等',
	PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '图片信息表';
DROP TABLE IF EXISTS `sku_attribute_35`;
CREATE TABLE `sku_attribute_35`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT 'Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
`property_id` VARCHAR(2048) NOT NULL DEFAULT '' COMMENT '属性id:属性值id；(存策略归一化后的id)',
`property_values` VARCHAR(2048) NOT NULl DEFAULT '' COMMENT '属性1：属性值；属性2:属性值 (存商家原始属性对)',
`property_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '对property_id+property_values做hash，用于重复判断',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品基础属性表';

DROP TABLE IF EXISTS `sku_cdt_35`;
CREATE TABLE `sku_cdt_35`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`leafcategoryid` BIGINT(20) NOT NULL COMMENT '叶子类目ID',
`classificationtype` TINYINT(4) NOT NULL COMMENT '类目来源 0：手工修改，1：机器学习，其他状态',
`confidence` FLOAT(5,2) NOT NULL DEFAULT '0' COMMENT '取值0-100，可信度(目前没在用)',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品分类信息表';

DROP TABLE IF EXISTS `sku_description_35`;
CREATE TABLE `sku_description_35`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `sku_desc_ori` LONGBLOB NOT NULL COMMENT '商品描述的压缩信息',
    `pd_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '商品描述的压缩信息',
    `sku_desc` LONGBLOB NOT NULL COMMENT '提取出来的商品描述压缩信息',
    `pd_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0 Undo, 1 doing, 2 done',
    `area` INT(11) NOT NULL DEFAULT '0' COMMENT '总的图片区域',
    `word_count` INT(11) NOT NULL DEFAULT '0' COMMENT '总字数',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品描述详情表';

DROP TABLE IF EXISTS `sku_info_35`;
CREATE TABLE `sku_info_35`(
    `sku_innerid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '商品shard内部id，自增id',
    `sku_hashkey` BIGINT(20) NOT NULL COMMENT 'Hash（skuid）的结果',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id,Merchantid(数字转化为8位的明文)拼接outerid。',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `unique_key` CHAR(32) NOT NULL DEFAULT '' COMMENT 'outer_id 的md5值  用来建索引',
    `feedid` BIGINT(20) NOT NULL COMMENT '属于哪个feed，feed平台导入商品时用',
    `ucid` BIGINT(2) NOT NULL DEFAULT '0' COMMENT '商家在百度内部的统一ID，和merchantid的区别是，merchantid是产品内部自己定义的',
    `title` VARCHAR(1024) NOT NULL COMMENT '标题（name字段去除，和title一样的东西）',
    `subtitle` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '副标题',
    `url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(web)',
    `mobileurl` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(移动端)',
    `outerid` VARCHAR(128) NOT NULL COMMENT '商户系统中的商品id',
    `category_ori` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '原网站面包屑（和微购schema不同，这边没有拆开分多个字段）',
    `brand_ori` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '原始网站品牌（归一化后品牌从attribute表找）',
    `starttime` DATETIME NOT NULL COMMENT '开始时间',
    `endtime` DATETIME NOT NULL COMMENT '结束时间',
    `merchant_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '商品状态：0 ONLINE(上架),1 OFFLINE（下架）',
    `manual_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '运营维护状态（0 Normal，1 Pause，2 uncheck，3 check）',
    `signature` VARCHAR(256) NOT NULL COMMENT '商品签名（8张相关表签名字段：除date,输入的绝大多数信息，用于重复判断）',
    `isselfopen` TINYINT(4) NOT NULL COMMENT '是否自营（1：自营，0：非自营）',
    `dataversion` BIGINT(20) NOT NULL COMMENT '数据版本（防止老的覆盖旧的）',
    `inactivetime` DATETIME NOT NULL COMMENT '失效时间（先保留，后续再看逻辑）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    `deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0：正常;1：已删除，会移出表',
    `seller_name` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '商家名称',
    `upc` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '条形码',
    PRIMARY KEY (`sku_innerid`),
    KEY `sku_hashkey` (`sku_hashkey`),
    KEY `skuid` (`skuid`),
    KEY `unique_key` (`unique_key`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品信息表';

DROP TABLE IF EXISTS `sku_pps_35`;
CREATE TABLE `sku_pps_35`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',    
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，用于表关联',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `regionid` INT(11) NOT NULL COMMENT '地址id，可以为仓库id，目前采用国标id，全国为0',
    `terminal` TINYINT(4) NOT NULL COMMENT '0:MOBILE,1:WEB(移动端还是web端等)',
    `promotion_info` VARCHAR(1024) NOT NULl DEFAULT '' COMMENT '促销信息',
    `promotion_price_machine` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：使用促销模型算出来的价格',
    `promotion_price_manual` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：人工修改的促销价格',
    `originalprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '原始价格',
    `discountprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '折扣价',
    `stock` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '库存值状态（1:有货，0：无货）',
    `post_pay` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否支持货到付款（默认不支持）', 
    `m_update_time` DATETIME NOT NULL COMMENT '商家的库存价格更新时间，用于比较时间进行更新',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku价格&库存表';

DROP TABLE IF EXISTS `sku_level_35`;
CREATE TABLE `sku_level_35`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `level` INT(11) NOT NULL DEFAULT '0' COMMENT '确定价格库存更新频率（0更新频率最低）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku更新频率表';

DROP TABLE IF EXISTS `sku_cspu_35`;
CREATE TABLE `sku_cspu_35`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `cspuid` BIGINT(20) NOT NULL COMMENT 'Cspu ID',
    `type` TINYINT(4)  NOT NULL COMMENT 'cspu来源:1:MANUAL_CLASSIFICATION：手工修改，2:MACHINE_CLASSIFICATION：机器学习',
    `confidence` FLOAT(5,2) NOT NULL DEFAULT '0'COMMENT '取值0-100，可信度',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`),
    KEY `cspuid` (`cspuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku到cspu映射';

DROP TABLE IF EXISTS `sku_comment_35`;
CREATE TABLE `sku_comment_35`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `reviewcount` INT(11) NOT NULL COMMENT '评论数',
    `goodcount` INT(11) NOT NULL COMMENT '好评数',
    `medcount` INT(11) NOT NULL COMMENT '中评数',
    `badcount` INT(11) NOT NULL COMMENT '差评数',
    `tags` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT 'json串，评论提取tag',
    `score` DOUBLE NOT NULL COMMENT '平均分',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku评论表';


DROP TABLE IF EXISTS `bcs_sku_image_35`;
CREATE TABLE `bcs_sku_image_35`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
	`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
	`image_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片第三方地址',
	`image_bcs_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片本地地址',
	`status` TINYINT(4) NOT NULL DEFAULT '3' COMMENT '图片下载状态：0未开始，1下载出错，2正在下载，3下载到本地',
	`addtime` DATETIME NOT NULL COMMENT '添加时间',
	`updatetime` DATETIME NOT NULL COMMENT '更新时间',
	`errormessage` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '错误信息',
	`width` INT(11) NOT NULL DEFAULT '0' COMMENT '图片宽度',
	`height` INT(11) NOT NULL DEFAULT '0' COMMENT '图片高度',
	`content_md5` CHAR(32) NOT NULL  DEFAULT '' COMMENT '内容MD5',
	`sequence` INT(11) NOT NULL DEFAULT '1' COMMENT '图片序号',
	`gips_image` VARCHAR(1024) NOT NULL COMMENT '缩略图json串，一堆url和尺寸',
	`type` TINYINT(4) NOT NULL COMMENT '图片类型：主图，俯视图、侧视45度图等',
	PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '图片信息表';
DROP TABLE IF EXISTS `sku_attribute_36`;
CREATE TABLE `sku_attribute_36`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT 'Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
`property_id` VARCHAR(2048) NOT NULL DEFAULT '' COMMENT '属性id:属性值id；(存策略归一化后的id)',
`property_values` VARCHAR(2048) NOT NULl DEFAULT '' COMMENT '属性1：属性值；属性2:属性值 (存商家原始属性对)',
`property_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '对property_id+property_values做hash，用于重复判断',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品基础属性表';

DROP TABLE IF EXISTS `sku_cdt_36`;
CREATE TABLE `sku_cdt_36`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`leafcategoryid` BIGINT(20) NOT NULL COMMENT '叶子类目ID',
`classificationtype` TINYINT(4) NOT NULL COMMENT '类目来源 0：手工修改，1：机器学习，其他状态',
`confidence` FLOAT(5,2) NOT NULL DEFAULT '0' COMMENT '取值0-100，可信度(目前没在用)',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品分类信息表';

DROP TABLE IF EXISTS `sku_description_36`;
CREATE TABLE `sku_description_36`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `sku_desc_ori` LONGBLOB NOT NULL COMMENT '商品描述的压缩信息',
    `pd_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '商品描述的压缩信息',
    `sku_desc` LONGBLOB NOT NULL COMMENT '提取出来的商品描述压缩信息',
    `pd_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0 Undo, 1 doing, 2 done',
    `area` INT(11) NOT NULL DEFAULT '0' COMMENT '总的图片区域',
    `word_count` INT(11) NOT NULL DEFAULT '0' COMMENT '总字数',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品描述详情表';

DROP TABLE IF EXISTS `sku_info_36`;
CREATE TABLE `sku_info_36`(
    `sku_innerid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '商品shard内部id，自增id',
    `sku_hashkey` BIGINT(20) NOT NULL COMMENT 'Hash（skuid）的结果',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id,Merchantid(数字转化为8位的明文)拼接outerid。',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `unique_key` CHAR(32) NOT NULL DEFAULT '' COMMENT 'outer_id 的md5值  用来建索引',
    `feedid` BIGINT(20) NOT NULL COMMENT '属于哪个feed，feed平台导入商品时用',
    `ucid` BIGINT(2) NOT NULL DEFAULT '0' COMMENT '商家在百度内部的统一ID，和merchantid的区别是，merchantid是产品内部自己定义的',
    `title` VARCHAR(1024) NOT NULL COMMENT '标题（name字段去除，和title一样的东西）',
    `subtitle` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '副标题',
    `url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(web)',
    `mobileurl` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(移动端)',
    `outerid` VARCHAR(128) NOT NULL COMMENT '商户系统中的商品id',
    `category_ori` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '原网站面包屑（和微购schema不同，这边没有拆开分多个字段）',
    `brand_ori` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '原始网站品牌（归一化后品牌从attribute表找）',
    `starttime` DATETIME NOT NULL COMMENT '开始时间',
    `endtime` DATETIME NOT NULL COMMENT '结束时间',
    `merchant_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '商品状态：0 ONLINE(上架),1 OFFLINE（下架）',
    `manual_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '运营维护状态（0 Normal，1 Pause，2 uncheck，3 check）',
    `signature` VARCHAR(256) NOT NULL COMMENT '商品签名（8张相关表签名字段：除date,输入的绝大多数信息，用于重复判断）',
    `isselfopen` TINYINT(4) NOT NULL COMMENT '是否自营（1：自营，0：非自营）',
    `dataversion` BIGINT(20) NOT NULL COMMENT '数据版本（防止老的覆盖旧的）',
    `inactivetime` DATETIME NOT NULL COMMENT '失效时间（先保留，后续再看逻辑）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    `deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0：正常;1：已删除，会移出表',
    `seller_name` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '商家名称',
    `upc` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '条形码',
    PRIMARY KEY (`sku_innerid`),
    KEY `sku_hashkey` (`sku_hashkey`),
    KEY `skuid` (`skuid`),
    KEY `unique_key` (`unique_key`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品信息表';

DROP TABLE IF EXISTS `sku_pps_36`;
CREATE TABLE `sku_pps_36`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',    
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，用于表关联',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `regionid` INT(11) NOT NULL COMMENT '地址id，可以为仓库id，目前采用国标id，全国为0',
    `terminal` TINYINT(4) NOT NULL COMMENT '0:MOBILE,1:WEB(移动端还是web端等)',
    `promotion_info` VARCHAR(1024) NOT NULl DEFAULT '' COMMENT '促销信息',
    `promotion_price_machine` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：使用促销模型算出来的价格',
    `promotion_price_manual` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：人工修改的促销价格',
    `originalprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '原始价格',
    `discountprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '折扣价',
    `stock` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '库存值状态（1:有货，0：无货）',
    `post_pay` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否支持货到付款（默认不支持）', 
    `m_update_time` DATETIME NOT NULL COMMENT '商家的库存价格更新时间，用于比较时间进行更新',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku价格&库存表';

DROP TABLE IF EXISTS `sku_level_36`;
CREATE TABLE `sku_level_36`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `level` INT(11) NOT NULL DEFAULT '0' COMMENT '确定价格库存更新频率（0更新频率最低）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku更新频率表';

DROP TABLE IF EXISTS `sku_cspu_36`;
CREATE TABLE `sku_cspu_36`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `cspuid` BIGINT(20) NOT NULL COMMENT 'Cspu ID',
    `type` TINYINT(4)  NOT NULL COMMENT 'cspu来源:1:MANUAL_CLASSIFICATION：手工修改，2:MACHINE_CLASSIFICATION：机器学习',
    `confidence` FLOAT(5,2) NOT NULL DEFAULT '0'COMMENT '取值0-100，可信度',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`),
    KEY `cspuid` (`cspuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku到cspu映射';

DROP TABLE IF EXISTS `sku_comment_36`;
CREATE TABLE `sku_comment_36`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `reviewcount` INT(11) NOT NULL COMMENT '评论数',
    `goodcount` INT(11) NOT NULL COMMENT '好评数',
    `medcount` INT(11) NOT NULL COMMENT '中评数',
    `badcount` INT(11) NOT NULL COMMENT '差评数',
    `tags` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT 'json串，评论提取tag',
    `score` DOUBLE NOT NULL COMMENT '平均分',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku评论表';


DROP TABLE IF EXISTS `bcs_sku_image_36`;
CREATE TABLE `bcs_sku_image_36`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
	`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
	`image_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片第三方地址',
	`image_bcs_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片本地地址',
	`status` TINYINT(4) NOT NULL DEFAULT '3' COMMENT '图片下载状态：0未开始，1下载出错，2正在下载，3下载到本地',
	`addtime` DATETIME NOT NULL COMMENT '添加时间',
	`updatetime` DATETIME NOT NULL COMMENT '更新时间',
	`errormessage` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '错误信息',
	`width` INT(11) NOT NULL DEFAULT '0' COMMENT '图片宽度',
	`height` INT(11) NOT NULL DEFAULT '0' COMMENT '图片高度',
	`content_md5` CHAR(32) NOT NULL  DEFAULT '' COMMENT '内容MD5',
	`sequence` INT(11) NOT NULL DEFAULT '1' COMMENT '图片序号',
	`gips_image` VARCHAR(1024) NOT NULL COMMENT '缩略图json串，一堆url和尺寸',
	`type` TINYINT(4) NOT NULL COMMENT '图片类型：主图，俯视图、侧视45度图等',
	PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '图片信息表';
DROP TABLE IF EXISTS `sku_attribute_37`;
CREATE TABLE `sku_attribute_37`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT 'Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
`property_id` VARCHAR(2048) NOT NULL DEFAULT '' COMMENT '属性id:属性值id；(存策略归一化后的id)',
`property_values` VARCHAR(2048) NOT NULl DEFAULT '' COMMENT '属性1：属性值；属性2:属性值 (存商家原始属性对)',
`property_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '对property_id+property_values做hash，用于重复判断',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品基础属性表';

DROP TABLE IF EXISTS `sku_cdt_37`;
CREATE TABLE `sku_cdt_37`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`leafcategoryid` BIGINT(20) NOT NULL COMMENT '叶子类目ID',
`classificationtype` TINYINT(4) NOT NULL COMMENT '类目来源 0：手工修改，1：机器学习，其他状态',
`confidence` FLOAT(5,2) NOT NULL DEFAULT '0' COMMENT '取值0-100，可信度(目前没在用)',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品分类信息表';

DROP TABLE IF EXISTS `sku_description_37`;
CREATE TABLE `sku_description_37`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `sku_desc_ori` LONGBLOB NOT NULL COMMENT '商品描述的压缩信息',
    `pd_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '商品描述的压缩信息',
    `sku_desc` LONGBLOB NOT NULL COMMENT '提取出来的商品描述压缩信息',
    `pd_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0 Undo, 1 doing, 2 done',
    `area` INT(11) NOT NULL DEFAULT '0' COMMENT '总的图片区域',
    `word_count` INT(11) NOT NULL DEFAULT '0' COMMENT '总字数',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品描述详情表';

DROP TABLE IF EXISTS `sku_info_37`;
CREATE TABLE `sku_info_37`(
    `sku_innerid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '商品shard内部id，自增id',
    `sku_hashkey` BIGINT(20) NOT NULL COMMENT 'Hash（skuid）的结果',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id,Merchantid(数字转化为8位的明文)拼接outerid。',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `unique_key` CHAR(32) NOT NULL DEFAULT '' COMMENT 'outer_id 的md5值  用来建索引',
    `feedid` BIGINT(20) NOT NULL COMMENT '属于哪个feed，feed平台导入商品时用',
    `ucid` BIGINT(2) NOT NULL DEFAULT '0' COMMENT '商家在百度内部的统一ID，和merchantid的区别是，merchantid是产品内部自己定义的',
    `title` VARCHAR(1024) NOT NULL COMMENT '标题（name字段去除，和title一样的东西）',
    `subtitle` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '副标题',
    `url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(web)',
    `mobileurl` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(移动端)',
    `outerid` VARCHAR(128) NOT NULL COMMENT '商户系统中的商品id',
    `category_ori` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '原网站面包屑（和微购schema不同，这边没有拆开分多个字段）',
    `brand_ori` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '原始网站品牌（归一化后品牌从attribute表找）',
    `starttime` DATETIME NOT NULL COMMENT '开始时间',
    `endtime` DATETIME NOT NULL COMMENT '结束时间',
    `merchant_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '商品状态：0 ONLINE(上架),1 OFFLINE（下架）',
    `manual_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '运营维护状态（0 Normal，1 Pause，2 uncheck，3 check）',
    `signature` VARCHAR(256) NOT NULL COMMENT '商品签名（8张相关表签名字段：除date,输入的绝大多数信息，用于重复判断）',
    `isselfopen` TINYINT(4) NOT NULL COMMENT '是否自营（1：自营，0：非自营）',
    `dataversion` BIGINT(20) NOT NULL COMMENT '数据版本（防止老的覆盖旧的）',
    `inactivetime` DATETIME NOT NULL COMMENT '失效时间（先保留，后续再看逻辑）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    `deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0：正常;1：已删除，会移出表',
    `seller_name` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '商家名称',
    `upc` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '条形码',
    PRIMARY KEY (`sku_innerid`),
    KEY `sku_hashkey` (`sku_hashkey`),
    KEY `skuid` (`skuid`),
    KEY `unique_key` (`unique_key`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品信息表';

DROP TABLE IF EXISTS `sku_pps_37`;
CREATE TABLE `sku_pps_37`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',    
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，用于表关联',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `regionid` INT(11) NOT NULL COMMENT '地址id，可以为仓库id，目前采用国标id，全国为0',
    `terminal` TINYINT(4) NOT NULL COMMENT '0:MOBILE,1:WEB(移动端还是web端等)',
    `promotion_info` VARCHAR(1024) NOT NULl DEFAULT '' COMMENT '促销信息',
    `promotion_price_machine` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：使用促销模型算出来的价格',
    `promotion_price_manual` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：人工修改的促销价格',
    `originalprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '原始价格',
    `discountprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '折扣价',
    `stock` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '库存值状态（1:有货，0：无货）',
    `post_pay` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否支持货到付款（默认不支持）', 
    `m_update_time` DATETIME NOT NULL COMMENT '商家的库存价格更新时间，用于比较时间进行更新',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku价格&库存表';

DROP TABLE IF EXISTS `sku_level_37`;
CREATE TABLE `sku_level_37`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `level` INT(11) NOT NULL DEFAULT '0' COMMENT '确定价格库存更新频率（0更新频率最低）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku更新频率表';

DROP TABLE IF EXISTS `sku_cspu_37`;
CREATE TABLE `sku_cspu_37`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `cspuid` BIGINT(20) NOT NULL COMMENT 'Cspu ID',
    `type` TINYINT(4)  NOT NULL COMMENT 'cspu来源:1:MANUAL_CLASSIFICATION：手工修改，2:MACHINE_CLASSIFICATION：机器学习',
    `confidence` FLOAT(5,2) NOT NULL DEFAULT '0'COMMENT '取值0-100，可信度',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`),
    KEY `cspuid` (`cspuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku到cspu映射';

DROP TABLE IF EXISTS `sku_comment_37`;
CREATE TABLE `sku_comment_37`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `reviewcount` INT(11) NOT NULL COMMENT '评论数',
    `goodcount` INT(11) NOT NULL COMMENT '好评数',
    `medcount` INT(11) NOT NULL COMMENT '中评数',
    `badcount` INT(11) NOT NULL COMMENT '差评数',
    `tags` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT 'json串，评论提取tag',
    `score` DOUBLE NOT NULL COMMENT '平均分',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku评论表';


DROP TABLE IF EXISTS `bcs_sku_image_37`;
CREATE TABLE `bcs_sku_image_37`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
	`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
	`image_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片第三方地址',
	`image_bcs_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片本地地址',
	`status` TINYINT(4) NOT NULL DEFAULT '3' COMMENT '图片下载状态：0未开始，1下载出错，2正在下载，3下载到本地',
	`addtime` DATETIME NOT NULL COMMENT '添加时间',
	`updatetime` DATETIME NOT NULL COMMENT '更新时间',
	`errormessage` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '错误信息',
	`width` INT(11) NOT NULL DEFAULT '0' COMMENT '图片宽度',
	`height` INT(11) NOT NULL DEFAULT '0' COMMENT '图片高度',
	`content_md5` CHAR(32) NOT NULL  DEFAULT '' COMMENT '内容MD5',
	`sequence` INT(11) NOT NULL DEFAULT '1' COMMENT '图片序号',
	`gips_image` VARCHAR(1024) NOT NULL COMMENT '缩略图json串，一堆url和尺寸',
	`type` TINYINT(4) NOT NULL COMMENT '图片类型：主图，俯视图、侧视45度图等',
	PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '图片信息表';
DROP TABLE IF EXISTS `sku_attribute_38`;
CREATE TABLE `sku_attribute_38`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT 'Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
`property_id` VARCHAR(2048) NOT NULL DEFAULT '' COMMENT '属性id:属性值id；(存策略归一化后的id)',
`property_values` VARCHAR(2048) NOT NULl DEFAULT '' COMMENT '属性1：属性值；属性2:属性值 (存商家原始属性对)',
`property_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '对property_id+property_values做hash，用于重复判断',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品基础属性表';

DROP TABLE IF EXISTS `sku_cdt_38`;
CREATE TABLE `sku_cdt_38`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`leafcategoryid` BIGINT(20) NOT NULL COMMENT '叶子类目ID',
`classificationtype` TINYINT(4) NOT NULL COMMENT '类目来源 0：手工修改，1：机器学习，其他状态',
`confidence` FLOAT(5,2) NOT NULL DEFAULT '0' COMMENT '取值0-100，可信度(目前没在用)',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品分类信息表';

DROP TABLE IF EXISTS `sku_description_38`;
CREATE TABLE `sku_description_38`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `sku_desc_ori` LONGBLOB NOT NULL COMMENT '商品描述的压缩信息',
    `pd_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '商品描述的压缩信息',
    `sku_desc` LONGBLOB NOT NULL COMMENT '提取出来的商品描述压缩信息',
    `pd_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0 Undo, 1 doing, 2 done',
    `area` INT(11) NOT NULL DEFAULT '0' COMMENT '总的图片区域',
    `word_count` INT(11) NOT NULL DEFAULT '0' COMMENT '总字数',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品描述详情表';

DROP TABLE IF EXISTS `sku_info_38`;
CREATE TABLE `sku_info_38`(
    `sku_innerid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '商品shard内部id，自增id',
    `sku_hashkey` BIGINT(20) NOT NULL COMMENT 'Hash（skuid）的结果',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id,Merchantid(数字转化为8位的明文)拼接outerid。',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `unique_key` CHAR(32) NOT NULL DEFAULT '' COMMENT 'outer_id 的md5值  用来建索引',
    `feedid` BIGINT(20) NOT NULL COMMENT '属于哪个feed，feed平台导入商品时用',
    `ucid` BIGINT(2) NOT NULL DEFAULT '0' COMMENT '商家在百度内部的统一ID，和merchantid的区别是，merchantid是产品内部自己定义的',
    `title` VARCHAR(1024) NOT NULL COMMENT '标题（name字段去除，和title一样的东西）',
    `subtitle` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '副标题',
    `url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(web)',
    `mobileurl` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(移动端)',
    `outerid` VARCHAR(128) NOT NULL COMMENT '商户系统中的商品id',
    `category_ori` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '原网站面包屑（和微购schema不同，这边没有拆开分多个字段）',
    `brand_ori` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '原始网站品牌（归一化后品牌从attribute表找）',
    `starttime` DATETIME NOT NULL COMMENT '开始时间',
    `endtime` DATETIME NOT NULL COMMENT '结束时间',
    `merchant_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '商品状态：0 ONLINE(上架),1 OFFLINE（下架）',
    `manual_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '运营维护状态（0 Normal，1 Pause，2 uncheck，3 check）',
    `signature` VARCHAR(256) NOT NULL COMMENT '商品签名（8张相关表签名字段：除date,输入的绝大多数信息，用于重复判断）',
    `isselfopen` TINYINT(4) NOT NULL COMMENT '是否自营（1：自营，0：非自营）',
    `dataversion` BIGINT(20) NOT NULL COMMENT '数据版本（防止老的覆盖旧的）',
    `inactivetime` DATETIME NOT NULL COMMENT '失效时间（先保留，后续再看逻辑）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    `deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0：正常;1：已删除，会移出表',
    `seller_name` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '商家名称',
    `upc` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '条形码',
    PRIMARY KEY (`sku_innerid`),
    KEY `sku_hashkey` (`sku_hashkey`),
    KEY `skuid` (`skuid`),
    KEY `unique_key` (`unique_key`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品信息表';

DROP TABLE IF EXISTS `sku_pps_38`;
CREATE TABLE `sku_pps_38`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',    
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，用于表关联',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `regionid` INT(11) NOT NULL COMMENT '地址id，可以为仓库id，目前采用国标id，全国为0',
    `terminal` TINYINT(4) NOT NULL COMMENT '0:MOBILE,1:WEB(移动端还是web端等)',
    `promotion_info` VARCHAR(1024) NOT NULl DEFAULT '' COMMENT '促销信息',
    `promotion_price_machine` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：使用促销模型算出来的价格',
    `promotion_price_manual` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：人工修改的促销价格',
    `originalprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '原始价格',
    `discountprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '折扣价',
    `stock` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '库存值状态（1:有货，0：无货）',
    `post_pay` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否支持货到付款（默认不支持）', 
    `m_update_time` DATETIME NOT NULL COMMENT '商家的库存价格更新时间，用于比较时间进行更新',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku价格&库存表';

DROP TABLE IF EXISTS `sku_level_38`;
CREATE TABLE `sku_level_38`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `level` INT(11) NOT NULL DEFAULT '0' COMMENT '确定价格库存更新频率（0更新频率最低）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku更新频率表';

DROP TABLE IF EXISTS `sku_cspu_38`;
CREATE TABLE `sku_cspu_38`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `cspuid` BIGINT(20) NOT NULL COMMENT 'Cspu ID',
    `type` TINYINT(4)  NOT NULL COMMENT 'cspu来源:1:MANUAL_CLASSIFICATION：手工修改，2:MACHINE_CLASSIFICATION：机器学习',
    `confidence` FLOAT(5,2) NOT NULL DEFAULT '0'COMMENT '取值0-100，可信度',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`),
    KEY `cspuid` (`cspuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku到cspu映射';

DROP TABLE IF EXISTS `sku_comment_38`;
CREATE TABLE `sku_comment_38`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `reviewcount` INT(11) NOT NULL COMMENT '评论数',
    `goodcount` INT(11) NOT NULL COMMENT '好评数',
    `medcount` INT(11) NOT NULL COMMENT '中评数',
    `badcount` INT(11) NOT NULL COMMENT '差评数',
    `tags` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT 'json串，评论提取tag',
    `score` DOUBLE NOT NULL COMMENT '平均分',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku评论表';


DROP TABLE IF EXISTS `bcs_sku_image_38`;
CREATE TABLE `bcs_sku_image_38`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
	`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
	`image_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片第三方地址',
	`image_bcs_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片本地地址',
	`status` TINYINT(4) NOT NULL DEFAULT '3' COMMENT '图片下载状态：0未开始，1下载出错，2正在下载，3下载到本地',
	`addtime` DATETIME NOT NULL COMMENT '添加时间',
	`updatetime` DATETIME NOT NULL COMMENT '更新时间',
	`errormessage` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '错误信息',
	`width` INT(11) NOT NULL DEFAULT '0' COMMENT '图片宽度',
	`height` INT(11) NOT NULL DEFAULT '0' COMMENT '图片高度',
	`content_md5` CHAR(32) NOT NULL  DEFAULT '' COMMENT '内容MD5',
	`sequence` INT(11) NOT NULL DEFAULT '1' COMMENT '图片序号',
	`gips_image` VARCHAR(1024) NOT NULL COMMENT '缩略图json串，一堆url和尺寸',
	`type` TINYINT(4) NOT NULL COMMENT '图片类型：主图，俯视图、侧视45度图等',
	PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '图片信息表';
DROP TABLE IF EXISTS `sku_attribute_39`;
CREATE TABLE `sku_attribute_39`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT 'Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
`property_id` VARCHAR(2048) NOT NULL DEFAULT '' COMMENT '属性id:属性值id；(存策略归一化后的id)',
`property_values` VARCHAR(2048) NOT NULl DEFAULT '' COMMENT '属性1：属性值；属性2:属性值 (存商家原始属性对)',
`property_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '对property_id+property_values做hash，用于重复判断',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品基础属性表';

DROP TABLE IF EXISTS `sku_cdt_39`;
CREATE TABLE `sku_cdt_39`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`leafcategoryid` BIGINT(20) NOT NULL COMMENT '叶子类目ID',
`classificationtype` TINYINT(4) NOT NULL COMMENT '类目来源 0：手工修改，1：机器学习，其他状态',
`confidence` FLOAT(5,2) NOT NULL DEFAULT '0' COMMENT '取值0-100，可信度(目前没在用)',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品分类信息表';

DROP TABLE IF EXISTS `sku_description_39`;
CREATE TABLE `sku_description_39`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `sku_desc_ori` LONGBLOB NOT NULL COMMENT '商品描述的压缩信息',
    `pd_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '商品描述的压缩信息',
    `sku_desc` LONGBLOB NOT NULL COMMENT '提取出来的商品描述压缩信息',
    `pd_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0 Undo, 1 doing, 2 done',
    `area` INT(11) NOT NULL DEFAULT '0' COMMENT '总的图片区域',
    `word_count` INT(11) NOT NULL DEFAULT '0' COMMENT '总字数',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品描述详情表';

DROP TABLE IF EXISTS `sku_info_39`;
CREATE TABLE `sku_info_39`(
    `sku_innerid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '商品shard内部id，自增id',
    `sku_hashkey` BIGINT(20) NOT NULL COMMENT 'Hash（skuid）的结果',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id,Merchantid(数字转化为8位的明文)拼接outerid。',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `unique_key` CHAR(32) NOT NULL DEFAULT '' COMMENT 'outer_id 的md5值  用来建索引',
    `feedid` BIGINT(20) NOT NULL COMMENT '属于哪个feed，feed平台导入商品时用',
    `ucid` BIGINT(2) NOT NULL DEFAULT '0' COMMENT '商家在百度内部的统一ID，和merchantid的区别是，merchantid是产品内部自己定义的',
    `title` VARCHAR(1024) NOT NULL COMMENT '标题（name字段去除，和title一样的东西）',
    `subtitle` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '副标题',
    `url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(web)',
    `mobileurl` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(移动端)',
    `outerid` VARCHAR(128) NOT NULL COMMENT '商户系统中的商品id',
    `category_ori` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '原网站面包屑（和微购schema不同，这边没有拆开分多个字段）',
    `brand_ori` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '原始网站品牌（归一化后品牌从attribute表找）',
    `starttime` DATETIME NOT NULL COMMENT '开始时间',
    `endtime` DATETIME NOT NULL COMMENT '结束时间',
    `merchant_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '商品状态：0 ONLINE(上架),1 OFFLINE（下架）',
    `manual_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '运营维护状态（0 Normal，1 Pause，2 uncheck，3 check）',
    `signature` VARCHAR(256) NOT NULL COMMENT '商品签名（8张相关表签名字段：除date,输入的绝大多数信息，用于重复判断）',
    `isselfopen` TINYINT(4) NOT NULL COMMENT '是否自营（1：自营，0：非自营）',
    `dataversion` BIGINT(20) NOT NULL COMMENT '数据版本（防止老的覆盖旧的）',
    `inactivetime` DATETIME NOT NULL COMMENT '失效时间（先保留，后续再看逻辑）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    `deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0：正常;1：已删除，会移出表',
    `seller_name` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '商家名称',
    `upc` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '条形码',
    PRIMARY KEY (`sku_innerid`),
    KEY `sku_hashkey` (`sku_hashkey`),
    KEY `skuid` (`skuid`),
    KEY `unique_key` (`unique_key`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品信息表';

DROP TABLE IF EXISTS `sku_pps_39`;
CREATE TABLE `sku_pps_39`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',    
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，用于表关联',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `regionid` INT(11) NOT NULL COMMENT '地址id，可以为仓库id，目前采用国标id，全国为0',
    `terminal` TINYINT(4) NOT NULL COMMENT '0:MOBILE,1:WEB(移动端还是web端等)',
    `promotion_info` VARCHAR(1024) NOT NULl DEFAULT '' COMMENT '促销信息',
    `promotion_price_machine` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：使用促销模型算出来的价格',
    `promotion_price_manual` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：人工修改的促销价格',
    `originalprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '原始价格',
    `discountprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '折扣价',
    `stock` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '库存值状态（1:有货，0：无货）',
    `post_pay` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否支持货到付款（默认不支持）', 
    `m_update_time` DATETIME NOT NULL COMMENT '商家的库存价格更新时间，用于比较时间进行更新',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku价格&库存表';

DROP TABLE IF EXISTS `sku_level_39`;
CREATE TABLE `sku_level_39`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `level` INT(11) NOT NULL DEFAULT '0' COMMENT '确定价格库存更新频率（0更新频率最低）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku更新频率表';

DROP TABLE IF EXISTS `sku_cspu_39`;
CREATE TABLE `sku_cspu_39`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `cspuid` BIGINT(20) NOT NULL COMMENT 'Cspu ID',
    `type` TINYINT(4)  NOT NULL COMMENT 'cspu来源:1:MANUAL_CLASSIFICATION：手工修改，2:MACHINE_CLASSIFICATION：机器学习',
    `confidence` FLOAT(5,2) NOT NULL DEFAULT '0'COMMENT '取值0-100，可信度',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`),
    KEY `cspuid` (`cspuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku到cspu映射';

DROP TABLE IF EXISTS `sku_comment_39`;
CREATE TABLE `sku_comment_39`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `reviewcount` INT(11) NOT NULL COMMENT '评论数',
    `goodcount` INT(11) NOT NULL COMMENT '好评数',
    `medcount` INT(11) NOT NULL COMMENT '中评数',
    `badcount` INT(11) NOT NULL COMMENT '差评数',
    `tags` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT 'json串，评论提取tag',
    `score` DOUBLE NOT NULL COMMENT '平均分',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku评论表';


DROP TABLE IF EXISTS `bcs_sku_image_39`;
CREATE TABLE `bcs_sku_image_39`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
	`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
	`image_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片第三方地址',
	`image_bcs_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片本地地址',
	`status` TINYINT(4) NOT NULL DEFAULT '3' COMMENT '图片下载状态：0未开始，1下载出错，2正在下载，3下载到本地',
	`addtime` DATETIME NOT NULL COMMENT '添加时间',
	`updatetime` DATETIME NOT NULL COMMENT '更新时间',
	`errormessage` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '错误信息',
	`width` INT(11) NOT NULL DEFAULT '0' COMMENT '图片宽度',
	`height` INT(11) NOT NULL DEFAULT '0' COMMENT '图片高度',
	`content_md5` CHAR(32) NOT NULL  DEFAULT '' COMMENT '内容MD5',
	`sequence` INT(11) NOT NULL DEFAULT '1' COMMENT '图片序号',
	`gips_image` VARCHAR(1024) NOT NULL COMMENT '缩略图json串，一堆url和尺寸',
	`type` TINYINT(4) NOT NULL COMMENT '图片类型：主图，俯视图、侧视45度图等',
	PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '图片信息表';
DROP TABLE IF EXISTS `sku_attribute_40`;
CREATE TABLE `sku_attribute_40`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT 'Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
`property_id` VARCHAR(2048) NOT NULL DEFAULT '' COMMENT '属性id:属性值id；(存策略归一化后的id)',
`property_values` VARCHAR(2048) NOT NULl DEFAULT '' COMMENT '属性1：属性值；属性2:属性值 (存商家原始属性对)',
`property_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '对property_id+property_values做hash，用于重复判断',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品基础属性表';

DROP TABLE IF EXISTS `sku_cdt_40`;
CREATE TABLE `sku_cdt_40`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`leafcategoryid` BIGINT(20) NOT NULL COMMENT '叶子类目ID',
`classificationtype` TINYINT(4) NOT NULL COMMENT '类目来源 0：手工修改，1：机器学习，其他状态',
`confidence` FLOAT(5,2) NOT NULL DEFAULT '0' COMMENT '取值0-100，可信度(目前没在用)',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品分类信息表';

DROP TABLE IF EXISTS `sku_description_40`;
CREATE TABLE `sku_description_40`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `sku_desc_ori` LONGBLOB NOT NULL COMMENT '商品描述的压缩信息',
    `pd_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '商品描述的压缩信息',
    `sku_desc` LONGBLOB NOT NULL COMMENT '提取出来的商品描述压缩信息',
    `pd_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0 Undo, 1 doing, 2 done',
    `area` INT(11) NOT NULL DEFAULT '0' COMMENT '总的图片区域',
    `word_count` INT(11) NOT NULL DEFAULT '0' COMMENT '总字数',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品描述详情表';

DROP TABLE IF EXISTS `sku_info_40`;
CREATE TABLE `sku_info_40`(
    `sku_innerid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '商品shard内部id，自增id',
    `sku_hashkey` BIGINT(20) NOT NULL COMMENT 'Hash（skuid）的结果',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id,Merchantid(数字转化为8位的明文)拼接outerid。',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `unique_key` CHAR(32) NOT NULL DEFAULT '' COMMENT 'outer_id 的md5值  用来建索引',
    `feedid` BIGINT(20) NOT NULL COMMENT '属于哪个feed，feed平台导入商品时用',
    `ucid` BIGINT(2) NOT NULL DEFAULT '0' COMMENT '商家在百度内部的统一ID，和merchantid的区别是，merchantid是产品内部自己定义的',
    `title` VARCHAR(1024) NOT NULL COMMENT '标题（name字段去除，和title一样的东西）',
    `subtitle` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '副标题',
    `url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(web)',
    `mobileurl` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(移动端)',
    `outerid` VARCHAR(128) NOT NULL COMMENT '商户系统中的商品id',
    `category_ori` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '原网站面包屑（和微购schema不同，这边没有拆开分多个字段）',
    `brand_ori` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '原始网站品牌（归一化后品牌从attribute表找）',
    `starttime` DATETIME NOT NULL COMMENT '开始时间',
    `endtime` DATETIME NOT NULL COMMENT '结束时间',
    `merchant_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '商品状态：0 ONLINE(上架),1 OFFLINE（下架）',
    `manual_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '运营维护状态（0 Normal，1 Pause，2 uncheck，3 check）',
    `signature` VARCHAR(256) NOT NULL COMMENT '商品签名（8张相关表签名字段：除date,输入的绝大多数信息，用于重复判断）',
    `isselfopen` TINYINT(4) NOT NULL COMMENT '是否自营（1：自营，0：非自营）',
    `dataversion` BIGINT(20) NOT NULL COMMENT '数据版本（防止老的覆盖旧的）',
    `inactivetime` DATETIME NOT NULL COMMENT '失效时间（先保留，后续再看逻辑）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    `deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0：正常;1：已删除，会移出表',
    `seller_name` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '商家名称',
    `upc` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '条形码',
    PRIMARY KEY (`sku_innerid`),
    KEY `sku_hashkey` (`sku_hashkey`),
    KEY `skuid` (`skuid`),
    KEY `unique_key` (`unique_key`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品信息表';

DROP TABLE IF EXISTS `sku_pps_40`;
CREATE TABLE `sku_pps_40`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',    
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，用于表关联',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `regionid` INT(11) NOT NULL COMMENT '地址id，可以为仓库id，目前采用国标id，全国为0',
    `terminal` TINYINT(4) NOT NULL COMMENT '0:MOBILE,1:WEB(移动端还是web端等)',
    `promotion_info` VARCHAR(1024) NOT NULl DEFAULT '' COMMENT '促销信息',
    `promotion_price_machine` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：使用促销模型算出来的价格',
    `promotion_price_manual` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：人工修改的促销价格',
    `originalprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '原始价格',
    `discountprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '折扣价',
    `stock` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '库存值状态（1:有货，0：无货）',
    `post_pay` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否支持货到付款（默认不支持）', 
    `m_update_time` DATETIME NOT NULL COMMENT '商家的库存价格更新时间，用于比较时间进行更新',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku价格&库存表';

DROP TABLE IF EXISTS `sku_level_40`;
CREATE TABLE `sku_level_40`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `level` INT(11) NOT NULL DEFAULT '0' COMMENT '确定价格库存更新频率（0更新频率最低）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku更新频率表';

DROP TABLE IF EXISTS `sku_cspu_40`;
CREATE TABLE `sku_cspu_40`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `cspuid` BIGINT(20) NOT NULL COMMENT 'Cspu ID',
    `type` TINYINT(4)  NOT NULL COMMENT 'cspu来源:1:MANUAL_CLASSIFICATION：手工修改，2:MACHINE_CLASSIFICATION：机器学习',
    `confidence` FLOAT(5,2) NOT NULL DEFAULT '0'COMMENT '取值0-100，可信度',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`),
    KEY `cspuid` (`cspuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku到cspu映射';

DROP TABLE IF EXISTS `sku_comment_40`;
CREATE TABLE `sku_comment_40`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `reviewcount` INT(11) NOT NULL COMMENT '评论数',
    `goodcount` INT(11) NOT NULL COMMENT '好评数',
    `medcount` INT(11) NOT NULL COMMENT '中评数',
    `badcount` INT(11) NOT NULL COMMENT '差评数',
    `tags` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT 'json串，评论提取tag',
    `score` DOUBLE NOT NULL COMMENT '平均分',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku评论表';


DROP TABLE IF EXISTS `bcs_sku_image_40`;
CREATE TABLE `bcs_sku_image_40`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
	`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
	`image_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片第三方地址',
	`image_bcs_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片本地地址',
	`status` TINYINT(4) NOT NULL DEFAULT '3' COMMENT '图片下载状态：0未开始，1下载出错，2正在下载，3下载到本地',
	`addtime` DATETIME NOT NULL COMMENT '添加时间',
	`updatetime` DATETIME NOT NULL COMMENT '更新时间',
	`errormessage` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '错误信息',
	`width` INT(11) NOT NULL DEFAULT '0' COMMENT '图片宽度',
	`height` INT(11) NOT NULL DEFAULT '0' COMMENT '图片高度',
	`content_md5` CHAR(32) NOT NULL  DEFAULT '' COMMENT '内容MD5',
	`sequence` INT(11) NOT NULL DEFAULT '1' COMMENT '图片序号',
	`gips_image` VARCHAR(1024) NOT NULL COMMENT '缩略图json串，一堆url和尺寸',
	`type` TINYINT(4) NOT NULL COMMENT '图片类型：主图，俯视图、侧视45度图等',
	PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '图片信息表';
DROP TABLE IF EXISTS `sku_attribute_41`;
CREATE TABLE `sku_attribute_41`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT 'Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
`property_id` VARCHAR(2048) NOT NULL DEFAULT '' COMMENT '属性id:属性值id；(存策略归一化后的id)',
`property_values` VARCHAR(2048) NOT NULl DEFAULT '' COMMENT '属性1：属性值；属性2:属性值 (存商家原始属性对)',
`property_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '对property_id+property_values做hash，用于重复判断',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品基础属性表';

DROP TABLE IF EXISTS `sku_cdt_41`;
CREATE TABLE `sku_cdt_41`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`leafcategoryid` BIGINT(20) NOT NULL COMMENT '叶子类目ID',
`classificationtype` TINYINT(4) NOT NULL COMMENT '类目来源 0：手工修改，1：机器学习，其他状态',
`confidence` FLOAT(5,2) NOT NULL DEFAULT '0' COMMENT '取值0-100，可信度(目前没在用)',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品分类信息表';

DROP TABLE IF EXISTS `sku_description_41`;
CREATE TABLE `sku_description_41`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `sku_desc_ori` LONGBLOB NOT NULL COMMENT '商品描述的压缩信息',
    `pd_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '商品描述的压缩信息',
    `sku_desc` LONGBLOB NOT NULL COMMENT '提取出来的商品描述压缩信息',
    `pd_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0 Undo, 1 doing, 2 done',
    `area` INT(11) NOT NULL DEFAULT '0' COMMENT '总的图片区域',
    `word_count` INT(11) NOT NULL DEFAULT '0' COMMENT '总字数',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品描述详情表';

DROP TABLE IF EXISTS `sku_info_41`;
CREATE TABLE `sku_info_41`(
    `sku_innerid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '商品shard内部id，自增id',
    `sku_hashkey` BIGINT(20) NOT NULL COMMENT 'Hash（skuid）的结果',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id,Merchantid(数字转化为8位的明文)拼接outerid。',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `unique_key` CHAR(32) NOT NULL DEFAULT '' COMMENT 'outer_id 的md5值  用来建索引',
    `feedid` BIGINT(20) NOT NULL COMMENT '属于哪个feed，feed平台导入商品时用',
    `ucid` BIGINT(2) NOT NULL DEFAULT '0' COMMENT '商家在百度内部的统一ID，和merchantid的区别是，merchantid是产品内部自己定义的',
    `title` VARCHAR(1024) NOT NULL COMMENT '标题（name字段去除，和title一样的东西）',
    `subtitle` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '副标题',
    `url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(web)',
    `mobileurl` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(移动端)',
    `outerid` VARCHAR(128) NOT NULL COMMENT '商户系统中的商品id',
    `category_ori` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '原网站面包屑（和微购schema不同，这边没有拆开分多个字段）',
    `brand_ori` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '原始网站品牌（归一化后品牌从attribute表找）',
    `starttime` DATETIME NOT NULL COMMENT '开始时间',
    `endtime` DATETIME NOT NULL COMMENT '结束时间',
    `merchant_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '商品状态：0 ONLINE(上架),1 OFFLINE（下架）',
    `manual_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '运营维护状态（0 Normal，1 Pause，2 uncheck，3 check）',
    `signature` VARCHAR(256) NOT NULL COMMENT '商品签名（8张相关表签名字段：除date,输入的绝大多数信息，用于重复判断）',
    `isselfopen` TINYINT(4) NOT NULL COMMENT '是否自营（1：自营，0：非自营）',
    `dataversion` BIGINT(20) NOT NULL COMMENT '数据版本（防止老的覆盖旧的）',
    `inactivetime` DATETIME NOT NULL COMMENT '失效时间（先保留，后续再看逻辑）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    `deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0：正常;1：已删除，会移出表',
    `seller_name` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '商家名称',
    `upc` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '条形码',
    PRIMARY KEY (`sku_innerid`),
    KEY `sku_hashkey` (`sku_hashkey`),
    KEY `skuid` (`skuid`),
    KEY `unique_key` (`unique_key`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品信息表';

DROP TABLE IF EXISTS `sku_pps_41`;
CREATE TABLE `sku_pps_41`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',    
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，用于表关联',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `regionid` INT(11) NOT NULL COMMENT '地址id，可以为仓库id，目前采用国标id，全国为0',
    `terminal` TINYINT(4) NOT NULL COMMENT '0:MOBILE,1:WEB(移动端还是web端等)',
    `promotion_info` VARCHAR(1024) NOT NULl DEFAULT '' COMMENT '促销信息',
    `promotion_price_machine` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：使用促销模型算出来的价格',
    `promotion_price_manual` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：人工修改的促销价格',
    `originalprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '原始价格',
    `discountprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '折扣价',
    `stock` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '库存值状态（1:有货，0：无货）',
    `post_pay` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否支持货到付款（默认不支持）', 
    `m_update_time` DATETIME NOT NULL COMMENT '商家的库存价格更新时间，用于比较时间进行更新',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku价格&库存表';

DROP TABLE IF EXISTS `sku_level_41`;
CREATE TABLE `sku_level_41`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `level` INT(11) NOT NULL DEFAULT '0' COMMENT '确定价格库存更新频率（0更新频率最低）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku更新频率表';

DROP TABLE IF EXISTS `sku_cspu_41`;
CREATE TABLE `sku_cspu_41`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `cspuid` BIGINT(20) NOT NULL COMMENT 'Cspu ID',
    `type` TINYINT(4)  NOT NULL COMMENT 'cspu来源:1:MANUAL_CLASSIFICATION：手工修改，2:MACHINE_CLASSIFICATION：机器学习',
    `confidence` FLOAT(5,2) NOT NULL DEFAULT '0'COMMENT '取值0-100，可信度',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`),
    KEY `cspuid` (`cspuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku到cspu映射';

DROP TABLE IF EXISTS `sku_comment_41`;
CREATE TABLE `sku_comment_41`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `reviewcount` INT(11) NOT NULL COMMENT '评论数',
    `goodcount` INT(11) NOT NULL COMMENT '好评数',
    `medcount` INT(11) NOT NULL COMMENT '中评数',
    `badcount` INT(11) NOT NULL COMMENT '差评数',
    `tags` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT 'json串，评论提取tag',
    `score` DOUBLE NOT NULL COMMENT '平均分',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku评论表';


DROP TABLE IF EXISTS `bcs_sku_image_41`;
CREATE TABLE `bcs_sku_image_41`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
	`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
	`image_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片第三方地址',
	`image_bcs_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片本地地址',
	`status` TINYINT(4) NOT NULL DEFAULT '3' COMMENT '图片下载状态：0未开始，1下载出错，2正在下载，3下载到本地',
	`addtime` DATETIME NOT NULL COMMENT '添加时间',
	`updatetime` DATETIME NOT NULL COMMENT '更新时间',
	`errormessage` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '错误信息',
	`width` INT(11) NOT NULL DEFAULT '0' COMMENT '图片宽度',
	`height` INT(11) NOT NULL DEFAULT '0' COMMENT '图片高度',
	`content_md5` CHAR(32) NOT NULL  DEFAULT '' COMMENT '内容MD5',
	`sequence` INT(11) NOT NULL DEFAULT '1' COMMENT '图片序号',
	`gips_image` VARCHAR(1024) NOT NULL COMMENT '缩略图json串，一堆url和尺寸',
	`type` TINYINT(4) NOT NULL COMMENT '图片类型：主图，俯视图、侧视45度图等',
	PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '图片信息表';
DROP TABLE IF EXISTS `sku_attribute_42`;
CREATE TABLE `sku_attribute_42`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT 'Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
`property_id` VARCHAR(2048) NOT NULL DEFAULT '' COMMENT '属性id:属性值id；(存策略归一化后的id)',
`property_values` VARCHAR(2048) NOT NULl DEFAULT '' COMMENT '属性1：属性值；属性2:属性值 (存商家原始属性对)',
`property_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '对property_id+property_values做hash，用于重复判断',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品基础属性表';

DROP TABLE IF EXISTS `sku_cdt_42`;
CREATE TABLE `sku_cdt_42`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`leafcategoryid` BIGINT(20) NOT NULL COMMENT '叶子类目ID',
`classificationtype` TINYINT(4) NOT NULL COMMENT '类目来源 0：手工修改，1：机器学习，其他状态',
`confidence` FLOAT(5,2) NOT NULL DEFAULT '0' COMMENT '取值0-100，可信度(目前没在用)',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品分类信息表';

DROP TABLE IF EXISTS `sku_description_42`;
CREATE TABLE `sku_description_42`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `sku_desc_ori` LONGBLOB NOT NULL COMMENT '商品描述的压缩信息',
    `pd_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '商品描述的压缩信息',
    `sku_desc` LONGBLOB NOT NULL COMMENT '提取出来的商品描述压缩信息',
    `pd_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0 Undo, 1 doing, 2 done',
    `area` INT(11) NOT NULL DEFAULT '0' COMMENT '总的图片区域',
    `word_count` INT(11) NOT NULL DEFAULT '0' COMMENT '总字数',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品描述详情表';

DROP TABLE IF EXISTS `sku_info_42`;
CREATE TABLE `sku_info_42`(
    `sku_innerid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '商品shard内部id，自增id',
    `sku_hashkey` BIGINT(20) NOT NULL COMMENT 'Hash（skuid）的结果',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id,Merchantid(数字转化为8位的明文)拼接outerid。',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `unique_key` CHAR(32) NOT NULL DEFAULT '' COMMENT 'outer_id 的md5值  用来建索引',
    `feedid` BIGINT(20) NOT NULL COMMENT '属于哪个feed，feed平台导入商品时用',
    `ucid` BIGINT(2) NOT NULL DEFAULT '0' COMMENT '商家在百度内部的统一ID，和merchantid的区别是，merchantid是产品内部自己定义的',
    `title` VARCHAR(1024) NOT NULL COMMENT '标题（name字段去除，和title一样的东西）',
    `subtitle` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '副标题',
    `url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(web)',
    `mobileurl` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(移动端)',
    `outerid` VARCHAR(128) NOT NULL COMMENT '商户系统中的商品id',
    `category_ori` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '原网站面包屑（和微购schema不同，这边没有拆开分多个字段）',
    `brand_ori` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '原始网站品牌（归一化后品牌从attribute表找）',
    `starttime` DATETIME NOT NULL COMMENT '开始时间',
    `endtime` DATETIME NOT NULL COMMENT '结束时间',
    `merchant_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '商品状态：0 ONLINE(上架),1 OFFLINE（下架）',
    `manual_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '运营维护状态（0 Normal，1 Pause，2 uncheck，3 check）',
    `signature` VARCHAR(256) NOT NULL COMMENT '商品签名（8张相关表签名字段：除date,输入的绝大多数信息，用于重复判断）',
    `isselfopen` TINYINT(4) NOT NULL COMMENT '是否自营（1：自营，0：非自营）',
    `dataversion` BIGINT(20) NOT NULL COMMENT '数据版本（防止老的覆盖旧的）',
    `inactivetime` DATETIME NOT NULL COMMENT '失效时间（先保留，后续再看逻辑）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    `deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0：正常;1：已删除，会移出表',
    `seller_name` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '商家名称',
    `upc` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '条形码',
    PRIMARY KEY (`sku_innerid`),
    KEY `sku_hashkey` (`sku_hashkey`),
    KEY `skuid` (`skuid`),
    KEY `unique_key` (`unique_key`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品信息表';

DROP TABLE IF EXISTS `sku_pps_42`;
CREATE TABLE `sku_pps_42`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',    
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，用于表关联',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `regionid` INT(11) NOT NULL COMMENT '地址id，可以为仓库id，目前采用国标id，全国为0',
    `terminal` TINYINT(4) NOT NULL COMMENT '0:MOBILE,1:WEB(移动端还是web端等)',
    `promotion_info` VARCHAR(1024) NOT NULl DEFAULT '' COMMENT '促销信息',
    `promotion_price_machine` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：使用促销模型算出来的价格',
    `promotion_price_manual` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：人工修改的促销价格',
    `originalprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '原始价格',
    `discountprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '折扣价',
    `stock` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '库存值状态（1:有货，0：无货）',
    `post_pay` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否支持货到付款（默认不支持）', 
    `m_update_time` DATETIME NOT NULL COMMENT '商家的库存价格更新时间，用于比较时间进行更新',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku价格&库存表';

DROP TABLE IF EXISTS `sku_level_42`;
CREATE TABLE `sku_level_42`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `level` INT(11) NOT NULL DEFAULT '0' COMMENT '确定价格库存更新频率（0更新频率最低）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku更新频率表';

DROP TABLE IF EXISTS `sku_cspu_42`;
CREATE TABLE `sku_cspu_42`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `cspuid` BIGINT(20) NOT NULL COMMENT 'Cspu ID',
    `type` TINYINT(4)  NOT NULL COMMENT 'cspu来源:1:MANUAL_CLASSIFICATION：手工修改，2:MACHINE_CLASSIFICATION：机器学习',
    `confidence` FLOAT(5,2) NOT NULL DEFAULT '0'COMMENT '取值0-100，可信度',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`),
    KEY `cspuid` (`cspuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku到cspu映射';

DROP TABLE IF EXISTS `sku_comment_42`;
CREATE TABLE `sku_comment_42`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `reviewcount` INT(11) NOT NULL COMMENT '评论数',
    `goodcount` INT(11) NOT NULL COMMENT '好评数',
    `medcount` INT(11) NOT NULL COMMENT '中评数',
    `badcount` INT(11) NOT NULL COMMENT '差评数',
    `tags` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT 'json串，评论提取tag',
    `score` DOUBLE NOT NULL COMMENT '平均分',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku评论表';


DROP TABLE IF EXISTS `bcs_sku_image_42`;
CREATE TABLE `bcs_sku_image_42`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
	`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
	`image_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片第三方地址',
	`image_bcs_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片本地地址',
	`status` TINYINT(4) NOT NULL DEFAULT '3' COMMENT '图片下载状态：0未开始，1下载出错，2正在下载，3下载到本地',
	`addtime` DATETIME NOT NULL COMMENT '添加时间',
	`updatetime` DATETIME NOT NULL COMMENT '更新时间',
	`errormessage` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '错误信息',
	`width` INT(11) NOT NULL DEFAULT '0' COMMENT '图片宽度',
	`height` INT(11) NOT NULL DEFAULT '0' COMMENT '图片高度',
	`content_md5` CHAR(32) NOT NULL  DEFAULT '' COMMENT '内容MD5',
	`sequence` INT(11) NOT NULL DEFAULT '1' COMMENT '图片序号',
	`gips_image` VARCHAR(1024) NOT NULL COMMENT '缩略图json串，一堆url和尺寸',
	`type` TINYINT(4) NOT NULL COMMENT '图片类型：主图，俯视图、侧视45度图等',
	PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '图片信息表';
DROP TABLE IF EXISTS `sku_attribute_43`;
CREATE TABLE `sku_attribute_43`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT 'Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
`property_id` VARCHAR(2048) NOT NULL DEFAULT '' COMMENT '属性id:属性值id；(存策略归一化后的id)',
`property_values` VARCHAR(2048) NOT NULl DEFAULT '' COMMENT '属性1：属性值；属性2:属性值 (存商家原始属性对)',
`property_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '对property_id+property_values做hash，用于重复判断',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品基础属性表';

DROP TABLE IF EXISTS `sku_cdt_43`;
CREATE TABLE `sku_cdt_43`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`leafcategoryid` BIGINT(20) NOT NULL COMMENT '叶子类目ID',
`classificationtype` TINYINT(4) NOT NULL COMMENT '类目来源 0：手工修改，1：机器学习，其他状态',
`confidence` FLOAT(5,2) NOT NULL DEFAULT '0' COMMENT '取值0-100，可信度(目前没在用)',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品分类信息表';

DROP TABLE IF EXISTS `sku_description_43`;
CREATE TABLE `sku_description_43`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `sku_desc_ori` LONGBLOB NOT NULL COMMENT '商品描述的压缩信息',
    `pd_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '商品描述的压缩信息',
    `sku_desc` LONGBLOB NOT NULL COMMENT '提取出来的商品描述压缩信息',
    `pd_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0 Undo, 1 doing, 2 done',
    `area` INT(11) NOT NULL DEFAULT '0' COMMENT '总的图片区域',
    `word_count` INT(11) NOT NULL DEFAULT '0' COMMENT '总字数',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品描述详情表';

DROP TABLE IF EXISTS `sku_info_43`;
CREATE TABLE `sku_info_43`(
    `sku_innerid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '商品shard内部id，自增id',
    `sku_hashkey` BIGINT(20) NOT NULL COMMENT 'Hash（skuid）的结果',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id,Merchantid(数字转化为8位的明文)拼接outerid。',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `unique_key` CHAR(32) NOT NULL DEFAULT '' COMMENT 'outer_id 的md5值  用来建索引',
    `feedid` BIGINT(20) NOT NULL COMMENT '属于哪个feed，feed平台导入商品时用',
    `ucid` BIGINT(2) NOT NULL DEFAULT '0' COMMENT '商家在百度内部的统一ID，和merchantid的区别是，merchantid是产品内部自己定义的',
    `title` VARCHAR(1024) NOT NULL COMMENT '标题（name字段去除，和title一样的东西）',
    `subtitle` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '副标题',
    `url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(web)',
    `mobileurl` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(移动端)',
    `outerid` VARCHAR(128) NOT NULL COMMENT '商户系统中的商品id',
    `category_ori` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '原网站面包屑（和微购schema不同，这边没有拆开分多个字段）',
    `brand_ori` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '原始网站品牌（归一化后品牌从attribute表找）',
    `starttime` DATETIME NOT NULL COMMENT '开始时间',
    `endtime` DATETIME NOT NULL COMMENT '结束时间',
    `merchant_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '商品状态：0 ONLINE(上架),1 OFFLINE（下架）',
    `manual_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '运营维护状态（0 Normal，1 Pause，2 uncheck，3 check）',
    `signature` VARCHAR(256) NOT NULL COMMENT '商品签名（8张相关表签名字段：除date,输入的绝大多数信息，用于重复判断）',
    `isselfopen` TINYINT(4) NOT NULL COMMENT '是否自营（1：自营，0：非自营）',
    `dataversion` BIGINT(20) NOT NULL COMMENT '数据版本（防止老的覆盖旧的）',
    `inactivetime` DATETIME NOT NULL COMMENT '失效时间（先保留，后续再看逻辑）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    `deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0：正常;1：已删除，会移出表',
    `seller_name` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '商家名称',
    `upc` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '条形码',
    PRIMARY KEY (`sku_innerid`),
    KEY `sku_hashkey` (`sku_hashkey`),
    KEY `skuid` (`skuid`),
    KEY `unique_key` (`unique_key`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品信息表';

DROP TABLE IF EXISTS `sku_pps_43`;
CREATE TABLE `sku_pps_43`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',    
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，用于表关联',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `regionid` INT(11) NOT NULL COMMENT '地址id，可以为仓库id，目前采用国标id，全国为0',
    `terminal` TINYINT(4) NOT NULL COMMENT '0:MOBILE,1:WEB(移动端还是web端等)',
    `promotion_info` VARCHAR(1024) NOT NULl DEFAULT '' COMMENT '促销信息',
    `promotion_price_machine` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：使用促销模型算出来的价格',
    `promotion_price_manual` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：人工修改的促销价格',
    `originalprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '原始价格',
    `discountprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '折扣价',
    `stock` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '库存值状态（1:有货，0：无货）',
    `post_pay` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否支持货到付款（默认不支持）', 
    `m_update_time` DATETIME NOT NULL COMMENT '商家的库存价格更新时间，用于比较时间进行更新',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku价格&库存表';

DROP TABLE IF EXISTS `sku_level_43`;
CREATE TABLE `sku_level_43`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `level` INT(11) NOT NULL DEFAULT '0' COMMENT '确定价格库存更新频率（0更新频率最低）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku更新频率表';

DROP TABLE IF EXISTS `sku_cspu_43`;
CREATE TABLE `sku_cspu_43`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `cspuid` BIGINT(20) NOT NULL COMMENT 'Cspu ID',
    `type` TINYINT(4)  NOT NULL COMMENT 'cspu来源:1:MANUAL_CLASSIFICATION：手工修改，2:MACHINE_CLASSIFICATION：机器学习',
    `confidence` FLOAT(5,2) NOT NULL DEFAULT '0'COMMENT '取值0-100，可信度',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`),
    KEY `cspuid` (`cspuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku到cspu映射';

DROP TABLE IF EXISTS `sku_comment_43`;
CREATE TABLE `sku_comment_43`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `reviewcount` INT(11) NOT NULL COMMENT '评论数',
    `goodcount` INT(11) NOT NULL COMMENT '好评数',
    `medcount` INT(11) NOT NULL COMMENT '中评数',
    `badcount` INT(11) NOT NULL COMMENT '差评数',
    `tags` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT 'json串，评论提取tag',
    `score` DOUBLE NOT NULL COMMENT '平均分',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku评论表';


DROP TABLE IF EXISTS `bcs_sku_image_43`;
CREATE TABLE `bcs_sku_image_43`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
	`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
	`image_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片第三方地址',
	`image_bcs_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片本地地址',
	`status` TINYINT(4) NOT NULL DEFAULT '3' COMMENT '图片下载状态：0未开始，1下载出错，2正在下载，3下载到本地',
	`addtime` DATETIME NOT NULL COMMENT '添加时间',
	`updatetime` DATETIME NOT NULL COMMENT '更新时间',
	`errormessage` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '错误信息',
	`width` INT(11) NOT NULL DEFAULT '0' COMMENT '图片宽度',
	`height` INT(11) NOT NULL DEFAULT '0' COMMENT '图片高度',
	`content_md5` CHAR(32) NOT NULL  DEFAULT '' COMMENT '内容MD5',
	`sequence` INT(11) NOT NULL DEFAULT '1' COMMENT '图片序号',
	`gips_image` VARCHAR(1024) NOT NULL COMMENT '缩略图json串，一堆url和尺寸',
	`type` TINYINT(4) NOT NULL COMMENT '图片类型：主图，俯视图、侧视45度图等',
	PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '图片信息表';
DROP TABLE IF EXISTS `sku_attribute_44`;
CREATE TABLE `sku_attribute_44`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT 'Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
`property_id` VARCHAR(2048) NOT NULL DEFAULT '' COMMENT '属性id:属性值id；(存策略归一化后的id)',
`property_values` VARCHAR(2048) NOT NULl DEFAULT '' COMMENT '属性1：属性值；属性2:属性值 (存商家原始属性对)',
`property_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '对property_id+property_values做hash，用于重复判断',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品基础属性表';

DROP TABLE IF EXISTS `sku_cdt_44`;
CREATE TABLE `sku_cdt_44`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`leafcategoryid` BIGINT(20) NOT NULL COMMENT '叶子类目ID',
`classificationtype` TINYINT(4) NOT NULL COMMENT '类目来源 0：手工修改，1：机器学习，其他状态',
`confidence` FLOAT(5,2) NOT NULL DEFAULT '0' COMMENT '取值0-100，可信度(目前没在用)',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品分类信息表';

DROP TABLE IF EXISTS `sku_description_44`;
CREATE TABLE `sku_description_44`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `sku_desc_ori` LONGBLOB NOT NULL COMMENT '商品描述的压缩信息',
    `pd_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '商品描述的压缩信息',
    `sku_desc` LONGBLOB NOT NULL COMMENT '提取出来的商品描述压缩信息',
    `pd_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0 Undo, 1 doing, 2 done',
    `area` INT(11) NOT NULL DEFAULT '0' COMMENT '总的图片区域',
    `word_count` INT(11) NOT NULL DEFAULT '0' COMMENT '总字数',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品描述详情表';

DROP TABLE IF EXISTS `sku_info_44`;
CREATE TABLE `sku_info_44`(
    `sku_innerid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '商品shard内部id，自增id',
    `sku_hashkey` BIGINT(20) NOT NULL COMMENT 'Hash（skuid）的结果',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id,Merchantid(数字转化为8位的明文)拼接outerid。',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `unique_key` CHAR(32) NOT NULL DEFAULT '' COMMENT 'outer_id 的md5值  用来建索引',
    `feedid` BIGINT(20) NOT NULL COMMENT '属于哪个feed，feed平台导入商品时用',
    `ucid` BIGINT(2) NOT NULL DEFAULT '0' COMMENT '商家在百度内部的统一ID，和merchantid的区别是，merchantid是产品内部自己定义的',
    `title` VARCHAR(1024) NOT NULL COMMENT '标题（name字段去除，和title一样的东西）',
    `subtitle` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '副标题',
    `url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(web)',
    `mobileurl` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(移动端)',
    `outerid` VARCHAR(128) NOT NULL COMMENT '商户系统中的商品id',
    `category_ori` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '原网站面包屑（和微购schema不同，这边没有拆开分多个字段）',
    `brand_ori` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '原始网站品牌（归一化后品牌从attribute表找）',
    `starttime` DATETIME NOT NULL COMMENT '开始时间',
    `endtime` DATETIME NOT NULL COMMENT '结束时间',
    `merchant_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '商品状态：0 ONLINE(上架),1 OFFLINE（下架）',
    `manual_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '运营维护状态（0 Normal，1 Pause，2 uncheck，3 check）',
    `signature` VARCHAR(256) NOT NULL COMMENT '商品签名（8张相关表签名字段：除date,输入的绝大多数信息，用于重复判断）',
    `isselfopen` TINYINT(4) NOT NULL COMMENT '是否自营（1：自营，0：非自营）',
    `dataversion` BIGINT(20) NOT NULL COMMENT '数据版本（防止老的覆盖旧的）',
    `inactivetime` DATETIME NOT NULL COMMENT '失效时间（先保留，后续再看逻辑）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    `deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0：正常;1：已删除，会移出表',
    `seller_name` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '商家名称',
    `upc` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '条形码',
    PRIMARY KEY (`sku_innerid`),
    KEY `sku_hashkey` (`sku_hashkey`),
    KEY `skuid` (`skuid`),
    KEY `unique_key` (`unique_key`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品信息表';

DROP TABLE IF EXISTS `sku_pps_44`;
CREATE TABLE `sku_pps_44`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',    
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，用于表关联',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `regionid` INT(11) NOT NULL COMMENT '地址id，可以为仓库id，目前采用国标id，全国为0',
    `terminal` TINYINT(4) NOT NULL COMMENT '0:MOBILE,1:WEB(移动端还是web端等)',
    `promotion_info` VARCHAR(1024) NOT NULl DEFAULT '' COMMENT '促销信息',
    `promotion_price_machine` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：使用促销模型算出来的价格',
    `promotion_price_manual` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：人工修改的促销价格',
    `originalprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '原始价格',
    `discountprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '折扣价',
    `stock` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '库存值状态（1:有货，0：无货）',
    `post_pay` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否支持货到付款（默认不支持）', 
    `m_update_time` DATETIME NOT NULL COMMENT '商家的库存价格更新时间，用于比较时间进行更新',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku价格&库存表';

DROP TABLE IF EXISTS `sku_level_44`;
CREATE TABLE `sku_level_44`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `level` INT(11) NOT NULL DEFAULT '0' COMMENT '确定价格库存更新频率（0更新频率最低）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku更新频率表';

DROP TABLE IF EXISTS `sku_cspu_44`;
CREATE TABLE `sku_cspu_44`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `cspuid` BIGINT(20) NOT NULL COMMENT 'Cspu ID',
    `type` TINYINT(4)  NOT NULL COMMENT 'cspu来源:1:MANUAL_CLASSIFICATION：手工修改，2:MACHINE_CLASSIFICATION：机器学习',
    `confidence` FLOAT(5,2) NOT NULL DEFAULT '0'COMMENT '取值0-100，可信度',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`),
    KEY `cspuid` (`cspuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku到cspu映射';

DROP TABLE IF EXISTS `sku_comment_44`;
CREATE TABLE `sku_comment_44`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `reviewcount` INT(11) NOT NULL COMMENT '评论数',
    `goodcount` INT(11) NOT NULL COMMENT '好评数',
    `medcount` INT(11) NOT NULL COMMENT '中评数',
    `badcount` INT(11) NOT NULL COMMENT '差评数',
    `tags` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT 'json串，评论提取tag',
    `score` DOUBLE NOT NULL COMMENT '平均分',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku评论表';


DROP TABLE IF EXISTS `bcs_sku_image_44`;
CREATE TABLE `bcs_sku_image_44`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
	`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
	`image_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片第三方地址',
	`image_bcs_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片本地地址',
	`status` TINYINT(4) NOT NULL DEFAULT '3' COMMENT '图片下载状态：0未开始，1下载出错，2正在下载，3下载到本地',
	`addtime` DATETIME NOT NULL COMMENT '添加时间',
	`updatetime` DATETIME NOT NULL COMMENT '更新时间',
	`errormessage` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '错误信息',
	`width` INT(11) NOT NULL DEFAULT '0' COMMENT '图片宽度',
	`height` INT(11) NOT NULL DEFAULT '0' COMMENT '图片高度',
	`content_md5` CHAR(32) NOT NULL  DEFAULT '' COMMENT '内容MD5',
	`sequence` INT(11) NOT NULL DEFAULT '1' COMMENT '图片序号',
	`gips_image` VARCHAR(1024) NOT NULL COMMENT '缩略图json串，一堆url和尺寸',
	`type` TINYINT(4) NOT NULL COMMENT '图片类型：主图，俯视图、侧视45度图等',
	PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '图片信息表';
DROP TABLE IF EXISTS `sku_attribute_45`;
CREATE TABLE `sku_attribute_45`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT 'Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
`property_id` VARCHAR(2048) NOT NULL DEFAULT '' COMMENT '属性id:属性值id；(存策略归一化后的id)',
`property_values` VARCHAR(2048) NOT NULl DEFAULT '' COMMENT '属性1：属性值；属性2:属性值 (存商家原始属性对)',
`property_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '对property_id+property_values做hash，用于重复判断',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品基础属性表';

DROP TABLE IF EXISTS `sku_cdt_45`;
CREATE TABLE `sku_cdt_45`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`leafcategoryid` BIGINT(20) NOT NULL COMMENT '叶子类目ID',
`classificationtype` TINYINT(4) NOT NULL COMMENT '类目来源 0：手工修改，1：机器学习，其他状态',
`confidence` FLOAT(5,2) NOT NULL DEFAULT '0' COMMENT '取值0-100，可信度(目前没在用)',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品分类信息表';

DROP TABLE IF EXISTS `sku_description_45`;
CREATE TABLE `sku_description_45`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `sku_desc_ori` LONGBLOB NOT NULL COMMENT '商品描述的压缩信息',
    `pd_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '商品描述的压缩信息',
    `sku_desc` LONGBLOB NOT NULL COMMENT '提取出来的商品描述压缩信息',
    `pd_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0 Undo, 1 doing, 2 done',
    `area` INT(11) NOT NULL DEFAULT '0' COMMENT '总的图片区域',
    `word_count` INT(11) NOT NULL DEFAULT '0' COMMENT '总字数',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品描述详情表';

DROP TABLE IF EXISTS `sku_info_45`;
CREATE TABLE `sku_info_45`(
    `sku_innerid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '商品shard内部id，自增id',
    `sku_hashkey` BIGINT(20) NOT NULL COMMENT 'Hash（skuid）的结果',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id,Merchantid(数字转化为8位的明文)拼接outerid。',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `unique_key` CHAR(32) NOT NULL DEFAULT '' COMMENT 'outer_id 的md5值  用来建索引',
    `feedid` BIGINT(20) NOT NULL COMMENT '属于哪个feed，feed平台导入商品时用',
    `ucid` BIGINT(2) NOT NULL DEFAULT '0' COMMENT '商家在百度内部的统一ID，和merchantid的区别是，merchantid是产品内部自己定义的',
    `title` VARCHAR(1024) NOT NULL COMMENT '标题（name字段去除，和title一样的东西）',
    `subtitle` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '副标题',
    `url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(web)',
    `mobileurl` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(移动端)',
    `outerid` VARCHAR(128) NOT NULL COMMENT '商户系统中的商品id',
    `category_ori` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '原网站面包屑（和微购schema不同，这边没有拆开分多个字段）',
    `brand_ori` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '原始网站品牌（归一化后品牌从attribute表找）',
    `starttime` DATETIME NOT NULL COMMENT '开始时间',
    `endtime` DATETIME NOT NULL COMMENT '结束时间',
    `merchant_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '商品状态：0 ONLINE(上架),1 OFFLINE（下架）',
    `manual_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '运营维护状态（0 Normal，1 Pause，2 uncheck，3 check）',
    `signature` VARCHAR(256) NOT NULL COMMENT '商品签名（8张相关表签名字段：除date,输入的绝大多数信息，用于重复判断）',
    `isselfopen` TINYINT(4) NOT NULL COMMENT '是否自营（1：自营，0：非自营）',
    `dataversion` BIGINT(20) NOT NULL COMMENT '数据版本（防止老的覆盖旧的）',
    `inactivetime` DATETIME NOT NULL COMMENT '失效时间（先保留，后续再看逻辑）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    `deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0：正常;1：已删除，会移出表',
    `seller_name` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '商家名称',
    `upc` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '条形码',
    PRIMARY KEY (`sku_innerid`),
    KEY `sku_hashkey` (`sku_hashkey`),
    KEY `skuid` (`skuid`),
    KEY `unique_key` (`unique_key`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品信息表';

DROP TABLE IF EXISTS `sku_pps_45`;
CREATE TABLE `sku_pps_45`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',    
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，用于表关联',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `regionid` INT(11) NOT NULL COMMENT '地址id，可以为仓库id，目前采用国标id，全国为0',
    `terminal` TINYINT(4) NOT NULL COMMENT '0:MOBILE,1:WEB(移动端还是web端等)',
    `promotion_info` VARCHAR(1024) NOT NULl DEFAULT '' COMMENT '促销信息',
    `promotion_price_machine` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：使用促销模型算出来的价格',
    `promotion_price_manual` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：人工修改的促销价格',
    `originalprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '原始价格',
    `discountprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '折扣价',
    `stock` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '库存值状态（1:有货，0：无货）',
    `post_pay` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否支持货到付款（默认不支持）', 
    `m_update_time` DATETIME NOT NULL COMMENT '商家的库存价格更新时间，用于比较时间进行更新',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku价格&库存表';

DROP TABLE IF EXISTS `sku_level_45`;
CREATE TABLE `sku_level_45`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `level` INT(11) NOT NULL DEFAULT '0' COMMENT '确定价格库存更新频率（0更新频率最低）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku更新频率表';

DROP TABLE IF EXISTS `sku_cspu_45`;
CREATE TABLE `sku_cspu_45`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `cspuid` BIGINT(20) NOT NULL COMMENT 'Cspu ID',
    `type` TINYINT(4)  NOT NULL COMMENT 'cspu来源:1:MANUAL_CLASSIFICATION：手工修改，2:MACHINE_CLASSIFICATION：机器学习',
    `confidence` FLOAT(5,2) NOT NULL DEFAULT '0'COMMENT '取值0-100，可信度',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`),
    KEY `cspuid` (`cspuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku到cspu映射';

DROP TABLE IF EXISTS `sku_comment_45`;
CREATE TABLE `sku_comment_45`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `reviewcount` INT(11) NOT NULL COMMENT '评论数',
    `goodcount` INT(11) NOT NULL COMMENT '好评数',
    `medcount` INT(11) NOT NULL COMMENT '中评数',
    `badcount` INT(11) NOT NULL COMMENT '差评数',
    `tags` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT 'json串，评论提取tag',
    `score` DOUBLE NOT NULL COMMENT '平均分',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku评论表';


DROP TABLE IF EXISTS `bcs_sku_image_45`;
CREATE TABLE `bcs_sku_image_45`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
	`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
	`image_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片第三方地址',
	`image_bcs_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片本地地址',
	`status` TINYINT(4) NOT NULL DEFAULT '3' COMMENT '图片下载状态：0未开始，1下载出错，2正在下载，3下载到本地',
	`addtime` DATETIME NOT NULL COMMENT '添加时间',
	`updatetime` DATETIME NOT NULL COMMENT '更新时间',
	`errormessage` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '错误信息',
	`width` INT(11) NOT NULL DEFAULT '0' COMMENT '图片宽度',
	`height` INT(11) NOT NULL DEFAULT '0' COMMENT '图片高度',
	`content_md5` CHAR(32) NOT NULL  DEFAULT '' COMMENT '内容MD5',
	`sequence` INT(11) NOT NULL DEFAULT '1' COMMENT '图片序号',
	`gips_image` VARCHAR(1024) NOT NULL COMMENT '缩略图json串，一堆url和尺寸',
	`type` TINYINT(4) NOT NULL COMMENT '图片类型：主图，俯视图、侧视45度图等',
	PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '图片信息表';
DROP TABLE IF EXISTS `sku_attribute_46`;
CREATE TABLE `sku_attribute_46`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT 'Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
`property_id` VARCHAR(2048) NOT NULL DEFAULT '' COMMENT '属性id:属性值id；(存策略归一化后的id)',
`property_values` VARCHAR(2048) NOT NULl DEFAULT '' COMMENT '属性1：属性值；属性2:属性值 (存商家原始属性对)',
`property_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '对property_id+property_values做hash，用于重复判断',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品基础属性表';

DROP TABLE IF EXISTS `sku_cdt_46`;
CREATE TABLE `sku_cdt_46`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`leafcategoryid` BIGINT(20) NOT NULL COMMENT '叶子类目ID',
`classificationtype` TINYINT(4) NOT NULL COMMENT '类目来源 0：手工修改，1：机器学习，其他状态',
`confidence` FLOAT(5,2) NOT NULL DEFAULT '0' COMMENT '取值0-100，可信度(目前没在用)',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品分类信息表';

DROP TABLE IF EXISTS `sku_description_46`;
CREATE TABLE `sku_description_46`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `sku_desc_ori` LONGBLOB NOT NULL COMMENT '商品描述的压缩信息',
    `pd_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '商品描述的压缩信息',
    `sku_desc` LONGBLOB NOT NULL COMMENT '提取出来的商品描述压缩信息',
    `pd_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0 Undo, 1 doing, 2 done',
    `area` INT(11) NOT NULL DEFAULT '0' COMMENT '总的图片区域',
    `word_count` INT(11) NOT NULL DEFAULT '0' COMMENT '总字数',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品描述详情表';

DROP TABLE IF EXISTS `sku_info_46`;
CREATE TABLE `sku_info_46`(
    `sku_innerid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '商品shard内部id，自增id',
    `sku_hashkey` BIGINT(20) NOT NULL COMMENT 'Hash（skuid）的结果',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id,Merchantid(数字转化为8位的明文)拼接outerid。',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `unique_key` CHAR(32) NOT NULL DEFAULT '' COMMENT 'outer_id 的md5值  用来建索引',
    `feedid` BIGINT(20) NOT NULL COMMENT '属于哪个feed，feed平台导入商品时用',
    `ucid` BIGINT(2) NOT NULL DEFAULT '0' COMMENT '商家在百度内部的统一ID，和merchantid的区别是，merchantid是产品内部自己定义的',
    `title` VARCHAR(1024) NOT NULL COMMENT '标题（name字段去除，和title一样的东西）',
    `subtitle` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '副标题',
    `url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(web)',
    `mobileurl` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(移动端)',
    `outerid` VARCHAR(128) NOT NULL COMMENT '商户系统中的商品id',
    `category_ori` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '原网站面包屑（和微购schema不同，这边没有拆开分多个字段）',
    `brand_ori` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '原始网站品牌（归一化后品牌从attribute表找）',
    `starttime` DATETIME NOT NULL COMMENT '开始时间',
    `endtime` DATETIME NOT NULL COMMENT '结束时间',
    `merchant_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '商品状态：0 ONLINE(上架),1 OFFLINE（下架）',
    `manual_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '运营维护状态（0 Normal，1 Pause，2 uncheck，3 check）',
    `signature` VARCHAR(256) NOT NULL COMMENT '商品签名（8张相关表签名字段：除date,输入的绝大多数信息，用于重复判断）',
    `isselfopen` TINYINT(4) NOT NULL COMMENT '是否自营（1：自营，0：非自营）',
    `dataversion` BIGINT(20) NOT NULL COMMENT '数据版本（防止老的覆盖旧的）',
    `inactivetime` DATETIME NOT NULL COMMENT '失效时间（先保留，后续再看逻辑）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    `deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0：正常;1：已删除，会移出表',
    `seller_name` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '商家名称',
    `upc` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '条形码',
    PRIMARY KEY (`sku_innerid`),
    KEY `sku_hashkey` (`sku_hashkey`),
    KEY `skuid` (`skuid`),
    KEY `unique_key` (`unique_key`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品信息表';

DROP TABLE IF EXISTS `sku_pps_46`;
CREATE TABLE `sku_pps_46`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',    
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，用于表关联',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `regionid` INT(11) NOT NULL COMMENT '地址id，可以为仓库id，目前采用国标id，全国为0',
    `terminal` TINYINT(4) NOT NULL COMMENT '0:MOBILE,1:WEB(移动端还是web端等)',
    `promotion_info` VARCHAR(1024) NOT NULl DEFAULT '' COMMENT '促销信息',
    `promotion_price_machine` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：使用促销模型算出来的价格',
    `promotion_price_manual` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：人工修改的促销价格',
    `originalprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '原始价格',
    `discountprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '折扣价',
    `stock` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '库存值状态（1:有货，0：无货）',
    `post_pay` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否支持货到付款（默认不支持）', 
    `m_update_time` DATETIME NOT NULL COMMENT '商家的库存价格更新时间，用于比较时间进行更新',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku价格&库存表';

DROP TABLE IF EXISTS `sku_level_46`;
CREATE TABLE `sku_level_46`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `level` INT(11) NOT NULL DEFAULT '0' COMMENT '确定价格库存更新频率（0更新频率最低）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku更新频率表';

DROP TABLE IF EXISTS `sku_cspu_46`;
CREATE TABLE `sku_cspu_46`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `cspuid` BIGINT(20) NOT NULL COMMENT 'Cspu ID',
    `type` TINYINT(4)  NOT NULL COMMENT 'cspu来源:1:MANUAL_CLASSIFICATION：手工修改，2:MACHINE_CLASSIFICATION：机器学习',
    `confidence` FLOAT(5,2) NOT NULL DEFAULT '0'COMMENT '取值0-100，可信度',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`),
    KEY `cspuid` (`cspuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku到cspu映射';

DROP TABLE IF EXISTS `sku_comment_46`;
CREATE TABLE `sku_comment_46`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `reviewcount` INT(11) NOT NULL COMMENT '评论数',
    `goodcount` INT(11) NOT NULL COMMENT '好评数',
    `medcount` INT(11) NOT NULL COMMENT '中评数',
    `badcount` INT(11) NOT NULL COMMENT '差评数',
    `tags` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT 'json串，评论提取tag',
    `score` DOUBLE NOT NULL COMMENT '平均分',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku评论表';


DROP TABLE IF EXISTS `bcs_sku_image_46`;
CREATE TABLE `bcs_sku_image_46`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
	`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
	`image_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片第三方地址',
	`image_bcs_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片本地地址',
	`status` TINYINT(4) NOT NULL DEFAULT '3' COMMENT '图片下载状态：0未开始，1下载出错，2正在下载，3下载到本地',
	`addtime` DATETIME NOT NULL COMMENT '添加时间',
	`updatetime` DATETIME NOT NULL COMMENT '更新时间',
	`errormessage` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '错误信息',
	`width` INT(11) NOT NULL DEFAULT '0' COMMENT '图片宽度',
	`height` INT(11) NOT NULL DEFAULT '0' COMMENT '图片高度',
	`content_md5` CHAR(32) NOT NULL  DEFAULT '' COMMENT '内容MD5',
	`sequence` INT(11) NOT NULL DEFAULT '1' COMMENT '图片序号',
	`gips_image` VARCHAR(1024) NOT NULL COMMENT '缩略图json串，一堆url和尺寸',
	`type` TINYINT(4) NOT NULL COMMENT '图片类型：主图，俯视图、侧视45度图等',
	PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '图片信息表';
DROP TABLE IF EXISTS `sku_attribute_47`;
CREATE TABLE `sku_attribute_47`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT 'Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
`property_id` VARCHAR(2048) NOT NULL DEFAULT '' COMMENT '属性id:属性值id；(存策略归一化后的id)',
`property_values` VARCHAR(2048) NOT NULl DEFAULT '' COMMENT '属性1：属性值；属性2:属性值 (存商家原始属性对)',
`property_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '对property_id+property_values做hash，用于重复判断',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品基础属性表';

DROP TABLE IF EXISTS `sku_cdt_47`;
CREATE TABLE `sku_cdt_47`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`leafcategoryid` BIGINT(20) NOT NULL COMMENT '叶子类目ID',
`classificationtype` TINYINT(4) NOT NULL COMMENT '类目来源 0：手工修改，1：机器学习，其他状态',
`confidence` FLOAT(5,2) NOT NULL DEFAULT '0' COMMENT '取值0-100，可信度(目前没在用)',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品分类信息表';

DROP TABLE IF EXISTS `sku_description_47`;
CREATE TABLE `sku_description_47`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `sku_desc_ori` LONGBLOB NOT NULL COMMENT '商品描述的压缩信息',
    `pd_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '商品描述的压缩信息',
    `sku_desc` LONGBLOB NOT NULL COMMENT '提取出来的商品描述压缩信息',
    `pd_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0 Undo, 1 doing, 2 done',
    `area` INT(11) NOT NULL DEFAULT '0' COMMENT '总的图片区域',
    `word_count` INT(11) NOT NULL DEFAULT '0' COMMENT '总字数',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品描述详情表';

DROP TABLE IF EXISTS `sku_info_47`;
CREATE TABLE `sku_info_47`(
    `sku_innerid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '商品shard内部id，自增id',
    `sku_hashkey` BIGINT(20) NOT NULL COMMENT 'Hash（skuid）的结果',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id,Merchantid(数字转化为8位的明文)拼接outerid。',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `unique_key` CHAR(32) NOT NULL DEFAULT '' COMMENT 'outer_id 的md5值  用来建索引',
    `feedid` BIGINT(20) NOT NULL COMMENT '属于哪个feed，feed平台导入商品时用',
    `ucid` BIGINT(2) NOT NULL DEFAULT '0' COMMENT '商家在百度内部的统一ID，和merchantid的区别是，merchantid是产品内部自己定义的',
    `title` VARCHAR(1024) NOT NULL COMMENT '标题（name字段去除，和title一样的东西）',
    `subtitle` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '副标题',
    `url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(web)',
    `mobileurl` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(移动端)',
    `outerid` VARCHAR(128) NOT NULL COMMENT '商户系统中的商品id',
    `category_ori` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '原网站面包屑（和微购schema不同，这边没有拆开分多个字段）',
    `brand_ori` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '原始网站品牌（归一化后品牌从attribute表找）',
    `starttime` DATETIME NOT NULL COMMENT '开始时间',
    `endtime` DATETIME NOT NULL COMMENT '结束时间',
    `merchant_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '商品状态：0 ONLINE(上架),1 OFFLINE（下架）',
    `manual_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '运营维护状态（0 Normal，1 Pause，2 uncheck，3 check）',
    `signature` VARCHAR(256) NOT NULL COMMENT '商品签名（8张相关表签名字段：除date,输入的绝大多数信息，用于重复判断）',
    `isselfopen` TINYINT(4) NOT NULL COMMENT '是否自营（1：自营，0：非自营）',
    `dataversion` BIGINT(20) NOT NULL COMMENT '数据版本（防止老的覆盖旧的）',
    `inactivetime` DATETIME NOT NULL COMMENT '失效时间（先保留，后续再看逻辑）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    `deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0：正常;1：已删除，会移出表',
    `seller_name` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '商家名称',
    `upc` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '条形码',
    PRIMARY KEY (`sku_innerid`),
    KEY `sku_hashkey` (`sku_hashkey`),
    KEY `skuid` (`skuid`),
    KEY `unique_key` (`unique_key`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品信息表';

DROP TABLE IF EXISTS `sku_pps_47`;
CREATE TABLE `sku_pps_47`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',    
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，用于表关联',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `regionid` INT(11) NOT NULL COMMENT '地址id，可以为仓库id，目前采用国标id，全国为0',
    `terminal` TINYINT(4) NOT NULL COMMENT '0:MOBILE,1:WEB(移动端还是web端等)',
    `promotion_info` VARCHAR(1024) NOT NULl DEFAULT '' COMMENT '促销信息',
    `promotion_price_machine` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：使用促销模型算出来的价格',
    `promotion_price_manual` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：人工修改的促销价格',
    `originalprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '原始价格',
    `discountprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '折扣价',
    `stock` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '库存值状态（1:有货，0：无货）',
    `post_pay` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否支持货到付款（默认不支持）', 
    `m_update_time` DATETIME NOT NULL COMMENT '商家的库存价格更新时间，用于比较时间进行更新',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku价格&库存表';

DROP TABLE IF EXISTS `sku_level_47`;
CREATE TABLE `sku_level_47`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `level` INT(11) NOT NULL DEFAULT '0' COMMENT '确定价格库存更新频率（0更新频率最低）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku更新频率表';

DROP TABLE IF EXISTS `sku_cspu_47`;
CREATE TABLE `sku_cspu_47`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `cspuid` BIGINT(20) NOT NULL COMMENT 'Cspu ID',
    `type` TINYINT(4)  NOT NULL COMMENT 'cspu来源:1:MANUAL_CLASSIFICATION：手工修改，2:MACHINE_CLASSIFICATION：机器学习',
    `confidence` FLOAT(5,2) NOT NULL DEFAULT '0'COMMENT '取值0-100，可信度',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`),
    KEY `cspuid` (`cspuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku到cspu映射';

DROP TABLE IF EXISTS `sku_comment_47`;
CREATE TABLE `sku_comment_47`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `reviewcount` INT(11) NOT NULL COMMENT '评论数',
    `goodcount` INT(11) NOT NULL COMMENT '好评数',
    `medcount` INT(11) NOT NULL COMMENT '中评数',
    `badcount` INT(11) NOT NULL COMMENT '差评数',
    `tags` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT 'json串，评论提取tag',
    `score` DOUBLE NOT NULL COMMENT '平均分',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku评论表';


DROP TABLE IF EXISTS `bcs_sku_image_47`;
CREATE TABLE `bcs_sku_image_47`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
	`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
	`image_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片第三方地址',
	`image_bcs_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片本地地址',
	`status` TINYINT(4) NOT NULL DEFAULT '3' COMMENT '图片下载状态：0未开始，1下载出错，2正在下载，3下载到本地',
	`addtime` DATETIME NOT NULL COMMENT '添加时间',
	`updatetime` DATETIME NOT NULL COMMENT '更新时间',
	`errormessage` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '错误信息',
	`width` INT(11) NOT NULL DEFAULT '0' COMMENT '图片宽度',
	`height` INT(11) NOT NULL DEFAULT '0' COMMENT '图片高度',
	`content_md5` CHAR(32) NOT NULL  DEFAULT '' COMMENT '内容MD5',
	`sequence` INT(11) NOT NULL DEFAULT '1' COMMENT '图片序号',
	`gips_image` VARCHAR(1024) NOT NULL COMMENT '缩略图json串，一堆url和尺寸',
	`type` TINYINT(4) NOT NULL COMMENT '图片类型：主图，俯视图、侧视45度图等',
	PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '图片信息表';
DROP TABLE IF EXISTS `sku_attribute_48`;
CREATE TABLE `sku_attribute_48`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT 'Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
`property_id` VARCHAR(2048) NOT NULL DEFAULT '' COMMENT '属性id:属性值id；(存策略归一化后的id)',
`property_values` VARCHAR(2048) NOT NULl DEFAULT '' COMMENT '属性1：属性值；属性2:属性值 (存商家原始属性对)',
`property_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '对property_id+property_values做hash，用于重复判断',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品基础属性表';

DROP TABLE IF EXISTS `sku_cdt_48`;
CREATE TABLE `sku_cdt_48`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`leafcategoryid` BIGINT(20) NOT NULL COMMENT '叶子类目ID',
`classificationtype` TINYINT(4) NOT NULL COMMENT '类目来源 0：手工修改，1：机器学习，其他状态',
`confidence` FLOAT(5,2) NOT NULL DEFAULT '0' COMMENT '取值0-100，可信度(目前没在用)',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品分类信息表';

DROP TABLE IF EXISTS `sku_description_48`;
CREATE TABLE `sku_description_48`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `sku_desc_ori` LONGBLOB NOT NULL COMMENT '商品描述的压缩信息',
    `pd_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '商品描述的压缩信息',
    `sku_desc` LONGBLOB NOT NULL COMMENT '提取出来的商品描述压缩信息',
    `pd_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0 Undo, 1 doing, 2 done',
    `area` INT(11) NOT NULL DEFAULT '0' COMMENT '总的图片区域',
    `word_count` INT(11) NOT NULL DEFAULT '0' COMMENT '总字数',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品描述详情表';

DROP TABLE IF EXISTS `sku_info_48`;
CREATE TABLE `sku_info_48`(
    `sku_innerid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '商品shard内部id，自增id',
    `sku_hashkey` BIGINT(20) NOT NULL COMMENT 'Hash（skuid）的结果',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id,Merchantid(数字转化为8位的明文)拼接outerid。',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `unique_key` CHAR(32) NOT NULL DEFAULT '' COMMENT 'outer_id 的md5值  用来建索引',
    `feedid` BIGINT(20) NOT NULL COMMENT '属于哪个feed，feed平台导入商品时用',
    `ucid` BIGINT(2) NOT NULL DEFAULT '0' COMMENT '商家在百度内部的统一ID，和merchantid的区别是，merchantid是产品内部自己定义的',
    `title` VARCHAR(1024) NOT NULL COMMENT '标题（name字段去除，和title一样的东西）',
    `subtitle` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '副标题',
    `url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(web)',
    `mobileurl` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(移动端)',
    `outerid` VARCHAR(128) NOT NULL COMMENT '商户系统中的商品id',
    `category_ori` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '原网站面包屑（和微购schema不同，这边没有拆开分多个字段）',
    `brand_ori` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '原始网站品牌（归一化后品牌从attribute表找）',
    `starttime` DATETIME NOT NULL COMMENT '开始时间',
    `endtime` DATETIME NOT NULL COMMENT '结束时间',
    `merchant_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '商品状态：0 ONLINE(上架),1 OFFLINE（下架）',
    `manual_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '运营维护状态（0 Normal，1 Pause，2 uncheck，3 check）',
    `signature` VARCHAR(256) NOT NULL COMMENT '商品签名（8张相关表签名字段：除date,输入的绝大多数信息，用于重复判断）',
    `isselfopen` TINYINT(4) NOT NULL COMMENT '是否自营（1：自营，0：非自营）',
    `dataversion` BIGINT(20) NOT NULL COMMENT '数据版本（防止老的覆盖旧的）',
    `inactivetime` DATETIME NOT NULL COMMENT '失效时间（先保留，后续再看逻辑）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    `deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0：正常;1：已删除，会移出表',
    `seller_name` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '商家名称',
    `upc` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '条形码',
    PRIMARY KEY (`sku_innerid`),
    KEY `sku_hashkey` (`sku_hashkey`),
    KEY `skuid` (`skuid`),
    KEY `unique_key` (`unique_key`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品信息表';

DROP TABLE IF EXISTS `sku_pps_48`;
CREATE TABLE `sku_pps_48`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',    
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，用于表关联',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `regionid` INT(11) NOT NULL COMMENT '地址id，可以为仓库id，目前采用国标id，全国为0',
    `terminal` TINYINT(4) NOT NULL COMMENT '0:MOBILE,1:WEB(移动端还是web端等)',
    `promotion_info` VARCHAR(1024) NOT NULl DEFAULT '' COMMENT '促销信息',
    `promotion_price_machine` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：使用促销模型算出来的价格',
    `promotion_price_manual` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：人工修改的促销价格',
    `originalprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '原始价格',
    `discountprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '折扣价',
    `stock` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '库存值状态（1:有货，0：无货）',
    `post_pay` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否支持货到付款（默认不支持）', 
    `m_update_time` DATETIME NOT NULL COMMENT '商家的库存价格更新时间，用于比较时间进行更新',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku价格&库存表';

DROP TABLE IF EXISTS `sku_level_48`;
CREATE TABLE `sku_level_48`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `level` INT(11) NOT NULL DEFAULT '0' COMMENT '确定价格库存更新频率（0更新频率最低）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku更新频率表';

DROP TABLE IF EXISTS `sku_cspu_48`;
CREATE TABLE `sku_cspu_48`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `cspuid` BIGINT(20) NOT NULL COMMENT 'Cspu ID',
    `type` TINYINT(4)  NOT NULL COMMENT 'cspu来源:1:MANUAL_CLASSIFICATION：手工修改，2:MACHINE_CLASSIFICATION：机器学习',
    `confidence` FLOAT(5,2) NOT NULL DEFAULT '0'COMMENT '取值0-100，可信度',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`),
    KEY `cspuid` (`cspuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku到cspu映射';

DROP TABLE IF EXISTS `sku_comment_48`;
CREATE TABLE `sku_comment_48`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `reviewcount` INT(11) NOT NULL COMMENT '评论数',
    `goodcount` INT(11) NOT NULL COMMENT '好评数',
    `medcount` INT(11) NOT NULL COMMENT '中评数',
    `badcount` INT(11) NOT NULL COMMENT '差评数',
    `tags` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT 'json串，评论提取tag',
    `score` DOUBLE NOT NULL COMMENT '平均分',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku评论表';


DROP TABLE IF EXISTS `bcs_sku_image_48`;
CREATE TABLE `bcs_sku_image_48`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
	`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
	`image_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片第三方地址',
	`image_bcs_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片本地地址',
	`status` TINYINT(4) NOT NULL DEFAULT '3' COMMENT '图片下载状态：0未开始，1下载出错，2正在下载，3下载到本地',
	`addtime` DATETIME NOT NULL COMMENT '添加时间',
	`updatetime` DATETIME NOT NULL COMMENT '更新时间',
	`errormessage` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '错误信息',
	`width` INT(11) NOT NULL DEFAULT '0' COMMENT '图片宽度',
	`height` INT(11) NOT NULL DEFAULT '0' COMMENT '图片高度',
	`content_md5` CHAR(32) NOT NULL  DEFAULT '' COMMENT '内容MD5',
	`sequence` INT(11) NOT NULL DEFAULT '1' COMMENT '图片序号',
	`gips_image` VARCHAR(1024) NOT NULL COMMENT '缩略图json串，一堆url和尺寸',
	`type` TINYINT(4) NOT NULL COMMENT '图片类型：主图，俯视图、侧视45度图等',
	PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '图片信息表';
DROP TABLE IF EXISTS `sku_attribute_49`;
CREATE TABLE `sku_attribute_49`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT 'Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
`property_id` VARCHAR(2048) NOT NULL DEFAULT '' COMMENT '属性id:属性值id；(存策略归一化后的id)',
`property_values` VARCHAR(2048) NOT NULl DEFAULT '' COMMENT '属性1：属性值；属性2:属性值 (存商家原始属性对)',
`property_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '对property_id+property_values做hash，用于重复判断',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品基础属性表';

DROP TABLE IF EXISTS `sku_cdt_49`;
CREATE TABLE `sku_cdt_49`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
`skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
`leafcategoryid` BIGINT(20) NOT NULL COMMENT '叶子类目ID',
`classificationtype` TINYINT(4) NOT NULL COMMENT '类目来源 0：手工修改，1：机器学习，其他状态',
`confidence` FLOAT(5,2) NOT NULL DEFAULT '0' COMMENT '取值0-100，可信度(目前没在用)',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `sku_innerid` (`sku_innerid`),
KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品分类信息表';

DROP TABLE IF EXISTS `sku_description_49`;
CREATE TABLE `sku_description_49`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `sku_desc_ori` LONGBLOB NOT NULL COMMENT '商品描述的压缩信息',
    `pd_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '商品描述的压缩信息',
    `sku_desc` LONGBLOB NOT NULL COMMENT '提取出来的商品描述压缩信息',
    `pd_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0 Undo, 1 doing, 2 done',
    `area` INT(11) NOT NULL DEFAULT '0' COMMENT '总的图片区域',
    `word_count` INT(11) NOT NULL DEFAULT '0' COMMENT '总字数',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品描述详情表';

DROP TABLE IF EXISTS `sku_info_49`;
CREATE TABLE `sku_info_49`(
    `sku_innerid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '商品shard内部id，自增id',
    `sku_hashkey` BIGINT(20) NOT NULL COMMENT 'Hash（skuid）的结果',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id,Merchantid(数字转化为8位的明文)拼接outerid。',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `unique_key` CHAR(32) NOT NULL DEFAULT '' COMMENT 'outer_id 的md5值  用来建索引',
    `feedid` BIGINT(20) NOT NULL COMMENT '属于哪个feed，feed平台导入商品时用',
    `ucid` BIGINT(2) NOT NULL DEFAULT '0' COMMENT '商家在百度内部的统一ID，和merchantid的区别是，merchantid是产品内部自己定义的',
    `title` VARCHAR(1024) NOT NULL COMMENT '标题（name字段去除，和title一样的东西）',
    `subtitle` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '副标题',
    `url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(web)',
    `mobileurl` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接(移动端)',
    `outerid` VARCHAR(128) NOT NULL COMMENT '商户系统中的商品id',
    `category_ori` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '原网站面包屑（和微购schema不同，这边没有拆开分多个字段）',
    `brand_ori` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '原始网站品牌（归一化后品牌从attribute表找）',
    `starttime` DATETIME NOT NULL COMMENT '开始时间',
    `endtime` DATETIME NOT NULL COMMENT '结束时间',
    `merchant_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '商品状态：0 ONLINE(上架),1 OFFLINE（下架）',
    `manual_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '运营维护状态（0 Normal，1 Pause，2 uncheck，3 check）',
    `signature` VARCHAR(256) NOT NULL COMMENT '商品签名（8张相关表签名字段：除date,输入的绝大多数信息，用于重复判断）',
    `isselfopen` TINYINT(4) NOT NULL COMMENT '是否自营（1：自营，0：非自营）',
    `dataversion` BIGINT(20) NOT NULL COMMENT '数据版本（防止老的覆盖旧的）',
    `inactivetime` DATETIME NOT NULL COMMENT '失效时间（先保留，后续再看逻辑）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    `deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0：正常;1：已删除，会移出表',
    `seller_name` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '商家名称',
    `upc` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '条形码',
    PRIMARY KEY (`sku_innerid`),
    KEY `sku_hashkey` (`sku_hashkey`),
    KEY `skuid` (`skuid`),
    KEY `unique_key` (`unique_key`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品信息表';

DROP TABLE IF EXISTS `sku_pps_49`;
CREATE TABLE `sku_pps_49`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',    
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，用于表关联',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `regionid` INT(11) NOT NULL COMMENT '地址id，可以为仓库id，目前采用国标id，全国为0',
    `terminal` TINYINT(4) NOT NULL COMMENT '0:MOBILE,1:WEB(移动端还是web端等)',
    `promotion_info` VARCHAR(1024) NOT NULl DEFAULT '' COMMENT '促销信息',
    `promotion_price_machine` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：使用促销模型算出来的价格',
    `promotion_price_manual` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '百度惠：人工修改的促销价格',
    `originalprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '原始价格',
    `discountprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '折扣价',
    `stock` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '库存值状态（1:有货，0：无货）',
    `post_pay` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否支持货到付款（默认不支持）', 
    `m_update_time` DATETIME NOT NULL COMMENT '商家的库存价格更新时间，用于比较时间进行更新',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku价格&库存表';

DROP TABLE IF EXISTS `sku_level_49`;
CREATE TABLE `sku_level_49`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `level` INT(11) NOT NULL DEFAULT '0' COMMENT '确定价格库存更新频率（0更新频率最低）',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku更新频率表';

DROP TABLE IF EXISTS `sku_cspu_49`;
CREATE TABLE `sku_cspu_49`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `cspuid` BIGINT(20) NOT NULL COMMENT 'Cspu ID',
    `type` TINYINT(4)  NOT NULL COMMENT 'cspu来源:1:MANUAL_CLASSIFICATION：手工修改，2:MACHINE_CLASSIFICATION：机器学习',
    `confidence` FLOAT(5,2) NOT NULL DEFAULT '0'COMMENT '取值0-100，可信度',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`),
    KEY `cspuid` (`cspuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku到cspu映射';

DROP TABLE IF EXISTS `sku_comment_49`;
CREATE TABLE `sku_comment_49`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `reviewcount` INT(11) NOT NULL COMMENT '评论数',
    `goodcount` INT(11) NOT NULL COMMENT '好评数',
    `medcount` INT(11) NOT NULL COMMENT '中评数',
    `badcount` INT(11) NOT NULL COMMENT '差评数',
    `tags` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT 'json串，评论提取tag',
    `score` DOUBLE NOT NULL COMMENT '平均分',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku评论表';


DROP TABLE IF EXISTS `bcs_sku_image_49`;
CREATE TABLE `bcs_sku_image_49`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
	`sku_innerid` BIGINT(20) NOT NULL COMMENT '商品shard内部id，自增id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
	`image_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片第三方地址',
	`image_bcs_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '图片本地地址',
	`status` TINYINT(4) NOT NULL DEFAULT '3' COMMENT '图片下载状态：0未开始，1下载出错，2正在下载，3下载到本地',
	`addtime` DATETIME NOT NULL COMMENT '添加时间',
	`updatetime` DATETIME NOT NULL COMMENT '更新时间',
	`errormessage` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '错误信息',
	`width` INT(11) NOT NULL DEFAULT '0' COMMENT '图片宽度',
	`height` INT(11) NOT NULL DEFAULT '0' COMMENT '图片高度',
	`content_md5` CHAR(32) NOT NULL  DEFAULT '' COMMENT '内容MD5',
	`sequence` INT(11) NOT NULL DEFAULT '1' COMMENT '图片序号',
	`gips_image` VARCHAR(1024) NOT NULL COMMENT '缩略图json串，一堆url和尺寸',
	`type` TINYINT(4) NOT NULL COMMENT '图片类型：主图，俯视图、侧视45度图等',
	PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '图片信息表';
