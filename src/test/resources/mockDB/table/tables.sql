
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`(
	`categoryid` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`parentid` BIGINT(20) NOT NULL DEFAULT '0',
	`name` VARCHAR(255) NOT NULL,
	`isleaf` TINYINT(4) NOT NULL DEFAULT '0',
	`pos` INT(11) NOT NULL DEFAULT '9999',
	`addtime`  DATETIME NOT NULL,
	`updatetime`  DATETIME NOT NULL,
	`deleted` TINYINT(4) NOT NULL DEFAULT '0',
	PRIMARY KEY (`categoryid`)
);

DROP TABLE IF EXISTS `category_property`;
CREATE TABLE `category_property`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`c_id` INT(11) NOT NULL,
	`isleaf` TINYINT(4) NOT NULL DEFAULT '0',
	`name` VARCHAR(100) NOT NULL,
	`pid` BIGINT(20) NOT NULL,
	`is_required` TINYINT(4) NOT NULL DEFAULT '0',
	`is_selfdefine` TINYINT(4) NOT NULL DEFAULT '0',
	`type` TINYINT(4) NOT NULL DEFAULT '2',
	`value_range` VARCHAR(400) NOT NULL,
	`prop_type` TINYINT(4) NOT NULL,
	`sequence` INT(11) NOT NULL DEFAULT '9999',
	`active` TINYINT(4) NOT NULL DEFAULT '1',
	`addtime`  DATETIME NOT NULL,
	`updatetime`  DATETIME NOT NULL,
	PRIMARY KEY (`id`)
);
DROP TABLE IF EXISTS `category_property_value`;
CREATE TABLE `category_property_value`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`c_id` INT(11) NOT NULL,
	`isleaf` TINYINT(4) NOT NULL DEFAULT '0',
	`pid` BIGINT(20) NOT NULL,
	`vid` BIGINT(20) NOT NULL,
	`alias` VARCHAR(255) NOT NULL DEFAULT '',
	`image_url` VARCHAR(1024) NOT NULL DEFAULT '',
	`active` TINYINT(4) NOT NULL DEFAULT '1',
	`addtime`  DATETIME NOT NULL,
	`updatetime`  DATETIME NOT NULL,
	PRIMARY KEY (`id`)
);
DROP TABLE IF EXISTS `base_property`;
CREATE TABLE `base_property`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`property_name` VARCHAR(100) NOT NULL,
	`en_name` VARCHAR(100) NOT NULL DEFAULT '',
	PRIMARY KEY (`id`)
);
DROP TABLE IF EXISTS `base_val`;
CREATE TABLE `base_val`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`value` VARCHAR(100) NOT NULL,
	PRIMARY KEY (`id`)
);
DROP TABLE IF EXISTS `cm_category`;
CREATE TABLE `cm_category`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(200) NOT NULL,
	`parentid` INT(11) NOT NULL DEFAULT '-1',
	`isleaf` TINYINT(4) NOT NULL DEFAULT '0',
	`pos` INT(11) NOT NULL DEFAULT '9999',
	`active` TINYINT(4) NOT NULL DEFAULT '1',
	`updatetime`  DATETIME NOT NULL,
	PRIMARY KEY (`id`)
);
DROP TABLE IF EXISTS `category_map`;
CREATE TABLE `category_map`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`cm_cid` INT(11) NOT NULL,
	`base_cid` INT(11) NOT NULL,
	`active` TINYINT(4) NOT NULL DEFAULT '1',
	`updatetime`  DATETIME NOT NULL,
	PRIMARY KEY (`id`)
);
DROP TABLE IF EXISTS `cm_property_value`;
CREATE TABLE `cm_property_value`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`cid` INT(11) NOT NULL,
	`cm_pid` INT(11) NOT NULL,
	`dic_vid` INT(11) NOT NULL,
	`pos` INT(11) NOT NULL DEFAULT '9999',
	`active` TINYINT(4) NOT NULL DEFAULT '1',
	`updatetime`  DATETIME NOT NULL,
	PRIMARY KEY (`id`)
);
DROP TABLE IF EXISTS `cm_property`;
CREATE TABLE `cm_property`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`cm_cid` INT(11) NOT NULL,
	`dic_pid` INT(11) NOT NULL,
	`base_pid` INT(11) NOT NULL,
	`pos` INT(11) NOT NULL DEFAULT '9999',
	`active` TINYINT(4) NOT NULL DEFAULT '1',
	`updatetime`  DATETIME NOT NULL,
	PRIMARY KEY (`id`)
);
DROP TABLE IF EXISTS `property_value_map`;
CREATE TABLE `property_value_map`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`cm_vid` INT(11) NOT NULL,
	`base_vid` INT(11) NOT NULL,
	`active` TINYINT(4) NOT NULL DEFAULT '1',
	`updatetime`  DATETIME NOT NULL,
	PRIMARY KEY (`id`)
);
DROP TABLE IF EXISTS `base_cm_value`;
CREATE TABLE `base_cm_value`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`value` VARCHAR(100) NOT NULL,
	PRIMARY KEY (`id`)
);
DROP TABLE IF EXISTS `base_cm_property`;
CREATE TABLE `base_cm_property`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(100) NOT NULL,
	PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `cspu_info`;
CREATE TABLE `cspu_info`(
    `cspuid` BIGINT(20) NOT NULL AUTO_INCREMENT ,
    `cid` BIGINT(20) NOT NULL ,
    `spuid` BIGINT(20) DEFAULT NULL ,
    `name` VARCHAR(255) NOT NULL ,
    `alias` VARCHAR(1024) DEFAULT NULL ,
    `brand` BIGINT(20) DEFAULT NULL ,
    `product_model` VARCHAR(255) DEFAULT NULL ,
    `product_upc` VARCHAR(255) DEFAULT NULL ,
    `sale_attribute` MEDIUMTEXT DEFAULT NULL ,
    `attribute` MEDIUMTEXT DEFAULT NULL ,
    `sale_attribute_orig` MEDIUMTEXT DEFAULT NULL ,
    `attribute_orig` MEDIUMTEXT DEFAULT NULL ,
    `extend_attribute` VARCHAR(1024) DEFAULT NULL ,
    `pack_list` VARCHAR(1024) DEFAULT NULL ,
    `price` DECIMAL(10,2) DEFAULT NULL ,
    `url` VARCHAR(1024) DEFAULT '' ,
    `big_field` LONGBLOB DEFAULT NULL ,
    `deleted` TINYINT(4) NOT NULL DEFAULT '0' ,
    `active` TINYINT(4) NOT NULL DEFAULT '1' ,
    `level` BIGINT(20) DEFAULT NULL ,
    `cspu_from` TINYINT(3) NOT NULL ,
    `addtime` DATETIME NOT NULL,
    `updatetime` DATETIME NOT NULL,
    PRIMARY KEY (`cspuid`)
);

DROP TABLE IF EXISTS `cspu_level`;
CREATE TABLE `cspu_level`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
    `cspuid` BIGINT(20) NOT NULL ,
    `level` BIGINT(20) NOT NULL DEFAULT 0,
    `addtime` DATETIME NOT NULL ,
    `updatetime` DATETIME NOT NULL ,
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `cspu_comment`;
CREATE TABLE `cspu_comment`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
    `cspuid` BIGINT(20) NOT NULL ,
    `reviewcount` INT(11) DEFAULT NULL ,
    `goodcount` INT(11) DEFAULT NULL ,
    `medcount` INT(11) DEFAULT NULL ,
    `badcount` INT(11) DEFAULT NULL ,
    `tags` VARCHAR(255) DEFAULT '' ,
    `score` DOUBLE DEFAULT NULL ,
    `addtime` DATETIME NULL DEFAULT NULL ,
    `updatetime` DATETIME NULL DEFAULT NULL ,
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `spu_info`;
CREATE TABLE `spu_info`(
    `spuid` BIGINT(20) NOT NULL AUTO_INCREMENT ,
    `cid` BIGINT(20) NOT NULL ,
    `name` VARCHAR(255) NOT NULL ,
    `brand` BIGINT(20) DEFAULT NULL ,
    `product_model` VARCHAR(255) DEFAULT NULL ,
    `deleted` TINYINT(4) NOT NULL DEFAULT '0' ,
    `active` TINYINT(4) NOT NULL DEFAULT '1' ,
    `addtime` DATETIME NULL DEFAULT NULL ,
    `updatetime` DATETIME NULL DEFAULT NULL ,
    PRIMARY KEY (`spuid`)
);

DROP TABLE IF EXISTS `spu_comment`;
CREATE TABLE `spu_comment`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
    `spuid` BIGINT(20) NOT NULL ,
    `reviewcount` INT(11) DEFAULT NULL ,
    `goodcount` INT(11) DEFAULT NULL ,
    `medcount` INT(11) DEFAULT NULL ,
    `badcount` INT(11) DEFAULT NULL ,
    `tags` VARCHAR(255) DEFAULT '' ,
    `score` DOUBLE DEFAULT NULL ,
    `addtime` DATETIME NULL DEFAULT NULL ,
    `updatetime` DATETIME NULL DEFAULT NULL ,
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `bcs_cspu_image`;
CREATE TABLE `bcs_cspu_image`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
    `cspuid` BIGINT(20) NOT NULL ,
    `image_url` VARCHAR(1024) NOT NULL DEFAULT '' ,
    `image_bcs_url` VARCHAR(1024) NOT NULL DEFAULT '' ,
    `status` TINYINT(4) NOT NULL DEFAULT '0' ,
    `addtime` DATETIME NULL DEFAULT NULL ,
    `updatetime` DATETIME NULL DEFAULT NULL ,
    `errormessage` VARCHAR(255) NOT NULL DEFAULT '' ,
    `width` INT(11) NOT NULL DEFAULT '0' ,
    `height` INT(11) NOT NULL DEFAULT '0' ,
    `content_md5` CHAR(32) NOT NULL  DEFAULT '' ,
    `sequence` INT(11) NOT NULL DEFAULT '1' ,
    `gips_image` VARCHAR(1024) NULL DEFAULT NULL ,
    `type` TINYINT(4) DEFAULT NULL ,
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `sku_attribute_0`;
CREATE TABLE `sku_attribute_0`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
`sku_innerid` BIGINT(20) NOT NULL ,
`skuid` VARCHAR(128) NOT NULL ,
`merchantid` BIGINT(20) NOT NULL ,
`property_id` VARCHAR(2048) NOT NULL DEFAULT '' ,
`property_values` VARCHAR(2048) NOT NULl DEFAULT '' ,
`property_hash` CHAR(32) NOT NULL DEFAULT '' ,
`addtime` DATETIME NOT NULL ,
`updatetime` DATETIME NOT NULL ,
PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `sku_cdt_0`;
CREATE TABLE `sku_cdt_0`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
`sku_innerid` BIGINT(20) NOT NULL ,
`skuid` VARCHAR(128) NOT NULL ,
`leafcategoryid` BIGINT(20) NOT NULL ,
`classificationtype` TINYINT(4) NOT NULL ,
`confidence` DECIMAL(5,2) NOT NULL DEFAULT '0' ,
`addtime` DATETIME NOT NULL ,
`updatetime` DATETIME NOT NULL ,
PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `sku_description_0`;
CREATE TABLE `sku_description_0`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
    `merchantid` BIGINT(20) NOT NULL ,
    `sku_innerid` BIGINT(20) NOT NULL ,
    `skuid` VARCHAR(128) NOT NULL ,
    `sku_desc_ori` LONGBLOB NOT NULL ,
    `pd_hash` CHAR(32) NOT NULL DEFAULT '' ,
    `sku_desc` LONGBLOB NOT NULL ,
    `pd_status` TINYINT(4) NOT NULL DEFAULT '0' ,
    `area` INT(11) NOT NULL DEFAULT '0' ,
    `Word_count` INT(11) NOT NULL DEFAULT '0' ,
    `addtime` DATETIME NOT NULL ,
    `updatetime` DATETIME NOT NULL ,
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `sku_info_0`;
CREATE TABLE `sku_info_0`(
    `sku_innerid` BIGINT(20) NOT NULL AUTO_INCREMENT ,
    `sku_hashkey` BIGINT(20) NOT NULL ,
    `skuid` VARCHAR(128) NOT NULL ,
    `merchantid` BIGINT(20) NOT NULL ,
    `unique_key` CHAR(32) NOT NULL DEFAULT '' ,
    `feedid` BIGINT(20) NOT NULL ,
    `ucid` BIGINT(2) NOT NULL DEFAULT '0' ,
    `title` VARCHAR(1024) NOT NULL ,
    `subtitle` VARCHAR(1024) NOT NULL DEFAULT '' ,
    `url` VARCHAR(1024) NOT NULL DEFAULT '' ,
    `mobileurl` VARCHAR(1024) NOT NULL DEFAULT '' ,
    `outerid` VARCHAR(128) NOT NULL ,
    `category_ori` VARCHAR(1024) NOT NULL DEFAULT '' ,
    `brand_ori` VARCHAR(128) NOT NULL DEFAULT '' ,
    `starttime` DATETIME NOT NULL ,
    `endtime` DATETIME NOT NULL ,
    `merchant_status` TINYINT(4) NOT NULL DEFAULT '0' ,
    `manual_status` TINYINT(4) NOT NULL DEFAULT '0' ,
    `signature` VARCHAR(256) NOT NULL ,
    `isselfopen` TINYINT(4) NOT NULL ,
    `dataversion` BIGINT(20) NOT NULL ,
    `inactivetime` DATETIME NOT NULL ,
    `addtime` DATETIME NOT NULL ,
    `updatetime` DATETIME NOT NULL ,
    `deleted` TINYINT(4) NOT NULL DEFAULT '0' ,
    `seller_name` VARCHAR(128) NOT NULL DEFAULT '' ,
    `upc` BIGINT(20) NOT NULL DEFAULT '0' ,
    PRIMARY KEY (`sku_innerid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `sku_pps_0`;
