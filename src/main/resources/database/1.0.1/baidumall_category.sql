DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`(
	`categoryid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增类目id',
	`parentid` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '父类目id(root节点的父节点id为0)',
	`name` VARCHAR(255) NOT NULL COMMENT '表示名称',
	`isleaf` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '1:叶节点 0:非叶节点',
	`pos` INT(11) NOT NULL DEFAULT '9999' COMMENT '优先级(Mall商家上传时候，确定类目排序)',
	`addtime`  DATETIME NOT NULL COMMENT '增加时间',
	`updatetime`  DATETIME NOT NULL COMMENT '更新时间',
	`deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否被删除，0：没有删除，1：删除',
	PRIMARY KEY (`categoryid`),
	KEY `idx_parentid` (`parentid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '后台类目表';

DROP TABLE IF EXISTS `category_property`;
CREATE TABLE `category_property`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
	`c_id` INT(11) NOT NULL COMMENT '叶子类目id',
	`isleaf` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '1:叶节点 0:非叶节点',
	`name` VARCHAR(100) NOT NULL COMMENT '属性项的名称',
	`pid` BIGINT(20) NOT NULL COMMENT '属性项的字典id',
	`is_required` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '1:必填 0:非必填',
	`is_selfdefine` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '1:自定义  0：非自定义',
	`type` TINYINT(4) NOT NULL DEFAULT '2' COMMENT '(0:STRING,1:KEY,2:ITEM,3:DOUBLE,4:LONG,5:ENUM,6:ARRAY,7:EAN...)筛选项的ENUM是单选，ARRAY是多选',
	`value_range` VARCHAR(400) NOT NULL COMMENT '属性值校验规则(用在通用属性上，Mall商家上传时候需要校验)',
	`prop_type` TINYINT(4) NOT NULL COMMENT '属性类型（0:item通用属性，1:KEY关键属性，2:NON_KEY非关键属性，3:SALE销售属性）',
	`sequence` INT(11) NOT NULL DEFAULT '9999' COMMENT '位置信息，发布商品的时候决定哪个先显示',
	`active` TINYINT(4) NOT NULL DEFAULT '1' COMMENT '是否有效',
	`addtime`  DATETIME NOT NULL COMMENT '增加时间',
	`updatetime`  DATETIME NOT NULL COMMENT '更新时间',
	PRIMARY KEY (`id`),
	KEY `idx_c_id_pid` (`c_id`, `pid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '后台属性项表';

DROP TABLE IF EXISTS `category_property_value`;
CREATE TABLE `category_property_value`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
	`c_id` INT(11) NOT NULL COMMENT '叶子类目id',
	`isleaf` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '1:叶节点 0:非叶节点',
	`pid` BIGINT(20) NOT NULL COMMENT '属性项的字典id',
	`vid` BIGINT(20) NOT NULL COMMENT '属性值字典id',
	`alias` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '属性值别名（明文），可以存多个，以分号分开',
	`image_url` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '运营订属性值对应图片，一般是颜色',
	`active` TINYINT(4) NOT NULL DEFAULT '1' COMMENT '是否有效',
	`addtime`  DATETIME NOT NULL COMMENT '增加时间',
	`updatetime`  DATETIME NOT NULL COMMENT '更新时间',
	PRIMARY KEY (`id`),
	KEY `idx_c_id_pid` (`c_id`, `pid`),
	KEY `idx_vid` (`vid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '后台属性值表';

DROP TABLE IF EXISTS `base_property`;
CREATE TABLE `base_property`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
	`property_name` VARCHAR(100) NOT NULL COMMENT '属性中文名',
	`en_name` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '属性英文名',
	PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '后台属性项字典表';

DROP TABLE IF EXISTS `base_val`;
CREATE TABLE `base_val`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
	`value` VARCHAR(100) NOT NULL COMMENT '属性值',
	PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '后台属性值字典表';

DROP TABLE IF EXISTS `cm_category`;
CREATE TABLE `cm_category`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id（前台类目id）',
	`name` VARCHAR(200) NOT NULL COMMENT '类目名称',
	`parentid` INT(11) NOT NULL DEFAULT '-1' COMMENT '父类目id',
	`isleaf` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '1:叶节点 0:非叶节点',
	`pos` INT(11) NOT NULL DEFAULT '9999' COMMENT '排序位置',
	`active` TINYINT(4) NOT NULL DEFAULT '1' COMMENT '是否有效',
	`updatetime`  DATETIME NOT NULL COMMENT '更新时间',
	PRIMARY KEY (`id`),
	KEY `idx_parentid` (`parentid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '前台类目表';

DROP TABLE IF EXISTS `category_map`;
CREATE TABLE `category_map`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
	`cm_cid` INT(11) NOT NULL COMMENT '前端类目id',
	`base_cid` INT(11) NOT NULL COMMENT '后端类目id',
	`active` TINYINT(4) NOT NULL DEFAULT '1' COMMENT '是否有效',
	`updatetime`  DATETIME NOT NULL COMMENT '更新时间',
	PRIMARY KEY (`id`),
	KEY `idx_cm_cid` (`cm_cid`),
	KEY `idx_base_cid` (`base_cid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '前后台类目映射表';

DROP TABLE IF EXISTS `cm_property_value`;
CREATE TABLE `cm_property_value`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
	`cid` INT(11) NOT NULL COMMENT '前台类目id（TODO，确认是否需要，原微购没有）',
	`cm_pid` INT(11) NOT NULL COMMENT '前台属性项ID',
	`dic_vid` INT(11) NOT NULL COMMENT '前台属性值字典ID',
	`pos` INT(11) NOT NULL DEFAULT '9999' COMMENT '位置',
	`active` TINYINT(4) NOT NULL DEFAULT '1' COMMENT '是否有效',
	`updatetime`  DATETIME NOT NULL COMMENT '更新时间',
	PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '前台属性值';

DROP TABLE IF EXISTS `cm_property`;
CREATE TABLE `cm_property`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
	`cm_cid` INT(11) NOT NULL COMMENT '前端类目id',
	`dic_pid` INT(11) NOT NULL COMMENT '前台属性项ID',
	`base_pid` INT(11) NOT NULL COMMENT '后台属性项ID',
	`pos` INT(11) NOT NULL DEFAULT '9999' COMMENT '位置',
	`active` TINYINT(4) NOT NULL DEFAULT '1' COMMENT '是否有效',
	`updatetime`  DATETIME NOT NULL COMMENT '更新时间',
	PRIMARY KEY (`id`),
	KEY `idx_cm_cid` (`cm_cid`),
	KEY `idx_dic_pid` (`dic_pid`),
	KEY `idx_base_pid` (`base_pid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT ='前台属性项(包含和后台的映射)';

DROP TABLE IF EXISTS `property_value_map`;
CREATE TABLE `property_value_map`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
	`cm_vid` INT(11) NOT NULL COMMENT '前台属性值ID',
	`base_vid` INT(11) NOT NULL COMMENT '后台属性值ID',
	`active` TINYINT(4) NOT NULL DEFAULT '1' COMMENT '是否有效',
	`updatetime`  DATETIME NOT NULL COMMENT '更新时间',
	PRIMARY KEY (`id`),
	KEY `idx_cm_vid` (`cm_vid`),
	KEY `idx_base_vid` (`base_vid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '前后台属性值映射表';

DROP TABLE IF EXISTS `base_cm_value`;
CREATE TABLE `base_cm_value`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
	`value` VARCHAR(100) NOT NULL COMMENT '前台属性值名称',
	PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '前台属性值字典';

DROP TABLE IF EXISTS `base_cm_property`;
CREATE TABLE `base_cm_property`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
	`name` VARCHAR(100) NOT NULL COMMENT '前台属性名称',
	PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '前台属性字典表';