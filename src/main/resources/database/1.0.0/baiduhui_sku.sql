DROP TABLE IF EXISTS `sku_attribute`;
CREATE TABLE `sku_attribute`(
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

DROP TABLE IF EXISTS `sku_cdt`;
CREATE TABLE `sku_cdt`(
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

DROP TABLE IF EXISTS `sku_description`;
CREATE TABLE `sku_description`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
    `sku_innerid` BIGINT(20) NOT NULL COMMENT '商品Shard内商品id',
    `skuid` VARCHAR(128) NOT NULL COMMENT '商品全局唯一标识id',
    `sku_desc_ori` LONGBLOB NOT NULL COMMENT '商品描述的压缩信息',
    `pd_hash` CHAR(32) NOT NULL DEFAULT '' COMMENT '商品描述的压缩信息',
    `sku_desc` LONGBLOB NOT NULL COMMENT '提取出来的商品描述压缩信息',
    `pd_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0 Undo, 1 doing, 2 done',
    `area` INT(11) NOT NULL DEFAULT '0' COMMENT '总的图片区域',
    `Word_count` INT(11) NOT NULL DEFAULT '0' COMMENT '总字数',
    `addtime` DATETIME NOT NULL COMMENT '添加时间',
    `updatetime` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `sku_innerid` (`sku_innerid`),
    KEY `skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品描述详情表';

DROP TABLE IF EXISTS `sku_info`;
CREATE TABLE `sku_info`(
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

DROP TABLE IF EXISTS `sku_pps`;
CREATE TABLE `sku_pps`(
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

DROP TABLE IF EXISTS `sku_level`;
CREATE TABLE `sku_level`(
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

DROP TABLE IF EXISTS `sku_cspu`;
CREATE TABLE `sku_cspu`(
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

DROP TABLE IF EXISTS `sku_comment`;
CREATE TABLE `sku_comment`(
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


DROP TABLE IF EXISTS `bcs_sku_image`;
CREATE TABLE `bcs_sku_image`(
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