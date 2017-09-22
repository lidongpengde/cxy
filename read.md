CREATE TABLE `advice_box` (
  `advice_box_id` bigint(10) NOT NULL,
  `advice_content` varchar(45) DEFAULT NULL,
  `advice_title` varchar(45) DEFAULT NULL,
  `advice_user_id` bigint(10) DEFAULT NULL,
  `advice_user_name` varchar(45) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`advice_box_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
