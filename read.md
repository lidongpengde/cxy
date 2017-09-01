--------------------------
CREATE TABLE `line_info` (
  `lid` int(11) NOT NULL AUTO_INCREMENT,
  `start` varchar(100) DEFAULT NULL COMMENT '出发地',
  `end` varchar(100) DEFAULT NULL COMMENT '到达',
  `start_time` timestamp(6) NULL DEFAULT NULL COMMENT '出发时间',
  `plate_number` int(11) DEFAULT NULL COMMENT '车牌号',
  `price` double DEFAULT NULL COMMENT '价格',
  `isBargin` int(11) DEFAULT NULL COMMENT '是否接受议价0不接受1接受',
  `status` int(11) DEFAULT NULL COMMENT '订单状态0已取消1进行中2已完成',
  `person_count` int(11) DEFAULT NULL COMMENT '乘车人数',
  `type` int(11) DEFAULT NULL COMMENT '0乘客1司机',
  `user_id` varchar(45) DEFAULT NULL COMMENT '发布人',
  PRIMARY KEY (`lid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `mobile` bigint(20) NOT NULL COMMENT '用户mobile',
  `age` bigint(30) NOT NULL COMMENT '年龄',
  `sex` varchar(10) NOT NULL COMMENT '性别',
  `user_name` varchar(30) DEFAULT NULL COMMENT '用户名）',
  `pass_word` varchar(30) DEFAULT NULL COMMENT '密码',
  `nick_name` bigint(30) DEFAULT NULL COMMENT '身份证号',
  `identify_status` bigint(10) DEFAULT NULL COMMENT '实名认证状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

CREATE TABLE `order_from` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `line_info_id` int(11) DEFAULT NULL,
  `publisher_id` int(11) DEFAULT NULL,
  `subscriber_id` int(11) DEFAULT NULL,
  `order_status` int(11) DEFAULT NULL COMMENT '0待确认1已确认2已完成',
  `create_time` datetime DEFAULT NULL,
  `line_info_price` double DEFAULT NULL,
  `line_info_start` varchar(45) DEFAULT NULL,
  `line_info_end` varchar(45) DEFAULT NULL,
  `publisher_name` varchar(45) DEFAULT NULL,
  `publisher_mobile` bigint(13) DEFAULT NULL,
  `subscriber_name` varchar(45) DEFAULT NULL,
  `subscriber_mobile` bigint(13) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='订单';

CREATE TABLE `comment` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `commenter_id` int(11) DEFAULT NULL,
  `commenter_name` varchar(45) DEFAULT NULL,
  `commentered_id` int(11) DEFAULT NULL,
  `commentered_name` varchar(45) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `comment_time` datetime DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评价';


配色方案：http://www.colorhunt.co/c/65978