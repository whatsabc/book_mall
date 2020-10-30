use com;

-- ----------------------------
-- author Jianshu
-- time 20201029
-- ----------------------------

-- ----------------------------
-- Table structure for mall_book 图书
-- ----------------------------
CREATE TABLE `mall_book`
(
	`book_isbn` VARCHAR(30) PRIMARY KEY COMMENT 'isbn',
	`book_name` VARCHAR(100) NOT NULL COMMENT '书名',
	`book_author` VARCHAR(100) COMMENT '作者',
	`book_press` VARCHAR(50) COMMENT '出版社',
	`book_pubdate` DATE DEFAULT NULL COMMENT '出版日期',
	`book_price` DECIMAL(20,2) NOT NULL COMMENT '价格，单位-元保留两位小数',
	`book_stock` INT NOT NULL COMMENT '库存总量',
	`book_status` INT DEFAULT '1' COMMENT '商品状态.1-在售 2-下架 3-删除',
	`book_category` VARCHAR(10) DEFAULT NULL COMMENT '图书分类',
	`book_createtime` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`book_update_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
	UNIQUE KEY `book_isbn_unique` (`book_isbn`) USING BTREE
);

-- ----------------------------
-- Table structure for mall_user 用户
-- ----------------------------
CREATE TABLE `mall_user`
(
	`user_uid` INT AUTO_INCREMENT PRIMARY KEY COMMENT '用户uid',
	`user_nickname` VARCHAR(50) NOT NULL COMMENT '昵称',
	`user_password` VARCHAR(50) NOT NULL COMMENT '密码，MD5加密',
	`user_name` VARCHAR(20) NOT NULL COMMENT '用户姓名',
	`user_sex` VARCHAR(2) CHECK(user_sex IN('男','女')) COMMENT '用户性别',
	`user_tel` VARCHAR(20) NOT NULL,
	`user_role` INT NOT NULL COMMENT '角色0-管理员，1普通用户',
	`create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '账户创建时间',
	`update_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
	UNIQUE KEY `user_nickname_unique` (`user_nickname`) USING BTREE
);

-- ----------------------------
-- Table structure for mall_order 订单
-- ----------------------------
CREATE TABLE `mall_order`
(
	`order_id` INT AUTO_INCREMENT PRIMARY KEY,
	`user_uid` INT,
	`book_isbn` CHAR(40),
	`order_status` INT DEFAULT NULL COMMENT '订单状态:0-已取消-10-未付款，20-已付款，40-已发货，50-交易成功，60-交易关闭',
	`order_payment` DECIMAL(20,2) DEFAULT NULL COMMENT '实际付款金额,单位是元,保留两位小数',
	`send_time` datetime DEFAULT NULL COMMENT '发货时间',
  	`end_time` datetime DEFAULT NULL COMMENT '交易完成时间',
  	`close_time` datetime DEFAULT NULL COMMENT '交易关闭时间',
	`order_createtime` datetime DEFAULT NULL COMMENT '创建时间',
  	`order_updatetime` datetime DEFAULT NULL COMMENT '更新时间',
 	FOREIGN KEY (`user_uid`) REFERENCES `mall_user`(`user_uid`),
 	FOREIGN KEY (`book_isbn`) REFERENCES `mall_book`(`book_isbn`)
);

-- ----------------------------
-- Table structure for mmall_order_item 订单子表
-- ----------------------------
CREATE TABLE `mall_order_item` (
	`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '订单子表id',
	`user_id` INT DEFAULT NULL,
	`order_id` INT,
	`order_no` bigint(20) DEFAULT NULL,
	`product_id` INT DEFAULT NULL COMMENT '商品id',
	`product_name` varchar(100) DEFAULT NULL COMMENT '商品名称',
	`product_image` varchar(500) DEFAULT NULL COMMENT '商品图片地址',
	`current_unit_price` decimal(20,2) DEFAULT NULL COMMENT '生成订单时的商品单价，单位是元,保留两位小数',
	`quantity` INT DEFAULT NULL COMMENT '商品数量',
	`total_price` decimal(20,2) DEFAULT NULL COMMENT '商品总价,单位是元,保留两位小数',
	`create_time` datetime DEFAULT NULL,
	`update_time` datetime DEFAULT NULL
);

-- ----------------------------
-- Table structure for mall_cart 购物车
-- ----------------------------
CREATE TABLE `mall_cart` (
	`cart_id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`user_id` INT NOT NULL,
	`book_isbn` CHAR(40) DEFAULT NULL COMMENT '图书isbn',
	`book_quantity` INT DEFAULT NULL COMMENT '数量',
	`book_checked` INT DEFAULT NULL COMMENT '是否选择,1=已勾选,0=未勾选',
	`cart_createtime` datetime DEFAULT NULL COMMENT '创建时间',
	`cart_updatetime` datetime DEFAULT NULL COMMENT '更新时间',
	FOREIGN KEY (`user_uid`) REFERENCES `mall_user`(`user_uid`),
	FOREIGN KEY (`book_isbn`) REFERENCES `mall_book`(`book_isbn`)
);