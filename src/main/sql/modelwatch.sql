CREATE TABLE `user` (
  `U_id` int(10) NOT NULL auto_increment,
  `U_username` varchar(30) DEFAULT NULL unique ,
  `U_avater` varchar(255) DEFAULT NULL,
  `U_password` varchar(150) DEFAULT NULL,
  `U_email` varchar(255) DEFAULT NULL unique ,
  `U_sex` varchar(1) DEFAULT NULL,
  `U_birthday` date DEFAULT NULL,
  `U_signature` varchar(100) DEFAULT NULL,
  `U_createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP(),
  PRIMARY KEY (`U_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `class` (
  `C_id` int(10) NOT NULL auto_increment ,
  `C_name` varchar(30) DEFAULT NULL,
  `C_context` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`C_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `project` (
  `P_id` bigint(20) NOT NULL auto_increment,
  `P_createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `P_name` varchar(30) DEFAULT NULL,
  `P_context` text,
  `P_preview` varchar(255) DEFAULT NULL,
  `P_Uid` int(10) DEFAULT NULL,
  PRIMARY KEY (`P_id`),
  foreign key (P_Uid) references user(U_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `log` (
  `L_id` bigint(20) NOT NULL auto_increment,
  `L_context` varchar(255) DEFAULT NULL,
  `L_Time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `L_pid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`L_id`),
  foreign key (L_pid) references project(P_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `model` (
  `M_id` bigint(20) NOT NULL auto_increment,
  `M_data` varchar(255) DEFAULT NULL,
  `M_preview` varchar(255) DEFAULT NULL,
  `M_name` varchar(30) DEFAULT NULL,
  `M_createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `M_pid` bigint(20) DEFAULT NULL,
  `M_cid` int(11) DEFAULT NULL,
  PRIMARY KEY (`M_id`),
  foreign key (M_pid) references project(P_id),
  foreign key (M_cid) references class(C_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `subscribe` (
  `Sub_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `Sub_pid` bigint(20) not NULL,
  `Sub_uid` int(10) not null ,
  primary key (Sub_pid,Sub_uid),
  foreign key (Sub_pid) references project(P_id),
  foreign key (Sub_uid) references user(U_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `invitation` (
  `Inv_Time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `Inv_state` tinyint(1) DEFAULT NULL ,
  `Inv_pid` bigint(20) NOT NULL,
  `Inv_uid` int(10) not NULL,
  primary key (Inv_pid,Inv_uid),
  foreign key (Inv_uid) references user(U_id),
  foreign key (Inv_pid) references project(P_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `mark` (
  `Mark_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `Mark_uid` int(10) not NULL,
  `Mark_mid` bigint(20) not NULL,
  primary key (Mark_uid,Mark_mid),
  foreign key (Mark_mid) references model(M_id),
  foreign key (Mark_uid) references user(U_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;