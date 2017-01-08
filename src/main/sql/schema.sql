-- 数据库初始化脚本

-- 创建数据库

CREATE DATABASE `movie_ticket`;

--  使用数据库
USE `movie_ticket`;

-- 创建电影票信息
CREATE TABLE ticket(
`ticket_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '电影票id',
`name` VARCHAR(120) NOT NULL COMMENT '商品名称',
`number` INT NOT NULL COMMENT '库存数量',
`create_time` TIMESTAMP NOT NULL DEFAULT  CURRENT_TIMESTAMP COMMENT '创建时间',
`start_time` TIMESTAMP  NOT  NULL  COMMENT '秒杀开启时间',
`end_time` TIMESTAMP NOT NULL COMMENT '秒杀结束时间',
PRIMARY KEY (ticket_id),
KEY idx_start_time(start_time),
KEY idx_end_time(end_time)
)ENGINE=INNODB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT ='电影票信息';

-- 电影票抢购明细表

CREATE TABLE grab_ticket_record(
`id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '自增id',
`ticket_id` BIGINT NOT NULL COMMENT '电影票id',
`user_id` VARCHAR(64) NOT NULL COMMENT '用户id',
`state` int(1) NOT NULL DEFAULT -1 COMMENT  '状态标识：-1代表无效，0代表成功，1代表付款',
`create_time` TIMESTAMP  NOT NULL COMMENT '创建时间',
PRIMARY KEY (id),
CONSTRAINT idx_user_ticket UNIQUE(ticket_id,user_id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT '电影票抢购明细表';

-- 用户登录认证相关的信息
CREATE TABLE user(
`user_id` VARCHAR(64) NOT NULL COMMENT '用户id',
`username` VARCHAR(64) NOT NULL COMMENT '用户名称',
`password` VARCHAR(64) NOT NULL COMMENT '用户密码',
`mail` VARCHAR(64) NOT NULL COMMENT '邮箱',
`delivery_address` VARCHAR(255) COMMENT  '收货地址',
`create_time` TIMESTAMP  NOT NULL DEFAULT  CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (user_id),
UNIQUE (username),/*名称为唯一 */
KEY idx_create_time(create_time)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT '用户信息表';

-- 初始化数据
INSERT  INTO
	ticket (`name`,number,start_time,end_time)
VALUES
	('长城',40,'2017-01-02 00:00:00','2017-01-03 00:00:00'),
	('铁道飞虎',20,'2017-01-05 00:00:00','2017-01-06 00:00:00'),
	('情圣',30,'2017-01-06 00:00:00','2017-01-07 00:00:00'),
	('星球大战外传',40,'2017-01-02 00:00:00','2017-01-03 00:00:00');
