DROP TABLE IF EXISTS `item_attribute`;
CREATE TABLE `item_attribute`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`itemid` BIGINT(20) NOT NULL COMMENT '商品内部id',
`property_id` VARCHAR(2048) NOT NULL COMMENT '属性id:属性值id；（存定义成id的属性）',
`property_values` VARCHAR(2048) NOT NULL COMMENT '属性1：属性值:是否自定义：image_url；属性2:属性值:是否自定义：image_url(图片显示在item页，这边存的是非销售属性，是否自定义，用于表示属性项是否自定义的，在百度Mall商家平台中，可以自定义属性项)',
`property_md5` CHAR(32) NOT NULL COMMENT '对property_id+property_values做hash，用于重复判断',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `idx_itemid` (`itemid`),
KEY `idx_property_md5` (`property_md5`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品基础属性表';

DROP TABLE IF EXISTS `item_cdt`;
CREATE TABLE `item_cdt`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`itemid` BIGINT(20) NOT NULL COMMENT '商品内部id',
`leafcategoryid` BIGINT(20) NOT NULL COMMENT '叶子类目ID',
`classificationtype` TINYINT(4) NOT NULL DEFAULT 1 COMMENT '类目来源 0运营修改，1：商家修改，2：机器学习',
`operator` BIGINT(20) NOT NULL COMMENT '商家或者运营的UCID，机器为0',
`confidence` INT(11) NOT NULL DEFAULT 100 COMMENT '取值0-100，可信度，默认100',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
`cdt_md5` CHAR(32) NOT NULL COMMENT '对item_id+classificationtype做MD5，用于重复判断',
PRIMARY KEY (`id`),
KEY `idx_itemid` (`itemid`),
KEY `idx_cdt_md5` (`cdt_md5`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品分类信息表';

DROP TABLE IF EXISTS `item_description`;
CREATE TABLE `item_description`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`merchantid` INT(11) NOT NULL COMMENT '商户id',
`shopid` INT(11) NOT NULL COMMENT '店铺id',
`itemid` BIGINT(20) NOT NULL COMMENT '商品内部id',
`item_desc_ori` LONGBLOB NOT NULL COMMENT '商品描述的压缩信息',
`pd_md5` CHAR(32) NOT NULL COMMENT '对item_desc_ori做hash',
`item_desc` LONGBLOB NOT NULL COMMENT '提取出来的商品描述压缩信息',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `idx_shopid` (`shopid`),
KEY `idx_itemid` (`itemid`),
KEY `idx_pd_md5` (`pd_md5`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品描述详情表';

DROP TABLE IF EXISTS `item_info`;
CREATE TABLE `item_info`(
`itemid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`merchantid` INT(11) NOT NULL COMMENT '商户id',
`ucid` BIGINT(20) NOT NULL COMMENT '商家在百度内部的统一ID，和merchantid的区别是，merchantid是产品内部自己定义的',
`shopid` INT(11) NOT NULL COMMENT '店铺id',
`outerid` VARCHAR(128) NOT NULL DEFAULT ''  COMMENT '商品在商家ERP系统中的id',
`item_info_hashcode` INT(11) NOT NULL COMMENT 'mechantid+outerid对应的hashcode',
`title` VARCHAR(1024) NOT NULL COMMENT '标题',
`subtitle` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '副标题',
`url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '官网链接',
`starttime` DATETIME NOT NULL COMMENT '开始时间',
`endtime` DATETIME NOT NULL COMMENT '结束时间',
`merchant_status` TINYINT(4) NOT NULL COMMENT '商家维护状态（0:ONLINE：下架，1:OFFLINE：上架，2:EDIT：编辑中，3：未生效）',
`manual_status` TINYINT(4) NOT NULL COMMENT '运营维护状态（0:Normal，1:Pause，2:uncheck，3:check）',
`shopcategory` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '店铺内分类，可以有多个分类（一期不考虑，留着字段）',
`dataversion` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '数据版本（防止老的覆盖旧的）',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
`ware_big_small_model` INT(11) NOT NULL DEFAULT 0 COMMENT '商品件型：必须输入，0免费、1超大件、2超大件半件、3大件、4大件半件、5中件、6中件半件、7小件、8超小件，FBP类型商品必须输入',
`ware_pack_type` INT(11) NOT NULL DEFAULT 1 COMMENT '商品包装：必须输入，1普通商品、2易碎品、3裸瓶液体、4带包装液体、5按原包装出库，FBP类型商品必须输入', 
`deleted` TINYINT(4) NOT NULL DEFAULT 0 COMMENT '0：正常1：已删除，会移出表',
`category_ori` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '原网站面包屑',
`brand_ori` CHAR(128) NOT NULL DEFAULT '' COMMENT '原始网站品牌',
PRIMARY KEY (`itemid`),
KEY `idx_shopid` (`shopid`),
KEY `idx_item_info_hashcode` (`item_info_hashcode`),
KEY `idx_addtime` (`addtime`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '商品信息表';

DROP TABLE IF EXISTS `sku_info`;
CREATE TABLE `sku_info`(
`skuid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`sku_type` TINYINT(4) NOT NULL DEFAULT 0 COMMENT '0:NORMAL,1:GIFT（赠品）',
`sell_attribute` VARCHAR(2048) NOT NULL COMMENT '销售属性（pid：vid：是否有小图）（多个之间；号分隔，是否有小图：1有，0没有）',
`property_values` VARCHAR(2048) NOT NULL COMMENT '属性1：属性值:是否自定义：是否有小图；属性2:属性值:是否自定义：是否有小图(图片显示在item页，这边只存销售属性/规格部分，是否有小图：1有，0没有)',
`upc` VARCHAR(128) NOT NULL DEFAULT 0 COMMENT '条形码（商家录入，运营平台能更改）',
`itemid` BIGINT(20) NOT NULL COMMENT '商品内部id',
`deleted` TINYINT(4) NOT NULL DEFAULT 0 COMMENT '是否被删除,1：是，0：否',
`weight` DECIMAL(10,2) NOT NULL DEFAULT 0 COMMENT '重量',
`volume` DECIMAL(10,2) NOT NULL DEFAULT 0 COMMENT '体积',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
`outerid` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '商品在商家ERP系统中的id',
PRIMARY KEY (`skuid`),
KEY `idx_itemid` (`itemid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'Sku基本信息表';

DROP TABLE IF EXISTS `sku_pps`;
CREATE TABLE `sku_pps`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`shopid` INT(11) NOT NULL COMMENT '店铺id',
`merchantid` BIGINT(20) NOT NULL COMMENT '商家id',
`skuid` BIGINT(20) NOT NULL COMMENT 'skuid',
`regionid` INT(11) NOT NULL DEFAULT 0 COMMENT '地址id，可以为仓库id，目前采用国标id，全国为0',
`pc_mobile` TINYINT(4) NOT NULL DEFAULT 0 COMMENT 'ALL:0，MOBILE:1，WEB:2',
`price` DECIMAL(10,2) NOT NULL COMMENT '原始价格',
`discount_price` DECIMAL(10,2) NOT NULL COMMENT '折扣价',
`flash_price` DECIMAL(10,2) NOT NULL DEFAULT 0 COMMENT '闪购价',
`ticket_price` DECIMAL(10,2) NOT NULL DEFAULT 0 COMMENT '点券价',
`promotion_type` TINYINT(4) NOT NULL DEFAULT 0 COMMENT 'NONE:0,PROMOTION:1,FLASH:2,ALL:3',
`stock` INT(11) NOT NULL COMMENT '库存值',
`post_pay` TINYINT(4) NOT NULL DEFAULT 0 COMMENT '是否支持货到付款,1：支持，0不支持',
`m_update_time` DATETIME NOT NULL COMMENT '商家的库存价格更新时间，用于比较时间进行更新',
`inventory_warning` INT(11) NOT NULL DEFAULT 0 COMMENT '预警库存',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
`pps_md5` CHAR(32) NOT NULL COMMENT '对sku_id+region_id+pc_mobile做MD5，用于重复判断',
PRIMARY KEY (`id`),
KEY `idx_skuid` (`skuid`),
KEY `idx_pps_md5` (`pps_md5`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'sku价格&库存表';

DROP TABLE IF EXISTS `bcs_image`;
CREATE TABLE `bcs_image`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
`itemid` BIGINT(20) NOT NULL COMMENT '商品ID',
`skuid` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '0代表是item级别的图片，其他情况表示是sku上的图片',
`imageUrl` VARCHAR(1024) NOT NULL COMMENT '图片第三方地址',
`imageBCSUrl` VARCHAR(1024) NOT NULL COMMENT '图片本地地址',
`bcs_status` TINYINT(4) NOT NULL DEFAULT 3 COMMENT '图片下载状态，0:done：已下载到本地，1:downing：正在下载，2:error：下载出错，3:undo：未开始。',
`addtime` DATETIME NOT NULL COMMENT '添加时间',
`updatetime` DATETIME NOT NULL COMMENT '更新时间',
`errormessage` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '错误信息',
`width` INT(11) NOT NULL COMMENT '图片宽度',
`height` INT(11) NOT NULL COMMENT '图片高度',
`content_md5` CHAR(32) NOT NULL COMMENT '内容MD5',
`sequence` INT(11) NOT NULL COMMENT '图片序号',
`gips_image` VARCHAR(1024) NOT NULL COMMENT '缩略图json串，一堆url和尺寸',
`pic_type` TINYINT(4) NOT NULL COMMENT '图片类型：主图:0，俯视图:1、侧视45度图:2，小图：3',
PRIMARY KEY (`id`),
KEY `idx_itemid` (`itemid`),
KEY `idx_skuid` (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '图片信息表bcs_image';