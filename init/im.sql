SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS t_friends;
DROP TABLE IF EXISTS t_group;
DROP TABLE IF EXISTS t_group_user;
DROP TABLE IF EXISTS t_message;
DROP TABLE IF EXISTS t_message_user;
DROP TABLE IF EXISTS t_user;




/* Create Tables */

CREATE TABLE t_friends
(
	user_id varchar(64),
	friend_id varchar(64),
	update_time datetime,
	-- 个人消息,群组消息
	type varchar(10) COMMENT '个人消息,群组消息'
);


CREATE TABLE t_group
(
	id varchar(64) NOT NULL,
	create_user varchar(64),
	name varchar(200),
	create_time datetime,
	update_time datetime,
	del_flag char(1),
	PRIMARY KEY (id)
);


CREATE TABLE t_group_user
(
	group_id varchar(64),
	user_id varchar(64)
);


CREATE TABLE t_message
(
	id varchar(64),
	sender varchar(64),
	create_time datetime,
	cotent text,
	-- (链接,图片,附件)
	content_type char(1) COMMENT '(链接,图片,附件)',
	group_id varchar(64),
	del_flag char(1)
);


CREATE TABLE t_message_user
(
	message_id varchar(64),
	receiver varchar(64),
	group_id varchar(64),
	read_flag char(1),
	del_flag char(1)
);


CREATE TABLE t_user
(
	id varchar(64) NOT NULL,
	name varchar(200),
	image varchar(2000),
	no varchar(200),
	status char(1),
	create_time datetime,
	update_time datetime,
	del_flag char(1),
	PRIMARY KEY (id)
);