CREATE TABLE `sku_pps_0`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
    `merchantid` BIGINT(20) NOT NULL ,
    `sku_innerid` BIGINT(20) NOT NULL ,
    `skuid` VARCHAR(128) NOT NULL ,
    `regionid` INT(11) NOT NULL ,
    `terminal` TINYINT(4) NOT NULL ,
    `promotion_info` VARCHAR(1024) NOT NULl DEFAULT '' ,
    `promotion_price_machine` DECIMAL(10,2) NOT NULL DEFAULT '0' ,
    `promotion_price_manual` DECIMAL(10,2) NOT NULL DEFAULT '0' ,
    `originalprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' ,
    `discountprice` DECIMAL(10,2) NOT NULL DEFAULT '0.00' ,
    `stock` TINYINT(4) NOT NULL DEFAULT '0' ,
    `post_pay` TINYINT(4) NOT NULL DEFAULT '0' ,
    `m_update_time` DATETIME NOT NULL ,
    `addtime` DATETIME NOT NULL ,
    `updatetime` DATETIME NOT NULL ,
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `sku_level_0`;
CREATE TABLE `sku_level_0`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
    `sku_innerid` BIGINT(20) NOT NULL ,
    `skuid` VARCHAR(128) NOT NULL ,
    `level` INT(11) NOT NULL DEFAULT '0' ,
    `addtime` DATETIME NOT NULL ,
    `updatetime` DATETIME NOT NULL ,
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `sku_cspu_0`;
CREATE TABLE `sku_cspu_0`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
    `sku_innerid` BIGINT(20) NOT NULL ,
    `skuid` VARCHAR(128) NOT NULL ,
    `cspuid` BIGINT(20) NOT NULL ,
    `type` TINYINT(4)  NOT NULL ,
    `confidence`DECIMAL(5,2) NOT NULL DEFAULT '0',
    `addtime` DATETIME NOT NULL ,
    `updatetime` DATETIME NOT NULL ,
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `sku_comment_0`;
CREATE TABLE `sku_comment_0`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
    `sku_innerid` BIGINT(20) NOT NULL ,
    `skuid` VARCHAR(128) NOT NULL ,
    `reviewcount` INT(11) NOT NULL ,
    `goodcount` INT(11) NOT NULL ,
    `medcount` INT(11) NOT NULL ,
    `badcount` INT(11) NOT NULL ,
    `tags` VARCHAR(1024) NOT NULL DEFAULT '' ,
    `score` DOUBLE NOT NULL ,
    `addtime` DATETIME NOT NULL ,
    `updatetime` DATETIME NOT NULL ,
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `bcs_sku_image_0`;
CREATE TABLE `bcs_sku_image_0`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
    `sku_innerid` BIGINT(20) NOT NULL ,
    `skuid` VARCHAR(128) NOT NULL ,
    `image_url` VARCHAR(1024) NOT NULL DEFAULT '' ,
    `image_bcs_url` VARCHAR(1024) NOT NULL DEFAULT '' ,
    `status` TINYINT(4) NOT NULL DEFAULT '3' ,
    `addtime` DATETIME NOT NULL ,
    `updatetime` DATETIME NOT NULL ,
    `errormessage` VARCHAR(255) NOT NULL DEFAULT '' ,
    `width` INT(11) NOT NULL DEFAULT '0' ,
    `height` INT(11) NOT NULL DEFAULT '0' ,
    `content_md5` CHAR(32) NOT NULL  DEFAULT '' ,
    `sequence` INT(11) NOT NULL DEFAULT '1' ,
    `gips_image` VARCHAR(1024) NOT NULL ,
    `type` TINYINT(4) NOT NULL ,
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

 DROP TABLE IF EXISTS `merchant_info`;
CREATE TABLE `merchant_info`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
    `ucid` BIGINT(20) DEFAULT NULL ,
    `name` VARCHAR(100) NOT NULL DEFAULT '' ,
    `addtime` DATETIME NOT NULL  DEFAULT '1970-01-01 00:00:00'  ,
    `updatetime` DATETIME NOT NULL  DEFAULT '1970-01-01 00:00:00'  ,
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `region_map`;
CREATE TABLE `region_map`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
    `cityid` INT(11) NOT NULL ,
    `cityname` VARCHAR(100) DEFAULT NULL ,
    `regionid` INT(11) NOT NULL ,
    `regionname` VARCHAR(100) DEFAULT NULL ,
    `addtime` DATETIME NOT NULL  DEFAULT '1970-01-01 00:00:00' ,
    `updatetime` DATETIME NOT NULL  DEFAULT '1970-01-01 00:00:00' ,
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `merchant_router`;
CREATE TABLE `merchant_router`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
    `merchant_id` BIGINT(20) NOT NULL ,
    `used_shards` VARCHAR(255) NOT NULL ,
    `merchant_amount_type` TINYINT(4) NOT NULL DEFAULT 0 ,
    `merchant_router_stat` TINYINT(4) NOT NULL DEFAULT 0 NULL ,
    `addtime` DATETIME NULL DEFAULT NULL ,
    `updatetime` DATETIME NULL DEFAULT NULL ,
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `item_attribute`;
CREATE TABLE `item_attribute`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
	`itemid` BIGINT(20) NOT NULL ,
	`property_id` VARCHAR(2048) NOT NULL ,
	`property_values` VARCHAR(2048) NOT NULL ,
	`property_md5` CHAR(32) NOT NULL ,
	`addtime` DATETIME NOT NULL ,
	`updatetime` DATETIME NOT NULL ,
PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `item_cdt`;
CREATE TABLE `item_cdt`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
    `itemid` BIGINT(20) NOT NULL ,
    `leafcategoryid` BIGINT(20) NOT NULL ,
    `classificationtype` TINYINT(4) NOT NULL DEFAULT 1 ,
    `operator` BIGINT(20) NOT NULL ,
    `confidence` INT(11) NOT NULL DEFAULT 100 ,
    `addtime` DATETIME NOT NULL ,
    `updatetime` DATETIME NOT NULL ,
    `cdt_md5` CHAR(32) NOT NULL ,
PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `item_description`;
CREATE TABLE `item_description`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
    `merchantid` INT(11) NOT NULL ,
    `shopid` INT(11) NOT NULL ,
    `itemid` BIGINT(20) NOT NULL ,
    `item_desc_ori` LONGBLOB NOT NULL ,
    `pd_md5` CHAR(32) NOT NULL ,
    `item_desc` LONGBLOB NOT NULL ,
    `addtime` DATETIME NOT NULL ,
    `updatetime` DATETIME NOT NULL ,
PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `item_info`;
CREATE TABLE `item_info`(
    `itemid` BIGINT(20) NOT NULL AUTO_INCREMENT ,
    `merchantid` INT(11) NOT NULL ,
    `ucid` BIGINT(20) NOT NULL ,
    `shopid` INT(11) NOT NULL ,
    `outerid` VARCHAR(128) NOT NULL DEFAULT ''  ,
    `item_info_hashcode` int(11) NOT NULL,
    `title` VARCHAR(1024) NOT NULL ,
    `subtitle` VARCHAR(1024) NOT NULL DEFAULT '' ,
    `url` VARCHAR(1024) NOT NULL DEFAULT '' ,
    `starttime` DATETIME NOT NULL ,
    `endtime` DATETIME NOT NULL ,
    `merchant_status` TINYINT(4) NOT NULL ,
    `manual_status` TINYINT(4) NOT NULL ,
    `shopcategory` VARCHAR(1024) NOT NULL DEFAULT '' ,
    `dataversion` BIGINT(20) NOT NULL DEFAULT 0 ,
    `addtime` DATETIME NOT NULL ,
    `updatetime` DATETIME NOT NULL ,
    `ware_big_small_model` INT(11) NOT NULL ,
    `ware_pack_type` INT(11) NOT NULL DEFAULT 1 ,
    `deleted` TINYINT(4) NOT NULL DEFAULT 0 ,
    `category_ori` VARCHAR(1024) NOT NULL DEFAULT '' ,
    `brand_ori` CHAR(128) NOT NULL DEFAULT '' ,
PRIMARY KEY (`itemid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `sku_info`;
CREATE TABLE `sku_info`(
    `skuid` BIGINT(20) NOT NULL AUTO_INCREMENT  ,
    `sku_type` TINYINT(4) NOT NULL DEFAULT 0  ,
    `sell_attribute` VARCHAR(2048) NOT NULL  ,
    `property_values` VARCHAR(2048) NOT NULL  ,
    `upc` VARCHAR(128) NOT NULL DEFAULT 0 ,
    `itemid` BIGINT(20) NOT NULL ,
    `deleted` TINYINT(4) NOT NULL DEFAULT 0 ,
    `weight` DECIMAL(10,2) NOT NULL DEFAULT 0 ,
    `volume` DECIMAL(10,2) NOT NULL DEFAULT 0 ,
    `addtime` DATETIME NOT NULL ,
    `updatetime` DATETIME NOT NULL ,
    `outerid` VARCHAR(128) NOT NULL DEFAULT '',
PRIMARY KEY (`skuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `sku_pps`;
CREATE TABLE `sku_pps`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
    `shopid` INT(11) NOT NULL ,
    `merchantid` BIGINT(20) NOT NULL ,
    `skuid` BIGINT(20) NOT NULL ,
    `regionid` INT(11) NOT NULL DEFAULT 0 ,
    `pc_mobile` TINYINT(4) NOT NULL DEFAULT 0 ,
    `price` DECIMAL(10,2) NOT NULL ,
    `discount_price` DECIMAL(10,2) NOT NULL ,
    `flash_price` DECIMAL(10,2) NOT NULL DEFAULT 0 ,
    `ticket_price` DECIMAL(10,2) NOT NULL DEFAULT 0 ,
    `promotion_type` TINYINT(4) NOT NULL DEFAULT 0 ,
    `stock` INT(11) NOT NULL ,
    `post_pay` TINYINT(4) NOT NULL DEFAULT 0 ,
    `m_update_time` DATETIME NOT NULL ,
    `inventory_warning` INT(11) NOT NULL DEFAULT 0 ,
    `addtime` DATETIME NOT NULL ,
    `updatetime` DATETIME NOT NULL ,
    `pps_md5` CHAR(32) NOT NULL ,
PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `bcs_image`;
CREATE TABLE `bcs_image`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
    `itemid` BIGINT(20) NOT NULL ,
    `skuid` BIGINT(20) NOT NULL DEFAULT 0 ,
    `imageUrl` VARCHAR(1024) NOT NULL ,
    `imageBCSUrl` VARCHAR(1024) NOT NULL ,
    `bcs_status` TINYINT(4) NOT NULL DEFAULT 3 ,
    `addtime` DATETIME NOT NULL ,
    `updatetime` DATETIME NOT NULL ,
    `errormessage` VARCHAR(255) NOT NULL DEFAULT '' ,
    `width` INT(11) NOT NULL ,
    `height` INT(11) NOT NULL ,
    `content_md5` CHAR(32) NOT NULL ,
    `sequence` INT(11) NOT NULL ,
    `gips_image` VARCHAR(1024) NOT NULL ,
    `pic_type` TINYINT(4) NOT NULL ,
PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `demo`;
CREATE TABLE `demo` (
   `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
   `msg` BIGINT(20) NOT NULL DEFAULT '0',
   PRIMARY KEY (`id`)
 ) ENGINE=INNODB DEFAULT CHARSET=utf8;